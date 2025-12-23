# TD03 : Navigation et Intents

## 🎯 Objectifs

- Naviguer entre plusieurs Activities.
- Passer des données avec Intent.
- Utiliser Activity Result API.

---

## Partie 1 : Application multi-écrans (45 min)

### Consignes

Créer une application "Mon Profil" avec 3 écrans :

1. **MainActivity** :
   - Bouton "Voir Profil" → `ProfileActivity`.
   - Bouton "Paramètres" → `SettingsActivity`.

2. **ProfileActivity** :
   - Afficher : Nom, Âge, Ville.
   - Bouton "Modifier" → `EditProfileActivity`.
   - Bouton "Retour".

3. **EditProfileActivity** :
   - Champs pour modifier Nom, Âge, Ville.
   - Bouton "Enregistrer" → retour à `ProfileActivity` avec nouvelles données.

### Code exemple : MainActivity.java

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("NAME", "Ahmed Ben Ali");
            intent.putExtra("AGE", 22);
            intent.putExtra("CITY", "Sousse");
            startActivity(intent);
        });

        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
```

---

## Partie 2 : Activity Result API (45 min)

### Consignes

Dans `ProfileActivity` :

1. Récupérer les données de `MainActivity`.
2. Afficher dans des `TextView`.
3. Bouton "Modifier" lance `EditProfileActivity`.
4. Utiliser **Activity Result API** pour récupérer les données modifiées.
5. Mettre à jour l'affichage.

### Code exemple : ProfileActivity.java

```java
public class ProfileActivity extends AppCompatActivity {

    private TextView textName, textAge, textCity;
    private ActivityResultLauncher<Intent> editLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textName = findViewById(R.id.textName);
        textAge = findViewById(R.id.textAge);
        textCity = findViewById(R.id.textCity);
        Button btnEdit = findViewById(R.id.btnEdit);

        // Récupérer les données
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        int age = intent.getIntExtra("AGE", 0);
        String city = intent.getStringExtra("CITY");

        textName.setText("Nom : " + name);
        textAge.setText("Âge : " + age);
        textCity.setText("Ville : " + city);

        // Activity Result Launcher
        editLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    String newName = data.getStringExtra("NAME");
                    int newAge = data.getIntExtra("AGE", 0);
                    String newCity = data.getStringExtra("CITY");

                    textName.setText("Nom : " + newName);
                    textAge.setText("Âge : " + newAge);
                    textCity.setText("Ville : " + newCity);
                }
            }
        );

        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(this, EditProfileActivity.class);
            editIntent.putExtra("NAME", name);
            editIntent.putExtra("AGE", age);
            editIntent.putExtra("CITY", city);
            editLauncher.launch(editIntent);
        });
    }
}
```

### Code exemple : EditProfileActivity.java

```java
public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editAge, editCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editCity = findViewById(R.id.editCity);
        Button btnSave = findViewById(R.id.btnSave);

        // Remplir avec données actuelles
        Intent intent = getIntent();
        editName.setText(intent.getStringExtra("NAME"));
        editAge.setText(String.valueOf(intent.getIntExtra("AGE", 0)));
        editCity.setText(intent.getStringExtra("CITY"));

        btnSave.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NAME", editName.getText().toString());
            resultIntent.putExtra("AGE", Integer.parseInt(editAge.getText().toString()));
            resultIntent.putExtra("CITY", editCity.getText().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
```

---

## Partie 3 : Intents implicites (20 min)

### Consignes

Ajouter dans `ProfileActivity` :

1. Bouton "Appeler" → ouvre le composeur avec un numéro.
2. Bouton "Partager" → partage le profil par SMS/Email.

### Code exemple

```java
// Appeler
Button btnCall = findViewById(R.id.btnCall);
btnCall.setOnClickListener(v -> {
    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
    dialIntent.setData(Uri.parse("tel:+21612345678"));
    startActivity(dialIntent);
});

// Partager
Button btnShare = findViewById(R.id.btnShare);
btnShare.setOnClickListener(v -> {
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, "Profil : " + name + ", " + age + " ans, " + city);
    startActivity(Intent.createChooser(shareIntent, "Partager via"));
});
```

---

## 📄 Livrable

- Projet avec 4 Activities.
- Vidéo de démonstration (1 min).

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Navigation fonctionnelle | 5 |
| Passage de données | 5 |
| Activity Result API | 6 |
| Intents implicites | 4 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
