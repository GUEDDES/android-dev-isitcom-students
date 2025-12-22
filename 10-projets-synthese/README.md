# Module 10 : Projets de synthèse

## 🎯 Objectifs

Ce module regroupe des projets complets permettant de mobiliser l'ensemble des compétences acquises durant le cours.

---

## Projet 1 : Application de gestion de contacts

### Description

Créer une application complète de gestion de contacts avec :

- Liste de contacts (RecyclerView).
- Ajout/modification/suppression de contacts.
- Stockage local avec Room Database.
- Interface Material Design.
- Mode sombre.

### Fonctionnalités attendues

1. **Écran principal** :
   - RecyclerView affichant tous les contacts.
   - FAB pour ajouter un contact.
   - Recherche par nom.
   - Menu pour trier (A-Z, récents).

2. **Écran ajout/modification** :
   - Champs : nom, prénom, téléphone, email, photo.
   - Validation des champs.
   - Bouton "Enregistrer".

3. **Écran détail** :
   - Affichage complet du contact.
   - Boutons : appeler, envoyer SMS, modifier, supprimer.

4. **Base de données** :
   - Entity Contact avec Room.
   - CRUD complet.

### Spécifications techniques

- Architecture : Activity + Fragments + RecyclerView + Room.
- Minimum SDK : 24.
- Navigation Component.
- Material Design 3.

### Critères d'évaluation (/30)

| Critère | Points |
|---------|--------|
| Base de données Room fonctionnelle | 6 |
| CRUD complet | 6 |
| RecyclerView avec adapter | 4 |
| Navigation entre écrans | 4 |
| Interface Material Design | 4 |
| Validation des champs | 3 |
| Gestion des erreurs | 3 |

---

## Projet 2 : Application de to-do list avancée

### Description

Application de gestion de tâches avec catégories, priorités et rappels.

### Fonctionnalités attendues

1. **Écran principal** :
   - RecyclerView avec tâches groupées par catégorie.
   - Filtres : toutes, actives, terminées.
   - Recherche.

2. **Ajout de tâche** :
   - Titre, description, date limite, priorité.
   - Catégorie (travail, personnel, études).
   - CheckBox "Rappel".

3. **Écran détail** :
   - Affichage complet.
   - Modifier statut (terminée/active).
   - Supprimer.

4. **Statistiques** :
   - Fragment affichant : nombre total, terminées, en cours.
   - Graphique par catégorie (optionnel).

### Spécifications techniques

- Room Database avec 2 tables (Task, Category).
- Bottom Navigation (Tâches, Statistiques, Profil).
- Notifications (rappels).

### Critères d'évaluation (/30)

| Critère | Points |
|---------|--------|
| Base de données (2 tables) | 6 |
| CRUD complet | 5 |
| Bottom Navigation | 4 |
| Filtres et recherche | 5 |
| Notifications | 5 |
| Interface soignée | 5 |

---

## Projet 3 : Application de gestion de dépenses

### Description

Suivi des dépenses personnelles avec catégories et statistiques.

### Fonctionnalités attendues

1. **Écran principal** :
   - RecyclerView des dépenses récentes.
   - Solde total en haut.
   - FAB pour ajouter une dépense.

2. **Ajout de dépense** :
   - Montant, catégorie, date, note.
   - Photo du reçu (optionnel).

3. **Écran statistiques** :
   - Total par mois.
   - Répartition par catégorie.
   - Graphique (optionnel).

4. **Catégories** :
   - Alimentation, transport, loisirs, santé, autre.
   - Possibilité d'ajouter des catégories personnalisées.

### Spécifications techniques

- Room avec tables Expense et Category.
- Fragments avec Navigation Component.
- Material Design (CardView, Chips).

### Critères d'évaluation (/30)

| Critère | Points |
|---------|--------|
| Base de données | 6 |
| CRUD | 5 |
| Calculs et statistiques | 6 |
| Interface Material | 5 |
| Gestion des catégories | 4 |
| Code propre et commenté | 4 |

---

## Projet 4 : Application de recettes de cuisine

### Description

Catalogue de recettes avec recherche et favoris.

### Fonctionnalités attendues

1. **Écran principal** :
   - RecyclerView en grille.
   - Chaque carte : photo, titre, temps de préparation.
   - Recherche par nom.

2. **Écran détail** :
   - Photo, titre, ingrédients, étapes.
   - Bouton favori (étoile).
   - Partage de la recette.

3. **Écran favoris** :
   - Liste des recettes favorites.

4. **Ajout de recette** :
   - Formulaire complet.
   - Upload photo depuis galerie.

### Spécifications techniques

- Room Database.
- Bottom Navigation (Recettes, Favoris, Ajouter).
- Intent implicite pour partage.

### Critères d'évaluation (/30)

| Critère | Points |
|---------|--------|
| Base de données | 5 |
| RecyclerView avec images | 6 |
| Navigation | 4 |
| Système de favoris | 5 |
| Partage de recette | 4 |
| Interface attrayante | 6 |

---

## Projet 5 : Application de gestion d'événements

### Description

Créer et gérer des événements (rendez-vous, réunions, anniversaires).

### Fonctionnalités attendues

1. **Écran principal** :
   - CalendarView (optionnel) ou liste par date.
   - Événements à venir.

2. **Ajout d'événement** :
   - Titre, date, heure, lieu, description.
   - Rappel (notification).

3. **Écran détail** :
   - Informations complètes.
   - Modifier/supprimer.

4. **Notifications** :
   - Rappel 1h avant l'événement.

### Spécifications techniques

- Room Database.
- AlarmManager ou WorkManager pour notifications.
- Material Design.

### Critères d'évaluation (/30)

| Critère | Points |
|---------|--------|
| Base de données | 5 |
| CRUD | 5 |
| Gestion des dates | 5 |
| Notifications fonctionnelles | 7 |
| Interface claire | 5 |
| Code structuré | 3 |

---

## Conseils généraux pour les projets

### Architecture recommandée

- Séparer logique métier et interface.
- Utiliser des packages : `models`, `database`, `adapters`, `activities`, `fragments`.

### Gestion des erreurs

- Valider les entrées utilisateur.
- Gérer les cas limites (liste vide, base de données vide).
- Messages d'erreur clairs.

### Interface utilisateur

- Cohérence visuelle.
- Feedback utilisateur (Toast, Snackbar, ProgressBar).
- Accessibilité (content descriptions, tailles de texte).

### Code propre

- Noms de variables explicites.
- Commentaires pour parties complexes.
- Indentation correcte.

---

## Modalités de rendu

1. **Code source** : projet Android Studio complet (zip).
2. **APK** : fichier APK installable.
3. **Documentation** :
   - README avec captures d'écran.
   - Guide d'utilisation.
   - Difficultés rencontrées.
4. **Présentation** : démonstration orale (5-10 min).

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
