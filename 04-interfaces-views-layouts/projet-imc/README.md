# Projet Module 4 : Application IMC (Indice de Masse Corporelle)

## 🎯 Objectif

Créer une application qui calcule l'IMC et affiche une interprétation visuelle du résultat.

Concepts : EditText, validation, conditions, couleurs dynamiques.

---

## 📋 Fonctionnalités

- Saisie du poids (kg)
- Saisie de la taille (cm)
- Bouton "Calculer"
- Affichage de l'IMC
- Catégorie avec couleur (Maigreur, Normal, Surpoids, Obésité)
- Réinitialisation

---

## 📱 Interface (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp"
    android:background="#F5F5F5">

    <!-- Titre -->
    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Calculateur IMC"
        android:textSize="32sp"
        android:textStyle="bold"
        android:textColor="#1976D2"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <!-- Poids -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/tilPoids"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Poids (kg)"
        app:boxBackgroundMode="outline"
        app:boxCornerRadiusTopStart="8dp"
        app:boxCornerRadiusTopEnd="8dp"
        app:boxCornerRadiusBottomStart="8dp"
        app:boxCornerRadiusBottomEnd="8dp"
        app:layout_constraintTop_toBottomOf="@id/tvTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="48dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etPoids"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="numberDecimal"
            android:textSize="18sp" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- Taille -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/tilTaille"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Taille (cm)"
        app:boxBackgroundMode="outline"
        app:boxCornerRadiusTopStart="8dp"
        app:boxCornerRadiusTopEnd="8dp"
        app:boxCornerRadiusBottomStart="8dp"
        app:boxCornerRadiusBottomEnd="8dp"
        app:layout_constraintTop_toBottomOf="@id/tilPoids"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etTaille"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="numberDecimal"
            android:textSize="18sp" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- Bouton Calculer -->
    <Button
        android:id="@+id/btnCalculer"
        android:layout_width="0dp"
        android:layout_height="60dp"
        android:text="Calculer IMC"
        android:textSize="18sp"
        android:textStyle="bold"
        android:backgroundTint="#1976D2"
        app:layout_constraintTop_toBottomOf="@id/tilTaille"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Carte Résultat -->
    <androidx.cardview.widget.CardView
        android:id="@+id/cvResult"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        app:cardCornerRadius="16dp"
        app:cardElevation="8dp"
        android:visibility="gone"
        app:layout_constraintTop_toBottomOf="@id/btnCalculer"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent">

        <LinearLayout
            android:id="@+id/llResult"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="24dp"
            android:background="@android:color/white">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Votre IMC"
                android:textSize="16sp"
                android:textColor="#757575"
                android:layout_gravity="center_horizontal" />

            <TextView
                android:id="@+id/tvIMC"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="0.0"
                android:textSize="48sp"
                android:textStyle="bold"
                android:textColor="#212121"
                android:layout_gravity="center_horizontal"
                android:layout_marginTop="8dp" />

            <TextView
                android:id="@+id/tvCategorie"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Normal"
                android:textSize="24sp"
                android:textStyle="bold"
                android:layout_gravity="center_horizontal"
                android:layout_marginTop="16dp"
                android:padding="12dp"
                android:background="@android:color/transparent" />

            <TextView
                android:id="@+id/tvDescription"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Votre poids est dans la norme"
                android:textSize="14sp"
                android:textColor="#757575"
                android:layout_gravity="center_horizontal"
                android:textAlignment="center"
                android:layout_marginTop="8dp" />

        </LinearLayout>

    </androidx.cardview.widget.CardView>

    <!-- Bouton Réinitialiser -->
    <Button
        android:id="@+id/btnReset"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Réinitialiser"
        android:textSize="16sp"
        android:backgroundTint="#9E9E9E"
        android:visibility="gone"
        app:layout_constraintTop_toBottomOf="@id/cvResult"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## ☕ Code Java (MainActivity.java)

