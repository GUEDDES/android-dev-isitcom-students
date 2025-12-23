# Checklist développement Android

## 🎯 Introduction

Cette checklist vous guide pour développer une application Android professionnelle.

---

## 🛠️ Phase 1 : Planification

### Analyse des besoins

- [ ] Définir les fonctionnalités principales
- [ ] Identifier les utilisateurs cibles
- [ ] Lister les écrans nécessaires
- [ ] Déterminer les données à stocker

### Design

- [ ] Créer des wireframes/maquettes
- [ ] Choisir la palette de couleurs
- [ ] Définir la navigation entre écrans
- [ ] Sélectionner les icônes

---

## 🏗️ Phase 2 : Configuration projet

### Création

- [ ] Créer le projet dans Android Studio
- [ ] Choisir package name unique (tn.isitcom.monapp)
- [ ] Définir minSdk (24 recommandé)
- [ ] Définir targetSdk (35 pour 2025)

### Dépendances

- [ ] Ajouter Material Components
- [ ] Ajouter Navigation Component (si fragments)
- [ ] Ajouter Room (si base de données)
- [ ] Ajouter Retrofit (si API)
- [ ] Ajouter Glide (si images)
- [ ] Synchroniser Gradle

### Structure

- [ ] Créer packages : data, ui, utils
- [ ] Créer sous-packages selon architecture

---

## 📦 Phase 3 : Modèles de données

### Entity (si Room)

- [ ] Créer classes Entity avec @Entity
- [ ] Définir @PrimaryKey
- [ ] Annoter @ColumnInfo si nécessaire

### DAO

- [ ] Créer interface DAO
- [ ] Définir méthodes @Insert, @Update, @Delete
- [ ] Ajouter requêtes @Query

### Database

- [ ] Créer classe Database extends RoomDatabase
- [ ] Implémenter getInstance() (Singleton)
- [ ] Lister entities dans @Database

---

## 🎨 Phase 4 : Interface utilisateur

### Ressources

- [ ] Définir couleurs dans colors.xml
- [ ] Externaliser textes dans strings.xml
- [ ] Ajouter icônes dans drawable/
- [ ] Créer thèmes (clair/sombre)

### Layouts

- [ ] Créer layouts XML pour chaque écran
- [ ] Utiliser ConstraintLayout
- [ ] Définir IDs pour toutes les vues
- [ ] Utiliser dp pour dimensions, sp pour textes

### Navigation

- [ ] Créer nav_graph.xml (si Navigation Component)
- [ ] Définir destinations (fragments)
- [ ] Configurer NavHostFragment
- [ ] Créer menu Bottom Navigation (si besoin)

---

## 💻 Phase 5 : Logique métier

### Activities/Fragments

- [ ] Créer Activities ou Fragments
- [ ] Implémenter onCreate() / onCreateView()
- [ ] Appeler setContentView() ou inflate()
- [ ] Initialiser vues avec findViewById()

### Adapters (si listes)

- [ ] Créer Adapter extends RecyclerView.Adapter
- [ ] Créer ViewHolder
- [ ] Implémenter onCreateViewHolder()
- [ ] Implémenter onBindViewHolder()
- [ ] Implémenter getItemCount()
- [ ] Gérer clics si nécessaire

### ViewModels (recommandé)

- [ ] Créer ViewModel extends ViewModel
- [ ] Définir LiveData pour données observables
- [ ] Créer Repository
- [ ] Observer LiveData dans Fragment/Activity

---

## ⚡ Phase 6 : Fonctionnalités

### CRUD

- [ ] Implémenter Create (ajout)
- [ ] Implémenter Read (lecture/affichage)
- [ ] Implémenter Update (modification)
- [ ] Implémenter Delete (suppression)

### Validations

- [ ] Valider champs formulaire
- [ ] Afficher messages d'erreur
- [ ] Désactiver boutons si nécessaire

### Navigation

- [ ] Tester navigation entre écrans
- [ ] Passer données avec Bundle
- [ ] Gérer bouton retour

---

## 🛡️ Phase 7 : Gestion d'erreurs

- [ ] Try-catch sur opérations critiques
- [ ] Vérifier connexion internet (si API)
- [ ] Gérer cas liste vide
- [ ] Afficher messages utilisateur (Toast/Snackbar)
- [ ] Logger erreurs dans Logcat

---

## 🧪 Phase 8 : Tests

### Tests manuels

- [ ] Tester sur émulateur
- [ ] Tester sur appareil réel
- [ ] Tester rotation écran
- [ ] Tester avec données vides
- [ ] Tester tous les boutons/actions
- [ ] Tester navigation complète

### Tests différentes tailles

- [ ] Tester sur petit écran (4")
- [ ] Tester sur grand écran (6")
- [ ] Tester sur tablette (optionnel)

### Tests cas limites

- [ ] Texte très long
- [ ] Caractères spéciaux
- [ ] Perte de connexion (si API)
- [ ] Base de données pleine

---

## 🎨 Phase 9 : Polish

### Material Design

- [ ] Utiliser Material Components
- [ ] Respecter espacements (8dp, 16dp)
- [ ] Ajouter élévations (CardView, FAB)
- [ ] Définir couleurs cohérentes

### Animations

- [ ] Transitions entre écrans fluides
- [ ] Feedback visuel sur clics
- [ ] Animations RecyclerView (optionnel)

### Dark Mode

- [ ] Créer values-night/
- [ ] Définir couleurs dark mode
- [ ] Tester passage clair/sombre

---

## 📝 Phase 10 : Documentation

- [ ] Commenter code complexe
- [ ] Créer README.md
- [ ] Lister fonctionnalités
- [ ] Ajouter captures d'écran
- [ ] Expliquer architecture

---

## 🚀 Phase 11 : Déploiement

### Préparation

- [ ] Vérifier versionName et versionCode
- [ ] Activer minification (ProGuard)
- [ ] Générer APK signé
- [ ] Tester APK release

### Publication (optionnelle)

- [ ] Créer compte Google Play Developer
- [ ] Préparer assets (icône, screenshots)
- [ ] Rédiger description
- [ ] Publier sur Play Store

---

## ⚠️ Points de vigilance

### Performance

- [ ] Pas d'opération Room sur UI thread
- [ ] Pas de code lourd dans onCreate()
- [ ] Images optimisées (taille raisonnable)
- [ ] RecyclerView plutôt que ListView

### Sécurité

- [ ] Clés API non hardcodées
- [ ] Permissions justifiées
- [ ] Données sensibles chiffrées

### UX

- [ ] Messages clairs et en français
- [ ] Feedback immédiat sur actions
- [ ] Pas de blocage UI
- [ ] Boutons suffisamment grands (48dp min)

---

## 📊 Grille d'auto-évaluation

| Critère | ✅ |
|---------|----|
| Application se lance sans crash | |
| Toutes les fonctionnalités marchent | |
| Interface soignée | |
| Pas d'erreurs Logcat | |
| Rotation écran OK | |
| Code commenté et lisible | |
| Architecture propre | |
| README complet | |

---

👨‍🏫 **Checklist Développement** | ISITCOM 2025-2026
