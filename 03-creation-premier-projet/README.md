# Module 3 : Premier projet Android (HelloWorld)

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous saurez :
- Créer un projet Android Studio de zéro.
- Comprendre la structure générée (manifest, java, res, Gradle).
- Modifier le texte affiché à l'écran via XML et via Java.
- Exécuter l'application sur un émulateur ou un téléphone.

---

## 🧰 Pré-requis

- Android Studio déjà installé (voir Module 2).
- JDK 17 configuré.
- Connaissances de base en Java (classes, méthodes).

---

## 1. Création du projet HelloWorld

### 1.1 Lancement d'Android Studio

1. Ouvrir **Android Studio**.
2. Sur l'écran d'accueil, cliquer sur **“New Project”**.

### 1.2 Choix du template

Pour rester en Java + XML, utiliser le template adapté.

1. Dans la liste des modèles, choisir : **Empty Views Activity**.
2. Vérifier que le langage par défaut indiqué est **Java**.

> Ne pas choisir “Empty Activity” si Android Studio propose *uniquement* Kotlin pour ce template.

### 1.3 Paramétrage du projet

Renseigner les informations suivantes :

- **Name** : `HelloIsitcom`  
- **Package name** : `tn.isitcom.helloisitcom`  
- **Save location** : dossier de travail sur votre machine  
- **Language** : `Java`  
- **Minimum SDK** : `API 24: Android 7.0 (Nougat)`  
- **Use legacy android.support libraries** : décoché

Cliquer sur **Finish** et laisser Android Studio générer le projet.

---

## 2. Structure du projet généré

Une fois le projet chargé, passer en vue **Android** dans le panneau de gauche.

### 2.1 Dossiers importants

- `app/java/tn.isitcom.helloisitcom/`
  - `MainActivity.java` : point d'entrée de l'interface principale.
- `app/res/layout/`
  - `activity_main.xml` : description de l'écran en XML.
- `app/manifests/`
  - `AndroidManifest.xml` : configuration globale (nom package, Activity principale…).
- `Gradle Scripts/`
  - `build.gradle (Module: app)` : configuration de compilation et dépendances.

### 2.2 Rôle des fichiers clés

| Fichier | Rôle |
|--------|------|
| `MainActivity.java` | Code Java exécuté à l'ouverture de l'écran principal |
| `activity_main.xml` | Définition visuelle de l'interface (TextView, Button…) |
| `AndroidManifest.xml` | Déclare l'application et ses composants au système |
| `build.gradle` | Indique comment compiler le module et quelles bibliothèques utiliser |

---

## 3. Comprendre MainActivity et onCreate

Ouvrir `MainActivity.java`.

Vous devez voir une classe similaire (version simplifiée) :

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Lie la classe Java au layout XML
        setContentView(R.layout.activity_main);
    }
}
```

Points importants :

- La classe **hérite** de `AppCompatActivity` → c'est un écran Android.
- La méthode `onCreate` est appelée au démarrage de l'écran.
- `setContentView(R.layout.activity_main)` charge le fichier XML `activity_main.xml`.

---

## 4. Modifier l'interface HelloWorld

### 4.1 Modifier le texte en XML

Ouvrir `res/layout/activity_main.xml` en vue **Code**.

Remplacer le contenu principal par :

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/textWelcome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bonjour ISITCOM !"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

Cliquer sur **Design** pour vérifier le rendu.

### 4.2 Changer le texte depuis Java

Dans `MainActivity.java` :

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération de la vue TextView déclarée dans le XML
        TextView textWelcome = findViewById(R.id.textWelcome);

        // Modification du texte au lancement
        textWelcome.setText("Bienvenue dans le cours Android ISITCOM");
    }
}
```

> Importer la classe `TextView` si nécessaire (`Alt+Enter` sur TextView).

---

## 5. Exécuter l'application

### 5.1 Sur un émulateur (AVD)

1. Ouvrir le **Device Manager**.
2. Créer un nouvel appareil virtuel (par exemple Pixel 5, API 30).
3. Lancer l'AVD.
4. Cliquer sur le bouton **Run ▶** de la barre d'outils.
5. Choisir l'AVD comme cible de déploiement.

