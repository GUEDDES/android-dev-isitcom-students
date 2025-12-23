# Module 10 : Fiche de synthèse

## 🎯 Objectif

Intégrer tous les modules précédents dans un projet complet et fonctionnel.

---

## 🏛️ Architecture MVVM

```
View (Activity/Fragment)
  │
  └── ViewModel
        │
        └── Repository
              │
              ├── Room Database
              └── API (Retrofit)
```

### Avantages

- Séparation des responsabilités
- Testabilité
- Gestion du cycle de vie
- Code maintenable

---

## 📦 Organisation du projet

```
app/
  └── src/main/java/tn/isitcom/monapp/
        ├── data/
        │   ├── model/          # Entités
        │   ├── dao/            # DAO Room
        │   ├── database/       # AppDatabase
        │   └── repository/     # Repositories
        ├── ui/
        │   ├── home/           # HomeFragment + ViewModel
        │   ├── detail/         # DetailFragment + ViewModel
        │   └── adapter/        # RecyclerView Adapters
        └── utils/              # Helpers, Constants
```

---

## 🔧 ViewModel avec LiveData

### ViewModel

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
    
    public void delete(Task task) {
        repository.delete(task);
    }
}
```

### Repository

```java
public class TaskRepository {
    
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    
    public TaskRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }
    
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    
    public void insert(Task task) {
        new Thread(() -> taskDao.insert(task)).start();
    }
    
    public void delete(Task task) {
        new Thread(() -> taskDao.delete(task)).start();
    }
}
```

### Utilisation dans Fragment

```java
public class HomeFragment extends Fragment {
    
    private TaskViewModel viewModel;
    private TaskAdapter adapter;
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        
        // Observer les données
        viewModel.getAllTasks().observe(getViewLifecycleOwner(), tasks -> {
            adapter.setTasks(tasks);
        });
    }
}
```

---

## 📝 Checklist projet complet

### Architecture
✅ Organisation en packages (data, ui, utils)  
✅ Séparation modèle / vue / contrôleur  
✅ ViewModel pour gérer données  
✅ Repository comme couche intermédiaire  

### Base de données
✅ Entity avec @PrimaryKey  
✅ DAO avec @Insert, @Update, @Delete, @Query  
✅ Database Singleton  
✅ Opérations sur thread secondaire  

### Interface
✅ Material Design Components  
✅ Navigation Component  
✅ Bottom Navigation (si multi-onglets)  
✅ RecyclerView pour listes  
✅ FAB pour ajout  
✅ Dark mode supporté  

### Fonctionnalités
✅ CRUD complet (Create, Read, Update, Delete)  
✅ Navigation fluide  
✅ Gestion erreurs  
✅ Messages utilisateur (Toast/Snackbar)  
✅ Validation formulaires  

---

## 🚀 Workflow développement

1. **Planification** : Schéma des écrans et données
2. **Base de données** : Entity + DAO + Database
3. **UI de base** : Fragments + Navigation
4. **Adapter** : RecyclerView pour afficher données
5. **ViewModel** : Gestion logique métier
6. **Formulaires** : Ajout/édition
7. **Tests** : Vérifier tous les cas d'usage
8. **Polish** : Animations, Material Design

---

## 📊 Exemples de projets

### 1. Gestionnaire de tâches
- Liste de tâches (Room)
- Ajout/modification/suppression
- Filtres (terminé/en cours)
- Recherche

### 2. Application de notes
- Création notes avec titre/contenu
- Stockage Room
- Catégories
- Partage notes

### 3. Liste de contacts
- CRUD contacts
- Appel/SMS (Intents implicites)
- Recherche
- Photo de profil

---

## ⚠️ Pièges à éviter

❌ Opérations Room sur UI thread  
❌ Activities trop chargées (utiliser Fragments)  
❌ Pas de gestion d'erreurs  
❌ Code dupliqué (utiliser helpers)  
❌ Pas de validation des entrées utilisateur  

---

## 🔑 Pattern Repository

```java
// Repository centralise accès données
public class MonRepository {
    
    private MonDao dao;
    
    public MonRepository(Application app) {
        dao = AppDatabase.getInstance(app).monDao();
    }
    
    public LiveData<List<Item>> getAll() {
        return dao.getAll();
    }
    
    public void insert(Item item) {
        // Exécuter en arrière-plan
        Executors.newSingleThreadExecutor().execute(() -> {
            dao.insert(item);
        });
    }
}
```

---

## 📚 Ressources avancées

- [Guide Architecture Android](https://developer.android.com/topic/architecture)
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
- [Repository Pattern](https://developer.android.com/codelabs/android-room-with-a-view)

---

## 🎯 Objectifs finaux

Après ce module, vous devez être capable de :

✅ Concevoir une architecture propre (MVVM)  
✅ Implémenter CRUD complet avec Room  
✅ Créer navigation fluide avec Fragments  
✅ Appliquer Material Design  
✅ Gérer cycle de vie correctement  
✅ Développer application complète de A à Z  

---

👨‍🏫 **Module 10 - Projets de synthèse** | ISITCOM 2025-2026
