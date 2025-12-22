# TD 02 : Interfaces et Layouts

**Durée** : 2 heures  
**Objectif** : Maîtriser les layouts Android et créer des interfaces utilisateur complexes.

---

## Partie 1 : ConstraintLayout (45 min)

### Exercice 1.1 : Formulaire d'inscription

Créer un écran d'inscription avec :

- Titre centré : "Inscription"
- 3 champs `EditText` (nom, email, mot de passe)
- 1 bouton "S'inscrire"
- Utiliser **uniquement ConstraintLayout**

**Contraintes à respecter** :
- Le titre doit être centré horizontalement et positionné en haut.
- Les champs doivent être alignés et espacés également.
- Le bouton doit être centré horizontalement et positionné sous les champs.

**Livrable** : Capture d'écran du Design Editor + fichier XML.

---

## Partie 2 : LinearLayout et poids (30 min)

### Exercice 2.1 : Calculatrice simple

Créer une interface de calculatrice basique :

- 1 `TextView` pour l'affichage (en haut)
- 4 boutons horizontaux : +, -, ×, ÷ (poids égaux)
- Utiliser `LinearLayout` avec `layout_weight`

**Livrable** : Fichier XML du layout.

---

## Partie 3 : Combinaison de layouts (45 min)

### Exercice 3.1 : Écran de profil utilisateur

Créer un écran de profil avec :

- En haut : photo de profil (ImageView circulaire) + nom (LinearLayout horizontal)
- Au centre : informations (email, téléphone, adresse) dans un LinearLayout vertical
- En bas : 2 boutons (Modifier, Déconnexion) dans un LinearLayout horizontal

**Contraintes** :
- Utiliser ScrollView comme layout principal pour gérer le défilement.
- Appliquer des marges et du padding cohérents.

**Livrable** : Projet complet exécutable.

---

## Barème (/20)

| Exercice | Points |
|----------|--------|
| Formulaire d'inscription (ConstraintLayout) | 7 |
| Calculatrice (LinearLayout + weight) | 5 |
| Écran de profil (combinaison layouts) | 8 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
