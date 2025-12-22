# 🔧 Dépannage Android Studio

## Problèmes Fréquents et Solutions

### 1. Erreurs d'Installation

#### ❌ "Installation failed: Out of disk space"

**Cause** : Espace disque insuffisant

**Solution** :
```bash
# Vérifier espace disponible
# Windows
wmic logicaldisk get name,freespace

# macOS/Linux
df -h

# Libérer au moins 15 GB avant d'installer
```

---

#### ❌ "Java version not found"

**Cause** : JDK non installé ou version < 17

**Solution** :
```bash
# Télécharger JDK 21 (recommandé)
https://adoptium.net/

# Définir JAVA_HOME
# Windows (PowerShell Admin)
[System.Environment]::SetEnvironmentVariable('JAVA_HOME', 'C:\Program Files\Java\jdk-21', 'Machine')

# macOS/Linux (ajouter dans ~/.bashrc ou ~/.zshrc)
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home
```

---

### 2. Problèmes SDK Manager

#### ❌ "Failed to download SDK component"

**Causes possibles** :
- Connexion internet instable
- Proxy/Firewall
- Serveur Google temporairement indisponible

**Solutions** :

```bash
# 1. Tester connexion
ping dl.google.com

# 2. Configurer proxy
File → Settings → Appearance & Behavior → System Settings → HTTP Proxy
→ Manual proxy configuration
→ Entrer host et port

# 3. Retry avec miroir Google
Gradle.properties (ajouter) :
systemProp.https.proxyHost=<proxy>
systemProp.https.proxyPort=<port>

# 4. Forcer re-téléchargement
Supprimer cache SDK Manager :
Windows: %USERPROFILE%\.android\cache
macOS/Linux: ~/.android/cache
```

---

#### ❌ "SDK location not found"

**Solution** :

```bash
# Créer/éditer local.properties à la racine projet

# Windows
sdk.dir=C:\\Users\\<username>\\AppData\\Local\\Android\\Sdk

# macOS
sdk.dir=/Users/<username>/Library/Android/sdk

# Linux
sdk.dir=/home/<username>/Android/Sdk

# Ou via interface
File → Project Structure → SDK Location → Android SDK location
```

---

### 3. Problèmes AVD/Émulateur

#### ❌ "Emulator: ERROR: x86 emulation requires hardware acceleration!"

**Windows** :

```powershell
# 1. Vérifier VT-x activé dans BIOS
# Redémarrer → F2/DEL → Advanced → CPU Configuration
# → Intel Virtualization Technology: Enabled

# 2. Installer HAXM
# Aller dans SDK Manager → SDK Tools → Intel HAXM
# Ou manuellement :
cd %LOCALAPPDATA%\Android\Sdk\extras\intel\Hardware_Accelerated_Execution_Manager
intelhaxm-android.exe

# 3. Si Hyper-V installé (Windows Pro)
# Désactiver temporairement
bcdedit /set hypervisorlaunchtype off
# Redémarrer

# Pour réactiver plus tard
bcdedit /set hypervisorlaunchtype auto
```

**macOS** :

```bash
# Vérifier Hypervisor disponible
sysctl kern.hv_support
# Doit retourner: kern.hv_support: 1

# Si problème persiste
# Réinitialiser émulateur
rm -rf ~/.android/avd/<nom_avd>.avd
# Recréer AVD
```

**Linux** :

```bash
# Installer KVM
sudo apt install qemu-kvm libvirt-daemon-system

# Ajouter user au groupe kvm
sudo usermod -aG kvm $USER
sudo usermod -aG libvirt $USER

# Vérifier KVM fonctionne
kvm-ok
# Doit afficher: "KVM acceleration can be used"

# Redémarrer session
```

---

#### ❌ "Emulator: PANIC: Cannot find AVD system path"

**Solution** :

```bash
# Définir variable environnement ANDROID_AVD_HOME

# Windows
setx ANDROID_AVD_HOME "%USERPROFILE%\.android\avd"

# macOS/Linux (~/.bashrc ou ~/.zshrc)
export ANDROID_AVD_HOME="$HOME/.android/avd"

# Redémarrer Android Studio
```

---

#### ❌ AVD très lent

**Solutions** :

```bash
# 1. Vérifier accélération hardware activée
AVD Manager → Edit AVD → Show Advanced Settings
Emulated Performance:
  Graphics: Hardware - GLES 2.0 (ou Automatic)

# 2. Augmenter RAM allouée
RAM: 4096 MB (au lieu de 2048)
VM Heap: 512 MB

# 3. Utiliser architecture x86_64
Système: x86_64 avec Google APIs

# 4. Activer Quick Boot
Boot option: Quick boot

# 5. Si toujours lent → utiliser appareil réel via USB
```

---

### 4. Problèmes Gradle

#### ❌ "Gradle sync failed"

**Solutions multiples** :

```bash
# 1. Nettoyer cache Gradle
File → Invalidate Caches... → Invalidate and Restart

# 2. Supprimer cache manuellement
# Windows
rmdir /s /q %USERPROFILE%\.gradle\caches

# macOS/Linux
rm -rf ~/.gradle/caches

# 3. Forcer re-téléchargement dépendances
./gradlew clean build --refresh-dependencies

# 4. Vérifier version Gradle compatible
# gradle/wrapper/gradle-wrapper.properties
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
```

---

#### ❌ "Could not resolve all dependencies"

**Solution** :

