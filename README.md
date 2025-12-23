# 🎯 Cours Android - ISITCOM 2025/2026

> Support de cours complet pour le développement d'applications Android natives en Java

[![Android](https://img.shields.io/badge/Android-15%20(API%2035)-3DDC84?logo=android)](https://developer.android.com/)
[![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

**Enseignant** : A. GUEDDES  
**Institution** : Institut Supérieur d'Informatique et des Technologies de Communication (ISITCOM)  
**Année universitaire** : 2025-2026

---

## 📚 Table des matières

### Modules théoriques (Cours)

| Module | Titre | Contenu |
|--------|-------|--------|
| **01** | [Environnement Android](01-environnement-android/) | Historique, architecture, types d'applications, marché |
| **02** | [SDK et outils](02-sdk-et-outils/) | Installation Android Studio, SDK Manager, AVD, ADB, Gradle |
| **03** | [Premier projet](03-creation-premier-projet/) | HelloWorld, structure projet, MainActivity, exécution |
| **04** | [Interfaces et layouts](04-interfaces-views-layouts/) | Views, widgets, ConstraintLayout, findViewById |
| **05** | [Cycle de vie et Intents](05-activites-cycle-vie-intents/) | Callbacks Activity, navigation, passage de données, Activity Result API |
| **06** | [RecyclerView](06-listes-recyclerview/) | Adapter, ViewHolder, listes dynamiques, gestion clics |
| **07** | [Room Database](07-donnees-room/) | Entity, DAO, CRUD, stockage local SQLite |
| **08** | [Fragments et Navigation](08-fragments-et-navigation/) | Fragments, Navigation Component, Bottom Navigation |
| **09** | [Ergonomie et Material Design](09-ergonomie-et-material/) | Thèmes, Material Components, Dark Mode |
| **10** | [Projets de synthèse](10-projets-synthese/) | Mini-projets complets, architecture MVVM |

### Travaux dirigés (TD)

Tous les TD sont disponibles dans le dossier [`TD-global/`](TD-global/)

| TD | Titre | Thème |
|----|-------|-------|
| **TD01** | Installation et configuration | Android Studio, SDK, premier projet |
| **TD02** | Interfaces utilisateur | Layouts, contraintes, views |
| **TD03** | Navigation multi-écrans | Activities, Intents, cycle de vie |
| **TD04** | Listes dynamiques | RecyclerView, Adapter, ViewHolder |
| **TD05** | Stockage local | Room Database, CRUD |
| **TD06** | Fragments | Navigation Component, Bottom Nav |
| **TD07** | Material Design | Thèmes, composants, ergonomie |
| **TD08** | Projet complet | Application Tasks Manager |
| **TD09** | API REST | Retrofit, JSON, images distantes |
| **TD10** | Projet final | Application complète au choix |

---

## 🚀 Démarrage rapide

### Prérequis

- **Ordinateur** : Windows 10/11, macOS, ou Linux
- **RAM** : 8 Go minimum (16 Go recommandé)
- **Disque** : 10 Go libres
- **Connexion internet** : pour télécharger SDK et dépendances

### Installation

1. **Télécharger Android Studio** : [developer.android.com/studio](https://developer.android.com/studio)
2. **Installer** : suivre l'assistant d'installation
3. **Configuration initiale** : choisir "Standard" lors du premier lancement
4. **SDK** : l'assistant téléchargera automatiquement les composants nécessaires

📖 Guide complet : [Module 2 - SDK et outils](02-sdk-et-outils/README.md)

### Premier projet

```bash
# Dans Android Studio
File > New > New Project > Empty Views Activity

Name: HelloIsitcom
Package: tn.isitcom.helloisitcom
Language: Java
Minimum SDK: API 24 (Android 7.0)
```

📖 Tutoriel complet : [Module 3 - Premier projet](03-creation-premier-projet/README.md)

---

## 📝 Structure du dépôt

```
android-dev-isitcom-students/
├── 01-environnement-android/       # Introduction à Android
│   ├── README.md                  # Cours principal
│   ├── fiche-synthese.md          # Résumé du module
│   ├── quiz.md                    # Quiz d'auto-évaluation
│   └── schemas/                   # Diagrammes et images
├── 02-sdk-et-outils/             # Configuration environnement
├── 03-creation-premier-projet/   # HelloWorld
├── 04-interfaces-views-layouts/  # UI et layouts
├── 05-activites-cycle-vie-intents/ # Navigation
├── 06-listes-recyclerview/       # Listes dynamiques
├── 07-donnees-room/              # Base de données
├── 08-fragments-et-navigation/   # Fragments
├── 09-ergonomie-et-material/     # Material Design
├── 10-projets-synthese/          # Projets complets
│
├── TD-global/                    # Tous les travaux dirigés
│   ├── README.md                  # Index des TD
│   ├── td01-installation/
│   ├── td02-interfaces/
│   ├── ...
│   └── td10-publication/
│
├── BONNES_PRATIQUES.md           # Conventions, architecture, performance
├── CHECKLIST.md                  # Guide développement complet
├── CONTRIBUTING.md               # Guide de contribution
├── EVALUATION.md                 # Critères d'évaluation
├── FAQ.md                        # Questions fréquentes (50+ Q&R)
├── GLOSSAIRE.md                  # Termes techniques A-Z
├── INDEX.md                      # Navigation rapide
├── QUICK_START.md                # Guide express
├── RACCOURCIS.md                 # Shortcuts Android Studio
├── RESSOURCES.md                 # Liens utiles externes
├── LICENSE                       # Licence MIT
└── README.md                     # Ce fichier
```

---

## 🎯 Objectifs pédagogiques

À la fin de ce cours, les étudiants seront capables de :

✅ Installer et configurer un environnement de développement Android  
✅ Créer des interfaces utilisateur adaptées et responsive  
✅ Gérer la navigation entre plusieurs écrans  
✅ Afficher des listes dynamiques avec RecyclerView  
✅ Stocker des données localement avec Room  
✅ Utiliser des Fragments et Navigation Component  
✅ Consommer des API REST avec Retrofit  
✅ Appliquer les principes du Material Design  
✅ Concevoir et développer une application complète  

---

## 🛠️ Stack technique

- **Langage** : Java 17
- **IDE** : Android Studio Ladybug | 2024.2.1
- **SDK** : Android 15 (API 35)
- **Min SDK** : Android 7.0 (API 24)
- **Build** : Gradle 8.x
- **Architecture** : MVVM (recommandée pour projets avancés)

### Bibliothèques principales

- **AndroidX** : AppCompat, ConstraintLayout, RecyclerView
- **Material Components** : com.google.android.material
- **Room** : Base de données locale
- **Retrofit** : Appels API REST
- **Glide** : Chargement d'images
- **Navigation Component** : Navigation entre fragments

---

## 📊 Progression recommandée

### Semaines 1-2 : Fondamentaux
- Module 1 : Découverte d'Android
- Module 2 : Installation environnement
- Module 3 : Premier projet
- **TD01** : Installation et HelloWorld

### Semaines 3-4 : Interfaces
- Module 4 : Views et layouts
- **TD02** : Construction d'interfaces
- Mini-projet : Carte de visite

### Semaines 5-6 : Navigation
- Module 5 : Cycle de vie et Intents
- **TD03** : Navigation multi-écrans
- Mini-projet : Application multi-écrans

### Semaines 7-8 : Listes
- Module 6 : RecyclerView
- **TD04** : Listes dynamiques
- Mini-projet : Liste de contacts

### Semaines 9-10 : Données
- Module 7 : Room Database
- **TD05** : Stockage local
- Mini-projet : Application de notes

### Semaines 11-12 : Avancé
- Module 8 : Fragments
- Module 9 : Material Design
- **TD06-TD07** : Fragments et thèmes

### Semaines 13-14 : Synthèse
- Module 10 : Projets complets
- **TD08-TD09** : API et projet complet
- **TD10** : Projet final

---

## 💯 Évaluation

- **TD et exercices** : 30%
- **Mini-projets** : 30%
- **Projet final** : 40%

### Projet final (TD10)

Choix entre 3 projets :
1. Gestionnaire de budget
2. Application de recettes
3. Suivi de lecture

📖 Grille d'évaluation détaillée : [EVALUATION.md](EVALUATION.md)

---

## 📚 Ressources du cours

### Guides essentiels

| Guide | Description | Lien |
|-------|-------------| ---- |
| **Quick Start** | Démarrage express en 15 min | [QUICK_START.md](QUICK_START.md) |
| **Glossaire** | Termes techniques A-Z | [GLOSSAIRE.md](GLOSSAIRE.md) |
| **FAQ** | 50+ questions/réponses | [FAQ.md](FAQ.md) |
| **Raccourcis** | Shortcuts Android Studio | [RACCOURCIS.md](RACCOURCIS.md) |
| **Checklist** | Guide développement complet | [CHECKLIST.md](CHECKLIST.md) |
| **Bonnes pratiques** | Conventions et optimisations | [BONNES_PRATIQUES.md](BONNES_PRATIQUES.md) |
| **Ressources** | Liens externes utiles | [RESSOURCES.md](RESSOURCES.md) |
| **Index** | Navigation rapide | [INDEX.md](INDEX.md) |

### Navigation rapide

📘 [Voir tous les modules](INDEX.md#modules-théoriques)  
📝 [Voir tous les TD](TD-global/README.md)  
🤔 [Consulter la FAQ](FAQ.md)  
✅ [Utiliser la checklist](CHECKLIST.md)  

---

## 🤝 Contribution

Les contributions sont les bienvenues ! Consultez [CONTRIBUTING.md](CONTRIBUTING.md).

### Comment aider

- Signaler des erreurs via [Issues](https://github.com/GUEDDES/android-dev-isitcom-students/issues)
- Proposer des améliorations via Pull Requests
- Partager des ressources externes pertinentes
- Ajouter des exemples de code

---

## 🔗 Ressources externes

- [Documentation Android officielle](https://developer.android.com/)
- [Material Design Guidelines](https://m3.material.io/)
- [Android Codelabs](https://codelabs.developers.google.com/?cat=Android)
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)

📖 Liste complète : [RESSOURCES.md](RESSOURCES.md)

---

## ❓ FAQ express

### Android Studio est très lent, que faire ?
➡️ Augmenter la RAM dans `Help > Edit Custom VM Options` : `-Xmx4096m`

### Mon émulateur ne démarre pas
➡️ Vérifier virtualisation BIOS ou utiliser appareil réel USB

### "Cannot resolve symbol R"
➡️ `Build > Clean Project` puis `Build > Rebuild Project`

### Comment passer des données entre Activities ?
➡️ Utiliser Intent.putExtra() et getIntent().getStringExtra()

### Base de données Room : "Cannot access database on main thread"
➡️ Exécuter opérations dans Thread ou Executor

📖 [Voir toutes les questions](FAQ.md) (50+ Q&R)

---

## 📧 Contact

**Enseignant** : A. GUEDDES  
**Email** : abdelwaheb.gueddes@isitc.u-sousse.tn  
**Institution** : ISITCOM - Université de Sousse

---

## 📜 Licence

Ce projet est sous licence MIT. Voir [LICENSE](LICENSE) pour plus de détails.

---

## 🎓 Statistiques du cours

- **10 modules** théoriques complets
- **10 TD** avec énoncés détaillés
- **30+ exercices** pratiques progressifs
- **15+ mini-projets** de difficulté croissante
- **3 projets finaux** au choix avec grilles d'évaluation
- **100+ pages** de documentation
- **50+ Q&R** dans la FAQ
- **8 guides** complémentaires

---

<p align="center">
  <i>Bon apprentissage et bon code ! 🚀</i>
</p>

<p align="center">
  <img src="https://developer.android.com/static/images/brand/Android_Robot.svg" width="100">
</p>

---

👨‍🏫 **Cours Android** | ISITCOM 2025-2026
