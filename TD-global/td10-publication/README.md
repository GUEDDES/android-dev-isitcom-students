# TD10 : Projet Final - Application complète

## 🎯 Objectifs

Ce TD final synthétise tous les modules du cours :
- Architecture propre (MVVM recommandée)
- Base de données locale (Room)
- Consommation d'API (Retrofit)
- Navigation fluide (Fragments + Bottom Navigation)
- Interface Material Design

---

## 📋 Choix du projet

Choisir **UN** projet parmi les trois proposés :

### Option 1 : Application de gestion de budget
### Option 2 : Application de recettes de cuisine
### Option 3 : Application de suivi de lecture

---

## Projet 1 : Gestionnaire de Budget

### Description

Application permettant de suivre ses dépenses et revenus.

### Fonctionnalités obligatoires

1. **Écran Transactions** :
   - Liste des transactions (RecyclerView).
   - Filtrer par type (dépense/revenu).
   - Ajouter transaction (FAB).
   - Supprimer transaction (swipe ou clic long).

2. **Écran Statistiques** :
   - Solde actuel.
   - Total dépenses du mois.
   - Total revenus du mois.
   - Graphique simple (optionnel : MPAndroidChart).

3. **Écran Catégories** :
   - Liste des catégories (Alimentation, Transport, Loisirs...).
   - Modifier/ajouter catégories.

4. **Stockage** :
   - Room Database pour transactions et catégories.

5. **Navigation** :
   - Bottom Navigation (3 onglets).

### Modèle de données

**Transaction** :
- id (auto)
- montant (double)
- type (DEPENSE / REVENU)
- catégorie (String)
- description (String)
- date (long timestamp)

**Catégorie** :
- id (auto)
- nom (String)
- icône (String)

### Bonus

- Export CSV des transactions.
- Dark mode.
- Notifications rappel saisie quotidienne.

---

## Projet 2 : Application de Recettes

### Description

Application pour découvrir, sauvegarder et créer des recettes de cuisine.

### Fonctionnalités obligatoires

1. **Écran Accueil** :
   - RecyclerView de recettes.
   - Recherche par nom.
   - Filtrer par catégorie (Entrée, Plat, Dessert).

2. **Détail Recette** :
   - Afficher image, titre, ingrédients, étapes.
   - Bouton "Favori" (sauvegarder en local).

3. **Écran Favoris** :
   - Liste des recettes sauvegardées (Room).

4. **Ajouter Recette** :
   - Formulaire (titre, ingrédients, étapes, image optionnelle).
   - Sauvegarder en Room.

5. **API externe** (optionnelle) :
   - Utiliser TheMealDB API : https://www.themealdb.com/api.php
   - Afficher recettes populaires.

### Modèle de données

**Recipe** :
- id (auto)
- titre (String)
- catégorie (String)
- ingrédients (String)
- instructions (String)
- imageUrl (String)
- isFavorite (boolean)

### Bonus

- Timer de cuisson.
- Partage de recette (Intent.ACTION_SEND).
- Mode offline complet.

---

## Projet 3 : Suivi de Lecture

### Description

Application pour gérer sa bibliothèque personnelle et suivre ses lectures.

### Fonctionnalités obligatoires

1. **Écran Bibliothèque** :
   - Liste des livres (RecyclerView).
   - Statut : À lire, En cours, Terminé.
   - Recherche par titre/auteur.

2. **Détail Livre** :
   - Titre, auteur, nombre de pages, statut.
   - Notes personnelles.
   - Note sur 5 étoiles.
   - Bouton "Marquer comme lu".

3. **Ajouter Livre** :
   - Formulaire manuel.
   - Optionnel : Scanner code-barre (Google Books API).

4. **Statistiques** :
   - Nombre de livres lus cette année.
   - Pages lues au total.
   - Temps de lecture estimé.

5. **Stockage** :
   - Room Database pour livres.

### Modèle de données

**Book** :
- id (auto)
- titre (String)
- auteur (String)
- nbPages (int)
- statut (A_LIRE, EN_COURS, TERMINE)
- note (int, 0-5)
- notesPerso (String)
- dateAjout (long)
- dateTermine (long, nullable)

### Bonus

- Objectif annuel (nombre de livres).
- Widget home screen avec livre en cours.
- Synchronisation cloud (Firebase).

---

## Contraintes techniques

### Obligatoire

- Minimum SDK : 24 (Android 7.0)
- Target SDK : 35 (Android 15)
- Langage : Java
- Architecture : au moins 3 packages (ui, data, model)
- Room pour stockage local
- RecyclerView pour listes
- Bottom Navigation (si multi-onglets)

### Optionnel mais valorisé

- MVVM avec ViewModel et LiveData
- Repository pattern
- API externe (Retrofit)
- Tests unitaires (au moins 2)
- Animations (transitions, RecyclerView)

---

## Livrables

1. **Code source** :
   - Projet Android Studio complet.
   - README.md expliquant fonctionnalités.

2. **APK compilé** :
   - Fichier .apk installable.

3. **Documentation** (PDF ou Markdown) :
   - Captures d'écran des écrans principaux.
   - Diagramme de classes simplifié.
   - Choix techniques justifiés.

4. **Vidéo démo** (optionnelle, 2-3 min) :
   - Démonstration des fonctionnalités.

---

## Grille d'évaluation

| Critère | Points |
|--------|--------|
| **Fonctionnel** | |
| Toutes les fonctionnalités obligatoires | 6 |
| Navigation fluide | 2 |
| Pas de crash | 2 |
| **Technique** | |
| Room correctement utilisé | 3 |
| Architecture propre (packages, séparation) | 2 |
| Code lisible (noms, indentation) | 2 |
| **Interface** | |
| Material Design respecté | 2 |
| UX cohérente | 1 |
| **Documentation** | |
| README clair | 1 |
| Captures d'écran | 1 |
| **Bonus** (max 3 points) | |
| Fonctionnalités bonus | +1 à +3 |

**Total** : /20 (+ bonus)

---

## Conseils

1. **Planifier** : Faire un schéma des écrans et des relations de données.
2. **Itérer** : Commencer par une version minimale fonctionnelle, puis ajouter features.
3. **Tester régulièrement** : Sur émulateur ET appareil réel.
4. **Versionner** : Utiliser Git (commits réguliers).
5. **Demander de l'aide** : Ne pas bloquer trop longtemps sur un problème.

---

## Ressources

- [Material Design Guidelines](https://m3.material.io/)
- [Android Developer Guide](https://developer.android.com/guide)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Retrofit Guide](https://square.github.io/retrofit/)
- [MVVM Architecture](https://developer.android.com/topic/architecture)

---

## Échéances

- **Rendu intermédiaire** (optionnel) : Semaine 12
- **Rendu final** : Dernière semaine du semestre
- **Soutenance** : 10 minutes de présentation + 5 minutes de questions

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026

**Bon courage ! 🚀**
