# 🏋️ Exercices Pratiques : SDK et Outils

## Exercice 1 : Installation et Vérification (⭐)

### Objectif
Vérifier que votre environnement est correctement installé.

### Instructions

1. **Ouvrir un terminal/invite de commandes**

2. **Exécuter les commandes suivantes** et noter les résultats :

```bash
# Vérifier ADB
adb version

# Lister les SDK installés
sdkmanager --list | grep "system-images"

# Vérifier Java
java -version
```

### Résultat attendu

```
Android Debug Bridge version 1.0.41
Version 35.0.X

openjdk version "17.0.X"
```

### Livrables

Capture d'écran montrant les 3 commandes exécutées avec succès.

---

## Exercice 2 : Création d'AVD Multi-versions (⭐⭐)

### Objectif
Créer plusieurs émulateurs pour tester sur différentes versions Android.

### Instructions

1. **Créer 3 AVD** avec les configurations suivantes :

#### AVD 1 : Test Moderne
- **Nom** : `Pixel6_API35_Modern`
- **Device** : Pixel 6
- **System Image** : API 35 (Android 15) - Google Play
- **RAM** : 4096 MB
- **Graphics** : Hardware

#### AVD 2 : Test Standard
- **Nom** : `Pixel5_API34_Standard`
- **Device** : Pixel 5
- **System Image** : API 34 (Android 14) - Google APIs
- **RAM** : 2048 MB
- **Graphics** : Hardware

#### AVD 3 : Test Compatibilité
- **Nom** : `Nexus5_API24_Compat`
- **Device** : Nexus 5
- **System Image** : API 24 (Android 7.0)
- **RAM** : 2048 MB
- **Graphics** : Automatic

2. **Lancer chaque AVD** et vérifier qu'il démarre correctement

3. **Mesurer le temps de démarrage** de chacun

### Livrables

- Capture d'écran du Device Manager montrant les 3 AVD créés
- Tableau des temps de démarrage
- Screenshot de chaque émulateur lancé

### Questions

1. Quel AVD démarre le plus rapidement ? Pourquoi ?
2. Quelle différence observez-vous entre Google Play et Google APIs ?

---

## Exercice 3 : Maîtriser ADB (⭐⭐)

### Objectif
Utiliser les commandes ADB pour manipuler un émulateur.

### Instructions

