# Corrections - Exercices Module 10

## Exercice 1 : Architecture MVVM basique

### Task.java (Entity)

```java
package tn.isitcom.mvvm.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String description;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
```

### TaskDao.java

```java
package tn.isitcom.mvvm.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.mvvm.data.model.Task;

@Dao
public interface TaskDao {
    
    @Insert
    void insert(Task task);
    
    @Update
    void update(Task task);
    
    @Delete
    void delete(Task task);
    
    @Query("SELECT * FROM tasks ORDER BY id DESC")
    LiveData<List<Task>> getAllTasks();
    
    @Query("DELETE FROM tasks")
    void deleteAll();
}
```

### AppDatabase.java

```java
package tn.isitcom.mvvm.data.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import tn.isitcom.mvvm.data.dao.TaskDao;
import tn.isitcom.mvvm.data.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    
    private static AppDatabase instance;
    
    public abstract TaskDao taskDao();
    
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "task_database"
            )
            .fallbackToDestructiveMigration()
            .build();
        }
        return instance;
    }
}
```

### TaskRepository.java

```java
package tn.isitcom.mvvm.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tn.isitcom.mvvm.data.dao.TaskDao;
import tn.isitcom.mvvm.data.database.AppDatabase;
import tn.isitcom.mvvm.data.model.Task;

public class TaskRepository {
    
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private ExecutorService executorService;

    public TaskRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void insert(Task task) {
        executorService.execute(() -> taskDao.insert(task));
    }

    public void update(Task task) {
        executorService.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        executorService.execute(() -> taskDao.delete(task));
    }

    public void deleteAll() {
        executorService.execute(() -> taskDao.deleteAll());
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
```

### TaskViewModel.java

```java
package tn.isitcom.mvvm.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import tn.isitcom.mvvm.data.model.Task;
import tn.isitcom.mvvm.data.repository.TaskRepository;

public class TaskViewModel extends AndroidViewModel {
    
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void update(Task task) {
        repository.update(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
}
```

### TaskAdapter.java

```java
package tn.isitcom.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import tn.isitcom.mvvm.R;
import tn.isitcom.mvvm.data.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks = new ArrayList<>();
    private OnTaskClickListener listener;

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    public TaskAdapter(OnTaskClickListener listener) {
        this.listener = listener;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.tvTitle.setText(task.getTitle());
        holder.tvDescription.setText(task.getDescription());
        
        holder.itemView.setOnClickListener(v -> listener.onTaskClick(task));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}
```

### MainActivity.java

```java
package tn.isitcom.mvvm.ui;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import tn.isitcom.mvvm.R;
import tn.isitcom.mvvm.adapter.TaskAdapter;
import tn.isitcom.mvvm.data.model.Task;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private TaskViewModel taskViewModel;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewModel
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);

        // Observer les données
        taskViewModel.getAllTasks().observe(this, tasks -> {
            adapter.setTasks(tasks);
        });

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            Task task = new Task("Nouvelle tâche", "Description");
            taskViewModel.insert(task);
            Toast.makeText(this, "Tâche ajoutée", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onTaskClick(Task task) {
        Toast.makeText(this, "Clic sur: " + task.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
```

### build.gradle (Module: app)

```gradle
dependencies {
    // Room
    implementation 'androidx.room:room-runtime:2.6.1'
    annotationProcessor 'androidx.room:room-compiler:2.6.1'
    
    // Lifecycle (ViewModel, LiveData)
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
    
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    
    // Material Design
    implementation 'com.google.android.material:material:1.11.0'
}
```

---

## Exercice 2 : LiveData et Observer

### CounterViewModel.java

```java
package tn.isitcom.livedata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
        Integer currentValue = counter.getValue();
        if (currentValue != null) {
            counter.setValue(currentValue + 1);
        }
    }
    
    public void decrement() {
        Integer currentValue = counter.getValue();
        if (currentValue != null && currentValue > 0) {
            counter.setValue(currentValue - 1);
        }
    }
    
    public void reset() {
        counter.setValue(0);
    }
}
```

### MainActivity.java

```java
package tn.isitcom.livedata;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer ViewModel
        viewModel = new ViewModelProvider(this).get(CounterViewModel.class);

        TextView tvCounter = findViewById(R.id.tv_counter);
        Button btnIncrement = findViewById(R.id.btn_increment);
        Button btnDecrement = findViewById(R.id.btn_decrement);
        Button btnReset = findViewById(R.id.btn_reset);

        // Observer le compteur
        viewModel.getCounter().observe(this, count -> {
            tvCounter.setText("Compteur: " + count);
        });

        // Listeners
        btnIncrement.setOnClickListener(v -> viewModel.increment());
        btnDecrement.setOnClickListener(v -> viewModel.decrement());
        btnReset.setOnClickListener(v -> viewModel.reset());
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:id="@+id/tv_counter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Compteur: 0"
        android:textSize="32sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_increment"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="+ Incrémenter"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_decrement"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="- Décrémenter"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_reset"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="↻ Réinitialiser" />

</LinearLayout>
```

