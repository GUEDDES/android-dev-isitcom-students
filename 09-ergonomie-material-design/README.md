# Module 9 : Ergonomie et Material Design

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Appliquer les principes de Material Design 3.
- Personnaliser les thèmes et les couleurs.
- Implémenter le mode sombre (Dark Theme).
- Utiliser les composants Material (FAB, Snackbar, CardView, Chips...).

---

## 1. Qu'est-ce que Material Design ?

**Material Design** est le langage de conception visuelle de Google. [file:2]

### 1.1 Principes clés

- **Matérialité** : surfaces, ombres, profondeur.
- **Cohérence** : expérience utilisateur unifiée.
- **Innovation** : animations fluides, transitions naturelles.

### 1.2 Material Design 3 (2025)

Version actuelle avec :

- **Dynamic Colors** : couleurs adaptées au fond d'écran (Android 12+).
- **Thème personnalisé** : système de couleurs étendu.
- **Composants modernisés** : boutons, cartes, navigation.

Documentation officielle : <https://m3.material.io>

---

## 2. Thèmes et styles

### 2.1 Fichier themes.xml

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="Theme.MyApp" parent="Theme.Material3.DayNight">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/secondary</item>
        <item name="colorTertiary">@color/tertiary</item>
        <item name="android:statusBarColor">@color/primary</item>
    </style>
</resources>
```

### 2.2 Définir les couleurs

Dans `res/values/colors.xml` :

```xml
<resources>
    <color name="primary">#6200EE</color>
    <color name="secondary">#03DAC6</color>
    <color name="tertiary">#FF6B6B</color>
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
</resources>
```

### 2.3 Appliquer le thème

Dans `AndroidManifest.xml` :

```xml
<application
    android:theme="@style/Theme.MyApp">
```

---

## 3. Mode sombre (Dark Theme)

### 3.1 Créer le thème sombre

Créer `res/values-night/themes.xml` :

```xml
<resources>
    <style name="Theme.MyApp" parent="Theme.Material3.DayNight">
        <item name="colorPrimary">#BB86FC</item>
        <item name="colorOnPrimary">@color/black</item>
        <item name="colorSecondary">#03DAC6</item>
        <item name="android:statusBarColor">#121212</item>
        <item name="android:windowBackground">#121212</item>
    </style>
</resources>
```

### 3.2 Couleurs adaptatives

Dans `res/values-night/colors.xml` :

```xml
<resources>
    <color name="background">#121212</color>
    <color name="surface">#1E1E1E</color>
    <color name="text_primary">#FFFFFF</color>
</resources>
```

### 3.3 Forcer un mode

Pour forcer le mode jour/nuit dans une Activity :

```java
// Mode sombre
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// Mode clair
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

// Suivre système
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
```

---

## 4. Composants Material essentiels

### 4.1 FloatingActionButton (FAB)

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/ic_add"
    app:layout_anchor="@id/recyclerView"
    app:layout_anchorGravity="bottom|end"
    android:layout_margin="16dp" />
```

### 4.2 CardView

```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp"
    android:layout_margin="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:text="Titre"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <TextView
            android:text="Description"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

### 4.3 Snackbar

```java
Snackbar.make(view, "Message", Snackbar.LENGTH_LONG)
    .setAction("Annuler", v -> {
        // Action
    })
    .show();
```

### 4.4 TextInputLayout

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:startIconDrawable="@drawable/ic_email">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/editEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />

</com.google.android.material.textfield.TextInputLayout>
```

### 4.5 Chips

```xml
<com.google.android.material.chip.ChipGroup
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:singleSelection="true">

    <com.google.android.material.chip.Chip
        android:id="@+id/chipAll"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Tous" />

    <com.google.android.material.chip.Chip
        android:id="@+id/chipActive"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Actifs" />

</com.google.android.material.chip.ChipGroup>
```

---

## 5. Animations et transitions

### 5.1 Animation de vue simple

```java
view.animate()
    .alpha(0f)
    .setDuration(300)
    .start();
```

### 5.2 Transition entre Activities

```java
Intent intent = new Intent(this, DetailActivity.class);
ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
startActivity(intent, options.toBundle());
```

Dans `themes.xml` :

```xml
<item name="android:windowActivityTransitions">true</item>
```

---

## 6. Accessibilité

### 6.1 Content Description

```xml
<ImageButton
    android:contentDescription="Ajouter une tâche"
    android:src="@drawable/ic_add" />
```

### 6.2 Tailles de texte adaptatives

Utiliser `sp` pour les textes (pas `dp`).

### 6.3 Contraste suffisant

Vérifier avec **Accessibility Scanner** (outil Android).

---

## 7. Exercices pratiques (Module 9)

### Exercice 1 – Thème personnalisé

1. Créer un thème avec vos couleurs.
2. Appliquer ce thème à l'application.
3. Vérifier l'affichage sur plusieurs écrans.

### Exercice 2 – Mode sombre

1. Implémenter le mode sombre.
2. Ajouter un bouton pour basculer entre clair/sombre.
3. Sauvegarder la préférence utilisateur (SharedPreferences).

### Exercice 3 – Composants Material

1. Utiliser FAB, CardView, Snackbar dans une même Activity.
2. FAB déclenche un Snackbar.
3. Afficher 3 cartes avec des informations.

### Exercice 4 – TextInputLayout avec validation

1. Créer un formulaire avec 3 champs (nom, email, téléphone).
2. Utiliser TextInputLayout.
3. Valider les champs et afficher des erreurs si invalides.

---

## 8. Mini-TP : Application stylée

### Consignes

Créer une application "Profil utilisateur" avec :

1. **Thème personnalisé** avec couleurs cohérentes.
2. **Mode sombre** fonctionnel.
3. **Écran principal** :
   - CardView affichant les infos (nom, email, bio).
   - FAB pour éditer le profil.
4. **Écran édition** :
   - TextInputLayout pour chaque champ.
   - Bouton "Enregistrer" avec Snackbar de confirmation.
5. **Animations** lors des transitions.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Thème personnalisé appliqué | 3 |
| Mode sombre fonctionnel | 3 |
| Utilisation correcte des composants Material | 6 |
| Animations fluides | 3 |
| Interface soignée et cohérente | 5 |

**Total** : /20

---

## 9. Ressources

- Material Design 3 : <https://m3.material.io>
- Material Components : <https://github.com/material-components/material-components-android>
- Color Tool : <https://material.io/resources/color>
- Icon Library : <https://fonts.google.com/icons>

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
