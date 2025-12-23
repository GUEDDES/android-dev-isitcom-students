# Corrections - Exercices Module 4

## Exercice 1 : Carte de profil

### Solution XML (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Nom -->
    <TextView
        android:id="@+id/tv_nom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Jean Dupont"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="@android:color/black"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintVertical_bias="0.35" />

    <!-- Email -->
    <TextView
        android:id="@+id/tv_email"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="jean.dupont@isitcom.tn"
        android:textSize="16sp"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@id/tv_nom"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Téléphone -->
    <TextView
        android:id="@+id/tv_telephone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="+216 12 345 678"
        android:textSize="16sp"
        android:layout_marginTop="8dp"
        app:layout_constraintTop_toBottomOf="@id/tv_email"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Bouton Contact -->
    <Button
        android:id="@+id/btn_contact"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contact"
        android:layout_marginTop="24dp"
        app:layout_constraintTop_toBottomOf="@id/tv_telephone"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Solution Java (MainActivity.java)

```java
package tn.isitcom.carteprofil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvEmail;
    private Button btnContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des vues
        tvEmail = findViewById(R.id.tv_email);
        btnContact = findViewById(R.id.btn_contact);

        // Gestion du clic sur le bouton
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tvEmail.getText().toString();
                Toast.makeText(MainActivity.this, 
                    "Email: " + email, 
                    Toast.LENGTH_LONG).show();
            }
        });
    }
}
```

### Version alternative avec lambda (Java 8+)

```java
btnContact.setOnClickListener(v -> {
    String email = tvEmail.getText().toString();
    Toast.makeText(this, "Email: " + email, Toast.LENGTH_LONG).show();
});
```

---

## Exercice 2 : Calculatrice simple

### Solution XML (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp">

    <!-- Premier nombre -->
    <EditText
        android:id="@+id/et_nombre1"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nombre 1"
        android:inputType="numberDecimal|numberSigned"
        android:textSize="18sp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <!-- Deuxième nombre -->
    <EditText
        android:id="@+id/et_nombre2"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nombre 2"
        android:inputType="numberDecimal|numberSigned"
        android:textSize="18sp"
        app:layout_constraintTop_toBottomOf="@id/et_nombre1"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Boutons opérations -->
    <LinearLayout
        android:id="@+id/layout_buttons"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:gravity="center"
        app:layout_constraintTop_toBottomOf="@id/et_nombre2"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp">

        <Button
            android:id="@+id/btn_add"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="+"
            android:textSize="20sp"
            android:layout_margin="4dp" />

        <Button
            android:id="@+id/btn_subtract"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="-"
            android:textSize="20sp"
            android:layout_margin="4dp" />

        <Button
            android:id="@+id/btn_multiply"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="×"
            android:textSize="20sp"
            android:layout_margin="4dp" />

        <Button
            android:id="@+id/btn_divide"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="÷"
            android:textSize="20sp"
            android:layout_margin="4dp" />
    </LinearLayout>

    <!-- Résultat -->
    <TextView
        android:id="@+id/tv_resultat"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Résultat : "
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="@android:color/holo_blue_dark"
        android:gravity="center"
        android:padding="16dp"
        android:background="#E3F2FD"
        app:layout_constraintTop_toBottomOf="@id/layout_buttons"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Solution Java (MainActivity.java)

