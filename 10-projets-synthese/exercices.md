# Module 10 : Exercices et projets pratiques

## Exercice 1 : Architecture MVVM basique

**Objectif** : Implémenter le pattern MVVM pour une simple liste.

### Structure

```
app/
  ├── data/
  │   ├── model/Task.java
  │   ├── dao/TaskDao.java
  │   ├── database/AppDatabase.java
  │   └── repository/TaskRepository.java
  ├── ui/
  │   ├── TaskViewModel.java
  │   └── MainActivity.java
  └── adapter/TaskAdapter.java
```

### Consignes

1. Créer Entity `Task` avec id, titre, description
2. Créer DAO avec CRUD complet
3. Créer Repository
4. Créer ViewModel qui expose LiveData
5. Observer les données dans MainActivity

---

## Exercice 2 : LiveData et Observer

**Objectif** : Utiliser LiveData pour mettre à jour l'UI automatiquement.

### ViewModel

```java
public class CounterViewModel extends ViewModel {
    
    private MutableLiveData<Integer> counter;
    
    public LiveData<Integer> getCounter() {
        if (counter == null) {
            counter = new MutableLiveData<>();
            counter.setValue(0);
        }
        return counter;
    }
    
    public void increment() {
        counter.setValue(counter.getValue() + 1);
    }
}
```

### Activity

```java
public class MainActivity extends AppCompatActivity {
    
    private CounterViewModel viewModel;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);
        
        TextView textCounter = findViewById(R.id.textCounter);
        Button btnIncrement = findViewById(R.id.btnIncrement);
        
        // Observer le compteur
        viewModel.getCounter().observe(this, count -> {
            textCounter.setText("Compteur : " + count);
        });
        
        btnIncrement.setOnClickListener(v -> viewModel.increment());
    }
}
```

---

## Exercice 3 : Repository Pattern

**Objectif** : Centraliser l'accès aux données.

```java
public class UserRepository {
    
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    
    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }
    
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    
    public void insert(User user) {
        new Thread(() -> userDao.insert(user)).start();
    }
    
    public void update(User user) {
        new Thread(() -> userDao.update(user)).start();
    }
    
    public void delete(User user) {
        new Thread(() -> userDao.delete(user)).start();
    }
}
```

---

## Exercice 4 : Intégration complète

**Objectif** : Application complète avec tous les composants.

### Fonctionnalités requises

1. **Room Database** : Stockage local
2. **RecyclerView** : Affichage liste
3. **ViewModel** : Gestion logique
4. **LiveData** : Observation données
5. **Navigation** : Fragments avec Bottom Nav
6. **Material Design** : Interface moderne

---

## Mini-projet 1 : Application de citations

### Description

Application pour sauvegarder et afficher des citations inspirantes.

### Fonctionnalités

1. **Afficher citations** (RecyclerView)
2. **Ajouter citation** (formulaire)
3. **Supprimer citation** (swipe)
4. **Catégories** (motivation, humour, sagesse)
5. **Recherche** par texte
6. **Partager** citation (Intent.ACTION_SEND)

### Modèle de données

```java
@Entity(tableName = "quotes")
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String text;
    private String author;
    private String category;
    private long timestamp;
    
    // Getters/Setters
}
```

### Architecture

- **Entity** : Quote
- **DAO** : QuoteDao (insert, delete, getAll, searchByText)
- **Repository** : QuoteRepository
- **ViewModel** : QuoteViewModel
- **UI** : MainActivity (liste) + AddQuoteActivity

---

## Mini-projet 2 : Gestionnaire de dépenses

### Description

Tracker de dépenses quotidiennes.

### Fonctionnalités

1. **Ajouter dépense** : montant, catégorie, date
2. **Liste dépenses** : tri par date décroissante
3. **Total du mois** : calcul automatique
4. **Catégories** : Alimentation, Transport, Loisirs, Autre
5. **Filtrer** par catégorie
6. **Graphique simple** : dépenses par catégorie (optionnel)

