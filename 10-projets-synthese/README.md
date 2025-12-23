# Module 10 : Projets de synthèse

## 🎯 Objectifs

Ce module propose des projets complets pour mettre en pratique tous les concepts vus dans les modules précédents.

---

## Projet 1 : Application de gestion de tâches (To-Do App)

### Description

Application complète de gestion de tâches avec toutes les fonctionnalités modernes.

### Fonctionnalités requises

1. **Écran principal** :
   - Liste des tâches (RecyclerView).
   - FAB pour ajouter une tâche.
   - Swipe pour supprimer.
   - Checkbox pour marquer comme terminée.

2. **Ajout/Modification** :
   - Titre et description (TextInputLayout).
   - Sélection de priorité (Spinner : Haute/Moyenne/Basse).
   - Date d'échéance (DatePicker).

3. **Base de données** :
   - Room pour le stockage local.
   - CRUD complet.

4. **Navigation** :
   - BottomNavigationView (Toutes, Actives, Terminées).
   - Fragments pour chaque section.

5. **Design** :
   - Material Design.
   - Dark Mode.
   - Animations.

### Structure du projet

```
app/
├── java/tn/isitcom/todoapp/
│   ├── ui/
│   │   ├── MainActivity.java
│   │   ├── AddTaskActivity.java
│   │   └── fragments/
│   │       ├── AllTasksFragment.java
│   │       ├── ActiveTasksFragment.java
│   │       └── CompletedTasksFragment.java
│   ├── adapter/
│   │   └── TaskAdapter.java
│   ├── database/
│   │   ├── AppDatabase.java
│   │   ├── Task.java
│   │   └── TaskDao.java
│   └── utils/
│       └── DateUtils.java
├── res/
│   ├── layout/
│   ├── navigation/
│   └── menu/
```

### Code exemple : Task.java (Entity)

```java
@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String priority; // "HIGH", "MEDIUM", "LOW"
    private long dueDate;
    private boolean completed;

    // Constructeurs, getters, setters
}
```

### Code exemple : TaskDao.java

```java
@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks ORDER BY dueDate ASC")
    List<Task> getAllTasks();

    @Query("SELECT * FROM tasks WHERE completed = 0 ORDER BY dueDate ASC")
    List<Task> getActiveTasks();

    @Query("SELECT * FROM tasks WHERE completed = 1 ORDER BY dueDate DESC")
    List<Task> getCompletedTasks();

    @Query("SELECT * FROM tasks WHERE priority = :priority")
    List<Task> getTasksByPriority(String priority);
}
```

### Barème d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Base de données Room fonctionnelle | 4 |
| CRUD complet | 4 |
| RecyclerView + Adapter | 3 |
| Navigation avec Fragments | 3 |
| Material Design appliqué | 3 |
| Fonctionnalités bonus (swipe, filtres) | 3 |

---

## Projet 2 : Application de contacts

### Description

Gestion complète de contacts avec recherche et catégories.

### Fonctionnalités

1. **Liste de contacts** (nom, téléphone, email, photo).
2. **Recherche** en temps réel.
3. **Catégories** (Famille, Amis, Travail).
4. **Détail** : affichage complet + actions (appeler, envoyer SMS, email).
5. **Stockage** : Room Database.
6. **Export** : partager un contact (Intent).

### Fonctionnalités avancées (bonus)

- Import de contacts depuis le téléphone.
- Ajout de photo depuis galerie ou caméra.
- Backup/Restore en JSON.

---

## Projet 3 : Application météo

### Description

Application de consultation de la météo avec API externe.

### Fonctionnalités

1. **Écran principal** :
   - Météo actuelle (température, condition, icône).
   - Ville sélectionnée.

2. **Prévisions** :
   - 7 jours (RecyclerView).

3. **Recherche** :
   - Rechercher une ville.
   - Liste de villes favorites (Room).

4. **API** :
   - OpenWeatherMap ou WeatherAPI.
   - Retrofit pour les appels réseau.

5. **Design** :
   - Animations selon la météo.
   - Dark Mode.

### Technologies

- **Retrofit** : appels API.
- **Glide** : chargement d'images.
- **Room** : favoris.
- **WorkManager** : mise à jour en arrière-plan.

---

## Projet 4 : Application e-commerce (mini)

### Description

Application de boutique en ligne simplifiée.

### Fonctionnalités

1. **Catalogue produits** (RecyclerView en grille).
2. **Détail produit** (image, description, prix).
3. **Panier** :
   - Ajouter/retirer produits.
   - Calculer total.
4. **Commande** :
   - Formulaire de commande.
   - Récapitulatif.
5. **Stockage** :
   - Produits : JSON local ou Room.
   - Panier : SharedPreferences ou Room.

