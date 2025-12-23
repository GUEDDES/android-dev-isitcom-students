# Corrections - Exercices Module 7

## Exercice 1 : Base simple

### Student.java (Entity)

```java
package tn.isitcom.roombasic.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "students")
public class Student {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private int age;

    // Constructeur
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

### StudentDao.java

```java
package tn.isitcom.roombasic.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.roombasic.entity.Student;

@Dao
public interface StudentDao {
    
    @Insert
    void insert(Student student);
    
    @Insert
    void insertAll(List<Student> students);
    
    @Update
    void update(Student student);
    
    @Delete
    void delete(Student student);
    
    @Query("SELECT * FROM students")
    List<Student> getAll();
    
    @Query("SELECT * FROM students WHERE id = :id")
    Student getById(int id);
    
    @Query("DELETE FROM students")
    void deleteAll();
}
```

### AppDatabase.java

```java
package tn.isitcom.roombasic.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import tn.isitcom.roombasic.dao.StudentDao;
import tn.isitcom.roombasic.entity.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    
    private static AppDatabase instance;
    
    public abstract StudentDao studentDao();
    
    // Singleton
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "student_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
```

### MainActivity.java

```java
package tn.isitcom.roombasic;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.roombasic.database.AppDatabase;
import tn.isitcom.roombasic.entity.Student;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RoomBasic";
    private AppDatabase database;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser database et executor
        database = AppDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        // Exécuter opérations en arrière-plan
        executor.execute(() -> {
            // Nettoyer la base
            database.studentDao().deleteAll();
            
            // Ajouter 3 étudiants
            Student s1 = new Student("Ahmed Ben Ali", 20);
            Student s2 = new Student("Fatma Trabelsi", 22);
            Student s3 = new Student("Mohamed Gharbi", 21);
            
            database.studentDao().insert(s1);
            database.studentDao().insert(s2);
            database.studentDao().insert(s3);
            
            Log.d(TAG, "3 étudiants ajoutés");
            
            // Lire tous les étudiants
            List<Student> students = database.studentDao().getAll();
            
            Log.d(TAG, "=== Liste des étudiants ===");
            for (Student student : students) {
                Log.d(TAG, student.toString());
            }
        });
    }
}
```

### build.gradle (Module: app)

```gradle
dependencies {
    // Room
    implementation 'androidx.room:room-runtime:2.6.1'
    annotationProcessor 'androidx.room:room-compiler:2.6.1'
}
```

### Logs attendus (Logcat)

```
D/RoomBasic: 3 étudiants ajoutés
D/RoomBasic: === Liste des étudiants ===
D/RoomBasic: Student{id=1, name='Ahmed Ben Ali', age=20}
D/RoomBasic: Student{id=2, name='Fatma Trabelsi', age=22}
D/RoomBasic: Student{id=3, name='Mohamed Gharbi', age=21}
```

---

## Exercice 2 : CRUD complet

### Note.java (Entity)

```java
package tn.isitcom.notes.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String content;
    private long date; // Timestamp

    public Note(String title, String content, long date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
```

### NoteDao.java

```java
package tn.isitcom.notes.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.notes.entity.Note;

@Dao
public interface NoteDao {
    
    @Insert
    long insert(Note note); // Retourne l'ID inséré
    
    @Update
    void update(Note note);
    
    @Delete
    void delete(Note note);
    
    @Query("SELECT * FROM notes ORDER BY date DESC")
    List<Note> getAll();
    
    @Query("SELECT * FROM notes WHERE id = :id")
    Note getById(int id);
    
    @Query("DELETE FROM notes")
    void deleteAll();
}
```

### NoteDatabase.java

```java
package tn.isitcom.notes.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import tn.isitcom.notes.dao.NoteDao;
import tn.isitcom.notes.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    
    private static NoteDatabase instance;
    
    public abstract NoteDao noteDao();
    
    public static synchronized NoteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                NoteDatabase.class,
                "notes_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
```

### MainActivity.java (Tests CRUD)

```java
package tn.isitcom.notes;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.notes.database.NoteDatabase;
import tn.isitcom.notes.entity.Note;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NotesApp";
    private NoteDatabase database;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = NoteDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        testerCRUD();
    }

    private void testerCRUD() {
        executor.execute(() -> {
            // Nettoyer
            database.noteDao().deleteAll();
            Log.d(TAG, "=== Test CRUD ===");

            // 1. CREATE (Insert)
            Note note1 = new Note("Cours Android", 
                "Apprendre Room Database", System.currentTimeMillis());
            long id1 = database.noteDao().insert(note1);
            note1.setId((int) id1);
            Log.d(TAG, "INSERT: Note ajoutée avec ID = " + id1);

            Note note2 = new Note("Shopping", 
                "Acheter du lait", System.currentTimeMillis());
            long id2 = database.noteDao().insert(note2);
            note2.setId((int) id2);
            Log.d(TAG, "INSERT: Note ajoutée avec ID = " + id2);

            // 2. READ (Get All)
            List<Note> allNotes = database.noteDao().getAll();
            Log.d(TAG, "\nREAD ALL: " + allNotes.size() + " notes");
            for (Note note : allNotes) {
                Log.d(TAG, "  - " + note.getTitle() + ": " + note.getContent());
            }

            // 3. READ (Get By ID)
            Note foundNote = database.noteDao().getById((int) id1);
            Log.d(TAG, "\nREAD BY ID: " + foundNote.getTitle());

            // 4. UPDATE
            note1.setTitle("Cours Android (modifié)");
            note1.setContent("Room est puissant !");
            database.noteDao().update(note1);
            Log.d(TAG, "\nUPDATE: Note 1 modifiée");

            Note updatedNote = database.noteDao().getById((int) id1);
            Log.d(TAG, "Après UPDATE: " + updatedNote.getTitle());

            // 5. DELETE
            database.noteDao().delete(note2);
            Log.d(TAG, "\nDELETE: Note 2 supprimée");

            List<Note> remainingNotes = database.noteDao().getAll();
            Log.d(TAG, "Notes restantes: " + remainingNotes.size());

            Log.d(TAG, "\n=== Test CRUD terminé ===");
        });
    }
}
```

---

## Exercice 3 : Room + RecyclerView

### NoteAdapter.java

```java
package tn.isitcom.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import tn.isitcom.notes.R;
import tn.isitcom.notes.entity.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnNoteListener listener;

    public interface OnNoteListener {
        void onNoteClick(Note note);
        void onNoteLongClick(Note note);
    }

    public NoteAdapter(OnNoteListener listener) {
        this.listener = listener;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent, tvDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNoteClick(notes.get(position));
                }
            });

            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onNoteLongClick(notes.get(position));
                }
                return true;
            });
        }

        public void bind(Note note) {
            tvTitle.setText(note.getTitle());
            tvContent.setText(note.getContent());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String dateStr = sdf.format(new Date(note.getDate()));
            tvDate.setText(dateStr);
        }
    }
}
```

### item_note.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="12dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/tv_title"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="18sp"
            android:textStyle="bold"
            android:textColor="@android:color/black" />

        <TextView
            android:id="@+id/tv_content"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:layout_marginTop="8dp"
            android:maxLines="2"
            android:ellipsize="end" />

        <TextView
            android:id="@+id/tv_date"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="12sp"
            android:textColor="@android:color/darker_gray"
            android:layout_marginTop="8dp" />

    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
```

