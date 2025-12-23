# Corrections - Exercices Module 6

## Exercice 1 : Liste simple

### Classe Person.java

```java
package tn.isitcom.listesimple;

public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

### Layout item_person.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp"
    android:background="?attr/selectableItemBackground">

    <TextView
        android:id="@+id/tv_name"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:textColor="@android:color/black" />

</LinearLayout>
```

### PersonAdapter.java

```java
package tn.isitcom.listesimple;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private List<Person> personList;

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_person, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.bind(person);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    // ViewHolder interne
    class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);

            // Gestion du clic
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Person person = personList.get(position);
                    Toast.makeText(v.getContext(), 
                        person.getName(), 
                        Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(Person person) {
            tvName.setText(person.getName());
        }
    }
}
```

### MainActivity.java

```java
package tn.isitcom.listesimple;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Préparer les données
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Ahmed Ben Ali"));
        personList.add(new Person("Fatma Trabelsi"));
        personList.add(new Person("Mohamed Gharbi"));
        personList.add(new Person("Salma Nasr"));
        personList.add(new Person("Yassine Messaoudi"));
        personList.add(new Person("Nour Hamdi"));
        personList.add(new Person("Rania Jebali"));
        personList.add(new Person("Karim Sassi"));
        personList.add(new Person("Ines Ferchichi"));
        personList.add(new Person("Aymen Bouazizi"));

        // Configurer RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        PersonAdapter adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);
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
    android:orientation="vertical">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

</LinearLayout>
```

### build.gradle (Module: app) - Dépendance

```gradle
dependencies {
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

---

## Exercice 2 : Liste de contacts

### Contact.java

```java
package tn.isitcom.contacts;

public class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
```

### item_contact.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="18sp"
            android:textStyle="bold"
            android:textColor="@android:color/black" />

        <TextView
            android:id="@+id/tv_phone"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="16sp"
            android:textColor="@android:color/darker_gray"
            android:layout_marginTop="4dp" />

    </LinearLayout>
</androidx.cardview.widget.CardView>
```

### ContactAdapter.java

```java
package tn.isitcom.contacts;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);

            // Clic pour appeler
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Contact contact = contactList.get(position);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + contact.getPhone()));
                    v.getContext().startActivity(intent);
                }
            });
        }

        public void bind(Contact contact) {
            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());
        }
    }
}
```

### MainActivity.java

```java
package tn.isitcom.contacts;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Données
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Ahmed Ben Ali", "+216 20 123 456"));
        contacts.add(new Contact("Fatma Trabelsi", "+216 22 234 567"));
        contacts.add(new Contact("Mohamed Gharbi", "+216 24 345 678"));
        contacts.add(new Contact("Salma Nasr", "+216 26 456 789"));
        contacts.add(new Contact("Yassine Messaoudi", "+216 28 567 890"));
        contacts.add(new Contact("Nour Hamdi", "+216 29 678 901"));
        contacts.add(new Contact("Rania Jebali", "+216 21 789 012"));
        contacts.add(new Contact("Karim Sassi", "+216 23 890 123"));

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        ContactAdapter adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }
}
```

---

## Exercice 3 : Liste avec images

### Product.java

```java
package tn.isitcom.products;

public class Product {
    private String name;
    private double price;
    private int imageResId;

    public Product(String name, double price, int imageResId) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
```

### item_product.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView 
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
        android:orientation="horizontal"
        android:padding="12dp">

        <ImageView
            android:id="@+id/iv_product"
            android:layout_width="80dp"
            android:layout_height="80dp"
            android:scaleType="centerCrop"
            android:contentDescription="Image produit" />

        <LinearLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:orientation="vertical"
            android:layout_marginStart="16dp"
            android:layout_gravity="center_vertical">

            <TextView
                android:id="@+id/tv_name"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="18sp"
                android:textStyle="bold"
                android:textColor="@android:color/black" />

            <TextView
                android:id="@+id/tv_price"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:textSize="16sp"
                android:textColor="@android:color/holo_green_dark"
                android:layout_marginTop="4dp" />

        </LinearLayout>
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

