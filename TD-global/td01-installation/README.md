# TD 01 : Installation et prise en main d'Android Studio

## 🎯 Objectifs

- Installer correctement Android Studio et le SDK Android.
- Configurer un émulateur Android (AVD).
- Créer et exécuter un premier projet HelloWorld.
- Comprendre la structure de base d'un projet Android.

---

## Exercice 1 : Installation d'Android Studio

### Consignes

1. Télécharger Android Studio depuis <https://developer.android.com/studio>.
2. Installer avec les paramètres par défaut.
3. Au premier lancement, sélectionner "Standard" pour la configuration.
4. Attendre le téléchargement complet des composants.

### À rendre

- Capture d'écran de l'écran d'accueil d'Android Studio.
- Capture d'écran du SDK Manager montrant les API 33, 34, 35 installées.

---

## Exercice 2 : Création d'un AVD

### Consignes

1. Ouvrir le **Device Manager**.
2. Créer un nouvel appareil virtuel :
   - Modèle : Pixel 5
   - Image système : Android 13 (API 33)
   - RAM : 2048 Mo
3. Lancer l'AVD.
4. Vérifier que l'émulateur démarre correctement.

### À rendre

- Capture d'écran de l'AVD en fonctionnement.

---

## Exercice 3 : Projet HelloWorld

### Consignes

1. Créer un nouveau projet :
   - Template : **Empty Views Activity**
   - Nom : `HelloISITCOM`
   - Package : `tn.isitcom.td01`
   - Langage : Java
   - Minimum SDK : API 24

2. Lancer l'application sur l'AVD créé précédemment.

3. Vérifier que "Hello World!" s'affiche.

### À rendre

- Capture d'écran de l'application fonctionnant sur l'émulateur.
- Copie du fichier `AndroidManifest.xml`.

---

## Exercice 4 : Exploration de la structure

### Consignes

1. Explorer les dossiers suivants dans la vue Android :
   - `app/java/`
   - `app/res/layout/`
   - `app/manifests/`
   - `Gradle Scripts/`

2. Répondre aux questions :
   - Quel est le rôle de `MainActivity.java` ?
   - Que contient `activity_main.xml` ?
   - À quoi sert `AndroidManifest.xml` ?
   - Quelle est la version de `compileSdk` dans `build.gradle` ?

### À rendre

- Document PDF/Word avec les réponses aux questions.

---

## Barème

| Exercice | Points |
|---------|--------|
| Installation Android Studio | 4 |
| Création AVD | 4 |
| Projet HelloWorld | 6 |
| Exploration structure | 6 |
| **Total** | **/20** |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
