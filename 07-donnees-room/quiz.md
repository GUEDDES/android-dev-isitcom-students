# Quiz - Module 7 : Room Database

## Questions à choix multiples

### Question 1
Qu'est-ce que Room ?

A) Une base de données  
B) Une bibliothèque Android pour SQLite  
C) Un serveur de données  
D) Un éditeur de base de données

<details>
<summary>Réponse</summary>
B) Bibliothèque officielle Android qui facilite l'utilisation de SQLite
</details>

---

### Question 2
Que représente une Entity ?

A) Une requête SQL  
B) Une table de la base de données  
C) Une connexion  
D) Un DAO

<details>
<summary>Réponse</summary>
B) Une Entity = une table (classe annotée @Entity)
</details>

---

### Question 3
Que signifie DAO ?

A) Data Access Object  
B) Database Android Object  
C) Data Application Object  
D) Direct Access Operation

<details>
<summary>Réponse</summary>
A) Data Access Object - Interface contenant les méthodes CRUD
</details>

---

### Question 4
Quelle annotation pour définir une table ?

A) @Table  
B) @Entity  
C) @Database  
D) @Room

<details>
<summary>Réponse</summary>
B) @Entity
</details>

---

### Question 5
Quelle annotation pour la clé primaire auto-incrémentée ?

A) @Id(autoGenerate = true)  
B) @PrimaryKey(autoGenerate = true)  
C) @Key(auto = true)  
D) @AutoIncrement

<details>
<summary>Réponse</summary>
B) @PrimaryKey(autoGenerate = true)
</details>

---

### Question 6
Quelle annotation pour insérer des données ?

A) @Insert  
B) @Add  
C) @Create  
D) @Save

<details>
<summary>Réponse</summary>
A) @Insert dans le DAO
</details>

---

### Question 7
Pourquoi ne JAMAIS faire d'opérations Room sur le thread principal ?

A) C'est interdit par Room  
B) Bloque l'interface (ANR - Application Not Responding)  
C) Ça ne marche pas  
D) C'est lent

<details>
<summary>Réponse</summary>
B) Bloque l'UI et peut causer ANR (crash)
</details>

---

### Question 8
Comment exécuter une opération Room en arrière-plan ?

A) Directement dans onCreate()  
B) new Thread(() -> { dao.insert(...) }).start()  
C) AsyncTask (déprécié)  
D) B ou C

<details>
<summary>Réponse</summary>
D) Thread, Executor, AsyncTask (déprécié), ou Coroutines (Kotlin)
</details>

---

### Question 9
Quelle annotation pour une requête SQL personnalisée ?

A) @SQL  
B) @Query  
C) @Request  
D) @Select

<details>
<summary>Réponse</summary>
B) @Query("SELECT * FROM ...")
</details>

---

### Question 10
Comment compter le nombre d'enregistrements ?

```java
A) @Query("COUNT * FROM users")
B) @Query("SELECT COUNT(*) FROM users")
C) @Count("users")
D) dao.count()
```

<details>
<summary>Réponse</summary>
B) @Query("SELECT COUNT(*) FROM users") int getCount();
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
    // getters/setters
}
```

A) Crée une requête  
B) Définit une table "users" avec colonnes id et name  
C) Crée un DAO  
D) Ouvre une connexion

<details>
<summary>Réponse</summary>
B) Déclare une table avec 2 colonnes
</details>

---

### Question 12
Que manque-t-il dans cette Database ?

```java
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // ???
}
```

A) Rien  
B) La méthode abstraite pour obtenir le DAO  
C) Le constructeur  
D) L'instance

<details>
<summary>Réponse</summary>
B) public abstract UserDao userDao();
</details>

---

### Question 13
Quelle est l'erreur ?

```java
public void addUser(User user) {
    AppDatabase db = AppDatabase.getInstance(this);
    db.userDao().insert(user); // Exécuté sur UI thread
}
```

A) Pas d'erreur  
B) Opération sur thread principal (crash)  
C) Mauvaise syntaxe  
D) Database non initialisée

<details>
<summary>Réponse</summary>
B) Doit être dans new Thread() ou Executor
</details>

---

### Question 14
Comment rechercher par nom ?

```java
@Dao
public interface UserDao {
    @Query("SELECT * FROM users WHERE name = :userName")
    User findByName(String userName);
}
```

Que représente `:userName` ?

A) Une colonne  
B) Un paramètre de la méthode  
C) Une constante  
D) Une erreur

<details>
<summary>Réponse</summary>
B) Paramètre lié à l'argument de la méthode
</details>

---

### Question 15
Que fait fallbackToDestructiveMigration() ?

```java
Room.databaseBuilder(...)
    .fallbackToDestructiveMigration()
    .build();
```

A) Sauvegarde les données  
B) Supprime et recrée la DB si changement de version  
C) Migre automatiquement  
D) Crée un backup

<details>
<summary>Réponse</summary>
B) Détruit et recrée la DB (perte de données) - à éviter en production
</details>

---

## Questions ouvertes

### Question 16
Expliquez le pattern Singleton pour la Database.

<details>
<summary>Réponse</summary>
Une seule instance de la Database existe dans toute l'application. Cela évite les multiples connexions coûteuses et les conflits d'accès. Implémentation via getInstance() avec synchronized.
</details>

---

### Question 17
Différence entre @Insert et @Query("INSERT...") ?

<details>
<summary>Réponse</summary>
- **@Insert** : Room génère automatiquement le SQL, simple et rapide
- **@Query("INSERT...")** : SQL manuel, plus flexible pour insérer avec conditions complexes
</details>

---

## Exercice pratique

### Question 18
Créez une Entity "Product" avec :
- id (auto)
- name (String)
- price (double)
- stock (int)

<details>
<summary>Solution</summary>

```java
@Entity(tableName = "products")
public class Product {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "name")
    private String name;
    
    @ColumnInfo(name = "price")
    private double price;
    
    @ColumnInfo(name = "stock")
    private int stock;
    
    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
```
</details>

---

### Question 19
Créez le DAO correspondant avec CRUD complet.

<details>
<summary>Solution</summary>

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
    
    @Query("SELECT * FROM products WHERE price < :maxPrice")
    List<Product> getProductsCheaperThan(double maxPrice);
    
    @Query("DELETE FROM products")
    void deleteAll();
    
    @Query("SELECT COUNT(*) FROM products")
    int getCount();
}
```
</details>

---

## Barème

- **18-19/19** : Excellent ! Maîtrise complète de Room
- **15-17/19** : Très bien
- **12-14/19** : Bien, quelques révisions
- **< 12/19** : Revoir le module en profondeur

---

👨‍🏫 **Module 7 - Room Database** | ISITCOM 2025-2026
