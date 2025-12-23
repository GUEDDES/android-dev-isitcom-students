# Module 5 : Quiz d'auto-évaluation

## Questions à choix multiples

### Question 1
Dans quel ordre sont appelées les méthodes au démarrage d'une Activity ?

A) onResume() → onStart() → onCreate()  
B) onCreate() → onStart() → onResume()  
C) onCreate() → onResume() → onStart()  
D) onStart() → onCreate() → onResume()

<details>
<summary>Réponse</summary>
B) onCreate() → onStart() → onResume() (dans cet ordre)
</details>

---

### Question 2
Quelle méthode est appelée quand l'Activity passe en arrière-plan ?

A) onStop()  
B) onPause()  
C) onDestroy()  
D) onBackground()

<details>
<summary>Réponse</summary>
B) onPause() (puis onStop() si complètement invisible)
</details>

---

### Question 3
Comment passer un String à une autre Activity ?

A) intent.setString("key", "value")  
B) intent.putExtra("key", "value")  
C) intent.addString("key", "value")  
D) intent.putString("key", "value")

<details>
<summary>Réponse</summary>
B) intent.putExtra("key", "value")
</details>

---

### Question 4
Comment récupérer un extra String dans la seconde Activity ?

A) getString("KEY")  
B) getIntent().getExtra("KEY")  
C) getIntent().getStringExtra("KEY")  
D) Intent.getStringExtra("KEY")

<details>
<summary>Réponse</summary>
C) getIntent().getStringExtra("KEY")
</details>

---

### Question 5
Quelle API utiliser pour récupérer un résultat d'une Activity en 2025 ?

A) startActivityForResult()  
B) Activity Result API (registerForActivityResult)  
C) onActivityResult()  
D) getActivityResult()

<details>
<summary>Réponse</summary>
B) Activity Result API (registerForActivityResult) - startActivityForResult est déprécié
</details>

---

### Question 6
Que fait la méthode finish() ?

A) Termine l'application  
B) Ferme l'Activity en cours  
C) Arrête le processus  
D) Met l'Activity en pause

<details>
<summary>Réponse</summary>
B) Ferme l'Activity en cours et retourne à la précédente
</details>

---

### Question 7
Comment ouvrir une URL dans le navigateur ?

A) Intent avec ACTION_VIEW et Uri  
B) startBrowser(url)  
C) WebView.open(url)  
D) Intent avec ACTION_SEND

<details>
<summary>Réponse</summary>
A) Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
</details>

---

### Question 8
Quelle méthode sauvegarder l'état lors de la rotation ?

A) onSave()  
B) onSaveInstanceState()  
C) saveState()  
D) onConfigurationChanged()

<details>
<summary>Réponse</summary>
B) onSaveInstanceState(Bundle outState)
</details>

---

### Question 9
Que signifie RESULT_OK ?

A) L'Activity a réussi son traitement  
B) Code de retour positif  
C) Pas d'erreur  
D) Toutes les réponses

<details>
<summary>Réponse</summary>
D) Toutes les réponses (constante indiquant succès)
</details>

---

### Question 10
Comment partager du texte vers d'autres apps ?

A) Intent ACTION_VIEW  
B) Intent ACTION_SEND avec type "text/plain"  
C) Intent ACTION_SHARE  
D) ShareManager.share()

<details>
<summary>Réponse</summary>
B) Intent avec ACTION_SEND et type "text/plain"
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
Intent intent = new Intent(this, DetailActivity.class);
intent.putExtra("USER_ID", 42);
intent.putExtra("USER_NAME", "Alice");
startActivity(intent);
```

A) Lance DetailActivity sans données  
B) Lance DetailActivity avec ID et nom  
C) Attend un résultat de DetailActivity  
D) Crée une nouvelle Activity

<details>
<summary>Réponse</summary>
B) Lance DetailActivity en lui passant un ID (42) et un nom ("Alice")
</details>

---

### Question 12
Que fait ce code dans DetailActivity ?

```java
int userId = getIntent().getIntExtra("USER_ID", -1);
String userName = getIntent().getStringExtra("USER_NAME");
```

A) Envoie des données  
B) Récupère les extras envoyés  
C) Crée un Intent  
D) Sauvegarde l'état

<details>
<summary>Réponse</summary>
B) Récupère les extras : userId (défaut -1 si absent) et userName
</details>

---

### Question 13
Quelle est l'erreur ?

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_main);
    super.onCreate(savedInstanceState);
}
```

A) Pas d'erreur  
B) super.onCreate() doit être appelé en premier  
C) setContentView() mal placé  
D) Manque un return

<details>
<summary>Réponse</summary>
B) super.onCreate() doit toujours être appelé AVANT setContentView()
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
B) Ouvre le clavier du téléphone avec le numéro  
C) Envoie un SMS  
D) Enregistre un contact

<details>
<summary>Réponse</summary>
B) Ouvre l'application téléphone avec le numéro pré-rempli (ACTION_DIAL)
</details>

---

### Question 15
Activity Result API : que fait ce code ?

```java
ActivityResultLauncher<Intent> launcher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> {
        if (result.getResultCode() == RESULT_OK) {
            String data = result.getData().getStringExtra("RESULT");
            textView.setText(data);
        }
    }
);
```

A) Lance une Activity et attend son résultat  
B) Enregistre un callback pour traiter le résultat  
C) Affiche le résultat dans un TextView  
D) Toutes les réponses

<details>
<summary>Réponse</summary>
D) Enregistre un launcher qui traite le résultat et affiche la donnée
</details>

---

## Questions ouvertes

### Question 16
Expliquez la différence entre onPause() et onStop().

<details>
<summary>Réponse</summary>
- **onPause()** : L'Activity perd le focus mais est encore partiellement visible (ex: dialog par-dessus)
- **onStop()** : L'Activity est complètement invisible (ex: nouvelle Activity en plein écran)
</details>

---

### Question 17
Pourquoi sauvegarder l'état dans onSaveInstanceState() ?

<details>
<summary>Réponse</summary>
Pour préserver les données temporaires lors de :
- Rotation de l'écran
- Mise en arrière-plan (si le système tue l'Activity)
- Changements de configuration
</details>

---

### Question 18
Quelle est la différence entre Intent explicite et implicite ?

<details>
<summary>Réponse</summary>
- **Explicite** : Cible une Activity précise de votre app (new Intent(this, DetailActivity.class))
- **Implicite** : Demande au système de trouver une app capable de traiter l'action (ACTION_VIEW, ACTION_SEND...)
</details>

---

## Barème

- **16-18/18** : Excellent ! Cycle de vie maîtrisé
- **13-15/18** : Très bien
- **10-12/18** : Bien, revoir certains concepts
- **< 10/18** : Revoir le module en détail

---

👨‍🏫 **Module 5 - Cycle de vie et Intents** | ISITCOM 2025-2026
