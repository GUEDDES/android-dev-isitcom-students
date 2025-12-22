# 🔧 Guide d'Installation Détaillé Android Studio

## Table des Matières

1. [Préparation du système](#préparation)
2. [Installation Windows](#windows)
3. [Installation macOS](#macos)
4. [Installation Linux](#linux)
5. [Configuration initiale](#configuration)
6. [Résolution de problèmes](#problemes)

---

## 📋 Préparation du Système {#préparation}

### Vérifier Java (JDK)

Android Studio inclut OpenJDK, mais vérifiez d'abord :

```bash
# Vérifier version Java
java -version

# Si absent ou < 17, télécharger :
# https://adoptium.net/
```

### Libérer de l'espace disque

```
Espace nécessaire :
- Android Studio : 1.1 GB
- SDK Android (base) : 3-5 GB
- Émulateurs (2-3 AVD) : 5-8 GB
- Cache Gradle : 2-3 GB
━━━━━━━━━━━━━━━━━━━━━━━━━
Total recommandé : 15-20 GB
```

---

## 🪟 Installation Windows {#windows}

### Étape 1 : Téléchargement

1. [developer.android.com/studio](https://developer.android.com/studio)
2. Fichier : `android-studio-2024.2.1.11-windows.exe` (~1.1 GB)

### Étape 2 : Installation

```powershell
# Exécuter le .exe (admin recommandé)
# Click droit → "Exécuter en tant qu'administrateur"
```

**Assistant d'installation** :

```
[1/6] Welcome
→ Next

[2/6] Choose Components
✅ Android Studio
✅ Android SDK
✅ Android Virtual Device
✅ Performance (Intel HAXM)
→ Next

[3/6] Configuration Settings
Installation Location:
C:\Program Files\Android\Android Studio
→ Next (ou Browse pour changer)

[4/6] Choose Start Menu Folder
→ Install

[5/6] Installation Progress
⏳ Attendre (~5 minutes)

[6/6] Completing Setup
✅ Start Android Studio
→ Finish
```

### Étape 3 : Intel HAXM (Accélération)

**Si pas installé automatiquement** :

```powershell
# Aller dans le dossier SDK
cd C:\Users\<username>\AppData\Local\Android\Sdk\extras\intel\Hardware_Accelerated_Execution_Manager

# Exécuter
intelhaxm-android.exe
```

**Alternative Windows 11 : WHPX**

```powershell
# Activer Hyper-V (Windows Pro uniquement)
Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All

# Redémarrer
```

---

## 🍎 Installation macOS {#macos}

### Étape 1 : Téléchargement

1. [developer.android.com/studio](https://developer.android.com/studio)
2. Fichier : `android-studio-2024.2.1.11-mac.dmg` (~1.1 GB)
3. Choisir :
   - **Mac with Apple chip** (M1/M2/M3)
   - **Mac with Intel chip**

### Étape 2 : Installation

```bash
# Ouvrir le .dmg
open android-studio-*.dmg

# Glisser Android Studio → Applications
```

### Étape 3 : Autorisation Sécurité

```
Premier lancement → "Android Studio ne peut pas être ouvert"

1. Préférences Système → Sécurité et confidentialité
2. Onglet "Général"
3. "Ouvrir quand même" (en bas)
4. Confirmer
```

### Étape 4 : Hypervisor (déjà intégré macOS)

```bash
# Vérifier que la virtualisation est disponible
sysctl kern.hv_support
# Doit retourner : kern.hv_support: 1
```

---

## 🐧 Installation Linux (Ubuntu/Debian) {#linux}

### Méthode 1 : Archive officielle

```bash
# 1. Télécharger
wget https://redirector.gvt1.com/edgedl/android/studio/ide-zips/2024.2.1.11/android-studio-2024.2.1.11-linux.tar.gz

# 2. Extraire dans /opt
sudo tar -xvzf android-studio-*.tar.gz -C /opt/

# 3. Installer dépendances
sudo apt update
sudo apt install -y libc6:i386 libncurses5:i386 libstdc++6:i386 lib32z1 libbz2-1.0:i386

# 4. Lancer
cd /opt/android-studio/bin
./studio.sh
```

### Méthode 2 : Snap (plus simple)

```bash
# Installation via Snap
sudo snap install android-studio --classic

# Lancer
android-studio
```

### Créer un raccourci Desktop

```bash
# Créer le fichier .desktop
sudo nano /usr/share/applications/android-studio.desktop
```

Contenu :

```ini
[Desktop Entry]
Version=1.0
Type=Application
Name=Android Studio
Icon=/opt/android-studio/bin/studio.png
Exec="/opt/android-studio/bin/studio.sh" %f
Comment=Android Development IDE
Categories=Development;IDE;
Terminal=false
StartupWMClass=jetbrains-studio
```

### Activer KVM (Accélération)

```bash
# Vérifier support virtualisation
egrep -c '(vmx|svm)' /proc/cpuinfo
# Doit retourner > 0

# Installer KVM
sudo apt install -y qemu-kvm libvirt-daemon-system libvirt-clients bridge-utils

# Ajouter utilisateur au groupe kvm
sudo adduser $USER kvm
sudo adduser $USER libvirt

# Redémarrer session
```

---

## ⚙️ Configuration Initiale {#configuration}

### Assistant Setup

Au premier lancement :

#### 1. Import Settings

```
○ Do not import settings
● Import settings from: <chemin>

→ OK
```

#### 2. Data Sharing

```
Send usage statistics to Google ?
→ Don't send (recommandé)
```

#### 3. Install Type

```
● Standard (recommandé)
  - Android SDK
  - AVD
  - Dernières versions API
  - Performance tools

○ Custom
  - Choix manuel de chaque composant

→ Next
```

#### 4. Select UI Theme

```
○ Light (clair)
● Darcula (sombre) ← Préféré par 80% devs

→ Next
```

#### 5. Verify Settings

```
Setup Type: Standard
SDK Folder: 
  Windows: C:\Users\<user>\AppData\Local\Android\Sdk
  macOS: /Users/<user>/Library/Android/sdk
  Linux: /home/<user>/Android/Sdk

Total Download Size: ~2.5 GB
SDK Components:
  ✅ Android SDK Platform 35
  ✅ Android SDK Build-Tools
  ✅ Android Emulator
  ✅ Intel/AMD Accelerator

→ Finish (lance téléchargement)
```

#### 6. Downloading Components

```
📥 Téléchargement en cours...

Android SDK Platform 35        [████████░░] 80%
Build Tools 35.0.0             [██████████] 100%
Emulator                       [████░░░░░░] 45%

Temps estimé : 10-30 minutes (selon connexion)
```

#### 7. Finish

```
✅ Installation terminée !

→ Finish

🎉 Android Studio est prêt !
```

### Première ouverture

```
╔══════════════════════════════════════╗
║   Welcome to Android Studio          ║
╠══════════════════════════════════════╣
║  📁 New Project                      ║
║  📂 Open                             ║
║  📚 Get from VCS                     ║
║  ⚙️  More Actions ▼                  ║
║     - SDK Manager                    ║
║     - AVD Manager                    ║
║     - Settings                       ║
║  📖 Learn                            ║
╚══════════════════════════════════════╝
```

---

## 🚨 Résolution de Problèmes {#problemes}

### Problème 1 : HAXM ne s'installe pas (Windows)

**Erreur** : "This computer does not support Intel Virtualization Technology (VT-x)"

**Solution** :
```
1. Redémarrer → Entrer dans BIOS (F2 / DEL / F10)
2. Chercher "Virtualization" ou "VT-x" ou "Intel VT"
3. Activer (Enabled)
4. Sauvegarder et redémarrer
5. Réinstaller HAXM
```

### Problème 2 : Gradle sync failed

**Erreur** : "Could not download..."

**Solutions** :

```bash
# 1. Vérifier connexion internet
ping google.com

# 2. Configurer proxy (si nécessaire)
File → Settings → HTTP Proxy
→ Manual proxy configuration
→ Entrer host:port

# 3. Forcer re-téléchargement
File → Invalidate Caches → Invalidate and Restart
```

### Problème 3 : AVD ne démarre pas

**Erreur** : "Emulator: ERROR: x86 emulation currently requires hardware acceleration!"

**Solutions** :

```bash
# Windows
1. Vérifier HAXM installé
2. Activer VT-x dans BIOS
3. Désactiver Hyper-V si conflit:
   bcdedit /set hypervisorlaunchtype off
   # Redémarrer

# macOS
# Normalement pas de problème (intégré)

# Linux
sudo apt install qemu-kvm
sudo usermod -aG kvm $USER
# Redémarrer session
```

### Problème 4 : Android Studio lent

**Solutions** :

```bash
# 1. Augmenter heap memory
Help → Edit Custom VM Options

# Ajouter/modifier :
-Xms2048m
-Xmx8192m

# 2. Activer Gradle Daemon
File → Settings → Build → Gradle
✅ Offline work (si pas de nouvelles dépendances)

# 3. Désactiver plugins inutiles
File → Settings → Plugins
→ Désactiver ceux non utilisés
```

### Problème 5 : "SDK location not found"

**Solution** :

```
File → Project Structure → SDK Location
→ Définir chemin SDK manuellement

Ou créer/éditer local.properties :
sdk.dir=C:\Users\<user>\AppData\Local\Android\Sdk
```

---

## ✅ Vérification Installation

### Checklist finale

```bash
# 1. Android Studio démarre ?
✅ Oui → Continuer
❌ Non → Voir problème 2

# 2. SDK Manager accessible ?
Tools → SDK Manager
✅ Oui → Continuer

# 3. Au moins une API installée ?
SDK Platforms → Android 15.0 (API 35)
✅ Installée

# 4. Émulateur fonctionne ?
Tools → Device Manager → Create Device → Launch
✅ Démarre en < 60s

# 5. ADB fonctionne ?
adb version
✅ Affiche version (ex: 35.0.0)
```

### Tester avec Hello World

```
1. New Project → Empty Views Activity
2. Name: TestInstall
3. Language: Java
4. Minimum SDK: API 24
5. Finish
6. Attendre Gradle sync
7. Run ▶️
8. Sélectionner AVD
9. App s'affiche → ✅ Installation OK !
```

---

[Retour au Module 2](./README.md)