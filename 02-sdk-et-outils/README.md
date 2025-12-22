# Module 2 : SDK et Outils de Développement

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Installer et configurer Android Studio correctement
- Comprendre le rôle du SDK Android et ses composants
- Créer et gérer des émulateurs (AVD)
- Utiliser les outils essentiels (SDK Manager, AVD Manager, ADB)
- Résoudre les problèmes courants d'installation

## 📚 Contenu du module

### 1. Android Studio : IDE Officiel

**Android Studio** est l'environnement de développement intégré (IDE) officiel pour Android, basé sur IntelliJ IDEA.

#### Pourquoi Android Studio ?

- **Officiel** : Maintenu par Google
- **Tout-en-un** : IDE + SDK + Émulateur + Outils
- **Puissant** : Autocomplétion intelligente, refactoring, débogage
- **Gratuit** : 100% gratuit et open source
- **Moderne** : Support Kotlin, Compose, IA intégrée (2025)

#### Versions

| Version | Nom de code | Date | Nouveautés |
|---------|-------------|------|------------|
| 2024.1 | Koala | Mai 2024 | Compose Preview amélioré |
| 2024.2 | Ladybug | Oct 2024 | AI code completion |
| 2025.1 | Meerkat | Prévu 2025 | Gradle 9.0, Performance |

**Recommandation 2025** : Installer la dernière version stable (2024.2+)

### 2. Le SDK Android

#### Qu'est-ce que le SDK ?

Le **Software Development Kit (SDK)** Android est une collection de :

- **Bibliothèques** : APIs Android pour accéder aux fonctionnalités système
- **Outils** : Compilateurs, débogueurs, analyseurs
- **Émulateurs** : Environnements virtuels de test
- **Documentation** : Références et guides

#### Composants du SDK

```
📦 Android SDK
├── 📁 platforms/          # Versions Android (API 24, 34, 35...)
├── 📁 build-tools/        # Outils de compilation (aapt, dx, zipalign)
├── 📁 platform-tools/     # ADB, fastboot
├── 📁 emulator/           # Émulateur Android
├── 📁 system-images/      # Images système pour AVD
├── 📁 sources/            # Code source Android
└── 📁 extras/             # Google Play Services, Support libs
```

#### SDK Manager

Le **SDK Manager** permet de :
- Télécharger des versions Android (APIs)
- Mettre à jour les outils
- Installer des images système pour émulateurs
- Gérer l'espace disque

### 3. Configuration Système Requise

#### Configuration Minimale

| Composant | Minimum | Recommandé |
|-----------|---------|------------|
| **OS** | Windows 10, macOS 10.14, Linux | Windows 11, macOS 12+ |
| **RAM** | 8 GB | 16 GB |
| **Disque** | 10 GB libre | 20 GB+ SSD |
| **CPU** | x64 moderne | Multi-core récent |
| **Résolution** | 1280x800 | 1920x1080+ |
| **JDK** | JDK 17+ | Inclus avec Android Studio |

#### Accélération Matérielle

Pour des émulateurs performants, installer :

**Windows** :
- Intel : HAXM (Hardware Accelerated Execution Manager)
- AMD : Windows Hypervisor Platform (WHPX)

**macOS** :
- Accélération native (Hypervisor Framework)

**Linux** :
- KVM (Kernel-based Virtual Machine)

### 4. Installation Pas à Pas

#### Étape 1 : Téléchargement

