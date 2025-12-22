# TD 02 : Interfaces et layouts

## 🎯 Objectifs

- Créer des interfaces en XML.
- Utiliser ConstraintLayout pour positionner des vues.
- Lier le code Java aux vues XML avec `findViewById`.
- Gérer les clics sur les boutons.

---

## Exercice 1 : Interface de login

### Consignes

1. Créer un nouveau projet `TD02Login`.
2. Dans `activity_main.xml`, créer une interface avec :
   - Un `TextView` pour le titre "Connexion".
   - Deux `EditText` (email et mot de passe).
   - Un `Button` "Se connecter".
   - Un `TextView` pour afficher les messages.

3. Utiliser `ConstraintLayout` pour organiser les vues.

4. Dans `MainActivity.java` :
   - Récupérer les vues.
   - Au clic sur le bouton, vérifier que les champs ne sont pas vides.
   - Si OK : afficher "Bienvenue [email]".
   - Si erreur : afficher "Champs obligatoires".

### À rendre

- Fichier `activity_main.xml`.
- Fichier `MainActivity.java`.
- Captures d'écran (avant et après clic).

---

## Exercice 2 : Calculatrice simple

### Consignes

1. Créer une interface avec :
   - Deux `EditText` pour les nombres.
   - Quatre `Button` (+, -, ×, ÷).
   - Un `TextView` pour le résultat.

2. Implémenter les 4 opérations.

3. Gérer la division par zéro (afficher "Erreur").

### À rendre

- Code complet.
- Captures d'écran de chaque opération.

---

## Exercice 3 : Afficher/masquer un texte

### Consignes

1. Un `TextView` avec un texte long.
2. Un bouton "Afficher/Masquer".
3. Alterner entre `View.VISIBLE` et `View.GONE`.

### À rendre

- Code Java du bouton.
- Captures d'écran.

---

## Barème

| Exercice | Points |
|---------|--------|
| Interface de login | 8 |
| Calculatrice | 8 |
| Afficher/masquer | 4 |
| **Total** | **/20** |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
