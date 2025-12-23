# Exercices pratiques - Module 5 : Cycle de vie et Intents

## Exercice 1 : Logger le cycle de vie

### Objectif
Comprendre l'ordre des callbacks.

### Consignes
1. Créer une Activity
2. Surcharger tous les callbacks :
   - `onCreate()`
   - `onStart()`
   - `onResume()`
   - `onPause()`
   - `onStop()`
   - `onDestroy()`
3. Ajouter `Log.d("Lifecycle", "Nom du callback")`
4. Observer Logcat lors de :
   - Lancement app
   - Appui sur Home
   - Retour à l'app
   - Rotation écran
   - Fermeture app

### Livrable
Code + copie des logs pour chaque scénario.

---

## Exercice 2 : Navigation basique

### Objectif
Naviguer entre deux écrans.

### Consignes
1. Créer `MainActivity` et `SecondActivity`
2. Dans MainActivity :
   - Bouton "Aller à l'écran 2"
   - Au clic, lancer SecondActivity
3. Dans SecondActivity :
   - Afficher "Vous êtes sur l'écran 2"
   - Bouton "Retour" qui appelle `finish()`

### Livrable
Projet avec navigation fonctionnelle.

---

## Exercice 3 : Passage de données

### Objectif
Envoyer des données entre Activities.

### Consignes
1. MainActivity :
   - EditText pour saisir nom
   - Bouton "Saluer"
2. GreetingActivity :
   - Récupérer le nom depuis Intent
   - Afficher "Bonjour [nom] !"
   - Bouton retour

### Livrable
Application avec passage de données.

---

## Exercice 4 : Intent implicite

### Objectif
Utiliser les actions système.

### Consignes
Créer une Activity avec 4 boutons :
1. **Ouvrir site web** : `ACTION_VIEW` vers https://isitcom.rnu.tn
2. **Appeler** : `ACTION_DIAL` vers +21612345678
3. **Envoyer email** : `ACTION_SENDTO` vers contact@isitcom.tn
4. **Partager texte** : `ACTION_SEND` avec texte "Cours Android ISITCOM"

### Livrable
Application avec 4 intents implicites fonctionnels.

---

## Exercice 5 : Activity Result API

### Objectif
Récupérer un résultat d'une Activity.

### Consignes
1. MainActivity :
   - Afficher une couleur de fond
   - Bouton "Changer couleur"
   - Lance ColorPickerActivity
2. ColorPickerActivity :
   - 3 boutons : Rouge, Vert, Bleu
   - Au clic, retourner la couleur choisie
3. MainActivity :
   - Changer le fond avec la couleur reçue

### Livrable
Application avec Activity Result API.

---

## Mini-projet : Application de profil

### Objectif
Créer une app avec saisie et affichage de profil.

### Fonctionnalités
1. **InputActivity** :
   - Saisir nom, prénom, âge, ville
   - Bouton "Valider"
2. **ProfileActivity** :
   - Afficher toutes les infos
   - Bouton "Modifier"
   - Au clic, retourner à InputActivity avec données
3. **InputActivity** :
   - Si données reçues, pré-remplir les champs

### Contraintes
- Utiliser Activity Result API
- Valider que tous les champs sont remplis
- Interface Material Design

### Grille d'évaluation
| Critère | Points |
|---------|--------|
| Navigation fonctionnelle | 4 |
| Passage de données correct | 4 |
| Activity Result API | 4 |
| Validation champs | 3 |
| Interface soignée | 3 |
| Code propre | 2 |

**Total** : /20