### MainActivity.java (Complet)

```java
package tn.isitcom.notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.notes.adapter.NoteAdapter;
import tn.isitcom.notes.database.NoteDatabase;
import tn.isitcom.notes.entity.Note;

public class MainActivity extends AppCompatActivity implements NoteAdapter.OnNoteListener {

    private NoteDatabase database;
    private Executor executor;
    private NoteAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = NoteDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        // RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoteAdapter(this);
        recyclerView.setAdapter(adapter);

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> showAddDialog());

        // Charger notes
        loadNotes();
    }

    private void loadNotes() {
        executor.execute(() -> {
            List<Note> notes = database.noteDao().getAll();
            runOnUiThread(() -> adapter.setNotes(notes));
        });
    }

    private void showAddDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_note, null);
        
        EditText etTitle = dialogView.findViewById(R.id.et_title);
        EditText etContent = dialogView.findViewById(R.id.et_content);

        new AlertDialog.Builder(this)
            .setTitle("Nouvelle note")
            .setView(dialogView)
            .setPositiveButton("Ajouter", (dialog, which) -> {
                String title = etTitle.getText().toString().trim();
                String content = etContent.getText().toString().trim();

                if (!title.isEmpty() && !content.isEmpty()) {
                    Note note = new Note(title, content, System.currentTimeMillis());
                    executor.execute(() -> {
                        database.noteDao().insert(note);
                        runOnUiThread(() -> {
                            loadNotes();
                            Toast.makeText(this, "Note ajoutée", Toast.LENGTH_SHORT).show();
                        });
                    });
                }
            })
            .setNegativeButton("Annuler", null)
            .show();
    }

    @Override
    public void onNoteClick(Note note) {
        // Modification
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_note, null);
        
        EditText etTitle = dialogView.findViewById(R.id.et_title);
        EditText etContent = dialogView.findViewById(R.id.et_content);
        
        etTitle.setText(note.getTitle());
        etContent.setText(note.getContent());

        new AlertDialog.Builder(this)
            .setTitle("Modifier note")
            .setView(dialogView)
            .setPositiveButton("Modifier", (dialog, which) -> {
                note.setTitle(etTitle.getText().toString().trim());
                note.setContent(etContent.getText().toString().trim());
                
                executor.execute(() -> {
                    database.noteDao().update(note);
                    runOnUiThread(() -> {
                        loadNotes();
                        Toast.makeText(this, "Note modifiée", Toast.LENGTH_SHORT).show();
                    });
                });
            })
            .setNegativeButton("Annuler", null)
            .show();
    }

    @Override
    public void onNoteLongClick(Note note) {
        // Suppression
        new AlertDialog.Builder(this)
            .setTitle("Supprimer")
            .setMessage("Supprimer \"" + note.getTitle() + "\" ?")
            .setPositiveButton("Supprimer", (dialog, which) -> {
                executor.execute(() -> {
                    database.noteDao().delete(note);
                    runOnUiThread(() -> {
                        loadNotes();
                        Toast.makeText(this, "Note supprimée", Toast.LENGTH_SHORT).show();
                    });
                });
            })
            .setNegativeButton("Annuler", null)
            .show();
    }
}
```

