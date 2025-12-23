# Module 9 : Ergonomie et Material Design

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Appliquer les principes du Material Design.
- Créer des interfaces modernes et cohérentes.
- Implémenter le Dark Mode.
- Utiliser les composants Material (FAB, Snackbar, Cards, etc.).
- Personnaliser les thèmes et couleurs.

---

## 1. Qu'est-ce que Material Design ?

**Material Design** est le langage de design développé par Google pour Android. [file:2]

### Principes clés

- **Hiérarchie visuelle** : importance des éléments claire.
- **Élévation et ombres** : profondeur (cartes, FAB).
- **Animations fluides** : transitions naturelles.
- **Typographie claire** : polices Roboto.
- **Couleurs harmonieuses** : palettes cohérentes.

### Ressources officielles

- Documentation : <https://m3.material.io/>
- Composants Android : <https://material.io/develop/android>

---

## 2. Configuration Material Components

### 2.1 Ajouter la dépendance

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    implementation 'com.google.android.material:material:1.11.0'
}
```

### 2.2 Utiliser un thème Material

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.DayNight">
        <item name="colorPrimary">@color/blue_500</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/orange_500</item>
    </style>
</resources>
```

---

## 3. Composants Material essentiels

### 3.1 FloatingActionButton (FAB)

Bouton d'action principal flottant.

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/ic_add"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_margin="16dp" />
```

```java
FloatingActionButton fab = findViewById(R.id.fab);
fab.setOnClickListener(v -> {
    // Action
});
```

### 3.2 Snackbar

Notification temporaire en bas de l'écran (remplace Toast).

```java
Snackbar.make(view, "Élément supprimé", Snackbar.LENGTH_LONG)
    .setAction("Annuler", v -> {
        // Action d'annulation
    })
    .show();
```

### 3.3 CardView

Carte avec élévation pour regrouper du contenu.

```xml
<com.google.android.material.card.MaterialCardView
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
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Titre de la carte"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Description de la carte" />

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

### 3.4 TextInputLayout

Champ de texte avec label flottant.

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/editEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />

</com.google.android.material.textfield.TextInputLayout>
```

### 3.5 BottomAppBar

Barre inférieure avec actions.

```xml
<com.google.android.material.bottomappbar.BottomAppBar
    android:id="@+id/bottomAppBar"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom"
    app:menu="@menu/bottom_app_bar_menu" />
```

---

## 4. Thèmes et couleurs

### 4.1 Définir les couleurs

Dans `res/values/colors.xml` :

```xml
<resources>
    <color name="blue_500">#2196F3</color>
    <color name="blue_700">#1976D2</color>
    <color name="orange_500">#FF9800</color>
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    <color name="gray_200">#EEEEEE</color>
</resources>
```

### 4.2 Personnaliser le thème

```xml
<style name="AppTheme" parent="Theme.Material3.DayNight">
    <item name="colorPrimary">@color/blue_500</item>
    <item name="colorPrimaryVariant">@color/blue_700</item>
    <item name="colorOnPrimary">@color/white</item>
    <item name="colorSecondary">@color/orange_500</item>
    <item name="colorOnSecondary">@color/black</item>
    <item name="android:statusBarColor">@color/blue_700</item>
</style>
```

---

## 5. Dark Mode (Mode sombre)

### 5.1 Créer le thème sombre

Créer `res/values-night/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.DayNight">
        <item name="colorPrimary">@color/blue_200</item>
        <item name="colorOnPrimary">@color/black</item>
        <item name="colorSecondary">@color/orange_200</item>
        <item name="android:statusBarColor">@color/black</item>
    </style>
</resources>
```

Dans `colors.xml`, ajouter :

```xml
<color name="blue_200">#90CAF9</color>
<color name="orange_200">#FFCC80</color>
```

### 5.2 Changer le mode par programmation

```java
import androidx.appcompat.app.AppCompatDelegate;

// Mode clair
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

