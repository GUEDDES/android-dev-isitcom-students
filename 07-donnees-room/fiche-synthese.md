# Module 7 : Fiche de synthèse

## 🎯 Concepts essentiels

**Room** = Bibliothèque officielle Android pour SQLite  
**Avantages** : Moins d'erreurs, code lisible, vérification compilation

---

## 🏛️ Architecture Room

```
Application
  │
  ├── Database (classe abstraite)
  │     │
  │     ├── Entity (table)
  │     │
  │     └── DAO (requêtes)
  │
  └── Repository (optionnel)
```

---

## 🛠️ Implémentation en 3 étapes

### Étape 1 : Entity (table)

```java
@Entity(tableName = "contacts")
public class Contact {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    @ColumnInfo(name = "nom")
    private String nom;
    
    @ColumnInfo(name = "telephone")
    private String telephone;
    
    // Constructeur, getters, setters
    public Contact(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getNom() { return nom; }
    public String getTelephone() { return telephone; }
}
```

---

### Étape 2 : DAO (Data Access Object)

```java
@Dao
public interface ContactDao {
    
    @Insert
    void insert(Contact contact);
    
    @Update
    void update(Contact contact);
    
    @Delete
    void delete(Contact contact);
    
    @Query("SELECT * FROM contacts ORDER BY nom ASC")
    List<Contact> getAllContacts();
    
    @Query("SELECT * FROM contacts WHERE id = :contactId")
    Contact getContactById(int contactId);
    
    @Query("SELECT * FROM contacts WHERE nom LIKE :search")
    List<Contact> searchByName(String search);
    
    @Query("DELETE FROM contacts")
    void deleteAll();
}
```

---

### Étape 3 : Database

```java
@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    
    public abstract ContactDao contactDao();
    
    private static AppDatabase instance;
    
    // Singleton
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "contacts_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
```

---

## ⚡ Utilisation dans Activity

### Opérations CRUD

```java
public class MainActivity extends AppCompatActivity {
    
    private AppDatabase db;
    private ContactDao contactDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        db = AppDatabase.getInstance(this);
        contactDao = db.contactDao();
        
        // CREATE
        new Thread(() -> {
            Contact contact = new Contact("Alice", "123456");
            contactDao.insert(contact);
        }).start();
        
        // READ
        new Thread(() -> {
            List<Contact> contacts = contactDao.getAllContacts();
            runOnUiThread(() -> {
                // Afficher dans RecyclerView
            });
        }).start();
        
        // UPDATE
        new Thread(() -> {
            Contact contact = contactDao.getContactById(1);
            contact.setNom("Alice Updated");
            contactDao.update(contact);
        }).start();
        
        // DELETE
        new Thread(() -> {
            Contact contact = contactDao.getContactById(1);
            contactDao.delete(contact);
        }).start();
    }
}
```

---

## 💾 Annotations principales

| Annotation | Usage |
|------------|-------|
| `@Entity` | Définir une table |
| `@PrimaryKey` | Clé primaire |
| `@ColumnInfo` | Nom de colonne |
| `@Ignore` | Exclure un champ |
| `@Dao` | Interface DAO |
| `@Insert` | Insérer |
| `@Update` | Modifier |
| `@Delete` | Supprimer |
| `@Query` | Requête SQL personnalisée |

---

## 🔍 Requêtes avancées

```java
@Dao
public interface ContactDao {
    
    // Recherche LIKE
    @Query("SELECT * FROM contacts WHERE nom LIKE '%' || :search || '%'")
    List<Contact> searchContacts(String search);
    
    // Compter
    @Query("SELECT COUNT(*) FROM contacts")
    int getCount();
    
    // Avec paramètres multiples
    @Query("SELECT * FROM contacts WHERE nom = :nom AND telephone = :tel")
    Contact findByNameAndPhone(String nom, String tel);
    
    // LiveData (observation automatique)
    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContactsLive();
}
```

---

## 🔄 Migration de version

```java
@Database(entities = {Contact.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE contacts ADD COLUMN email TEXT");
        }
    };
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "contacts_database"
            )
            .addMigrations(MIGRATION_1_2)
            .build();
        }
        return instance;
    }
}
```

---

## ⚠️ Règles importantes

❌ **JAMAIS** d'opération Room sur le thread principal (UI)  
✅ Toujours utiliser Thread, AsyncTask, ou Executor  
✅ Alternative moderne : Kotlin Coroutines ou RxJava

---

## 🛠️ Executor helper

```java
public class DatabaseExecutor {
    
    private static final ExecutorService executor = 
        Executors.newSingleThreadExecutor();
    
    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }
}

// Utilisation
DatabaseExecutor.execute(() -> {
    contactDao.insert(contact);
});
```

---

## 🔑 Mémo rapide

```java
// 1. Entity
@Entity
public class MonObjet {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    // ...
}

// 2. DAO
@Dao
public interface MonDao {
    @Insert
    void insert(MonObjet obj);
    
    @Query("SELECT * FROM MonObjet")
    List<MonObjet> getAll();
}

// 3. Database
@Database(entities = {MonObjet.class}, version = 1)
public abstract class AppDb extends RoomDatabase {
    public abstract MonDao monDao();
}

// 4. Utilisation
AppDb db = AppDb.getInstance(this);
new Thread(() -> {
    db.monDao().insert(new MonObjet("test"));
}).start();
```

---

## 📝 Checklist

✅ Ajouter dépendances Room dans build.gradle  
✅ Créer Entity avec @PrimaryKey  
✅ Créer DAO avec @Insert, @Update, @Delete, @Query  
✅ Créer Database avec getInstance (Singleton)  
✅ Toutes opérations dans Thread secondaire  
✅ Tester insertion/lecture avant d'aller plus loin  

---

👨‍🏫 **Module 7 - Room Database** | ISITCOM 2025-2026