### dialog_note.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/et_title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Titre"
        android:inputType="text" />

    <EditText
        android:id="@+id/et_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Contenu"
        android:inputType="textMultiLine"
        android:minLines="4"
        android:layout_marginTop="8dp" />

</LinearLayout>
```

---

## Exercice 4 : Requêtes avancées

### Book.java (Entity)

```java
package tn.isitcom.books.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "books")
public class Book {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String author;
    private boolean isRead;

    public Book(String title, String author, boolean isRead) {
        this.title = title;
        this.author = author;
        this.isRead = isRead;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isRead=" + isRead +
                '}';
    }
}
```

### BookDao.java (Requêtes avancées)

```java
package tn.isitcom.books.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import tn.isitcom.books.entity.Book;

@Dao
public interface BookDao {
    
    @Insert
    void insert(Book book);
    
    @Query("SELECT * FROM books")
    List<Book> getAll();
    
    // Requête 1 : Tous les livres lus
    @Query("SELECT * FROM books WHERE isRead = 1")
    List<Book> getReadBooks();
    
    // Requête 2 : Tous les livres non lus
    @Query("SELECT * FROM books WHERE isRead = 0")
    List<Book> getUnreadBooks();
    
    // Requête 3 : Livres par auteur
    @Query("SELECT * FROM books WHERE author = :author")
    List<Book> getBooksByAuthor(String author);
    
    // Requête 4 : Nombre total de livres
    @Query("SELECT COUNT(*) FROM books")
    int getTotalBooksCount();
    
    // Requêtes bonus
    @Query("SELECT COUNT(*) FROM books WHERE isRead = 1")
    int getReadBooksCount();
    
    @Query("SELECT COUNT(*) FROM books WHERE isRead = 0")
    int getUnreadBooksCount();
    
    @Query("SELECT DISTINCT author FROM books ORDER BY author")
    List<String> getAllAuthors();
    