**Points clés** :
- ✅ Données survivent à la rotation d'écran
- ✅ UI se met à jour automatiquement
- ✅ Séparation logique métier / UI

---

## Exercice 3 : Repository Pattern

### User.java (Entity)

```java
package tn.isitcom.repository.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private String email;
    private int age;

    public User(String name, String email, int age) {
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
```

### UserDao.java

```java
package tn.isitcom.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.repository.entity.User;

@Dao
public interface UserDao {
    
    @Insert
    void insert(User user);
    
    @Update
    void update(User user);
    
    @Delete
    void delete(User user);
    
    @Query("SELECT * FROM users ORDER BY name ASC")
    LiveData<List<User>> getAllUsers();
    
    @Query("SELECT * FROM users WHERE id = :id")
    LiveData<User> getUserById(int id);
}
```

### UserRepository.java (Complet)

```java
package tn.isitcom.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tn.isitcom.repository.dao.UserDao;
import tn.isitcom.repository.database.AppDatabase;
import tn.isitcom.repository.entity.User;

public class UserRepository {
    
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newSingleThreadExecutor();
    }

    // Retourne LiveData pour observation
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserById(int id) {
        return userDao.getUserById(id);
    }

    // Opérations en arrière-plan
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }
}
```

### UserViewModel.java

```java
package tn.isitcom.repository.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import tn.isitcom.repository.UserRepository;
import tn.isitcom.repository.entity.User;

public class UserViewModel extends AndroidViewModel {
    
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> getUserById(int id) {
        return repository.getUserById(id);
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }
}
```

### MainActivity.java

```java
package tn.isitcom.repository;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import tn.isitcom.repository.adapter.UserAdapter;
import tn.isitcom.repository.entity.User;
import tn.isitcom.repository.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        // Observer
        userViewModel.getAllUsers().observe(this, users -> {
            adapter.setUsers(users);
        });

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            User user = new User("Ahmed", "ahmed@example.com", 25);
            userViewModel.insert(user);
        });
    }
}
```

**Avantages du Repository Pattern** :
- ✅ Centralise l'accès aux données
- ✅ Facilite les tests unitaires
- ✅ Permet de changer de source (API, local, cache)
- ✅ Sépare logique métier de la persistance

---

## Mini-projet 1 : Application de citations

### Quote.java (Entity)

```java
package tn.isitcom.quotes.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quotes")
public class Quote {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String text;
    private String author;
    private String category;
    private long timestamp;

    public Quote(String text, String author, String category) {
        this.text = text;
        this.author = author;
        this.category = category;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
```

### QuoteDao.java

```java
package tn.isitcom.quotes.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import tn.isitcom.quotes.entity.Quote;

@Dao
public interface QuoteDao {
    
    @Insert
    void insert(Quote quote);
    
    @Delete
    void delete(Quote quote);
    
    @Query("SELECT * FROM quotes ORDER BY timestamp DESC")
    LiveData<List<Quote>> getAllQuotes();
    
    @Query("SELECT * FROM quotes WHERE category = :category ORDER BY timestamp DESC")
    LiveData<List<Quote>> getQuotesByCategory(String category);
    
    @Query("SELECT * FROM quotes WHERE text LIKE '%' || :searchText || '%' OR author LIKE '%' || :searchText || '%'")
    LiveData<List<Quote>> searchQuotes(String searchText);
}
```

### QuoteRepository.java

```java
package tn.isitcom.quotes.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import tn.isitcom.quotes.dao.QuoteDao;
import tn.isitcom.quotes.database.QuoteDatabase;
import tn.isitcom.quotes.entity.Quote;

public class QuoteRepository {
    
    private QuoteDao quoteDao;
    private ExecutorService executorService;

    public QuoteRepository(Application application) {
        QuoteDatabase db = QuoteDatabase.getInstance(application);
        quoteDao = db.quoteDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Quote>> getAllQuotes() {
        return quoteDao.getAllQuotes();
    }

    public LiveData<List<Quote>> getQuotesByCategory(String category) {
        return quoteDao.getQuotesByCategory(category);
    }

    public LiveData<List<Quote>> searchQuotes(String searchText) {
        return quoteDao.searchQuotes(searchText);
    }

    public void insert(Quote quote) {
        executorService.execute(() -> quoteDao.insert(quote));
    }

    public void delete(Quote quote) {
        executorService.execute(() -> quoteDao.delete(quote));
    }
}
```

