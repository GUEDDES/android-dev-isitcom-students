# Corrections - Exercices Module 2

## Exercice 1 : Installation et configuration

### Solution

**Étape 1 : Ouvrir SDK Manager**
```
File > Settings > Appearance & Behavior > System Settings > Android SDK
```

**Étape 2 : Vérifier API installées**

Dans l'onglet **SDK Platforms**, cocher :
- ☑ Android 15.0 (VanillaIceCream) - API Level 35
- ☑ Android 14.0 (UpsideDownCake) - API Level 34
- ☑ Android 13.0 (Tiramisu) - API Level 33

**Étape 3 : Vérifier SDK Tools**

Dans l'onglet **SDK Tools**, cocher :
- ☑ Android SDK Build-Tools (dernière version)
- ☑ Android Emulator
- ☑ Android SDK Platform-Tools
- ☑ Google Play services (optionnel)

**Étape 4 : Appliquer**

Cliquer sur **Apply** puis **OK**. Téléchargement automatique.

### Points de vigilance

⚠️ Taille téléchargement : ~1-2 GB par API  
⚠️ Vérifier connexion internet stable  
⚠️ Libérer espace disque suffisant (5+ GB)  

---

## Exercice 2 : Création AVD

### Solution complète

**Étape 1 : Ouvrir Device Manager**
```
Tools > Device Manager
```
Ou icône téléphone dans la barre d'outils

**Étape 2 : Créer appareil**

1. Cliquer **Create Device**
2. Catégorie : **Phone**
3. Sélectionner : **Pixel 5**
   - Taille : 6.0"
   - Résolution : 1080 x 2340
   - Densité : 440 dpi
4. Cliquer **Next**

**Étape 3 : Image système**

1. Onglet **Recommended**
2. Sélectionner **Tiramisu (Android 13.0, API 33)**
3. Si non téléchargée, cliquer icône download
4. Cliquer **Next**

**Étape 4 : Configuration AVD**

```
AVD Name: Pixel_5_API_33
Startup orientation: Portrait
Advanced Settings:
  - RAM: 2048 MB
  - Internal Storage: 2048 MB
  - SD Card: (laisser vide)
  - Graphics: Automatic
```

**Étape 5 : Lancement**

1. Cliquer **Finish**
2. Dans Device Manager, cliquer icône Play (▶️)
3. Attendre démarrage (30-60 secondes)

### Commandes alternatives (CLI)

```bash
# Lister AVD
avdmanager list avd

# Créer AVD
avdmanager create avd \
  -n Pixel_5_API_33 \
  -k "system-images;android-33;google_apis;x86_64" \
  -d "pixel_5"

# Lancer AVD
emulator -avd Pixel_5_API_33
```

### Dépannage

| Problème | Solution |
|----------|----------|
| AVD ne démarre pas | Vérifier virtualisation BIOS activée |
| Très lent | Augmenter RAM, utiliser image x86_64 |
| Écran noir | Changer Graphics à "Software" |

---

## Exercice 3 : Tests ADB

### Solutions commandes

#### Commande 1 : Lister appareils

```bash
$ adb devices
```

**Résultat attendu :**
```
List of devices attached
emulator-5554   device
```

**Explication :**
- `emulator-5554` : identifiant de l'émulateur
- `device` : statut connecté et prêt

#### Commande 2 : Version Android

```bash
$ adb shell getprop ro.build.version.release
```

**Résultat attendu :**
```
13
```

**Explication :**
Affiche la version Android de l'appareil connecté (ici Android 13)

#### Commande 3 : Logs système

```bash
$ adb logcat -d | head -20
```

**Résultat attendu :**
```
--------- beginning of main
12-23 10:30:15.123  1234  1234 I System  : System server starting
12-23 10:30:15.456  1234  1234 I ActivityManager: Start proc
...
```

**Explication :**
- `logcat -d` : dump des logs actuels (sans suivre)
- `head -20` : afficher seulement 20 premières lignes

### Commandes ADB supplémentaires utiles

