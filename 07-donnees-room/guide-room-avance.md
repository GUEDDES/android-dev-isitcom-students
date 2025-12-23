# Guide avancé Room Database

## 🎯 Objectifs

Ce guide couvre les aspects avancés de Room, notamment :
- Convertisseurs de types (TypeConverter)
- Gestion des dates
- Relations entre tables
- Migration de base de données
- Requêtes complexes

---

## 📅 Convertisseurs de dates (TypeConverter)

### Problème

Room ne peut stocker que des types primitifs. Pour stocker des objets `Date`, il faut les convertir en `Long` (timestamp).

### Solution : TypeConverter

#### 1. Créer la classe Converters

```java
package tn.isitcom.myapp.data.converter;

import androidx.room.TypeConverter;
import java.util.Date;

public class Converters {
    
    /**
     * Convertit un timestamp Long en objet Date
     * Utilisé lors de la LECTURE depuis la base de données
     * 
     * @param value Timestamp en millisecondes (peut être null)
     * @return Objet Date correspondant, ou null si value est null
     */
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    
    /**
     * Convertit un objet Date en timestamp Long
     * Utilisé lors de l'ÉCRITURE dans la base de données
     * 
     * @param date Objet Date à convertir (peut être null)
     * @return Timestamp en millisecondes, ou null si date est null
     */
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
```

#### 2. Enregistrer dans la Database

```java
package tn.isitcom.myapp.data.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;

import tn.isitcom.myapp.data.converter.Converters;
import tn.isitcom.myapp.data.model.Task;
import tn.isitcom.myapp.data.dao.TaskDao;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})  // ⭐ IMPORTANT : Enregistrer les convertisseurs
public abstract class AppDatabase extends RoomDatabase {
    
    public abstract TaskDao taskDao();
    
    private static volatile AppDatabase INSTANCE;
    
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        "app_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
```

#### 3. Utiliser Date dans l'Entity

```java
package tn.isitcom.myapp.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import java.util.Date;

@Entity(tableName = "tasks")
public class Task {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "title")
    private String title;
    
    @ColumnInfo(name = "description")
    private String description;
    
    @ColumnInfo(name = "created_at")
    private Date createdAt;  // ⭐ Objet Date directement
    
    @ColumnInfo(name = "due_date")
    private Date dueDate;    // ⭐ Stocké automatiquement comme Long
    
    @ColumnInfo(name = "is_completed")
    private boolean isCompleted;
    
    // Constructeur
    public Task(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.createdAt = new Date();  // Date actuelle
        this.dueDate = dueDate;
        this.isCompleted = false;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
```

---

## 🔍 Requêtes avec dates

### DAO avec filtres de dates

```java
package tn.isitcom.myapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import java.util.Date;
import java.util.List;

import tn.isitcom.myapp.data.model.Task;

@Dao
public interface TaskDao {
    
    @Insert
    void insert(Task task);
    
    @Update
    void update(Task task);
    
    @Delete
    void delete(Task task);
    
    @Query("SELECT * FROM tasks ORDER BY created_at DESC")
    LiveData<List<Task>> getAllTasks();
    
    /**
     * Récupérer les tâches créées après une date donnée
     * Le timestamp est automatiquement converti grâce au TypeConverter
     */
    @Query("SELECT * FROM tasks WHERE created_at > :afterDate ORDER BY created_at DESC")
    LiveData<List<Task>> getTasksAfter(Date afterDate);
    
    /**
     * Récupérer les tâches dont la date limite est entre deux dates
     */
    @Query("SELECT * FROM tasks WHERE due_date BETWEEN :startDate AND :endDate")
    LiveData<List<Task>> getTasksBetween(Date startDate, Date endDate);
    
    /**
     * Récupérer les tâches en retard (date limite passée et non complétées)
     */
    @Query("SELECT * FROM tasks WHERE due_date < :currentDate AND is_completed = 0")
    LiveData<List<Task>> getOverdueTasks(Date currentDate);
    
    /**
     * Compter les tâches créées aujourd'hui
     */
    @Query("SELECT COUNT(*) FROM tasks WHERE created_at >= :startOfDay AND created_at < :endOfDay")
    int countTasksToday(Date startOfDay, Date endOfDay);
    
    @Query("DELETE FROM tasks")
    void deleteAll();
}
```

---

## 💻 Utilisation dans le code

### Exemple 1 : Insérer une tâche avec date

```java
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    
    private AppDatabase db;
    private TaskDao taskDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = AppDatabase.getInstance(this);
        taskDao = db.taskDao();
        
        Button btnAdd = findViewById(R.id.btnAddTask);
        btnAdd.setOnClickListener(v -> addTask());
    }
    
    private void addTask() {
        // Créer une date limite (dans 7 jours)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date dueDate = calendar.getTime();
        
        // Créer la tâche
        Task task = new Task(
            "Réviser Android",
            "Préparer examen module 7",
            dueDate
        );
        
        // Insérer en arrière-plan
        new Thread(() -> {
            taskDao.insert(task);
            runOnUiThread(() -> {
                Toast.makeText(this, "Tâche ajoutée", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}
```

### Exemple 2 : Afficher les tâches en retard

```java
public class TaskListFragment extends Fragment {
    
    private TaskViewModel viewModel;
    private TaskAdapter adapter;
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        
        // Observer les tâches en retard
        Date now = new Date();
        viewModel.getOverdueTasks(now).observe(getViewLifecycleOwner(), tasks -> {
            adapter.setTasks(tasks);
            TextView count = view.findViewById(R.id.textOverdueCount);
            count.setText("Tâches en retard : " + tasks.size());
        });
    }
}
```

### Exemple 3 : Formater les dates pour affichage

