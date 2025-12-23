# Module 9 : Quiz d'auto-évaluation - Material Design

## Questions à choix multiples

### Question 1
Qu'est-ce que Material Design ?

A) Un langage de programmation  
B) Un système de design de Google  
C) Une base de données  
D) Un émulateur

<details>
<summary>Réponse</summary>
B) Un système de design créé par Google pour des interfaces modernes et cohérentes
</details>

---

### Question 2
Quel est le package des Material Components ?

A) android.widget  
B) android.material  
C) com.google.android.material  
D) androidx.material

<details>
<summary>Réponse</summary>
C) com.google.android.material
</details>

---

### Question 3
Quel thème parent utiliser pour Material Design 3 ?

A) Theme.AppCompat  
B) Theme.Material3.Light  
C) Theme.Design  
D) Theme.Android

<details>
<summary>Réponse</summary>
B) Theme.Material3.Light ou Theme.Material3.Dark
</details>

---

### Question 4
Qu'est-ce qu'un FAB ?

A) Floating Activity Button  
B) Floating Action Button  
C) Fixed Action Bar  
D) Fragment Action Button

<details>
<summary>Réponse</summary>
B) Floating Action Button - bouton flottant pour action principale
</details>

---

### Question 5
Quel composant Material remplace EditText ?

A) MaterialEditText  
B) TextInputLayout + TextInputEditText  
C) MaterialInput  
D) DesignEditText

<details>
<summary>Réponse</summary>
B) TextInputLayout contient TextInputEditText
</details>

---

### Question 6
Comment supporter le dark mode ?

A) Créer values-night/themes.xml  
B) Changer les couleurs manuellement  
C) Utiliser CSS  
D) Impossible

<details>
<summary>Réponse</summary>
A) Créer un dossier values-night avec themes.xml adapté
</details>

---

### Question 7
Quel espacement respecter en Material Design ?

A) Multiples de 2dp  
B) Multiples de 4dp  
C) Multiples de 10dp  
D) N'importe quel

<details>
<summary>Réponse</summary>
B) Multiples de 4dp (4, 8, 16, 24, 32...)
</details>

---

### Question 8
Quelle élévation pour une Card normale ?

A) 0dp  
B) 2dp  
C) 8dp  
D) 16dp

<details>
<summary>Réponse</summary>
B) 2dp pour card, 4dp pour menu, 8dp pour dialog
</details>

---

### Question 9
Que fait Snackbar par rapport à Toast ?

A) Identique  
B) Permet d'ajouter une action  
C) Plus rapide  
D) Pour les erreurs seulement

<details>
<summary>Réponse</summary>
B) Snackbar permet d'ajouter un bouton d'action (Annuler, Réessayer...)
</details>

---

### Question 10
Où placer le FAB classiquement ?

A) En haut à gauche  
B) Au centre  
C) En bas à droite  
D) N'importe où

<details>
<summary>Réponse</summary>
C) En bas à droite avec marges de 16dp
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```xml
<com.google.android.material.card.MaterialCardView
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp">
    
    <TextView android:text="Contenu" />
    
</com.google.android.material.card.MaterialCardView>
```

A) Crée un bouton  
B) Crée une carte avec ombre et coins arrondis  
C) Crée un fragment  
D) Affiche une image

<details>
<summary>Réponse</summary>
B) Crée une MaterialCardView avec élévation 4dp et coins arrondis 8dp
</details>

---

### Question 12
Comment afficher un Snackbar avec action ?

<details>
<summary>Réponse</summary>

```java
Snackbar.make(view, "Elément supprimé", Snackbar.LENGTH_LONG)
    .setAction("Annuler", v -> {
        // Code pour annuler
    })
    .show();
```
</details>

---

### Question 13
Créez un TextInputLayout avec hint "Email" et icône.

<details>
<summary>Réponse</summary>

```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Email"
    app:startIconDrawable="@drawable/ic_email"
    app:endIconMode="clear_text">
    
    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:inputType="textEmailAddress" />
        
</com.google.android.material.textfield.TextInputLayout>
```
</details>

---

## Questions sur les thèmes

