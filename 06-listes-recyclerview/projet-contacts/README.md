# 📞 Projet Module 6 : Application Contacts avec RecyclerView

## 🎯 Objectif

Créer une application de gestion de contacts avec liste scrollable, ajout, suppression et recherche.

**Concepts** : RecyclerView, Adapter, ViewHolder, ItemClickListener, SearchView, Dialog, Intent implicite.

---

## ✨ Fonctionnalités

✅ Liste de contacts avec avatar coloré  
✅ Ajout de nouveau contact (Dialog Material Design)  
✅ Suppression au clic long avec confirmation  
✅ Recherche en temps réel (nom ou téléphone)  
✅ Click pour appeler (Intent implicite ACTION_DIAL)  
✅ Vue vide quand aucun contact  
✅ Scroll fluide avec animations  

---

## 📂 Structure du projet

```
app/src/main/
├── java/tn/isitcom/contacts/
│   ├── MainActivity.java          # Activity principale
│   ├── ContactAdapter.java        # Adapter RecyclerView
│   └── Contact.java               # Modèle de données
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # Layout principal
│   │   ├── item_contact.xml       # Layout item liste
│   │   └── dialog_add_contact.xml # Dialog ajout contact
│   ├── drawable/
│   │   ├── circle_bg.xml          # Fond circulaire avatar
│   │   ├── ic_add.xml             # Icône + (FAB)
│   │   └── ic_call.xml            # Icône téléphone
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── mipmap/
│       ├── ic_launcher.png
│       └── ic_launcher_round.png
└── AndroidManifest.xml
```

---

## 📝 Fichiers du projet

### 1️⃣ **Contact.java** - Modèle de données

**Emplacement** : `app/src/main/java/tn/isitcom/contacts/Contact.java`

```java
package tn.isitcom.contacts;

public class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    // Première lettre pour l'avatar
    public String getInitial() {
        return name.isEmpty() ? "?" : String.valueOf(name.charAt(0)).toUpperCase();
    }
}
```

---

### 2️⃣ **ContactAdapter.java** - Adapter RecyclerView

**Emplacement** : `app/src/main/java/tn/isitcom/contacts/ContactAdapter.java`

```java
package tn.isitcom.contacts;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private List<Contact> contactsFiltered; // Pour la recherche
    private OnContactClickListener listener;

    // Interface pour gérer les clics
    public interface OnContactClickListener {
        void onCallClick(Contact contact);
        void onLongClick(Contact contact, int position);
    }

    public ContactAdapter(List<Contact> contacts, OnContactClickListener listener) {
        this.contacts = contacts;
        this.contactsFiltered = new ArrayList<>(contacts);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactsFiltered.get(position);
        holder.bind(contact, listener);
    }

    @Override
    public int getItemCount() {
        return contactsFiltered.size();
    }

    /**
     * Filtrer les contacts selon la requête de recherche
     */
    public void filter(String query) {
        contactsFiltered.clear();
        
        if (query.isEmpty()) {
            contactsFiltered.addAll(contacts);
        } else {
            String queryLower = query.toLowerCase();
            for (Contact contact : contacts) {
                if (contact.getName().toLowerCase().contains(queryLower) ||
                    contact.getPhone().contains(queryLower)) {
                    contactsFiltered.add(contact);
                }
            }
        }
        
        notifyDataSetChanged();
    }

    /**
     * Supprimer un contact
     */
    public void removeContact(int position) {
        Contact contact = contactsFiltered.get(position);
        contacts.remove(contact);
        contactsFiltered.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * ViewHolder (contient les références aux vues)
     */
    static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView tvAvatar, tvName, tvPhone;
        ImageView ivCall;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAvatar = itemView.findViewById(R.id.tvAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
            ivCall = itemView.findViewById(R.id.ivCall);
        }

        public void bind(Contact contact, OnContactClickListener listener) {
            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhone());
            tvAvatar.setText(contact.getInitial());

            // Couleur aléatoire pour l'avatar
            int[] colors = {0xFF1976D2, 0xFF388E3C, 0xFFF57C00, 0xFFD32F2F, 0xFF7B1FA2};
            int color = colors[new Random(contact.getName().hashCode()).nextInt(colors.length)];
            tvAvatar.setBackgroundColor(color);

            // Click sur bouton d'appel
            ivCall.setOnClickListener(v -> listener.onCallClick(contact));

            // Long click sur l'item entier
            itemView.setOnLongClickListener(v -> {
                listener.onLongClick(contact, getAdapterPosition());
                return true;
            });
        }
    }
}
```

