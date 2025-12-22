# 🔧 Guide de Dépannage : SDK et Outils Android

## Table des Matières

1. [Problèmes d'Installation](#problèmes-dinstallation)
2. [Problèmes SDK](#problèmes-sdk)
3. [Problèmes Émulateur](#problèmes-émulateur)
4. [Problèmes ADB](#problèmes-adb)
5. [Problèmes Gradle](#problèmes-gradle)
6. [Problèmes de Performance](#problèmes-de-performance)

---

## Problèmes d'Installation

### ❌ Erreur : "Unable to access Android SDK add-on list"

**Cause** : Problème de connexion ou proxy.

**Solutions** :

```bash
# 1. Vérifier la connexion Internet
ping google.com

# 2. Configurer proxy dans Android Studio
# File → Settings → Appearance & Behavior → System Settings → HTTP Proxy
# Choisir "Manual proxy configuration" si nécessaire

# 3. Forcer HTTP au lieu de HTTPS
# Dans gradle.properties
android.injected.build.model.v2.use.http=true
```

---

### ❌ Erreur : "Android Studio installation is corrupt"

**Solution** : Réinstallation propre

```powershell
# Windows
# 1. Désinstaller Android Studio
# 2. Supprimer les dossiers
rmdir /s C:\Program Files\Android
rmdir /s %USERPROFILE%\.android
rmdir /s %USERPROFILE%\.AndroidStudio*
rmdir /s %USERPROFILE%\AppData\Local\Android
rmdir /s %USERPROFILE%\AppData\Roaming\Google\AndroidStudio*

# 3. Réinstaller
```

```bash
# Linux/Mac
rm -rf ~/Android
rm -rf ~/.android
rm -rf ~/.AndroidStudio*
rm -rf ~/Library/Android  # Mac seulement

# Réinstaller Android Studio
```

---

## Problèmes SDK

### ❌ "SDK location not found. Define location with sdk.dir..."

**Solution 1** : Définir dans `local.properties`

```properties
# Créer/éditer local.properties à la racine du projet

# Windows
sdk.dir=C:\\Users\\[VotreNom]\\AppData\\Local\\Android\\Sdk

# Mac
sdk.dir=/Users/[VotreNom]/Library/Android/sdk

# Linux
sdk.dir=/home/[VotreNom]/Android/Sdk
```

**Solution 2** : Variable d'environnement

```bash
# Linux/Mac (~/.bashrc ou ~/.zshrc)
export ANDROID_HOME=$HOME/Android/Sdk
export ANDROID_SDK_ROOT=$ANDROID_HOME
export PATH=$PATH:$ANDROID_HOME/tools
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Recharger
source ~/.bashrc
```

```powershell
# Windows (PowerShell admin)
[System.Environment]::SetEnvironmentVariable(
    'ANDROID_HOME', 
    'C:\Users\[Nom]\AppData\Local\Android\Sdk', 
    'User'
)
```

---

### ❌ "Failed to install the following Android SDK packages"

**Causes possibles** :
- Espace disque insuffisant
- Permissions manquantes
- Connexion interrompue

**Solutions** :

```bash
# 1. Vérifier espace disque
# Windows
wmic logicaldisk get size,freespace,caption

# Linux/Mac
df -h

# 2. Réparer permissions (Linux/Mac)
sudo chown -R $USER:$USER ~/Android/Sdk
chmod -R 755 ~/Android/Sdk

# 3. Installer manuellement via sdkmanager
sdkmanager "platforms;android-35"
sdkmanager "build-tools;35.0.0"
```

---

## Problèmes Émulateur

### ❌ Émulateur très lent / freeze

**Diagnostic** :

```bash
# Vérifier virtualisation activée

# Windows (PowerShell)
Get-ComputerInfo | Select-Object HyperVisorPresent, HyperVRequirementVirtualizationFirmwareEnabled

# Linux
egrep -c '(vmx|svm)' /proc/cpuinfo
# Si 0 : virtualisation désactivée
```

**Solutions par ordre de priorité** :

#### 1. Activer l'accélération matérielle

**Windows** :
```powershell
# Désactiver Hyper-V (conflits avec HAXM)
bcdedit /set hypervisorlaunchtype off
# Redémarrer

# Installer HAXM
# SDK Manager → SDK Tools → Intel x86 Emulator Accelerator (HAXM)
```

**Linux** :
```bash
# Installer KVM
sudo apt install qemu-kvm libvirt-daemon-system

# Ajouter utilisateur aux groupes
sudo usermod -aG kvm $USER
sudo usermod -aG libvirt $USER

# Vérifier
kvm-ok
```

#### 2. Optimiser les paramètres AVD

- Graphics : **Hardware - GLES 2.0**
- Boot option : **Quick Boot** (avec snapshot)
- RAM : **4096 MB** (si votre PC a 16GB+)
- Multi-Core CPU : **4 cores**

#### 3. Utiliser image x86_64

⚠️ **Jamais d'images ARM** (ex: armeabi-v7a) pour l'émulateur !

---

### ❌ "The emulator process has terminated"

**Solution 1** : Augmenter RAM disponible

```bash
# Éditer config.ini de l'AVD
# Chemin : ~/.android/avd/[AVD_NAME].avd/config.ini

hw.ramSize=2048  # Réduire à 2048 ou 1536
```

**Solution 2** : Mode Software rendering

```bash
# Lancer avec paramètre
emulator -avd Pixel6_API35 -gpu swiftshader_indirect
```

**Solution 3** : Recréer l'AVD avec Cold Boot

---

### ❌ "PANIC: Cannot find AVD system path"

**Solution** :

```bash
# Définir variable ANDROID_AVD_HOME

# Linux/Mac
export ANDROID_AVD_HOME=$HOME/.android/avd

# Windows
set ANDROID_AVD_HOME=%USERPROFILE%\.android\avd

# Vérifier existence
ls $ANDROID_AVD_HOME  # Linux/Mac
dir %ANDROID_AVD_HOME%  # Windows
```

---

## Problèmes ADB

### ❌ "adb: command not found" / "adb n'est pas reconnu"

**Solution** : Ajouter au PATH

```bash
# Linux/Mac (~/.bashrc ou ~/.zshrc)
export PATH=$PATH:$ANDROID_HOME/platform-tools
source ~/.bashrc

# Vérifier
which adb
adb version
```

```powershell
# Windows (GUI)
# Système → Paramètres système avancés → Variables d'environnement
# Path → Modifier → Nouveau
# Ajouter : C:\Users\[Nom]\AppData\Local\Android\Sdk\platform-tools

# Ou PowerShell admin
[System.Environment]::SetEnvironmentVariable(
    'Path',
    $env:Path + ';C:\Users\[Nom]\AppData\Local\Android\Sdk\platform-tools',
    'User'
)

# Redémarrer terminal
```

---

### ❌ "adb server version doesn't match this client"

**Solution** :

```bash
# Tuer tous les processus ADB
adb kill-server

# Windows
taskkill /F /IM adb.exe

# Linux/Mac
killall adb

# Redémarrer
adb start-server
adb devices
```

---

### ❌ "device offline" ou "device unauthorized"

**Solution** :

```bash
# 1. Révoquer autorisations USB
adb kill-server
rm ~/.android/adbkey*  # Linux/Mac
del %USERPROFILE%\.android\adbkey*  # Windows

# 2. Redémarrer ADB
adb start-server

# 3. Reconnecter appareil
# → Accepter popup "Autoriser débogage USB" sur le téléphone

# 4. Vérifier
adb devices
```

---

## Problèmes Gradle

### ❌ "Gradle sync failed: Connection timed out"

**Solutions** :

```bash
# 1. Mode offline Gradle
# File → Settings → Build → Gradle
# Cocher "Offline work"

# 2. Augmenter timeout
# gradle.properties
org.gradle.daemon.idletimeout=10800000
org.gradle.jvmargs=-Xmx4096m -XX:MaxPermSize=1024m

# 3. Utiliser mirror Maven (Chine/pays avec restrictions)
# build.gradle (project)
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/google' }
        maven { url 'https://maven.aliyun.com/repository/jcenter' }
        google()
        mavenCentral()
    }
}
```

---

### ❌ "Could not resolve all dependencies"

**Solution** :

```bash
# 1. Nettoyer cache
./gradlew clean

# 2. Invalider caches Android Studio
# File → Invalidate Caches / Restart → Invalidate and Restart

# 3. Supprimer dossiers build
find . -type d -name build -exec rm -rf {} +  # Linux/Mac
for /d /r . %d in (build) do @if exist "%d" rd /s /q "%d"  # Windows

# 4. Resync avec refresh
./gradlew clean build --refresh-dependencies
```

---

### ❌ "Unsupported class file major version 65"

**Cause** : Version Java incompatible.

**Solution** :

```groovy
// build.gradle (Module: app)
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
}
```

```bash
# Vérifier version Java utilisée
java -version

# Android Studio utilise son JDK embarqué
# File → Project Structure → SDK Location → JDK location
# Utiliser "Embedded JDK" (recommandé)
```

---

## Problèmes de Performance

### 🐌 Android Studio très lent

**Optimisations** :

#### 1. Augmenter RAM allouée

```bash
# Help → Edit Custom VM Options
# Modifier studio.vmoptions

-Xms2048m
-Xmx8192m
-XX:ReservedCodeCacheSize=1024m
-XX:+UseG1GC
-XX:SoftRefLRUPolicyMSPerMB=50
```

#### 2. Désactiver fonctionnalités inutilisées

```
File → Settings → Plugins
# Désactiver :
- Markdown (si non utilisé)
- GitHub Copilot (si non utilisé)
- Designer (si vous codez XML manuellement)
```

#### 3. Exclure dossiers de l'indexation

```
File → Settings → Editor → File Types → Ignored Files and Folders
# Ajouter :
- build
- .gradle
- .idea
```

#### 4. Mode Power Save

```
File → Power Save Mode
# Désactive indexation en arrière-plan
# À activer si batterie faible
```

---

### 🐌 Compilation Gradle lente

**Optimisations gradle.properties** :

```properties
# Activer daemon Gradle
org.gradle.daemon=true

# Compilation parallèle
org.gradle.parallel=true

# Augmenter RAM Gradle
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m -XX:+HeapDumpOnOutOfMemoryError

# Configuration à la demande
org.gradle.configureondemand=true

# Cache build
org.gradle.caching=true

# AndroidX (projets modernes)
android.useAndroidX=true
android.enableJetifier=true
```

---

## 🆘 Ressources d'Aide

### Documentation officielle
- [Troubleshoot Android Studio](https://developer.android.com/studio/troubleshoot)
- [Troubleshoot Emulator](https://developer.android.com/studio/run/emulator-troubleshooting)
- [Known Issues](https://developer.android.com/studio/known-issues)

### Communautés
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android-studio)
- [Reddit r/androiddev](https://reddit.com/r/androiddev)
- [Android Developers Discord](https://discord.gg/androiddev)

### Logs utiles

```bash
# Logs Android Studio
# Help → Show Log in Explorer/Finder

# Logs émulateur
~/.android/avd/[AVD_NAME].avd/

# Logs ADB
adb logcat > logcat.txt
```

---

## 📋 Checklist de Dépannage Systématique

Avant de chercher de l'aide :

- [ ] Redémarrer Android Studio
- [ ] Invalider caches (Invalidate Caches / Restart)
- [ ] Nettoyer projet (Build → Clean Project)
- [ ] Resynchroniser Gradle (File → Sync Project)
- [ ] Vérifier connexion Internet
- [ ] Vérifier espace disque disponible
- [ ] Consulter logs (Help → Show Log)
- [ ] Chercher l'erreur sur Stack Overflow

---

[Retour au Module 2](./README.md)