### Question 14
Définissez un thème Material 3 avec couleur primaire #6200EE.

<details>
<summary>Réponse</summary>

```xml
<!-- values/colors.xml -->
<resources>
    <color name="primary">#6200EE</color>
    <color name="primary_variant">#3700B3</color>
    <color name="secondary">#03DAC6</color>
</resources>

<!-- values/themes.xml -->
<resources>
    <style name="AppTheme" parent="Theme.Material3.Light">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryVariant">@color/primary_variant</item>
        <item name="colorSecondary">@color/secondary</item>
    </style>
</resources>
```
</details>

---

### Question 15
Comment créer un thème sombre automatique ?

<details>
<summary>Réponse</summary>

```
res/
  ├── values/
  │   ├── themes.xml          (clair)
  │   └── colors.xml
  └── values-night/
      ├── themes.xml          (sombre)
      └── colors.xml
```

```xml
<!-- values-night/themes.xml -->
<resources>
    <style name="AppTheme" parent="Theme.Material3.Dark">
        <item name="colorPrimary">@color/primary_dark</item>
        <item name="android:statusBarColor">@color/black</item>
    </style>
</resources>
```

Android choisit automatiquement selon les paramètres système.
</details>

---

## Questions pratiques

### Question 16
Créez un FAB en bas à droite pour ajouter un item.

<details>
<summary>Réponse</summary>

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <!-- Contenu -->
    
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAdd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_add"
        android:contentDescription="@string/add"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_margin="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

```java
FloatingActionButton fab = findViewById(R.id.fabAdd);
fab.setOnClickListener(v -> {
    // Ajouter un item
});
```
</details>

---

### Question 17
Créez un MaterialButton avec icône et coins arrondis.

<details>
<summary>Réponse</summary>

```xml
<com.google.android.material.button.MaterialButton
    android:id="@+id/btnSave"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/save"
    app:icon="@drawable/ic_save"
    app:cornerRadius="12dp"
    app:iconGravity="textStart" />
```
</details>

---

### Question 18
Activez le mode sombre programmatiquement.

<details>
<summary>Réponse</summary>

```java
import androidx.appcompat.app.AppCompatDelegate;

// Mode sombre
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

// Mode clair
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

// Suivre système
AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
```
</details>

---

### Question 19
Validez un email dans TextInputLayout et affichez une erreur.

<details>
<summary>Réponse</summary>

```java
TextInputLayout emailLayout = findViewById(R.id.emailLayout);
TextInputEditText emailInput = findViewById(R.id.emailInput);
Button btnSubmit = findViewById(R.id.btnSubmit);

btnSubmit.setOnClickListener(v -> {
    String email = emailInput.getText().toString().trim();
    
    if (email.isEmpty()) {
        emailLayout.setError("Email requis");
    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        emailLayout.setError("Email invalide");
    } else {
        emailLayout.setError(null); // Pas d'erreur
        // Continuer...
    }
});
```
</details>

---

### Question 20
Créez un Bottom Sheet simple.

<details>
<summary>Réponse</summary>

```java
public class MyBottomSheet extends BottomSheetDialogFragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        
        TextView option1 = view.findViewById(R.id.option1);
        option1.setOnClickListener(v -> {
            // Action
            dismiss();
        });
        
        return view;
    }
}

// Afficher
MyBottomSheet bottomSheet = new MyBottomSheet();
bottomSheet.show(getSupportFragmentManager(), "BottomSheet");
```
</details>

---

## Barème

- **18-20/20** : Excellent ! Maîtrise complète de Material Design
- **15-17/20** : Très bien
- **12-14/20** : Bien, approfondir les thèmes
- **< 12/20** : Revoir le module

---

## Points clés à retenir

✅ **Material Components** = Widgets modernes de Google  
✅ **Theme.Material3** = Thème de base  
✅ **Espacements** = Multiples de 4dp  
✅ **Dark mode** = values-night/themes.xml  
✅ **FAB** = Action principale en bas à droite  
✅ **Snackbar** = Toast avec action  
✅ **TextInputLayout** = Meilleur qu'EditText  

---

👨‍🏫 **Module 9 - Material Design** | ISITCOM 2025-2026