### Modèle

```java
@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private double amount;
    private String category;
    private String description;
    private long date;
}
```

### Calculs

```java
@Query("SELECT SUM(amount) FROM expenses WHERE date >= :startOfMonth")
LiveData<Double> getTotalThisMonth(long startOfMonth);

@Query("SELECT * FROM expenses WHERE category = :category")
LiveData<List<Expense>> getExpensesByCategory(String category);
```

---

## Mini-projet 3 : Application de TODO avancée

### Description

Gestionnaire de tâches avec catégories et priorités.

### Fonctionnalités

1. **CRUD tâches**
2. **Priorités** : Haute, Moyenne, Basse (couleurs différentes)
3. **Catégories** : Travail, Personnel, Urgent
4. **Date limite** : alerte si proche
5. **Filtres** : Par statut, catégorie, priorité
6. **Recherche** par titre
7. **Statistiques** : nombre de tâches par statut

### Modèle avancé

```java
@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private boolean completed;
    private String category;
    private int priority; // 1=Haute, 2=Moyenne, 3=Basse
    private long dueDate;
    private long createdAt;
}
```

### DAO avancé

```java
@Query("SELECT * FROM tasks WHERE completed = :isCompleted ORDER BY priority ASC, dueDate ASC")
LiveData<List<Task>> getTasksByStatus(boolean isCompleted);

@Query("SELECT * FROM tasks WHERE category = :category AND completed = 0")
LiveData<List<Task>> getActiveTasksByCategory(String category);

@Query("SELECT COUNT(*) FROM tasks WHERE completed = 0 AND dueDate < :today")
LiveData<Integer> getOverdueTasks(long today);
```

---

## Projet final : Application de réservation

### Description

Système de réservation (restaurant, coiffeur, médecin...).

### Fonctionnalités principales

1. **Liste créneaux disponibles**
2. **Réserver créneau** : nom, téléphone, date, heure
3. **Mes réservations** : liste avec statut
4. **Annuler réservation** : confirmation
5. **Notifications** : rappel 1h avant
6. **Historique** : réservations passées
7. **Admin** : valider/refuser réservations

### Modèles

```java
@Entity
public class TimeSlot {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date; // "2025-12-25"
    private String time; // "14:00"
    private boolean available;
}

@Entity
public class Reservation {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int timeSlotId;
    private String customerName;
    private String customerPhone;
    private String status; // PENDING, CONFIRMED, CANCELLED
    private long createdAt;
}
```

### Architecture avancée

- **3 Fragments** : Disponibilités, Mes réservations, Profil
- **Bottom Navigation**
- **2 ViewModels** : TimeSlotViewModel, ReservationViewModel
- **2 Repositories**
- **Material Design** : Cards, FAB, Snackbar
- **Validation** : numéro téléphone, date future

### Grille d'évaluation

| Critère | Points |
|---------|--------|
| Architecture MVVM | 4 |
| Room Database complet | 4 |
| Navigation Fragments | 3 |
| CRUD réservations | 4 |
| Validation données | 2 |
| Material Design | 2 |
| Code propre | 3 |

**Total** : /22 (bonus possible)

---

## Conseils pour les projets

### Planification

1. **Dessiner** les écrans sur papier
2. **Lister** les entités et relations
3. **Définir** les opérations CRUD nécessaires
4. **Prévoir** les cas d'erreur

### Développement

1. **Commencer** par la base de données
2. **Tester** chaque DAO individuellement
3. **Créer** UI basique
4. **Connecter** ViewModel
5. **Améliorer** UI progressivement

### Tests

- Tester sur plusieurs tailles d'écran
- Vérifier rotation écran
- Tester avec beaucoup de données
- Vérifier cas limites (liste vide, valeurs nulles)

---

👨‍🏫 **Module 10 - Projets de synthèse** | ISITCOM 2025-2026
