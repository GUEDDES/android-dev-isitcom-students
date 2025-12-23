# Module 6 : Fiche de synthèse

## 🎯 Concepts clés

### RecyclerView = Liste optimisée

**Problème** : ListView recrée toutes les vues (lent)  
**Solution** : RecyclerView recycle les vues hors écran (rapide)

---

## 📦 Architecture RecyclerView

```
RecyclerView
  │
  ├── Adapter (étape 1) : Lie données et vues
  │     └── ViewHolder (étape 2) : Contient les vues
  │
  └── LayoutManager (étape 3) : Organisation (vertical/horizontal)
```

---

## 🛠️ Implémentation en 3 étapes

### Étape 1 : Classe de modèle

```java
public class Contact {
    private String nom;
    private String telephone;
    
    public Contact(String nom, String telephone) {
        this.nom = nom;
        this.telephone = telephone;
    }
    
    // Getters
    public String getNom() { return nom; }
    public String getTelephone() { return telephone; }
}
```

---

### Étape 2 : Layout de l'item (item_contact.xml)

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">
    
    <TextView
        android:id="@+id/textNom"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:textStyle="bold" />
    
    <TextView
        android:id="@+id/textTelephone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor="#666" />
        
</LinearLayout>
```

---

### Étape 3 : Adapter

```java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    
    private List<Contact> contacts;
    
    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }
    
    // Créer le ViewHolder (appelé une fois par vue visible)
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }
    
    // Lier données et vue (appelé pour chaque item)
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.textNom.setText(contact.getNom());
        holder.textTelephone.setText(contact.getTelephone());
    }
    
    // Nombre total d'items
    @Override
    public int getItemCount() {
        return contacts.size();
    }
    
    // ViewHolder : contient les vues
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textNom, textTelephone;
        
        public ContactViewHolder(View itemView) {
            super(itemView);
            textNom = itemView.findViewById(R.id.textNom);
            textTelephone = itemView.findViewById(R.id.textTelephone);
        }
    }
}
```

---

### Étape 4 : Utilisation dans Activity

```java
public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        
        // Créer données
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Alice", "123456"));
        contacts.add(new Contact("Bob", "789012"));
        
        // Configurer RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactAdapter(contacts));
    }
}
```

---

## 👆 Gestion des clics

### Dans l'Adapter

```java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    
    private List<Contact> contacts;
    private OnItemClickListener listener;
    
    // Interface callback
    public interface OnItemClickListener {
        void onItemClick(Contact contact, int position);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    
    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.bind(contact, position, listener);
    }
    
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textNom;
        
        public ContactViewHolder(View itemView) {
            super(itemView);
            textNom = itemView.findViewById(R.id.textNom);
        }
        
        public void bind(Contact contact, int position, OnItemClickListener listener) {
            textNom.setText(contact.getNom());
            
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(contact, position);
                }
            });
        }
    }
}
```

### Dans l'Activity

```java
ContactAdapter adapter = new ContactAdapter(contacts);
adapter.setOnItemClickListener((contact, position) -> {
    Toast.makeText(this, "Clic : " + contact.getNom(), Toast.LENGTH_SHORT).show();
});
recyclerView.setAdapter(adapter);
```

---

## 🔄 Mettre à jour les données

```java
// Notifier tous les changements
adapter.notifyDataSetChanged();

// Plus performant : notifier un item spécifique
adapter.notifyItemInserted(position);
adapter.notifyItemRemoved(position);
adapter.notifyItemChanged(position);
```

---

## 📋 LayoutManager

```java
// Liste verticale (par défaut)
recyclerView.setLayoutManager(new LinearLayoutManager(this));

// Liste horizontale
recyclerView.setLayoutManager(
    new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
);

// Grille (2 colonnes)
recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
```

---

## ⚠️ Erreurs courantes

| Problème | Cause | Solution |
|----------|-------|----------|
| Liste vide | LayoutManager non défini | Appeler setLayoutManager |
| Crash dans bind | Liste null | Vérifier données avant |
| Clic ne marche pas | Listener non configuré | Implémenter interface |
| getItemCount retourne 0 | Liste vide | Vérifier données |

---

## 📝 Checklist

✅ Créer classe modèle (Contact, Produit...)  
✅ Créer layout item (item_contact.xml)  
✅ Créer Adapter avec ViewHolder  
✅ Définir LayoutManager  
✅ Passer données à l'Adapter  
✅ Appeler setAdapter() sur RecyclerView  

---

## 🔑 Squelette Adapter

```java
public class MonAdapter extends RecyclerView.Adapter<MonAdapter.ViewHolder> {
    
    private List<MonObjet> items;
    
    public MonAdapter(List<MonObjet> items) {
        this.items = items;
    }
    
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }
    
    @Override
    public int getItemCount() {
        return items.size();
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        // Déclarer vues
        
        public ViewHolder(View itemView) {
            super(itemView);
            // findViewById...
        }
        
        public void bind(MonObjet item) {
            // Afficher données
        }
    }
}
```

---

👨‍🏫 **Module 6 - RecyclerView** | ISITCOM 2025-2026
