# TD 09 : Intégration d'APIs et réseau

## 🎯 Objectifs

- Comprendre les requêtes HTTP.
- Utiliser Retrofit pour consommer une API REST.
- Afficher des données distantes dans RecyclerView.
- Gérer les états de chargement.

---

## Exercice 1 : Configuration Retrofit

### Consignes

1. Ajouter les dépendances Retrofit et Gson.
2. Créer une interface API pour JSONPlaceholder :
   - <https://jsonplaceholder.typicode.com/posts>
3. Créer un client Retrofit.
4. Tester une requête GET simple.

### À rendre

- Fichier `build.gradle` avec dépendances.
- Interface `ApiService.java`.
- Classe `RetrofitClient.java`.

---

## Exercice 2 : Afficher une liste de posts

### Consignes

1. Créer une classe `Post` (id, title, body).
2. Récupérer la liste des posts depuis l'API.
3. Afficher dans un RecyclerView.
4. Au clic sur un post, afficher le détail.

### À rendre

- Classe `Post.java`.
- Adapter et ViewHolder.
- Code de la requête API.
- Captures d'écran.

---

## Exercice 3 : Gestion du chargement

### Consignes

1. Ajouter un ProgressBar visible pendant le chargement.
2. Afficher un message d'erreur si la requête échoue.
3. Bouton "Réessayer" en cas d'erreur.

### À rendre

- Code de gestion des états.
- Captures d'écran (chargement, succès, erreur).

---

## Exercice 4 : Recherche et pagination (bonus)

### Consignes

1. Ajouter un champ de recherche.
2. Filtrer les posts par titre.
3. Implémenter la pagination (charger 10 posts à la fois).

### À rendre

- Code complet.
- Captures d'écran.

---

## Barème

| Exercice | Points |
|---------|--------|
| Configuration Retrofit | 4 |
| Liste de posts | 8 |
| Gestion chargement | 6 |
| Recherche/pagination (bonus) | +4 |
| **Total** | **/20 (+4)** |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
