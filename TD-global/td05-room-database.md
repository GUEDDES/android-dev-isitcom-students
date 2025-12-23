# TD05 : Base de données Room

## 🎯 Objectifs

- Créer une base de données locale avec Room.
- Implémenter les opérations CRUD.
- Lier Room avec RecyclerView.

---

## Partie 1 : Configuration Room (20 min)

### Étape 1 : Ajouter les dépendances

```gradle
dependencies {
    def room_version = "2.6.1"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
}
```

### Étape 2 : Créer l'Entity

```java
@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String major;
    private int age;
    
    // Constructeur, getters, setters
}
```

### Étape 3 : Créer le DAO

```java
@Dao
public interface StudentDao {
    @Insert
    void insert(Student student);
    
    @Update
    void update(Student student);
    
    @Delete
    void delete(Student student);
    
    @Query("SELECT * FROM students ORDER BY name ASC")
    List<Student> getAllStudents();
    
    @Query("SELECT * FROM students WHERE major = :major")
    List<Student> getStudentsByMajor(String major);
}
```

### Étape 4 : Créer la Database

```java
@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    
    public abstract StudentDao studentDao();
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "student_database"
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries() // Pour le TD uniquement
            .build();
        }
        return instance;
    }
}
```

---

## Partie 2 : CRUD complet (60 min)

### Consignes

Créer une application de gestion d'étudiants :

1. **Écran principal** :
   - RecyclerView affichant tous les étudiants.
   - FAB pour ajouter un étudiant.
   - Au clic sur un étudiant : modifier.
   - Clic long : supprimer (avec confirmation).

2. **Écran ajout/modification** :
   - Champs : Nom, Filière, Âge.
   - Bouton "Enregistrer".

### Code exemple : MainActivity.java

```java
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private AppDatabase database;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadStudents();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudents();
    }

    private void loadStudents() {
        studentList = database.studentDao().getAllStudents();
        if (adapter == null) {
            adapter = new StudentAdapter(studentList, this::editStudent, this::deleteStudent);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.updateList(studentList);
        }
    }

    private void editStudent(Student student) {
        Intent intent = new Intent(this, AddStudentActivity.class);
        intent.putExtra("STUDENT_ID", student.getId());
        startActivity(intent);
    }

    private void deleteStudent(Student student) {
        new AlertDialog.Builder(this)
            .setTitle("Supprimer")
            .setMessage("Supprimer " + student.getName() + " ?")
            .setPositiveButton("Oui", (dialog, which) -> {
                database.studentDao().delete(student);
                loadStudents();
                Toast.makeText(this, "Supprimé", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Annuler", null)
            .show();
    }
}
```

---

## Partie 3 : Requêtes avancées (20 min)

### Consignes

1. Ajouter un Spinner pour filtrer par filière.
2. Implémenter la requête `getStudentsByMajor`.
3. Ajouter un bouton "Statistiques" qui affiche :
   - Nombre total d'étudiants.
   - Âge moyen.
   - Répartition par filière.

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Configuration Room | 4 |
| Ajout d'étudiant | 4 |
| Modification | 4 |
| Suppression | 4 |
| Requêtes avancées | 4 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