### 5.2 Sur un téléphone réel

1. Activer les **Options développeur** sur le téléphone.
2. Activer **Débogage USB**.
3. Connecter le téléphone par câble.
4. Autoriser le PC sur le téléphone.
5. Lancer l'app avec **Run ▶** et choisir l'appareil réel.

Si tout est correct, l'écran affiche votre message personnalisé.

---

## 6. Exercices pratiques

### Exercice 1 – Personnaliser l'écran d'accueil

Objectif : transformer HelloWorld en un écran de bienvenue ISITCOM.

1. Modifier le texte pour afficher :
   - Votre nom et prénom.
   - Votre groupe.
   - L'année universitaire.
2. Changer la couleur du texte.
3. Changer la taille de police.

Aide :

```xml
android:textColor="@android:color/holo_blue_dark"
android:textSize="26sp"
```

### Exercice 2 – Ajouter un bouton interactif

Objectif : ajouter un bouton qui change le texte quand on clique.

1. Ajouter un `Button` dans `activity_main.xml` :

```xml
<Button
    android:id="@+id/btnClick"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Clique ici"
    app:layout_constraintTop_toBottomOf="@id/textWelcome"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
```

2. Dans `MainActivity.java` :

```java
Button btnClick = findViewById(R.id.btnClick);
TextView textWelcome = findViewById(R.id.textWelcome);

btnClick.setOnClickListener(v -> {
    textWelcome.setText("Bouton cliqué !");
});
```

3. Exécuter et tester.

### Exercice 3 – Toast de bienvenue

Objectif : afficher un message temporaire (Toast) au démarrage.

Dans `onCreate` :

```java
Toast.makeText(this, "Hello ISITCOM !", Toast.LENGTH_SHORT).show();
```

Questions :
- Que se passe-t-il si vous changez `Toast.LENGTH_SHORT` par `Toast.LENGTH_LONG` ?
- Quand le Toast apparaît-il dans le cycle de vie de l'Activity ?

---

## 7. Mini-TP HelloIsitcom+ (à rendre)

Réaliser une petite application basée sur HelloWorld avec les contraintes suivantes :

1. L'écran doit afficher :
   - Un titre : "Bienvenue à l'ISITCOM".
   - Un sous-titre avec votre section et groupe.
   - Un texte descriptif (2–3 lignes) sur le module Android.
2. Ajouter **deux boutons** :
   - `btnAbout` : lorsque l'on clique, le texte descriptif change.
   - `btnReset` : remet les textes à leur état initial.
3. Afficher un Toast différent lors de chaque clic.
4. Utiliser des couleurs cohérentes (pas d'arc-en-ciel).

### Critères d'évaluation

| Critère | Points |
|--------|--------|
| Projet se compile et s'exécute | 4 |
| Respect des consignes UI | 4 |
| Gestion correcte des clics | 4 |
| Lisibilité du code (indentation, noms) | 4 |
| Personnalisation (textes, couleurs) | 4 |

**Total** : /20

---

## 8. Erreurs fréquentes et solutions

- **Erreur : "Cannot resolve symbol R"**  
  → Vérifier qu'il n'y a pas d'erreur dans les fichiers XML et synchroniser Gradle.

- **Application qui plante au lancement (crash)**  
  → Regarder l'onglet **Logcat**, chercher `FATAL EXCEPTION` et identifier la ligne de code concernée.

- **ID incorrect dans findViewById**  
  → S'assurer que l'ID dans le XML (`@+id/textWelcome`) correspond exactement à celui utilisé en Java (`R.id.textWelcome`).

- **Émulateur très lent**  
  → Diminuer la résolution de l'AVD ou utiliser un appareil réel.

---

## 9. Pour aller plus loin

- Ajouter un champ `EditText` pour que l'utilisateur saisisse son prénom, puis l'afficher dans le `TextView`.
- Changer la langue de l'app en utilisant `strings.xml` (français/anglais).
- Tester l'application sur différentes versions d'Android.

---

👨‍🏫 **Enseignant** : A. GUEDDES  
📍 ISITCOM – Université de Sousse  
📆 2025-2026
