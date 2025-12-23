# Module 5 : Activités, cycle de vie et Intents

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Comprendre le cycle de vie d'une Activity et ses callbacks.
- Naviguer entre plusieurs écrans avec les Intents.
- Passer des données entre Activities.
- Récupérer un résultat d'une Activity (Activity Result API).

---

## 1. Le cycle de vie d'une Activity

### 1.1 États principaux

Une Activity passe par plusieurs états durant son existence : 

- **Created** : l'Activity vient d'être créée.
- **Started** : l'Activity devient visible.
- **Resumed** : l'Activity est au premier plan et interactive.
- **Paused** : l'Activity est partiellement masquée (ex : dialogue par-dessus).
- **Stopped** : l'Activity est complètement cachée.
- **Destroyed** : l'Activity est détruite.

### 1.2 Callbacks essentiels

Le système Android appelle automatiquement ces méthodes : 

| Callback | Rôle |
|---------|------|
| `onCreate()` | Initialisation (layout, vues, données) |
| `onStart()` | Activity devient visible |
| `onResume()` | Activity au premier plan (interactive) |
| `onPause()` | Activity va être masquée (sauvegarder données temporaires) |
| `onStop()` | Activity complètement cachée |
| `onDestroy()` | Nettoyage final (fermeture connexions, threads) |

### 1.3 Exemple minimal

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Lifecycle", "onCreate appelé");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecycle", "onStart appelé");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecycle", "onResume appelé");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecycle", "onPause appelé");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecycle", "onStop appelé");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecycle", "onDestroy appelé");
    }
}
```

> Toujours appeler `super.` dans chaque callback.

---

## 2. Navigation avec les Intents

### 2.1 Intent explicite : naviguer vers une Activity spécifique

Un **Intent explicite** désigne précisément la classe cible. 

**Exemple** : passer de `MainActivity` à `SecondActivity`.

```java
// Dans MainActivity
Button btnGo = findViewById(R.id.btnGo);
btnGo.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
    startActivity(intent);
});
```

**Déclaration dans AndroidManifest.xml** :

```xml
<activity android:name=".SecondActivity" />
```

### 2.2 Intent implicite : action générique

Un **Intent implicite** ne précise pas la classe mais une **action** (ouvrir URL, appeler, partager...). 

**Exemples :**

```java
// Ouvrir une URL
Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://isitcom.rnu.tn"));
startActivity(webIntent);

// Composer un numéro
Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+21612345678"));
startActivity(dialIntent);

// Partager du texte
Intent shareIntent = new Intent(Intent.ACTION_SEND);
shareIntent.setType("text/plain");
shareIntent.putExtra(Intent.EXTRA_TEXT, "Texte à partager");
startActivity(Intent.createChooser(shareIntent, "Partager via"));
```

---

## 3. Passer des données entre Activities

### 3.1 Envoyer des données

Utiliser `putExtra` pour ajouter des paires clé-valeur : 

```java
Intent intent = new Intent(this, ProfileActivity.class);
intent.putExtra("USER_NAME", "Ahmed");
intent.putExtra("USER_AGE", 22);
intent.putExtra("IS_ADMIN", false);
startActivity(intent);
```

### 3.2 Récupérer les données

Dans `ProfileActivity` :

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    Intent intent = getIntent();
    String name = intent.getStringExtra("USER_NAME");
    int age = intent.getIntExtra("USER_AGE", 0);
    boolean isAdmin = intent.getBooleanExtra("IS_ADMIN", false);

    TextView textInfo = findViewById(R.id.textInfo);
    textInfo.setText("Nom: " + name + ", Age: " + age);
}
```

---

## 4. Activity Result API (moderne 2025)

### 4.1 Problème de `startActivityForResult` (déprécié)

`startActivityForResult` est obsolète depuis Android 11 (API 30). 

Il fallait gérer un code de requête global et un callback `onActivityResult` unique.

### 4.2 Solution moderne : `ActivityResultLauncher`

Déclarer un launcher avant `onCreate` : 