---

## Projet 5 : Application de quiz

### Description

Application de quiz interactif avec score et statistiques.

### Fonctionnalités

1. **Questions** :
   - Questions à choix multiples.
   - Timer par question.

2. **Score** :
   - Calcul du score.
   - Classement local.

3. **Catégories** :
   - Plusieurs thèmes (Sport, Histoire, Science).

4. **Statistiques** :
   - Graphiques (MPAndroidChart).
   - Historique des parties.

5. **Stockage** :
   - Questions : JSON local.
   - Scores : Room.

---

## 📱 Projet exemple complet : Application de notes avancée

### Description détaillée

Application de prise de notes avec fonctionnalités avancées.

### Architecture complète

```
NotesApp/
├── data/
│   ├── database/
│   │   ├── AppDatabase.java
│   │   ├── Note.java
│   │   ├── NoteDao.java
│   │   ├── Category.java
│   │   └── CategoryDao.java
│   └── repository/
│       └── NoteRepository.java
├── ui/
│   ├── main/
│   │   ├── MainActivity.java
│   │   └── NotesFragment.java
│   ├── add/
│   │   └── AddNoteActivity.java
│   ├── detail/
│   │   └── NoteDetailActivity.java
│   └── settings/
│       └── SettingsFragment.java
├── adapter/
│   └── NoteAdapter.java
└── utils/
    ├── DateFormatter.java
    └── PreferencesManager.java
```

### Note.java (Entity complète)

```java
@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String content;
    private int categoryId;
    private long createdAt;
    private long updatedAt;
    private int color; // Couleur de la note
    private boolean pinned; // Épinglée en haut

    public Note(String title, String content, int categoryId) {
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
        this.color = 0xFFFFFFFF; // Blanc par défaut
        this.pinned = false;
    }

    // Getters et Setters complets
}
```

### NoteDao.java (requêtes avancées)

```java
@Dao
public interface NoteDao {

    @Insert
    long insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM notes ORDER BY pinned DESC, updatedAt DESC")
    List<Note> getAllNotes();

    @Query("SELECT * FROM notes WHERE categoryId = :categoryId ORDER BY updatedAt DESC")
    List<Note> getNotesByCategory(int categoryId);

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%'")
    List<Note> searchNotes(String query);

    @Query("SELECT * FROM notes WHERE pinned = 1 ORDER BY updatedAt DESC")
    List<Note> getPinnedNotes();
}
```

### MainActivity.java (complète)

```java
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private AppDatabase database;
    private List<Note> noteList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser la base de données
        database = AppDatabase.getInstance(this);

        // Configurer RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadNotes();

        // FAB pour ajouter
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
        });

        // SearchView
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchNotes(newText);
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        noteList = database.noteDao().getAllNotes();
        if (adapter == null) {
            adapter = new NoteAdapter(noteList, this::openNoteDetail, this::deleteNote);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateNotes(noteList);
        }
    }

    private void searchNotes(String query) {
        List<Note> results = database.noteDao().searchNotes(query);
        adapter.updateNotes(results);
    }

    private void openNoteDetail(Note note) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("NOTE_ID", note.getId());
        startActivity(intent);
    }

    private void deleteNote(Note note) {
        new AlertDialog.Builder(this)
            .setTitle("Supprimer")
            .setMessage("Supprimer cette note ?")
            .setPositiveButton("Oui", (dialog, which) -> {
                database.noteDao().delete(note);
                loadNotes();
                Snackbar.make(recyclerView, "Note supprimée", Snackbar.LENGTH_SHORT).show();
            })
            .setNegativeButton("Annuler", null)
            .show();
    }
}
```

### Fonctionnalités avancées implémentées

1. **Recherche en temps réel**.
2. **Notes épinglées** (toujours en haut).
3. **Couleurs personnalisées** par note.
4. **Catégories** pour organiser.
5. **Export/Import** en JSON.
6. **Dark Mode** complet.
7. **Swipe to delete** avec annulation.

---

## Consignes générales pour tous les projets

### Livrables

1. **Code source** complet (projet Android Studio).
2. **Documentation** (README.md) :
   - Description du projet.
   - Fonctionnalités implémentées.
   - Instructions d'installation.
3. **APK** compilé et fonctionnel.
4. **Vidéo de démonstration** (2-3 minutes).

### Critères d'évaluation généraux

| Critère | Points |
|---------|--------|
| Fonctionnalités complètes | 6 |
| Base de données et CRUD | 4 |
| Interface utilisateur | 4 |
| Navigation | 2 |
| Code propre et commenté | 2 |
| Fonctionnalités bonus | 2 |

**Total** : /20

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