1. **Lancer un émulateur** (n'importe lequel)

2. **Vérifier la connexion** :
```bash
adb devices
```

3. **Installer une application système** :
```bash
# Télécharger une APK simple (ex: calculatrice)
# Installer avec ADB
adb install calculator.apk
```

4. **Explorer le système de fichiers** :
```bash
adb shell ls /sdcard/
adb shell ls /system/app/
```

5. **Créer un fichier de test** :
```bash
adb shell "echo 'Test ISITCOM' > /sdcard/test.txt"
adb shell cat /sdcard/test.txt
```

6. **Récupérer le fichier** :
```bash
adb pull /sdcard/test.txt ./
```

7. **Prendre un screenshot** :
```bash
adb shell screencap /sdcard/screenshot.png
adb pull /sdcard/screenshot.png
```

8. **Voir les logs système** :
```bash
adb logcat | grep "System"
```

### Livrables

- Document texte avec **toutes les commandes exécutées**
- Screenshots des résultats
- Fichier `test.txt` récupéré
- Screenshot de l'émulateur

---

## Exercice 4 : Optimisation Émulateur (⭐⭐⭐)

### Objectif
Comparer les performances avec/sans accélération matérielle.

### Instructions

#### Partie A : Sans accélération

1. Créer un AVD **sans accélération** :
   - Graphics : Software
   - Boot option : Cold boot
   - RAM : 1536 MB

2. Mesurer :
   - Temps de démarrage
   - Fluidité des animations (subjectif)
   - Temps pour ouvrir l'app Paramètres

#### Partie B : Avec accélération

1. Créer un AVD **avec accélération** :
   - Graphics : Hardware - GLES 2.0
   - Boot option : Quick boot
   - RAM : 4096 MB
   - Vérifier HAXM/KVM activé

2. Mesurer les mêmes critères

### Livrables

**Tableau comparatif** :

| Critère | Sans accélération | Avec accélération | Gain |
|---------|-------------------|-------------------|
------|
| Temps boot | | | |
| Fluidité | | | |
| Ouverture app | | | |

**Analyse** : Rédiger un paragraphe (5-10 lignes) expliquant l'importance de l'accélération matérielle.

---

## Exercice 5 : Dépannage Avancé (⭐⭐⭐)

### Objectif
Résoudre des problèmes courants d'installation.

### Scénarios à simuler et résoudre

#### Scénario 1 : Port ADB occupé

```bash
# Tuer le serveur ADB
adb kill-server

# Identifier le processus utilisant le port 5037
# Windows
netstat -ano | findstr :5037

# Linux/Mac
lsof -i :5037

# Redémarrer ADB
adb start-server
adb devices
```

#### Scénario 2 : Gradle cache corrompu

```bash
# Supprimer le cache Gradle
# Windows
rmdir /s %USERPROFILE%\.gradle\caches

# Linux/Mac
rm -rf ~/.gradle/caches

# Resynchroniser
./gradlew clean build --refresh-dependencies
```

#### Scénario 3 : Émulateur figé

```bash
# Lister les émulateurs en cours
adb devices

# Redémarrer l'émulateur
adb -s emulator-5554 reboot

# Ou forcer l'arrêt
adb -s emulator-5554 emu kill
```

### Livrables

- **Guide de dépannage** (format PDF ou Markdown)
- Au moins **5 problèmes** avec leurs solutions
- Captures d'écran des résolutions

---

## Exercice 6 : Configuration Multi-OS (⭐⭐⭐⭐)

### Objectif
Documenter l'installation complète pour les 3 OS.

### Instructions

Créer un **guide d'installation illustré** pour :

1. **Windows 10/11**
2. **macOS (Intel et Apple Silicon)**
3. **Ubuntu 22.04 LTS**

Pour chaque OS, documenter :
- Prérequis système
- Étapes d'installation Android Studio
- Configuration SDK
- Création AVD
- Test avec commande `adb devices`
- Problèmes spécifiques à l'OS

### Livrables

**Document structuré** (15-20 pages) avec :
- Table des matières
- Captures d'écran à chaque étape
- Encadrés "⚠️ Attention" pour pièges
- Section "❌ Problèmes connus"

---

## Projet Bonus : Script d'Installation Automatique (⭐⭐⭐⭐⭐)

### Objectif
Créer un script qui automatise l'installation.

### Fonctionnalités requises

```bash
#!/bin/bash
# install-android-env.sh

# Vérifier prérequis
# Télécharger Android Studio
# Extraire archive
# Configurer SDK
# Télécharger system images API 35, 34, 24
# Créer 2 AVD par défaut
# Configurer variables d'environnement
# Vérifier installation
```

### Livrables

- **Script bash** (Linux/Mac) ou **PowerShell** (Windows)
- **README** expliquant l'usage
- **Vidéo** (2-3 min) de démonstration

---

## 📊 Barème

| Exercice | Points | Difficulté |
|----------|--------|------------|
| Exercice 1 | 5 | ⭐ |
| Exercice 2 | 10 | ⭐⭐ |
| Exercice 3 | 10 | ⭐⭐ |
| Exercice 4 | 15 | ⭐⭐⭐ |
| Exercice 5 | 20 | ⭐⭐⭐ |
| Exercice 6 | 30 | ⭐⭐⭐⭐ |
| Bonus | 10 | ⭐⭐⭐⭐⭐ |

**Total** : 100 points (+ 10 bonus)

## ⏰ Durée Estimée

- Exercices 1-3 : 1h30
- Exercices 4-5 : 2h
- Exercice 6 : 3h
- Bonus : 2h

---

[Retour au Module 2](./README.md)