# Module 9 : Ergonomie et Material Design

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Appliquer les principes du Material Design dans vos applications.
- Créer et personnaliser des thèmes (couleurs, styles).
- Implémenter le mode sombre (Dark Mode).
- Utiliser les composants Material (FAB, Snackbar, Card, Toolbar).

---

## 1. Qu'est-ce que Material Design ?

Material Design est le langage de design créé par Google pour Android : [file:2]

- **Principes** : profondeur, mouvement, signification.
- **Composants** : boutons, cartes, dialogs standardisés.
- **Accessibilité** : couleurs contrastées, tailles de texte adaptées.

**Documentation officielle** : <https://m3.material.io/>

---

## 2. Ajouter Material Components

### 2.1 Dépendance

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    implementation 'com.google.android.material:material:1.11.0'
}
```

### 2.2 Thème Material

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.DayNight.NoActionBar">
        <item name="colorPrimary">@color/blue_500</item>
        <item name="colorPrimaryVariant">@color/blue_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/orange_500</item>
        <item name="colorOnSecondary">@color/black</item>
    </style>
</resources>
```

Dans `res/values/colors.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="blue_500">#2196F3</color>
    <color name="blue_700">#1976D2</color>
    <color name="orange_500">#FF9800</color>
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
</resources>
```

---

## 3. Composants Material essentiels

### 3.1 Floating Action Button (FAB)

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

Gestion du clic :

```java
FloatingActionButton fab = findViewById(R.id.fab);
fab.setOnClickListener(v -> {
    // Action
});
```

### 3.2 Snackbar

Remplace le Toast pour un feedback plus moderne :

```java
Snackbar.make(findViewById(android.R.id.content), "Élément supprimé", Snackbar.LENGTH_LONG)
    .setAction("Annuler", v -> {
        // Action d'annulation
    })
    .show();
```

### 3.3 CardView

Pour créer des cartes visuelles :

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
            android:text="Description de la carte"
            android:layout_marginTop="8dp" />

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

### 3.4 Toolbar Material

```xml
<com.google.android.material.appbar.MaterialToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    android:elevation="4dp"
    app:title="Mon Application"
    app:titleTextColor="@color/white" />
```

Dans l'Activity :

```java
MaterialToolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
```

### 3.5 TextInputLayout

Pour des champs de saisie élégants :

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:startIconDrawable="@drawable/ic_email">

    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />

</com.google.android.material.textfield.TextInputLayout>
```

---

## 4. Dark Mode (Mode sombre)

### 4.1 Création du thème sombre

Créer `res/values-night/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.DayNight.NoActionBar">
        <item name="colorPrimary">@color/blue_200</item>
        <item name="colorPrimaryVariant">@color/blue_700</item>
        <item name="colorOnPrimary">@color/black</item>
        <item name="android:colorBackground">#121212</item>
    </style>
</resources>
```

Créer `res/values-night/colors.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="blue_200">#90CAF9</color>
</resources>
```

### 4.2 Forcer un mode

Dans l'Activity :

```java
import androidx.appcompat.app.AppCompatDelegate;

// Forcer le mode clair
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

// Forcer le mode sombre
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// Suivre les paramètres système
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
```

### 4.3 Toggle Dark Mode

```java
Switch switchDarkMode = findViewById(R.id.switchDarkMode);
switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
    if (isChecked) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
});
```

---

## 5. Styles personnalisés

### 5.1 Créer un style de bouton

Dans `res/values/styles.xml` :

```xml
<resources>
    <style name="ButtonPrimary" parent="Widget.Material3.Button">
        <item name="android:textColor">@color/white</item>
        <item name="backgroundTint">@color/blue_500</item>
        <item name="cornerRadius">8dp</item>
    </style>
</resources>
```

Utilisation :

```xml
<Button
    style="@style/ButtonPrimary"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Valider" />
```

### 5.2 Style de TextView

```xml
<style name="TitleText">
    <item name="android:textSize">24sp</item>
    <item name="android:textStyle">bold</item>
    <item name="android:textColor">?attr/colorPrimary</item>
</style>
```

---

## 6. Exercices pratiques (Module 9)

### Exercice 1 – Moderniser HelloWorld

1. Appliquer un thème Material 3 à l'application HelloWorld.
2. Remplacer le bouton standard par un bouton Material.
3. Ajouter un FAB qui affiche un Snackbar.

### Exercice 2 – Liste avec CardView

1. Reprendre l'exercice RecyclerView.
2. Encapsuler chaque élément dans une MaterialCardView.
3. Ajouter une ombre et des coins arrondis.

### Exercice 3 – Dark Mode complet

1. Créer un thème sombre pour votre application.
2. Ajouter un switch dans les paramètres pour basculer.
3. Tester sur plusieurs écrans.

### Exercice 4 – Formulaire élégant

1. Créer un formulaire d'inscription avec TextInputLayout.
2. Ajouter des icônes (email, mot de passe, téléphone).
3. Valider les champs et afficher des erreurs avec `setError()`.

---

## 7. Mini-TP : Application avec Material Design complet

### Consignes

Créer une application "Bibliothèque" avec :

1. **Écran principal** :
   - Toolbar Material avec titre.
   - RecyclerView avec CardView (liste de livres).
   - FAB pour ajouter un livre.

2. **Écran ajout/modification** :
   - TextInputLayout pour titre, auteur, année.
   - Boutons Material (Enregistrer, Annuler).

3. **Dark Mode** :
   - Support complet du mode sombre.
   - Switch dans un écran Paramètres.

4. **Interactions** :
   - Snackbar lors de l'ajout/suppression.
   - Dialog Material pour confirmation de suppression.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Thème Material appliqué | 3 |
| Composants Material utilisés | 4 |
| Dark Mode fonctionnel | 4 |
| CardView dans RecyclerView | 3 |
| TextInputLayout dans formulaire | 3 |
| Interface cohérente et soignée | 3 |

**Total** : /20

---

## 8. Ressources utiles

- **Material Design 3** : <https://m3.material.io/>
- **Material Components Android** : <https://github.com/material-components/material-components-android>
- **Color Tool** : <https://material.io/resources/color/>
- **Icons** : <https://fonts.google.com/icons>

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
