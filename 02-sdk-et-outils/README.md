# Module 2 : SDK Android et Outils de Développement

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Installer Android Studio correctement sur votre machine
- Configurer le SDK Manager et télécharger les APIs nécessaires
- Créer et configurer des émulateurs Android (AVD)
- Comprendre le rôle de Gradle dans la compilation
- Utiliser ADB pour déboguer sur appareil réel

## 📋 Prérequis Système

Avant d'installer Android Studio, vérifiez que votre machine répond aux exigences :

| Composant | Minimum | Recommandé |
|-----------|---------|------------|
| **RAM** | 8 GB | 16 GB |
| **Espace disque** | 10 GB libre | 20 GB libre |
| **Processeur** | Intel/AMD 64-bit | Multi-core |
| **Résolution écran** | 1280x800 | 1920x1080 |
| **Java** | JDK 17+ | JDK 21 |
| **OS** | Windows 10+ / macOS 10.14+ / Linux | - |

⚡ **Accélération matérielle** (pour émulateur) :
- Windows : Intel HAXM ou WHPX (Hyper-V)
- macOS : Hypervisor Framework (intégré)
- Linux : KVM

## 📥 Installation Android Studio

### Étape 1 : Téléchargement

1. Rendez-vous sur [developer.android.com/studio](https://developer.android.com/studio)
2. Cliquez sur **"Download Android Studio"**
3. Acceptez les conditions d'utilisation
4. Téléchargement : ~1.1 GB

### Étape 2 : Installation selon OS

#### 🪟 Windows

```powershell
# 1. Exécuter le fichier .exe téléchargé
# 2. Suivre l'assistant d'installation
# 3. Sélectionner les composants :
   ✅ Android Studio
   ✅ Android SDK
   ✅ Android Virtual Device
   ✅ Performance (Intel HAXM)

# 4. Choisir le dossier d'installation
# Par défaut : C:\Program Files\Android\Android Studio

# 5. Finaliser l'installation
```

#### 🍎 macOS

```bash
# 1. Ouvrir le fichier .dmg téléchargé
# 2. Glisser Android Studio dans Applications
# 3. Lancer depuis Applications
# 4. Autoriser dans Préférences Système > Sécurité
```

#### 🐧 Linux (Ubuntu/Debian)

```bash
# 1. Extraire l'archive
sudo tar -xvzf android-studio-*.tar.gz -C /opt/

# 2. Lancer le script
cd /opt/android-studio/bin
./studio.sh

# 3. Créer un raccourci (optionnel)
sudo nano /usr/share/applications/android-studio.desktop
```

### Étape 3 : Configuration initiale

Au premier lancement, l'assistant de configuration se lance automatiquement :

1. **Welcome** : Next
2. **Install Type** : 
   - 📌 **Standard** (recommandé) : Configuration par défaut
   - Custom : Configuration avancée
3. **Select UI Theme** : 
   - Darcula (sombre) 🌙
   - Light (clair) ☀️
4. **Verify Settings** : Vérifier les composants
5. **Downloading Components** : Patience (~2-3 GB)
6. **Finish** : Android Studio est prêt ! 🎉

## 🛠️ SDK Manager

### Qu'est-ce que le SDK ?

Le **Software Development Kit (SDK)** est l'ensemble des outils et bibliothèques nécessaires pour développer des applications Android.

### Accéder au SDK Manager

**Méthode 1** : Menu
```
Tools → SDK Manager
```

**Méthode 2** : Icône
```
Clic sur l'icône 🔧 dans la barre d'outils
```

**Méthode 3** : Écran d'accueil
```
More Actions → SDK Manager
```

### SDK Platforms (Versions Android)

Dans l'onglet **SDK Platforms**, cochez :

| Version | API | Priorité | Raison |
|---------|-----|----------|--------|
| Android 15.0 (V) | 35 | ⭐⭐⭐ | Dernière stable (2024) |
| Android 14.0 (U) | 34 | ⭐⭐⭐ | Largement adoptée |
| Android 13.0 (T) | 33 | ⭐⭐ | Encore utilisée |
| Android 10.0 (Q) | 29 | ⭐⭐ | Support dark theme |
| Android 7.0 (Nougat) | 24 | ⭐⭐⭐ | Base recommandée (minSdk) |

💡 **Astuce** : Cochez "Show Package Details" pour voir les sous-composants.

### SDK Tools

Dans l'onglet **SDK Tools**, vérifiez que ces outils sont installés :

#### Essentiels ✅
- ✅ **Android SDK Build-Tools** (dernière version)
- ✅ **Android SDK Platform-Tools** (adb, fastboot)
- ✅ **Android SDK Tools** (obsolète mais parfois utile)
- ✅ **Android Emulator** (pour AVD)
- ✅ **Intel/AMD Emulator Accelerator** (HAXM/Hypervisor)

#### Optionnels
- ☑️ **Google Play Services**
- ☑️ **Google USB Driver** (Windows uniquement)
- ☑️ **Android SDK Command-line Tools**

### Emplacement du SDK

Par défaut :
- **Windows** : `C:\Users\<username>\AppData\Local\Android\Sdk`
- **macOS** : `~/Library/Android/sdk`
- **Linux** : `~/Android/Sdk`

⚙️ Pour changer l'emplacement : Edit → "Android SDK Location"

## 📱 Android Virtual Device (AVD)

### Qu'est-ce qu'un AVD ?

Un **AVD** est un émulateur Android qui simule un appareil physique. Il permet de tester l'application sans téléphone réel.

### Créer un AVD

#### Méthode 1 : Device Manager

1. **Ouvrir Device Manager**
   ```
   Tools → Device Manager
   Ou icône 📱 dans la barre
   ```

2. **Create Virtual Device**

3. **Choisir le matériel**
   - Catégories : Phone, Tablet, Wear OS, TV, Automotive
   - Recommandé pour débuter : **Pixel 6** (Phone)
   - Caractéristiques affichées : Taille écran, résolution, densité

4. **Sélectionner une image système**
   
   | Colonne | Description |
   |---------|-------------|
   | **Release Name** | Version Android (ex: Tiramisu = 13) |
   | **API Level** | Numéro d'API (ex: 33) |
   | **ABI** | Architecture processeur |
   | **Target** | Google APIs / Google Play |

   **Recommandation** :
   - ✅ Choisir **x86_64** (plus rapide avec accélération)
   - ✅ Préférer **Google APIs** (accès Play Store en test)
   - 📥 Télécharger si pas déjà installé (~1-2 GB)

5. **Configurer l'AVD**
   
   **AVD Name** : `Pixel_6_API_35` (exemple)
   
   **Startup Orientation** :
   - Portrait (📱)
   - Landscape (📱→)

   **Advanced Settings** (optionnel) :
   ```
   RAM : 2048 MB (minimum) → 4096 MB (recommandé)
   VM Heap : 256 MB → 512 MB
   Internal Storage : 2048 MB → 4096 MB
   SD Card : 512 MB (optionnel)
   Graphics : Automatic → Hardware (plus rapide)
   ```

6. **Finish** → AVD créé ! ✅

### Lancer un AVD

**Option 1** : Depuis Device Manager
```
▶️ Clic sur le bouton Play à côté de l'AVD
```

**Option 2** : Depuis la barre d'outils
```
Sélectionner l'AVD dans le menu déroulant → Run
```

### Contrôles de l'émulateur

Barre latérale de l'émulateur :

| Icône | Fonction | Raccourci |
|-------|----------|------------|
| 🔙 | Retour (Back) | Esc |
| 🏠 | Accueil (Home) | Home |
| ⏹️ | Tâches récentes | - |
| 🔊 | Volume +/- | - |
| 🔄 | Rotation écran | Ctrl+F11 / Cmd+Left |
| 📸 | Capture écran | - |
| 📍 | Position GPS | - |
| ⚙️ | Paramètres étendus | ... |

### Snapshots (Démarrage rapide)

Les snapshots sauvegardent l'état de l'émulateur :

✅ **Quick Boot** : Activé par défaut
- Premier démarrage : ~60 secondes
- Démarrages suivants : ~6 secondes

🔧 Configuration : Device Manager → ⚙️ → Snapshots

## 🔧 Gradle : Système de Compilation

### Qu'est-ce que Gradle ?

**Gradle** est l'outil de build automation utilisé par Android Studio. Il transforme votre code Java/Kotlin + ressources en fichier APK/AAB installable.

### Fichiers Gradle importants

#### 1. `settings.gradle` (Projet)

```gradle
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "MonAppli"
include ':app'
```

#### 2. `build.gradle` (Project)

```gradle
plugins {
    id 'com.android.application' version '8.2.0' apply false
}
```

#### 3. `build.gradle` (Module: app)

Le plus important ! Configuration de l'application :

```gradle
plugins {
    id 'com.android.application'
}

android {
    namespace 'tn.isitcom.monappli'
    compileSdk 35  // API pour compiler

    defaultConfig {
        applicationId "tn.isitcom.monappli"
        minSdk 24      // API minimum supportée
        targetSdk 35   // API ciblée
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

### Synchronisation Gradle

À chaque modification de `build.gradle` :

```
📊 "Sync Now" apparaît en haut
→ Cliquer pour synchroniser
→ Attendre la fin (barre de progression en bas)
```

⚠️ **Erreurs fréquentes** :
- ❌ Version incompatible : Mettre à jour Gradle
- ❌ Dépendance introuvable : Vérifier repositories
- ❌ Proxy/Firewall : Configurer dans Settings → HTTP Proxy

## 🔌 ADB : Android Debug Bridge

### Qu'est-ce qu'ADB ?

**ADB** est un outil en ligne de commande pour communiquer avec un appareil Android (réel ou émulateur).

Emplacement : `<SDK>/platform-tools/adb`

### Commandes ADB utiles

```bash
# Lister les appareils connectés
adb devices

# Installer une APK
adb install chemin/vers/app.apk

# Désinstaller une app
adb uninstall tn.isitcom.monappli

# Afficher les logs en temps réel
adb logcat

# Copier fichier vers appareil
adb push fichier.txt /sdcard/

# Récupérer fichier depuis appareil
adb pull /sdcard/fichier.txt .

# Shell interactif
adb shell

# Redémarrer appareil
adb reboot

# Capturer écran
adb exec-out screencap -p > screenshot.png
```

### Activer le débogage USB (appareil réel)

1. **Activer Options développeur**
   ```
   Paramètres → À propos du téléphone
   → Appuyer 7 fois sur "Numéro de build"
   ```

2. **Activer Débogage USB**
   ```
   Paramètres → Options développeur
   → Activer "Débogage USB"
   ```

3. **Connecter via USB**
   - Brancher le câble
   - Autoriser l'ordinateur sur le téléphone

4. **Vérifier**
   ```bash
   adb devices
   # Doit afficher votre appareil
   ```

## 🎓 Exercices Pratiques

### Exercice 1 : Installation complète

✅ **Checklist** :
- [ ] Android Studio installé
- [ ] SDK API 24, 34, 35 téléchargées
- [ ] Émulateur installé
- [ ] Accélération matérielle activée
- [ ] AVD créé et lancé avec succès

### Exercice 2 : Créer 2 AVD différents

Créez deux émulateurs :
1. **Pixel 6 - API 35** (moderne)
2. **Nexus 5 - API 24** (ancien, pour tester compatibilité)

### Exercice 3 : Manipuler avec ADB

Utilisez ADB pour :
1. Lister vos appareils connectés
2. Afficher les logs du système
3. Installer une application (télécharger une APK test)
4. Faire une capture d'écran

## 📚 Ressources Complémentaires

- [📖 Guide d'installation officiel](https://developer.android.com/studio/install)
- [🎬 Vidéo : Installer Android Studio](https://www.youtube.com/watch?v=0zx_eFyHRU0)
- [📘 Documentation SDK Manager](https://developer.android.com/studio/intro/update)
- [🔧 Guide ADB complet](https://developer.android.com/studio/command-line/adb)
- [⚡ Accélération émulateur](https://developer.android.com/studio/run/emulator-acceleration)

## ❓ Quiz de Validation

Testez vos connaissances ! [Accéder au quiz](./quiz.md)

## ➡️ Module suivant

[Module 3 : Premier Projet HelloWorld](../03-creation-premier-projet/README.md)

---

👨‍🏫 **Enseignant** : A. GUEDDES | ISITCom 2025-2026