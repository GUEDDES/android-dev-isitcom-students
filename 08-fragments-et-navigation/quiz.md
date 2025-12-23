# Module 8 : Quiz d'auto-évaluation - Fragments et Navigation

## Questions à choix multiples

### Question 1
Qu'est-ce qu'un Fragment ?

A) Un écran complet  
B) Une portion réutilisable d'interface dans une Activity  
C) Un layout XML  
D) Une base de données

<details>
<summary>Réponse</summary>
B) Une portion réutilisable d'interface utilisateur dans une Activity
</details>

---

### Question 2
Quelle méthode d'un Fragment crée la vue ?

A) onCreate()  
B) onCreateView()  
C) createView()  
D) buildView()

<details>
<summary>Réponse</summary>
B) onCreateView() - retourne la vue inflatee
</details>

---

### Question 3
Comment inflater un layout dans un Fragment ?

A) setContentView(R.layout.fragment)  
B) inflate(R.layout.fragment)  
C) inflater.inflate(R.layout.fragment, container, false)  
D) getView(R.layout.fragment)

<details>
<summary>Réponse</summary>
C) inflater.inflate(R.layout.fragment, container, false)
</details>

---

### Question 4
Qu'est-ce que Navigation Component ?

A) Un GPS  
B) Une bibliothèque pour gérer la navigation entre fragments  
C) Un layout  
D) Un widget

<details>
<summary>Réponse</summary>
B) Bibliothèque Android Jetpack pour simplifier la navigation
</details>

---

### Question 5
Où définir le graphe de navigation ?

A) AndroidManifest.xml  
B) build.gradle  
C) res/navigation/nav_graph.xml  
D) strings.xml

<details>
<summary>Réponse</summary>
C) Dans res/navigation/nav_graph.xml
</details>

---

### Question 6
Comment naviguer vers un fragment programmatiquement ?

A) startActivity()  
B) navController.navigate(R.id.fragment)  
C) openFragment()  
D) switchTo()

<details>
<summary>Réponse</summary>
B) navController.navigate(R.id.destinationFragment)
</details>

---

### Question 7
Quel composant héberge les fragments dans l'Activity ?

A) FrameLayout  
B) FragmentContainer  
C) NavHostFragment  
D) FragmentHolder

<details>
<summary>Réponse</summary>
C) NavHostFragment (FragmentContainerView)
</details>

---

### Question 8
Comment passer des données entre fragments ?

A) Intent  
B) Bundle avec arguments  
C) Static variables  
D) Fichier

<details>
<summary>Réponse</summary>
B) Bundle passé dans navigate() ou setArguments()
</details>

---

### Question 9
Quelle différence entre Activity et Fragment ?

A) Aucune  
B) Fragment vit dans une Activity  
C) Activity est plus petite  
D) Fragment ne peut pas avoir de layout

<details>
<summary>Réponse</summary>
B) Un Fragment doit être hébergé dans une Activity
</details>

---

### Question 10
Que fait `app:startDestination` dans nav_graph ?

A) Définit l'écran de fin  
B) Définit le fragment de démarrage  
C) Arrête la navigation  
D) Crée une Activity

<details>
<summary>Réponse</summary>
B) Définit quel fragment s'affiche en premier
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
public class HomeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
```

A) Crée une Activity  
B) Crée un Fragment et inflate son layout  
C) Affiche un Toast  
D) Lance une animation

<details>
<summary>Réponse</summary>
B) Définit un Fragment et inflate fragment_home.xml
</details>

---

### Question 12
Comment obtenir le NavController dans un Fragment ?

```java
NavController navController = ___________;
```

A) getNavController()  
B) Navigation.findNavController(view)  
C) new NavController()  
D) this.navController

<details>
<summary>Réponse</summary>
B) Navigation.findNavController(view)
</details>

---

### Question 13
Quel est le problème dans ce code ?

```java
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    TextView text = findViewById(R.id.textTitle);
    return view;
}
```

A) Pas d'erreur  
B) Doit utiliser view.findViewById()  
C) inflater incorrect  
D) Manque super

<details>
<summary>Réponse</summary>
B) Dans un Fragment, utiliser view.findViewById() pas findViewById() directement
</details>

---

### Question 14
Comment passer un String "username" = "Alice" au fragment suivant ?

<details>
<summary>Réponse</summary>

```java
Bundle bundle = new Bundle();
bundle.putString("username", "Alice");
navController.navigate(R.id.profileFragment, bundle);

