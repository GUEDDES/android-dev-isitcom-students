# Module 10 : Projets de synthèse

## 🎯 Objectifs du module

Ce module propose des projets complets pour mettre en pratique tous les concepts vus dans le cours : [file:2]

- Activities, Fragments, Navigation
- RecyclerView et Adapters
- Room Database (CRUD)
- Material Design
- Intents et communication entre écrans

---

## Projet 1 : Application de gestion de tâches (To-Do App)

### Fonctionnalités

**Écran principal :**
- Liste des tâches (RecyclerView + CardView)
- Affichage : titre, description, date, statut (complétée ou non)
- FAB pour ajouter une nouvelle tâche
- Possibilité de filtrer (toutes, en cours, terminées)

**Écran ajout/modification :**
- Champs : titre, description, date (DatePicker)
- Boutons : Enregistrer, Annuler

**Base de données :**
- Entity Task (id, title, description, date, completed)
- DAO avec CRUD complet
- Room Database

**Interactions :**
- Clic sur tâche → écran de modification
- Clic long → suppression avec confirmation (Dialog)
- Checkbox dans la liste pour marquer comme terminée
- Snackbar après ajout/suppression

### Architecture technique

```
MainActivity (RecyclerView)
  ↓
TaskAdapter
  ↓
AppDatabase (Room)
  ↓
TaskDao → Task Entity
```

### Critères d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Room Database correctement implémentée | 4 |
| CRUD fonctionnel (Create, Read, Update, Delete) | 5 |
| RecyclerView avec CardView | 3 |
| Navigation entre écrans | 3 |
| Material Design appliqué | 2 |
| Gestion des clics et interactions | 2 |
| Code propre et commenté | 1 |

---

## Projet 2 : Application de contacts

### Fonctionnalités

**Écran principal :**
- Liste des contacts (nom, téléphone, photo)
- Barre de recherche pour filtrer
- FAB pour ajouter un contact

**Écran détail :**
- Affichage complet du contact
- Boutons : Appeler (Intent implicite), SMS, Email
- Bouton Modifier

**Écran ajout/modification :**
- Champs : nom, prénom, téléphone, email, adresse
- Photo (optionnel)
- Validation des champs

**Base de données :**
- Entity Contact (id, firstName, lastName, phone, email, address)

**Fonctionnalités avancées :**
- Suppression avec Swipe ou clic long
- Tri alphabétique
- Export de la liste (Intent SEND)

### Critères d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Room Database et CRUD | 4 |
| RecyclerView avec recherche | 3 |
| Écran détail avec Intents implicites | 4 |
| Formulaire de saisie validé | 3 |
| Navigation fluide | 2 |
| Material Design | 2 |
| Fonctionnalités bonus (swipe, tri) | 2 |

---

## Projet 3 : Application de notes (Note App)

### Fonctionnalités

**Écran principal avec Navigation :**
- Bottom Navigation : Toutes, Favoris, Archivées
- Fragment pour chaque catégorie
- RecyclerView avec CardView
- Menu avec option de recherche

**Écran note :**
- Titre et contenu (EditText multiligne)
- Date de création/modification automatique
- Bouton favori (étoile)
- Menu : Archiver, Supprimer, Partager

**Base de données :**
- Entity Note (id, title, content, date, isFavorite, isArchived)

**Fonctionnalités avancées :**
- Recherche dans les notes
- Catégories/tags
- Dark Mode

### Critères d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Fragments et Bottom Navigation | 4 |
| Room Database avec catégories | 4 |
| Recherche fonctionnelle | 3 |
| Partage de notes (Intent) | 2 |
| Interface Material Design | 3 |
| Dark Mode | 2 |
| Code structuré | 2 |

---

## Projet 4 : Application e-commerce simplifiée

### Fonctionnalités

**Architecture 3 écrans :**

1. **Liste produits :**
   - RecyclerView avec image, nom, prix
   - Bouton "Ajouter au panier"

2. **Détail produit :**
   - Image, description complète, prix
   - Quantité (+ / -)
   - Bouton "Ajouter au panier"

3. **Panier :**
   - Liste des produits ajoutés
   - Quantité modifiable
   - Total calculé
   - Bouton "Valider la commande"

**Base de données :**
- Entity Product (id, name, description, price, imageRes)
- Entity CartItem (id, productId, quantity)

**Fonctionnalités :**
- Badge sur icône panier (nombre d'articles)
- Snackbar lors de l'ajout
- Dialog de confirmation de commande

### Critères d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Room avec 2 entities liées | 5 |
| Navigation produits → détail → panier | 4 |
| Calcul du total | 2 |
| RecyclerView avec images | 3 |
| Gestion des quantités | 3 |
| Material Design | 2 |
| Interactions fluides | 1 |

---

## Projet 5 : Application météo (avec API fictive)

### Fonctionnalités

**Écran principal :**
- Ville sélectionnée
- Température, description, icône
- Prévisions sur 5 jours (RecyclerView horizontale)

**Écran recherche ville :**
- Champ de recherche
- Liste de villes favorites (Room)

**Simulation API :**
- Créer une classe WeatherService qui retourne des données fictives
- Utiliser des données pré-remplies

**Fonctionnalités :**
- Sauvegarder villes favorites
- Rafraîchir les données (SwipeRefreshLayout)
- Partager la météo

### Critères d'évaluation (/20)

| Critère | Points |
|---------|--------|
| Room pour villes favorites | 3 |
| RecyclerView prévisions | 3 |
| Service de simulation | 4 |
| Recherche de ville | 3 |
| SwipeRefreshLayout | 2 |
| Partage (Intent) | 2 |
| Interface Material | 3 |

---

## Conseils de réalisation

### Phase 1 : Planification (1 heure)
- Dessiner les écrans (wireframes)
- Lister les entités Room
- Définir la navigation

### Phase 2 : Développement (8-10 heures)
- Créer la base Room (Entity, DAO, Database)
- Créer les layouts XML
- Développer les Activities/Fragments
- Implémenter les RecyclerView
- Gérer la navigation
- Ajouter Material Design

### Phase 3 : Tests et finitions (2 heures)
- Tester toutes les fonctionnalités
- Gérer les cas d'erreur
- Améliorer l'UI
- Documenter le code

---

## Grille d'évaluation générique

| Aspect | Excellent (5) | Bien (3-4) | Moyen (2) | Insuffisant (0-1) |
|--------|---------------|------------|-----------|-------------------|
| **Architecture** | Code structuré, patterns respectés | Quelques améliorations possibles | Structure confuse | Pas de structure |
| **Fonctionnalités** | Toutes implémentées et testées | Principales fonctionnent | Incomplètes | Ne fonctionne pas |
| **UI/UX** | Material Design, fluide, cohérent | Globalement correct | Basique | Brouillon |
| **Base de données** | CRUD complet, relations gérées | CRUD fonctionnel | Incomplet | Non fonctionnel |
| **Code** | Propre, commenté, lisible | Acceptable | Peu lisible | Illisible |

---

## Ressources pour les projets

- **Icons** : <https://fonts.google.com/icons>
- **Images** : <https://unsplash.com> (libres de droits)
- **Mockups** : <https://www.figma.com>
- **Documentation** : <https://developer.android.com>

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
