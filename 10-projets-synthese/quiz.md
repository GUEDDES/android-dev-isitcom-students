# Module 10 : Quiz d'auto-évaluation - Projet de synthèse

## Questions d'architecture

### Question 1
Que signifie MVVM ?

A) Model View View Model  
B) Multi View Virtual Machine  
C) Model View ViewModel  
D) Multiple View Model

<details>
<summary>Réponse</summary>
C) Model View ViewModel - Architecture séparant logique métier et UI
</details>

---

### Question 2
Quel est le rôle du Repository ?

A) Afficher les données  
B) Centraliser l'accès aux données (Room, API...)  
C) Gérer les fragments  
D) Créer les vues

<details>
<summary>Réponse</summary>
B) Le Repository centralise l'accès aux sources de données (base locale, API...)
</details>

---

### Question 3
Qu'est-ce que LiveData ?

A) Une base de données  
B) Un observable qui respecte le cycle de vie  
C) Un fragment  
D) Un thread

<details>
<summary>Réponse</summary>
B) LiveData est observable et lifecycle-aware (met à jour l'UI automatiquement)
</details>

---

### Question 4
Où placer la logique métier dans MVVM ?

A) Dans l'Activity  
B) Dans le Fragment  
C) Dans le ViewModel  
D) Dans la Database

<details>
<summary>Réponse</summary>
C) Le ViewModel contient la logique métier et expose des LiveData
</details>

---

### Question 5
Pourquoi utiliser ViewModel plutôt qu'une Activity ?

A) Plus rapide  
B) Survit aux rotations d'écran  
C) Plus joli  
D) Obligatoire

<details>
<summary>Réponse</summary>
B) ViewModel survit aux changements de configuration (rotation, changement langue...)
</details>

---

## Questions pratiques

### Question 6
Structure recommandée d'un projet Android ?

<details>
<summary>Réponse</summary>

```
app/src/main/java/tn/isitcom/monapp/
  ├── data/
  │   ├── model/          # Entités (User, Task...)
  │   ├── dao/            # Interfaces DAO
  │   ├── database/       # AppDatabase
  │   └── repository/     # Repositories
  ├── ui/
  │   ├── home/           # HomeFragment + HomeViewModel
  │   ├── detail/         # DetailFragment + DetailViewModel
  │   └── adapter/        # RecyclerView Adapters
  └── utils/              # Constantes, Helpers
```
</details>

---

### Question 7
Implémentez un ViewModel basique pour une liste de tasks.

<details>
<summary>Réponse</summary>

```java
public class TaskViewModel extends ViewModel {
    
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    
    public TaskViewModel(Application application) {
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }
    
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
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
}
```
</details>

---

### Question 8
Comment observer des LiveData dans un Fragment ?

<details>
<summary>Réponse</summary>

```java
public class HomeFragment extends Fragment {
    
    private TaskViewModel viewModel;
    private TaskAdapter adapter;
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialiser ViewModel
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        
        // Configurer RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        // Observer LiveData
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            // Mettre à jour adapter quand données changent
            adapter.setTasks(tasks);
        });
    }
}
```
</details>

---

## Projet complet : Gestionnaire de tâches

### Question 9
Listez les fonctionnalités minimales d'un gestionnaire de tâches.

<details>
<summary>Réponse</summary>

**Fonctionnalités CRUD** :
1. Ajouter une tâche (titre, description)
2. Afficher liste des tâches (RecyclerView)
3. Marquer tâche complétée (CheckBox)
4. Modifier une tâche (clic sur item)
5. Supprimer une tâche (swipe ou bouton)

**Fonctionnalités avancées** :
6. Filtrer (toutes/complétées/actives)
7. Rechercher par titre
8. Trier (date, titre, priorité)
9. Catégories/tags
10. Dark mode
</details>

---

### Question 10
Définissez l'Entity Task.

<details>
<summary>Réponse</summary>

```java
@Entity(tableName = "tasks")
public class Task {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String description;
    private boolean completed;
    private long createdAt;
    
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.createdAt = System.currentTimeMillis();
    }
    
    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    
    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
```
</details>

---

### Question 11
Créez le TaskDao avec méthodes CRUD et requêtes spéciales.

<details>
<summary>Réponse</summary>

```java
@Dao
public interface TaskDao {
    
    @Insert
    void insert(Task task);
    
    @Update
    void update(Task task);
    
    @Delete
    void delete(Task task);
    
    // Toutes les tâches (LiveData pour auto-update)
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    LiveData<List<Task>> getAllTasks();
    
    // Tâches actives uniquement
    @Query("SELECT * FROM tasks WHERE completed = 0 ORDER BY createdAt DESC")
    LiveData<List<Task>> getActiveTasks();
    
    // Tâches complétées uniquement
    @Query("SELECT * FROM tasks WHERE completed = 1 ORDER BY createdAt DESC")
    LiveData<List<Task>> getCompletedTasks();
    
    // Recherche par titre
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :search || '%'")
    LiveData<List<Task>> searchTasks(String search);
    
    // Compter tâches actives
    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 0")
    LiveData<Integer> getActiveCount();
    
    // Supprimer tout
    @Query("DELETE FROM tasks")
    void deleteAll();
}
```
</details>

