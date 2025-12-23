# 🚀 Guide de démarrage rapide Android

## 🎯 Pour les étudiants qui débutent

Ce guide vous accompagne pas à pas pour créer votre premier projet Android.

---

## ✅ Étape 1 : Vérifier les prérequis (5 min)

### Matériel nécessaire

☐ **Ordinateur** :
  - Windows 10/11, macOS 10.14+, ou Linux
  - Processeur 64 bits
  - **8 Go RAM minimum** (16 Go recommandé)
  - **10 Go d'espace disque libre**
  - Connexion Internet

☐ **Téléphone Android** (optionnel mais recommandé) :
  - Android 7.0 (API 24) ou supérieur
  - Câble USB

---

## 📥 Étape 2 : Installer Android Studio (20 min)

### Téléchargement

1. Aller sur [developer.android.com/studio](https://developer.android.com/studio)
2. Cliquer sur **"Download Android Studio"**
3. Accepter les conditions
4. Télécharger (environ 1 Go)

### Installation

**Windows** :
1. Exécuter le fichier `.exe`
2. Suivre l'assistant (tout en "Next")
3. Installer dans le dossier par défaut

**macOS** :
1. Ouvrir le fichier `.dmg`
2. Glisser Android Studio dans Applications
3. Lancer depuis le Launchpad

**Linux (Ubuntu/Debian)** :
```bash
sudo snap install android-studio --classic
# OU
sudo apt install android-studio
```

### Premier lancement (15 min)

1. **Lancer Android Studio**
2. Choisir **"Standard"** dans l'assistant
3. Accepter les licences
4. **Attendre le téléchargement** du SDK (2-3 Go)
5. **Terminer** l'installation

⚠️ **Si erreur "SDK not found"** :
- Tools → SDK Manager
- Vérifier que Android SDK est installé

---

## 🎉 Étape 3 : Créer votre premier projet (10 min)

### Nouveau projet

1. **File** → **New** → **New Project**
2. Choisir **"Empty Views Activity"**
3. Cliquer **Next**

### Configuration

```
Name: HelloISITCOM
Package name: tn.isitcom.helloisitcom
Save location: (choisir un dossier)
Language: Java
Minimum SDK: API 24 (Android 7.0)
```

4. Cliquer **Finish**
5. **Attendre** que Gradle synchronise (2-5 min)

✅ **Succès** : Vous voyez le code de `MainActivity.java`

---

## 📱 Étape 4 : Exécuter l'application (15 min)

### Option A : Sur émulateur (recommandé pour débuter)

1. Cliquer sur l'icône **Device Manager** (📱 en haut à droite)
2. **Create Device**
3. Choisir **Pixel 6** (ou autre modèle)
4. **Next**
5. Télécharger une **image système** :
   - Recommandé : **UpsideDownCake (API 34)**
   - Cliquer sur **Download** (environ 1 Go)
6. **Next** → **Finish**
7. **Lancer** l'émulateur (triangle vert ▶)
8. **Attendre** le démarrage (1-2 min la première fois)

### Option B : Sur téléphone réel

1. **Sur le téléphone** :
   - Paramètres → À propos du téléphone
   - Taper 7 fois sur **"Numéro de build"**
   - Retour → Options développeur
   - Activer **"Débogage USB"**

2. **Connecter** le téléphone par USB
3. **Autoriser** le débogage sur le téléphone
4. Vérifier qu'il apparaît dans Android Studio

### Exécuter

1. Cliquer sur le bouton **Run** ▶ (triangle vert)
2. Choisir l'émulateur ou le téléphone
3. **Attendre** la compilation (30 sec - 2 min)

🎉 **Félicitations !** Vous voyez "Hello World!" sur l'écran

---

## ✏️ Étape 5 : Modifier l'interface (10 min)

### Changer le texte

1. Ouvrir **`app/res/layout/activity_main.xml`**
2. Trouver :
```xml
<TextView
    android:text="Hello World!"
    ... />
```
3. Remplacer par :
```xml
<TextView
    android:text="Bienvenue à l'ISITCOM!"
    android:textSize="24sp"
    android:textColor="#2196F3"
    ... />
```
4. **Run** ▶ pour voir le changement

### Ajouter un bouton

1. Dans `activity_main.xml`, après le `</TextView>`, ajouter :
```xml
<Button
    android:id="@+id/btnHello"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Cliquez-moi"
    app:layout_constraintTop_toBottomOf="@id/textView"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="16dp" />
```

2. Dans **`MainActivity.java`**, dans `onCreate()`, après `setContentView(...)` :
```java
Button btn = findViewById(R.id.btnHello);
btn.setOnClickListener(v -> {
    Toast.makeText(this, "Bonjour l'ISITCOM!", Toast.LENGTH_SHORT).show();
});
```

3. **Run** ▶ et tester le clic

---

## 🔧 Raccourcis clavier utiles

| Action | Windows/Linux | macOS |
|--------|---------------|-------|
| Exécuter | Shift + F10 | Ctrl + R |
| Format code | Ctrl + Alt + L | Cmd + Opt + L |
| Rechercher | Ctrl + F | Cmd + F |
| Auto-complétion | Ctrl + Space | Ctrl + Space |
| Importer classe | Alt + Enter | Opt + Enter |

---

## ⚠️ Problèmes fréquents

### Émulateur ne démarre pas

**Solution 1** : Activer la virtualisation dans le BIOS
- Redémarrer PC → F2 ou Del pendant démarrage
- Chercher "Virtualization" ou "VT-x"
- Activer et sauvegarder

**Solution 2** : Utiliser un téléphone réel

### Android Studio très lent

**Solution** : Augmenter la RAM
1. Help → Edit Custom VM Options
2. Modifier :
```
-Xmx4096m
```
3. Redémarrer Android Studio

### "Cannot resolve symbol R"

**Solution** :
1. File → Sync Project with Gradle Files
2. Build → Clean Project
3. Build → Rebuild Project

### Gradle sync échoue

**Solution** :
1. Vérifier la connexion Internet
2. File → Invalidate Caches / Restart
3. Supprimer le dossier `.gradle` du projet

---

## 📚 Prochaines étapes

✅ Félicitations ! Vous avez :
- ✅ Installé Android Studio
- ✅ Créé votre premier projet
- ✅ Exécuté une application
- ✅ Modifié l'interface

### Continuer l'apprentissage

1. **Module 1** : [Environnement Android](01-environnement-android/README.md)
2. **Module 2** : [SDK et outils](02-sdk-et-outils/README.md)
3. **Module 3** : [Premier projet](03-creation-premier-projet/README.md)
4. **TD01** : [Installation et configuration](TD-global/td01-installation-premier-projet.md)

---

## 📞 Besoin d'aide ?

- **Documentation** : [developer.android.com](https://developer.android.com)
- **Stack Overflow** : [stackoverflow.com/questions/tagged/android](https://stackoverflow.com/questions/tagged/android)
- **Discord ISITCOM** : (demander le lien au prof)
- **Email enseignant** : abdelwaheb.gueddes@isitc.u-sousse.tn

---

🎓 **Bon démarrage !** 🚀

---

👨‍🏫 **Guide démarrage rapide** | ISITCOM 2025-2026