```java
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    
    private List<Task> tasks;
    private SimpleDateFormat dateFormat;
    
    public TaskAdapter() {
        this.tasks = new ArrayList<>();
        // Format : 23 déc. 2025
        this.dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.FRENCH);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = tasks.get(position);
        
        holder.textTitle.setText(task.getTitle());
        
        // Formater la date limite
        if (task.getDueDate() != null) {
            String formattedDate = dateFormat.format(task.getDueDate());
            holder.textDueDate.setText("Échéance : " + formattedDate);
            
            // Colorer en rouge si en retard
            if (task.getDueDate().before(new Date()) && !task.isCompleted()) {
                holder.textDueDate.setTextColor(Color.RED);
            }
        }
    }
    
    // ... reste du code
}
```

---

## 🛠️ Helper pour dates

### Classe DateUtils

```java
package tn.isitcom.myapp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    
    private static final SimpleDateFormat DATE_FORMAT = 
        new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
    
    private static final SimpleDateFormat DATETIME_FORMAT = 
        new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.FRENCH);
    
    /**
     * Formater une date en chaîne
     */
    public static String formatDate(Date date) {
        if (date == null) return "";
        return DATE_FORMAT.format(date);
    }
    
    /**
     * Formater date et heure
     */
    public static String formatDateTime(Date date) {
        if (date == null) return "";
        return DATETIME_FORMAT.format(date);
    }
    
    /**
     * Obtenir le début de la journée (00:00:00)
     */
    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    
    /**
     * Obtenir la fin de la journée (23:59:59)
     */
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    
    /**
     * Ajouter des jours à une date
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
    
    /**
     * Vérifier si une date est aujourd'hui
     */
    public static boolean isToday(Date date) {
        Calendar today = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        
        return today.get(Calendar.YEAR) == cal.get(Calendar.YEAR) &&
               today.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR);
    }
    
    /**
     * Calculer le nombre de jours entre deux dates
     */
    public static long daysBetween(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
}
```

---

## 📊 Exemple complet : Application de tâches

### Repository

```java
package tn.isitcom.myapp.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tn.isitcom.myapp.data.database.AppDatabase;
import tn.isitcom.myapp.data.dao.TaskDao;
import tn.isitcom.myapp.data.model.Task;

public class TaskRepository {
    
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService;
    
    public TaskRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newSingleThreadExecutor();
    }
    
    // CRUD Operations
    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }
    
    public void update(Task task) {
        executorService.execute(() -> taskDao.update(task));
    }
    
    public void delete(Task task) {
        executorService.execute(() -> taskDao.delete(task));
    }
    
    public void deleteAll() {
        executorService.execute(() -> taskDao.deleteAll());
    }
    
    // Queries
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    
    public LiveData<List<Task>> getOverdueTasks() {
        return taskDao.getOverdueTasks(new Date());
    }
    
    public LiveData<List<Task>> getTasksBetween(Date start, Date end) {
        return taskDao.getTasksBetween(start, end);
    }
}
```

### ViewModel

```java
package tn.isitcom.myapp.ui.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.Date;
import java.util.List;

import tn.isitcom.myapp.data.model.Task;
import tn.isitcom.myapp.data.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    
    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }
    
    public void insert(Task task) {
        repository.insert(task);
    }
    
    public void update(Task task) {
        repository.update(task);
    }
    
    public void delete(Task task) {
        repository.delete(task);
    }
    
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    
    public LiveData<List<Task>> getOverdueTasks() {
        return repository.getOverdueTasks();
    }
}
```

---

## ⚠️ Points importants

### ✅ Bonnes pratiques

1. **Toujours utiliser TypeConverter pour les dates**
   - Room ne supporte pas Date nativement
   - Le convertisseur gère automatiquement la conversion

2. **Stocker en UTC quand possible**
   ```java
   Date date = new Date(); // UTC par défaut
   ```

3. **Formater uniquement pour l'affichage**
   ```java
   // ❌ Éviter de stocker des String
   task.setDate("23/12/2025");
   
   // ✅ Stocker des Date
   task.setDate(new Date());
   ```

4. **Utiliser Calendar pour manipulations**
   ```java
   Calendar cal = Calendar.getInstance();
   cal.add(Calendar.DAY_OF_YEAR, 7);
   Date futureDate = cal.getTime();
   ```

### ❌ Erreurs à éviter

1. **Oublier @TypeConverters dans Database**
   ```java
   // ❌ Sans annotation
   @Database(entities = {Task.class}, version = 1)
   public abstract class AppDatabase extends RoomDatabase { }
   
   // ✅ Avec annotation
   @Database(entities = {Task.class}, version = 1)
   @TypeConverters({Converters.class})
   public abstract class AppDatabase extends RoomDatabase { }
   ```

2. **Comparer des dates sans précaution**
   ```java
   // ❌ Comparaison imprécise (inclut heures/minutes)
   if (date1.equals(date2)) { }
   
   // ✅ Comparer uniquement les jours
   if (DateUtils.isSameDay(date1, date2)) { }
   ```

---

## 📚 Exercices pratiques

### Exercice 1 : Ajouter un champ lastModified

1. Ajouter `private Date lastModified;` dans Task
2. Mettre à jour automatiquement lors de modifications
3. Afficher "Modifié il y a X jours" dans l'adapter

### Exercice 2 : Filtrer par période

Créer une requête pour afficher :
- Tâches de cette semaine
- Tâches du mois en cours
- Tâches de l'année

### Exercice 3 : Statistiques

Afficher :
- Nombre de tâches créées par jour (7 derniers jours)
- Taux de complétion par semaine
- Temps moyen de complétion

---

👨‍🏫 **Module 7 - Room Database Avancé** | ISITCOM 2025-2026