---

### Question 12
Créez le TaskRepository.

<details>
<summary>Réponse</summary>

```java
public class TaskRepository {
    
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> activeTasks;
    private LiveData<List<Task>> completedTasks;
    
    public TaskRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        activeTasks = taskDao.getActiveTasks();
        completedTasks = taskDao.getCompletedTasks();
    }
    
    // Opérations CRUD (async)
    public void insert(Task task) {
        new Thread(() -> taskDao.insert(task)).start();
    }
    
    public void update(Task task) {
        new Thread(() -> taskDao.update(task)).start();
    }
    
    public void delete(Task task) {
        new Thread(() -> taskDao.delete(task)).start();
    }
    
    public void deleteAll() {
        new Thread(() -> taskDao.deleteAll()).start();
    }
    
    // Getters LiveData
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    
    public LiveData<List<Task>> getActiveTasks() {
        return activeTasks;
    }
    
    public LiveData<List<Task>> getCompletedTasks() {
        return completedTasks;
    }
    
    public LiveData<List<Task>> searchTasks(String query) {
        return taskDao.searchTasks(query);
    }
}
```
</details>

---

### Question 13
Créez l'Adapter pour afficher les tasks dans RecyclerView.

<details>
<summary>Réponse</summary>

```java
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    
    private List<Task> tasks = new ArrayList<>();
    private OnTaskClickListener listener;
    
    public interface OnTaskClickListener {
        void onTaskClick(Task task);
        void onTaskLongClick(Task task);
        void onCheckboxClick(Task task);
    }
    
    public void setOnTaskClickListener(OnTaskClickListener listener) {
        this.listener = listener;
    }
    
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }
    
    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position), listener);
    }
    
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textDescription;
        CheckBox checkCompleted;
        
        public TaskViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            checkCompleted = itemView.findViewById(R.id.checkCompleted);
        }
        
        public void bind(Task task, OnTaskClickListener listener) {
            textTitle.setText(task.getTitle());
            textDescription.setText(task.getDescription());
            checkCompleted.setChecked(task.isCompleted());
            
            // Style si complétée
            if (task.isCompleted()) {
                textTitle.setPaintFlags(textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                textTitle.setPaintFlags(textTitle.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }
            
            // Listeners
            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onTaskClick(task);
            });
            
            itemView.setOnLongClickListener(v -> {
                if (listener != null) listener.onTaskLongClick(task);
                return true;
            });
            
            checkCompleted.setOnClickListener(v -> {
                if (listener != null) listener.onCheckboxClick(task);
            });
        }
    }
}
```
</details>

---

## Checklist projet complet

### Question 14
Quels sont les éléments obligatoires d'un projet de synthèse ?

<details>
<summary>Réponse</summary>

**Architecture** :
✅ Entity avec @PrimaryKey  
✅ DAO avec @Insert, @Update, @Delete, @Query  
✅ Database Singleton  
✅ Repository  
✅ ViewModel avec LiveData  

**Interface** :
✅ Navigation Component avec fragments  
✅ RecyclerView avec Adapter  
✅ Material Design Components  
✅ FAB pour ajout  
✅ Bottom Navigation (si multi-onglets)  
✅ Dark mode support  

**Fonctionnalités** :
✅ CRUD complet (Create, Read, Update, Delete)  
✅ Validation formulaires  
✅ Messages utilisateur (Toast/Snackbar)  
✅ Gestion erreurs  

**Code quality** :
✅ Code indenté et commenté  
✅ Noms de variables clairs  
✅ Pas de code dupliqué  
✅ Organisation en packages  
</details>

---

### Question 15
Comment tester son application avant livraison ?

<details>
<summary>Réponse</summary>

**Tests fonctionnels** :
1. Ajouter un item → vérifier affichage
2. Modifier un item → vérifier sauvegarde
3. Supprimer un item → vérifier disparition
4. Rotation écran → données conservées ?
5. Quitter app et revenir → données sauvegardées ?

**Tests edge cases** :
6. Champs vides → validation marche ?
7. Texte très long → UI cassée ?
8. Liste vide → message approprié ?
9. 100+ items → performances OK ?
10. Pas de connexion (si API) → erreur gérée ?

**Tests appareils** :
11. Tester sur émulateur ET appareil physique
12. Tester différentes tailles écran
13. Tester dark mode
</details>

---

## Barème projet final

### Question 16
Critères d'évaluation d'un projet de synthèse ?

<details>
<summary>Réponse</summary>

**Architecture (30 points)** :
- Entity/DAO/Database : 10 pts
- Repository : 5 pts
- ViewModel : 10 pts
- Organisation code : 5 pts

**Interface (30 points)** :
- Navigation fragments : 10 pts
- RecyclerView : 10 pts
- Material Design : 5 pts
- Ergonomie : 5 pts

**Fonctionnalités (30 points)** :
- CRUD complet : 15 pts
- Fonctions avancées : 10 pts
- Gestion erreurs : 5 pts

**Qualité code (10 points)** :
- Code propre : 5 pts
- Comments/README : 3 pts
- Tests : 2 pts

**TOTAL : /100**
</details>

---

👨‍🏫 **Module 10 - Projets de synthèse** | ISITCOM 2025-2026