---

### 3️⃣ **MainActivity.java** - Activity principale

**Emplacement** : `app/src/main/java/tn/isitcom/contacts/MainActivity.java`

```java
package tn.isitcom.contacts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ContactAdapter.OnContactClickListener {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contacts;
    private TextView tvEmpty;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupRecyclerView();
        loadSampleData();

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> showAddContactDialog());

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        tvEmpty = findViewById(R.id.tvEmpty);
        searchView = findViewById(R.id.searchView);
        contacts = new ArrayList<>();
    }

    private void setupRecyclerView() {
        adapter = new ContactAdapter(contacts, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void loadSampleData() {
        contacts.add(new Contact("Alice Martin", "+216 12 345 678", "alice@mail.com"));
        contacts.add(new Contact("Bob Dupont", "+216 98 765 432", "bob@mail.com"));
        contacts.add(new Contact("Claire Bernard", "+216 54 321 098", "claire@mail.com"));
        contacts.add(new Contact("David Petit", "+216 71 234 567", "david@mail.com"));
        contacts.add(new Contact("Émilie Rousseau", "+216 20 111 222", "emilie@mail.com"));
        
        adapter.notifyDataSetChanged();
        updateEmptyView();
    }

    private void showAddContactDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_contact, null);
        EditText etName = dialogView.findViewById(R.id.etName);
        EditText etPhone = dialogView.findViewById(R.id.etPhone);
        EditText etEmail = dialogView.findViewById(R.id.etEmail);

        new AlertDialog.Builder(this)
            .setTitle("Nouveau contact")
            .setView(dialogView)
            .setPositiveButton("Ajouter", (dialog, which) -> {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String email = etEmail.getText().toString().trim();

                if (name.isEmpty() || phone.isEmpty()) {
                    Toast.makeText(this, "Nom et téléphone obligatoires", Toast.LENGTH_SHORT).show();
                    return;
                }

                contacts.add(new Contact(name, phone, email));
                adapter.notifyItemInserted(contacts.size() - 1);
                recyclerView.smoothScrollToPosition(contacts.size() - 1);
                updateEmptyView();
                Toast.makeText(this, "Contact ajouté", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Annuler", null)
            .show();
    }

    @Override
    public void onCallClick(Contact contact) {
        // Intent implicite pour appeler
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + contact.getPhone()));
        startActivity(callIntent);
    }

    @Override
    public void onLongClick(Contact contact, int position) {
        new AlertDialog.Builder(this)
            .setTitle("Supprimer ?")
            .setMessage("Voulez-vous supprimer " + contact.getName() + " ?")
            .setPositiveButton("Supprimer", (dialog, which) -> {
                adapter.removeContact(position);
                updateEmptyView();
                Toast.makeText(this, "Contact supprimé", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("Annuler", null)
            .show();
    }

    private void updateEmptyView() {
        tvEmpty.setVisibility(contacts.isEmpty() ? View.VISIBLE : View.GONE);
        recyclerView.setVisibility(contacts.isEmpty() ? View.GONE : View.VISIBLE);
    }
}
```

---

## 📱 Layouts XML

### 4️⃣ **activity_main.xml** - Layout principal

