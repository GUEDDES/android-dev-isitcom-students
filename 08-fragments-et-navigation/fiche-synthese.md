# Module 8 : Fiche de synthèse

## 🎯 Concepts clés

**Fragment** = Portion réutilisable d'interface dans une Activity  
**Avantages** : Modularité, réutilisation, navigation fluide

---

## 📦 Architecture

```
Activity (conteneur)
  │
  ├── Fragment A
  ├── Fragment B
  └── Fragment C
```

---

## 🛠️ Créer un Fragment

### 1. Classe Fragment

```java
public class HomeFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflater le layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        // Initialiser vues
        TextView text = view.findViewById(R.id.textTitle);
        text.setText("Accueil");
        
        return view;
    }
}
```

### 2. Layout fragment_home.xml

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    
    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home" />
        
</LinearLayout>
```

---

## 🔄 Navigation Component

### 1. Dépendances (build.gradle)

```gradle
dependencies {
    implementation 'androidx.navigation:navigation-fragment:2.7.6'
    implementation 'androidx.navigation:navigation-ui:2.7.6'
}
```

### 2. Graphe de navigation (nav_graph.xml)

```xml
<navigation
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">
    
    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.app.HomeFragment"
        android:label="Accueil" />
    
    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.app.ProfileFragment"
        android:label="Profil" />
        
</navigation>
```

### 3. Activity avec NavHost

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
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottomNav"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
    
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottomNav"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:menu="@menu/bottom_menu"
        app:layout_constraintBottom_toBottomOf="parent" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 📱 Bottom Navigation

### 1. Menu (res/menu/bottom_menu.xml)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    
    <item
        android:id="@id/homeFragment"
        android:icon="@drawable/ic_home"
        android:title="Accueil" />
    
    <item
        android:id="@id/profileFragment"
        android:icon="@drawable/ic_person"
        android:title="Profil" />
        
</menu>
```

### 2. Configuration dans Activity

```java
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        
        NavigationUI.setupWithNavController(bottomNav, navController);
    }
}
```

---

## 🔗 Navigation programmatique

### Dans un Fragment

```java
// Navigation simple
NavController navController = Navigation.findNavController(view);
navController.navigate(R.id.profileFragment);

// Avec arguments
Bundle args = new Bundle();
args.putString("USER_NAME", "Alice");
navController.navigate(R.id.profileFragment, args);
```

### Récupérer arguments

```java
@Override
public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    if (getArguments() != null) {
        String userName = getArguments().getString("USER_NAME");
        TextView text = view.findViewById(R.id.textName);
        text.setText(userName);
    }
}
```

---

## 🔄 Cycle de vie Fragment

```
onAttach() → onCreate() → onCreateView() → onViewCreated() → onStart() → onResume()
                                                                    ↓
onDetach() ← onDestroy() ← onDestroyView() ← onStop() ← onPause()
```

### Méthodes importantes

```java
@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Initialisation données
}

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Créer la vue
    return inflater.inflate(R.layout.fragment_home, container, false);
}

@Override
public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    // Initialiser vues (findViewById, listeners...)
}
```

---

## ⚠️ Différences Activity vs Fragment

| Activity | Fragment |
|----------|----------|
| setContentView() | inflate() dans onCreateView |
| findViewById() | view.findViewById() |
| this | getContext() / requireContext() |
| startActivity() | navController.navigate() |

---

## 🔑 Mémo rapide

```java
// Créer Fragment
public class MonFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mon, container, false);
    }
}

// Naviguer
Navigation.findNavController(view).navigate(R.id.destination);

// Passer données
Bundle args = new Bundle();
args.putString("KEY", "value");
navController.navigate(R.id.dest, args);

// Récupérer
String value = getArguments().getString("KEY");
```

---

## 📝 Checklist

✅ Ajouter dépendances Navigation  
✅ Créer nav_graph.xml avec destinations  
✅ Ajouter NavHostFragment dans layout Activity  
✅ Configurer Bottom Navigation avec menu  
✅ Lier BottomNav et NavController  
✅ Utiliser view.findViewById() dans Fragments  

---

👨‍🏫 **Module 8 - Fragments et Navigation** | ISITCOM 2025-2026