```java
public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> editProfileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Enregistrer le launcher
        editProfileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        String newName = data.getStringExtra("NEW_NAME");
                        Toast.makeText(this, "Nouveau nom: " + newName, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );

        // 2. Lancer l'Activity quand nécessaire
        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            editProfileLauncher.launch(intent);
        });
    }
}
```

### 4.3 Retourner un résultat

Dans `EditProfileActivity` :

```java
Button btnSave = findViewById(R.id.btnSave);
btnSave.setOnClickListener(v -> {
    Intent resultIntent = new Intent();
    resultIntent.putExtra("NEW_NAME", "Ahmed Updated");
    setResult(RESULT_OK, resultIntent);
    finish();
});

Button btnCancel = findViewById(R.id.btnCancel);
btnCancel.setOnClickListener(v -> {
    setResult(RESULT_CANCELED);
    finish();
});
```

---

## 5. Atelier guidé : Application multi-écrans

### Objectif

Créer une application avec 3 écrans :

1. **MainActivity** : écran d'accueil avec 2 boutons.
2. **ProfileActivity** : affiche un profil utilisateur.
3. **SettingsActivity** : affiche des paramètres fictifs.

### Étapes

1. Créer 3 Activities (New → Activity → Empty Views Activity).
2. Dans `MainActivity`, ajouter 2 boutons :
   - "Profil" → `ProfileActivity`.
   - "Paramètres" → `SettingsActivity`.
3. Passer le nom d'utilisateur depuis `MainActivity` vers `ProfileActivity`.
4. Afficher ce nom dans un `TextView` de `ProfileActivity`.

---

## 6. Exercices pratiques (Module 5)

### Exercice 1 – Logger le cycle de vie

1. Dans une Activity, surcharger tous les callbacks (`onCreate`, `onStart`, etc.).
2. Ajouter des `Log.d("Lifecycle", "...")` dans chacun.
3. Observer Logcat lors de :
   - Lancement de l'app.
   - Appui sur Home.
   - Retour à l'app.
   - Rotation de l'écran.

### Exercice 2 – Navigation simple

1. Créer une deuxième Activity.
2. Ajouter un bouton dans la première qui lance la deuxième.
3. Ajouter un bouton "Retour" dans la deuxième qui appelle `finish()`.

### Exercice 3 – Passage de données

1. Créer un champ `EditText` dans `MainActivity` pour saisir un prénom.
2. Un bouton "Valider" lance `GreetingActivity`.
3. `GreetingActivity` affiche "Bonjour [prénom]".

### Exercice 4 – Retour avec résultat

1. `MainActivity` lance `ChooseColorActivity`.
2. `ChooseColorActivity` affiche 3 boutons (rouge, vert, bleu).
3. Au clic sur un bouton, retourner la couleur choisie à `MainActivity`.
4. `MainActivity` change la couleur de fond de l'écran en fonction du résultat.

---

## 7. Mini-TP : Application de profil utilisateur

### Consignes

Créer une application avec 2 écrans :

1. **Écran 1 – Saisie** :
   - Champs : nom, prénom, âge (EditText).
   - Bouton "Valider".

2. **Écran 2 – Affichage** :
   - Afficher les informations saisies.
   - Bouton "Modifier" qui retourne à l'écran 1 (avec Activity Result API).
   - Si modification, mettre à jour l'affichage.

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| Navigation fonctionnelle | 4 |
| Passage de données correct | 4 |
| Activity Result API utilisée | 4 |
| Interface claire | 4 |
| Code propre et commenté | 4 |

**Total** : /20

---

## 8. Erreurs fréquentes

- **Activity non déclarée dans le Manifest**  
  → Crash au lancement, vérifier `<activity>` dans `AndroidManifest.xml`.

- **`NullPointerException` sur `getIntent().getStringExtra()`**  
  → Vérifier que la clé correspond exactement et fournir une valeur par défaut.

- **Utiliser `startActivityForResult` en 2025**  
  → Passer à Activity Result API (launcher).

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
