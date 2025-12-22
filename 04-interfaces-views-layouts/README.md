# Module 4 : Interfaces, vues et layouts

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Expliquer ce qu’est une `Activity` et une `View`.
- Créer une interface utilisateur avec XML (layouts + widgets). [file:2]
- Lier le code Java aux vues XML (`findViewById`). [file:2]
- Réagir aux actions utilisateur (clics, saisie). [file:2]

---

## 1. Activity = écran + layout

Une **Activity** représente un écran de l’application. [file:2]

- Côté Java : classe qui étend `AppCompatActivity`.
- Côté XML : fichier `layout` qui décrit les vues affichées. [file:2]

Exemple minimal :

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

`setContentView` lie la classe Java au layout XML `activity_main.xml`. [file:2]

---

## 2. Vues (Views) et widgets

Une **View** est un élément visuel de base : texte, bouton, champ de saisie, image, etc. [file:2]

Exemples de widgets courants : [file:2]

- `TextView` : afficher un texte.
- `EditText` : saisir un texte.
- `Button` : bouton cliquable.
- `ImageView` : afficher une image.
- `CheckBox` / `RadioButton` : cases à cocher / choix unique.

Exemple XML :

```xml
<TextView
    android:id="@+id/textTitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Bonjour" />
```

---

## 3. Layouts : organiser l’écran

Les layouts sont des vues qui **contiennent** d’autres vues et définissent leur positionnement. [file:2]

Types principaux : [file:2]

- `LinearLayout` : organisation verticale ou horizontale.
- `ConstraintLayout` : positionnement flexible, recommandé.
- `FrameLayout` : superposition.

Exemple `ConstraintLayout` + `TextView` centré :

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textCenter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Centre écran"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 4. Lien XML ↔ Java (`findViewById`)

Chaque vue définie en XML devient un **objet Java** lors de l’exécution. [file:2]

### 4.1 Récupérer une vue

```java
TextView textTitle = findViewById(R.id.textTitle);
Button btnOk = findViewById(R.id.btnOk);
```

### 4.2 Modifier dynamiquement

```java
textTitle.setText("Nouveau titre");
textTitle.setTextColor(Color.RED);
textTitle.setTextSize(20);
```

> Tous les widgets héritent de la classe `View` et partagent des méthodes communes (`setVisibility`, `setOnClickListener`, etc.). [file:2]

---

## 5. Gérer les clics sur un bouton

### 5.1 Via `setOnClickListener`

```java
Button btnClick = findViewById(R.id.btnClick);
TextView textMessage = findViewById(R.id.textMessage);

btnClick.setOnClickListener(v -> {
    textMessage.setText("Bouton cliqué !");
});
```

### 5.2 Afficher un Toast

```java
Toast.makeText(this, "Clic détecté", Toast.LENGTH_SHORT).show();
```

---

## 6. Petit atelier guidé

### Objectif

Construire un écran de **login simple** :

- Deux `EditText` : email et mot de passe.
- Un `Button` : "Se connecter".
- Un `TextView` pour afficher un message.

### Étapes

1. Dans `activity_main.xml` :
   - Utiliser un `ConstraintLayout`.
   - Placer les deux champs l’un sous l’autre.
   - Placer le bouton sous les champs.
   - Placer le `TextView` en bas de l’écran.

2. Dans `MainActivity.java` :
   - Récupérer les vues par `findViewById`.
   - Sur clic du bouton :
     - Lire le texte des deux `EditText`.
     - Si les champs sont vides → afficher un message d’erreur.
     - Sinon → afficher `"Bienvenue <email>"` dans le `TextView`.

---

## 7. Exercices pratiques (Module 4)

### Exercice 1 – Carte de visite

Créer un écran qui affiche :

- Nom et prénom.
- Spécialité (ex : L3 GL).
- Email étudiant.
- Un bouton "Contact" qui affiche un Toast avec votre email.

### Exercice 2 – Compteur de clics

1. Ajouter un `TextView` avec la valeur initiale `0`.
2. Ajouter un bouton `+1`.
3. À chaque clic sur le bouton, incrémenter le compteur et mettre à jour le `TextView`.

### Exercice 3 – Afficher/masquer un texte

1. Ajouter un `TextView` et un bouton "Afficher / Masquer".
2. À chaque clic, alterner entre `View.VISIBLE` et `View.GONE`.

---

## 8. Erreurs fréquentes

- **`NullPointerException` sur `findViewById`**  
  → Vérifier que `setContentView` pointe bien sur le bon layout et que l’ID existe dans le XML.

- **Widget non visible**  
  → Vérifier les contraintes dans `ConstraintLayout` et la taille (`wrap_content` / `match_parent`).

- **Texte dur écrit en dur dans le XML**  
  → Préférer utiliser `strings.xml` pour les textes réutilisables.

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
