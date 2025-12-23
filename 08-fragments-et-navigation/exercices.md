# Exercices pratiques - Module 8 : Fragments

## Exercice 1 : Fragment statique

### Objectif
Créer et afficher un Fragment.

### Consignes
1. Créer `HomeFragment` avec layout simple
2. Dans MainActivity, afficher le fragment
3. Ajouter TextView dans le fragment

### Livrable
Application avec fragment affiché.

---

## Exercice 2 : Navigation entre Fragments

### Objectif
Naviguer entre plusieurs fragments.

### Consignes
1. Créer 3 fragments (Home, Profile, Settings)
2. MainActivity avec 3 boutons
3. Chaque bouton affiche le fragment correspondant

### Livrable
Navigation manuelle entre fragments.

---

## Exercice 3 : Navigation Component

### Objectif
Utiliser Navigation Component.

### Consignes
1. Configurer Navigation Component
2. Créer graphe de navigation
3. Ajouter 3 fragments
4. Naviguer avec `NavController`

### Livrable
Navigation avec Navigation Component.

---

## Exercice 4 : Bottom Navigation

### Objectif
Implémenter Bottom Navigation.

### Consignes
1. Ajouter BottomNavigationView
2. Créer menu avec 3 items
3. Lier avec Navigation Component
4. Tester navigation entre onglets

### Livrable
Bottom Navigation fonctionnelle.

---

## Mini-projet : Application multi-onglets

### Objectif
Application complète avec fragments.

### Fonctionnalités
1. **Bottom Navigation avec 3 onglets** :
   - Home : liste de produits (RecyclerView + Room)
   - Favoris : produits favoris uniquement
   - Profil : informations utilisateur
2. **Actions** :
   - Ajouter/retirer des favoris
   - Clic sur produit : détail dans nouveau fragment
3. **Navigation** :
   - Utiliser Navigation Component
   - Transitions fluides

### Contraintes
- Minimum 3 fragments
- Bottom Navigation Material
- Room pour stockage
- Architecture propre

### Grille d'évaluation
| Critère | Points |
|---------|--------|
| Fragments créés correctement | 4 |
| Navigation Component | 4 |
| Bottom Navigation | 4 |
| RecyclerView + Room | 5 |
| Interface soignée | 3 |

**Total** : /20
