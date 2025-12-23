# Module 5 : Fiche de synthèse

## 🎯 Concepts essentiels

### Cycle de vie d'une Activity

```
[onCreate] → [onStart] → [onResume] → [ACTIVE]
                                ↓
[onDestroy] ← [onStop] ← [onPause]
```

---

## 🔄 Méthodes du cycle de vie

| Méthode | Appelée quand | Usage typique |
|---------|--------------|---------------|
| `onCreate()` | Création | Initialisation, setContentView |
| `onStart()` | Visible | Démarrer animations |
| `onResume()` | Interaction possible | Reprendre vidéo/capteurs |
| `onPause()` | Perte focus | Mettre en pause vidéo |
| `onStop()` | Invisible | Libérer ressources lourdes |
| `onDestroy()` | Destruction | Nettoyage final |

---

## 🔗 Navigation entre Activities

### Intent explicite
```java
// Démarrer SecondActivity
Intent intent = new Intent(this, SecondActivity.class);
startActivity(intent);
```

### Passer des données
```java
// Activity 1 : envoyer
Intent intent = new Intent(this, SecondActivity.class);
intent.putExtra("NOM_CLE", "valeur");
intent.putExtra("AGE", 25);
startActivity(intent);

// Activity 2 : recevoir
String nom = getIntent().getStringExtra("NOM_CLE");
int age = getIntent().getIntExtra("AGE", 0);
```

---

## 🔙 Récupérer un résultat (Activity Result API)

### Activity 1 : Lancer et attendre résultat
```java
private ActivityResultLauncher<Intent> launcher;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    // Enregistrer le launcher
    launcher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                String message = data.getStringExtra("MESSAGE");
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    );
    
    // Lancer l'activity
    Button btn = findViewById(R.id.btnLaunch);
    btn.setOnClickListener(v -> {
        Intent intent = new Intent(this, SecondActivity.class);
        launcher.launch(intent);
    });
}
```

### Activity 2 : Renvoyer résultat
```java
Intent resultIntent = new Intent();
resultIntent.putExtra("MESSAGE", "Données renvoyées");
setResult(RESULT_OK, resultIntent);
finish();  // Fermer l'activity
```

---

## 🎬 Intent implicite (actions système)

### Ouvrir URL
```java
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("https://isitcom.tn"));
startActivity(intent);
```

### Appeler un numéro
```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:+21612345678"));
startActivity(intent);
```

### Envoyer un email
```java
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("mailto:contact@isitcom.tn"));
intent.putExtra(Intent.EXTRA_SUBJECT, "Sujet");
startActivity(intent);
```

### Partager du texte
```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("text/plain");
intent.putExtra(Intent.EXTRA_TEXT, "Texte à partager");
startActivity(Intent.createChooser(intent, "Partager via"));
```

---

## 💾 Sauvegarder l'état (rotation, etc.)

```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("CLE", "valeur");
    outState.putInt("SCORE", score);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (savedInstanceState != null) {
        String valeur = savedInstanceState.getString("CLE");
        int score = savedInstanceState.getInt("SCORE", 0);
    }
}
```

---

## ⚠️ Pièges courants

| Problème | Cause | Solution |
|----------|-------|----------|
| Activity non déclarée | Manque dans Manifest | Ajouter `<activity>` |
| Crash au retour | ResultCode non vérifié | Toujours tester RESULT_OK |
| Données perdues (rotation) | Pas de onSaveInstanceState | Implémenter la sauvegarde |
| Intent null | getIntent() après finish() | Vérifier avant |

---

## 📝 Checklist

✅ Toujours appeler `super.` dans callbacks  
✅ Vérifier les extras avant `.getStringExtra()`  
✅ Utiliser Activity Result API (pas startActivityForResult)  
✅ Sauvegarder l'état dans onSaveInstanceState  
✅ Déclarer toutes les Activities dans AndroidManifest.xml  

---

## 🔑 Mémo rapide

```java
// Navigation simple
startActivity(new Intent(this, SecondActivity.class));

// Avec données
Intent i = new Intent(this, SecondActivity.class);
i.putExtra("KEY", value);
startActivity(i);

// Récupérer données
String data = getIntent().getStringExtra("KEY");

// Retour avec résultat
setResult(RESULT_OK, intent);
finish();
```

---

👨‍🏫 **Module 5 - Cycle de vie et Intents** | ISITCOM 2025-2026
