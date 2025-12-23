# TD07 : Material Design et thèmes

## 🎯 Objectifs

- Appliquer Material Design.
- Personnaliser thèmes et couleurs.
- Implémenter le Dark Mode.

---

## Partie 1 : Refonte d'une application existante (60 min)

### Consignes

Reprendre l'application du TD05 (gestion d'étudiants) et la moderniser :

1. **Remplacer les composants** :
   - `EditText` → `TextInputLayout` + `TextInputEditText`.
   - Boutons standards → `MaterialButton`.
   - Layout simple → `MaterialCardView`.

2. **Ajouter un FAB** pour l'ajout.

3. **Snackbar** au lieu de Toast pour les confirmations.

4. **BottomAppBar** avec menu.

### Exemple : Remplacement EditText

**Avant** :
```xml
<EditText
    android:id="@+id/editName"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Nom" />
```

**Après** :
```xml
<com.google.android.material.textfield.TextInputLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="Nom"
    style="@style/Widget.Material3.TextInputLayout.OutlinedBox">

    <com.google.android.material.textfield.TextInputEditText
        android:id="@+id/editName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</com.google.android.material.textfield.TextInputLayout>
```

---

## Partie 2 : Thème personnalisé (30 min)

### Consignes

1. Définir une palette de couleurs personnalisée dans `colors.xml`.
2. Créer un thème dans `themes.xml`.
3. Appliquer à l'application.

### Exemple : themes.xml

```xml
<resources>
    <style name="AppTheme" parent="Theme.Material3.DayNight">
        <item name="colorPrimary">@color/teal_500</item>
        <item name="colorPrimaryVariant">@color/teal_700</item>
        <item name="colorOnPrimary">@color/white</item>
        <item name="colorSecondary">@color/orange_500</item>
        <item name="colorOnSecondary">@color/black</item>
        <item name="android:statusBarColor">@color/teal_700</item>
    </style>
</resources>
```

---

## Partie 3 : Dark Mode (30 min)

### Consignes

1. Créer `res/values-night/themes.xml`.
2. Définir les couleurs pour le mode sombre.
3. Ajouter un switch dans les paramètres pour basculer.
4. Sauvegarder la préférence avec `SharedPreferences`.

### Code exemple : SettingsFragment.java

```java
public class SettingsFragment extends Fragment {

    private SwitchMaterial switchDarkMode;
    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = requireActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        switchDarkMode = view.findViewById(R.id.switchDarkMode);

        // Charger la préférence
        boolean isDarkMode = preferences.getBoolean("dark_mode", false);
        switchDarkMode.setChecked(isDarkMode);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("dark_mode", isChecked).apply();
            
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}
```

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Composants Material | 6 |
| Thème personnalisé | 6 |
| Dark Mode | 6 |
| Interface soignée | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
