# TD 01 : Découverte d'Android Studio et Premier Projet

**Durée** : 2 heures  
**Objectif** : Se familiariser avec Android Studio et créer une application HelloWorld fonctionnelle.

---

## Partie 1 : Installation et configuration (30 min)

### Exercice 1.1 : Vérification de l'installation

1. Vérifier que JDK 17+ est installé :
   ```bash
   java -version
   ```

2. Lancer Android Studio et vérifier que le SDK Manager est accessible.

3. Télécharger au moins les API 33, 34, 35.

**Livrable** : Capture d'écran du SDK Manager montrant les APIs installées.

---

## Partie 2 : Création du premier projet (45 min)

### Exercice 2.1 : HelloISITCOM

1. Créer un nouveau projet :
   - Template : **Empty Views Activity**
   - Nom : `HelloISITCOM`
   - Package : `tn.isitcom.helloisitcom`
   - Langage : **Java**
   - Minimum SDK : **API 24**

2. Explorer la structure du projet :
   - Identifier `MainActivity.java`
   - Identifier `activity_main.xml`
   - Identifier `AndroidManifest.xml`

3. Modifier `activity_main.xml` pour afficher :
   - Votre nom complet
   - Votre groupe
   - "Bienvenue au cours Android"

4. Modifier la couleur du texte et la taille de police.

**Livrable** : Capture d'écran de l'application exécutée sur l'émulateur.

---

## Partie 3 : Interaction utilisateur (45 min)

### Exercice 3.1 : Bouton interactif

1. Ajouter un `Button` dans le layout.
2. Au clic sur le bouton, changer le texte du `TextView`.
3. Afficher un Toast "Bouton cliqué !".

### Exercice 3.2 : Compteur de clics

1. Ajouter un compteur qui s'incrémente à chaque clic.
2. Afficher la valeur dans le `TextView`.
3. Ajouter un bouton "Réinitialiser" qui remet le compteur à zéro.

**Livrable** : Code source complet (MainActivity.java + activity_main.xml).

---

## Barème (/20)

| Exercice | Points |
|----------|--------|
| Installation et configuration | 4 |
| Projet HelloISITCOM | 6 |
| Bouton interactif | 5 |
| Compteur de clics | 5 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
