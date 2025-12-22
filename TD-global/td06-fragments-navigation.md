# TD06 : Fragments et Navigation

## 🎯 Objectifs

- Créer et gérer des Fragments.
- Utiliser Navigation Component.
- Implémenter Bottom Navigation.

---

## Partie 1 : Fragments de base (45 min)

### Exercice 1.1 : Créer un Fragment

Créer `HomeFragment.java` :

```java
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.*;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        TextView textTitle = view.findViewById(R.id.textTitle);
        textTitle.setText("Fragment Home");
    }
}
```

Créer `fragment_home.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Home"
        android:textSize="24sp"
        android:textStyle="bold" />

</LinearLayout>
```

### Exercice 1.2 : Afficher le Fragment

Dans `MainActivity` :

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, new HomeFragment())
            .commit();
    }
}
```

Dans `activity_main.xml` :

```xml
<FrameLayout
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

---

## Partie 2 : Navigation Component (60 min)

### Exercice 2.1 : Dépendances

```gradle
dependencies {
    def nav_version = "2.7.6"

    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"
}
```

### Exercice 2.2 : Créer 3 Fragments

- `HomeFragment`
- `ProfileFragment`
- `SettingsFragment`

### Exercice 2.3 : Graphe de navigation

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
        android:name="tn.isitcom.app.HomeFragment"
        android:label="Home">
        <action
            android:id="@+id/action_home_to_profile"
            app:destination="@id/profileFragment" />
    </fragment>

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.app.ProfileFragment"
        android:label="Profile" />

    <fragment
        android:id="@+id/settingsFragment"
        android:name="tn.isitcom.app.SettingsFragment"
        android:label="Settings" />

</navigation>
```

### Exercice 2.4 : NavHostFragment

Dans `activity_main.xml` :

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:navGraph="@navigation/nav_graph"
    app:defaultNavHost="true" />
```

### Exercice 2.5 : Navigation entre Fragments

Dans `HomeFragment` :

```java
Button btnGoProfile = view.findViewById(R.id.btnGoProfile);
btnGoProfile.setOnClickListener(v -> {
    NavController navController = Navigation.findNavController(v);
    navController.navigate(R.id.action_home_to_profile);
});
```

---

## Partie 3 : Bottom Navigation (45 min)

### Exercice 3.1 : Bottom Navigation View

Dans `activity_main.xml` :

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
        app:navGraph="@navigation/nav_graph"
        app:defaultNavHost="true"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:menu="@menu/bottom_menu"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Exercice 3.2 : Menu

Créer `res/menu/bottom_menu.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/ic_home"
        android:title="Home" />
    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/ic_person"
        android:title="Profile" />
    <item
        android:id="@+id/settingsFragment"
        android:icon="@drawable/ic_settings"
        android:title="Settings" />
</menu>
```

### Exercice 3.3 : Lier avec Navigation

Dans `MainActivity` :

```java
BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
NavigationUI.setupWithNavController(bottomNav, navController);
```

---

## 🎯 TP Noté : Application multi-onglets (/20)

### Consignes

Créer une application "ISITCOM Hub" avec 3 onglets :

1. **Accueil** :
   - Titre de bienvenue.
   - Liste de 3 actualités (RecyclerView simple).
   - Clic sur une actualité → affiche détail dans un nouveau fragment.

2. **Cours** :
   - Liste de cours (nom, enseignant, salle).
   - Clic → affiche détail du cours.

3. **Profil** :
   - Nom, prénom, groupe.
   - Bouton "Modifier" → formulaire de modification.
   - Bouton "Déconnexion" → retour accueil.

### Contraintes

- Navigation Component obligatoire.
- Bottom Navigation.
- Au moins 5 fragments différents.
- Material Design.

### Barème

| Critère | Points |
|---------|--------|
| 5 Fragments créés | 3 |
| Navigation Component | 4 |
| Bottom Navigation | 4 |
| RecyclerView dans Accueil | 3 |
| Navigation vers détails | 3 |
| Interface cohérente | 3 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