```bash
# Installer APK
adb install mon_app.apk

# Désinstaller app
adb uninstall tn.isitcom.monapp

# Copier fichier vers appareil
adb push fichier.txt /sdcard/

# Récupérer fichier depuis appareil
adb pull /sdcard/fichier.txt .

# Ouvrir shell interactif
adb shell

# Redémarrer appareil
adb reboot

# Capturer screenshot
adb shell screencap /sdcard/screen.png
adb pull /sdcard/screen.png

# Enregistrer vidéo écran
adb shell screenrecord /sdcard/video.mp4
# (Arrêter avec Ctrl+C après quelques secondes)
adb pull /sdcard/video.mp4
```

### Fichier résultat type (resultat_adb.txt)

```txt
=== Tests ADB - [Votre Nom] ===
Date : 23/12/2025

--- Commande 1 : adb devices ---
List of devices attached
emulator-5554   device

--- Commande 2 : Version Android ---
13

--- Commande 3 : Logs (20 premières lignes) ---
--------- beginning of main
12-23 10:30:15.123  1234  1234 I System  : System server starting
12-23 10:30:15.456  1234  1234 I ActivityManager: Start proc 1234
12-23 10:30:15.789  1234  1234 I WindowManager: Window added
...
(17 lignes supplémentaires)

--- Informations supplémentaires ---
Modele appareil : Pixel 5
API Level : 33
```

---

## Exercice 4 : Analyse Gradle

### Solution détaillée

#### Fichier build.gradle (Module: app)

```gradle
plugins {
    id 'com.android.application'
}

android {
    namespace 'tn.isitcom.monapp'
    compileSdk 35  // ①
    
    defaultConfig {
        applicationId "tn.isitcom.monapp"  // ②
        minSdk 24  // ③
        targetSdk 35  // ④
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

dependencies {  // ⑤
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

#### Explications détaillées

**① compileSdk (35)**
- **Définition** : Version de l'API Android utilisée pour compiler l'app
- **Rôle** : Détermine les API disponibles lors du développement
- **Exemple** : Si `compileSdk 35`, vous pouvez utiliser toutes les API jusqu'à Android 15
- **Conseil** : Toujours utiliser la dernière version stable

**② applicationId ("tn.isitcom.monapp")**
- **Définition** : Identifiant unique de l'application sur Play Store
- **Format** : notation DNS inversée (domaine.organisation.app)
- **Importance** : Ne peut pas être changé après publication
- **Exemple** : `tn.isitcom.calculatrice`, `tn.isitcom.notes`

**③ minSdk (24)**
- **Définition** : Version Android minimum supportée
- **Rôle** : Définit la compatibilité descendante
- **API 24** = Android 7.0 (Nougat) - sorti 2016
- **Couverture** : ~95% des appareils en 2025
- **Compromis** : Plus bas = plus d'appareils, mais moins de nouvelles API

**④ targetSdk (35)**
- **Définition** : Version Android cible pour laquelle l'app est optimisée
- **Rôle** : Android applique les comportements de cette version
- **Importance** : Google Play impose targetSdk récent (35 en 2025)
- **Conseil** : Maintenir à jour avec compileSdk

**⑤ Dépendances clés**

##### Exemple 1 : AppCompat
```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
```
- **Rôle** : Compatibilité avec anciennes versions Android
- **Contient** : AppCompatActivity, Themes, Widgets compatibles
- **Essentielle** : Oui, base de toute app Android

##### Exemple 2 : Material Design
```gradle
implementation 'com.google.android.material:material:1.11.0'
```
- **Rôle** : Composants Material Design 3
- **Contient** : MaterialButton, TextInputLayout, CardView, FAB...
- **Essentielle** : Fortement recommandée pour UI moderne

#### Schéma relations SDK

```
minSdk (24)  ≤  targetSdk (35)  ≤  compileSdk (35)
    │               │                  │
    │               │                  └───> API disponibles
    │               └─────────────────> Comportements appliqués
    └────────────────────────────> Appareils compatibles
```

### Document livrable type (analyse_gradle.md)

```markdown
# Analyse Gradle - [Votre Nom]

## Projet analysé
- Nom : MonApplication
- Package : tn.isitcom.monapp

## Configuration SDK

### compileSdk : 35
**Explication :** Version API pour compilation (Android 15).  
**Impact :** Accès aux dernières API Android.

### minSdk : 24
**Explication :** Version minimum Android 7.0.  
**Impact :** Compatible avec 95% des appareils.

