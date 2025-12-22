# Module 6 : Listes dynamiques avec RecyclerView

## 🎯 Objectifs d'apprentissage

À la fin de ce module, vous serez capable de :
- Comprendre le fonctionnement de RecyclerView.
- Créer un Adapter personnalisé.
- Gérer les clics sur les éléments de la liste.
- Afficher des listes en grille ou en liste linéaire.

---

## 1. Pourquoi RecyclerView ?

`ListView` (ancien) est remplacé par **RecyclerView** (moderne) car : [file:2]

- Performances optimisées (recyclage des vues).
- Flexibilité : liste verticale, horizontale, grille.
- Animations intégrées.

---

## 2. Architecture RecyclerView

Trois composants principaux : [file:2]

- **RecyclerView** : widget qui affiche la liste.
- **Adapter** : gère les données et crée les vues pour chaque élément.
- **ViewHolder** : contient les références aux vues d'un élément.

---

## 3. Étapes de création

### 3.1 Ajouter la dépendance

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

Synchroniser Gradle.

### 3.2 Ajouter RecyclerView dans le layout

Dans `activity_main.xml` :

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### 3.3 Créer le layout d'un élément

Créer `res/layout/item_contact.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:id="@+id/textName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Nom"
        android:textSize="18sp"
        android:textStyle="bold" />

    <TextView
        android:id="@+id/textPhone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Téléphone"
        android:textSize="14sp" />

</LinearLayout>
```

### 3.4 Créer la classe de données

```java
public class Contact {
    private String name;
    private String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() { return name; }
    public String getPhone() { return phone; }
}
```

### 3.5 Créer l'Adapter

```java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;

    public ContactAdapter(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.textName.setText(contact.getName());
        holder.textPhone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textPhone;

        public ContactViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
        }
    }
}
```

### 3.6 Configurer RecyclerView dans l'Activity

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Ahmed Ben Ali", "+216 12 345 678"));
        contacts.add(new Contact("Fatma Gharbi", "+216 98 765 432"));
        contacts.add(new Contact("Mohamed Slimi", "+216 55 123 456"));

        ContactAdapter adapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(adapter);
    }
}
```

---

## 4. Gérer les clics

### 4.1 Interface de callback

```java
public interface OnItemClickListener {
    void onItemClick(Contact contact);
}
```

### 4.2 Modifier l'Adapter

```java
private OnItemClickListener listener;

public ContactAdapter(List<Contact> contactList, OnItemClickListener listener) {
    this.contactList = contactList;
    this.listener = listener;
}

@Override
public void onBindViewHolder(ContactViewHolder holder, int position) {
    Contact contact = contactList.get(position);
    holder.textName.setText(contact.getName());
    holder.textPhone.setText(contact.getPhone());

    holder.itemView.setOnClickListener(v -> listener.onItemClick(contact));
}
```

### 4.3 Utilisation dans l'Activity

```java
ContactAdapter adapter = new ContactAdapter(contacts, contact -> {
    Toast.makeText(this, "Clic sur " + contact.getName(), Toast.LENGTH_SHORT).show();
});
```

---

## 5. Afficher en grille

Remplacer `LinearLayoutManager` par `GridLayoutManager` :

```java
recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 colonnes
```

---

## 6. Exercices pratiques (Module 6)

### Exercice 1 – Liste de produits

1. Créer une classe `Product` (nom, prix).
2. Afficher une liste de 5 produits.
3. Au clic sur un produit, afficher un Toast avec le nom et le prix.

### Exercice 2 – Liste avec images

1. Ajouter un `ImageView` dans `item_contact.xml`.
2. Ajouter une photo (drawable) pour chaque contact.
3. Afficher l'image dans le ViewHolder.

### Exercice 3 – Liste en grille

1. Afficher la liste de contacts en grille (2 colonnes).
2. Adapter le layout pour que l'affichage soit harmonieux.

---

## 7. Mini-TP : Application de liste de tâches

### Consignes

Créer une application "To-Do List" :

1. Afficher une liste de tâches (titre + description).
2. Bouton FAB (Floating Action Button) pour ajouter une tâche.
3. Au clic sur une tâche, ouvrir un écran de détail.
4. Possibilité de supprimer une tâche (clic long ou bouton).

### Critères d'évaluation

| Critère | Points |
|---------|--------|
| RecyclerView fonctionnel | 5 |
| Ajout de tâches | 5 |
| Détail d'une tâche | 5 |
| Suppression | 3 |
| Interface soignée | 2 |

**Total** : /20

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
