# Module 9 : Fiche de synthèse

## 🎯 Qu'est-ce que Material Design ?

**Material Design** = Langage de design de Google  
**Objectif** : Applications belles, cohérentes et intuitives

---

## 🎨 Composants Material

### 1. MaterialButton

```xml
<com.google.android.material.button.MaterialButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="OK"
    app:icon="@drawable/ic_check"
    app:cornerRadius="8dp" />
```

### 2. TextInputLayout

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:startIconDrawable="@drawable/ic_email">
    
    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
        
</com.google.android.material.textfield.TextInputLayout>
```

### 3. CardView

```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp">
    
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contenu carte" />
        
</com.google.android.material.card.MaterialCardView>
```

### 4. FloatingActionButton (FAB)

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/ic_add"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_margin="16dp" />
```

---

## 🎨 Thèmes et couleurs

### themes.xml

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.Light">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/secondary</item>
        <item name="colorSurface">@color/surface</item>
    </style>
</resources>
```

### colors.xml

```xml
<resources>
    <color name="primary">#6200EE</color>
    <color name="secondary">#03DAC6</color>
    <color name="surface">#FFFFFF</color>
    <color name="error">#B00020</color>
</resources>
```

---

## 🌙 Dark Mode

### 1. Créer themes.xml (night)

```
res/
  ├── values/
  │   └── themes.xml         (mode clair)
  └── values-night/
      └── themes.xml         (mode sombre)
```

### values-night/themes.xml

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.Dark">
        <item name="colorPrimary">@color/primary_dark</item>
        <item name="colorSurface">@color/surface_dark</item>
    </style>
</resources>
```

### Détection du mode

```java
int nightMode = getResources().getConfiguration().uiMode 
    & Configuration.UI_MODE_NIGHT_MASK;

if (nightMode == Configuration.UI_MODE_NIGHT_YES) {
    // Mode sombre actif
}
```

---

## 📱 Bottom Navigation

### Layout

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/bottomNav"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:menu="@menu/bottom_menu"
    app:layout_constraintBottom_toBottomOf="parent" />
```

### Menu (res/menu/bottom_menu.xml)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/nav_home"
        android:icon="@drawable/ic_home"
        android:title="Accueil" />
    <item
        android:id="@+id/nav_search"
        android:icon="@drawable/ic_search"
        android:title="Recherche" />
</menu>
```

---

## 🎭 Animations

### Transition entre Activities

```java
// Activity 1
Intent intent = new Intent(this, SecondActivity.class);
ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
startActivity(intent, options.toBundle());
```

### Animer une vue

```java
view.animate()
    .alpha(0f)          // Transparence
    .translationY(100f) // Déplacement
    .setDuration(300)   // Durée (ms)
    .start();
```

---

## 📰 Toolbar

### Layout

```xml
<com.google.android.material.appbar.MaterialToolbar
    android:id="@+id/toolbar"
    android:layout_width="match_parent"
    android:layout_height="?attr/actionBarSize"
    android:background="?attr/colorPrimary"
    app:title="Mon App"
    app:titleTextColor="@color/white" />
```

### Activer dans Activity

```java
MaterialToolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);
```

---

## 📦 Snackbar

```java
Snackbar.make(view, "Message", Snackbar.LENGTH_SHORT)
    .setAction("Annuler", v -> {
        // Action au clic
    })
    .show();
```

---

## 📝 Guidelines Material Design

### Espacement

- **Padding/Margin** : Multiples de 4dp (4, 8, 16, 24, 32...)
- **Élévation** : 0dp (plat), 2dp (carte), 4dp (FAB), 8dp (menu)

### Typographie

```xml
android:textAppearance="@style/TextAppearance.Material3.HeadlineLarge"
android:textAppearance="@style/TextAppearance.Material3.BodyMedium"
```

### Icônes

- Taille : 24dp pour les actions, 48dp pour les zones tactiles
- Format : VectorDrawable (SVG)

---

## ⚠️ Bonnes pratiques

✅ Utiliser Material Components au lieu des widgets standard  
✅ Définir couleurs dans colors.xml  
✅ Supporter le dark mode  
✅ Respecter les espacements (8dp, 16dp)  
✅ Utiliser VectorDrawable pour les icônes  
✅ Animer les transitions importantes  

---

## 🔑 Mémo rapide

```xml
<!-- Button Material -->
<com.google.android.material.button.MaterialButton
    android:text="OK" />

<!-- Card -->
<com.google.android.material.card.MaterialCardView
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp" />

<!-- FAB -->
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:src="@drawable/ic_add" />

<!-- Input -->
<com.google.android.material.textfield.TextInputLayout
    android:hint="Email">
    <com.google.android.material.textfield.TextInputEditText />
</com.google.android.material.textfield.TextInputLayout>
```

---

## 📚 Ressources

- [Material Design 3](https://m3.material.io/)
- [Material Components Android](https://github.com/material-components/material-components-android)
- [Material Icons](https://fonts.google.com/icons)

---

👨‍🏫 **Module 9 - Material Design** | ISITCOM 2025-2026