### MainActivity.java (avec recherche)

```java
package tn.isitcom.quotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import tn.isitcom.quotes.adapter.QuoteAdapter;
import tn.isitcom.quotes.entity.Quote;
import tn.isitcom.quotes.viewmodel.QuoteViewModel;

public class MainActivity extends AppCompatActivity implements QuoteAdapter.OnQuoteListener {

    private QuoteViewModel viewModel;
    private QuoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(QuoteViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new QuoteAdapter(this);
        recyclerView.setAdapter(adapter);

        // Observer
        viewModel.getAllQuotes().observe(this, quotes -> {
            adapter.setQuotes(quotes);
        });

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Quote quote = adapter.getQuoteAt(viewHolder.getAdapterPosition());
                viewModel.delete(quote);
                Snackbar.make(recyclerView, "Citation supprimée", Snackbar.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // FAB
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddQuoteActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.searchQuotes(query).observe(MainActivity.this, quotes -> {
                    adapter.setQuotes(quotes);
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    viewModel.getAllQuotes().observe(MainActivity.this, quotes -> {
                        adapter.setQuotes(quotes);
                    });
                }
                return true;
            }
        });
        
        return true;
    }

    @Override
    public void onShareClick(Quote quote) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareText = "\"" + quote.getText() + "\"\n- " + quote.getAuthor();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Partager via"));
    }
}
```

---

## Mini-projet 2 : Gestionnaire de dépenses

*(Code similaire au Module 7, avec ajout de fonctionnalités avancées)*

### Fonctionnalités supplémentaires

```java
// Dans ExpenseDao.java
@Query("SELECT SUM(amount) FROM expenses WHERE date >= :startOfMonth AND date <= :endOfMonth")
LiveData<Double> getTotalForMonth(long startOfMonth, long endOfMonth);

@Query("SELECT category, SUM(amount) as total FROM expenses GROUP BY category ORDER BY total DESC")
LiveData<List<CategoryTotal>> getTotalByCategory();

// Classe pour résultat
public class CategoryTotal {
    public String category;
    public double total;
}
```

### ExpenseViewModel.java (avec calculs)

```java
public class ExpenseViewModel extends AndroidViewModel {
    
    private ExpenseRepository repository;
    private LiveData<List<Expense>> allExpenses;
    private LiveData<Double> monthlyTotal;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        repository = new ExpenseRepository(application);
        allExpenses = repository.getAllExpenses();
        
        // Calcul début/fin du mois
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        long startOfMonth = cal.getTimeInMillis();
        
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        long endOfMonth = cal.getTimeInMillis();
        
        monthlyTotal = repository.getTotalForMonth(startOfMonth, endOfMonth);
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public LiveData<Double> getMonthlyTotal() {
        return monthlyTotal;
    }

    public LiveData<List<Expense>> filterByCategory(String category) {
        return repository.getExpensesByCategory(category);
    }

    public void insert(Expense expense) {
        repository.insert(expense);
    }

    public void delete(Expense expense) {
        repository.delete(expense);
    }
}
```

---

## Mini-projet 3 : TODO avancée

### Task.java (Entity complète)

```java
@Entity(tableName = "tasks")
public class Task {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String description;
    private boolean completed;
    private String category; // "Travail", "Personnel", "Urgent"
    private int priority; // 1=Haute, 2=Moyenne, 3=Basse
    private long dueDate;
    private long createdAt;

    public Task(String title, String description, String category, int priority, long dueDate) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.category = category;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = System.currentTimeMillis();
    }

    // Getters et Setters complets
    // ...
}
```

### TaskDao.java (Requêtes avancées)

```java
@Dao
public interface TaskDao {
    
    @Insert
    void insert(Task task);
    
    @Update
    void update(Task task);
    
    @Delete
    void delete(Task task);
    
    // Toutes les tâches par priorité et date
    @Query("SELECT * FROM tasks WHERE completed = :isCompleted ORDER BY priority ASC, dueDate ASC")
    LiveData<List<Task>> getTasksByStatus(boolean isCompleted);
    
    // Tâches actives par catégorie
    @Query("SELECT * FROM tasks WHERE category = :category AND completed = 0 ORDER BY priority ASC")
    LiveData<List<Task>> getActiveTasksByCategory(String category);
    
    // Nombre de tâches en retard
    @Query("SELECT COUNT(*) FROM tasks WHERE completed = 0 AND dueDate < :today")
    LiveData<Integer> getOverdueTasks(long today);
    
    // Statistiques par priorité
    @Query("SELECT priority, COUNT(*) as count FROM tasks WHERE completed = 0 GROUP BY priority")
    LiveData<List<PriorityCount>> getTaskCountByPriority();
    
    // Recherche
    @Query("SELECT * FROM tasks WHERE title LIKE '%' || :searchText || '%' ORDER BY priority ASC")
    LiveData<List<Task>> searchTasks(String searchText);
}
```

