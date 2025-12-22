# TD01 : Installation et premier projet

## 🎯 Objectifs

- Installer et configurer Android Studio.
- Créer un projet Android de base.
- Exécuter l'application sur émulateur et appareil réel.

---

## Partie 1 : Installation (30 min)

### Exercice 1.1 : Téléchargement et installation

1. Télécharger Android Studio depuis <https://developer.android.com/studio>
2. Installer avec les options par défaut.
3. Au premier lancement, choisir "Standard Setup".
4. Attendre le téléchargement des composants SDK.

**Livrable** : Capture d'écran de l'écran d'accueil d'Android Studio.

### Exercice 1.2 : Configuration du SDK

1. Ouvrir `Settings` → `Android SDK`.
2. Cocher API 34 et 35.
3. Dans l'onglet SDK Tools, cocher :
   - Android SDK Build-Tools
   - Android Emulator
   - Android SDK Platform-Tools
4. Appliquer les changements.

**Livrable** : Capture du SDK Manager avec les packages installés.

---

## Partie 2 : Création du projet (45 min)

### Exercice 2.1 : Nouveau projet HelloWorld

1. Cliquer sur "New Project".
2. Sélectionner "Empty Views Activity".
3. Configurer :
   - Name : `HelloISITCOM`
   - Package : `tn.isitcom.helloisitcom`
   - Language : `Java`
   - Minimum SDK : `API 24`
4. Finir la création.

**Questions** :
- Quels sont les fichiers générés automatiquement ?
- À quoi sert le fichier `AndroidManifest.xml` ?

### Exercice 2.2 : Exploration de la structure

1. Ouvrir `MainActivity.java` et identifier :
   - La méthode `onCreate()`
   - L'appel `setContentView()`
2. Ouvrir `activity_main.xml` en mode Code.
3. Identifier le `TextView` par défaut.

**Livrable** : Répondre aux questions dans un fichier texte.

---

## Partie 3 : Modification de l'interface (30 min)

### Exercice 3.1 : Personnalisation du texte

Dans `activity_main.xml`, modifier le `TextView` :

```xml
<TextView
    android:id="@+id/textWelcome"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Bienvenue à l'ISITCOM"
    android:textSize="28sp"
    android:textStyle="bold"
    android:textColor="#1976D2"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
```

### Exercice 3.2 : Ajout d'un bouton

Ajouter un bouton sous le TextView :

```xml
<Button
    android:id="@+id/btnClick"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Cliquez ici"
    app:layout_constraintTop_toBottomOf="@id/textWelcome"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="24dp" />
```

---

## Partie 4 : Interaction Java (30 min)

### Exercice 4.1 : Gérer le clic

Dans `MainActivity.java` :

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView textWelcome = findViewById(R.id.textWelcome);
    Button btnClick = findViewById(R.id.btnClick);

    btnClick.setOnClickListener(v -> {
        textWelcome.setText("Bouton cliqué !");
        Toast.makeText(this, "Hello ISITCOM!", Toast.LENGTH_SHORT).show();
    });
}
```

---

## Partie 5 : Exécution (15 min)

### Exercice 5.1 : Créer un AVD

1. Ouvrir Device Manager.
2. Créer un Pixel 5, API 33.
3. Lancer l'AVD.

### Exercice 5.2 : Exécuter l'app

1. Cliquer sur Run ▶.
2. Sélectionner l'AVD.
3. Tester le bouton.

**Livrable** : Capture d'écran de l'app en cours d'exécution.

---

## 🎯 TP Noté : Carte de visite interactive (/20)

Créer une application "Carte de Visite" :

### Consignes

1. Afficher :
   - Votre nom et prénom (TextView, taille 24sp, gras).
   - Votre groupe (TextView, 18sp).
   - Votre email (TextView, 16sp, couleur bleue).
   - Année universitaire (TextView, 14sp).

2. Ajouter 2 boutons :
   - "Email" : affiche un Toast avec votre email.
   - "Téléphone" : change le texte principal en "Contactez-moi !".

3. Interface :
   - ConstraintLayout.
   - Couleurs cohérentes.
   - Espacement approprié.

### Barème

| Critère | Points |
|---------|--------|
| Projet compile et s'exécute | 4 |
| Informations affichées correctement | 4 |
| Boutons fonctionnels | 4 |
| Toast correct | 2 |
| Interface soignée | 3 |
| Code propre et indenté | 3 |

**Date de rendu** : À définir par l'enseignant.

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
