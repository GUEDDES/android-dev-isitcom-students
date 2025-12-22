# TD 03 : Activities, Intents et Navigation

**Durée** : 2 heures  
**Objectif** : Maîtriser la navigation entre écrans et le passage de données.

---

## Partie 1 : Navigation simple (30 min)

### Exercice 1.1 : Application 3 écrans

Créer une application avec 3 Activities :

1. **MainActivity** : Menu avec 2 boutons (Profil, Paramètres)
2. **ProfileActivity** : Affiche "Page Profil"
3. **SettingsActivity** : Affiche "Page Paramètres"

Chaque écran doit avoir un bouton "Retour" qui ferme l'Activity (`finish()`).

**Livrable** : Projet fonctionnel.

---

## Partie 2 : Passage de données (45 min)

### Exercice 2.1 : Formulaire avec affichage

Créer 2 écrans :

1. **Écran 1 (Saisie)** :
   - Champs : Nom, Prénom, Âge
   - Bouton "Valider"

2. **Écran 2 (Affichage)** :
   - Afficher les données reçues sous forme : "Bonjour [Prénom] [Nom], vous avez [Âge] ans."

**Contraintes** :
- Valider que les champs ne sont pas vides avant de naviguer.
- Afficher un Toast d'erreur si un champ est vide.

**Livrable** : Code source des 2 Activities.

---

## Partie 3 : Activity Result API (45 min)

### Exercice 3.1 : Sélecteur de couleur

Créer 2 écrans :

1. **MainActivity** :
   - Bouton "Choisir une couleur"
   - L'arrière-plan change selon la couleur choisie

2. **ColorPickerActivity** :
   - 3 boutons : Rouge, Vert, Bleu
   - Au clic, retourner la couleur à MainActivity

**Contraintes** :
- Utiliser `ActivityResultLauncher` (pas `startActivityForResult`).
- Afficher un Snackbar après le changement de couleur.

**Livrable** : Projet complet exécutable.

---

## Barème (/20)

| Exercice | Points |
|----------|--------|
| Navigation 3 écrans | 5 |
| Passage de données | 7 |
| Activity Result API | 8 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
