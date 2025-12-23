# Module 7 : Stockage local avec Room Database

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Comprendre l'architecture Room (Entity, DAO, Database).
- Créer une base de données locale.
- Effectuer des opérations CRUD (Create, Read, Update, Delete).
- Lier Room avec RecyclerView pour afficher les données.

---

## 1. Pourquoi Room ?

Room est une bibliothèque Android qui facilite l'utilisation de SQLite : 

- **Simplification** : moins de code boilerplate.
- **Sécurité** : vérification à la compilation.
- **Intégration** : fonctionne avec LiveData, Flow, Coroutines.

Alternatives : SQLite brut (complexe), SharedPreferences (données simples), Firebase (cloud).

---

## 2. Architecture Room

Trois composants principaux : 

- **Entity** : représente une table (classe Java annotée).
- **DAO** (Data Access Object) : contient les requêtes SQL.
- **Database** : point d'accès à la base.

```
App → Database → DAO → Entity (Table)
```

---

## 3. Configuration initiale

### 3.1 Ajouter les dépendances

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    def room_version = "2.6.1"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
}
```

Synchroniser Gradle.

---

## 4. Créer une Entity

Une **Entity** représente une table.

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

    // Constructeurs
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
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
}
```

---

## 5. Créer un DAO

Un **DAO** contient les méthodes d'accès aux données.

```java
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
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

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    Task getTaskById(int taskId);
}
```

---

## 6. Créer la Database

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
            .allowMainThreadQueries() // ⚠️ Pour tests uniquement
            .build();
        }
        return instance;
    }
}
```

> **Important** : `allowMainThreadQueries()` est à éviter en production (utiliser AsyncTask, Executors ou Coroutines).

---

## 7. Opérations CRUD

### 7.1 Insérer une tâche

```java
AppDatabase db = AppDatabase.getInstance(this);
Task task = new Task("Acheter lait", "Au supermarché");
db.taskDao().insert(task);
```

### 7.2 Lire toutes les tâches

```java
List<Task> tasks = db.taskDao().getAllTasks();
for (Task t : tasks) {
    Log.d("Room", t.getTitle());
}
```

### 7.3 Modifier une tâche

```java
task.setCompleted(true);
db.taskDao().update(task);
```

### 7.4 Supprimer une tâche

```java
db.taskDao().delete(task);
```

---

## 8. Intégration avec RecyclerView

### 8.1 Adapter simple

```java
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.textTitle.setText(task.getTitle());
        holder.textDesc.setText(task.getDescription());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public void updateTasks(List<Task> newTasks) {
        this.taskList = newTasks;
        notifyDataSetChanged();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDesc;

        public TaskViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
        }
    }
}
```

### 8.2 Charger et afficher

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
            Task newTask = new Task("Nouvelle tâche", "Description");
            db.taskDao().insert(newTask);
            loadTasks();
        });
    }

    private void loadTasks() {
        List<Task> tasks = db.taskDao().getAllTasks();
        if (adapter == null) {
            adapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateTasks(tasks);
        }
    }
}
```

---

## 9. Exercices pratiques (Module 7)

### Exercice 1 – CRUD complet

1. Créer une base Room avec une table `Student` (nom, âge, groupe).
2. Implémenter les 4 opérations : ajouter, lire, modifier, supprimer.
3. Afficher la liste dans un RecyclerView.

### Exercice 2 – Requête avec filtres

1. Ajouter une requête dans le DAO :
   ```java
   @Query("SELECT * FROM tasks WHERE completed = :isCompleted")
   List<Task> getTasksByStatus(boolean isCompleted);
   ```
2. Ajouter un bouton pour filtrer les tâches terminées.

### Exercice 3 – Suppression avec confirmation

1. Au clic long sur une tâche, afficher un dialogue de confirmation.
2. Si confirmé, supprimer la tâche de la base et rafraîchir la liste.

---

## 10. Mini-TP : Application de gestion de notes

### Consignes

Créer une application "Notes" :

1. **Entity** : `Note` (id, titre, contenu, date).
2. **Écran principal** :
   - RecyclerView affichant toutes les notes.
   - FAB pour ajouter une note.
3. **Écran ajout/modification** :
   - Champs titre et contenu.
   - Bouton "Enregistrer".
4. **Fonctionnalités** :
   - Clic sur une note → modification.
   - Clic long → suppression avec confirmation.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Room correctement configuré | 4 |
| CRUD fonctionnel | 6 |
| Interface RecyclerView | 4 |
| Ajout/modification | 4 |
| Suppression avec confirmation | 2 |

**Total** : /20

---

## 11. Erreurs fréquentes

- **Erreur "Cannot access database on the main thread"**  
  → Utiliser Executors, AsyncTask ou Coroutines (ou `.allowMainThreadQueries()` temporairement).

- **Crash à la migration de version**  
  → Utiliser `.fallbackToDestructiveMigration()` ou implémenter une migration.

- **Entity sans `@PrimaryKey`**  
  → Toujours définir une clé primaire.

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
