# TD 03 : Cycle de vie et Intents

## 🎯 Objectifs

- Comprendre le cycle de vie d'une Activity.
- Naviguer entre plusieurs Activities.
- Passer des données avec les Intents.
- Utiliser Activity Result API.

---

## Exercice 1 : Logger le cycle de vie

### Consignes

1. Créer un projet `TD03Lifecycle`.
2. Surcharger tous les callbacks (`onCreate`, `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`).
3. Ajouter des `Log.d("Lifecycle", "...")` dans chacun.
4. Tester :
   - Lancement de l'app.
   - Appui sur Home.
   - Retour à l'app.
   - Rotation de l'écran.

### À rendre

- Fichier `MainActivity.java`.
- Captures d'écran du Logcat montrant les callbacks.

---

## Exercice 2 : Navigation simple

### Consignes

1. Créer 2 Activities : `MainActivity` et `SecondActivity`.
2. Bouton dans `MainActivity` qui lance `SecondActivity`.
3. Bouton dans `SecondActivity` qui revient à `MainActivity` (`finish()`).

### À rendre

- Code des deux Activities.
- Fichier `AndroidManifest.xml`.

---

## Exercice 3 : Passage de données

### Consignes

1. `MainActivity` : champ `EditText` pour saisir un prénom.
2. Bouton "Valider" lance `GreetingActivity`.
3. `GreetingActivity` affiche "Bonjour [prénom]".

### À rendre

- Code complet.
- Captures d'écran.

---

## Exercice 4 : Activity Result API

### Consignes

1. `MainActivity` lance `ChooseColorActivity`.
2. `ChooseColorActivity` : 3 boutons (rouge, vert, bleu).
3. Au clic, retourner la couleur à `MainActivity`.
4. `MainActivity` change la couleur de fond.

### À rendre

- Code des deux Activities.
- Captures d'écran.

---

## Barème

| Exercice | Points |
|---------|--------|
| Cycle de vie | 5 |
| Navigation simple | 5 |
| Passage de données | 5 |
| Activity Result API | 5 |
| **Total** | **/20** |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