**Emplacement** : `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.appcompat.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="0dp"
        android:layout_height="?attr/actionBarSize"
        android:background="?attr/colorPrimary"
        android:elevation="4dp"
        app:title="Mes Contacts"
        app:titleTextColor="@android:color/white"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <androidx.appcompat.widget.SearchView
        android:id="@+id/searchView"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:queryHint="Rechercher un contact..."
        android:background="@android:color/white"
        android:elevation="2dp"
        app:layout_constraintTop_toBottomOf="@id/toolbar"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:padding="8dp"
        android:clipToPadding="false"
        app:layout_constraintTop_toBottomOf="@id/searchView"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fabAdd"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:src="@drawable/ic_add"
        android:contentDescription="Ajouter contact"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_margin="16dp" />

    <TextView
        android:id="@+id/tvEmpty"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aucun contact"
        android:textSize="18sp"
        android:textColor="#9E9E9E"
        android:visibility="gone"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### 5️⃣ **item_contact.xml** - Layout item liste

**Emplacement** : `app/src/main/res/layout/item_contact.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<com.google.android.material.card.MaterialCardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardCornerRadius="12dp"
    app:cardElevation="2dp">

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:padding="16dp">

        <!-- Avatar circulaire -->
        <TextView
            android:id="@+id/tvAvatar"
            android:layout_width="48dp"
            android:layout_height="48dp"
            android:background="@drawable/circle_bg"
            android:gravity="center"
            android:text="A"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            android:textStyle="bold"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintStart_toStartOf="parent" />

        <!-- Nom -->
        <TextView
            android:id="@+id/tvName"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="Alice Martin"
            android:textSize="18sp"
            android:textStyle="bold"
            android:textColor="#212121"
            app:layout_constraintTop_toTopOf="@id/tvAvatar"
            app:layout_constraintStart_toEndOf="@id/tvAvatar"
            app:layout_constraintEnd_toStartOf="@id/ivCall"
            android:layout_marginStart="16dp" />

        <!-- Téléphone -->
        <TextView
            android:id="@+id/tvPhone"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:text="+216 12 345 678"
            android:textSize="14sp"
            android:textColor="#757575"
            app:layout_constraintTop_toBottomOf="@id/tvName"
            app:layout_constraintStart_toEndOf="@id/tvAvatar"
            app:layout_constraintEnd_toStartOf="@id/ivCall"
            android:layout_marginStart="16dp"
            android:layout_marginTop="4dp" />

        <!-- Bouton appel -->
        <ImageView
            android:id="@+id/ivCall"
            android:layout_width="40dp"
            android:layout_height="40dp"
            android:src="@drawable/ic_call"
            android:padding="8dp"
            android:background="?attr/selectableItemBackgroundBorderless"
            android:contentDescription="Appeler"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:tint="@color/purple_500" />

    </androidx.constraintlayout.widget.ConstraintLayout>

</com.google.android.material.card.MaterialCardView>
```

---

### 6️⃣ **dialog_add_contact.xml** - Dialog ajout

**Emplacement** : `app/src/main/res/layout/dialog_add_contact.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">

    <!-- Champ Nom -->
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
        android:layout_marginBottom="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etName"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPersonName"
            android:maxLines="1" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- Champ Téléphone -->
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Téléphone"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"
        android:layout_marginBottom="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etPhone"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="phone"
            android:maxLines="1" />

    </com.google.android.material.textfield.TextInputLayout>

    <!-- Champ Email -->
    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email (optionnel)"
        style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/etEmail"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textEmailAddress"
            android:maxLines="1" />

    </com.google.android.material.textfield.TextInputLayout>

</LinearLayout>
```

---

## 🎨 Fichiers Drawable

Tous les fichiers drawable sont disponibles dans le fichier **[RESSOURCES.md](RESSOURCES.md)** :

- ✅ `circle_bg.xml` - Fond circulaire avatar
- ✅ `ic_add.xml` - Icône +
- ✅ `ic_call.xml` - Icône téléphone

---

## 📦 Dépendances Gradle

**Emplacement** : `app/build.gradle`

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
}
```

---

## 🔍 AndroidManifest.xml

**Emplacement** : `app/src/main/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tn.isitcom.contacts">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="Contacts"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Material3.Light">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
    </application>

</manifest>
```

---

## 📋 Explication détaillée

### 1. Architecture RecyclerView

**Composants** :
1. **RecyclerView** : Vue scrollable performante
2. **Adapter** : Pont entre données et vues
3. **ViewHolder** : Contient les références aux vues (pattern)
4. **LayoutManager** : Gère la disposition (Linear, Grid, Staggered)

**Avantages vs ListView** :
- Recyclage automatique des vues (mémoire optimisée)
- Animations intégrées
- Layouts flexibles

