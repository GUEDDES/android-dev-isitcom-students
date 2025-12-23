# Exercices pratiques - Module 4 : Interfaces et layouts

## Exercice 1 : Carte de profil

### Objectif
Créer une carte de profil avec informations personnelles.

### Consignes
1. Créer un nouveau projet
2. Dans `activity_main.xml`, utiliser `ConstraintLayout`
3. Ajouter :
   - `TextView` pour nom (police 24sp, gras)
   - `TextView` pour email
   - `TextView` pour téléphone
   - `Button` "Contact"
4. Centrer verticalement tous les éléments
5. Au clic sur le bouton, afficher un Toast avec l'email

### Livrable
Code Java + XML + capture d'écran.

---

## Exercice 2 : Calculatrice simple

### Objectif
Créer une interface de calculatrice basique.

### Consignes
1. Ajouter deux `EditText` (type `number`)
2. Quatre boutons : `+`, `-`, `*`, `/`
3. Un `TextView` pour afficher le résultat
4. Au clic sur un bouton :
   - Récupérer les deux nombres
   - Effectuer l'opération
   - Afficher le résultat
5. Gérer la division par zéro

### Livrable
Projet complet avec gestion d'erreurs.

---

## Exercice 3 : Formulaire d'inscription

### Objectif
Créer un formulaire avec validation.

### Consignes
1. Champs requis :
   - Nom complet (`EditText`)
   - Email (`EditText`, type `textEmailAddress`)
   - Mot de passe (`EditText`, type `textPassword`)
   - Confirmation mot de passe
   - `CheckBox` "J'accepte les conditions"
   - Bouton "S'inscrire"
2. Validation :
   - Tous les champs remplis
   - Email valide (contient `@`)
   - Mots de passe identiques
   - CheckBox cochée
3. Afficher Toast de succès ou erreur

### Livrable
Application fonctionnelle avec validation.

---

## Exercice 4 : Galerie d'images

### Objectif
Afficher plusieurs images avec description.

### Consignes
1. Créer un `ScrollView`
2. À l'intérieur, un `LinearLayout` vertical
3. Ajouter 5 blocs (image + texte) :
   - `ImageView` (images depuis `drawable`)
   - `TextView` (description)
4. Au clic sur une image, afficher Toast avec le nom

### Livrable
Interface scrollable avec images.

---

## Mini-projet : Écran de connexion complet

### Objectif
Créer un écran de connexion professionnel.

### Fonctionnalités
1. Logo en haut (ImageView)
2. Champ email
3. Champ mot de passe
4. CheckBox "Se souvenir de moi"
5. Bouton "Connexion"
6. TextView "Mot de passe oublié ?"
7. TextView "Créer un compte"

### Contraintes
- Utiliser Material Components
- Couleurs cohérentes
- Centrer les éléments
- Ajouter icônes aux champs

### Livrable
Interface soignée + code fonctionnel.

### Grille d'évaluation
| Critère | Points |
|---------|--------|
| Interface respecte les consignes | 5 |
| Code propre et commenté | 3 |
| Validation fonctionnelle | 5 |
| Design soigné | 4 |
| Gestion des erreurs | 3 |

**Total** : /20
