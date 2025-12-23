# Module 9 : Exercices pratiques

## Exercice 1 : Conversion vers Material Components

**Objectif** : Transformer une interface classique en Material Design.

### Consignes

1. Prendre une Activity existante avec :
   - Button standard
   - EditText standard
   - TextView pour le titre

2. Remplacer par :
   - `MaterialButton`
   - `TextInputLayout` + `TextInputEditText`
   - `MaterialToolbar`

3. Ajouter :
   - Élévation sur les cartes
   - Coins arrondis
   - Icônes sur les boutons

---

## Exercice 2 : Thème personnalisé

**Objectif** : Créer un thème aux couleurs de votre choix.

### Étape 1 : Définir les couleurs

Dans `res/values/colors.xml` :

```xml
<resources>
    <color name="primary">#6200EE</color>
    <color name="primary_variant">#3700B3</color>
    <color name="secondary">#03DAC6</color>
    <color name="secondary_variant">#018786</color>
    <color name="background">#FFFFFF</color>
    <color name="surface">#FFFFFF</color>
    <color name="error">#B00020</color>
    <color name="on_primary">#FFFFFF</color>
    <color name="on_secondary">#000000</color>
    <color name="on_background">#000000</color>
    <color name="on_surface">#000000</color>
    <color name="on_error">#FFFFFF</color>
</resources>
```

### Étape 2 : Créer le thème

Dans `res/values/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.Light">
        <item name="colorPrimary">@color/primary</item>
        <item name="colorPrimaryVariant">@color/primary_variant</item>
        <item name="colorSecondary">@color/secondary</item>
        <item name="colorSecondaryVariant">@color/secondary_variant</item>
        <item name="android:colorBackground">@color/background</item>
        <item name="colorSurface">@color/surface</item>
        <item name="colorError">@color/error</item>
        <item name="colorOnPrimary">@color/on_primary</item>
        <item name="colorOnSecondary">@color/on_secondary</item>
        <item name="colorOnBackground">@color/on_background</item>
        <item name="colorOnSurface">@color/on_surface</item>
        <item name="colorOnError">@color/on_error</item>
    </style>
</resources>
```

### Étape 3 : Tester

- Changer les couleurs selon vos préférences
- Vérifier que tous les éléments s'adaptent
- Tester la lisibilité (contraste suffisant)

---

## Exercice 3 : Dark Mode

**Objectif** : Implémenter le support du mode sombre.

### Étape 1 : Créer values-night

1. Clic droit sur `res` → New → Android Resource Directory
2. Resource type : `values`
3. Qualifiers : Night Mode → `Night`
4. Créer `themes.xml` dans ce dossier

### Étape 2 : Définir couleurs sombres

`res/values-night/colors.xml` :

```xml
<resources>
    <color name="primary">#BB86FC</color>
    <color name="primary_variant">#3700B3</color>
    <color name="secondary">#03DAC6</color>
    <color name="background">#121212</color>
    <color name="surface">#121212</color>
    <color name="on_primary">#000000</color>
    <color name="on_secondary">#000000</color>
    <color name="on_background">#FFFFFF</color>
    <color name="on_surface">#FFFFFF</color>
</resources>
```

### Étape 3 : Thème sombre

`res/values-night/themes.xml` :

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.Dark">
        <!-- Mêmes attributs que le thème clair -->
    </style>
</resources>
```

### Test

- Sur l'émulateur : Settings → Display → Dark theme
- Vérifier que l'app s'adapte automatiquement

---

## Exercice 4 : FAB avec action

**Objectif** : Ajouter un FloatingActionButton fonctionnel.

### Layout

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:src="@drawable/ic_add"
        app:tint="@color/white" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### Code Java

```java
FloatingActionButton fab = findViewById(R.id.fab);
fab.setOnClickListener(v -> {
    // Action : ouvrir dialogue ou nouvelle Activity
    Intent intent = new Intent(this, AddItemActivity.class);
    startActivity(intent);
});
```

---

## Exercice 5 : CardView avec élévation

**Objectif** : Créer une liste de cartes Material.

### Layout item (item_card.xml)

```xml
<com.google.android.material.card.MaterialCardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardElevation="4dp"
    app:cardCornerRadius="12dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/textTitle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:textSize="18sp"
            android:textStyle="bold"
            android:textColor="?attr/colorOnSurface" />

        <TextView
            android:id="@+id/textDescription"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="8dp"
            android:textColor="?attr/colorOnSurfaceVariant" />

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

---

## Exercice 6 : Snackbar avec action

**Objectif** : Afficher un Snackbar avec bouton d'annulation.

```java
public void deleteItem(Item item, int position) {
    // Supprimer l'item
    items.remove(position);
    adapter.notifyItemRemoved(position);
    
    // Afficher Snackbar avec option Annuler
    Snackbar.make(recyclerView, "Item supprimé", Snackbar.LENGTH_LONG)
        .setAction("Annuler", v -> {
            // Restaurer l'item
            items.add(position, item);
            adapter.notifyItemInserted(position);
        })
        .show();
}
```

---

## Exercice 7 : Toolbar personnalisée

**Objectif** : Ajouter une Toolbar avec menu.

### Layout

```xml
<com.google.android.material.appbar.AppBarLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <com.google.android.material.appbar.MaterialToolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        app:title="Mon App"
        app:titleTextColor="@color/white"
        app:menu="@menu/menu_main" />

</com.google.android.material.appbar.AppBarLayout>
```

### Menu (res/menu/menu_main.xml)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <item
        android:id="@+id/action_search"
        android:icon="@drawable/ic_search"
        android:title="Rechercher"
        app:showAsAction="ifRoom" />

    <item
        android:id="@+id/action_settings"
        android:title="Paramètres"
        app:showAsAction="never" />

</menu>
```

### Code Java

```java
MaterialToolbar toolbar = findViewById(R.id.toolbar);
setSupportActionBar(toolbar);

toolbar.setOnMenuItemClickListener(item -> {
    if (item.getItemId() == R.id.action_search) {
        // Action recherche
        return true;
    } else if (item.getItemId() == R.id.action_settings) {
        // Ouvrir paramètres
        return true;
    }
    return false;
});
```

---

## Mini-projet : Profil utilisateur Material

### Objectif

Créer un écran de profil utilisateur avec Material Design.

### Fonctionnalités

1. **Toolbar** avec bouton retour
2. **Image de profil** (CircleImageView)
3. **Informations** dans des CardView :
   - Nom, email, téléphone
4. **FAB** pour éditer le profil
5. **Boutons Material** pour actions (Appeler, Envoyer email)
6. **Support dark mode**

### Grille d'évaluation

| Critère | Points |
|---------|--------|
| Toolbar configurée | 2 |
| Cartes Material utilisées | 3 |
| Image profil affichée | 2 |
| FAB fonctionnel | 2 |
| Boutons Material avec icônes | 3 |
| Dark mode supporté | 3 |
| Interface soignée | 3 |
| Code propre | 2 |

**Total** : /20

---

## Ressources

- [Material Design 3](https://m3.material.io/)
- [Material Components Catalog](https://material.io/components)
- [Color Tool](https://material.io/resources/color/)

---

👨‍🏫 **Module 9 - Material Design** | ISITCOM 2025-2026
