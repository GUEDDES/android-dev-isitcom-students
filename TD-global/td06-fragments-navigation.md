# TD06 : Fragments et Navigation Component

## 🎯 Objectifs

- Maîtriser les Fragments.
- Utiliser Navigation Component.
- Implémenter Bottom Navigation.

---

## Partie 1 : Application multi-onglets (60 min)

### Consignes

Créer une application "My Shop" avec 3 onglets :

1. **HomeFragment** : Liste de produits (RecyclerView).
2. **CartFragment** : Panier (liste des produits ajoutés).
3. **ProfileFragment** : Informations utilisateur.

### Étape 1 : Configuration

Ajouter les dépendances :

```gradle
dependencies {
    def nav_version = "2.7.6"
    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"
}
```

### Étape 2 : Créer les Fragments

**HomeFragment.java** :

```java
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        List<Product> products = getProducts();
        ProductAdapter adapter = new ProductAdapter(products, this::addToCart);
        recyclerView.setAdapter(adapter);
    }

    private void addToCart(Product product) {
        // Logique ajout au panier
        Toast.makeText(getContext(), product.getName() + " ajouté", Toast.LENGTH_SHORT).show();
    }
}
```

### Étape 3 : Navigation Graph

Créer `res/navigation/nav_graph.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.myshop.HomeFragment"
        android:label="Accueil" />

    <fragment
        android:id="@+id/cartFragment"
        android:name="tn.isitcom.myshop.CartFragment"
        android:label="Panier" />

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.myshop.ProfileFragment"
        android:label="Profil" />

</navigation>
```

### Étape 4 : Bottom Navigation

**activity_main.xml** :

```xml
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
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:navGraph="@navigation/nav_graph"
        app:defaultNavHost="true" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintBottom_toBottomOf="parent"
        app:menu="@menu/bottom_menu" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**MainActivity.java** :

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}
```

---

## Partie 2 : Panier partagé (30 min)

### Consignes

1. Créer une classe `CartManager` (singleton) pour gérer le panier.
2. Méthodes : `addProduct`, `removeProduct`, `getProducts`, `getTotalPrice`.
3. Afficher le panier dans `CartFragment`.
4. Afficher le nombre d'articles dans le badge de Bottom Navigation.

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Fragments créés | 4 |
| Navigation Component | 5 |
| Bottom Navigation | 5 |
| Panier fonctionnel | 6 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
