# 📝 Exercices complémentaires - Tous modules

## Module 4 : Interfaces et Layouts

### Exercice bonus 1 : Carte de visite interactive

**Objectif** : Créer une carte de visite avec boutons interactifs.

**Fonctionnalités** :
- Photo de profil (ImageView)
- Nom, titre, entreprise (TextViews)
- Boutons : Appeler, Email, Site web
- Chaque bouton ouvre l'app appropriée (Intent implicite)

**Compétences** : Layouts, ImageView, Button, Intents implicites

---

### Exercice bonus 2 : Convertisseur d'unités

**Objectif** : Convertir différentes unités de mesure.

**Fonctionnalités** :
- Spinner pour choisir type (longueur, poids, température)
- EditText pour valeur d'entrée
- Spinner pour unité source
- Spinner pour unité destination
- TextView pour résultat
- Bouton "Convertir"

**Formules** :
- Kilomètres → Miles : `miles = km * 0.621371`
- Celsius → Fahrenheit : `F = (C * 9/5) + 32`
- Kilogrammes → Livres : `lbs = kg * 2.20462`

---

## Module 5 : Cycle de vie et Intents

### Exercice bonus 3 : Minuteur avec sauvegarde d'état

**Objectif** : Minuteur qui survit à la rotation d'écran.

**Fonctionnalités** :
- Input : Durée en secondes
- Bouton Start/Pause
- Affichage temps restant
- Sauvegarder temps restant lors rotation

**Code** :
```java
private Handler handler = new Handler();
private Runnable runnable;
private int remainingTime;

@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("time", remainingTime);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
        remainingTime = savedInstanceState.getInt("time");
    }
}
```

---

### Exercice bonus 4 : Galerie d'images avec partage

**Objectif** : Afficher images avec possibilité de partager.

**Fonctionnalités** :
- GridView/RecyclerView avec images
- Clic sur image : Afficher en grand (nouvelle Activity)
- Bouton "Partager" : Intent.ACTION_SEND

**Code partage** :
```java
Intent shareIntent = new Intent(Intent.ACTION_SEND);
shareIntent.setType("image/*");
shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
startActivity(Intent.createChooser(shareIntent, "Partager via"));
```

---

## Module 6 : RecyclerView

### Exercice bonus 5 : Liste avec filtres multiples

**Objectif** : RecyclerView avec filtrage et tri.

**Fonctionnalités** :
- Afficher liste de produits (nom, prix, catégorie)
- SearchView : Filtrer par nom
- Spinner : Filtrer par catégorie
- Menu : Trier par nom ou prix

**Modèle** :
```java
public class Product {
    private String name;
    private double price;
    private String category;
}
```

**Filtrage** :
```java
public void filter(String query, String category) {
    List<Product> filtered = new ArrayList<>();
    for (Product p : allProducts) {
        boolean matchName = p.getName().toLowerCase().contains(query.toLowerCase());
        boolean matchCategory = category.equals("Tous") || p.getCategory().equals(category);
        if (matchName && matchCategory) {
            filtered.add(p);
        }
    }
    adapter.setProducts(filtered);
}
```

---

### Exercice bonus 6 : RecyclerView avec sections

**Objectif** : Liste avec headers de section.

**Exemple** : Liste de contacts groupés par initiale (A, B, C...).

**Approche** :
1. Créer 2 ViewHolders : HeaderViewHolder, ContactViewHolder
2. Dans Adapter, détecter type d'élément
3. Retourner bon ViewHolder selon type

**Code** :
```java
private static final int TYPE_HEADER = 0;
private static final int TYPE_ITEM = 1;

@Override
public int getItemViewType(int position) {
    return items.get(position).isHeader() ? TYPE_HEADER : TYPE_ITEM;
}

@Override
public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_HEADER) {
        return new HeaderViewHolder(...);
    } else {
        return new ItemViewHolder(...);
    }
}
```

---

## Module 7 : Room Database

### Exercice bonus 7 : Migration de base de données

**Objectif** : Ajouter une colonne à une table existante.

**Scénario** :
- Version 1 : Table User avec id, name
- Version 2 : Ajouter colonne email

**Migration** :
```java
static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE users ADD COLUMN email TEXT");
    }
};

// Dans Database
Room.databaseBuilder(context, AppDatabase.class, "app.db")
    .addMigrations(MIGRATION_1_2)
    .build();
```

---

### Exercice bonus 8 : Relations Room

**Objectif** : Implémenter relation One-to-Many.

**Exemple** : Utilisateur avec plusieurs tâches.

