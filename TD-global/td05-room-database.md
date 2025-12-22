# TD05 : Room Database

## 🎯 Objectifs

- Configurer Room Database.
- Créer Entity, DAO, Database.
- Effectuer des opérations CRUD.

---

## Partie 1 : Configuration (30 min)

### Exercice 1.1 : Dépendances

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    def room_version = "2.6.1"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
}
```

### Exercice 1.2 : Entity

Créer `Task.java` :

```java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private boolean completed;
    private long timestamp;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
```

### Exercice 1.3 : DAO

Créer `TaskDao.java` :

```java
import androidx.room.*;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("SELECT * FROM tasks ORDER BY timestamp DESC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    Task getTaskById(int taskId);

    @Query("SELECT * FROM tasks WHERE completed = :isCompleted ORDER BY timestamp DESC")
    List<Task> getTasksByStatus(boolean isCompleted);
}
```

### Exercice 1.4 : Database

Créer `AppDatabase.java` :

```java
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "task_database"
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries() // Pour tests uniquement
            .build();
        }
        return instance;
    }
}
```

---

## Partie 2 : Opérations CRUD (60 min)

### Exercice 2.1 : Insérer une tâche

```java
AppDatabase db = AppDatabase.getInstance(this);

Button btnAdd = findViewById(R.id.btnAdd);
btnAdd.setOnClickListener(v -> {
    Task task = new Task("Acheter lait", "Au supermarché");
    db.taskDao().insert(task);
    Toast.makeText(this, "Tâche ajoutée", Toast.LENGTH_SHORT).show();
});
```

### Exercice 2.2 : Lire toutes les tâches

```java
List<Task> tasks = db.taskDao().getAllTasks();
for (Task task : tasks) {
    Log.d("Room", task.getTitle());
}
```

### Exercice 2.3 : Modifier une tâche

```java
Task task = db.taskDao().getTaskById(1);
if (task != null) {
    task.setCompleted(true);
    db.taskDao().update(task);
}
```

### Exercice 2.4 : Supprimer une tâche

```java
Task task = db.taskDao().getTaskById(1);
if (task != null) {
    db.taskDao().delete(task);
}
```

---

## Partie 3 : Intégration avec RecyclerView (45 min)

### Exercice 3.1 : Adapter avec Room

```java
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadTasks();

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            // Ouvrir dialog ou nouvelle activity
            addTask("Nouvelle tâche", "Description");
        });
    }

    private void loadTasks() {
        List<Task> tasks = db.taskDao().getAllTasks();
        if (adapter == null) {
            adapter = new TaskAdapter(tasks, this::onTaskClick);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateTasks(tasks);
        }
    }

    private void addTask(String title, String description) {
        Task task = new Task(title, description);
        db.taskDao().insert(task);
        loadTasks();
    }

    private void onTaskClick(Task task) {
        // Marquer comme terminée
        task.setCompleted(!task.isCompleted());
        db.taskDao().update(task);
        loadTasks();
    }
}
```

---

## 🎯 TP Noté : Application de gestion de contacts (/20)

### Consignes

Créer une application "Mes Contacts" :

1. **Entity Contact** :
   - id, nom, prénom, téléphone, email.

2. **Fonctionnalités** :
   - Afficher tous les contacts (RecyclerView).
   - Ajouter un contact (formulaire).
   - Modifier un contact (clic sur l'élément).
   - Supprimer un contact (clic long avec confirmation).
   - Rechercher un contact par nom.

3. **Interface** :
   - FAB pour ajouter.
   - SearchView pour rechercher.
   - Material Design.

### Barème

| Critère | Points |
|---------|--------|
| Entity, DAO, Database corrects | 4 |
| CRUD complet | 6 |
| RecyclerView avec Room | 4 |
| Recherche fonctionnelle | 3 |
| Interface Material | 2 |
| Code propre | 1 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