    @Query("DELETE FROM books")
    void deleteAll();
}
```

### MainActivity.java (Tests)

```java
package tn.isitcom.books;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.books.database.BookDatabase;
import tn.isitcom.books.entity.Book;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "BooksApp";
    private BookDatabase database;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = BookDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        testerRequetes();
    }

    private void testerRequetes() {
        executor.execute(() -> {
            // Nettoyer et insérer des données de test
            database.bookDao().deleteAll();
            
            database.bookDao().insert(new Book("Clean Code", "Robert Martin", true));
            database.bookDao().insert(new Book("Design Patterns", "Gang of Four", false));
            database.bookDao().insert(new Book("Refactoring", "Martin Fowler", true));
            database.bookDao().insert(new Book("The Pragmatic Programmer", "Hunt & Thomas", false));
            database.bookDao().insert(new Book("Head First Java", "Sierra & Bates", true));
            database.bookDao().insert(new Book("Effective Java", "Joshua Bloch", false));
            database.bookDao().insert(new Book("Code Complete", "Steve McConnell", true));
            
            Log.d(TAG, "\n=== TESTS DES REQUÊTES ===");
            
            // Test 1 : Tous les livres
            List<Book> allBooks = database.bookDao().getAll();
            Log.d(TAG, "\n1. TOUS LES LIVRES (" + allBooks.size() + ")");
            for (Book book : allBooks) {
                Log.d(TAG, "  - " + book.getTitle() + " (" + book.getAuthor() + ")");
            }
            
            // Test 2 : Livres lus
            List<Book> readBooks = database.bookDao().getReadBooks();
            Log.d(TAG, "\n2. LIVRES LUS (" + readBooks.size() + ")");
            for (Book book : readBooks) {
                Log.d(TAG, "  - " + book.getTitle());
            }
            
            // Test 3 : Livres non lus
            List<Book> unreadBooks = database.bookDao().getUnreadBooks();
            Log.d(TAG, "\n3. LIVRES NON LUS (" + unreadBooks.size() + ")");
            for (Book book : unreadBooks) {
                Log.d(TAG, "  - " + book.getTitle());
            }
            
            // Test 4 : Livres par auteur
            List<Book> martinBooks = database.bookDao().getBooksByAuthor("Robert Martin");
            Log.d(TAG, "\n4. LIVRES DE ROBERT MARTIN (" + martinBooks.size() + ")");
            for (Book book : martinBooks) {
                Log.d(TAG, "  - " + book.getTitle());
            }
            
            // Test 5 : Statistiques
            int totalCount = database.bookDao().getTotalBooksCount();
            int readCount = database.bookDao().getReadBooksCount();
            int unreadCount = database.bookDao().getUnreadBooksCount();
            
            Log.d(TAG, "\n5. STATISTIQUES");
            Log.d(TAG, "  Total: " + totalCount);
            Log.d(TAG, "  Lus: " + readCount);
            Log.d(TAG, "  Non lus: " + unreadCount);
            
            // Test 6 : Tous les auteurs
            List<String> authors = database.bookDao().getAllAuthors();
            Log.d(TAG, "\n6. AUTEURS DISTINCTS (" + authors.size() + ")");
            for (String author : authors) {
                Log.d(TAG, "  - " + author);
            }
            
            Log.d(TAG, "\n=== TESTS TERMINÉS ===");
        });
    }
}
```

---

## Mini-projet : Gestion de dépenses

### Expense.java (Entity)

```java
package tn.isitcom.expenses.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expenses")
public class Expense {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private double amount;
    private String category;
    private String description;
    private long date;

    public Expense(double amount, String category, String description, long date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
```

### ExpenseDao.java

```java
package tn.isitcom.expenses.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.expenses.entity.Expense;

@Dao
public interface ExpenseDao {
    
    @Insert
    void insert(Expense expense);
    
    @Update
    void update(Expense expense);
    
    @Delete
    void delete(Expense expense);
    
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    List<Expense> getAll();
    
    @Query("SELECT SUM(amount) FROM expenses")
    double getTotalAmount();
    
    @Query("SELECT SUM(amount) FROM expenses WHERE category = :category")
    double getTotalByCategory(String category);
    
    @Query("SELECT DISTINCT category FROM expenses")
    List<String> getAllCategories();
}
```

### ExpenseAdapter.java

```java
package tn.isitcom.expenses.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import tn.isitcom.expenses.R;
import tn.isitcom.expenses.entity.Expense;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {

    private List<Expense> expenses = new ArrayList<>();
    private OnExpenseListener listener;

    public interface OnExpenseListener {
        void onExpenseClick(Expense expense);
    }

    public ExpenseAdapter(OnExpenseListener listener) {
        this.listener = listener;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_expense, parent, false);
        return new ExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.bind(expenses.get(position));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAmount, tvCategory, tvDescription, tvDate;

        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAmount = itemView.findViewById(R.id.tv_amount);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onExpenseClick(expenses.get(position));
                }
            });
        }

        public void bind(Expense expense) {
            tvAmount.setText(String.format(Locale.getDefault(), "%.2f TND", expense.getAmount()));
            tvCategory.setText(expense.getCategory());
            tvDescription.setText(expense.getDescription());
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            tvDate.setText(sdf.format(new Date(expense.getDate())));
        }
    }
}
```

### MainActivity.java (Complet)

```java
package tn.isitcom.expenses;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.expenses.adapter.ExpenseAdapter;
import tn.isitcom.expenses.database.ExpenseDatabase;
import tn.isitcom.expenses.entity.Expense;

