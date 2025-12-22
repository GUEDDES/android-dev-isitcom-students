# 📝 Fiche de Synthèse : SDK et Outils

## 🔑 Points Clés

### Android Studio

**Définition** : IDE officiel Google pour Android  
**Version 2025** : 2024.2+ (Ladybug)  
**Contient** : Éditeur + SDK + Émulateur + Gradle

### SDK Android

**Composants** :
- `platforms/` → Versions Android (API 24, 34, 35...)
- `build-tools/` → Compilateurs (aapt, dx, d8)
- `platform-tools/` → ADB, fastboot
- `emulator/` → Émulateur AVD

### Configuration Minimale

| Élément | Minimum |
|---------|----------|
| RAM | 8 GB |
| Disque | 10 GB SSD |
| CPU | x64 |
| OS | Win 10 / macOS 10.14 / Linux |

### APIs Recommandées 2025

- **minSdk** : API 24 (Android 7.0) - 95% couverture
- **targetSdk** : API 35 (Android 15)
- **compileSdk** : API 35

## 🛠️ Outils Essentiels

### SDK Manager

**Accès** : `Tools > SDK Manager`

**Fonctions** :
- Télécharger APIs
- Installer outils
- Gérer images système
- Mettre à jour composants

### AVD Manager

**Accès** : `Tools > Device Manager`

**Fonctions** :
- Créer émulateurs
- Configurer specs (RAM, résolution)
- Gérer snapshots
- Lancer/arrêter AVD

### ADB (Android Debug Bridge)

**Commandes vitales** :
```bash
adb devices          # Lister appareils
adb install app.apk  # Installer app
adb logcat           # Voir logs
adb shell            # Shell Android
adb reboot           # Redémarrer
```

### Gradle

**Rôle** : Système de build

**Fichiers** :
- `settings.gradle.kts` → Config projet
- `build.gradle.kts` (app) → Config module
- `gradle.properties` → Propriétés

**Commandes** :
```bash
./gradlew clean       # Nettoyer
./gradlew build       # Compiler
./gradlew assemble    # Générer APK
```

## ⚡ Accélération Matérielle

### Windows

**Intel** : HAXM  
**AMD** : WHPX (Windows Hypervisor Platform)

### Linux

**KVM** (Kernel-based Virtual Machine)
```bash
sudo apt install qemu-kvm
sudo usermod -aG kvm $USER
```

### macOS

**Hypervisor Framework** (natif)

## 🎯 Checklist Installation

- [ ] Android Studio installé
- [ ] SDK Manager : API 24, 34, 35
- [ ] AVD créé (Pixel 8 API 35)
- [ ] AVD lancé avec succès
- [ ] ADB fonctionnel
- [ ] Accélération matérielle activée
- [ ] Quick Boot configuré

## 🚨 Problèmes Fréquents

### Émulateur lent

**Solutions** :
- Activer Quick Boot
- Graphics: Hardware
- Accélération matérielle (HAXM/KVM)
- Réduire résolution

### Gradle Sync Failed

**Solutions** :
- `File > Invalidate Caches`
- Vérifier connexion internet
- `./gradlew clean build`

### ADB offline

**Solutions** :
```bash
adb kill-server
adb start-server
```

## 📊 Comparaison Émulateur vs Réel

| Critère | Émulateur | Appareil Réel |
|---------|-----------|---------------|
| **Performances** | Moyen | Excellent |
| **Multi-version** | ✅ Facile | ❌ Limité |
| **Coût** | Gratuit | Acheter appareil |
| **Capteurs** | Simulés | Réels |
| **Tests** | Rapide | Précis |

**Recommandation** : Développer sur émulateur, valider sur réel

## 💡 Astuces Productivité

### Raccourcis Clavier

| Action | Raccourci |
|--------|----------|
| Recherche | Double Shift |
| Exécuter | Shift+F10 |
| Déboguer | Shift+F9 |
| Format code | Ctrl+Alt+L |
| Renommer | Shift+F6 |

### Optimisation Gradle

```properties
# gradle.properties
org.gradle.jvmargs=-Xmx4096m
org.gradle.parallel=true
org.gradle.caching=true
```

### Plugins Utiles

- **Rainbow Brackets** : Colorer parenthèses
- **Key Promoter X** : Apprendre raccourcis
- **GitToolBox** : Infos Git

## 📦 Structure SDK

```
~/Library/Android/sdk/ (macOS)
C:\Users\nom\AppData\Local\Android\Sdk (Windows)

├── platforms/
│   ├── android-24/
│   ├── android-34/
│   └── android-35/
├── build-tools/
│   └── 34.0.0/
├── platform-tools/
│   ├── adb
│   └── fastboot
├── emulator/
└── system-images/
    └── android-35/
        └── google_apis/
            └── x86_64/
```

## 🔢 Versions API Importantes

| API | Android | Nom | Couverture 2025 |
|-----|---------|-----|------------------|
| 24 | 7.0 | Nougat | 95.1% |
| 28 | 9.0 | Pie | 85.3% |
| 31 | 12.0 | S | 72.5% |
| 34 | 14.0 | U | 35.2% |
| 35 | 15.0 | V | 8.1% |

## ⏱️ Temps Typiques

- **Installation Android Studio** : 15-20 min
- **Téléchargement SDK** : 10-15 min
- **Création AVD** : 2-5 min
- **Premier démarrage AVD** : 1-2 min
- **Démarrage Quick Boot** : 5-10 sec

## 📱 Configuration AVD Optimale

**Smartphone Standard** :
- Device : Pixel 8
- System : API 35 x86_64
- RAM : 2048 MB
- Storage : 2048 MB
- Graphics : Hardware - GLES 2.0
- Boot : Quick boot

## 🎓 Compétences Acquises

À la fin du Module 2, vous maîtrisez :

✅ Installation Android Studio  
✅ Configuration SDK Manager  
✅ Création et gestion AVD  
✅ Utilisation ADB  
✅ Résolution problèmes courants  
✅ Optimisation performances  

---

[Retour au Module 2](./README.md)