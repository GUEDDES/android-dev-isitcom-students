# Module 3 : Quiz d'auto-évaluation

## Questions à choix multiples

### Question 1
Quelle méthode est appelée automatiquement au démarrage d'une Activity ?

A) onStart()  
B) onCreate()  
C) onResume()  
D) main()

<details>
<summary>Réponse</summary>
B) onCreate()
</details>

---

### Question 2
À quoi sert `setContentView(R.layout.activity_main)` ?

A) Créer une nouvelle Activity  
B) Lier le fichier XML d'interface à l'Activity  
C) Afficher un message Toast  
D) Démarrer l'application

<details>
<summary>Réponse</summary>
B) Lier le fichier XML d'interface à l'Activity
</details>

---

### Question 3
Que représente la classe R en Android ?

A) Une classe de ressources réseau  
B) Une classe générée contenant les IDs des ressources  
C) Une classe pour les requêtes HTTP  
D) Une classe de recyclage mémoire

<details>
<summary>Réponse</summary>
B) Une classe générée contenant les IDs des ressources
</details>

---

### Question 4
Quel attribut XML définit l'identifiant d'une vue ?

A) android:name  
B) android:id  
C) android:identifier  
D) android:key

<details>
<summary>Réponse</summary>
B) android:id
</details>

---

### Question 5
Comment récupérer une vue TextView en Java ?

A) TextView tv = new TextView();  
B) TextView tv = getView(R.id.myText);  
C) TextView tv = findViewById(R.id.myText);  
D) TextView tv = R.id.myText;

<details>
<summary>Réponse</summary>
C) TextView tv = findViewById(R.id.myText);
</details>

---

### Question 6
Que signifie `match_parent` pour `layout_width` ?

A) S'adapter au contenu  
B) Prendre toute la largeur disponible du parent  
C) Largeur fixe de 100dp  
D) Largeur automatique

<details>
<summary>Réponse</summary>
B) Prendre toute la largeur disponible du parent
</details>

---

### Question 7
Quelle est la syntaxe correcte pour créer un nouvel ID en XML ?

A) android:id="myText"  
B) android:id="@id/myText"  
C) android:id="@+id/myText"  
D) android:id="#myText"

<details>
<summary>Réponse</summary>
C) android:id="@+id/myText"
</details>

---

### Question 8
Comment afficher un message temporaire (Toast) ?

A) Toast.show("Message");  
B) Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();  
C) showToast("Message");  
D) displayMessage("Message");

<details>
<summary>Réponse</summary>
B) Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
</details>

---

### Question 9
Où est définie l'Activity principale de l'application ?

A) MainActivity.java  
B) build.gradle  
C) AndroidManifest.xml  
D) activity_main.xml

<details>
<summary>Réponse</summary>
C) AndroidManifest.xml (avec intent-filter LAUNCHER)
</details>

---

### Question 10
Quelle commande Gradle synchronise les dépendances ?

A) Build > Make Project  
B) File > Sync Project with Gradle Files  
C) Tools > Gradle Sync  
D) Run > Sync Gradle

<details>
<summary>Réponse</summary>
B) File > Sync Project with Gradle Files
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
Button btn = findViewById(R.id.btnClick);
btn.setOnClickListener(v -> {
    TextView text = findViewById(R.id.textMessage);
    text.setText("Bouton cliqué!");
});
```

A) Change le texte du bouton au clic  
B) Change le texte d'un TextView au clic sur le bouton  
C) Crée un nouveau TextView  
D) Affiche un Toast

<details>
<summary>Réponse</summary>
B) Change le texte d'un TextView au clic sur le bouton
</details>

---

### Question 12
Quelle est l'erreur dans ce code ?

```java
protected void onCreate(Bundle savedInstanceState) {
    TextView text = findViewById(R.id.textWelcome);
    setContentView(R.layout.activity_main);
    text.setText("Hello");
}
```

A) Pas d'erreur  
B) `findViewById` avant `setContentView`  
C) Manque `super.onCreate()`  
D) B et C

<details>
<summary>Réponse</summary>
D) findViewById avant setContentView ET manque super.onCreate()
</details>

---

## Questions ouvertes

### Question 13
Expliquez la différence entre `wrap_content` et `match_parent` pour `layout_width`.

<details>
<summary>Réponse</summary>
- `wrap_content` : la vue s'adapte à la taille de son contenu
- `match_parent` : la vue prend toute la largeur disponible du parent
</details>

---

### Question 14
Pourquoi faut-il toujours appeler `super.onCreate()` au début de la méthode `onCreate()` ?

<details>
<summary>Réponse</summary>
Pour que la classe parente (AppCompatActivity) puisse effectuer ses initialisations nécessaires au bon fonctionnement de l'Activity.
</details>

---

### Question 15
Que se passe-t-il si vous oubliez de déclarer une Activity dans AndroidManifest.xml ?

<details>
<summary>Réponse</summary>
L'application crashe au lancement de cette Activity avec une exception "Unable to find explicit activity class".
</details>

---

## Barème

- **15/15** : Excellent ! Vous maîtrisez le module
- **12-14/15** : Très bien, quelques révisions mineures
- **9-11/15** : Bien, revoir certains concepts
- **< 9/15** : Revoir le module en détail

---

👨‍🏫 **Module 3 - Premier projet Android** | ISITCOM 2025-2026
