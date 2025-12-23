# Module 7 : Quiz d'auto-évaluation - Room Database

## Questions à choix multiples

### Question 1
Qu'est-ce que Room en Android ?

A) Un composant d'interface  
B) Une bibliothèque pour SQLite  
C) Un gestionnaire de fragments  
D) Un layout

<details>
<summary>Réponse</summary>
B) Une bibliothèque officielle Android pour faciliter l'utilisation de SQLite
</details>

---

### Question 2
Quelle annotation définit une table dans Room ?

A) @Table  
B) @Entity  
C) @Database  
D) @Model

<details>
<summary>Réponse</summary>
B) @Entity
</details>

---

### Question 3
Que représente DAO dans Room ?

A) Data Access Object  
B) Database Application Object  
C) Data Array Operation  
D) Delete And Override

<details>
<summary>Réponse</summary>
A) Data Access Object - Interface pour accéder aux données
</details>

---

### Question 4
Quelle annotation utiliser pour la clé primaire ?

A) @Id  
B) @PrimaryKey  
C) @Key  
D) @Primary

<details>
<summary>Réponse</summary>
B) @PrimaryKey
</details>

---

### Question 5
Comment générer automatiquement l'ID ?

A) @PrimaryKey(auto = true)  
B) @PrimaryKey(generate = true)  
C) @PrimaryKey(autoGenerate = true)  
D) @AutoIncrement

<details>
<summary>Réponse</summary>
C) @PrimaryKey(autoGenerate = true)
</details>

---

### Question 6
Où DOIT-ON exécuter les opérations Room ?

A) Sur le thread principal  
B) Sur un thread secondaire  
C) N'importe où  
D) Dans onCreate() uniquement

<details>
<summary>Réponse</summary>
B) Toujours sur un thread secondaire (jamais sur UI thread)
</details>

---

### Question 7
Quelle annotation pour insérer des données ?

A) @Add  
B) @Create  
C) @Insert  
D) @Save

<details>
<summary>Réponse</summary>
C) @Insert
</details>

---

### Question 8
Comment faire une requête personnalisée ?

A) @Select  
B) @Query  
C) @Find  
D) @Search

<details>
<summary>Réponse</summary>
B) @Query("SELECT * FROM table")
</details>

---

### Question 9
Quel est le pattern recommandé pour utiliser Room ?

A) Activity directement  
B) Singleton Database  
C) Nouvelle instance à chaque fois  
D) Static methods

<details>
<summary>Réponse</summary>
B) Singleton Database (une seule instance dans toute l'app)
</details>

---

### Question 10
Que fait `fallbackToDestructiveMigration()` ?

A) Fait une migration propre  
B) Supprime et recrée la base si version change  
C) Sauvegarde les données  
D) Bloque les changements

<details>
<summary>Réponse</summary>
B) Supprime et recrée la base (perte de données) - à utiliser en développement
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
@Entity(tableName = "users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
}
```

A) Crée une interface  
B) Définit une table "users" avec colonnes id et name  
C) Crée un DAO  
D) Exécute une requête

<details>
<summary>Réponse</summary>
B) Définit la structure d'une table "users" avec id auto-incrémenté
</details>

---

### Question 12
Quelle est l'erreur dans ce code ?

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AppDatabase db = AppDatabase.getInstance(this);
    User user = new User("Alice");
    db.userDao().insert(user);
}
```

A) Pas d'erreur  
B) Opération Room sur thread principal  
C) Manque setContentView  
D) getInstance mal appelé

<details>
<summary>Réponse</summary>
B) L'insertion est sur le thread principal - doit être dans un Thread ou Executor
</details>

---

### Question 13
Que retourne cette requête ?

```java
@Query("SELECT * FROM tasks WHERE completed = 1")
List<Task> getCompletedTasks();
```

A) Toutes les tâches  
B) Les tâches non complétées  
C) Les tâches complétées  
D) La première tâche

<details>
<summary>Réponse</summary>
C) Liste des tâches où completed = 1 (true)
</details>

---

### Question 14
Comment corriger ce code pour le rendre asynchrone ?

```java
User user = new User("Bob");
db.userDao().insert(user);
```

<details>
<summary>Réponse</summary>

```java
new Thread(() -> {
    User user = new User("Bob");
    db.userDao().insert(user);
}).start();

// Ou avec Executor
Executors.newSingleThreadExecutor().execute(() -> {
    db.userDao().insert(user);
});
```
</details>

---

## Questions pratiques

### Question 15
Créez une Entity "Product" avec :
- id (auto-généré)
- name (String)
- price (double)
- Table nommée "products"

<details>
<summary>Réponse</summary>

```java
@Entity(tableName = "products")
public class Product {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private double price;
    
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    // Getters et setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
}
```
</details>

---

### Question 16
Créez un DAO pour Product avec méthodes CRUD de base.

<details>
<summary>Réponse</summary>

```java
@Dao
public interface ProductDao {
    
    @Insert
    void insert(Product product);
    
    @Update
    void update(Product product);
    
    @Delete
    void delete(Product product);
    
    @Query("SELECT * FROM products ORDER BY name ASC")
    List<Product> getAllProducts();
    
    @Query("SELECT * FROM products WHERE id = :productId")
    Product getProductById(int productId);
    
    @Query("DELETE FROM products")
    void deleteAll();
}
```
</details>

---

### Question 17
Créez la classe Database pour Product.

<details>
<summary>Réponse</summary>

```java
@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    
    public abstract ProductDao productDao();
    
    private static AppDatabase instance;
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "products_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
```
</details>

---

### Question 18
Comment rechercher des produits par nom (LIKE) ?

<details>
<summary>Réponse</summary>

```java
@Dao
public interface ProductDao {
    
    @Query("SELECT * FROM products WHERE name LIKE '%' || :search || '%'")
    List<Product> searchProducts(String search);
}

// Utilisation
List<Product> results = dao.searchProducts("phone");
// Retourne tous produits contenant "phone"
```
</details>

---

### Question 19
Quelle est la différence entre `List<Product>` et `LiveData<List<Product>>` en retour ?

<details>
<summary>Réponse</summary>

- `List<Product>` : Données statiques, une seule lecture
- `LiveData<List<Product>>` : Observable, met à jour automatiquement l'UI quand les données changent

```java
// Static
@Query("SELECT * FROM products")
List<Product> getAll();

// Observable (recommandé)
@Query("SELECT * FROM products")
LiveData<List<Product>> getAllLive();
```
</details>

---

### Question 20
Comment gérer une migration de version 1 à 2 (ajout colonne "category") ?

<details>
<summary>Réponse</summary>

```java
@Database(entities = {Product.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(
                "ALTER TABLE products ADD COLUMN category TEXT DEFAULT 'General'"
            );
        }
    };
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "products_database"
            )
            .addMigrations(MIGRATION_1_2)
            .build();
        }
        return instance;
    }
}
```
</details>

---

## Barème

- **18-20/20** : Excellent ! Maîtrise complète de Room
- **15-17/20** : Très bien, quelques détails à revoir
- **12-14/20** : Bien, revoir les concepts avancés
- **< 12/20** : Revoir le module en détail

---

## Points clés à retenir

✅ **Entity** = Table avec @PrimaryKey  
✅ **DAO** = Interface avec @Insert, @Update, @Delete, @Query  
✅ **Database** = Singleton avec getInstance()  
✅ **Thread** = Jamais d'opération Room sur UI thread  
✅ **Migration** = Gérer changements de version proprement  
✅ **LiveData** = Observer automatiquement les changements  

---

👨‍🏫 **Module 7 - Room Database** | ISITCOM 2025-2026
