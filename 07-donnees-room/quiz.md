# Module 7 : Quiz d'auto-évaluation

## Questions à choix multiples

### Question 1
Qu'est-ce que Room ?

A) Une salle de cours  
B) Une bibliothèque de base de données pour Android  
C) Un type de layout  
D) Un composant Material Design

<details>
<summary>Réponse</summary>
B) Bibliothèque officielle Android pour SQLite (couche d'abstraction)
</details>

---

### Question 2
Quels sont les 3 composants principaux de Room ?

A) Activity, Fragment, View  
B) Entity, DAO, Database  
C) Model, View, Controller  
D) Table, Column, Row

<details>
<summary>Réponse</summary>
B) Entity (table), DAO (requêtes), Database (classe abstraite)
</details>

---

### Question 3
Que signifie @Entity ?

A) Annotation pour Activity  
B) Définit une table de base de données  
C) Marque un fragment  
D) Définit une vue

<details>
<summary>Réponse</summary>
B) @Entity définit une classe comme table de BD
</details>

---

### Question 4
Que fait @PrimaryKey(autoGenerate = true) ?

A) Génère automatiquement l'ID  
B) Crée une clé primaire auto-incrémentée  
C) Identifiant unique  
D) Toutes les réponses

<details>
<summary>Réponse</summary>
D) Toutes les réponses (ID unique auto-incrémenté)
</details>

---

### Question 5
Que signifie DAO ?

A) Data Access Object  
B) Database Access Only  
C) Data And Object  
D) Database Application Object

<details>
<summary>Réponse</summary>
A) Data Access Object (interface pour accéder aux données)
</details>

---

### Question 6
Quelle annotation pour insérer dans la BD ?

A) @Add  
B) @Insert  
C) @Create  
D) @Save

<details>
<summary>Réponse</summary>
B) @Insert
</details>

---

### Question 7
Comment exécuter une requête personnalisée ?

A) @Query("SELECT * FROM table")  
B) @Select("table")  
C) @Get("table")  
D) @Fetch("table")

<details>
<summary>Réponse</summary>
A) @Query avec requête SQL entre guillemets
</details>

---

### Question 8
Pourquoi ne jamais exécuter Room sur le thread principal ?

A) Interdit par Android  
B) Risque de crash (NetworkOnMainThreadException-like)  
C) Bloque l'interface  
D) B et C

<details>
<summary>Réponse</summary>
D) Bloquerait l'UI et crasherait l'app (StrictMode)
</details>

---

### Question 9
Comment observer automatiquement les changements ?

A) Avec Observer  
B) Avec LiveData  
C) Avec Thread  
D) Avec AsyncTask

<details>
<summary>Réponse</summary>
B) LiveData<List<Entity>> - s'actualise automatiquement
</details>

---

### Question 10
Que fait @TypeConverter ?

A) Change le type d'une Activity  
B) Convertit des types complexes (Date, List...) pour Room  
C) Transforme XML en Java  
D) Convertit des unités

<details>
<summary>Réponse</summary>
B) Permet de stocker des types non-primitifs (Date → Long, etc.)
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

A) Crée une Activity  
B) Définit une table "users" avec id et name  
C) Crée un fragment  
D) Affiche des utilisateurs

<details>
<summary>Réponse</summary>
B) Définit une entité Room = table SQL "users"
</details>

---

### Question 12
Corrigez ce DAO :

```java
@Dao
public interface UserDao {
    void insert(User user);
}
```

A) Pas d'erreur  
B) Manque @Insert  
C) Manque @Query  
D) Mauvais type de retour

<details>
<summary>Réponse</summary>
B) Il faut @Insert devant la méthode
</details>

---

### Question 13
Que fait cette requête ?

```java
@Query("SELECT * FROM tasks WHERE is_completed = 1")
LiveData<List<Task>> getCompletedTasks();
```

A) Insère des tâches  
B) Récupère les tâches terminées  
C) Supprime des tâches  
D) Met à jour des tâches

<details>
<summary>Réponse</summary>
B) SELECT avec filtre sur is_completed = 1 (true)
</details>

---

### Question 14
Comment utiliser ce DAO ?

```java
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
```

A) AppDatabase.userDao().insert(user)  
B) new AppDatabase().userDao()  
C) AppDatabase.getInstance(context).userDao()  
D) UserDao.insert(user)

<details>
<summary>Réponse</summary>
C) Via l'instance Singleton de la Database
</details>

---

### Question 15
Que fait ce code ?

```java
new Thread(() -> {
    userDao.insert(new User("Alice"));
}).start();
```

A) Crash  
B) Insère Alice dans un thread secondaire  
C) Affiche Alice  
D) Ne fait rien

<details>
<summary>Réponse</summary>
B) Exécute l'insertion en arrière-plan (correct)
</details>

---

## Questions ouvertes

### Question 16
Expliquez le pattern Singleton pour la Database.

<details>
<summary>Réponse</summary>
Une seule instance de la Database existe dans toute l'application. On utilise :
```java
private static AppDatabase INSTANCE;
public static synchronized AppDatabase getInstance(Context context) {
    if (INSTANCE == null) {
        INSTANCE = Room.databaseBuilder(...).build();
    }
    return INSTANCE;
}
```
Cela évite de créer plusieurs connexions coûteuses.
</details>

---

### Question 17
Comment migrer de version 1 à version 2 ?

<details>
<summary>Réponse</summary>
Créer un objet Migration :
```java
static final Migration MIGRATION_1_2 = new Migration(1, 2) {
    @Override
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE users ADD COLUMN email TEXT");
    }
};

// Puis l'ajouter
Room.databaseBuilder(...)
    .addMigrations(MIGRATION_1_2)
    .build();
```
</details>

---

### Question 18
Pourquoi utiliser LiveData avec Room ?

<details>
<summary>Réponse</summary>
LiveData :
- S'actualise automatiquement quand les données changent
- Respect le cycle de vie (pas de leak)
- Pas besoin de rafraîchir manuellement l'UI
- Room gère les threads automatiquement
</details>

---

## Barème

- **16-18/18** : Excellent ! Room maîtrisé
- **13-15/18** : Très bien
- **10-12/18** : Bien, revoir certains points
- **< 10/18** : Revoir le module

---

👨‍🏫 **Module 7 - Room Database** | ISITCOM 2025-2026
