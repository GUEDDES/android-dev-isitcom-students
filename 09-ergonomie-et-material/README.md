# Module 9 : Ergonomie et Material Design

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Appliquer les principes du Material Design.
- Utiliser les composants Material (CardView, FAB, Snackbar...).
- Créer des thèmes personnalisés (couleurs, typographie).
- Implémenter le Dark Mode.

---

## 1. Qu'est-ce que Material Design ?

**Material Design** est le langage de design de Google pour Android. [file:2]

### Principes clés

- **Matérialité** : interfaces inspirées du papier et de l'encre.
- **Mouvement intentionnel** : animations significatives.
- **Typographie audacieuse** : hiérarchie claire.
- **Couleurs expressives** : palettes harmonieuses.

Ressource officielle : <https://m3.material.io/>

---

## 2. Composants Material essentiels

### 2.1 CardView

Afficher du contenu dans une carte avec ombre.

**Dépendance** :

```gradle
implementation 'androidx.cardview:cardview:1.0.0'
```

**Exemple XML** :

```xml
<androidx.cardview.widget.CardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="16dp"
    app:cardCornerRadius="8dp"
    app:cardElevation="4dp">

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
            android:text="Contenu de la carte"
            android:layout_marginTop="8dp" />

    </LinearLayout>

</androidx.cardview.widget.CardView>
```

### 2.2 FloatingActionButton (FAB)

Bouton circulaire flottant pour action principale.

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|end"
    android:layout_margin="16dp"
    android:src="@drawable/ic_add"
    app:tint="@android:color/white" />
```

### 2.3 Snackbar

Message temporaire en bas de l'écran (remplace Toast).

```java
Snackbar.make(view, "Message de confirmation", Snackbar.LENGTH_LONG)
    .setAction("Annuler", v -> {
        // Action au clic
    })
    .show();
```

### 2.4 TextInputLayout

Champ de saisie avec label flottant et validation.

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:errorEnabled="true">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/editEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />

</com.google.android.material.textfield.TextInputLayout>
```

**Validation en Java** :

```java
TextInputLayout emailLayout = findViewById(R.id.emailLayout);
TextInputEditText editEmail = findViewById(R.id.editEmail);

if (editEmail.getText().toString().isEmpty()) {
    emailLayout.setError("Email requis");
} else {
    emailLayout.setError(null);
}
```

---

## 3. Thèmes et styles

### 3.1 Définir un thème personnalisé

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="Theme.MyApp" parent="Theme.Material3.Light.NoActionBar">
        <!-- Couleur primaire -->
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@android:color/white</item>

        <!-- Couleur secondaire -->
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@android:color/black</item>

        <!-- Couleurs de surface -->
        <item name="android:colorBackground">@color/white</item>
        <item name="colorSurface">@color/white</item>
    </style>
</resources>
```

### 3.2 Définir les couleurs

Dans `res/values/colors.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_500">#6200EE</color>
    <color name="purple_700">#3700B3</color>
    <color name="teal_200">#03DAC5</color>
    <color name="teal_700">#018786</color>
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
</resources>
```

### 3.3 Appliquer le thème

Dans `AndroidManifest.xml` :

```xml
<application
    android:theme="@style/Theme.MyApp">
```

---

## 4. Dark Mode

### 4.1 Créer le thème sombre

Créer `res/values-night/themes.xml` :

```xml
<resources>
    <style name="Theme.MyApp" parent="Theme.Material3.Dark.NoActionBar">
        <item name="colorPrimary">@color/purple_200</item>
        <item name="android:colorBackground">@color/dark_background</item>
    </style>
</resources>
```

Dans `res/values-night/colors.xml` :

```xml
<resources>
    <color name="purple_200">#BB86FC</color>
    <color name="dark_background">#121212</color>
</resources>
```

### 4.2 Forcer le mode

```java
// Forcer le mode clair
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

// Forcer le mode sombre
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// Suivre le système
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

## 5. Typographie Material

Définir des styles de texte dans `res/values/styles.xml` :

```xml
<style name="TextAppearance.MyApp.Headline1" parent="TextAppearance.Material3.HeadlineLarge">
    <item name="android:textSize">32sp</item>
    <item name="android:textStyle">bold</item>
</style>

<style name="TextAppearance.MyApp.Body1" parent="TextAppearance.Material3.BodyMedium">
    <item name="android:textSize">16sp</item>
</style>
```

Utilisation :

```xml
<TextView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Titre principal"
    android:textAppearance="@style/TextAppearance.MyApp.Headline1" />
```

---

## 6. Toolbar Material

### 6.1 Ajouter une Toolbar

Dans le layout :

```xml
<com.google.android.material.appbar.MaterialToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    android:elevation="4dp"
    app:title="Mon Application"
    app:titleTextColor="@android:color/white" />
```

### 6.2 Configurer dans l'Activity

```java
MaterialToolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
```

---

## 7. Exercices pratiques (Module 9)

### Exercice 1 – Composants Material

1. Créer un écran avec 3 CardView.
2. Ajouter un FAB pour ajouter une nouvelle carte.
3. Afficher un Snackbar à l'ajout.

### Exercice 2 – Thème personnalisé

1. Créer un thème avec couleurs ISITCOM (bleu/orange).
2. Appliquer le thème à toute l'application.
3. Vérifier que tous les boutons et textes respectent les couleurs.

### Exercice 3 – Dark Mode

1. Implémenter le Dark Mode complet.
2. Ajouter un Switch dans les paramètres pour basculer.
3. Tester le changement dynamique.

---

## 8. Mini-TP : Application avec Material Design complet

### Consignes

Créer une application "Contacts Material" :

1. **Liste** : RecyclerView avec CardView pour chaque contact.
2. **FAB** : ajouter un nouveau contact.
3. **Formulaire** : TextInputLayout pour nom et téléphone.
4. **Snackbar** : confirmation ajout/suppression.
5. **Dark Mode** : toggle dans les paramètres.
6. **Toolbar** : avec titre et bouton retour.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| CardView + RecyclerView | 4 |
| FAB fonctionnel | 3 |
| TextInputLayout avec validation | 4 |
| Snackbar approprié | 2 |
| Dark Mode fonctionnel | 4 |
| Interface cohérente | 3 |

**Total** : /20

---

## 9. Bonnes pratiques Material

- Utiliser des **espaces cohérents** (8dp, 16dp, 24dp).
- Limiter les **couleurs** (primaire, secondaire, surface).
- Éviter les **animations excessives**.
- Respecter les **tailles de touche** (min 48dp).
- Tester en **mode sombre** systématiquement.

---

## 10. Ressources supplémentaires

- Material Design 3 : <https://m3.material.io/>
- Composants Android : <https://material.io/develop/android>
- Icônes Material : <https://fonts.google.com/icons>
- Générateur de palettes : <https://material.io/resources/color/>

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