### ProductAdapter.java

```java
package tn.isitcom.products;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProduct;
        private TextView tvName, tvPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_product);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPrice = itemView.findViewById(R.id.tv_price);

            // Clic
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Product product = productList.get(position);
                    String detail = String.format(Locale.getDefault(),
                        "%s - %.2f TND", product.getName(), product.getPrice());
                    Toast.makeText(v.getContext(), detail, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(Product product) {
            ivProduct.setImageResource(product.getImageResId());
            tvName.setText(product.getName());
            tvPrice.setText(String.format(Locale.getDefault(), 
                "%.2f TND", product.getPrice()));
        }
    }
}
```

### MainActivity.java

```java
package tn.isitcom.products;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Données (images à placer dans res/drawable)
        List<Product> products = new ArrayList<>();
        products.add(new Product("Ordinateur Portable", 2500.00, R.drawable.laptop));
        products.add(new Product("Smartphone", 1200.00, R.drawable.smartphone));
        products.add(new Product("Tablette", 800.00, R.drawable.tablet));
        products.add(new Product("Écouteurs", 150.00, R.drawable.headphones));
        products.add(new Product("Clavier", 80.00, R.drawable.keyboard));

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
```

---

## Exercice 4 : Grille d'éléments

### Modification MainActivity.java

```java
package tn.isitcom.products;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Données
        List<Product> products = new ArrayList<>();
        products.add(new Product("Ordinateur Portable", 2500.00, R.drawable.laptop));
        products.add(new Product("Smartphone", 1200.00, R.drawable.smartphone));
        products.add(new Product("Tablette", 800.00, R.drawable.tablet));
        products.add(new Product("Écouteurs", 150.00, R.drawable.headphones));
        products.add(new Product("Clavier", 80.00, R.drawable.keyboard));

        // RecyclerView avec GridLayoutManager
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        
        // 2 colonnes
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        
        ProductAdapter adapter = new ProductAdapter(products);
        recyclerView.setAdapter(adapter);
    }
}
```

### item_product_grid.xml (adapté pour grille)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView 
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
        android:padding="12dp">

        <ImageView
            android:id="@+id/iv_product"
            android:layout_width="match_parent"
            android:layout_height="120dp"
            android:scaleType="centerCrop"
            android:contentDescription="Image produit" />

        <TextView
            android:id="@+id/tv_name"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="16sp"
            android:textStyle="bold"
            android:textColor="@android:color/black"
            android:layout_marginTop="8dp"
            android:maxLines="2"
            android:ellipsize="end" />

        <TextView
            android:id="@+id/tv_price"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:textColor="@android:color/holo_green_dark"
            android:layout_marginTop="4dp" />

    </LinearLayout>