```java
package tn.isitcom.imccalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // Vues
    private TextInputEditText etPoids, etTaille;
    private TextInputLayout tilPoids, tilTaille;
    private Button btnCalculer, btnReset;
    private CardView cvResult;
    private TextView tvIMC, tvCategorie, tvDescription;
    private LinearLayout llResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        initViews();

        // Listeners
        btnCalculer.setOnClickListener(v -> calculerIMC());
        btnReset.setOnClickListener(v -> reinitialiser());
    }

    /**
     * Initialisation de toutes les vues
     */
    private void initViews() {
        etPoids = findViewById(R.id.etPoids);
        etTaille = findViewById(R.id.etTaille);
        tilPoids = findViewById(R.id.tilPoids);
        tilTaille = findViewById(R.id.tilTaille);
        btnCalculer = findViewById(R.id.btnCalculer);
        btnReset = findViewById(R.id.btnReset);
        cvResult = findViewById(R.id.cvResult);
        tvIMC = findViewById(R.id.tvIMC);
        tvCategorie = findViewById(R.id.tvCategorie);
        tvDescription = findViewById(R.id.tvDescription);
        llResult = findViewById(R.id.llResult);
    }

    /**
     * Calcul de l'IMC
     */
    private void calculerIMC() {
        // Récupération des valeurs
        String poidsStr = etPoids.getText().toString().trim();
        String tailleStr = etTaille.getText().toString().trim();

        // Validation
        if (!validerEntrees(poidsStr, tailleStr)) {
            return;
        }

        // Conversion
        double poids = Double.parseDouble(poidsStr);
        double tailleCm = Double.parseDouble(tailleStr);
        double tailleM = tailleCm / 100.0; // Conversion cm -> m

        // Calcul IMC = poids / (taille^2)
        double imc = poids / (tailleM * tailleM);

        // Affichage du résultat
        afficherResultat(imc);
    }

    /**
     * Validation des entrées utilisateur
     */
    private boolean validerEntrees(String poids, String taille) {
        // Réinitialiser les erreurs
        tilPoids.setError(null);
        tilTaille.setError(null);

        boolean valide = true;

        // Vérification poids
        if (poids.isEmpty()) {
            tilPoids.setError("Veuillez saisir votre poids");
            valide = false;
        } else {
            try {
                double p = Double.parseDouble(poids);
                if (p <= 0 || p > 500) {
                    tilPoids.setError("Poids invalide (1-500 kg)");
                    valide = false;
                }
            } catch (NumberFormatException e) {
                tilPoids.setError("Valeur numérique invalide");
                valide = false;
            }
        }

        // Vérification taille
        if (taille.isEmpty()) {
            tilTaille.setError("Veuillez saisir votre taille");
            valide = false;
        } else {
            try {
                double t = Double.parseDouble(taille);
                if (t <= 0 || t > 300) {
                    tilTaille.setError("Taille invalide (1-300 cm)");
                    valide = false;
                }
            } catch (NumberFormatException e) {
                tilTaille.setError("Valeur numérique invalide");
                valide = false;
            }
        }

        if (!valide) {
            Toast.makeText(this, "Veuillez corriger les erreurs", Toast.LENGTH_SHORT).show();
        }

        return valide;
    }

    /**
     * Affichage du résultat avec catégorisation
     */
    private void afficherResultat(double imc) {
        // Afficher la carte de résultat
        cvResult.setVisibility(View.VISIBLE);
        btnReset.setVisibility(View.VISIBLE);

        // Afficher la valeur de l'IMC
        tvIMC.setText(String.format("%.1f", imc));

        // Catégorisation selon l'OMS
        String categorie;
        String description;
        int couleur;

        if (imc < 18.5) {
            categorie = "Maigreur";
            description = "Votre IMC indique une insuffisance pondérale";
            couleur = Color.parseColor("#2196F3"); // Bleu
        } else if (imc < 25) {
            categorie = "Normal";
            description = "Votre poids est dans la norme";
            couleur = Color.parseColor("#4CAF50"); // Vert
        } else if (imc < 30) {
            categorie = "Surpoids";
            description = "Votre IMC indique un surpoids";
            couleur = Color.parseColor("#FF9800"); // Orange
        } else {
            categorie = "Obésité";
            description = "Votre IMC indique une obésité";
            couleur = Color.parseColor("#F44336"); // Rouge
        }

        // Appliquer les valeurs
        tvCategorie.setText(categorie);
        tvCategorie.setTextColor(couleur);
        tvDescription.setText(description);
        llResult.setBackgroundColor(Color.argb(30, 
            Color.red(couleur), 
            Color.green(couleur), 
            Color.blue(couleur)));
    }

    /**
     * Réinitialiser l'application
     */
    private void reinitialiser() {
        etPoids.setText("");
        etTaille.setText("");
        tilPoids.setError(null);
        tilTaille.setError(null);
        cvResult.setVisibility(View.GONE);
        btnReset.setVisibility(View.GONE);
        Toast.makeText(this, "Réinitialisé", Toast.LENGTH_SHORT).show();
    }
}
```

