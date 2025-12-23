# TD02 : Interfaces et Layouts

## 🎯 Objectifs

- Maîtriser ConstraintLayout.
- Utiliser différents widgets (TextView, EditText, Button, ImageView).
- Créer une interface utilisateur complète.

---

## Partie 1 : Formulaire d'inscription (45 min)

### Consignes

Créer un écran d'inscription avec :

1. **Logo** en haut (ImageView).
2. **Titre** : "Créer un compte" (TextView, 24sp, bold).
3. **Champs de saisie** (EditText) :
   - Nom complet.
   - Email.
   - Mot de passe (inputType="textPassword").
   - Confirmation mot de passe.
4. **Bouton** : "S'inscrire".
5. **TextView** en bas : "Déjà inscrit ? Connexion" (cliquable).

### Contraintes de design

- Utiliser **ConstraintLayout**.
- Marges : 16dp.
- Tous les champs doivent avoir la même largeur (match_parent avec marges).
- Le bouton doit être centré horizontalement.

### Code exemple : activity_register.xml

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <ImageView
        android:id="@+id/imageLogo"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:src="@drawable/ic_launcher_foreground"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Créer un compte"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toBottomOf="@id/imageLogo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <EditText
        android:id="@+id/editName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        app:layout_constraintTop_toBottomOf="@id/textTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <!-- Autres champs similaires -->

    <Button
        android:id="@+id/btnRegister"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="S'inscrire"
        app:layout_constraintTop_toBottomOf="@id/editConfirmPassword"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## Partie 2 : Validation du formulaire (30 min)

### Consignes

Dans `RegisterActivity.java` :

1. Récupérer les valeurs des champs au clic sur "S'inscrire".
2. Vérifier :
   - Aucun champ vide.
   - Email contient "@".
   - Mot de passe ≥ 6 caractères.
   - Les deux mots de passe correspondent.
3. Si erreur : afficher un Toast avec le message d'erreur.
4. Si valide : afficher "Inscription réussie !".

### Code exemple

```java
public class RegisterActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword, editConfirmPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> validateForm());
    }

    private void validateForm() {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString();
        String confirmPassword = editConfirmPassword.getText().toString();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!email.contains("@")) {
            Toast.makeText(this, "Email invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Mot de passe trop court", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Inscription réussie !", Toast.LENGTH_LONG).show();
    }
}
```

---

## Partie 3 : Écran de profil (30 min)

### Consignes

Créer un écran `ProfileActivity` affichant :

1. Photo de profil (ImageView circulaire).
2. Nom.
3. Email.
4. Bouton "Modifier".

### Bonus

- Ajouter un `CardView` pour encadrer les informations.
- Utiliser des icônes pour chaque information.

---

## 📄 Livrable

- Projet Android Studio avec 2 Activities.
- Captures d'écran des 2 écrans.

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Formulaire complet | 6 |
| Validation fonctionnelle | 6 |
| Interface soignée | 4 |
| Écran profil | 4 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
