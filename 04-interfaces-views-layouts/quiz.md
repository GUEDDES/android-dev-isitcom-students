# Module 4 : Quiz d'auto-évaluation

## Questions à choix multiples

### Question 1
Qu'est-ce qu'une View en Android ?

A) Un écran complet  
B) Un élément visuel de base  
C) Un fichier XML  
D) Une base de données

<details>
<summary>Réponse</summary>
B) Un élément visuel de base (TextView, Button, etc.)
</details>

---

### Question 2
Quel layout est recommandé pour des interfaces complexes en 2025 ?

A) LinearLayout  
B) RelativeLayout  
C) ConstraintLayout  
D) FrameLayout

<details>
<summary>Réponse</summary>
C) ConstraintLayout (flexible et performant)
</details>

---

### Question 3
Quelle unité utiliser pour les dimensions (largeur, hauteur, marges) ?

A) px  
B) dp  
C) sp  
D) pt

<details>
<summary>Réponse</summary>
B) dp (density-independent pixels)
</details>

---

### Question 4
Quelle unité utiliser pour les tailles de texte ?

A) px  
B) dp  
C) sp  
D) em

<details>
<summary>Réponse</summary>
C) sp (scale-independent pixels)
</details>

---

### Question 5
Comment récupérer le texte d'un EditText ?

A) editText.getTextString()  
B) editText.toString()  
C) editText.getText().toString()  
D) editText.readText()

<details>
<summary>Réponse</summary>
C) editText.getText().toString()
</details>

---

### Question 6
Que fait `View.GONE` ?

A) Supprime la vue  
B) Cache la vue et libère l'espace  
C) Cache la vue mais garde l'espace  
D) Rend la vue transparente

<details>
<summary>Réponse</summary>
B) Cache la vue et libère l'espace (différent de INVISIBLE)
</details>

---

### Question 7
Quel attribut définit un texte d'aide dans un EditText ?

A) android:help  
B) android:placeholder  
C) android:hint  
D) android:description

<details>
<summary>Réponse</summary>
C) android:hint
</details>

---

### Question 8
Comment centrer une vue horizontalement dans ConstraintLayout ?

A) android:gravity="center"  
B) app:layout_constraintStart_toStartOf="parent" + End_toEndOf  
C) android:layout_centerHorizontal="true"  
D) android:alignParentCenter="true"

<details>
<summary>Réponse</summary>
B) Contraindre Start et End au parent
</details>

---

### Question 9
Où externaliser les textes pour faciliter la traduction ?

A) strings.xml  
B) texts.xml  
C) languages.xml  
D) Directement dans le code Java

<details>
<summary>Réponse</summary>
A) strings.xml (dans res/values/)
</details>

---

### Question 10
Que signifie `match_parent` ?

A) Adapter au contenu  
B) Prendre toute la place du parent  
C) Taille fixe  
D) Taille automatique

<details>
<summary>Réponse</summary>
B) Prendre toute la largeur/hauteur disponible du parent
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
EditText input = findViewById(R.id.editName);
Button btn = findViewById(R.id.btnSubmit);
TextView result = findViewById(R.id.textResult);

btn.setOnClickListener(v -> {
    String name = input.getText().toString();
    result.setText("Bonjour " + name);
});
```

A) Affiche un Toast  
B) Récupère un nom saisi et l'affiche dans un TextView  
C) Valide un formulaire  
D) Crée une nouvelle Activity

<details>
<summary>Réponse</summary>
B) Au clic sur le bouton, récupère le texte saisi et l'affiche formaté
</details>

---

### Question 12
Quelle est l'erreur dans ce XML ?

```xml
<TextView
    android:id="@+id/textTitle"
    android:layout_width="wrap_content"
    android:text="Titre"
    android:textSize="18dp" />
```

A) Pas d'erreur  
B) Manque layout_height  
C) textSize doit être en sp  
D) B et C

<details>
<summary>Réponse</summary>
D) Manque layout_height ET textSize doit être en sp
</details>

---

### Question 13
Que fait ce code ?

```java
CheckBox check = findViewById(R.id.checkAgree);
Button btn = findViewById(R.id.btnSubmit);

btn.setEnabled(check.isChecked());
```

A) Coche la case si le bouton est cliqué  
B) Active/désactive le bouton selon la case  
C) Affiche un message  
D) Créé une erreur

<details>
<summary>Réponse</summary>
B) Le bouton est activé seulement si la case est cochée
</details>

---

## Questions ouvertes

### Question 14
Expliquez la différence entre `View.GONE` et `View.INVISIBLE`.

<details>
<summary>Réponse</summary>
- `View.GONE` : cache la vue et libère l'espace qu'elle occupait
- `View.INVISIBLE` : cache la vue mais garde l'espace (comme si elle était transparente)
</details>

---

### Question 15
Pourquoi utiliser ConstraintLayout plutôt que LinearLayout imbriqués ?

<details>
<summary>Réponse</summary>
- Meilleures performances (hiérarchie plate)
- Plus flexible (positionnement relatif)
- Éditeur visuel puissant dans Android Studio
- Adaptatif aux différentes tailles d'écran
</details>

---

## Exercice pratique

### Question 16
Créez en XML un écran de connexion avec :
- Un TextView "Connexion" centré en haut
- Un EditText pour l'email
- Un EditText pour le mot de passe (inputType password)
- Un Button "Se connecter"
- Le tout dans un ConstraintLayout

<details>
<summary>Solution</summary>

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Connexion"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <EditText
        android:id="@+id/editEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:inputType="textEmailAddress"
        app:layout_constraintTop_toBottomOf="@id/textTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <EditText
        android:id="@+id/editPassword"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Mot de passe"
        android:inputType="textPassword"
        app:layout_constraintTop_toBottomOf="@id/editEmail"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <Button
        android:id="@+id/btnLogin"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Se connecter"
        app:layout_constraintTop_toBottomOf="@id/editPassword"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
</details>

---

## Barème

- **15-16/16** : Excellent ! Maîtrise complète
- **12-14/16** : Très bien
- **9-11/16** : Bien, quelques révisions
- **< 9/16** : Revoir le module

---

👨‍🏫 **Module 4 - Interfaces et Layouts** | ISITCOM 2025-2026