### targetSdk : 35
**Explication :** Version cible Android 15.  
**Impact :** Application des nouveaux comportements sécurité.

### applicationId : "tn.isitcom.monapp"
**Explication :** Identifiant unique Google Play.  
**Impact :** Ne peut pas être modifié après publication.

## Dépendances

### 1. androidx.appcompat:appcompat:1.6.1
**Rôle :** Compatibilité descendante  
**Contenu :** AppCompatActivity, thèmes, widgets  
**Importance :** Critique - base de l'app

### 2. com.google.android.material:material:1.11.0
**Rôle :** Composants Material Design  
**Contenu :** Buttons, Cards, TextInputLayout  
**Importance :** Très recommandée - UI moderne

## Conclusion
Configuration standard et appropriée pour une application moderne.
```

---

## Mini-projet : Configuration avancée

### Solution complète

#### Étape 1 : Création des deux AVD

**AVD 1 : Tests rapides**
```
Nom : Quick_Test_API_30
Appareil : Pixel 3a (petit écran 5.6")
Système : Android 11 (API 30)
RAM : 1536 MB
Stockage : 1024 MB
Graphics : Hardware
```

**AVD 2 : Tests finaux**
```
Nom : Final_Test_API_34
Appareil : Pixel 6 Pro (grand écran 6.7")
Système : Android 14 (API 34)
RAM : 2048 MB
Stockage : 2048 MB
Graphics : Automatic
```

#### Étape 2 : Création projet test

```bash
File > New > New Project
Template : Empty Views Activity
Name : TestAVD
Package : tn.isitcom.testavd
Minimum SDK : API 24
```

#### Étape 3 : Tests de performance

**Méthodologie :**
1. Nettoyer projet : `Build > Clean Project`
2. Lancer AVD 1 (Quick_Test_API_30)
3. Chronométrer depuis clic Run jusqu'à app visible
4. Fermer AVD 1
5. Répéter avec AVD 2 (Final_Test_API_34)
6. Comparer résultats

### Rapport type (rapport_avd.md)

```markdown
# Rapport Comparatif AVD
**Étudiant :** [Votre Nom]  
**Date :** 23/12/2025

## Configuration matérielle
- Processeur : Intel i5 / AMD Ryzen 5
- RAM : 8 GB
- SSD : Oui

## AVD 1 : Tests rapides
| Caractéristique | Valeur |
|------------------|--------|
| Nom | Quick_Test_API_30 |
| Appareil | Pixel 3a |
| API Level | 30 (Android 11) |
| Taille écran | 5.6" - 1080x2220 |
| RAM | 1536 MB |

**Temps de lancement :**
- Démarrage AVD : 25 secondes
- Lancement app : 8 secondes
- **Total : 33 secondes**

## AVD 2 : Tests finaux
| Caractéristique | Valeur |
|------------------|--------|
| Nom | Final_Test_API_34 |
| Appareil | Pixel 6 Pro |
| API Level | 34 (Android 14) |
| Taille écran | 6.7" - 1440x3120 |
| RAM | 2048 MB |

**Temps de lancement :**
- Démarrage AVD : 35 secondes
- Lancement app : 12 secondes
- **Total : 47 secondes**

## Analyse comparative

### Performances
- AVD 1 est **30% plus rapide** au démarrage
- AVD 1 consomme **25% moins de RAM**
- AVD 2 a résolution **33% plus élevée**

### Cas d'usage recommandés

**AVD 1 (Quick_Test_API_30) :**
- ✅ Tests rapides pendant développement
- ✅ Vérification logique métier
- ✅ Débogage code
- ❌ Tests UI précis

**AVD 2 (Final_Test_API_34) :**
- ✅ Tests finaux avant livraison
- ✅ Vérification UI détaillée
- ✅ Tests avec dernière API
- ✅ Captures d'écran professionnelles

## Conclusion
Strat u00e9gie optimale : utiliser AVD 1 pour 80% du développement,  
AVD 2 pour validation finale.

## Captures d'écran
[Insérer screenshots des deux AVD avec l'app lancée]
```

---

👨‍🏫 **Corrections Module 2** | ISITCOM 2025-2026
