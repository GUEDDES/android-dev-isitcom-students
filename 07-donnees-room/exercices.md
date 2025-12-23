# Exercices pratiques - Module 7 : Room Database

## Exercice 1 : Base simple

### Objectif
Créer une base Room basique.

### Consignes
1. Créer Entity `Student` : id, name, age
2. Créer DAO avec méthodes CRUD
3. Créer Database
4. Dans MainActivity :
   - Ajouter 3 étudiants
   - Lire et afficher dans Logcat

### Livrable
Code avec base fonctionnelle.

---

## Exercice 2 : CRUD complet

### Objectif
Implémenter toutes les opérations.

### Consignes
1. Entity `Note` : id, title, content, date
2. DAO avec :
   - `insert(Note)`
   - `update(Note)`
   - `delete(Note)`
   - `getAll()`
   - `getById(int id)`
3. Tester toutes les opérations

### Livrable
Application avec CRUD testé.

---

## Exercice 3 : Room + RecyclerView

### Objectif
Afficher les données de la base dans une liste.

### Consignes
1. Reprendre exercice 2
2. Afficher toutes les notes dans RecyclerView
3. FAB pour ajouter note
4. Au clic sur note : modification
5. Clic long : suppression

### Livrable
Application complète Room + RecyclerView.

---

## Exercice 4 : Requêtes avancées

### Objectif
Utiliser des requêtes SQL personnalisées.

### Consignes
1. Entity `Book` : id, title, author, isRead
2. DAO avec requêtes :
   - Tous les livres lus
   - Tous les livres non lus
   - Livres par auteur
   - Nombre total de livres
3. Tester toutes les requêtes

### Livrable
Code avec requêtes fonctionnelles.

---

## Mini-projet : Application de gestion de dépenses

### Objectif
Créer une app de suivi de dépenses.

### Fonctionnalités
1. **Entity Expense** :
   - id, amount, category, description, date
2. **Écran principal** :
   - RecyclerView des dépenses
   - Afficher montant + catégorie + date
   - FAB pour ajouter
3. **Ajout dépense** :
   - Champs : montant, catégorie (spinner), description
   - Sauvegarder en Room
4. **Statistiques** (optionnel) :
   - Total des dépenses
   - Dépenses par catégorie

### Contraintes
- Architecture propre (packages)
- Gestion des dates (timestamp)
- Interface Material
- Validation des champs

### Grille d'évaluation
| Critère | Points |
|---------|--------|
| Room correctement configuré | 4 |
| CRUD fonctionnel | 6 |
| RecyclerView avec données | 4 |
| Ajout/modification | 4 |
| Interface soignée | 2 |

**Total** : /20