```gradle
// build.gradle (Project)
allprojects {
    repositories {
        google()        // Obligatoire en premier
        mavenCentral()
        // Si utilisation bibliothèque externe
        maven { url 'https://jitpack.io' }
    }
}

// Vérifier connexion internet
// Vérifier firewall n'empêche pas téléchargement
```

---

#### ❌ "Gradle build daemon disappeared unexpectedly"

**Cause** : Mémoire insuffisante

**Solution** :

```bash
# Augmenter heap Gradle
# Créer/éditer gradle.properties

org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
org.gradle.parallel=true
org.gradle.caching=true
```

---

### 5. Problèmes ADB

#### ❌ "adb: command not found"

**Solution** :

```bash
# Ajouter platform-tools au PATH

# Windows (PowerShell Admin)
$env:Path += ";C:\Users\<username>\AppData\Local\Android\Sdk\platform-tools"

# Permanent
[System.Environment]::SetEnvironmentVariable(
    "Path",
    [System.Environment]::GetEnvironmentVariable("Path", "Machine") + ";C:\Users\<username>\AppData\Local\Android\Sdk\platform-tools",
    "Machine"
)

# macOS/Linux (~/.bashrc ou ~/.zshrc)
export PATH="$PATH:$HOME/Library/Android/sdk/platform-tools"

# Recharger
source ~/.bashrc  # ou ~/.zshrc
```

---

#### ❌ "adb devices" ne montre rien (appareil réel)

**Solutions** :

```bash
# 1. Vérifier débogage USB activé sur téléphone
Paramètres → À propos → Taper 7x "Numéro de build"
Paramètres → Options développeur → Débogage USB: ON

# 2. Autoriser ordinateur sur téléphone
# Popup "Autoriser débogage USB" → Toujours autoriser

# 3. Tester avec autre câble USB (certains = charge uniquement)

# 4. Redémarrer serveur ADB
adb kill-server
adb start-server
adb devices

# 5. Windows : Installer driver USB
OEM USB Drivers: https://developer.android.com/studio/run/oem-usb

# 6. Vérifier mode connexion
Mode fichiers / MTP (pas charge uniquement)
```

---

### 6. Problèmes Performance

#### 🐌 Android Studio très lent

**Solutions** :

```bash
# 1. Augmenter mémoire IDE
Help → Edit Custom VM Options

# Modifier/Ajouter
-Xms2048m        # Heap initial
-Xmx8192m        # Heap max (ajuster selon RAM)
-XX:ReservedCodeCacheSize=512m
-XX:+UseG1GC

# 2. Désactiver plugins inutiles
File → Settings → Plugins
→ Désactiver Markdown, CVS, etc.

# 3. Exclure dossiers du scan
File → Settings → Editor → File Types
→ Ignore files and folders
Ajouter: *.iml;.idea;.gradle;build;

# 4. Augmenter Gradle heap
gradle.properties:
org.gradle.jvmargs=-Xmx4096m
org.gradle.daemon=true
org.gradle.parallel=true

# 5. SSD fortement recommandé (vs HDD)
```

---

### 7. Problèmes Spécifiques OS

#### 🪟 Windows : Chemin trop long

**Erreur** : "The filename or extension is too long"

**Solution** :

```powershell
# Activer chemins longs (PowerShell Admin)
New-ItemProperty -Path "HKLM:\SYSTEM\CurrentControlSet\Control\FileSystem" `
-Name "LongPathsEnabled" -Value 1 -PropertyType DWORD -Force

# Redémarrer

# Ou déplacer projet plus près racine
C:\AndroidProjects\MonApp
# Au lieu de
C:\Users\NomTresLong\Documents\Projets\Android\MonAppli
```

---

#### 🍎 macOS : "Android Studio is damaged"

**Solution** :

```bash
# Autoriser app non signée
sudo xattr -cr /Applications/Android\ Studio.app

# Relancer
```

---

#### 🐧 Linux : Problèmes graphiques

**Solution** :

```bash
# Si interface mal affichée
# Éditer studio.sh, ajouter avant dernière ligne:
export _JAVA_OPTIONS='-Dawt.useSystemAAFontSettings=lcd'

# Lancer
./studio.sh
```

---

## 🆘 Dernier Recours

### Réinstallation complète

```bash
# 1. Désinstaller Android Studio
# Windows: Panneau de configuration → Désinstaller
# macOS: Glisser Android Studio vers Corbeille
# Linux: sudo apt remove android-studio

# 2. Supprimer dossiers configuration

# Windows
rmdir /s /q %USERPROFILE%\.android
rmdir /s /q %USERPROFILE%\.AndroidStudio*
rmdir /s /q %LOCALAPPDATA%\Android
rmdir /s /q %USERPROFILE%\.gradle

# macOS
rm -rf ~/.android
rm -rf ~/Library/Android
rm -rf ~/Library/Application\ Support/Google/AndroidStudio*
rm -rf ~/.gradle

# Linux
rm -rf ~/.android
rm -rf ~/.AndroidStudio*
rm -rf ~/Android
rm -rf ~/.gradle

# 3. Réinstaller Android Studio (guide ci-dessus)
```

---

## 📞 Obtenir de l'Aide

### Forums et Communautés

- [Stack Overflow - android-studio](https://stackoverflow.com/questions/tagged/android-studio)
- [Reddit r/androiddev](https://reddit.com/r/androiddev)
- [Android Developers Discord](https://discord.gg/androiddev)

### Logs Utiles

```bash
# Logs Android Studio
Help → Show Log in Explorer/Finder/Files

# Logs Gradle
./gradlew build --stacktrace --debug > build.log

# Logs émulateur
adb logcat > emulator.log
```

---

[Retour au Module 2](./README.md)