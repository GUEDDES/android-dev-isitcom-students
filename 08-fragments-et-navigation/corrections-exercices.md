# Corrections - Exercices Module 8

## Exercice 1 : Fragment statique

### HomeFragment.java

```java
package tn.isitcom.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
```

### fragment_home.xml

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Fragment"
        android:textSize="24sp"
        android:textStyle="bold" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bienvenue dans le fragment Home"
        android:textSize="16sp"
        android:layout_marginTop="16dp" />

</LinearLayout>
```

### MainActivity.java

```java
package tn.isitcom.fragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Afficher HomeFragment au démarrage
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            
            HomeFragment homeFragment = new HomeFragment();
            transaction.add(R.id.fragment_container, homeFragment);
            transaction.commit();
        }
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

---

## Exercice 2 : Navigation entre Fragments

### HomeFragment.java

```java
package tn.isitcom.navfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
```

### ProfileFragment.java

```java
package tn.isitcom.navfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
```

### SettingsFragment.java

```java
package tn.isitcom.navfragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
```

### MainActivity.java (avec navigation)

```java
package tn.isitcom.navfragments;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHome = findViewById(R.id.btn_home);
        Button btnProfile = findViewById(R.id.btn_profile);
        Button btnSettings = findViewById(R.id.btn_settings);

        // Afficher Home par défaut
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        // Listeners
        btnHome.setOnClickListener(v -> loadFragment(new HomeFragment()));
        btnProfile.setOnClickListener(v -> loadFragment(new ProfileFragment()));
        btnSettings.setOnClickListener(v -> loadFragment(new SettingsFragment()));
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Permet retour arrière
        transaction.commit();
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

    <!-- Boutons de navigation -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="8dp">

        <Button
            android:id="@+id/btn_home"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Home"
            android:layout_margin="4dp" />

        <Button
            android:id="@+id/btn_profile"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Profile"
            android:layout_margin="4dp" />

        <Button
            android:id="@+id/btn_settings"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Settings"
            android:layout_margin="4dp" />
    </LinearLayout>

    <!-- Conteneur fragments -->
    <FrameLayout
        android:id="@+id/fragment_container"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1" />

</LinearLayout>
```

### fragment_home.xml, fragment_profile.xml, fragment_settings.xml

```xml
<!-- fragment_home.xml -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:background="#E3F2FD">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="🏠 Home"
        android:textSize="32sp"
        android:textStyle="bold" />
</LinearLayout>

<!-- fragment_profile.xml -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:background="#F3E5F5">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="👤 Profile"
        android:textSize="32sp"
        android:textStyle="bold" />
</LinearLayout>

<!-- fragment_settings.xml -->
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:background="#FFF3E0">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="⚙️ Settings"
        android:textSize="32sp"
        android:textStyle="bold" />
</LinearLayout>
```

---

## Exercice 3 : Navigation Component

### build.gradle (Module: app)

```gradle
dependencies {
    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment:2.7.6'
    implementation 'androidx.navigation:navigation-ui:2.7.6'
}
```

### res/navigation/nav_graph.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.navcomponent.HomeFragment"
        android:label="Home">
        <action
            android:id="@+id/action_home_to_profile"
            app:destination="@id/profileFragment" />
        <action
            android:id="@+id/action_home_to_settings"
            app:destination="@id/settingsFragment" />
    </fragment>

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.navcomponent.ProfileFragment"
        android:label="Profile" />

    <fragment
        android:id="@+id/settingsFragment"
        android:name="tn.isitcom.navcomponent.SettingsFragment"
        android:label="Settings" />

</navigation>
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### HomeFragment.java (avec navigation)

```java
package tn.isitcom.navcomponent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button btnGoToProfile = view.findViewById(R.id.btn_go_to_profile);
        Button btnGoToSettings = view.findViewById(R.id.btn_go_to_settings);

        btnGoToProfile.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_home_to_profile)
        );

        btnGoToSettings.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_home_to_settings)
        );

        return view;
    }
}
```

### fragment_home.xml (avec boutons)

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
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home Fragment"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_go_to_profile"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aller au Profile"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_go_to_settings"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aller aux Settings" />

