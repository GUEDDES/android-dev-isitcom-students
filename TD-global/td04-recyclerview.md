# TD 04 : RecyclerView et Listes dynamiques

**Durée** : 2 heures  
**Objectif** : Afficher des listes dynamiques avec RecyclerView et gérer les interactions.

---

## Partie 1 : RecyclerView basique (45 min)

### Exercice 1.1 : Liste de contacts

Créer une application affichant une liste de contacts :

**Classe Contact** :
```java
public class Contact {
    private String name;
    private String phone;
    // Constructeur, getters
}
```

**Tâches** :
1. Créer un layout `item_contact.xml` (nom + téléphone).
2. Créer un `ContactAdapter` avec ViewHolder.
3. Afficher 10 contacts dans un RecyclerView.

**Livrable** : Capture d'écran de l'application + code de l'Adapter.

---

## Partie 2 : Gestion des clics (30 min)

### Exercice 2.1 : Clic sur contact

1. Au clic sur un contact, afficher un Toast avec le nom et le numéro.
2. Au clic long, afficher un Dialog de confirmation de suppression.
3. Si confirmé, supprimer le contact de la liste (et notifier l'Adapter).

**Livrable** : Code source complet.

---

## Partie 3 : CardView et grille (45 min)

### Exercice 3.1 : Liste de produits

Créer une application e-commerce simplifiée :

**Classe Product** :
```java
public class Product {
    private String name;
    private double price;
    private int imageRes;
    // Constructeur, getters
}
```

**Tâches** :
1. Afficher 12 produits dans un RecyclerView en **grille 2 colonnes**.
2. Utiliser `MaterialCardView` pour chaque produit.
3. Au clic, afficher un Toast avec le nom et le prix.

**Livrable** : Projet complet avec captures d'écran.

---

## Barème (/20)

| Exercice | Points |
|----------|--------|
| RecyclerView contacts | 6 |
| Gestion clics et suppression | 6 |
| Grille de produits avec CardView | 8 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