---

## 📚 Explication détaillée

### 1. Material Design Components

**TextInputLayout** :
- Enveloppe `EditText` avec animation du label
- Méthode `setError()` pour afficher les erreurs inline
- `boxBackgroundMode="outline"` : bordure visible

**CardView** :
- Carte avec ombre pour le résultat
- `cardCornerRadius` : coins arrondis
- `cardElevation` : hauteur de l'ombre

### 2. Validation robuste

```java
private boolean validerEntrees(String poids, String taille) {
    // Vérifications :
    // 1. Champs non vides
    // 2. Conversion possible en double
    // 3. Valeurs dans une plage réaliste
}
```

**Avantages** :
- Empêche les crashs (NumberFormatException)
- Guide l'utilisateur avec des messages clairs
- Valeurs réalistes (poids 1-500kg, taille 1-300cm)

### 3. Calcul de l'IMC

**Formule** : IMC = poids (kg) / [taille (m)]²

```java
double tailleM = tailleCm / 100.0;  // cm → m
double imc = poids / (tailleM * tailleM);
```

### 4. Catégorisation OMS

| IMC | Catégorie | Couleur |
|-----|-----------|--------|
| < 18.5 | Maigreur | Bleu |
| 18.5 - 24.9 | Normal | Vert |
| 25.0 - 29.9 | Surpoids | Orange |
| ≥ 30.0 | Obésité | Rouge |

### 5. Interface dynamique

**Visibilité** :
```java
cvResult.setVisibility(View.VISIBLE);  // Afficher
cvResult.setVisibility(View.GONE);     // Masquer (n'occupe pas d'espace)
```

**Couleurs dynamiques** :
```java
llResult.setBackgroundColor(Color.argb(30, r, g, b)); // Fond transparent
tvCategorie.setTextColor(couleur);                    // Texte coloré
```

---

## 🛠️ Configuration Gradle

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

---

## 🎯 Tests à effectuer

### Test 1 : Calcul normal
- Poids : `70`
- Taille : `175`
- **Résultat attendu** : IMC = 22.9, Catégorie "Normal" (vert)

### Test 2 : Validation
- Laisser un champ vide
- **Résultat attendu** : Message d'erreur sous le champ

### Test 3 : Valeurs extrêmes
- Poids : `1000`
- **Résultat attendu** : "Poids invalide (1-500 kg)"

### Test 4 : Maigreur
- Poids : `45`
- Taille : `170`
- **Résultat attendu** : IMC = 15.6, Catégorie "Maigreur" (bleu)

### Test 5 : Obésité
- Poids : `100`
- Taille : `170`
- **Résultat attendu** : IMC = 34.6, Catégorie "Obésité" (rouge)

---

## 💡 Améliorations possibles

1. **Unités** : Ajouter un switch kg/lbs et cm/inches
2. **Historique** : Sauvegarder les calculs (SharedPreferences)
3. **Graphique** : Courbe d'évolution de l'IMC dans le temps
4. **Conseils** : Afficher des recommandations personnalisées
5. **Partage** : Partager le résultat (Intent.ACTION_SEND)
6. **Animation** : Animer l'apparition du résultat
7. **Âge/Sexe** : Affiner le calcul selon le profil

---

## 📖 Concepts Android utilisés

✅ **TextInputLayout** : Champs de saisie Material Design  
✅ **CardView** : Cartes avec ombre  
✅ **Validation** : Vérification des entrées  
✅ **Visibilité dynamique** : VISIBLE / GONE  
✅ **Couleurs programmatiques** : Color.parseColor(), Color.argb()  
✅ **String formatting** : String.format()  
✅ **Exception handling** : try-catch NumberFormatException  

---

🎓 **Projet pédagogique** - Module 4 - ISITCOM 2025/2026
