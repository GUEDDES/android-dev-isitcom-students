# 🏋️ Exercices Pratiques : SDK et Outils

## Exercice 1 : Installation et Vérification

### Objectif
Vérifier que votre environnement est correctement configuré.

### Consignes

1. **Vérifier la version d'Android Studio** :
   - Menu : `Help > About`
   - Noter la version (ex: 2024.2.1)

2. **Vérifier les SDK installés** :
   - Ouvrir SDK Manager
   - Prendre une capture d'écran de l'onglet "SDK Platforms"
   - Vérifier API 24, 34, 35

3. **Tester ADB** :
   ```bash
   # Ouvrir Terminal dans Android Studio
   adb version
   adb devices
   ```
   - Copier le résultat

4. **Calculer l'espace disque utilisé** :
   - SDK Manager > Android SDK Location
   - Noter le chemin
   - Vérifier la taille du dossier

### Livrables

- Document PDF avec :
  - Version Android Studio
  - Capture SDK Manager
  - Résultat commandes ADB
  - Espace disque utilisé

---

## Exercice 2 : Création d'Émulateurs

### Objectif
Créer plusieurs émulateurs pour tests multiples.

### Consignes

Créer 3 AVD différents :

#### AVD 1 : Smartphone Moderne
- **Appareil** : Pixel 8
- **Système** : API 35 (Android 15)
- **Nom** : `Modern_Phone_API35`
- **RAM** : 2048 MB
- **Résolution** : 1080x2400

#### AVD 2 : Smartphone Ancien
- **Appareil** : Pixel 4
- **Système** : API 24 (Android 7.0)
- **Nom** : `Legacy_Phone_API24`
- **RAM** : 1536 MB
- **Résolution** : 1080x1920

#### AVD 3 : Tablette
- **Appareil** : Pixel Tablet
- **Système** : API 34 (Android 14)
- **Nom** : `Tablet_API34`
- **RAM** : 3072 MB
- **Résolution** : 2560x1600

### Tâches

1. Créer les 3 AVD
2. Lancer chaque AVD et prendre une capture
3. Mesurer le temps de démarrage de chacun
4. Tester la rotation (portrait ↔ paysage)
5. Noter les différences d'interface

### Livrables

- 3 captures d'écran des AVD lancés
- Tableau comparatif :

| AVD | Temps démarrage | RAM utilisée | Fluidité |
|-----|-----------------|--------------|----------|
| Modern | ? | ? | ? |
| Legacy | ? | ? | ? |
| Tablet | ? | ? | ? |

---

## Exercice 3 : Commandes ADB

### Objectif
Manipuler ADB pour interagir avec un émulateur.