</LinearLayout>
```

---

## Exercice 4 : Bottom Navigation

### build.gradle

```gradle
dependencies {
    implementation 'androidx.navigation:navigation-fragment:2.7.6'
    implementation 'androidx.navigation:navigation-ui:2.7.6'
    implementation 'com.google.android.material:material:1.11.0'
}
```

### res/menu/bottom_nav_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/homeFragment"
        android:icon="@android:drawable/ic_menu_home"
        android:title="Home" />
    
    <item
        android:id="@+id/profileFragment"
        android:icon="@android:drawable/ic_menu_myplaces"
        android:title="Profile" />
    
    <item
        android:id="@+id/settingsFragment"
        android:icon="@android:drawable/ic_menu_preferences"
        android:title="Settings" />
</menu>
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:menu="@menu/bottom_nav_menu"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### MainActivity.java

```java
package tn.isitcom.bottomnav;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        
        // Lier Bottom Navigation avec Navigation Component
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}
```

---

## Mini-projet : Application multi-onglets

### Product.java (Entity)

```java
package tn.isitcom.multitabs.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class Product {
    
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String name;
    private double price;
    private boolean isFavorite;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.isFavorite = false;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
```

### ProductDao.java

```java
package tn.isitcom.multitabs.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
import tn.isitcom.multitabs.entity.Product;

@Dao
public interface ProductDao {
    
    @Insert
    void insert(Product product);
    
    @Update
    void update(Product product);
    
    @Query("SELECT * FROM products")
    List<Product> getAll();
    
    @Query("SELECT * FROM products WHERE isFavorite = 1")
    List<Product> getFavorites();
    
    @Query("DELETE FROM products")
    void deleteAll();
}
```

### HomeFragment.java (Liste produits)

```java
package tn.isitcom.multitabs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.multitabs.R;
import tn.isitcom.multitabs.adapter.ProductAdapter;
import tn.isitcom.multitabs.database.AppDatabase;
import tn.isitcom.multitabs.entity.Product;

public class HomeFragment extends Fragment implements ProductAdapter.OnProductListener {

    private AppDatabase database;
    private Executor executor;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        database = AppDatabase.getInstance(requireContext());
        executor = Executors.newSingleThreadExecutor();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        loadProducts();

        return view;
    }

    private void loadProducts() {
        executor.execute(() -> {
            List<Product> products = database.productDao().getAll();
            requireActivity().runOnUiThread(() -> adapter.setProducts(products));
        });
    }

    @Override
    public void onProductClick(Product product) {
        // Naviguer vers détail
        Bundle bundle = new Bundle();
        bundle.putInt("productId", product.getId());
        Navigation.findNavController(requireView())
            .navigate(R.id.action_home_to_detail, bundle);
    }

    @Override
    public void onFavoriteClick(Product product) {
        product.setFavorite(!product.isFavorite());
        executor.execute(() -> {
            database.productDao().update(product);
            requireActivity().runOnUiThread(this::loadProducts);
        });
    }
}
```

### FavoritesFragment.java

```java
package tn.isitcom.multitabs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import tn.isitcom.multitabs.R;
import tn.isitcom.multitabs.adapter.ProductAdapter;
import tn.isitcom.multitabs.database.AppDatabase;
import tn.isitcom.multitabs.entity.Product;

public class FavoritesFragment extends Fragment implements ProductAdapter.OnProductListener {

    private AppDatabase database;
    private Executor executor;
    private ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        database = AppDatabase.getInstance(requireContext());
        executor = Executors.newSingleThreadExecutor();

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        
        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);

        loadFavorites();

        return view;
    }

    private void loadFavorites() {
        executor.execute(() -> {
            List<Product> favorites = database.productDao().getFavorites();
            requireActivity().runOnUiThread(() -> adapter.setProducts(favorites));
        });
    }

    @Override
    public void onProductClick(Product product) {
        // Navigation vers détail
    }

    @Override
    public void onFavoriteClick(Product product) {
        product.setFavorite(false);
        executor.execute(() -> {
            database.productDao().update(product);
            requireActivity().runOnUiThread(this::loadFavorites);
        });
    }
}
```

### ProfileFragment.java

```java
package tn.isitcom.multitabs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import tn.isitcom.multitabs.R;

public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvEmail = view.findViewById(R.id.tv_email);

        tvName.setText("Ahmed Ben Ali");
        tvEmail.setText("ahmed@isitcom.tn");

        return view;
    }
}
```

### res/navigation/nav_graph.xml (complet)

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.multitabs.fragments.HomeFragment"
        android:label="Produits">
        <action
            android:id="@+id/action_home_to_detail"
            app:destination="@id/detailFragment" />
    </fragment>

    <fragment
        android:id="@+id/favoritesFragment"
        android:name="tn.isitcom.multitabs.fragments.FavoritesFragment"
        android:label="Favoris" />

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.multitabs.fragments.ProfileFragment"
        android:label="Profil" />

    <fragment
        android:id="@+id/detailFragment"
        android:name="tn.isitcom.multitabs.fragments.DetailFragment"
        android:label="Détail" />

</navigation>
```

### Grille d'évaluation

| Critère | Points | Évaluation |
|---------|--------|-------------|
| Fragments créés correctement | 4 | HomeFragment, FavoritesFragment, ProfileFragment |
| Navigation Component | 4 | nav_graph.xml, NavController |
| Bottom Navigation | 4 | BottomNavigationView liée |
| RecyclerView + Room | 5 | ProductDao, Adapter, affichage |
| Interface soignée | 3 | Material Design, transitions |
| **Total** | **/20** | |

---

👨‍🏫 **Corrections Module 8** | ISITCOM 2025-2026
