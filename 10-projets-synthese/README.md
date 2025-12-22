# Module 10 : Projets de synthèse

## 🎯 Objectifs

Mettre en pratique tous les concepts vus dans les modules précédents à travers des projets complets.

---

## Projet 1 : Application de gestion de tâches (To-Do Advanced)

### Description

Créer une application complète de gestion de tâches avec toutes les fonctionnalités modernes.

### Fonctionnalités requises

1. **Écran principal** :
   - RecyclerView avec CardView pour afficher les tâches.
   - FAB pour ajouter une nouvelle tâche.
   - Swipe pour supprimer une tâche.
   - Filtres : toutes, complétées, en cours.

2. **Ajout/Modification de tâche** :
   - Titre (obligatoire).
   - Description.
   - Date d'échéance (DatePicker).
   - Priorité (haute, moyenne, basse).
   - Validation des champs.

3. **Détail de tâche** :
   - Affichage complet des informations.
   - Bouton "Marquer comme terminée".
   - Bouton "Modifier".
   - Bouton "Supprimer" avec confirmation.

4. **Base de données** :
   - Room avec Entity, DAO, Database.
   - CRUD complet.

5. **Navigation** :
   - Fragments avec Navigation Component.
   - Bottom Navigation (Tâches, Statistiques, Paramètres).

6. **Design** :
   - Material Design 3.
   - Dark Mode.
   - Thème personnalisé.

### Barème (/20)

| Fonctionnalité | Points |
|---------------|--------|
| RecyclerView + CardView | 2 |
| Room Database fonctionnelle | 4 |
| CRUD complet | 3 |
| Navigation Component | 2 |
| Validation formulaire | 2 |
| Filtres fonctionnels | 2 |
| Material Design appliqué | 2 |
| Dark Mode | 1 |
| Code propre et commenté | 2 |

---

## Projet 2 : Application de contacts

### Description

Créer une application de gestion de contacts personnelle.

### Fonctionnalités requises

1. **Liste de contacts** :
   - RecyclerView avec photo, nom, téléphone.
   - Barre de recherche.
   - Tri alphabétique.

2. **Fiche contact** :
   - Photo (sélection depuis galerie ou caméra).
   - Nom, prénom.
   - Téléphone, email.
   - Adresse.
   - Notes.

3. **Actions** :
   - Appeler (Intent implicite).
   - Envoyer SMS.
   - Envoyer email.
   - Partager le contact.

4. **Favoris** :
   - Marquer un contact comme favori.
   - Onglet séparé pour les favoris.

5. **Base de données Room** :
   - Stockage persistant.
   - Recherche dans la base.

### Barème (/20)

| Fonctionnalité | Points |
|---------------|--------|
| Liste avec RecyclerView | 2 |
| Room Database | 4 |
| Ajout/Modification/Suppression | 3 |
| Recherche fonctionnelle | 2 |
| Intents (appel, SMS, email) | 3 |
| Gestion favoris | 2 |
| Interface Material | 2 |
| Photos (bonus) | +2 |
| Code organisé | 2 |

---

## Projet 3 : Application de notes

### Description

Créer une application de prise de notes type Google Keep.

### Fonctionnalités requises

1. **Liste de notes** :
   - RecyclerView en grille.
   - Couleur par note.
   - Aperçu du contenu.

2. **Création/Édition** :
   - Titre.
   - Contenu (multiligne).
   - Couleur de fond.
   - Tags/Labels.

3. **Recherche** :
   - Recherche dans titre et contenu.
   - Filtre par tag.

4. **Organisation** :
   - Archives.
   - Corbeille avec suppression définitive.
   - Épingler des notes.

5. **Partage** :
   - Partager le texte via Intent.

### Barème (/20)

| Fonctionnalité | Points |
|---------------|--------|
| RecyclerView en grille | 2 |
| Room Database | 4 |
| Création/Édition | 3 |
| Couleurs personnalisées | 2 |
| Recherche | 2 |
| Archives/Corbeille | 2 |
| Partage | 1 |
| Tags (bonus) | +2 |
| Interface soignée | 2 |
| Code propre | 2 |

---

## Projet 4 : Application de quiz

### Description

Créer une application de quiz interactif.

### Fonctionnalités requises

1. **Écran d'accueil** :
   - Liste des catégories.
   - Statistiques globales.

2. **Quiz** :
   - Questions à choix multiples.
   - Timer par question.
   - Progression visuelle.
   - Feedback immédiat.

3. **Résultats** :
   - Score final.
   - Réponses correctes/incorrectes.
   - Partage du score.

4. **Base de données** :
   - Stockage des questions.
   - Historique des scores.

5. **Navigation** :
   - Fragments pour chaque écran.

### Barème (/20)

| Fonctionnalité | Points |
|---------------|--------|
| Liste catégories | 2 |
| Affichage questions | 3 |
| Timer fonctionnel | 2 |
| Calcul du score | 2 |
| Room pour questions | 3 |
| Historique des scores | 2 |
| Navigation fluide | 2 |
| Feedback visuel | 2 |
| Code structuré | 2 |

---

## Consignes générales pour tous les projets

### Livrables attendus

1. **Code source** :
   - Projet Android Studio complet.
   - Code commenté et indenté.
   - Noms de variables explicites.

2. **Documentation** :
   - README.md avec :
     - Description de l'application.
     - Fonctionnalités implémentées.
     - Technologies utilisées.
     - Instructions d'installation.
     - Captures d'écran.

3. **APK** :
   - Fichier APK de l'application compilée.

### Critères d'évaluation transversaux

- **Fonctionnalité** (60%) : respect du cahier des charges.
- **Design** (20%) : ergonomie, Material Design, cohérence.
- **Code** (20%) : organisation, lisibilité, bonnes pratiques.

### Bonus possibles

- Animations.
- Fonctionnalités supplémentaires innovantes.
- Tests unitaires.
- Internationalisation (multilingue).

---

## Planning suggéré

| Phase | Durée | Tâches |
|-------|-------|--------|
| Semaine 1 | 3h | Conception, maquettes, base de données |
| Semaine 2 | 3h | Interface principale, navigation |
| Semaine 3 | 3h | Fonctionnalités CRUD |
| Semaine 4 | 3h | Finalisation, tests, documentation |

---

## Ressources utiles

- Icônes : <https://fonts.google.com/icons>
- Palettes de couleurs : <https://materialui.co/colors>
- Maquettes : Figma, Adobe XD
- Inspiration : Google Play Store

---

## Soutenance

### Déroulement (15 min)

1. **Démonstration** (8 min) :
   - Présentation des fonctionnalités.
   - Scénarios d'usage.

2. **Code** (4 min) :
   - Architecture du projet.
   - Points techniques intéressants.

3. **Questions** (3 min) :
   - Choix techniques.
   - Difficultés rencontrées.
   - Améliorations possibles.

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
