# 🧠 Quiz : SDK et Outils

## Questions à Choix Multiples

### Question 1
**Quelle est la RAM minimum recommandée pour Android Studio ?**

A) 4 GB  
B) 8 GB ✅  
C) 16 GB  
D) 32 GB

<details>
<summary>Explication</summary>
8 GB est le minimum officiel, mais 16 GB est fortement recommandé pour un confort optimal, surtout avec l'émulateur.
</details>

---

### Question 2
**Que signifie SDK ?**

A) System Development Kit  
B) Software Development Kit ✅  
C) Standard Developer Kit  
D) Secure Debug Kit

<details>
<summary>Explication</summary>
SDK = Software Development Kit, ensemble d'outils et bibliothèques pour développer des applications.
</details>

---

### Question 3
**Quel API level est recommandé comme minSdk en 2025 ?**

A) API 21  
B) API 24 ✅  
C) API 30  
D) API 35

<details>
<summary>Explication</summary>
API 24 (Android 7.0 Nougat) couvre ~95% des appareils, excellent compromis entre compatibilité et fonctionnalités modernes.
</details>

---

### Question 4
**Qu'est-ce qu'un AVD ?**

A) Android Virtual Display  
B) Android Virtual Device ✅  
C) Advanced Video Driver  
D) Application Version Descriptor

<details>
<summary>Explication</summary>
AVD = Android Virtual Device, un émulateur qui simule un appareil Android physique.
</details>

---

### Question 5
**Quelle architecture processeur est la plus rapide pour AVD ?**

A) ARM v7  
B) ARM v8  
C) x86  
D) x86_64 ✅

<details>
<summary>Explication</summary>
x86_64 bénéficie de l'accélération matérielle sur PC (HAXM/Hypervisor), donc beaucoup plus rapide qu'ARM.
</details>

---

### Question 6
**Que fait Gradle dans Android Studio ?**

A) Édite le code  
B) Compile et construit l'APK ✅  
C) Dessine les interfaces  
D) Gère les versions Git

<details>
<summary>Explication</summary>
Gradle est le système de build qui compile le code Java/Kotlin, gère les dépendances et génère l'APK/AAB.
</details>

---

### Question 7
**Que signifie ADB ?**

A) Android Debug Bridge ✅  
B) Application Data Base  
C) Advanced Developer Bundle  
D) Android Device Builder

<details>
<summary>Explication</summary>
ADB = Android Debug Bridge, outil en ligne de commande pour communiquer avec appareils Android.
</details>

---

### Question 8
**Commande ADB pour lister les appareils connectés ?**

A) adb list  
B) adb show  
C) adb devices ✅  
D) adb connect

<details>
<summary>Explication</summary>
`adb devices` affiche tous les appareils (émulateurs + réels) connectés et leur statut.
</details>

---

### Question 9
**Où sont stockés les fichiers SDK par défaut sur Windows ?**

A) C:\Program Files\Android\Sdk  
B) C:\Android\Sdk  
C) C:\Users\<username>\AppData\Local\Android\Sdk ✅  
D) C:\Users\<username>\Documents\Android\Sdk

<details>
<summary>Explication</summary>
Par défaut, le SDK Android est dans le dossier AppData\Local de l'utilisateur Windows.
</details>

---

### Question 10
**Quelle technologie accélère l'émulateur sur Windows ?**

A) DirectX  
B) OpenGL  
C) Intel HAXM ou WHPX ✅  
D) Vulkan

<details>
<summary>Explication</summary>
Intel HAXM (ou WHPX si Hyper-V activé) permet l'accélération matérielle de l'émulateur Android.
</details>

---

## Questions Ouvertes

### Question 11
**Expliquez la différence entre minSdk, targetSdk et compileSdk.**

<details>
<summary>Réponse suggérée</summary>

**minSdk** (API minimum) :
- Version Android minimale pour exécuter l'app
- Ex: minSdk 24 = fonctionne sur Android 7.0+
- Plus bas = plus de compatibilité mais moins de features

**targetSdk** (API cible) :
- Version Android pour laquelle l'app est optimisée
- Google Play impose targetSdk récent (35 en 2025)
- Active comportements spécifiques à cette version

**compileSdk** (API compilation) :
- Version SDK utilisée pour compiler
- Doit être ≥ targetSdk
- Permet d'utiliser nouvelles APIs

**Exemple recommandé 2025** :
```gradle
minSdk 24      // 95% couverture
targetSdk 35   // Dernière stable
compileSdk 35  // Même que targetSdk
```
</details>

---

### Question 12
**Pourquoi Quick Boot est important pour AVD ?**

<details>
<summary>Réponse suggérée</summary>

**Sans Quick Boot (Cold Boot)** :
- Démarre l'émulateur de zéro
- Charge le système Android complet
- Durée : ~60-90 secondes
- Comme allumer un téléphone éteint

**Avec Quick Boot (activé)** :
- Restaure un snapshot de l'état précédent
- Reprend où on s'était arrêté
- Durée : ~6-10 secondes
- Comme sortir un téléphone de veille

**Avantages** :
- ⏱️ Gain de temps énorme en développement
- 🔄 Tests plus rapides (redémarrages fréquents)
- 💾 État préservé (apps déjà installées)

**Configuration** :
Device Manager → ⚙️ → Boot option → Quick Boot
</details>

---

### Question 13
**Quelles sont les 3 commandes ADB les plus utiles en développement ?**

<details>
<summary>Réponse suggérée</summary>

**1. `adb logcat`** 🔍
- Affiche logs en temps réel
- Debug : voir erreurs, exceptions, messages
- Filtrer : `adb logcat | grep MonApp`
- Essentiel pour comprendre les crashes

**2. `adb install -r app.apk`** 📦
- Installe ou met à jour une APK
- `-r` = remplace si déjà installée
- Plus rapide que via Android Studio parfois
- Utile pour tester sur plusieurs appareils

**3. `adb shell`** 💻
- Accès terminal Linux de l'appareil
- Explorer fichiers : `ls`, `cd`
- Permissions : `pm grant <package> <permission>`
- Base de données : `sqlite3 /data/data/.../databases/db`

**Bonus** :
- `adb devices` : lister appareils
- `adb uninstall <package>` : désinstaller
- `adb pull/push` : transférer fichiers
</details>

---

## 🎯 Score de Maîtrise

- **13/13** : Expert des outils ! 🏆
- **10-12** : Très bien, prêt à développer 👍
- **7-9** : Bon début, pratiquer les exercices 📚
- **< 7** : Relire le module et refaire l'installation 💪

---

[Retour au Module 2](./README.md)