1. Aller sur [developer.android.com/studio](https://developer.android.com/studio)
2. Cliquer sur **Download Android Studio**
3. Accepter les conditions
4. Choisir la version pour votre OS
5. Télécharger (~1 GB)

#### Étape 2 : Installation

**Windows** :
```powershell
# Exécuter l'installeur
android-studio-2024.2.1-windows.exe

# Suivre l'assistant
- Composants : Cocher tout
- Dossier : C:\Program Files\Android\Android Studio
- Menu démarrer : Oui
```

**macOS** :
```bash
# Ouvrir le DMG
open android-studio-2024.2.1-mac.dmg

# Glisser Android Studio vers Applications
drag Android Studio.app to /Applications

# Lancer
open /Applications/Android\ Studio.app
```

**Linux (Ubuntu/Debian)** :
```bash
# Extraire l'archive
sudo tar -xvzf android-studio-2024.2.1-linux.tar.gz -C /opt/

# Lancer
cd /opt/android-studio/bin
./studio.sh

# Créer un lanceur (optionnel)
sudo nano /usr/share/applications/android-studio.desktop
```

#### Étape 3 : Premier Lancement

1. **Écran d'accueil** : Choisir "Do not import settings"
2. **Setup Wizard** : Cliquer "Next"
3. **Install Type** :
   - ✅ **Standard** (recommandé) : Tout par défaut
   - Custom : Contrôle avancé
4. **UI Theme** : Choisir Darcula (sombre) ou Light
5. **Verify Settings** : Vérifier l'espace disque (~8 GB)
6. **Download Components** : Patience (~10-15 min)

#### Étape 4 : Configuration SDK Manager

**Ouvrir SDK Manager** :
- Menu : `Tools > SDK Manager`
- Ou icône 🔧 dans la toolbar

**SDK Platforms (onglet)** :
Cocher :
- ✅ Android 15.0 (API 35) - Dernière version
- ✅ Android 14.0 (API 34)
- ✅ Android 7.0 (API 24) - MinSDK recommandé
- ✅ Show Package Details : Cocher "Android SDK Platform" + "Sources for Android"

**SDK Tools (onglet)** :
Vérifier que ces outils sont installés :
- ✅ Android SDK Build-Tools (dernière version)
- ✅ Android Emulator
- ✅ Android SDK Platform-Tools
- ✅ Google Play services
- ✅ Intel x86 Emulator Accelerator (HAXM) - Windows/Mac Intel

**Appliquer** : Cliquer "Apply" puis "OK"

### 5. Création d'un Émulateur (AVD)

#### Qu'est-ce qu'un AVD ?

Un **Android Virtual Device (AVD)** est un émulateur qui simule un appareil Android réel.

#### Créer un AVD

1. **Ouvrir AVD Manager** :
   - Menu : `Tools > Device Manager`
   - Ou icône 📱 dans la toolbar

2. **Create Virtual Device** :
   - Cliquer sur "Create Device"

3. **Choisir un appareil** :
   - Category : Phone
   - Appareil : **Pixel 8** (recommandé)
   - Cliquer "Next"

4. **Choisir une image système** :
   - Release : **VanillaIceCream (API 35)**
   - ABI : **x86_64** (plus rapide avec accélération)
   - Télécharger si nécessaire
   - Cliquer "Next"

5. **Configuration AVD** :
   - AVD Name : `Pixel_8_API_35`
   - Startup orientation : Portrait
   - ✅ Enable Device Frame
   - Graphics : **Hardware - GLES 2.0** (recommandé)
   - RAM : 2048 MB (minimum)
   - VM Heap : 512 MB
   - Internal Storage : 2048 MB
   - SD Card : 512 MB (optionnel)
   - Cliquer "Finish"

6. **Lancer l'émulateur** :
   - Cliquer sur ▶️ (Play) dans la liste
   - Patienter 30-60 secondes (premier lancement)

#### Conseils AVD

💡 **Astuce Snapshot** :
- Activer "Quick Boot" pour démarrage rapide (~5 secondes)
- Settings AVD > Advanced > Boot option : **Quick boot**

💡 **Multi-AVD** :
Créer plusieurs AVD pour tester :
- Pixel 8 (API 35) - Dernière version
- Pixel 6 (API 31) - Version populaire
- Tablet (API 34) - Grands écrans
- Pixel 4 (API 24) - MinSDK

### 6. Android Debug Bridge (ADB)

**ADB** est un outil en ligne de commande pour communiquer avec les appareils Android.

#### Commandes ADB Essentielles

```bash
# Vérifier ADB installé
adb version

# Lister les appareils connectés
adb devices

# Installer une app
adb install chemin/vers/app.apk

# Désinstaller une app
adb uninstall com.example.monapp

# Voir les logs
adb logcat

# Copier fichier vers appareil
adb push fichier.txt /sdcard/

# Copier fichier depuis appareil
adb pull /sdcard/fichier.txt .

# Shell Android
adb shell

# Redémarrer appareil
adb reboot

# Tuer le serveur ADB
adb kill-server
adb start-server
```

#### Utilisation Pratique

**Déboguer sur appareil réel** :
1. Activer "Options développeur" :
   - Paramètres > À propos du téléphone
   - Taper 7 fois sur "Numéro de build"
2. Activer "Débogage USB" :
   - Paramètres > Options développeur
   - Activer "Débogage USB"
3. Connecter USB
4. Autoriser sur le téléphone
5. Vérifier : `adb devices`

### 7. Structure de l'Interface Android Studio

```
┌─────────────────────────────────────────────────┐
│ Android Studio                         [_][□][X]│
├─────────────────────────────────────────────────┤
│ File Edit View Navigate Code...         🔍 ▶️  │ <- Menu & Toolbar
├──────────┬──────────────────────────────────────┤
│ Project  │  MainActivity.java                   │
│ ├─ app   │  ┌──────────────────────────────┐   │
│ │ ├─ java│  │ public class MainActivity    │   │
│ │ ├─ res │  │ extends AppCompatActivity {  │   │ <- Éditeur
│ │ │ ├─ layout  │                          │   │
│ │ │ └─ values  │                          │   │
│ │ └─ manifests │                          │   │
│ └─ Gradle│  └──────────────────────────────┘   │
├──────────┴──────────────────────────────────────┤
│ 🔨 Build   ⚠️ Problems   📋 Logcat   💻 Terminal│ <- Panneaux bas
└─────────────────────────────────────────────────┘
```

#### Panneaux Importants

- **Project** (gauche) : Arborescence des fichiers
- **Editor** (centre) : Code Java/Kotlin/XML
- **Build** (bas) : Résultats de compilation
- **Logcat** (bas) : Logs en temps réel
- **Terminal** (bas) : Ligne de commande

## 🛠️ Outils Complémentaires

### Gradle

**Gradle** est le système de build d'Android.

- Compile le code Java/Kotlin
- Gère les dépendances
- Génère l'APK/AAB
- Configure les variantes (debug/release)

**Fichiers Gradle** :
- `settings.gradle.kts` : Configuration projet
- `build.gradle.kts` (Project) : Dépôts, plugins
- `build.gradle.kts` (Module: app) : Configuration app

### Jetpack

**Android Jetpack** : Collection de bibliothèques modernes.

- **Architecture** : ViewModel, LiveData, Room
- **UI** : Compose, Navigation, Paging
- **Foundation** : AppCompat, KTX
- **Behavior** : WorkManager, Notifications

## 📊 Checklist Installation

- [ ] Android Studio installé et lancé
- [ ] SDK Manager configuré (API 24, 34, 35)
- [ ] Émulateur créé (Pixel 8 API 35)
- [ ] Émulateur lancé avec succès
- [ ] ADB fonctionnel (`adb version`)
- [ ] Interface Android Studio familière
- [ ] Projet test créé (Module 3)

## ⚠️ Problèmes Courants

### Émulateur ne démarre pas

**Cause** : Accélération matérielle non activée

**Solution Windows** :
```powershell
# Vérifier virtualisation BIOS
systeminfo | findstr "Hyper-V"

# Installer HAXM
# SDK Manager > SDK Tools > Intel x86 Emulator Accelerator
```

**Solution Linux** :
```bash
# Installer KVM
sudo apt install qemu-kvm libvirt-daemon-system
sudo usermod -aG kvm $USER
```

### Gradle Sync Failed

**Cause** : Connexion internet, proxy, versions incompatibles

**Solutions** :
1. File > Invalidate Caches > Invalidate and Restart
2. Vérifier `gradle.properties` (proxy)
3. Mettre à jour Gradle : `./gradlew wrapper --gradle-version 8.5`

### SDK Not Found

**Cause** : Chemin SDK incorrect

**Solution** :
1. File > Project Structure
2. SDK Location > Android SDK location
3. Définir : `/Users/nom/Library/Android/sdk` (macOS)
4. Ou : `C:\Users\nom\AppData\Local\Android\Sdk` (Windows)

## 📝 Exercices Pratiques

[Accéder aux exercices](./exercices.md)

## ➡️ Module suivant

[Module 3 : Création Premier Projet](../03-creation-premier-projet/README.md)

---

👨‍🏫 **Enseignant** : A. GUEDDES | ISITCom 2025-2026