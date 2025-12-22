# 🔧 Guide de Dépannage Android Studio

## Problèmes d'Installation

### 1. Erreur "JDK Not Found"

**Symptôme** :
```
Unable to start the daemon process.
This problem might be caused by incorrect configuration of the daemon.
For example, an unrecognized jvm option is used.
```

**Cause** : JDK manquant ou version incorrecte.

**Solutions** :

**Option A** : Utiliser le JDK embarqué (recommandé)
```bash
# File > Project Structure > SDK Location
# JDK location : /Applications/Android Studio.app/jbr (macOS)
# Ou : C:\Program Files\Android\Android Studio\jbr (Windows)
```

**Option B** : Installer JDK 17
```bash
# Windows (via Chocolatey)
choco install openjdk17

# macOS (via Homebrew)
brew install openjdk@17

# Ubuntu/Debian
sudo apt install openjdk-17-jdk

# Vérifier
java -version
```

---

### 2. Erreur "SDK Not Found"

**Symptôme** :
```
SDK location not found. Define location with sdk.dir in the local.properties file
or with an ANDROID_HOME environment variable.
```

**Solution 1** : Définir dans Android Studio
```
File > Project Structure > SDK Location
-> Android SDK location: /Users/nom/Library/Android/sdk
```

**Solution 2** : Variable d'environnement

**Windows** :
```powershell
# PowerShell (Admin)
[System.Environment]::SetEnvironmentVariable(
    "ANDROID_HOME",
    "C:\Users\$env:USERNAME\AppData\Local\Android\Sdk",
    "User"
)
```

**macOS/Linux** :
```bash
# Ajouter à ~/.zshrc ou ~/.bashrc
export ANDROID_HOME=$HOME/Library/Android/sdk
export PATH=$PATH:$ANDROID_HOME/emulator
export PATH=$PATH:$ANDROID_HOME/platform-tools

# Recharger
source ~/.zshrc
```

---

### 3. Installation Bloquée à 100%

**Symptôme** : "Downloading Components" reste à 100% indéfiniment.

**Causes possibles** :
- Antivirus bloquant
- Proxy/Firewall
- Connexion instable

**Solutions** :

1. **Désactiver antivirus temporairement**

2. **Configurer proxy** (si nécessaire) :
```
File > Settings > Appearance & Behavior > System Settings > HTTP Proxy
-> Manual proxy configuration
   Host: proxy.exemple.com
   Port: 8080
```

3. **Téléchargement manuel** :
```bash
# Télécharger SDK manuellement
# https://developer.android.com/studio#command-tools
wget https://dl.google.com/android/repository/commandlinetools-linux.zip
unzip commandlinetools-linux.zip -d ~/Android/sdk/cmdline-tools/latest
```

---

## Problèmes Gradle

### 4. Gradle Sync Failed

**Symptôme** :
```
Gradle sync failed: Connection timed out: connect
```

**Solutions** :

**1. Invalider caches** :
```
File > Invalidate Caches > Invalidate and Restart
```

**2. Nettoyer projet** :
```bash
# Terminal Android Studio
./gradlew clean
./gradlew build --refresh-dependencies
```

**3. Mettre à jour Gradle** :
```kotlin
// gradle/wrapper/gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.5-bin.zip
```

**4. Mode offline (si connexion instable)** :
```
File > Settings > Build, Execution, Deployment > Gradle
✅ Offline work
```

---

### 5. Erreur "Could not resolve dependency"

**Symptôme** :
```
Could not resolve com.android.tools.build:gradle:8.5.0
```

**Solution** : Ajouter dépôts Google

```kotlin
// settings.gradle.kts
pluginsManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
```

---

### 6. Build Lent (> 5 minutes)

**Solutions d'optimisation** :

**1. Augmenter mémoire Gradle** :
```properties
# gradle.properties
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=1024m
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.daemon=true
```

**2. Activer configuration cache** :
```properties
org.gradle.configuration-cache=true
```

**3. Build Analyzer** :
```
Build > Analyze Build Performance
-> Identifier les tâches lentes
```

---