// Réception dans ProfileFragment
@Override
public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    if (getArguments() != null) {
        String username = getArguments().getString("username");
    }
}
```
</details>

---

## Questions sur Bottom Navigation

### Question 15
Comment lier BottomNavigationView avec Navigation Component ?

<details>
<summary>Réponse</summary>

```java
BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
NavigationUI.setupWithNavController(bottomNav, navController);
```

Important : Les IDs du menu doivent correspondre aux IDs des fragments dans nav_graph.xml
</details>

---

### Question 16
Créez un menu bottom_menu.xml avec 3 items : Home, Search, Profile.

<details>
<summary>Réponse</summary>

```xml
<!-- res/menu/bottom_menu.xml -->
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    
    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/ic_home"
        android:title="@string/home" />
    
    <item
        android:id="@+id/searchFragment"
        android:icon="@drawable/ic_search"
        android:title="@string/search" />
    
    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/ic_person"
        android:title="@string/profile" />
        
</menu>
```
</details>

---

## Questions pratiques

### Question 17
Créez un Fragment basique "DetailFragment" qui affiche un TextView.

<details>
<summary>Réponse</summary>

```java
public class DetailFragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        TextView textView = view.findViewById(R.id.textDetail);
        textView.setText("Détails");
    }
}
```

```xml
<!-- fragment_detail.xml -->
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center">
    
    <TextView
        android:id="@+id/textDetail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp" />
        
</LinearLayout>
```
</details>

---

### Question 18
Créez un nav_graph.xml avec 2 fragments : Home et Detail.

<details>
<summary>Réponse</summary>

```xml
<!-- res/navigation/nav_graph.xml -->
<navigation
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">
    
    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.myapp.HomeFragment"
        android:label="Accueil">
        
        <action
            android:id="@+id/action_home_to_detail"
            app:destination="@id/detailFragment" />
    </fragment>
    
    <fragment
        android:id="@+id/detailFragment"
        android:name="tn.isitcom.myapp.DetailFragment"
        android:label="Détail" />
        
</navigation>
```
</details>

---

### Question 19
Dans HomeFragment, ajoutez un bouton qui navigue vers DetailFragment.

<details>
<summary>Réponse</summary>

```java
public class HomeFragment extends Fragment {
    
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        Button btnDetail = view.findViewById(R.id.btnDetail);
        btnDetail.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_home_to_detail);
        });
    }
}
```
</details>

---

### Question 20
Quelle est la différence entre `this` et `requireContext()` dans un Fragment ?

<details>
<summary>Réponse</summary>

- `this` dans un Fragment référence le Fragment lui-même (pas un Context)
- `requireContext()` retourne le Context de l'Activity qui héberge le Fragment
- `getContext()` peut retourner null si le Fragment n'est pas attaché
- `requireContext()` lance une exception si pas de Context (plus sûr)

```java
// Dans un Fragment
Toast.makeText(requireContext(), "Message", Toast.LENGTH_SHORT).show();
// Pas : Toast.makeText(this, ...)
```
</details>

---

## Barème

- **18-20/20** : Excellent ! Maîtrise complète des Fragments
- **15-17/20** : Très bien
- **12-14/20** : Bien, revoir certains concepts
- **< 12/20** : Revoir le module

---

## Points clés à retenir

✅ **Fragment** = Portion réutilisable d'UI dans Activity  
✅ **onCreateView()** = Inflater le layout  
✅ **onViewCreated()** = Initialiser les vues  
✅ **view.findViewById()** = Pas findViewById() directement  
✅ **Navigation Component** = Simplifier navigation entre fragments  
✅ **Bundle** = Passer données entre fragments  
✅ **requireContext()** = Obtenir Context dans Fragment  

---

👨‍🏫 **Module 8 - Fragments et Navigation** | ISITCOM 2025-2026