```java
package tn.isitcom.calculatrice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNombre1, etNombre2;
    private TextView tvResultat;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des vues
        etNombre1 = findViewById(R.id.et_nombre1);
        etNombre2 = findViewById(R.id.et_nombre2);
        tvResultat = findViewById(R.id.tv_resultat);
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);

        // Listeners
        btnAdd.setOnClickListener(v -> calculer('+'));
        btnSubtract.setOnClickListener(v -> calculer('-'));
        btnMultiply.setOnClickListener(v -> calculer('*'));
        btnDivide.setOnClickListener(v -> calculer('/'));
    }

    /**
     * Effectue le calcul selon l'opération choisie
     */
    private void calculer(char operation) {
        // Vérification champs vides
        String str1 = etNombre1.getText().toString();
        String str2 = etNombre2.getText().toString();

        if (str1.isEmpty() || str2.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir les deux champs", 
                Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // Conversion en nombres
            double nombre1 = Double.parseDouble(str1);
            double nombre2 = Double.parseDouble(str2);
            double resultat = 0;

            // Calcul selon opération
            switch (operation) {
                case '+':
                    resultat = nombre1 + nombre2;
                    break;
                case '-':
                    resultat = nombre1 - nombre2;
                    break;
                case '*':
                    resultat = nombre1 * nombre2;
                    break;
                case '/':
                    // Gestion division par zéro
                    if (nombre2 == 0) {
                        Toast.makeText(this, 
                            "Division par zéro impossible !", 
                            Toast.LENGTH_LONG).show();
                        return;
                    }
                    resultat = nombre1 / nombre2;
                    break;
            }

            // Affichage du résultat
            tvResultat.setText(String.format("Résultat : %.2f", resultat));

        } catch (NumberFormatException e) {
            Toast.makeText(this, 
                "Veuillez entrer des nombres valides", 
                Toast.LENGTH_SHORT).show();
        }
    }
}
```

### Points clés de la solution

✅ **Validation** : Vérification champs vides  
✅ **Gestion erreurs** : Try-catch pour NumberFormatException  
✅ **Division par zéro** : Vérification explicite  
✅ **Formatage** : Résultat avec 2 décimales  
✅ **Feedback utilisateur** : Toast pour erreurs  

---

## Exercice 3 : Formulaire d'inscription

### Solution XML (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="24dp"
        android:gravity="center_horizontal">

        <!-- Titre -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Inscription"
            android:textSize="28sp"
            android:textStyle="bold"
            android:layout_marginBottom="32dp" />

        <!-- Nom complet -->
        <EditText
            android:id="@+id/et_nom"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Nom complet"
            android:inputType="textPersonName"
            android:layout_marginBottom="16dp" />

        <!-- Email -->
        <EditText
            android:id="@+id/et_email"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email"
            android:inputType="textEmailAddress"
            android:layout_marginBottom="16dp" />

        <!-- Mot de passe -->
        <EditText
            android:id="@+id/et_password"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Mot de passe"
            android:inputType="textPassword"
            android:layout_marginBottom="16dp" />

        <!-- Confirmation mot de passe -->
        <EditText
            android:id="@+id/et_password_confirm"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Confirmer le mot de passe"
            android:inputType="textPassword"
            android:layout_marginBottom="24dp" />

        <!-- Checkbox conditions -->
        <CheckBox
            android:id="@+id/cb_conditions"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="J'accepte les conditions d'utilisation"
            android:layout_marginBottom="24dp" />

        <!-- Bouton inscription -->
        <Button
            android:id="@+id/btn_inscrire"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="S'inscrire"
            android:textSize="18sp" />

    </LinearLayout>
</ScrollView>
```

### Solution Java (MainActivity.java)

```java
package tn.isitcom.inscription;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etEmail, etPassword, etPasswordConfirm;
    private CheckBox cbConditions;
    private Button btnInscrire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        etNom = findViewById(R.id.et_nom);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etPasswordConfirm = findViewById(R.id.et_password_confirm);
        cbConditions = findViewById(R.id.cb_conditions);
        btnInscrire = findViewById(R.id.btn_inscrire);

        // Listener bouton
        btnInscrire.setOnClickListener(v -> validerInscription());
    }

    /**
     * Valide le formulaire d'inscription
     */
    private void validerInscription() {
        String nom = etNom.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String passwordConfirm = etPasswordConfirm.getText().toString();

        // Validation 1 : Champs vides
        if (nom.isEmpty()) {
            etNom.setError("Le nom est requis");
            etNom.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etEmail.setError("L'email est requis");
            etEmail.requestFocus();
            return;
        }

        // Validation 2 : Email valide
        if (!email.contains("@")) {
            etEmail.setError("Email invalide");
            etEmail.requestFocus();
            return;
        }

        // Validation 3 : Mot de passe
        if (password.isEmpty()) {
            etPassword.setError("Le mot de passe est requis");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Minimum 6 caractères");
            etPassword.requestFocus();
            return;
        }

        // Validation 4 : Mots de passe identiques
        if (!password.equals(passwordConfirm)) {
            etPasswordConfirm.setError("Les mots de passe ne correspondent pas");
            etPasswordConfirm.requestFocus();
            return;
        }

        // Validation 5 : Conditions acceptées
        if (!cbConditions.isChecked()) {
            Toast.makeText(this, 
                "Veuillez accepter les conditions", 
                Toast.LENGTH_SHORT).show();
            return;
        }

        // Succès
        Toast.makeText(this, 
            "Inscription réussie ! Bienvenue " + nom, 
            Toast.LENGTH_LONG).show();
        
        // Réinitialiser le formulaire
        etNom.setText("");
        etEmail.setText("");
        etPassword.setText("");
        etPasswordConfirm.setText("");
        cbConditions.setChecked(false);
    }
}
```

### Amélioration : Validation email avancée

```java
private boolean isEmailValide(String email) {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
}

