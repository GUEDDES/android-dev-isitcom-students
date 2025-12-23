# Module 4 : Fiche de synthèse

## 🎯 Concepts essentiels

### Activity vs View

- **Activity** = écran complet de l'application
- **View** = élément visuel (bouton, texte, image...)

---

## 📦 Hiérarchie des vues

```
Activity
  └── Layout (ViewGroup)
        ├── TextView
        ├── Button
        └── EditText
```

---

## 🏗️ Types de Layouts

### ConstraintLayout (recommandé)
```xml
<androidx.constraintlayout.widget.ConstraintLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

### LinearLayout
```xml
<LinearLayout
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
    <TextView android:text="Titre" />
    <Button android:text="OK" />
    
</LinearLayout>
```

---

## 🎨 Widgets principaux

| Widget | Usage | Attributs clés |
|--------|-------|----------------|
| TextView | Afficher texte | text, textSize, textColor |
| EditText | Saisir texte | hint, inputType |
| Button | Bouton | text, onClick |
| ImageView | Image | src, scaleType |
| CheckBox | Case | checked, text |
| RadioButton | Choix unique | checked, text |

---

## 🔗 Lien XML ↔ Java

### findViewById()
```java
// Récupérer une vue
TextView text = findViewById(R.id.textTitle);
Button btn = findViewById(R.id.btnOk);
EditText input = findViewById(R.id.editName);
```

### Modifier une vue
```java
text.setText("Nouveau texte");
text.setTextColor(Color.RED);
text.setTextSize(20);
text.setVisibility(View.GONE);  // Cacher
```

---

## 🖱️ Gestion des événements

### Clic sur bouton
```java
Button btn = findViewById(R.id.btnSubmit);
btn.setOnClickListener(v -> {
    // Code exécuté au clic
});
```

### Lire un EditText
```java
EditText input = findViewById(R.id.editName);
String text = input.getText().toString();
```

### Vérifier CheckBox
```java
CheckBox check = findViewById(R.id.checkAgree);
if (check.isChecked()) {
    // Case cochée
}
```

---

## 📏 Unités de mesure

| Unité | Usage |
|-------|-------|
| **dp** (density-independent pixels) | Dimensions (largeur, hauteur, marges) |
| **sp** (scale-independent pixels) | Tailles de texte (s'adapte aux préférences) |
| **px** | Pixels physiques (à éviter) |

```xml
android:layout_width="200dp"
android:textSize="16sp"
android:padding="8dp"
```

---

## 🎯 Contraintes ConstraintLayout

### Centrer horizontalement
```xml
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
```

### Centrer verticalement
```xml
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
```

### Positionner sous un élément
```xml
app:layout_constraintTop_toBottomOf="@id/textTitle"
```

---

## 🎨 Ressources

### strings.xml
```xml
<resources>
    <string name="app_name">Mon App</string>
    <string name="welcome">Bienvenue</string>
</resources>
```

Utilisation :
```xml
android:text="@string/welcome"
```

### colors.xml
```xml
<resources>
    <color name="primary">#2196F3</color>
</resources>
```

---

## ⚠️ Erreurs courantes

| Problème | Cause | Solution |
|----------|-------|----------|
| findViewById retourne null | setContentView non appelé | Appeler avant findViewById |
| Vue non visible | Contraintes manquantes | Définir toutes contraintes |
| Crash sur clic | Listener mal configuré | Vérifier setOnClickListener |
| Texte coupé | wrap_content trop petit | Utiliser match_parent ou contraintes |

---

## 📝 Checklist interface

✅ setContentView() appelé dans onCreate()  
✅ Tous les widgets ont un ID unique  
✅ Contraintes définies (si ConstraintLayout)  
✅ Dimensions en dp, textes en sp  
✅ Textes externalisés dans strings.xml  
✅ findViewById() après setContentView()  

---

👨‍🏫 **Module 4 - Interfaces et Layouts** | ISITCOM 2025-2026
