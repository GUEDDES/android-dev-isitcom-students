# Quiz - Module 6 : RecyclerView

## Questions à choix multiples

### Question 1
Pourquoi utiliser RecyclerView au lieu de ListView ?

A) Plus facile à utiliser  
B) Recycle les vues hors écran (performances)  
C) Moins de code  
D) Plus joli

<details>
<summary>Réponse</summary>
B) RecyclerView recycle les vues = beaucoup plus performant pour grandes listes
</details>

---

### Question 2
Quels sont les 3 composants essentiels de RecyclerView ?

A) Adapter, ViewHolder, ListView  
B) Adapter, ViewHolder, LayoutManager  
C) Activity, Fragment, Adapter  
D) ViewHolder, ListView, Manager

<details>
<summary>Réponse</summary>
B) Adapter (lie données), ViewHolder (contient vues), LayoutManager (organisation)
</details>

---

### Question 3
Que fait onCreateViewHolder() ?

A) Crée la vue pour chaque item (appelé pour chaque ligne)  
B) Crée le ViewHolder (appelé une fois par vue visible)  
C) Lie les données  
D) Compte les items

<details>
<summary>Réponse</summary>
B) Crée le ViewHolder, appelé seulement pour les vues visibles (recyclées ensuite)
</details>

---

### Question 4
Que fait onBindViewHolder() ?

A) Crée les vues  
B) Lie les données aux vues (appelé pour chaque item)  
C) Configure le LayoutManager  
D) Détruit les vues

<details>
<summary>Réponse</summary>
B) Met à jour les vues avec les données de l'item à la position donnée
</details>

---

### Question 5
Que retourne getItemCount() ?

A) Le nombre de vues visibles  
B) Le nombre total d'items dans la liste  
C) La hauteur d'un item  
D) L'index actuel

<details>
<summary>Réponse</summary>
B) Le nombre total d'items à afficher
</details>

---

### Question 6
Quel LayoutManager pour une liste verticale ?

A) VerticalLayoutManager  
B) LinearLayoutManager  
C) GridLayoutManager  
D) ListLayoutManager

<details>
<summary>Réponse</summary>
B) LinearLayoutManager (orientation verticale par défaut)
</details>

---

### Question 7
Comment afficher une grille de 2 colonnes ?

A) new GridLayoutManager(this, 2)  
B) new LinearLayoutManager(this, 2)  
C) new ColumnLayoutManager(this, 2)  
D) recyclerView.setColumns(2)

<details>
<summary>Réponse</summary>
A) GridLayoutManager avec le nombre de colonnes
</details>

---

### Question 8
Comment notifier l'Adapter qu'un item a été ajouté à la position 5 ?

A) adapter.notifyDataSetChanged()  
B) adapter.notifyItemInserted(5)  
C) adapter.refresh()  
D) recyclerView.update(5)

<details>
<summary>Réponse</summary>
B) notifyItemInserted(position) - plus performant que notifyDataSetChanged()
</details>

---

### Question 9
Que contient le ViewHolder ?

A) Les données de l'item  
B) Les références aux vues (TextView, ImageView...)  
C) Le code de l'Adapter  
D) Le RecyclerView

<details>
<summary>Réponse</summary>
B) Les références aux vues pour éviter de rappeler findViewById()
</details>

---

### Question 10
Où appeler setLayoutManager() ?

A) Dans l'Adapter  
B) Dans le ViewHolder  
C) Dans l'Activity/Fragment  
D) Dans le layout XML

<details>
<summary>Réponse</summary>
C) Dans onCreate() de l'Activity ou onViewCreated() du Fragment
</details>

---

## Questions de code

### Question 11
Que fait ce code ?

```java
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.setAdapter(new ContactAdapter(contacts));
```

A) Crée une liste verticale de contacts  
B) Crée une grille  
C) Configure seulement  
D) Affiche un message

<details>
<summary>Réponse</summary>
A) Configure RecyclerView avec liste verticale et affiche les contacts
</details>

---

### Question 12
Que manque-t-il dans ce ViewHolder ?

```java
static class MyViewHolder extends RecyclerView.ViewHolder {
    TextView textName;
    
    public MyViewHolder(View itemView) {
        super(itemView);
        // ???
    }
}
```

A) Rien  
B) findViewById pour initialiser textName  
C) setContentView  
D) new TextView()

<details>
<summary>Réponse</summary>
B) textName = itemView.findViewById(R.id.textName);
</details>

---

### Question 13
Quel est le problème dans cet Adapter ?

```java
@Override
public int getItemCount() {
    return 0;
}
```

A) Pas de problème  
B) Retourne toujours 0, liste vide  
C) Mauvais type de retour  
D) Nom de méthode incorrect

<details>
<summary>Réponse</summary>
B) Doit retourner items.size() ou liste.length
</details>

---

### Question 14
Comment implémenter un clic sur un item ?

```java
// Dans onBindViewHolder
A) holder.itemView.setOnClickListener(v -> { /* code */ });
B) recyclerView.setOnClickListener(...);
C) adapter.setOnClickListener(...);
D) Impossible
```

<details>
<summary>Réponse</summary>
A) Sur holder.itemView dans onBindViewHolder ou via interface callback
</details>

---

### Question 15
Que fait ce code ?

```java
List<Contact> contacts = new ArrayList<>();
contacts.add(new Contact("Alice", "123"));
contacts.add(new Contact("Bob", "456"));

ContactAdapter adapter = new ContactAdapter(contacts);
adapter.notifyDataSetChanged();
```

A) Met à jour l'affichage  
B) Appel inutile (adapter pas encore attaché)  
C) Crée une erreur  
D) Supprime les données

<details>
<summary>Réponse</summary>
B) notifyDataSetChanged() inutile avant setAdapter(). Appeler après modifications ultérieures.
</details>

---

## Questions ouvertes

### Question 16
Expliquez le principe du recyclage dans RecyclerView.

<details>
<summary>Réponse</summary>
Quand un item sort de l'écran (scroll), sa vue est réutilisée pour afficher un nouvel item qui entre. Seules les données changent (via onBindViewHolder), pas la structure de la vue. Économise mémoire et temps de création.
</details>

---

### Question 17
Différence entre notifyDataSetChanged() et notifyItemInserted() ?

<details>
<summary>Réponse</summary>
- **notifyDataSetChanged()** : Rafraîchit toute la liste (lent, pas d'animation)
- **notifyItemInserted(position)** : Notifie qu'un seul item a changé (rapide, avec animation)
</details>

---

## Exercice pratique

### Question 18
Créez un Adapter simple pour afficher une liste de noms (String).

<details>
<summary>Solution</summary>

```java
public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {
    
    private List<String> names;
    
    public NameAdapter(List<String> names) {
        this.names = names;
    }
    
    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new NameViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        holder.textName.setText(names.get(position));
    }
    
    @Override
    public int getItemCount() {
        return names.size();
    }
    
    static class NameViewHolder extends RecyclerView.ViewHolder {
        TextView textName;
        
        public NameViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(android.R.id.text1);
        }
    }
}
```
</details>

---

## Barème

- **17-18/18** : Excellent ! Maîtrise complète de RecyclerView
- **14-16/18** : Très bien
- **11-13/18** : Bien, quelques révisions
- **< 11/18** : Revoir le module

---

👨‍🏫 **Module 6 - RecyclerView** | ISITCOM 2025-2026
