# Exercices pratiques - Module 6 : RecyclerView

## Exercice 1 : Liste simple

### Objectif
Afficher une liste de noms.

### Consignes
1. Créer classe `Person` avec attribut `name`
2. Créer layout `item_person.xml` avec un TextView
3. Créer `PersonAdapter` et `PersonViewHolder`
4. Afficher 10 noms dans MainActivity
5. Au clic sur un nom, afficher Toast

### Livrable
Application avec liste cliquable.

---

## Exercice 2 : Liste de contacts

### Objectif
Afficher nom + numéro de téléphone.

### Consignes
1. Classe `Contact` : name, phone
2. Layout item avec 2 TextViews
3. Adapter personnalisé
4. Ajouter 8 contacts fictifs
5. Au clic, lancer Intent pour appeler (ACTION_DIAL)

### Livrable
Liste de contacts avec action d'appel.

---

## Exercice 3 : Liste avec images

### Objectif
Ajouter des images aux éléments.

### Consignes
1. Classe `Product` : name, price, imageResId
2. Layout item : ImageView + 2 TextViews
3. Ajouter 5 produits avec images (drawable)
4. Afficher en liste
5. Au clic, afficher détail dans Toast

### Livrable
Liste avec images.

---

## Exercice 4 : Grille d'éléments

### Objectif
Afficher en grille au lieu de liste.

### Consignes
1. Reprendre exercice 3
2. Remplacer LinearLayoutManager par GridLayoutManager (2 colonnes)
3. Adapter le layout item pour grille

### Livrable
Affichage en grille fonctionnel.

---

## Exercice 5 : Suppression d'éléments

### Objectif
Supprimer un élément au clic long.

### Consignes
1. Reprendre exercice 2 (contacts)
2. Au clic long :
   - Afficher dialogue de confirmation
   - Si confirmé, supprimer de la liste
   - Appeler `notifyItemRemoved(position)`

### Livrable
Liste avec suppression.

---

## Mini-projet : Application To-Do

### Objectif
Créer une liste de tâches avec ajout/suppression.

### Fonctionnalités
1. **Écran principal** :
   - RecyclerView de tâches
   - Chaque item : titre + description
   - FAB pour ajouter tâche
2. **Ajout de tâche** :
   - Dialogue avec 2 EditText (titre, description)
   - Bouton "Ajouter"
   - Ajouter à la liste
3. **Actions** :
   - Clic : afficher détail dans Toast
   - Clic long : supprimer avec confirmation

### Contraintes
- Classe `Task` : id, title, description
- Adapter personnalisé
- Layout item soigné
- Utiliser Material Components (FAB)

### Grille d'évaluation
| Critère | Points |
|---------|--------|
| RecyclerView fonctionnel | 5 |
| Ajout de tâches | 5 |
| Suppression avec confirmation | 4 |
| Interface Material | 3 |
| Code propre | 3 |

**Total** : /20
