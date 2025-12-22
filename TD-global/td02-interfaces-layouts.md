# TD02 : Interfaces et layouts

## 🎯 Objectifs

- Maîtriser ConstraintLayout.
- Utiliser différents widgets (EditText, Button, CheckBox...).
- Lier les vues au code Java.

---

## Partie 1 : ConstraintLayout (45 min)

### Exercice 1.1 : Interface de connexion

Créer un écran de connexion avec :

- 2 `EditText` (email, mot de passe).
- 1 `Button` "Se connecter".
- 1 `TextView` pour les messages d'erreur.

Contraintes :
- Email centré horizontalement, à 30% du haut.
- Mot de passe 16dp sous l'email.
- Bouton 24dp sous le mot de passe.
- TextView en bas de l'écran.

### Exercice 1.2 : Validation du formulaire

Dans `MainActivity.java` :

```java
EditText editEmail = findViewById(R.id.editEmail);
EditText editPassword = findViewById(R.id.editPassword);
Button btnLogin = findViewById(R.id.btnLogin);
TextView textError = findViewById(R.id.textError);

btnLogin.setOnClickListener(v -> {
    String email = editEmail.getText().toString();
    String password = editPassword.getText().toString();

    if (email.isEmpty() || password.isEmpty()) {
        textError.setText("⚠️ Tous les champs sont obligatoires");
        textError.setTextColor(Color.RED);
    } else if (password.length() < 6) {
        textError.setText("⚠️ Mot de passe trop court (min 6 caractères)");
    } else {
        textError.setText("✅ Connexion réussie !");
        textError.setTextColor(Color.GREEN);
    }
});
```

---

## Partie 2 : Widgets variés (45 min)

### Exercice 2.1 : Formulaire d'inscription

Créer un formulaire avec :

- `EditText` : nom, prénom, email, téléphone.
- `RadioGroup` : sexe (Homme/Femme).
- `CheckBox` : "J'accepte les conditions".
- `Button` : "S'inscrire".

### Exercice 2.2 : Afficher les données

À la soumission :

1. Vérifier que tous les champs sont remplis.
2. Vérifier que la CheckBox est cochée.
3. Afficher les données dans un Toast ou un TextView.

---

## Partie 3 : LinearLayout (30 min)

### Exercice 3.1 : Calculatrice simple

Créer une interface avec LinearLayout vertical :

- 2 `EditText` pour les nombres.
- 4 `Button` (+, -, ×, ÷).
- 1 `TextView` pour le résultat.

Implémenter les 4 opérations.

---

## 🎯 TP Noté : Formulaire de contact (/20)

### Consignes

Créer une application "Formulaire de Contact" :

1. **Champs** :
   - Nom (obligatoire).
   - Email (obligatoire, format validé).
   - Sujet (obligatoire).
   - Message (multiligne, obligatoire).

2. **Actions** :
   - Bouton "Envoyer" : valide et affiche un résumé.
   - Bouton "Réinitialiser" : vide tous les champs.

3. **Validation** :
   - Email doit contenir @.
   - Message min 10 caractères.
   - Afficher erreurs en rouge.

### Barème

| Critère | Points |
|---------|--------|
| Interface complète | 4 |
| Validation des champs | 6 |
| Bouton Envoyer fonctionnel | 4 |
| Bouton Réinitialiser | 2 |
| Messages d'erreur clairs | 2 |
| Code propre | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