## Problèmes Émulateur

### 7. Émulateur Ne Démarre Pas

**Symptôme** : Écran noir ou erreur "The emulator process has terminated"

**Cause** : Accélération matérielle non activée.

**Solution Windows (Intel)** :

1. **Vérifier virtualisation BIOS** :
```powershell
systeminfo | findstr "Virtualization"
# Doit afficher "Enabled"
```

2. **Installer HAXM** :
```
SDK Manager > SDK Tools
✅ Intel x86 Emulator Accelerator (HAXM)

# Ou manuellement
cd C:\Users\nom\AppData\Local\Android\Sdk\extras\intel\Hardware_Accelerated_Execution_Manager
intelhaxm-android.exe
```

3. **Si erreur HAXM** (Hyper-V conflit) :
```powershell
# Désactiver Hyper-V (nécessite redémarrage)
bcdedit /set hypervisorlaunchtype off

# Ou utiliser WHPX à la place
SDK Manager > SDK Tools
✅ Windows Hypervisor Platform
```

**Solution Windows (AMD)** :
```powershell
# Activer Windows Hypervisor Platform
Enable-WindowsOptionalFeature -Online -FeatureName HypervisorPlatform

# AVD Manager > Edit AVD
# Graphics: Software - GLES 2.0
```

**Solution Linux** :
```bash
# Installer KVM
sudo apt install qemu-kvm libvirt-daemon-system libvirt-clients bridge-utils

# Ajouter user au groupe kvm
sudo usermod -aG kvm $USER
sudo usermod -aG libvirt $USER

# Vérifier
kvm-ok
# Doit afficher "KVM acceleration can be used"

# Relancer session
```

**Solution macOS** :
```bash
# macOS utilise Hypervisor Framework nativement
# Si problème, vérifier permissions
sudo chown -R $(whoami) ~/Library/Android
```

---

### 8. Émulateur Très Lent

**Optimisations** :

**1. Activer Quick Boot** :
```
AVD Manager > Edit AVD > Show Advanced Settings
✅ Boot option: Quick boot
✅ Save quick-boot state for faster launching
```

**2. Réduire résolution** :
```
Edit AVD > Show Advanced Settings
Scaled Density: 240 dpi (au lieu de 420)
```

**3. Désactiver animations** :
```
Émulateur > Settings > Developer options
Window animation scale: OFF
Transition animation scale: OFF
Animator duration scale: OFF
```

**4. Allouer plus de RAM** :
```
Edit AVD > Show Advanced Settings
RAM: 3072 MB (si PC a 16GB+)
```

**5. Graphics Hardware** :
```
Edit AVD
Graphics: Hardware - GLES 2.0
```

---

### 9. Erreur "Insufficient Storage"

**Symptôme** : "Can't install APK: Insufficient storage"

**Solution** :

```bash
# Augmenter stockage interne AVD
AVD Manager > Edit AVD > Show Advanced Settings
Internal Storage: 4096 MB

# Ou nettoyer via ADB
adb shell pm clear com.android.providers.downloads
adb shell rm -rf /data/local/tmp/*
```

---

### 10. Émulateur Perd Connexion Réseau

**Symptôme** : Pas d'accès internet dans l'émulateur.

**Solution 1** : Redémarrer réseau émulateur
```bash
adb root
adb shell svc wifi disable
adb shell svc wifi enable
```

**Solution 2** : DNS alternatifs
```bash
adb shell setprop net.dns1 8.8.8.8
adb shell setprop net.dns2 8.8.4.4
```

**Solution 3** : Proxy émulateur
```
AVD Manager > Edit AVD > Show Advanced Settings
Proxy: Manual
Host: 127.0.0.1
Port: 8888 (selon votre proxy)
```

---

## Problèmes ADB

### 11. ADB Device Offline

**Symptôme** :
```bash
adb devices
# emulator-5554   offline
```

**Solutions** :

```bash
# 1. Redémarrer serveur ADB
adb kill-server
adb start-server
adb devices

# 2. Redémarrer ADB sur émulateur
adb -s emulator-5554 reboot

# 3. Si persistant, supprimer .android
rm -rf ~/.android/adbkey*
adb kill-server
adb start-server
```

