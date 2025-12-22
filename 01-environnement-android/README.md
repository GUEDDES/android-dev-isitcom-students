# Module 1 : Environnement Android

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Comprendre ce qu'est une application mobile et ses différents types
- Expliquer l'architecture de la plateforme Android
- Identifier les versions d'Android et leur importance
- Distinguer les différences entre applications natives, hybrides et web

## 📝 Contenu du module

### 1. Applications Mobiles

Une **application mobile** est un programme conçu pour fonctionner sur des appareils portables comme les smartphones, tablettes et montres connectées.

#### Systèmes d'exploitation mobiles (2025)

- **Android** : 72.2% du marché mondial
- **iOS** : 27.1% du marché
- **HarmonyOS** : 0.4% (principalement en Chine)
- **Autres** : < 0.3%

#### Canaux de distribution

Les applications sont distribuées via des **boutiques d'applications** :
- Google Play Store (Android)
- Apple App Store (iOS)
- Huawei AppGallery (HarmonyOS)

### 2. Types d'Applications

#### 🟢 Applications Natives

**Définition** : Développées spécifiquement pour une plateforme.

**Technologies** :
- Android : Java/Kotlin + Android SDK
- iOS : Swift/Objective-C + iOS SDK

**Avantages** :
- Performances maximales
- Accès complet aux fonctionnalités du téléphone (caméra, GPS, capteurs)
- Expérience utilisateur optimale
- Interface fluide et réactive

**Inconvénients** :
- Coût élevé (2 équipes distinctes)
- Maintenance complexe
- Temps de développement doublé

**Exemples** : Instagram, Spotify, Snapchat

---

#### 🟡 Applications Hybrides

**Définition** : Code unique fonctionnant sur plusieurs plateformes.

**Technologies** :
- Flutter (Google)
- React Native (Meta)
- Ionic (Angular-based)

**Avantages** :
- Code partagé entre Android et iOS
- Coût modéré (1 seule équipe)
- Maintenance simplifiée
- Développement plus rapide

**Inconvénients** :
- Performances légèrement inférieures (-10 à -20%)
- Taille de l'application plus importante
- Dépendance aux frameworks tiers
- Nouvelles APIs parfois en retard

**Exemples** : Alibaba, eBay, BMW

---

#### 🔵 Applications Web (PWA)

**Définition** : Sites web accessibles via navigateur, installables.

**Technologies** :
- HTML5, CSS3, JavaScript
- Frameworks : React, Angular, Vue.js

**Avantages** :
- Coût très faible
- Mises à jour instantanées
- Pas d'installation via store
- SEO optimisé

**Inconvénients** :
- Performances limitées
- Accès restreint aux APIs natives
- Fonctionnement hors-ligne partiel
- Moins d'intégration système

**Exemples** : Twitter Lite, Pinterest, Forbes

### 3. Guide de Choix

| Critère | Natif | Hybride | PWA |
|---------|-------|---------|-----|
| **Performances** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Coût** | €€€€€ | €€€ | € |
| **Temps dev** | Long | Moyen | Court |
| **UX** | Excellence | Très bon | Bon |
| **Accès matériel** | Complet | Élevé | Limité |

**Quand choisir Native ?**
- Applications critiques (performances)
- Jeux 3D
- Applications complexes
- Budget conséquent

**Quand choisir Hybride ?**
- MVP rapide
- Applications business standard
- Budget modéré
- Équipe unique

**Quand choisir PWA ?**
- Contenu informatif
- SEO important
- Budget limité
- Mises à jour fréquentes

### 4. Plateforme Android

#### Histoire

- **2003** : Création par Andy Rubin
- **2005** : Acquisition par Google
- **2008** : Première version publique (Android 1.0)
- **2025** : Android 16 en développement

#### Caractéristiques

- **Open Source** : Code accessible (AOSP)
- **Noyau Linux** : Base solide et sécurisée
- **Multi-constructeurs** : Samsung, Xiaomi, Oppo, etc.
- **Écosystème riche** : 3.8M d'applications sur Google Play

#### Versions et APIs

Chaque version d'Android possède un **numéro d'API** :

| Version | API | Nom | Année | Nouveautés clés |
|---------|-----|-----|------|------------------|
| Android 7.0 | 24 | Nougat | 2016 | Multi-window |
| Android 10 | 29 | Q | 2019 | Dark theme, Bubbles |
| Android 12 | 31 | S | 2021 | Material You |
| Android 14 | 34 | U | 2023 | Gestes prédictifs |
| Android 15 | 35 | V | 2024 | Private Space |
| Android 16 | 36 | - | 2025 | En développement |

**Pourquoi c'est important ?**
- Chaque API ajoute de nouvelles fonctionnalités
- Assure la compatibilité ascendante
- Définit les fonctions disponibles

**Recommandation 2025** :
- **minSdk** : API 24 (95% de couverture)
- **targetSdk** : API 35 (dernière stable)

### 5. Architecture Android

Android est structuré en **couches** :

```
┌─────────────────────────┐
│   Applications          │  <- Instagram, Chrome, etc.
├─────────────────────────┤
│   Framework Java        │  <- Android SDK
├─────────────────────────┤
│   Bibliothèques C/C++    │  <- OpenGL, SQLite
├─────────────────────────┤
│   Runtime Android (ART) │  <- Machine virtuelle
├─────────────────────────┤
│   Noyau Linux           │  <- Drivers, sécurité
└─────────────────────────┘
```

## 📊 Tableau Récapitulatif

| Aspect | Détails |
|--------|----------|
| **Part de marché** | 72.2% mondial (2025) |
| **Appareils actifs** | 2.5 milliards |
| **Applications** | 3.8M sur Play Store |
| **Langage principal** | Java / Kotlin |
| **Open Source** | Oui (AOSP) |
| **Dernier API** | 35 (Android 15) |

## 🎯 Quiz de Validation

Testez vos connaissances ! [Accéder au quiz](./quiz.md)

## 🔗 Ressources

- [Documentation officielle Android](https://developer.android.com)
- [Android Platform Architecture](https://developer.android.com/guide/platform)
- [Distribution Dashboard](https://developer.android.com/about/dashboards)

## ➡️ Module suivant

[Module 2 : SDK et Outils](../02-sdk-et-outils/README.md)

---

👨‍🏫 **Enseignant** : A. GUEDDES | ISITCom 2025-2026