public class MainActivity extends AppCompatActivity implements ExpenseAdapter.OnExpenseListener {

    private ExpenseDatabase database;
    private Executor executor;
    private ExpenseAdapter adapter;
    private TextView tvTotal;

    private static final String[] CATEGORIES = {
        "Alimentation", "Transport", "Logement", 
        "Santé", "Loisirs", "Autre"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = ExpenseDatabase.getInstance(this);
        executor = Executors.newSingleThreadExecutor();

        tvTotal = findViewById(R.id.tv_total);
        
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpenseAdapter(this);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> showAddDialog());

        loadExpenses();
    }

    private void loadExpenses() {
        executor.execute(() -> {
            List<Expense> expenses = database.expenseDao().getAll();
            double total = database.expenseDao().getTotalAmount();
            
            runOnUiThread(() -> {
                adapter.setExpenses(expenses);
                tvTotal.setText(String.format("Total: %.2f TND", total));
            });
        });
    }

    private void showAddDialog() {
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_expense, null);
        
        EditText etAmount = dialogView.findViewById(R.id.et_amount);
        Spinner spinnerCategory = dialogView.findViewById(R.id.spinner_category);
        EditText etDescription = dialogView.findViewById(R.id.et_description);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
            this, android.R.layout.simple_spinner_item, CATEGORIES);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);

        new AlertDialog.Builder(this)
            .setTitle("Nouvelle dépense")
            .setView(dialogView)
            .setPositiveButton("Ajouter", (dialog, which) -> {
                String amountStr = etAmount.getText().toString().trim();
                String category = spinnerCategory.getSelectedItem().toString();
                String description = etDescription.getText().toString().trim();

                if (amountStr.isEmpty() || description.isEmpty()) {
                    Toast.makeText(this, "Remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                double amount = Double.parseDouble(amountStr);
                Expense expense = new Expense(amount, category, description, System.currentTimeMillis());
                
                executor.execute(() -> {
                    database.expenseDao().insert(expense);
                    runOnUiThread(() -> {
                        loadExpenses();
                        Toast.makeText(this, "Dépense ajoutée", Toast.LENGTH_SHORT).show();
                    });
                });
            })
            .setNegativeButton("Annuler", null)
            .show();
    }

    @Override
    public void onExpenseClick(Expense expense) {
        // Afficher détail ou permettre modification
        String detail = String.format(
            "Montant: %.2f TND\nCatégorie: %s\nDescription: %s",
            expense.getAmount(), expense.getCategory(), expense.getDescription()
        );
        Toast.makeText(this, detail, Toast.LENGTH_LONG).show();
    }
}
```

### dialog_expense.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/et_amount"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Montant (TND)"
        android:inputType="numberDecimal" />

    <Spinner
        android:id="@+id/spinner_category"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="8dp" />

    <EditText
        android:id="@+id/et_description"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Description"
        android:minLines="2"
        android:layout_marginTop="8dp" />

</LinearLayout>
```

### Grille d'évaluation

| Critère | Points | Évaluation |
|---------|--------|-------------|
| Room correctement configuré | 4 | Entity, DAO, Database |
| CRUD fonctionnel | 6 | Insert, Update, Delete, Query |
| RecyclerView avec données | 4 | Adapter, affichage |
| Ajout/modification | 4 | DialogueSpinner, validation |
| Interface soignée | 2 | Material Components, design |
| **Total** | **/20** | |

---

👨‍🏫 **Corrections Module 7** | ISITCOM 2025-2026
