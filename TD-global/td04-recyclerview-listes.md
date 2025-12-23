# TD04 : RecyclerView et Listes

## 🎯 Objectifs

- Maîtriser RecyclerView.
- Créer un Adapter personnalisé.
- Gérer les clics sur les éléments.

---

## Partie 1 : Liste de contacts (60 min)

### Consignes

Créer une application "Mes Contacts" :

1. **Classe Contact** :
   ```java
   public class Contact {
       private String name;
       private String phone;
       private int photoResId;
       
       // Constructeur, getters, setters
   }
   ```

2. **Layout item_contact.xml** :
   - Photo circulaire (ImageView, 50dp).
   - Nom (TextView, bold, 16sp).
   - Téléphone (TextView, 14sp).
   - Le tout dans un CardView.

3. **ContactAdapter** :
   - Afficher la liste de contacts.
   - Au clic sur un contact, ouvrir `ContactDetailActivity`.

4. **MainActivity** :
   - RecyclerView avec 10 contacts fictifs.
   - FAB pour "ajouter" (Toast pour l'instant).

### Code exemple : ContactAdapter.java

```java
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private OnContactClickListener listener;

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public ContactAdapter(List<Contact> contactList, OnContactClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
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
        holder.imagePhoto.setImageResource(contact.getPhotoResId());

        holder.itemView.setOnClickListener(v -> listener.onContactClick(contact));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhoto;
        TextView textName, textPhone;

        ContactViewHolder(View itemView) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.imagePhoto);
            textName = itemView.findViewById(R.id.textName);
            textPhone = itemView.findViewById(R.id.textPhone);
        }
    }
}
```

### Code exemple : item_contact.xml

```xml
<com.google.android.material.card.MaterialCardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:padding="12dp"
        android:gravity="center_vertical">

        <ImageView
            android:id="@+id/imagePhoto"
            android:layout_width="50dp"
            android:layout_height="50dp"
            android:src="@drawable/ic_person" />

        <LinearLayout
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:orientation="vertical"
            android:layout_marginStart="12dp">

            <TextView
                android:id="@+id/textName"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Nom"
                android:textSize="16sp"
                android:textStyle="bold" />

            <TextView
                android:id="@+id/textPhone"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Téléphone"
                android:textSize="14sp" />

        </LinearLayout>

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

---

## Partie 2 : Affichage en grille (20 min)

### Consignes

1. Ajouter un bouton "Mode Grille" dans le menu.
2. Au clic, basculer entre `LinearLayoutManager` et `GridLayoutManager` (2 colonnes).
3. Adapter le layout pour que l'affichage en grille soit harmonieux.

---

## Partie 3 : Recherche (30 min)

### Consignes

1. Ajouter un `SearchView` dans la toolbar.
2. Filtrer la liste en temps réel selon le nom.
3. Méthode dans l'Adapter :
   ```java
   public void filterList(List<Contact> filteredList) {
       contactList = filteredList;
       notifyDataSetChanged();
   }
   ```

### Code exemple : MainActivity.java

```java
private void filterContacts(String query) {
    List<Contact> filteredList = new ArrayList<>();
    for (Contact contact : allContacts) {
        if (contact.getName().toLowerCase().contains(query.toLowerCase())) {
            filteredList.add(contact);
        }
    }
    adapter.filterList(filteredList);
}
```

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| RecyclerView fonctionnel | 6 |
| Adapter correct | 5 |
| Gestion des clics | 3 |
| Mode grille | 3 |
| Recherche | 3 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
