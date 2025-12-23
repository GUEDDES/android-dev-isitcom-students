# Exercices pratiques - Module 2 : SDK et outils

## Exercice 1 : Installation et configuration

### Objectif
Vérifier que l'environnement de développement est correctement configuré.

### Consignes
1. Ouvrir Android Studio
2. Aller dans `File > Settings > Appearance & Behavior > System Settings > Android SDK`
3. Vérifier que les API suivantes sont installées :
   - Android 15.0 (API 35)
   - Android 14.0 (API 34)
   - Android 13.0 (API 33)
4. Dans l'onglet `SDK Tools`, vérifier :
   - Android SDK Build-Tools
   - Android Emulator
   - Android SDK Platform-Tools
5. Faire une capture d'écran

### Livrable
Capture d'écran du SDK Manager avec les API installées.

---

## Exercice 2 : Création d'un AVD

### Objectif
Créer et configurer un émulateur Android.

### Consignes
1. Ouvrir le **Device Manager**
2. Cliquer sur **Create Device**
3. Choisir : **Pixel 5**
4. Sélectionner l'image système : **Android 13 (API 33)**
5. Configuration :
   - RAM : 2048 MB
   - Stockage interne : 2048 MB
6. Lancer l'AVD
7. Faire une capture d'écran de l'émulateur lancé

### Livrable
Capture d'écran de l'AVD en fonctionnement.

---

## Exercice 3 : Tests ADB

### Objectif
Utiliser ADB en ligne de commande.

### Consignes
1. Ouvrir un terminal
2. Exécuter les commandes suivantes :
   ```bash
   adb devices
   adb shell getprop ro.build.version.release
   adb logcat -d | head -20
   ```
3. Copier les résultats dans un fichier texte

### Livrable
Fichier texte avec les résultats des commandes.

---

## Exercice 4 : Analyse Gradle

### Objectif
Comprendre la structure d'un fichier `build.gradle`.

### Consignes
1. Créer un nouveau projet Android vide
2. Ouvrir `build.gradle (Module: app)`
3. Identifier et expliquer :
   - `compileSdk`
   - `minSdk`
   - `targetSdk`
   - `applicationId`
   - Deux dépendances dans le bloc `dependencies`

### Livrable
Document (PDF ou Markdown) avec explications.

---

## Mini-projet : Configuration avancée

### Objectif
Créer un environnement optimisé.

### Consignes
1. Créer **deux AVD** :
   - Un pour tests rapides (API 30, petit écran)
   - Un pour tests finaux (API 34, grand écran)
2. Créer un projet de test
3. Exécuter le projet sur les deux AVD
4. Comparer les temps de lancement

### Livrable
Rapport comparatif avec captures d'écran.