**Entités** :
```java
@Entity
public class User {
    @PrimaryKey
    public int userId;
    public String name;
}

@Entity(foreignKeys = @ForeignKey(
    entity = User.class,
    parentColumns = "userId",
    childColumns = "userOwnerId",
    onDelete = ForeignKey.CASCADE
))
public class Task {
    @PrimaryKey
    public int taskId;
    public String title;
    public int userOwnerId;
}
```

**Relation** :
```java
public class UserWithTasks {
    @Embedded public User user;
    @Relation(
        parentColumn = "userId",
        entityColumn = "userOwnerId"
    )
    public List<Task> tasks;
}

// DAO
@Transaction
@Query("SELECT * FROM User WHERE userId = :userId")
UserWithTasks getUserWithTasks(int userId);
```

---

## Module 8 : Fragments et Navigation

### Exercice bonus 9 : Bottom Navigation avec badges

**Objectif** : Afficher badges de notification sur tabs.

**Code** :
```java
BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNav.getChildAt(0);

// Badge sur premier item
View itemView = menuView.getChildAt(0);
View badge = LayoutInflater.from(this).inflate(R.layout.badge, menuView, false);
itemView.addView(badge);
```

**Ou avec Material 3** :
```java
BadgeDrawable badge = bottomNav.getOrCreateBadge(R.id.nav_notifications);
badge.setNumber(5);
badge.setVisible(true);
```

---

### Exercice bonus 10 : ViewPager2 avec TabLayout

**Objectif** : Swipe entre fragments avec tabs.

**Layout** :
```xml
<com.google.android.material.tabs.TabLayout
    android:id="@+id/tabLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content" />

<androidx.viewpager2.widget.ViewPager2
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="0dp" />
```

**Code** :
```java
ViewPager2 viewPager = findViewById(R.id.viewPager);
TabLayout tabLayout = findViewById(R.id.tabLayout);

ViewPagerAdapter adapter = new ViewPagerAdapter(this);
viewPager.setAdapter(adapter);

new TabLayoutMediator(tabLayout, viewPager,
    (tab, position) -> {
        switch (position) {
            case 0: tab.setText("Accueil"); break;
            case 1: tab.setText("Profil"); break;
            case 2: tab.setText("Paramètres"); break;
        }
    }
).attach();
```

---

## Projets mini-synthèse

### Projet A : Application de recettes

**Fonctionnalités** :
- Liste recettes (RecyclerView)
- Détail recette (nouveau Fragment)
- Sauvegarder favoris (Room)
- Recherche par nom
- Catégories (Entrée, Plat, Dessert)

**Modèles** :
```java
@Entity
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String ingredients;
    private String instructions;
    private String category;
    private boolean favorite;
}
```

---

### Projet B : Tracker d'habitudes

**Fonctionnalités** :
- Créer habitudes (ex: Boire 2L d'eau)
- Marquer comme fait chaque jour
- Statistiques : Streak actuel (jours consécutifs)
- Historique par habitude

**Modèles** :
```java
@Entity
public class Habit {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int currentStreak;
}

@Entity
public class HabitEntry {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int habitId;
    private long date;
    private boolean completed;
}
```

---

### Projet C : Application de météo locale

**Fonctionnalités** :
- Afficher météo actuelle (température, description)
- Prévisions 5 jours
- Sauvegarder villes favorites (Room)
- Changement de ville

**API** : [OpenWeatherMap](https://openweathermap.org/api) (gratuit)

**Librairie** : Retrofit pour appels API

---

## Défis avancés

### Défi 1 : Mode hors ligne

Modifier une app existante pour :
- Sauvegarder données localement (Room)
- Détecter connexion Internet
- Afficher données cachées si offline
- Synchroniser quand connexion retrouvée

---

### Défi 2 : Authentification locale

Créer système de connexion :
- Écran inscription (nom, email, mot de passe)
- Sauvegarder utilisateur (Room, mot de passe hashé)
- Écran connexion avec validation
- Session utilisateur (SharedPreferences)

---

### Défi 3 : Widget Android

Créer widget pour écran d'accueil :
- Afficher résumé info (ex: nombre de tâches)
- Mise à jour automatique
- Clic ouvre l'application

**Guide** : [App Widgets](https://developer.android.com/guide/topics/appwidgets/overview)

---

## Ressources supplémentaires

### Datasets pour pratiquer
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) - API REST factice
- [The Movie DB API](https://www.themoviedb.org/settings/api) - Films/séries
- [OpenWeatherMap](https://openweathermap.org/api) - Météo
- [NewsAPI](https://newsapi.org/) - Actualités

### Outils de test
- [Postman](https://www.postman.com/) - Tester APIs
- [JSON Editor Online](https://jsoneditoronline.org/) - Visualiser JSON
- [Regex101](https://regex101.com/) - Tester regex

---

👨‍🏫 **Exercices complémentaires** | ISITCOM 2025-2026
