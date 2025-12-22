# Module 2 : SDK Android et Outils de Développement

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Installer Android Studio sur votre système
- Configurer le SDK Android avec les bonnes versions
- Créer et gérer des émulateurs (AVD)
- Utiliser les outils de débogage (ADB, Logcat)
- Résoudre les problèmes d'installation courants

## 📋 Prérequis Système

### Configuration Minimale

| Composant | Minimum | Recommandé |
|-----------|---------|------------|
| **RAM** | 8 GB | 16 GB |
| **Disque** | 10 GB libre | 20 GB (SSD) |
| **Processeur** | Intel i5 / AMD Ryzen 5 | Intel i7 / Ryzen 7 |
| **Résolution** | 1280x800 | 1920x1080 |
| **OS** | Windows 10, macOS 10.14, Ubuntu 18.04 | Dernières versions |

### Logiciels requis

- **JDK 17+** (Java Development Kit)
- **Connexion Internet** pour téléchargement (~3 GB)
- **Accélération matérielle** : Intel HAXM (Windows/Mac) ou KVM (Linux)

## 📥 Installation d'Android Studio

### Étape 1 : Téléchargement

1. Accédez au site officiel : [developer.android.com/studio](https://developer.android.com/studio)
2. Cliquez sur **"Download Android Studio"**
3. Acceptez les termes et conditions
4. Téléchargez la version correspondant à votre OS

**Tailles approximatives** :
- Windows : ~1.1 GB
- macOS : ~1.0 GB
- Linux : ~1.0 GB

### Étape 2 : Installation par OS

#### 🪟 Windows

```powershell
# 1. Double-cliquer sur android-studio-2024.2.1.X-windows.exe
# 2. Suivre l'assistant d'installation
# 3. Installer Android SDK
# 4. Installer Android Virtual Device
# 5. Installer Intel HAXM (accélération émulateur)
```

**Chemin d'installation par défaut** :
```
C:\Program Files\Android\Android Studio
```

**SDK par défaut** :
```
C:\Users\[VotreNom]\AppData\Local\Android\Sdk
```

#### 🍎 macOS

```bash
# 1. Ouvrir le fichier .dmg téléchargé
# 2. Glisser Android Studio dans Applications
# 3. Lancer Android Studio
# 4. Suivre l'assistant de configuration
```

**Chemin d'installation** :
```
/Applications/Android Studio.app
```

**SDK par défaut** :
```
~/Library/Android/sdk
```

#### 🐧 Linux (Ubuntu/Debian)

```bash
# Méthode 1 : Snap (recommandé)
sudo snap install android-studio --classic

# Méthode 2 : Archive tar.gz
cd ~/Downloads
tar -xvzf android-studio-2024.2.1.X-linux.tar.gz
sudo mv android-studio /opt/
cd /opt/android-studio/bin
./studio.sh
```

**SDK par défaut** :
```
~/Android/Sdk
```

### Étape 3 : Premier Lancement

Lors du premier lancement, Android Studio lance un **assistant de configuration** :

1. **Import Settings** : Choisir "Do not import settings"
2. **Data Sharing** : Accepter ou refuser selon préférence
3. **Install Type** : Sélectionner **"Standard"**
4. **UI Theme** : Light ou Darcula (thème sombre)
5. **Verify Settings** : Vérifier l'emplacement du SDK
6. **Download Components** : Patientez (~2 GB à télécharger)

## 🔧 Configuration du SDK Manager

### Accéder au SDK Manager

**Depuis l'écran d'accueil** :
```
Configure → SDK Manager
```

**Depuis un projet ouvert** :
```
Tools → SDK Manager
```
ou raccourci : `Ctrl+Alt+S` (Windows/Linux), `Cmd+,` (Mac)

### SDK Platforms (Onglet 1)

Cochez les versions Android nécessaires :

| Version | API | Pourquoi l'installer ? |
|---------|-----|------------------------|
| Android 15.0 (V) | 35 | 🟢 **Dernière version stable** |
| Android 14.0 (U) | 34 | 🟢 **Version précédente** |
| Android 13.0 (T) | 33 | 🟡 Encore répandue |
| Android 12.0 (S) | 31 | 🟡 Compatibilité |
| Android 7.0 (Nougat) | 24 | 🟢 **minSdk recommandé** |

**Recommandation 2025** :
- ✅ **API 35** (targetSdk)
- ✅ **API 34** (test)
- ✅ **API 24** (minSdk - 95% couverture)

### SDK Tools (Onglet 2)

Cochez les outils essentiels :

- ✅ **Android SDK Build-Tools** (dernière version)
- ✅ **Android SDK Command-line Tools**
- ✅ **Android Emulator**
- ✅ **Android SDK Platform-Tools** (ADB, fastboot)
- ✅ **Intel x86 Emulator Accelerator (HAXM)** (Windows/Mac)
- ✅ **Google Play Services**
- ⬜ **NDK** (optionnel, pour C/C++)
- ⬜ **CMake** (optionnel, pour C/C++)

### Validation de l'installation

```bash
# Vérifier la version d'ADB
adb version

# Lister les appareils connectés
adb devices

# Vérifier l'emplacement du SDK
echo $ANDROID_HOME  # Linux/Mac
echo %ANDROID_HOME% # Windows
```

## 📱 Création d'un Émulateur (AVD)

### Pourquoi un émulateur ?

- ✅ Tester sans appareil physique
- ✅ Tester plusieurs versions Android
- ✅ Tester différentes résolutions
- ✅ Snapshots pour démarrage rapide
- ⚠️ Performances inférieures à un vrai téléphone

### Étapes de création

#### 1. Ouvrir AVD Manager

**Depuis l'écran d'accueil** :
```
Configure → AVD Manager
```

**Depuis un projet** :
```
Tools → Device Manager
```
ou icône 📱 dans la barre d'outils

#### 2. Créer un nouveau Virtual Device

Cliquer sur **"Create Virtual Device"**

#### 3. Choisir un appareil

**Recommandations** :

| Appareil | Résolution | Cas d'usage |
|----------|------------|-------------|
| **Pixel 6** | 1080x2400 | 🟢 Développement standard |
| Pixel 8 Pro | 1344x2992 | Test haute résolution |
| Nexus 5 | 1080x1920 | Ancien format |
| Pixel Tablet | 2560x1600 | Test tablette |

#### 4. Télécharger une System Image

Choisir une **image système** à télécharger :

**Onglet Recommended** :
- ✅ **API 35** (Android 15) - Google APIs
- ✅ **API 34** (Android 14) - Google Play
- ✅ **API 24** (Android 7.0) - Test compatibilité

**Types d'images** :
- **Google Play** : Inclut Play Store (testing production)
- **Google APIs** : APIs Google sans Play Store
- **AOSP** : Android pur sans services Google

⚡ **Important** : Choisir architecture **x86_64** pour performances optimales

#### 5. Configuration AVD

**Nom** : Donner un nom explicite (ex: `Pixel6_API35_PlayStore`)

**Paramètres avancés** :
- **RAM** : 2048 MB (minimum) à 4096 MB
- **VM Heap** : 512 MB
- **Internal Storage** : 2048 MB
- **SD Card** : 512 MB (optionnel)
- **Graphics** : Hardware - GLES 2.0 (recommandé)
- **Boot option** : Cold boot (ou Quick boot avec snapshot)

#### 6. Lancer l'émulateur

Cliquer sur ▶️ (Play) dans AVD Manager

**Première exécution** : 1-2 minutes (cold boot)
**Avec snapshot** : 10-20 secondes

### Raccourcis clavier émulateur

| Touche | Action |
|--------|--------|
| `Ctrl+M` | Menu |
| `Ctrl+H` | Home |
| `Ctrl+← / →` | Back / Rotation |
| `Ctrl+F11/F12` | Rotation gauche/droite |
| `F11` | Plein écran |

## 🛠️ Outils de Développement

### ADB (Android Debug Bridge)

**Commandes essentielles** :

```bash
# Lister appareils connectés
adb devices

# Installer une APK
adb install app.apk

# Désinstaller une app
adb uninstall com.example.app

# Voir les logs en temps réel
adb logcat

# Filtrer les logs
adb logcat | grep "MainActivity"

# Redémarrer ADB server
adb kill-server
adb start-server

# Accéder au shell Android
adb shell

# Copier fichier vers l'appareil
adb push fichier.txt /sdcard/

# Récupérer fichier depuis appareil
adb pull /sdcard/fichier.txt

# Prendre screenshot
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png

# Enregistrer vidéo (max 180s)
adb shell screenrecord /sdcard/demo.mp4
```

### Logcat (Logs système)

**Dans Android Studio** :
```
View → Tool Windows → Logcat
```

**Niveaux de logs** :
- 🟣 **Verbose** : Tout (très verbeux)
- 🔵 **Debug** : Informations de débogage
- 🟢 **Info** : Messages informatifs
- 🟡 **Warning** : Avertissements
- 🔴 **Error** : Erreurs
- ⚫ **Assert** : Erreurs critiques

**Filtrer les logs** :
```java
// Dans votre code
Log.v("TAG", "Message verbose");
Log.d("TAG", "Message debug");
Log.i("TAG", "Message info");
Log.w("TAG", "Message warning");
Log.e("TAG", "Message erreur");
```

### Gradle

**Synchroniser le projet** :
```
File → Sync Project with Gradle Files
```
ou icône 🐘 dans la barre d'outils

**Nettoyer le build** :
```bash
./gradlew clean
```

**Compiler l'app** :
```bash
./gradlew assembleDebug
```

## 🔥 Accélération Matérielle

### Vérifier la virtualisation

#### Windows

```powershell
# Vérifier dans le Gestionnaire des tâches
# Onglet "Performances" → CPU → Virtualisation : Activée

# Ou avec PowerShell
systeminfo | findstr /i "Virtualization"
```

**Activer dans le BIOS** :
- Redémarrer → F2/F10/Del (selon fabricant)
- Chercher "Intel VT-x" ou "AMD-V"
- Activer et sauvegarder

#### macOS

La virtualisation est activée par défaut sur Mac (Apple Silicon ou Intel).

#### Linux

```bash
# Vérifier KVM
egrep -c '(vmx|svm)' /proc/cpuinfo
# Si > 0 : virtualisation supportée

# Installer KVM
sudo apt install qemu-kvm libvirt-daemon-system libvirt-clients bridge-utils

# Ajouter utilisateur au groupe
sudo usermod -aG kvm $USER
sudo usermod -aG libvirt $USER

# Redémarrer la session
```

## ❌ Problèmes Fréquents et Solutions

### Problème 1 : "SDK location not found"

**Solution** :
1. File → Project Structure → SDK Location
2. Définir manuellement le chemin du SDK
3. Exemple Windows : `C:\Users\[Nom]\AppData\Local\Android\Sdk`

### Problème 2 : Émulateur très lent

**Solutions** :
- ✅ Activer HAXM/KVM (voir section accélération)
- ✅ Utiliser image x86_64 (pas ARM)
- ✅ Allouer plus de RAM (4 GB)
- ✅ Choisir Graphics: Hardware - GLES 2.0
- ✅ Fermer autres applications

### Problème 3 : "ADB not found"

**Solution** :
```bash
# Ajouter platform-tools au PATH

# Windows (PowerShell admin)
[System.Environment]::SetEnvironmentVariable('Path', $env:Path + ';C:\Users\[Nom]\AppData\Local\Android\Sdk\platform-tools', 'User')

# Linux/Mac (~/.bashrc ou ~/.zshrc)
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
```

### Problème 4 : Gradle sync failed

**Solutions** :
1. File → Invalidate Caches / Restart
2. Vérifier connexion Internet
3. Supprimer `.gradle` dans le projet
4. Build → Clean Project → Rebuild Project

### Problème 5 : Émulateur ne démarre pas

**Solutions** :
- Vérifier virtualisation activée
- Désactiver Hyper-V (Windows) : `bcdedit /set hypervisorlaunchtype off`
- Recréer l'AVD avec moins de RAM
- Choisir Cold Boot au lieu de Quick Boot

## ✅ Checklist de Validation

Avant de passer au module suivant, vérifiez :

- [ ] Android Studio installé et fonctionnel
- [ ] SDK API 35, 34, 24 téléchargés
- [ ] SDK Build-Tools et Platform-Tools installés
- [ ] Au moins 1 AVD créé et fonctionnel
- [ ] ADB fonctionne en ligne de commande
- [ ] Accélération matérielle activée
- [ ] Premier lancement émulateur réussi

## 📚 Exercices Pratiques

Voir le fichier [exercices.md](./exercices.md) pour pratiquer.

## 🔗 Ressources

- [Documentation Android Studio](https://developer.android.com/studio/intro)
- [SDK Manager](https://developer.android.com/studio/intro/update)
- [AVD Manager](https://developer.android.com/studio/run/managing-avds)
- [ADB Documentation](https://developer.android.com/studio/command-line/adb)

## ➡️ Module suivant

[Module 3 : Création du Premier Projet](../03-creation-premier-projet/README.md)

---

👨‍🏫 **Enseignant** : A. GUEDDES | ISITCom 2025-2026