// Mode sombre
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// Suivre les paramètres système
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
```

---

## 6. Animations et transitions

### 6.1 Animation de clic

```xml
<Button
    android:id="@+id/btnAnimate"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Cliquez"
    android:background="?attr/selectableItemBackground" />
```

### 6.2 Transition entre Activities

```java
Intent intent = new Intent(this, DetailActivity.class);
ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
startActivity(intent, options.toBundle());
```

---

## 7. Exercices pratiques (Module 9)

### Exercice 1 – Composants Material

1. Créer un écran avec :
   - Un FAB.
   - Deux CardView.
   - Un TextInputLayout.
2. Au clic sur le FAB, afficher un Snackbar.

### Exercice 2 – Thème personnalisé

1. Créer un thème avec vos propres couleurs.
2. Appliquer ce thème à votre application.
3. Vérifier que tous les composants utilisent les bonnes couleurs.

### Exercice 3 – Dark Mode

1. Implémenter le Dark Mode.
2. Ajouter un switch dans les paramètres pour basculer entre clair/sombre.
3. Sauvegarder la préférence (SharedPreferences).

---

## 8. 🎨 Projet exemple : Application de galerie moderne

### Description

Créer une application de galerie d'images avec interface Material Design complète.

### Fonctionnalités

1. **Écran principal** :
   - RecyclerView en grille avec CardView.
   - Chaque carte affiche une image + titre.
   - FAB pour ajouter une image.

2. **Écran détail** :
   - Image en plein écran.
   - Titre et description.
   - Bouton partager (Snackbar de confirmation).

3. **Paramètres** :
   - Switch Dark Mode.
   - Choix du nombre de colonnes (2/3/4).

### Structure du projet

```
app/
├── java/tn/isitcom/gallery/
│   ├── MainActivity.java
│   ├── DetailActivity.java
│   ├── SettingsActivity.java
│   ├── adapter/
│   │   └── ImageAdapter.java
│   └── model/
│       └── Image.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── activity_detail.xml
│   │   ├── activity_settings.xml
│   │   └── item_image.xml
│   ├── values/
│   │   ├── themes.xml
│   │   └── colors.xml
│   └── values-night/
│       └── themes.xml
```

### Code exemple : MainActivity.java

```java
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private List<Image> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser la liste
        imageList = new ArrayList<>();
        imageList.add(new Image("Coucher de soleil", R.drawable.sunset));
        imageList.add(new Image("Montagne", R.drawable.mountain));
        imageList.add(new Image("Plage", R.drawable.beach));

        // Configurer RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ImageAdapter(imageList, this::openDetail);
        recyclerView.setAdapter(adapter);

        // FAB pour ajouter
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Snackbar.make(v, "Fonctionnalité à venir", Snackbar.LENGTH_SHORT).show();
        });
    }

    private void openDetail(Image image) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("IMAGE_TITLE", image.getTitle());
        intent.putExtra("IMAGE_RES", image.getResourceId());
        startActivity(intent);
    }
}
```

### Code exemple : item_image.xml

```xml
<com.google.android.material.card.MaterialCardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="200dp"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="12dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <ImageView
            android:id="@+id/imageView"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:scaleType="centerCrop" />

        <TextView
            android:id="@+id/textTitle"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="8dp"
            android:textSize="14sp"
            android:textStyle="bold"
            android:gravity="center" />

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

---

## 9. Mini-TP : Application de blog moderne

### Consignes

Créer une application de lecture d'articles avec :

1. **Liste d'articles** (RecyclerView + CardView).
2. **Détail d'article** (image + titre + contenu).
3. **FAB** pour ajouter un article aux favoris.
4. **Dark Mode** fonctionnel.
5. **BottomNavigationView** (Accueil, Favoris, Profil).

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Utilisation de Material Components | 5 |
| Interface soignée et cohérente | 5 |
| Dark Mode fonctionnel | 4 |
| Navigation fluide | 4 |
| Code propre | 2 |

**Total** : /20

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