### Prérequis
Lancer un AVD (n'importe lequel).

### Consignes

Exécuter les commandes suivantes et noter les résultats :

```bash
# 1. Lister les appareils
adb devices
# Résultat attendu : emulator-5554    device

# 2. Obtenir des infos système
adb shell getprop ro.build.version.release
adb shell getprop ro.product.model
adb shell getprop ro.build.version.sdk

# 3. Lister les apps installées
adb shell pm list packages | grep google

# 4. Créer un fichier sur l'émulateur
echo "Hello ISITCom" > test.txt
adb push test.txt /sdcard/Download/

# 5. Vérifier le fichier
adb shell ls /sdcard/Download/
adb shell cat /sdcard/Download/test.txt

# 6. Récupérer le fichier
adb pull /sdcard/Download/test.txt test_downloaded.txt
cat test_downloaded.txt

# 7. Prendre une capture d'écran
adb shell screencap /sdcard/screenshot.png
adb pull /sdcard/screenshot.png .

# 8. Enregistrer une vidéo (Ctrl+C pour arrêter)
adb shell screenrecord /sdcard/demo.mp4
# Attendre 5 secondes, Ctrl+C
adb pull /sdcard/demo.mp4 .

# 9. Voir les logs en temps réel
adb logcat | grep ISITCom
# Taper quelque chose dans l'émulateur

# 10. Redémarrer l'émulateur
adb reboot
```

### Livrables

- Document avec résultats de chaque commande
- Fichier `screenshot.png` récupéré
- Explication de ce que fait chaque commande

---

## Exercice 4 : Configuration Avancée

### Objectif
Personnaliser Android Studio pour productivité maximale.

### Consignes

#### 1. Thème et Apparence

- `File > Settings > Appearance & Behavior > Appearance`
- Tester les thèmes : Darcula, Light, High Contrast
- Choisir votre préféré

#### 2. Éditeur de Code

- `Settings > Editor > Font`
  - Police : JetBrains Mono (ou Fira Code)
  - Taille : 14
  - Line spacing : 1.2

- `Settings > Editor > Color Scheme`
  - Tester différents schemes
  - Recommandation : Darcula, Monokai, One Dark

#### 3. Raccourcis Clavier

Apprendre ces raccourcis :

| Action | Windows/Linux | macOS |
|--------|---------------|-------|
| Recherche globale | Ctrl + Shift + F | Cmd + Shift + F |
| Auto-format code | Ctrl + Alt + L | Cmd + Opt + L |
| Complétion code | Ctrl + Space | Ctrl + Space |
| Renommer | Shift + F6 | Shift + F6 |
| Exécuter app | Shift + F10 | Ctrl + R |
| Déboguer app | Shift + F9 | Ctrl + D |
| Build project | Ctrl + F9 | Cmd + F9 |

#### 4. Plugins Utiles

- `Settings > Plugins`
- Installer :
  - ✅ **Rainbow Brackets** : Colorer les parenthèses
  - ✅ **Material Theme UI** : Interface moderne
  - ✅ **Key Promoter X** : Apprendre raccourcis
  - ✅ **GitToolBox** : Infos Git inline

#### 5. Performance

- `Help > Edit Custom VM Options`
- Augmenter la mémoire :
  ```
  -Xms2048m
  -Xmx8192m
  ```

### Livrables

- Capture d'écran de votre Android Studio personnalisé
- Liste des 5 raccourcis que vous utiliserez le plus
- Nom du thème choisi

---

## Exercice 5 : Résolution de Problèmes

### Objectif
Apprendre à diagnostiquer et résoudre les problèmes courants.

### Scénarios à Résoudre

#### Scénario 1 : Gradle Sync Failed

**Problème simulé** :
```
Could not resolve com.android.tools.build:gradle:8.5.0
```

**Tâches** :
1. Identifier la cause probable
2. Proposer 3 solutions
3. Expliquer comment vérifier la connexion Maven

#### Scénario 2 : Émulateur Lent

**Problème** : Émulateur prend 5 minutes à démarrer

**Tâches** :
1. Vérifier l'accélération matérielle
2. Configurer "Quick Boot"
3. Optimiser les paramètres AVD (RAM, Graphics)
4. Comparer avant/après

#### Scénario 3 : ADB Device Not Found

**Problème** : `adb devices` ne liste rien

**Tâches** :
1. Redémarrer serveur ADB : `adb kill-server && adb start-server`
2. Vérifier ports utilisés : `netstat -an | grep 5037`
3. Autoriser sur appareil si réel
4. Tester avec émulateur

### Livrables

- Document décrivant :
  - Diagnostic de chaque problème
  - Solutions appliquées
  - Résultat final
  - Leçons apprises

---

## Exercice 6 : Benchmark Émulateur

### Objectif
Comparer performances émulateur vs appareil réel.

### Consignes

Si vous avez un smartphone Android :

1. **Installer une app de benchmark** (via Play Store sur les deux) :
   - AnTuTu Benchmark
   - Ou Geekbench

2. **Exécuter le benchmark** :
   - Sur l'émulateur (API 35)
   - Sur votre smartphone réel

3. **Comparer les résultats** :
   - Score CPU
   - Score GPU
   - Score RAM
   - Score I/O

4. **Analyser** :
   - Quelles différences ?
   - Pourquoi ?
   - Quand utiliser émulateur vs réel ?

### Livrables

- Captures d'écran des scores
- Tableau comparatif
- Analyse (minimum 200 mots)

---

## Exercice 7 : Automatisation ADB

### Objectif
Créer un script pour automatiser des tâches ADB.

### Consignes

Créer un script bash (Linux/Mac) ou PowerShell (Windows) qui :

1. Vérifie si un appareil est connecté
2. Affiche les infos système (modèle, version Android)
3. Liste les apps installées
4. Prend une capture d'écran
5. La sauvegarde avec timestamp

**Script bash** :
```bash
#!/bin/bash

echo "=== ISITCom ADB Tool ==="

# Vérifier appareil
if ! adb devices | grep -q "device$"; then
    echo "❌ Aucun appareil connecté"
    exit 1
fi

echo "✅ Appareil détecté"

# Infos système
echo "📱 Modèle: $(adb shell getprop ro.product.model)"
echo "🤖 Android: $(adb shell getprop ro.build.version.release)"
echo "🔢 API: $(adb shell getprop ro.build.version.sdk)"

# Nombre d'apps
app_count=$(adb shell pm list packages | wc -l)
echo "📦 Apps installées: $app_count"

# Capture
timestamp=$(date +%Y%m%d_%H%M%S)
filename="screenshot_$timestamp.png"
adb shell screencap /sdcard/$filename
adb pull /sdcard/$filename .
adb shell rm /sdcard/$filename

echo "📸 Capture sauvegardée: $filename"
echo "✅ Terminé!"
```

### Livrables

- Script fonctionnel
- Capture d'écran de l'exécution
- Documentation du script

---

## Barème Total

| Exercice | Points |
|----------|--------|
| Ex 1 : Installation | 10 pts |
| Ex 2 : AVD | 15 pts |
| Ex 3 : ADB | 20 pts |
| Ex 4 : Configuration | 15 pts |
| Ex 5 : Troubleshooting | 20 pts |
| Ex 6 : Benchmark | 10 pts |
| Ex 7 : Script | 10 pts |
| **Total** | **100 pts** |

**Obligatoire** : Ex 1-5  
**Bonus** : Ex 6-7

---

[Retour au Module 2](./README.md)