### 2. Pattern ViewHolder

**Sans ViewHolder** (mauvais) :
```java
// findViewById() appelé à chaque scroll = LENT
TextView tv = convertView.findViewById(R.id.tvName);
```

**Avec ViewHolder** (bon) :
```java
// findViewById() appelé une seule fois
static class ViewHolder {
    TextView tvName;
    ViewHolder(View view) {
        tvName = view.findViewById(R.id.tvName);
    }
}
```

### 3. Gestion des clics

**Interface callback** :
```java
public interface OnContactClickListener {
    void onCallClick(Contact contact);
    void onLongClick(Contact contact, int position);
}
```

**Implémentation dans Activity** :
```java
public class MainActivity implements OnContactClickListener {
    @Override
    public void onCallClick(Contact contact) { ... }
}
```

### 4. Recherche en temps réel

**Filtrage de liste** :
```java
public void filter(String query) {
    contactsFiltered.clear();
    if (query.isEmpty()) {
        contactsFiltered.addAll(contacts);
    } else {
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(query.toLowerCase())) {
                contactsFiltered.add(c);
            }
        }
    }
    notifyDataSetChanged();
}
```

### 5. Intent implicite (appel téléphonique)

```java
Intent callIntent = new Intent(Intent.ACTION_DIAL);
callIntent.setData(Uri.parse("tel:+21612345678"));
startActivity(callIntent);
```

**ACTION_DIAL** : Ouvre le dialer (ne nécessite pas de permission)  
**ACTION_CALL** : Appelle directement (permission CALL_PHONE requise)  

---

## 🧪 Tests à effectuer

### Test 1 : Affichage liste
1. Lancer l'app
2. **Attendu** : 5 contacts affichés avec avatars colorés

### Test 2 : Ajout contact
1. Cliquer sur FAB (+)
2. Remplir formulaire
3. **Attendu** : Nouveau contact en bas de liste

### Test 3 : Recherche
1. Taper "Ali" dans SearchView
2. **Attendu** : Seul "Alice Martin" affiché

### Test 4 : Appel
1. Cliquer sur icône téléphone
2. **Attendu** : Ouverture du dialer avec numéro pré-rempli

### Test 5 : Suppression
1. Long click sur un contact
2. Confirmer suppression
3. **Attendu** : Contact disparaît avec animation

---

## 💡 Améliorations possibles

1. **Tri** : Ordre alphabétique avec sections (A, B, C...)
2. **Swipe to delete** : ItemTouchHelper
3. **Photos** : Vraies images au lieu d'initiales
4. **Édition** : Modifier un contact existant
5. **Stockage** : Room Database pour persistance
6. **Synchronisation** : Contacts du téléphone (ContentProvider)
7. **Export** : Sauvegarder en vCard

---

## 📚 Concepts Android utilisés

✅ **RecyclerView** : Liste performante et scrollable  
✅ **Adapter Pattern** : Lien données-vues  
✅ **ViewHolder Pattern** : Recyclage des vues  
✅ **SearchView** : Recherche en temps réel  
✅ **FloatingActionButton** : Action principale  
✅ **AlertDialog** : Dialogues de confirmation  
✅ **Intent implicite** : ACTION_DIAL  
✅ **notifyDataSetChanged()** : Actualisation de liste  
✅ **Material Design 3** : TextInputLayout, Cards  

---

## ✅ Checklist avant exécution

- [ ] Tous les fichiers Java copiés
- [ ] Tous les layouts XML créés
- [ ] Fichiers drawable ajoutés (voir RESSOURCES.md)
- [ ] Dépendances Gradle configurées
- [ ] AndroidManifest.xml correct
- [ ] Package name : `tn.isitcom.contacts`
- [ ] Sync Gradle réussi
- [ ] Aucune erreur de compilation

---

## 🚀 Exécution

1. Créer nouveau projet Android Studio
   - Empty Views Activity
   - Package : `tn.isitcom.contacts`
   - Min SDK : API 24

2. Copier tous les fichiers selon la structure

3. Ajouter les dépendances dans `build.gradle`

4. Sync Gradle

5. Run sur émulateur ou appareil

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 6 : RecyclerView et listes dynamiques
