# TD07 : Material Design et thèmes

## 🎯 Objectifs

- Appliquer Material Design.
- Créer des thèmes personnalisés.
- Implémenter le Dark Mode.

---

## Partie 1 : Composants Material (45 min)

### Exercice 1.1 : CardView

Créer une interface avec 3 CardView :

```xml
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <androidx.cardview.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="16dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Carte 1"
                    android:textSize="20sp"
                    android:textStyle="bold" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Description de la carte 1"
                    android:layout_marginTop="8dp" />

            </LinearLayout>

        </androidx.cardview.widget.CardView>

        <!-- Répéter pour cartes 2 et 3 -->

    </LinearLayout>

</ScrollView>
```

### Exercice 1.2 : FloatingActionButton

Ajouter un FAB :

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

Gérer le clic :

```java
FloatingActionButton fab = findViewById(R.id.fab);
fab.setOnClickListener(v -> {
    Snackbar.make(v, "Ajout d'un élément", Snackbar.LENGTH_LONG)
        .setAction("Annuler", view -> {
            Toast.makeText(this, "Action annulée", Toast.LENGTH_SHORT).show();
        })
        .show();
});
```

### Exercice 1.3 : TextInputLayout

Formulaire avec validation :

```xml
<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/emailLayout"
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

Validation :

```java
TextInputLayout emailLayout = findViewById(R.id.emailLayout);
TextInputEditText editEmail = findViewById(R.id.editEmail);

Button btnSubmit = findViewById(R.id.btnSubmit);
btnSubmit.setOnClickListener(v -> {
    String email = editEmail.getText().toString();
    
    if (email.isEmpty()) {
        emailLayout.setError("Email requis");
    } else if (!email.contains("@")) {
        emailLayout.setError("Email invalide");
    } else {
        emailLayout.setError(null);
        Toast.makeText(this, "Email valide", Toast.LENGTH_SHORT).show();
    }
});
```

---

## Partie 2 : Thèmes personnalisés (45 min)

### Exercice 2.1 : Définir les couleurs

Dans `res/values/colors.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Couleurs ISITCOM -->
    <color name="isitcom_blue">#1565C0</color>
    <color name="isitcom_blue_dark">#0D47A1</color>
    <color name="isitcom_orange">#FF6F00</color>
    <color name="isitcom_orange_dark">#E65100</color>
    
    <color name="white">#FFFFFF</color>
    <color name="black">#000000</color>
    <color name="light_gray">#F5F5F5</color>
    <color name="dark_gray">#212121</color>
</resources>
```

### Exercice 2.2 : Créer le thème

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="Theme.ISITCOM" parent="Theme.Material3.Light.NoActionBar">
        <item name="colorPrimary">@color/isitcom_blue</item>
        <item name="colorPrimaryVariant">@color/isitcom_blue_dark</item>
        <item name="colorOnPrimary">@color/white</item>
        
        <item name="colorSecondary">@color/isitcom_orange</item>
        <item name="colorSecondaryVariant">@color/isitcom_orange_dark</item>
        <item name="colorOnSecondary">@color/white</item>
        
        <item name="android:colorBackground">@color/white</item>
        <item name="colorSurface">@color/white</item>
    </style>
</resources>
```

Appliquer dans `AndroidManifest.xml` :

```xml
<application
    android:theme="@style/Theme.ISITCOM">
```

---

## Partie 3 : Dark Mode (60 min)

### Exercice 3.1 : Thème sombre

Créer `res/values-night/themes.xml` :

```xml
<resources>
    <style name="Theme.ISITCOM" parent="Theme.Material3.Dark.NoActionBar">
        <item name="colorPrimary">@color/isitcom_blue_light</item>
        <item name="android:colorBackground">@color/dark_gray</item>
        <item name="colorSurface">@color/black</item>
    </style>
</resources>
```

Ajouter couleurs dans `res/values-night/colors.xml` :

```xml
<resources>
    <color name="isitcom_blue_light">#42A5F5</color>
    <color name="isitcom_orange_light">#FFA726</color>
</resources>
```

### Exercice 3.2 : Toggle Dark Mode

Dans les paramètres :

```xml
<Switch
    android:id="@+id/switchDarkMode"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Mode sombre" />
```

Gérer le changement :

```java
Switch switchDarkMode = findViewById(R.id.switchDarkMode);

// Vérifier le mode actuel
int currentMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
switchDarkMode.setChecked(currentMode == Configuration.UI_MODE_NIGHT_YES);

switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
    if (isChecked) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
});
```

---

## 🎯 TP Noté : Application ISITCOM Material (/20)

### Consignes

Créer une application "ISITCOM Info" avec Material Design complet :

1. **Écran d'accueil** :
   - 4 CardView (Actualités, Emploi du temps, Notes, Bibliothèque).
   - FAB pour ajouter une note rapide.

2. **Actualités** :
   - RecyclerView avec CardView.
   - Au moins 5 actualités.
   - Clic → détail.

3. **Paramètres** :
   - Toggle Dark Mode.
   - Choix de langue (Spinner).
   - Notifications (Switch).

4. **Design** :
   - Thème ISITCOM (bleu/orange).
   - Dark Mode fonctionnel.
   - Toolbar avec icône.
   - Bottom Navigation.

### Barème

| Critère | Points |
|---------|--------|
| CardView + FAB | 3 |
| RecyclerView actualités | 3 |
| Thème personnalisé | 4 |
| Dark Mode complet | 4 |
| Snackbar / TextInputLayout | 2 |
| Navigation fluide | 2 |
| Cohérence visuelle | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
