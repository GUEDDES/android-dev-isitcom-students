# TD03 : Cycle de vie et Intents

## 🎯 Objectifs

- Comprendre le cycle de vie d'une Activity.
- Naviguer entre plusieurs écrans.
- Passer des données entre Activities.

---

## Partie 1 : Cycle de vie (30 min)

### Exercice 1.1 : Logger le cycle de vie

Dans `MainActivity.java`, surcharger tous les callbacks :

```java
private static final String TAG = "Lifecycle";

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.d(TAG, "onCreate appelé");
}

@Override
protected void onStart() {
    super.onStart();
    Log.d(TAG, "onStart appelé");
}

// ... onResume, onPause, onStop, onDestroy
```

Observer Logcat lors de :
- Lancement de l'app.
- Appui sur Home.
- Retour à l'app.
- Rotation de l'écran.

**Questions** :
1. Quel est l'ordre des callbacks au lancement ?
2. Que se passe-t-il lors de la rotation ?

---

## Partie 2 : Navigation simple (45 min)

### Exercice 2.1 : Créer une deuxième Activity

1. New → Activity → Empty Views Activity.
2. Nom : `SecondActivity`.
3. Dans `MainActivity`, ajouter un bouton :

```java
Button btnGo = findViewById(R.id.btnGo);
btnGo.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
    startActivity(intent);
});
```

### Exercice 2.2 : Retour avec bouton

Dans `SecondActivity` :

```java
Button btnBack = findViewById(R.id.btnBack);
btnBack.setOnClickListener(v -> finish());
```

---

## Partie 3 : Passage de données (45 min)

### Exercice 3.1 : Envoyer des données

Dans `MainActivity` :

```java
Intent intent = new Intent(this, ProfileActivity.class);
intent.putExtra("NAME", "Ahmed");
intent.putExtra("AGE", 22);
startActivity(intent);
```

Dans `ProfileActivity` :

```java
String name = getIntent().getStringExtra("NAME");
int age = getIntent().getIntExtra("AGE", 0);

TextView textInfo = findViewById(R.id.textInfo);
textInfo.setText("Nom: " + name + "\nÂge: " + age);
```

---

## Partie 4 : Activity Result API (45 min)

### Exercice 4.1 : Sélection de couleur

**MainActivity** lance `ColorPickerActivity` et reçoit la couleur :

```java
private ActivityResultLauncher<Intent> colorPickerLauncher;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    View rootView = findViewById(R.id.rootView);

    colorPickerLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    int color = data.getIntExtra("COLOR", Color.WHITE);
                    rootView.setBackgroundColor(color);
                }
            }
        }
    );

    Button btnPickColor = findViewById(R.id.btnPickColor);
    btnPickColor.setOnClickListener(v -> {
        Intent intent = new Intent(this, ColorPickerActivity.class);
        colorPickerLauncher.launch(intent);
    });
}
```

**ColorPickerActivity** retourne la couleur :

```java
Button btnRed = findViewById(R.id.btnRed);
btnRed.setOnClickListener(v -> {
    Intent resultIntent = new Intent();
    resultIntent.putExtra("COLOR", Color.RED);
    setResult(RESULT_OK, resultIntent);
    finish();
});
```

---

## 🎯 TP Noté : Application multi-écrans (/20)

### Consignes

Créer une application avec 3 écrans :

1. **Écran 1 – Accueil** :
   - Titre "Bienvenue".
   - 2 boutons : "Profil" et "Paramètres".

2. **Écran 2 – Profil** :
   - Champs : nom, prénom, âge.
   - Bouton "Enregistrer" qui retourne à l'accueil et affiche un Toast de confirmation.

3. **Écran 3 – Paramètres** :
   - Switch "Notifications".
   - Spinner "Langue" (Français, Anglais, Arabe).
   - Bouton "Appliquer" qui retourne à l'accueil.

### Barème

| Critère | Points |
|---------|--------|
| 3 Activities créées | 3 |
| Navigation fonctionnelle | 4 |
| Passage de données | 4 |
| Activity Result API | 4 |
| Interface cohérente | 3 |
| Code propre | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
