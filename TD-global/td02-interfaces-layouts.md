# TD02 : Interfaces et Layouts - VERSION CORRIGÉE

## 🎯 Objectifs

- Maîtriser ConstraintLayout
- Utiliser différents widgets (TextView, EditText, Button, ImageView)
- Créer une interface utilisateur complète
- Valider des formulaires

---

## Partie 1 : Formulaire d'inscription (45 min)

### Consignes

Créer un écran d'inscription avec :

1. **Logo** en haut (ImageView)
2. **Titre** : "Créer un compte" (TextView, 24sp, bold)
3. **Champs de saisie** (EditText) :
   - Nom complet
   - Email
   - Mot de passe (inputType="textPassword")
   - Confirmation mot de passe
4. **Bouton** : "S'inscrire"
5. **TextView** en bas : "Déjà inscrit ? Connexion" (cliquable)

### Contraintes de design

- Utiliser **ConstraintLayout**
- Marges : 16dp
- Tous les champs doivent avoir la même largeur (0dp avec contraintes)
- Le bouton doit être centré horizontalement

---

### Code complet : activity_register.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Logo -->
    <ImageView
        android:id="@+id/imageLogo"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/ic_launcher_foreground"
        android:contentDescription="Logo"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <!-- Titre -->
    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Créer un compte"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="@android:color/black"
        app:layout_constraintTop_toBottomOf="@id/imageLogo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Champ Nom complet -->
    <EditText
        android:id="@+id/editName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        android:inputType="textPersonName"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/textTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Champ Email -->
    <EditText
        android:id="@+id/editEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:inputType="textEmailAddress"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Champ Mot de passe -->
    <EditText
        android:id="@+id/editPassword"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Mot de passe"
        android:inputType="textPassword"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editEmail"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Champ Confirmation mot de passe -->
    <EditText
        android:id="@+id/editConfirmPassword"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Confirmer mot de passe"
        android:inputType="textPassword"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editPassword"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Bouton S'inscrire -->
    <Button
        android:id="@+id/btnRegister"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="S'inscrire"
        android:textSize="16sp"
        app:layout_constraintTop_toBottomOf="@id/editConfirmPassword"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Lien Connexion -->
    <TextView
        android:id="@+id/textLogin"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Déjà inscrit ? Connexion"
        android:textColor="@android:color/holo_blue_dark"
        android:textSize="14sp"
        app:layout_constraintTop_toBottomOf="@id/btnRegister"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## Partie 2 : Validation du formulaire (30 min)

### Consignes

Dans `RegisterActivity.java` :

1. Récupérer les valeurs des champs au clic sur "S'inscrire"
2. Vérifier :
   - Aucun champ vide
   - Email contient "@"
   - Mot de passe ≥ 6 caractères
   - Les deux mots de passe correspondent
3. Si erreur : afficher un Toast avec le message d'erreur
4. Si valide : afficher "Inscription réussie !"

---

### Code complet : RegisterActivity.java

```java
package tn.isitcom.td02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword, editConfirmPassword;
    private Button btnRegister;
    private TextView textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialisation des vues
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        textLogin = findViewById(R.id.textLogin);

        // Listener bouton inscription
        btnRegister.setOnClickListener(v -> validateForm());

        // Listener lien connexion
        textLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Redirection vers connexion", Toast.LENGTH_SHORT).show();
            // Intent vers LoginActivity si elle existe
        });
    }

    private void validateForm() {
        // Récupération des valeurs
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        // Vérification champs vides
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "❌ Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification longueur nom
        if (name.length() < 3) {
            Toast.makeText(this, "❌ Le nom doit contenir au moins 3 caractères", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification email
        if (!email.contains("@") || !email.contains(".")) {
            Toast.makeText(this, "❌ Email invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification longueur mot de passe
        if (password.length() < 6) {
            Toast.makeText(this, "❌ Le mot de passe doit contenir au moins 6 caractères", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérification correspondance mots de passe
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "❌ Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validation réussie
        Toast.makeText(this, "✅ Inscription réussie ! Bienvenue " + name, Toast.LENGTH_LONG).show();
        
        // Ici on pourrait sauvegarder les données ou rediriger vers l'accueil
        clearFields();
    }

    private void clearFields() {
        editName.setText("");
        editEmail.setText("");
        editPassword.setText("");
        editConfirmPassword.setText("");
    }
}
```

---

## Partie 3 : Écran de profil (30 min)

### Consignes

Créer un écran `ProfileActivity` affichant :

1. Photo de profil (ImageView circulaire)
2. Nom
3. Email
4. Bouton "Modifier"

---

### Code complet : activity_profile.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Photo de profil -->
    <ImageView
        android:id="@+id/imageProfile"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_launcher_foreground"
        android:background="@android:drawable/btn_star_big_on"
        android:contentDescription="Photo de profil"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <!-- Card contenant les informations -->
    <androidx.cardview.widget.CardView
        android:id="@+id/cardInfo"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:cardElevation="4dp"
        app:cardCornerRadius="8dp"
        app:layout_constraintTop_toBottomOf="@id/imageProfile"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="16dp">

            <!-- Nom -->
            <TextView
                android:id="@+id/textName"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ahmed Ben Ali"
                android:textSize="20sp"
                android:textStyle="bold"
                android:textColor="@android:color/black" />

            <!-- Email -->
            <TextView
                android:id="@+id/textEmail"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="ahmed.benali@isitcom.tn"
                android:textSize="16sp"
                android:textColor="@android:color/darker_gray"
                android:layout_marginTop="8dp" />

            <!-- Bio -->
            <TextView
                android:id="@+id/textBio"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Étudiant en informatique à l'ISITCOM"
                android:textSize="14sp"
                android:layout_marginTop="12dp" />

        </LinearLayout>

    </androidx.cardview.widget.CardView>

    <!-- Bouton Modifier -->
    <Button
        android:id="@+id/btnEdit"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Modifier le profil"
        app:layout_constraintTop_toBottomOf="@id/cardInfo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### Code : ProfileActivity.java

```java
package tn.isitcom.td02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName, textEmail, textBio;
    private Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textName = findViewById(R.id.textName);
        textEmail = findViewById(R.id.textEmail);
        textBio = findViewById(R.id.textBio);
        btnEdit = findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(v -> {
            Toast.makeText(this, "Fonction de modification à implémenter", Toast.LENGTH_SHORT).show();
        });
    }
}
```

---

## Bonus : Améliorations

### 1. Photo de profil circulaire

Utiliser la bibliothèque Glide ou créer un drawable circulaire.

### 2. Icônes Material

Ajouter des icônes devant chaque information :
- `@drawable/ic_person` pour le nom
- `@drawable/ic_email` pour l'email

### 3. Validation email avancée

```java
private boolean isValidEmail(String email) {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
}
```

---

## 📝 Livrable

- Projet Android Studio avec 2 Activities (Register + Profile)
- Captures d'écran des 2 écrans
- Code fonctionnel et testé

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Formulaire inscription complet et conforme | 5 |
| Validation fonctionnelle avec tous les contrôles | 5 |
| Interface soignée (marges, tailles, couleurs) | 3 |
| Écran profil avec CardView | 4 |
| Code propre et commenté | 2 |
| Gestion des clics (lien connexion, bouton modifier) | 1 |

---

## 📚 Ressources

- [ConstraintLayout Guide](https://developer.android.com/training/constraint-layout)
- [Material EditText](https://material.io/components/text-fields)
- [CardView Documentation](https://developer.android.com/guide/topics/ui/layout/cardview)

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
