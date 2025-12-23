# Quiz - Module 5 : Cycle de vie et Intents

## Questions à choix multiples

### Question 1
Quelle méthode du cycle de vie est appelée lorsque l'Activity devient visible ?

A) onCreate()  
B) onStart()  
C) onResume()  
D) onPause()

<details>
<summary>Réponse</summary>
B) onStart() - L'Activity devient visible mais n'a pas encore le focus
</details>

---

### Question 2
Quelle méthode est appelée quand l'utilisateur peut interagir avec l'Activity ?

A) onStart()  
B) onResume()  
C) onCreate()  
D) onRestart()

<details>
<summary>Réponse</summary>
B) onResume() - L'Activity est au premier plan et interactive
</details>

---

### Question 3
Qu'est-ce qu'un Intent explicite ?

A) Un Intent qui démarre une Activity spécifique de l'application  
B) Un Intent qui demande au système de choisir l'application  
C) Un Intent pour partager des données  
D) Un Intent pour les services

<details>
<summary>Réponse</summary>
A) Intent explicite = désigne une classe spécifique (SecondActivity.class)
</details>

---

### Question 4
Comment passer une chaîne de caractères à une autre Activity ?

A) intent.setString("KEY", "value")  
B) intent.putExtra("KEY", "value")  
C) intent.addData("KEY", "value")  
D) intent.passString("KEY", "value")

<details>
<summary>Réponse</summary>
B) intent.putExtra("KEY", "value")
</details>

---

### Question 5
Quelle API remplace startActivityForResult() dépréciée ?

A) startActivityWithResult()  
B) Activity Result API avec registerForActivityResult()  
C) launchActivity()  
D) openActivityForResult()

<details>
<summary>Réponse</summary>
B) Activity Result API avec registerForActivityResult()
</details>

---

### Question 6
Que fait finish() ?

A) Termine l'application  
B) Ferme l'Activity courante  
C) Met l'Activity en pause  
D) Redémarre l'Activity

<details>
<summary>Réponse</summary>
B) Ferme l'Activity courante et retourne à la précédente
</details>

---

### Question 7
Quelle méthode utiliser pour sauvegarder l'état lors d'une rotation ?

A) onSave()  
B) onSaveInstanceState()  
C) saveState()  
D) onPause()

<details>
<summary>Réponse</summary>
B) onSaveInstanceState(Bundle outState)
</details>

---

### Question 8
Quel Intent implicite ouvre une URL dans le navigateur ?

A) Intent.ACTION_VIEW  
B) Intent.ACTION_OPEN  
C) Intent.ACTION_WEB  
D) Intent.ACTION_BROWSER

<details>
<summary>Réponse</summary>
A) Intent.ACTION_VIEW avec Uri.parse("https://...")
</details>

---

### Question 9
Où déclarer toutes les Activities ?

A) build.gradle  
B) AndroidManifest.xml  
C) strings.xml  
D) MainActivity.java

<details>
<summary>Réponse</summary>
B) AndroidManifest.xml avec balise <activity>
</details>

---

### Question 10
Que fait getIntent() ?

A) Crée un nouvel Intent  
B) Récupère l'Intent qui a lancé l'Activity  
C) Lance une Activity  
D) Retourne un Intent vide