### MainActivity.java (avec filtres)

```java
public class MainActivity extends AppCompatActivity {

    private TaskViewModel viewModel;
    private TaskAdapter adapter;
    private String currentFilter = "ALL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        setupRecyclerView();
        setupFilters();
        loadTasks();
    }

    private void setupFilters() {
        Chip chipAll = findViewById(R.id.chip_all);
        Chip chipActive = findViewById(R.id.chip_active);
        Chip chipCompleted = findViewById(R.id.chip_completed);
        Chip chipWork = findViewById(R.id.chip_work);
        Chip chipPersonal = findViewById(R.id.chip_personal);

        chipAll.setOnClickListener(v -> {
            currentFilter = "ALL";
            loadTasks();
        });

        chipActive.setOnClickListener(v -> {
            currentFilter = "ACTIVE";
            viewModel.getTasksByStatus(false).observe(this, tasks -> {
                adapter.setTasks(tasks);
            });
        });

        chipCompleted.setOnClickListener(v -> {
            currentFilter = "COMPLETED";
            viewModel.getTasksByStatus(true).observe(this, tasks -> {
                adapter.setTasks(tasks);
            });
        });

        chipWork.setOnClickListener(v -> {
            currentFilter = "WORK";
            viewModel.getTasksByCategory("Travail").observe(this, tasks -> {
                adapter.setTasks(tasks);
            });
        });
    }

    private void loadTasks() {
        viewModel.getAllTasks().observe(this, tasks -> {
            adapter.setTasks(tasks);
        });
    }
}
```

---

## Projet final : Réservation

### TimeSlot.java

```java
@Entity(tableName = "time_slots")
public class TimeSlot {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String date; // "2025-12-25"
    private String time; // "14:00"
    private boolean available;

    // Constructeur, Getters, Setters
}
```

### Reservation.java

```java
@Entity(tableName = "reservations", 
        foreignKeys = @ForeignKey(entity = TimeSlot.class,
                                  parentColumns = "id",
                                  childColumns = "timeSlotId",
                                  onDelete = ForeignKey.CASCADE))
public class Reservation {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private int timeSlotId;
    private String customerName;
    private String customerPhone;
    private String status; // "PENDING", "CONFIRMED", "CANCELLED"
    private long createdAt;

    // Constructeur, Getters, Setters
}
```

### ReservationWithSlot.java (Relation)

```java
public class ReservationWithSlot {
    @Embedded
    public Reservation reservation;
    
    @Relation(
        parentColumn = "timeSlotId",
        entityColumn = "id"
    )
    public TimeSlot timeSlot;
}
```

### Validation

```java
public class ValidationUtils {
    
    public static boolean isValidPhone(String phone) {
        // Format tunisien : +216 XX XXX XXX ou 20 XXX XXX
        return phone.matches("^(\\+216)?[2-9]\\d{7}$");
    }
    
    public static boolean isFutureDate(String date, String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            Date reservationDate = sdf.parse(date + " " + time);
            return reservationDate != null && reservationDate.after(new Date());
        } catch (ParseException e) {
            return false;
        }
    }
}
```

### Grille d'évaluation détaillée

| Critère | Points | Détails |
|---------|--------|----------|
| **Architecture MVVM** | 4 | Séparation correcte Model/View/ViewModel |
| **Room Database** | 4 | Entities, DAOs, Relations, Migrations |
| **Navigation Fragments** | 3 | Bottom Nav + 3 fragments minimum |
| **CRUD réservations** | 4 | Create, Read, Update, Delete fonctionnels |
| **Validation données** | 2 | Téléphone, date future, champs requis |
| **Material Design** | 2 | Cards, FAB, Snackbar, Thème cohérent |
| **Code propre** | 3 | Nommage, commentaires, organisation packages |
| **Bonus** | +2 | Notifications, animations, fonctionnalités extras |
| **Total** | **/22** | (+2 bonus possible) |

---

👨‍🏫 **Corrections Module 10** | ISITCOM 2025-2026
