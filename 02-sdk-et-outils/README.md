# Module 2 : SDK Android, Android Studio et outils

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Installer et mettre à jour le SDK Android.
- Configurer Android Studio pour le développement natif.
- Créer et utiliser des émulateurs (AVD).
- Comprendre le rôle d’ADB et de Gradle.

---

## 1. Stack minimale pour développer sur Android

Pour développer une application Android native, il faut :

- **SDK Android** : bibliothèques et outils pour compiler et tester. [file:2]
- **Android Studio** : IDE officiel de Google. [file:2]
- **Java** (ou Kotlin) : langage de programmation.
- **Gradle** : système de build (compilation, packaging). [file:2]
- **AVD/ADB** : exécution et débogage sur émulateur ou appareil réel. [file:2]

> Android Studio regroupe IDE + SDK + émulateur + outils dans une seule installation.

---

## 2. Installation d’Android Studio

### 2.1 Téléchargement

1. Aller sur : <https://developer.android.com/studio>  
2. Télécharger la version pour votre système (Windows, macOS, Linux). [file:2]

Configuration recommandée :

- **RAM** : 8 Go minimum (16 Go confortable).
- **Disque** : 10 Go libres.
- **Java** : JDK 17 ou plus.

### 2.2 Installation pas à pas

1. Lancer l’installateur.
2. Accepter les licences.
3. Laisser les options par défaut (Android Studio + SDK). [file:2]
4. Au premier lancement, choisir **Standard** pour la configuration.
5. Attendre le téléchargement des composants (peut prendre plusieurs minutes).

---

## 3. SDK Android et SDK Manager

### 3.1 Rôle du SDK

Le **SDK Android** fournit : [file:2]

- Les **API Android** (Framework, Jetpack, Material Components).
- Les outils de compilation et de débogage.
- Les images système pour les émulateurs.
- La documentation intégrée. [file:2]

### 3.2 Ouvrir le SDK Manager

Dans Android Studio :

1. `File` → `Settings` (ou `Android Studio` → `Settings` sur macOS).
2. Section **Appearance & Behavior > System Settings > Android SDK**.

Vérifier :

- Onglet **SDK Platforms** : 
  - cocher au moins **Android 15 (API 35)** et une ou deux versions en dessous (ex : 14, 13). [file:2]
- Onglet **SDK Tools** : 
  - cocher **Android SDK Build-Tools**, **Android Emulator**, **Android SDK Platform-Tools**.

> L’API cible conseillée en 2025 est 35 (Android 15), avec un minSdk autour de 24. [file:2]

---

## 4. AVD : créer un émulateur

### 4.1 Device Manager

1. Dans la barre d’outils, cliquer sur l’icône **Device Manager** (téléphone + Android). [file:2]
2. Cliquer sur **Create Device**.
3. Choisir un modèle (ex : **Pixel 5**).
4. Choisir une image système (ex : **Android 13 - API 33**).
5. Lancer la création.

### 4.2 Conseils de configuration

- Éviter les API trop anciennes pour l’AVD.
- Si la machine est lente :
  - Réduire la **RAM** de l’AVD (1536–2048 Mo).
  - Baisser la **résolution**.

> L’AVD permet de tester différentes tailles d’écran et versions d’Android sans téléphone. [file:2]

---

## 5. ADB : Android Debug Bridge

### 5.1 Définition

ADB est un outil en ligne de commande qui permet de :

- Lister les appareils branchés (émulateurs et téléphones).
- Installer/désinstaller une application.
- Afficher les logs.
- Ouvrir un shell sur l’appareil.

### 5.2 Commandes de base

Dans un terminal :

```bash
adb devices          # liste des appareils
adb install app.apk  # installe un APK
adb logcat           # affiche les logs en temps réel
```

> Android Studio utilise ADB en arrière-plan pour lancer et déboguer vos apps. [file:2]

---

## 6. Gradle : système de build

### 6.1 Rôle de Gradle

Gradle est l’outil qui : [file:2]

- Télécharge les dépendances (bibliothèques externes).
- Compile le code Java.
- Transforme les ressources XML en formats optimisés.
- Génère les APK / AAB.

### 6.2 Fichiers importants

- `build.gradle (Project)` : configuration globale (dépôts, version du plugin Android).
- `build.gradle (Module: app)` :
  - `defaultConfig` : applicationId, minSdk, targetSdk, versionCode, versionName.
  - `dependencies` : bibliothèques utilisées. [file:2]

Exemple (simplifié) :

```gradle
android {
    compileSdk 35

    defaultConfig {
        applicationId "tn.isitcom.helloisitcom"
        minSdk 24
        targetSdk 35
        versionCode 1
        versionName "1.0"
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
}
```

### 6.3 Synchronisation Gradle

Après modification d’un `build.gradle` :

- Cliquer sur **Sync Now** en haut de l’éditeur.
- Attendre la fin de la synchronisation.

Si erreur :

- Vérifier les versions des dépendances.
- Vérifier la connexion internet.

---

## 7. Exécution : émulateur vs appareil réel

### 7.1 Appareil réel (recommandé)

1. Activer **Options pour les développeurs** sur le téléphone.
2. Activer **Débogage USB**.
3. Connecter le téléphone via USB.
4. Autoriser le PC.
5. Dans Android Studio, cliquer sur **Run ▶** et choisir le téléphone.

### 7.2 Émulateur

- Pratique pour tester rapidement sans câble.
- Peut être plus lent selon la machine.

### 7.3 Astuce

- Développer sur émulateur au début.
- Toujours tester au moins une fois sur un téléphone réel avant l'évaluation.

---

## 8. Exercices pratiques (Module 2)

### Exercice 1 – Capture d’écran de la configuration SDK

Objectif : vérifier que le SDK est bien configuré.

1. Ouvrir le **SDK Manager**.
2. Cocher au moins API 33, 34, 35.
3. Faire une capture d’écran.
4. La déposer dans un dossier `captures/` de votre projet.

### Exercice 2 – Création d’un AVD

1. Créer un AVD de type **Pixel 5 – API 33**.
2. Lancer l’AVD.
3. Faire une capture d’écran de l’AVD ouvert.

### Exercice 3 – Tester ADB

1. Ouvrir un terminal.
2. Taper `adb devices`.
3. Vérifier que votre appareil ou l’AVD apparaît dans la liste.

---

## 9. Foire aux problèmes (FAQ rapide)

- **Android Studio très lent**  
  → Fermer les projets inutiles, augmenter la RAM, fermer les applications lourdes.

- **Pas d’appareil dans `adb devices`**  
  → Vérifier câble USB, drivers, débogage USB activé, ou que l’AVD est bien lancé.

- **Erreur de synchronisation Gradle**  
  → Vérifier proxy, connexion internet, versions des plugins.

---

👨‍🏫 **Enseignant** : A. GUEDDES – Module Android ISITCOM 2025-2026