// Utilisation
if (!isEmailValide(email)) {
    etEmail.setError("Format email invalide");
    return;
}
```

---

## Exercice 4 : Galerie d'images

### Solution XML (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <!-- Titre -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Ma Galerie"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_gravity="center"
            android:layout_marginBottom="16dp" />

        <!-- Image 1 -->
        <ImageView
            android:id="@+id/image1"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/photo1"
            android:scaleType="centerCrop"
            android:contentDescription="Photo montagne"
            android:layout_marginBottom="8dp" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Montagne majestueuse"
            android:textSize="16sp"
            android:layout_marginBottom="16dp" />

        <!-- Image 2 -->
        <ImageView
            android:id="@+id/image2"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/photo2"
            android:scaleType="centerCrop"
            android:contentDescription="Photo plage"
            android:layout_marginBottom="8dp" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Plage paradisiaque"
            android:textSize="16sp"
            android:layout_marginBottom="16dp" />

        <!-- Image 3 -->
        <ImageView
            android:id="@+id/image3"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/photo3"
            android:scaleType="centerCrop"
            android:contentDescription="Photo forêt"
            android:layout_marginBottom="8dp" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Forêt luxuriante"
            android:textSize="16sp"
            android:layout_marginBottom="16dp" />

        <!-- Image 4 -->
        <ImageView
            android:id="@+id/image4"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/photo4"
            android:scaleType="centerCrop"
            android:contentDescription="Photo désert"
            android:layout_marginBottom="8dp" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Désert infini"
            android:textSize="16sp"
            android:layout_marginBottom="16dp" />

        <!-- Image 5 -->
        <ImageView
            android:id="@+id/image5"
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:src="@drawable/photo5"
            android:scaleType="centerCrop"
            android:contentDescription="Photo ville"
            android:layout_marginBottom="8dp" />
        
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Ville moderne"
            android:textSize="16sp"
            android:layout_marginBottom="16dp" />

    </LinearLayout>
</ScrollView>
```

### Solution Java (MainActivity.java)

```java
package tn.isitcom.galerie;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération des ImageView
        ImageView image1 = findViewById(R.id.image1);
        ImageView image2 = findViewById(R.id.image2);
        ImageView image3 = findViewById(R.id.image3);
        ImageView image4 = findViewById(R.id.image4);
        ImageView image5 = findViewById(R.id.image5);

        // Listeners
        image1.setOnClickListener(v -> afficherNom("Montagne majestueuse"));
        image2.setOnClickListener(v -> afficherNom("Plage paradisiaque"));
        image3.setOnClickListener(v -> afficherNom("Forêt luxuriante"));
        image4.setOnClickListener(v -> afficherNom("Désert infini"));
        image5.setOnClickListener(v -> afficherNom("Ville moderne"));
    }

    private void afficherNom(String nom) {
        Toast.makeText(this, nom, Toast.LENGTH_SHORT).show();
    }
}
```

### Version optimisée avec tableau

```java
public class MainActivity extends AppCompatActivity {

    private static final int[] IMAGE_IDS = {
        R.id.image1, R.id.image2, R.id.image3, R.id.image4, R.id.image5
    };
    
    private static final String[] IMAGE_NAMES = {
        "Montagne majestueuse",
        "Plage paradisiaque",
        "Forêt luxuriante",
        "Désert infini",
        "Ville moderne"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < IMAGE_IDS.length; i++) {
            ImageView imageView = findViewById(IMAGE_IDS[i]);
            final String nom = IMAGE_NAMES[i];
            imageView.setOnClickListener(v -> 
                Toast.makeText(this, nom, Toast.LENGTH_SHORT).show()
            );
        }
    }
}
```