<details>
<summary>Réponse</summary>
B) Récupère l'Intent reçu par l'Activity
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
Intent intent = new Intent(this, ProfileActivity.class);
intent.putExtra("USER_ID", 123);
startActivity(intent);
```

A) Crée une Activity  
B) Lance ProfileActivity avec l'ID utilisateur 123  
C) Envoie un message  
D) Sauvegarde des données

<details>
<summary>Réponse</summary>
B) Démarre ProfileActivity en lui passant l'ID 123
</details>

---

### Question 12
Comment récupérer l'extra "USER_ID" dans ProfileActivity ?

```java
A) int id = getExtra("USER_ID");
B) int id = getIntent().getIntExtra("USER_ID", 0);
C) int id = Intent.getInt("USER_ID");
D) int id = this.getInt("USER_ID");
```

<details>
<summary>Réponse</summary>
B) getIntent().getIntExtra("USER_ID", 0) - 0 est la valeur par défaut
</details>

---

### Question 13
Quelle est l'erreur dans ce code ?

```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    outState.putString("NAME", userName);
}
```

A) Pas d'erreur  
B) Manque super.onSaveInstanceState()  
C) Mauvais type Bundle  
D) Clé incorrecte

<details>
<summary>Réponse</summary>
B) Il faut appeler super.onSaveInstanceState(outState) avant ou après
</details>

---

### Question 14
Que fait ce code ?

```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:+21612345678"));
startActivity(intent);
```

A) Appelle directement le numéro  
B) Ouvre le composeur avec le numéro pré-rempli  
C) Envoie un SMS  
D) Ajoute un contact

<details>
<summary>Réponse</summary>
B) Ouvre l'application Téléphone avec le numéro saisi
</details>

---

### Question 15
Activity Result API - Associer les parties

```java
// Partie 1
ActivityResultLauncher<Intent> launcher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> {
        if (result.getResultCode() == RESULT_OK) {
            // Traiter résultat
        }
    }
);

// Partie 2
launcher.launch(intent);

// Partie 3 (dans l'Activity lancée)
setResult(RESULT_OK, data);
finish();
```

Quel est l'ordre d'exécution ?

A) 1 → 2 → 3  
B) 2 → 1 → 3  
C) 1 → 3 → 2  
D) 3 → 1 → 2

<details>
<summary>Réponse</summary>
A) 1 (enregistrer) → 2 (lancer) → 3 (retourner résultat)
</details>

---

## Questions ouvertes

### Question 16
Expliquez la différence entre onPause() et onStop().

<details>
<summary>Réponse</summary>
- **onPause()** : L'Activity est partiellement visible (ex: dialogue par-dessus), perd le focus mais reste visible
- **onStop()** : L'Activity n'est plus visible du tout, complètement en arrière-plan
</details>

---

### Question 17
Pourquoi utiliser un Intent implicite plutôt qu'explicite ?

<details>
<summary>Réponse</summary>
Pour déléguer une action au système (ouvrir URL, partager, appeler) et laisser l'utilisateur choisir l'application. Permet d'utiliser des fonctionnalités système sans implémenter soi-même.
</details>

---

### Question 18
Que se passe-t-il si on ne sauvegarde pas l'état dans onSaveInstanceState() ?

<details>
<summary>Réponse</summary>
Lors d'une rotation d'écran ou destruction temporaire de l'Activity, toutes les données non sauvegardées (variables, textes saisis) sont perdues. L'Activity repart de zéro.
</details>

---

## Exercice pratique

### Question 19
Créez le code pour :
1. Lancer une Activity "DetailActivity"
2. Lui passer un nom (String) et un âge (int)
3. Récupérer ces données dans DetailActivity
4. Les afficher dans des TextView

<details>
<summary>Solution</summary>

**MainActivity.java**
```java
// Lancer DetailActivity
Intent intent = new Intent(this, DetailActivity.class);
intent.putExtra("NAME", "Alice");
intent.putExtra("AGE", 25);
startActivity(intent);
```

**DetailActivity.java**
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    
    // Récupérer extras
    String name = getIntent().getStringExtra("NAME");
    int age = getIntent().getIntExtra("AGE", 0);
    
    // Afficher
    TextView textName = findViewById(R.id.textName);
    TextView textAge = findViewById(R.id.textAge);
    
    textName.setText(name);
    textAge.setText("Age: " + age + " ans");
}
```
</details>

---

## Barème

- **18-19/19** : Excellent ! Maîtrise complète du cycle de vie et navigation
- **15-17/19** : Très bien, quelques révisions mineures
- **12-14/19** : Bien, revoir certains concepts
- **< 12/19** : Reprendre le module en détail

---

👨‍🏫 **Module 5 - Cycle de vie et Intents** | ISITCOM 2025-2026
