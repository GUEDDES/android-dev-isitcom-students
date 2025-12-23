# Module 6 : Quiz d'auto-évaluation

## Questions à choix multiples

### Question 1
Pourquoi utiliser RecyclerView plutôt que ListView ?

A) Plus joli  
B) Recycle les vues hors écran (performances)  
C) Plus simple  
D) Obligatoire depuis Android 10

<details>
<summary>Réponse</summary>
B) RecyclerView recycle les vues pour meilleures performances
</details>

---

### Question 2
Quels sont les 3 composants essentiels de RecyclerView ?

A) Adapter, ViewHolder, LayoutInflater  
B) Adapter, ViewHolder, LayoutManager  
C) RecyclerView, ListView, GridView  
D) Activity, Fragment, View

<details>
<summary>Réponse</summary>
B) Adapter (lie données), ViewHolder (contient vues), LayoutManager (organisation)
</details>

---

### Question 3
Que fait onCreateViewHolder() ?

A) Crée une nouvelle vue pour chaque item  
B) Lie données à la vue  
C) Retourne le nombre d'items  
D) Détruit les vues

<details>
<summary>Réponse</summary>
A) Crée (inflate) une nouvelle vue - appelé une fois par vue visible
</details>

---

### Question 4
Que fait onBindViewHolder() ?

A) Crée une vue  
B) Lie les données d'un item à une vue existante  
C) Compte les items  
D) Définit le layout

<details>
<summary>Réponse</summary>
B) Lie (bind) les données à une vue recyclée - appelé pour chaque item
</details>

---

### Question 5
Que retourne getItemCount() ?

A) La taille de l'écran  
B) Le nombre total d'items dans la liste  
C) Le nombre de vues visibles  
D) 0

<details>
<summary>Réponse</summary>
B) Le nombre total d'items à afficher
</details>

---

### Question 6
Comment afficher une liste verticale ?

A) recyclerView.setOrientation(VERTICAL)  
B) recyclerView.setLayoutManager(new LinearLayoutManager(this))  
C) recyclerView.setVertical(true)  
D) recyclerView.setLayout(LinearLayout.VERTICAL)

<details>
<summary>Réponse</summary>
B) setLayoutManager(new LinearLayoutManager(this)) - vertical par défaut
</details>

---

### Question 7
Comment afficher une grille 2 colonnes ?

A) new GridLayoutManager(this, 2)  
B) new GridManager(2)  
C) setColumns(2)  
D) new LayoutManager(GRID, 2)

<details>
<summary>Réponse</summary>
A) new GridLayoutManager(this, 2)
</details>

---

### Question 8
Comment notifier un changement de données ?

A) adapter.update()  
B) adapter.notifyDataSetChanged()  
C) adapter.refresh()  
D) recyclerView.update()

<details>
<summary>Réponse</summary>
B) adapter.notifyDataSetChanged() (ou notifyItemInserted/Removed pour être plus précis)
</details>

---

### Question 9
Où définir le listener de clic dans l'Adapter ?

A) Dans onCreateViewHolder()  
B) Dans onBindViewHolder()  
C) Dans getItemCount()  
D) Dans le constructeur

<details>
<summary>Réponse</summary>
B) Dans onBindViewHolder() ou via une interface callback
</details>

---

### Question 10
Que fait LayoutInflater.from(parent.getContext()).inflate() ?

A) Crée une Activity  
B) Convertit XML en objet View  
C) Affiche un Toast  
D) Insère dans la base de données

<details>
<summary>Réponse</summary>
B) Convertit (inflate) un layout XML en objet View Java
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
@Override
public int getItemCount() {
    return items.size();
}
```

A) Retourne 0  
B) Compte les vues visibles  
C) Retourne le nombre d'items dans la liste  
D) Crée des items

<details>
<summary>Réponse</summary>
C) Retourne le nombre total d'items à afficher
</details>

---

### Question 12
Quelle est l'erreur ?

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
MyAdapter adapter = new MyAdapter(dataList);
recyclerView.setAdapter(adapter);
// Manque quelque chose ?
```

A) Pas d'erreur  
B) Manque setLayoutManager()  
C) Manque setContentView()  
D) Manque notifyDataSetChanged()

<details>
<summary>Réponse</summary>
B) Il faut appeler recyclerView.setLayoutManager(new LinearLayoutManager(this))
</details>

---

### Question 13
Que fait ce code ?

```java
adapter.setOnItemClickListener((item, position) -> {
    Toast.makeText(this, item.getName(), Toast.LENGTH_SHORT).show();
});
```

A) Crée un adapter  
B) Définit un callback de clic sur item  
C) Affiche tous les items  
D) Supprime un item

<details>
<summary>Réponse</summary>
B) Définit une action à exécuter lors du clic sur un item
</details>

---

### Question 14
Corrigez ce ViewHolder :

```java
static class ViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    
    public ViewHolder(View itemView) {
        textName = itemView.findViewById(R.id.textName);
    }
}
```

A) Pas d'erreur  
B) Manque super(itemView)  
C) static inutile  
D) B est correct

<details>
<summary>Réponse</summary>
B et D) Il faut appeler super(itemView) dans le constructeur
</details>

---

### Question 15
Que fait ce code ?

```java
adapter.notifyItemInserted(items.size() - 1);
```

A) Insère un nouvel item  
B) Notifie qu'un item a été ajouté à la fin  
C) Supprime un item  
D) Met à jour tous les items

<details>
<summary>Réponse</summary>
B) Notifie que le dernier item a été ajouté (avec animation)
</details>

---

## Questions ouvertes

### Question 16
Expliquez le pattern ViewHolder et son intérêt.

<details>
<summary>Réponse</summary>
Le ViewHolder stocke les références aux vues (TextView, Button...) pour éviter de rappeler findViewById() à chaque fois. Cela améliore les performances en réduisant les recherches dans la hiérarchie des vues.
</details>

---

### Question 17
Quelle est la différence entre notifyDataSetChanged() et notifyItemInserted() ?

<details>
<summary>Réponse</summary>
- **notifyDataSetChanged()** : Rafraîchit toute la liste (pas d'animation)
- **notifyItemInserted(position)** : Notifie qu'un seul item a changé (avec animation, plus performant)
</details>

---

### Question 18
Comment implanter un clic sur item avec interface callback ?

<details>
<summary>Réponse</summary>
```java
// 1. Définir interface dans Adapter
public interface OnItemClickListener {
    void onItemClick(Item item, int position);
}

// 2. Stocker listener
private OnItemClickListener listener;

// 3. Setter
public void setOnItemClickListener(OnItemClickListener listener) {
    this.listener = listener;
}

// 4. Appeler dans onBindViewHolder
itemView.setOnClickListener(v -> {
    if (listener != null) {
        listener.onItemClick(items.get(position), position);
    }
});
```
</details>

---

## Barème

- **16-18/18** : Excellent ! RecyclerView maîtrisé
- **13-15/18** : Très bien
- **10-12/18** : Bien, revoir certains concepts
- **< 10/18** : Revoir le module

---

👨‍🏫 **Module 6 - RecyclerView** | ISITCOM 2025-2026