</androidx.cardview.widget.CardView>
```

---

## Exercice 5 : Suppression d'éléments

### ContactAdapter.java (avec suppression)

```java
package tn.isitcom.contacts;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvPhone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);

            // Clic simple : appeler
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Contact contact = contactList.get(position);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + contact.getPhone()));
                    v.getContext().startActivity(intent);
                }
            });

            // Clic long : supprimer
            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    afficherDialogueSupprimer(position, v);
                }
                return true;
            });
        }

        private void afficherDialogueSupprimer(int position, View view) {
            Contact contact = contactList.get(position);
            
            new AlertDialog.Builder(view.getContext())
                .setTitle("Supprimer contact")
                .setMessage("Voulez-vous supprimer " + contact.getName() + " ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    // Supprimer de la liste
                    contactList.remove(position);
                    // Notifier l'adapter
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, contactList.size());
                })
                .setNegativeButton("Annuler", null)
                .show();
        }

        public void bind(Contact contact) {
            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());
        }
    }
}
```

---

## Mini-projet : Application To-Do

### Task.java

```java
package tn.isitcom.todo;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String title;
    private String description;

    public Task(String title, String description) {
        this.id = idCounter++;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
```

### item_task.xml

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
            android:id="@+id/tv_description"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="14sp"
            android:textColor="@android:color/darker_gray"
            android:layout_marginTop="8dp"
            android:maxLines="2"
            android:ellipsize="end" />

    </LinearLayout>
</com.google.android.material.card.MaterialCardView>
```

### TaskAdapter.java

```java
package tn.isitcom.todo;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
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
        Task task = taskList.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);

            // Clic simple : afficher détail
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Task task = taskList.get(position);
                    String detail = task.getTitle() + "\n\n" + task.getDescription();
                    Toast.makeText(v.getContext(), detail, Toast.LENGTH_LONG).show();
                }
            });

            // Clic long : supprimer
            itemView.setOnLongClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    confirmerSuppression(position, v);
                }
                return true;
            });
        }

        private void confirmerSuppression(int position, View view) {
            Task task = taskList.get(position);
            
            new AlertDialog.Builder(view.getContext())
                .setTitle("Supprimer tâche")
                .setMessage("Supprimer \"" + task.getTitle() + "\" ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    taskList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, taskList.size());
                    Toast.makeText(view.getContext(), 
                        "Tâche supprimée", 
                        Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Annuler", null)
                .show();
        }

        public void bind(Task task) {
            tvTitle.setText(task.getTitle());
            tvDescription.setText(task.getDescription());
        }
    }
}
```

### MainActivity.java

```java
package tn.isitcom.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser la liste
        taskList = new ArrayList<>();
        taskList.add(new Task("Cours Android", "Terminer le module RecyclerView"));
        taskList.add(new Task("Projet ISITCOM", "Soumettre le rapport"));

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new TaskAdapter(taskList);
        recyclerView.setAdapter(adapter);

        // FAB pour ajouter
        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener(v -> afficherDialogueAjout());
    }

    private void afficherDialogueAjout() {
        // Créer layout du dialogue
        View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_add_task, null);
        
        EditText etTitle = dialogView.findViewById(R.id.et_title);
        EditText etDescription = dialogView.findViewById(R.id.et_description);

        new AlertDialog.Builder(this)
            .setTitle("Nouvelle tâche")
            .setView(dialogView)
            .setPositiveButton("Ajouter", (dialog, which) -> {
                String title = etTitle.getText().toString().trim();
                String description = etDescription.getText().toString().trim();

                if (!title.isEmpty() && !description.isEmpty()) {
                    Task newTask = new Task(title, description);
                    taskList.add(newTask);
                    adapter.notifyItemInserted(taskList.size() - 1);
                } else {
                    // Toast erreur
                }
            })
            .setNegativeButton("Annuler", null)
            .show();
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="8dp" />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_add"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:src="@android:drawable/ic_input_add"
        android:contentDescription="Ajouter tâche" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### dialog_add_task.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Titre">
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />
    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Description"
        android:layout_marginTop="16dp">
        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_description"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:minLines="3" />
    </com.google.android.material.textfield.TextInputLayout>

</LinearLayout>
```

### build.gradle (dépendances)

```gradle
dependencies {
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.coordinatorlayout:coordinatorlayout:1.2.0'
    implementation 'androidx.cardview:cardview:1.0.0'
}
```

### Grille d'évaluation

| Critère | Points | Évaluation |
|---------|--------|-------------|
| RecyclerView fonctionnel | 5 | Affichage liste complète |
| Ajout de tâches | 5 | Dialogue + ajout dynamique |
| Suppression avec confirmation | 4 | AlertDialog + notifyItemRemoved |
| Interface Material | 3 | FAB, CardView, TextInputLayout |
| Code propre | 3 | Commentaires, organisation |
| **Total** | **/20** | |

---

👨‍🏫 **Corrections Module 6** | ISITCOM 2025-2026