---

## Mini-projet : Écran de connexion complet

### Solution XML (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp"
    android:background="@android:color/white">

    <!-- Logo -->
    <ImageView
        android:id="@+id/iv_logo"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/logo"
        android:contentDescription="Logo application"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <!-- Titre -->
    <TextView
        android:id="@+id/tv_titre"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Connexion"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="@color/primary"
        app:layout_constraintTop_toBottomOf="@id/iv_logo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Champ Email -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/til_email"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Email"
        app:startIconDrawable="@drawable/ic_email"
        app:layout_constraintTop_toBottomOf="@id/tv_titre"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_email"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textEmailAddress" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Champ Mot de passe -->
    <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/til_password"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Mot de passe"
        app:startIconDrawable="@drawable/ic_lock"
        app:endIconMode="password_toggle"
        app:layout_constraintTop_toBottomOf="@id/til_email"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_password"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword" />
    </com.google.android.material.textfield.TextInputLayout>

    <!-- Checkbox Se souvenir -->
    <CheckBox
        android:id="@+id/cb_remember"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Se souvenir de moi"
        app:layout_constraintTop_toBottomOf="@id/til_password"
        app:layout_constraintStart_toStartOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Bouton Connexion -->
    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_login"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Se connecter"
        android:textSize="16sp"
        android:padding="14dp"
        app:layout_constraintTop_toBottomOf="@id/cb_remember"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Mot de passe oublié -->
    <TextView
        android:id="@+id/tv_forgot_password"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Mot de passe oublié ?"
        android:textColor="@color/primary"
        android:textSize="14sp"
        android:clickable="true"
        android:focusable="true"
        app:layout_constraintTop_toBottomOf="@id/btn_login"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Créer compte -->
    <TextView
        android:id="@+id/tv_create_account"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Créer un compte"
        android:textColor="@color/primary"
        android:textSize="14sp"
        android:textStyle="bold"
        android:clickable="true"
        android:focusable="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### Fichier colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="primary">#6200EE</color>
    <color name="primary_dark">#3700B3</color>
    <color name="accent">#03DAC5</color>
</resources>
```

### Solution Java (MainActivity.java)

```java
package tn.isitcom.connexion;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etEmail, etPassword;
    private CheckBox cbRemember;
    private MaterialButton btnLogin;
    private TextView tvForgotPassword, tvCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        cbRemember = findViewById(R.id.cb_remember);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvCreateAccount = findViewById(R.id.tv_create_account);

        // Listeners
        btnLogin.setOnClickListener(v -> tentativeConnexion());
        tvForgotPassword.setOnClickListener(v -> 
            Toast.makeText(this, "Réinitialisation mot de passe", Toast.LENGTH_SHORT).show()
        );
        tvCreateAccount.setOnClickListener(v -> 
            Toast.makeText(this, "Création de compte", Toast.LENGTH_SHORT).show()
        );
    }

    private void tentativeConnexion() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        // Validation
        if (email.isEmpty()) {
            etEmail.setError("Email requis");
            etEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email invalide");
            etEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Mot de passe requis");
            etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            etPassword.setError("Minimum 6 caractères");
            etPassword.requestFocus();
            return;
        }

        // Succès
        String message = "Connexion réussie !";
        if (cbRemember.isChecked()) {
            message += " (Mémorisé)";
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
```

### Grille d'évaluation

| Critère | Points | Détails |
|---------|--------|----------|
| Interface complète | 5 | Tous les éléments présents |
| Material Components | 4 | TextInputLayout, MaterialButton |
| Code propre | 3 | Commentaires, nommage clair |
| Validation fonctionnelle | 5 | Tous les champs validés |
| Design soigné | 4 | Couleurs, espacements, icônes |
| Gestion erreurs | 3 | Toast et setError() |
| **Total** | **/20** | |

---

👨‍🏫 **Corrections Module 4** | ISITCOM 2025-2026
