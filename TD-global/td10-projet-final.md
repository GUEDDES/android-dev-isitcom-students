# TD10 : Projet final de synthèse

## 🎯 Objectif

Réaliser un projet Android complet intégrant tous les concepts du semestre.

---

## Choix du sujet

Choisir **un** des projets suivants ou proposer le vôtre (validation enseignant).

---

## Sujet 1 : Application de gestion de budget

### Description

Application pour suivre ses dépenses et revenus.

### Fonctionnalités

1. **Transactions** :
   - Ajouter dépense/revenu (montant, catégorie, date, note).
   - Liste des transactions (RecyclerView).
   - Modifier/supprimer.

2. **Catégories** :
   - Catégories prédéfinies (Alimentation, Transport, Loisirs, etc.).
   - Ajouter catégorie personnalisée.

3. **Statistiques** :
   - Total dépenses/revenus du mois.
   - Répartition par catégorie (graphique).
   - Historique mensuel.

4. **Filtres et recherche** :
   - Par date.
   - Par catégorie.
   - Par montant.

5. **Design** :
   - Bottom Navigation (Accueil, Transactions, Statistiques, Profil).
   - Material Design.
   - Dark Mode.

### Technologies

- Room Database.
- Fragments + Navigation Component.
- MPAndroidChart pour les graphiques.
- Material Components.

---

## Sujet 2 : Réseau social simplifié

### Description

Mini réseau social avec publications, likes, commentaires.

### Fonctionnalités

1. **Publications** :
   - Créer publication (texte + image optionnelle).
   - Feed avec RecyclerView.
   - Liker/Unliker.
   - Commenter.

2. **Profil** :
   - Photo, nom, bio.
   - Liste de ses publications.
   - Modifier profil.

3. **Recherche** :
   - Rechercher utilisateurs.
   - Rechercher publications.

4. **Base de données** :
   - Room avec 3 tables : User, Post, Comment.
   - Relations entre tables.

5. **Design** :
   - Material Design moderne.
   - Animations.
   - Dark Mode.

---

## Sujet 3 : Application de livraison de repas

### Description

Application type "Food Delivery".

### Fonctionnalités

1. **Restaurants** :
   - Liste des restaurants (RecyclerView en grille).
   - Détail restaurant (menu).

2. **Menu** :
   - Liste des plats par catégorie.
   - Détail plat (photo, description, prix).

3. **Panier** :
   - Ajouter/retirer plats.
   - Quantité.
   - Total.

4. **Commande** :
   - Formulaire adresse livraison.
   - Récapitulatif.
   - Historique commandes.

5. **Base de données** :
   - Room : Restaurant, Dish, Order, OrderItem.

---

## Sujet 4 : Application de quiz interactif

### Description

Application de quiz avec score et classement.

### Fonctionnalités

1. **Thèmes** :
   - Plusieurs catégories (Sport, Histoire, Géographie, etc.).

2. **Quiz** :
   - Questions à choix multiples.
   - Timer par question.
   - Score en temps réel.

3. **Résultat** :
   - Score final.
   - Correction détaillée.
   - Partage sur réseaux sociaux.

4. **Classement** :
   - Meilleurs scores.
   - Historique personnel.

5. **Base de données** :
   - Room : Question, Category, Score.
   - Import questions depuis JSON.

---

## Sujet 5 : Application de gestion de bibliothèque personnelle

### Description

Gérer sa collection de livres.

### Fonctionnalités

1. **Livres** :
   - Ajouter livre (titre, auteur, ISBN, année, genre).
   - Scanner code-barre (optionnel).
   - Photo de couverture.

2. **Collection** :
   - Liste des livres.
   - Recherche et filtres.
   - Tri (titre, auteur, date ajout).

3. **Lecture** :
   - Statut (Non lu, En cours, Lu).
   - Note personnelle.
   - Commentaire.

4. **Statistiques** :
   - Nombre de livres.
   - Livres lus cette année.
   - Répartition par genre.

5. **API** (bonus) :
   - Google Books API pour récupérer infos livre.

---

## Critères techniques obligatoires

1. **Architecture** :
   - Au moins 3 Activities ou Fragments.
   - Navigation Component ou Intents.

2. **Base de données** :
   - Room avec au moins 2 tables.
   - CRUD complet.

3. **Interface** :
   - Material Design.
   - RecyclerView.
   - Composants Material (FAB, CardView, etc.).

4. **Fonctionnalités** :
   - Recherche.
   - Filtres ou tri.
   - Dark Mode.

---

## Livrables

1. **Code source** (projet Android Studio complet).
2. **APK** compilé et signé.
3. **README.md** avec :
   - Titre et description.
   - Fonctionnalités implémentées.
   - Technologies utilisées.
   - Captures d'écran.
   - Instructions d'installation.
   - Difficultés rencontrées.
4. **Vidéo de démonstration** (3-5 minutes).
5. **Présentation** (10 slides PowerPoint/PDF).

---

## Présentation orale

- **Durée** : 10 minutes + 5 minutes de questions.
- **Contenu** :
  - Présentation du projet.
  - Démonstration live.
  - Architecture technique.
  - Difficultés et solutions.

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Fonctionnalités complètes | 6 |
| Architecture et code propre | 4 |
| Base de données et CRUD | 3 |
| Interface utilisateur | 3 |
| Qualité du README et documentation | 2 |
| Présentation orale | 2 |

---

## Conseils

- Commencer par les fonctionnalités de base.
- Tester régulièrement.
- Versionner avec Git.
- Ne pas attendre la dernière semaine.
- Demander de l'aide si bloqué.

---

👨‍🏫 **Enseignant** : A. GUEDDES  
📅 **Date limite** : À définir