---

### 12. Multiple Devices Connected

**Symptôme** : Erreur "more than one device/emulator"

**Solution** : Spécifier appareil

```bash
# Lister appareils
adb devices
# emulator-5554   device
# ZY226XXXXX      device

# Spécifier avec -s
adb -s emulator-5554 install app.apk
adb -s ZY226XXXXX logcat

# Ou via variable
export ANDROID_SERIAL=emulator-5554
adb install app.apk
```

---

### 13. Port 5037 Déjà Utilisé

**Symptôme** :
```
cannot bind 'tcp:5037': Address already in use
```

**Solution** :

**Windows** :
```powershell
# Trouver processus utilisant port 5037
netstat -ano | findstr :5037

# Tuer processus (remplacer PID)
taskkill /PID 12345 /F

# Redémarrer ADB
adb start-server
```

**Linux/macOS** :
```bash
# Trouver PID
lsof -i :5037

# Tuer
kill -9 <PID>

# Redémarrer
adb start-server
```

---

## Problèmes Interface

### 14. Android Studio Freeze

**Solutions** :

**1. Augmenter mémoire IDE** :
```
Help > Edit Custom VM Options

# studio.vmoptions
-Xms2048m
-Xmx8192m
-XX:ReservedCodeCacheSize=1024m
```

**2. Désactiver plugins non utilisés** :
```
File > Settings > Plugins
-> Désactiver plugins inutiles
```

**3. Exclure fichiers indexation** :
```
File > Settings > Editor > File Types
Ignored Files and Folders:
add: *.iml;.idea;.gradle;build;
```

---

### 15. Logcat Ne S'Affiche Pas

**Symptôme** : Panneau Logcat vide.

**Solutions** :

```
# 1. Vérifier filtre
Logcat > Niveau: Verbose
Logcat > Filtre: No Filters

# 2. Redémarrer Logcat
Logcat > Gear icon > Clear logcat
Logcat > Pause/Resume

# 3. Via Terminal
adb logcat -c  # Clear
adb logcat    # Voir logs
```

---

## Problèmes Build

### 16. R.java Not Generated

**Symptôme** : "Cannot resolve symbol R"

**Causes** :
- Erreur XML ressources
- Problème Gradle sync
- Cache corrompu

**Solutions** :

```bash
# 1. Vérifier erreurs XML
Build > Make Project (Ctrl+F9)
-> Corriger erreurs res/*.xml

# 2. Clean + Rebuild
Build > Clean Project
Build > Rebuild Project

# 3. Invalider caches
File > Invalidate Caches > Invalidate and Restart

# 4. Supprimer build
rm -rf app/build
./gradlew clean build
```

---

### 17. Manifest Merge Failed

**Symptôme** :
```
Manifest merger failed : Attribute application@appComponentFactory
```

**Solution** : Ajouter outils fusion

```xml
<!-- AndroidManifest.xml -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">
    
    <application
        tools:replace="android:appComponentFactory"
        android:appComponentFactory="whatever"
        ... >
```

---

## Conseils Prévention

### Maintenance Régulière

**Chaque semaine** :
```bash
# Nettoyer builds
./gradlew clean

# Mettre à jour SDK
SDK Manager > Check for updates

# Nettoyer caches Gradle
rm -rf ~/.gradle/caches
```

**Chaque mois** :
```
File > Invalidate Caches > Invalidate and Restart

Help > Check for Updates
```

**Sauvegardes** :
```bash
# Sauvegarder AVDs
cp -r ~/.android/avd backup_avd/

# Sauvegarder settings
cp -r ~/.AndroidStudio* backup_settings/
```

---

## Ressources Supplémentaires

- [Issue Tracker Android](https://issuetracker.google.com/issues?q=componentid:192708)
- [Stack Overflow - android-studio](https://stackoverflow.com/questions/tagged/android-studio)
- [Reddit r/androiddev](https://reddit.com/r/androiddev)

---

[Retour au Module 2](./README.md)