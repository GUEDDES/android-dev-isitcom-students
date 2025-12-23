# Exercices pratiques - Module 9 : Material Design

## Exercice 1 : Thème Material 3

### Objectif
Appliquer un thème Material Design 3 à votre application.

### Consignes
1. Ajouter Material Components dans `build.gradle`
2. Modifier `res/values/themes.xml` pour utiliser Material3
3. Définir des couleurs primaires, secondaires, tertiaires
4. Créer une activité simple avec :
   - Toolbar
   - 2 boutons (primaire, secondaire)
   - 2 cartes (Card)
5. Tester en mode clair et sombre

### Livrable
Application avec thème Material complet.

---

## Exercice 2 : Cards et Elevation

### Objectif
Utiliser MaterialCardView avec différentes élévations.

### Consignes
1. Créer 3 cartes avec contenus différents
2. Appliquer des élévations différentes (2dp, 4dp, 8dp)
3. Ajouter des coins arrondis (12dp)
4. Une carte avec image + texte
5. Une carte cliquable avec effet ripple

### Exemple de contenu
- Carte 1 : Profil utilisateur (avatar + nom + email)
- Carte 2 : Actualité (image + titre + description)
- Carte 3 : Statistique (icône + nombre + label)

### Livrable
Ecran avec 3 cartes Material bien stylisées.

---

## Exercice 3 : Floating Action Button (FAB)

### Objectif
Implémenter FAB avec actions multiples.

### Consignes
1. Ajouter un FAB principal en bas à droite
2. Au clic : afficher Toast
3. Changer l'icône du FAB dynamiquement
4. Ajouter un Extended FAB avec texte
5. Implémenter FAB avec Snackbar

### Actions à tester
- FAB normal : ajout d'élément
- Extended FAB : "Ajouter une tâche"
- FAB + Snackbar : confirmation d'action

### Livrable
Activité avec FAB fonctionnels.

---

## Exercice 4 : Bottom Sheet

### Objectif
Créer un Bottom Sheet modal et persistant.

### Consignes

#### Bottom Sheet Modal
1. Bouton qui ouvre un Bottom Sheet
2. Contenu : formulaire simple (nom, email)
3. Boutons Valider/Annuler
4. Fermer au clic sur Annuler

#### Bottom Sheet Persistant
1. Bottom Sheet toujours visible en bas
2. Peut être tiré vers le haut/bas
3. 3 états : collapsed, half-expanded, expanded

### Livrable
2 activités démontrant les 2 types de Bottom Sheet.

---

## Exercice 5 : Navigation Drawer

### Objectif
Implémenter un menu latéral.

### Consignes
1. Ajouter DrawerLayout dans `activity_main.xml`
2. Créer `res/menu/drawer_menu.xml` avec 5 items :
   - Accueil
   - Profil
   - Paramètres
   - Aide
   - Déconnexion
3. Ajouter header avec image + nom
4. Implémenter navigation entre fragments
5. Afficher item sélectionné

### Livrable
Application avec Navigation Drawer fonctionnel.

---

## Exercice 6 : Chips

### Objectif
Utiliser Chips pour filtres et sélections.

### Consignes
1. **ChipGroup avec filtres** : 
   - 4 chips (Tous, Travail, Personnel, Urgent)
   - Sélection unique
   - Afficher un Toast du filtre sélectionné

2. **Chips avec actions** :
   - Chip avec icône de fermeture
   - Possibilité de supprimer le chip

3. **Input Chip** :
   - Saisie de tags
   - Ajout dynamique de chips

### Livrable
Ecran démontrant les 3 types de Chips.

---

## Exercice 7 : Snackbar et Messages

### Objectif
Afficher différents types de messages.

### Consignes
1. **Snackbar simple** : message de confirmation
2. **Snackbar avec action** : "Annuler" une suppression
3. **Snackbar avec couleur** : erreur (rouge), succès (vert)
4. **Durée personnalisée** : SHORT, LONG, INDEFINITE
5. Positionner au-dessus d'un FAB

### Scénarios à tester
- Suppression d'élément avec undo
- Validation de formulaire
- Erreur de connexion

### Livrable
Application démontrant 5 types de Snackbar.

---

## Exercice 8 : Text Fields

### Objectif
Créer un formulaire avec TextInputLayout.

### Consignes
1. **Champ nom** :
   - Icône personne
   - Helper text
   - Validation : non vide

2. **Champ email** :
   - Icône email
   - Validation : format email
   - Message d'erreur personnalisé

3. **Champ mot de passe** :
   - Icône cadenas
   - Bouton afficher/masquer
   - Validation : minimum 6 caractères

4. **Champ description** :
   - Multiligne
   - Compteur de caractères (max 200)

### Livrable
Formulaire complet avec validations.

---

## Exercice 9 : Dialogs Material

### Objectif
Créer différents types de dialogues.

### Consignes

1. **Simple Dialog** : confirmation de suppression
2. **Dialog avec liste** : sélection d'une option
3. **Dialog avec radio buttons** : choix unique
4. **Dialog personnalisé** : formulaire d'ajout
5. **DatePicker Material** : sélection de date

### Livrable
5 boutons ouvrant chaque type de dialogue.

---

## Exercice 10 : Animations et Transitions

### Objectif
Ajouter des animations Material.

### Consignes

1. **Shared Element Transition** :
   - Liste d'images
   - Clic → ouverture détail avec animation

2. **Animations FAB** :
   - Rotation au clic
   - Scale animation

3. **Animations RecyclerView** :
   - Apparition progressive des items
   - Animation d'ajout/suppression

4. **Motion Layout** (optionnel) :
   - Swipe pour révéler action

### Livrable
Application avec animations fluides.

---

## Mini-projet : Application Profil Material

### Objectif
Créer une application de profil utilisateur avec Material Design.

### Fonctionnalités requises

1. **Écran d'accueil** :
   - Toolbar avec menu
   - Image de profil (cercle)
   - Nom et bio
   - 3 statistiques en cartes (Posts, Followers, Following)
   - FAB pour éditer profil

2. **Navigation Drawer** :
   - Header avec cover photo
   - Menu : Profil, Paramètres, Thème, Déconnexion

3. **Écran édition** :
   - TextFields pour nom, bio, email
   - Switch pour notifications
   - Chips pour intérêts
   - Bouton sauvegarder (avec Snackbar)

4. **Écran paramètres** :
   - Liste avec SwitchPreference
   - Sélection thème (Clair/Sombre/Auto)
   - Langue

5. **Bottom Sheet** :
   - Options de partage
   - Copier lien profil
   - Signaler

### Contraintes techniques
- Material Design 3
- Thème dynamique (clair/sombre)
- Fragments avec Navigation Component
- Animations entre écrans
- Code propre et commenté

### Grille d'évaluation

| Critère | Points |
|---------|--------|
| Thème Material 3 correct | 3 |
| Navigation Drawer | 3 |
| Cards bien stylisées | 2 |
| FAB + Snackbar | 2 |
| TextFields avec validation | 3 |
| Bottom Sheet | 2 |
| Thème clair/sombre | 2 |
| Animations | 2 |
| Code propre | 3 |

**Total** : /22

---

## Ressources

### Documentation officielle
- [Material Design 3](https://m3.material.io/)
- [Material Components Android](https://github.com/material-components/material-components-android)

### Outils
- [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/)
- [Material Icons](https://fonts.google.com/icons)

### Exemples de code
- [Material Components Samples](https://github.com/material-components/material-components-android-examples)

---

👨‍🏫 **Module 9 - Material Design** | ISITCOM 2025-2026
