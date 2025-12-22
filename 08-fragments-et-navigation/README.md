# Module 8 : Fragments et Navigation

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Comprendre ce qu'est un Fragment et son cycle de vie.
- Créer et intégrer des Fragments dans une Activity.
- Gérer la navigation entre Fragments avec Navigation Component.
- Implémenter une Bottom Navigation.

---

## 1. Qu'est-ce qu'un Fragment ?

Un **Fragment** est une portion réutilisable d'interface utilisateur. [file:2]

- Peut être ajouté à une Activity.
- Possède son propre cycle de vie.
- Permet de créer des interfaces modulaires.

**Cas d'usage** : [file:2]

- Tablettes : afficher liste + détail côte à côte.
- Navigation : onglets, bottom navigation.
- Réutilisation : même fragment dans plusieurs activities.

---

## 2. Cycle de vie d'un Fragment

Callbacks principaux : [file:2]

- `onAttach()` : attaché à l'Activity.
- `onCreate()` : création du fragment.
- `onCreateView()` : création de l'interface (inflation du layout).
- `onViewCreated()` : après création de la vue.
- `onStart()`, `onResume()`, `onPause()`, `onStop()`
- `onDestroyView()` : destruction de la vue.
- `onDestroy()`, `onDetach()`

---

## 3. Créer un Fragment simple

### 3.1 Classe Fragment

```java
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Initialiser les vues ici
    }
}
```

### 3.2 Layout du Fragment

Créer `res/layout/fragment_home.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Fragment Home"
        android:textSize="24sp" />

</LinearLayout>
```

---

## 4. Afficher un Fragment dans une Activity

### 4.1 Layout de l'Activity

Dans `activity_main.xml`, ajouter un conteneur :

```xml
<FrameLayout
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### 4.2 Ajouter le Fragment

```java
public class MainActivity extends AppCompatActivity {

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
}
```

---

## 5. Navigation Component

### 5.1 Ajouter les dépendances

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    def nav_version = "2.7.6"

    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"
}
```

### 5.2 Créer le graphe de navigation

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
        android:label="Home" />

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.app.ProfileFragment"
        android:label="Profile" />

</navigation>
```

### 5.3 Ajouter NavHostFragment dans l'Activity

```xml
<androidx.fragment.app.FragmentContainerView
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:navGraph="@navigation/nav_graph"
    app:defaultNavHost="true" />
```

### 5.4 Naviguer entre Fragments

```java
Button btnGoProfile = view.findViewById(R.id.btnGoProfile);
btnGoProfile.setOnClickListener(v -> {
    NavController navController = Navigation.findNavController(v);
    navController.navigate(R.id.profileFragment);
});
```

---

## 6. Bottom Navigation

### 6.1 Ajouter Bottom Navigation dans le layout

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:menu="@menu/bottom_menu" />
```

### 6.2 Créer le menu

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
        android:icon="@drawable/ic_profile"
        android:title="Profile" />
</menu>
```

### 6.3 Lier avec Navigation Component

```java
BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
NavigationUI.setupWithNavController(bottomNav, navController);
```

---

## 7. Exercices pratiques (Module 8)

### Exercice 1 – Fragment statique

1. Créer 3 fragments (Home, About, Contact).
2. Ajouter 3 boutons dans l'Activity pour afficher chaque fragment.

### Exercice 2 – Navigation Component

1. Utiliser Navigation Component pour naviguer entre les 3 fragments.
2. Ajouter un bouton "Retour" dans chaque fragment sauf Home.

### Exercice 3 – Bottom Navigation

1. Implémenter une Bottom Navigation avec 3 onglets.
2. Chaque onglet affiche un fragment différent.

---

## 8. Mini-TP : Application multi-onglets

### Consignes

Créer une application avec Bottom Navigation et 3 onglets :

1. **Home** : afficher une liste de produits (RecyclerView).
2. **Favorites** : afficher les produits favoris.
3. **Profile** : afficher les informations utilisateur.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Fragments créés correctement | 4 |
| Navigation Component | 4 |
| Bottom Navigation fonctionnelle | 4 |
| RecyclerView dans Home | 4 |
| Interface soignée | 4 |

**Total** : /20

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
