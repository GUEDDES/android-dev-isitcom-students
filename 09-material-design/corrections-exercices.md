# Corrections - Exercices Module 9

## Exercice 1 : Thème Material 3

### build.gradle (Module: app)

```gradle
dependencies {
    implementation 'com.google.android.material:material:1.11.0'
}
```

### res/values/themes.xml

```xml
<resources>
    <!-- Thème clair -->
    <style name="Theme.MyApp" parent="Theme.Material3.Light.NoActionBar">
        <!-- Couleurs primaires -->
        <item name="colorPrimary">@color/md_theme_light_primary</item>
        <item name="colorOnPrimary">@color/md_theme_light_onPrimary</item>
        <item name="colorPrimaryContainer">@color/md_theme_light_primaryContainer</item>
        
        <!-- Couleurs secondaires -->
        <item name="colorSecondary">@color/md_theme_light_secondary</item>
        <item name="colorOnSecondary">@color/md_theme_light_onSecondary</item>
        
        <!-- Couleurs tertiaires -->
        <item name="colorTertiary">@color/md_theme_light_tertiary</item>
        
        <!-- Couleurs de fond -->
        <item name="android:colorBackground">@color/md_theme_light_background</item>
        <item name="colorSurface">@color/md_theme_light_surface</item>
        
        <!-- Couleurs d'erreur -->
        <item name="colorError">@color/md_theme_light_error</item>
    </style>
</resources>
```

### res/values-night/themes.xml

```xml
<resources>
    <!-- Thème sombre -->
    <style name="Theme.MyApp" parent="Theme.Material3.Dark.NoActionBar">
        <item name="colorPrimary">@color/md_theme_dark_primary</item>
        <item name="colorOnPrimary">@color/md_theme_dark_onPrimary</item>
        <item name="colorPrimaryContainer">@color/md_theme_dark_primaryContainer</item>
        
        <item name="colorSecondary">@color/md_theme_dark_secondary</item>
        <item name="colorOnSecondary">@color/md_theme_dark_onSecondary</item>
        
        <item name="colorTertiary">@color/md_theme_dark_tertiary</item>
        
        <item name="android:colorBackground">@color/md_theme_dark_background</item>
        <item name="colorSurface">@color/md_theme_dark_surface</item>
        
        <item name="colorError">@color/md_theme_dark_error</item>
    </style>
</resources>
```

### res/values/colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Couleurs mode clair -->
    <color name="md_theme_light_primary">#6750A4</color>
    <color name="md_theme_light_onPrimary">#FFFFFF</color>
    <color name="md_theme_light_primaryContainer">#EADDFF</color>
    <color name="md_theme_light_secondary">#625B71</color>
    <color name="md_theme_light_onSecondary">#FFFFFF</color>
    <color name="md_theme_light_tertiary">#7D5260</color>
    <color name="md_theme_light_background">#FFFBFE</color>
    <color name="md_theme_light_surface">#FFFBFE</color>
    <color name="md_theme_light_error">#B3261E</color>
    
    <!-- Couleurs mode sombre -->
    <color name="md_theme_dark_primary">#D0BCFF</color>
    <color name="md_theme_dark_onPrimary">#381E72</color>
    <color name="md_theme_dark_primaryContainer">#4F378B</color>
    <color name="md_theme_dark_secondary">#CCC2DC</color>
    <color name="md_theme_dark_onSecondary">#332D41</color>
    <color name="md_theme_dark_tertiary">#EFB8C8</color>
    <color name="md_theme_dark_background">#1C1B1F</color>
    <color name="md_theme_dark_surface">#1C1B1F</color>
    <color name="md_theme_dark_error">#F2B8B5</color>
</resources>
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.google.android.material.appbar.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <com.google.android.material.appbar.MaterialToolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            app:title="Material Design 3"
            app:titleTextColor="?attr/colorOnPrimary" />

    </com.google.android.material.appbar.AppBarLayout>

    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="16dp">

            <Button
                android:id="@+id/btn_primary"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Bouton Primaire"
                android:layout_marginBottom="16dp" />

            <Button
                android:id="@+id/btn_secondary"
                style="@style/Widget.Material3.Button.TonalButton"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Bouton Secondaire"
                android:layout_marginBottom="24dp" />

            <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:cardElevation="4dp"
                app:cardCornerRadius="12dp"
                android:layout_marginBottom="16dp">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical"
                    android:padding="16dp">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Carte 1"
                        android:textSize="20sp"
                        android:textStyle="bold" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Contenu de la première carte"
                        android:layout_marginTop="8dp" />

                </LinearLayout>
            </com.google.android.material.card.MaterialCardView>

            <com.google.android.material.card.MaterialCardView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:cardElevation="4dp"
                app:cardCornerRadius="12dp">

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical"
                    android:padding="16dp">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Carte 2"
                        android:textSize="20sp"
                        android:textStyle="bold" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Contenu de la deuxième carte"
                        android:layout_marginTop="8dp" />

                </LinearLayout>
            </com.google.android.material.card.MaterialCardView>

        </LinearLayout>
    </androidx.core.widget.NestedScrollView>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### MainActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnPrimary = findViewById(R.id.btn_primary);
        Button btnSecondary = findViewById(R.id.btn_secondary);

        btnPrimary.setOnClickListener(v -> 
            Toast.makeText(this, "Bouton primaire cliqué", Toast.LENGTH_SHORT).show()
        );

        btnSecondary.setOnClickListener(v -> 
            Toast.makeText(this, "Bouton secondaire cliqué", Toast.LENGTH_SHORT).show()
        );

        // Basculer entre mode clair et sombre (optionnel)
        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_theme) {
                toggleTheme();
                return true;
            }
            return false;
        });
    }

    private void toggleTheme() {
        int currentMode = AppCompatDelegate.getDefaultNightMode();
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}
```

---

## Exercice 2 : Cards et Elevation

### activity_cards.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Carte 1 : Profil -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardElevation="2dp"
            app:cardCornerRadius="12dp"
            android:layout_marginBottom="16dp"
            android:clickable="true"
            android:focusable="true"
            app:rippleColor="?attr/colorPrimary">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:padding="16dp">

                <ImageView
                    android:layout_width="64dp"
                    android:layout_height="64dp"
                    android:src="@drawable/ic_person"
                    android:background="@drawable/circle_background"
                    android:padding="12dp" />

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="vertical"
                    android:layout_marginStart="16dp">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Ahmed Ben Ali"
                        android:textSize="18sp"
                        android:textStyle="bold" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="ahmed@isitcom.tn"
                        android:textSize="14sp"
                        android:textColor="?attr/colorOnSurfaceVariant"
                        android:layout_marginTop="4dp" />

                </LinearLayout>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>

        <!-- Carte 2 : Actualité avec image -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardElevation="4dp"
            app:cardCornerRadius="12dp"
            android:layout_marginBottom="16dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="180dp"
                    android:scaleType="centerCrop"
                    android:src="@drawable/sample_image" />

                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="vertical"
                    android:padding="16dp">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Nouvelle actualité"
                        android:textSize="20sp"
                        android:textStyle="bold" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Description de l'actualité avec plus de détails sur le contenu."
                        android:textSize="14sp"
                        android:layout_marginTop="8dp"
                        android:maxLines="3"
                        android:ellipsize="end" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Il y a 2 heures"
                        android:textSize="12sp"
                        android:textColor="?attr/colorOnSurfaceVariant"
                        android:layout_marginTop="8dp" />

                </LinearLayout>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>

        <!-- Carte 3 : Statistique -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardElevation="8dp"
            app:cardCornerRadius="12dp"
            app:cardBackgroundColor="?attr/colorPrimaryContainer">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="horizontal"
                android:padding="24dp"
                android:gravity="center_vertical">

                <ImageView
                    android:layout_width="48dp"
                    android:layout_height="48dp"
                    android:src="@drawable/ic_star"
                    app:tint="?attr/colorPrimary" />

                <LinearLayout
                    android:layout_width="0dp"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="vertical"
                    android:layout_marginStart="16dp">

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="1,234"
                        android:textSize="32sp"
                        android:textStyle="bold"
                        android:textColor="?attr/colorPrimary" />

                    <TextView
                        android:layout_width="wrap_content"
                        android:layout_height="wrap_content"
                        android:text="Tâches complétées"
                        android:textSize="14sp" />

                </LinearLayout>
            </LinearLayout>
        </com.google.android.material.card.MaterialCardView>

    </LinearLayout>
</ScrollView>
```

### res/drawable/circle_background.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="?attr/colorPrimaryContainer" />
</shape>
```

---

## Exercice 3 : FAB

### activity_fab.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Contenu de la page"
                android:textSize="18sp" />

        </LinearLayout>
    </androidx.core.widget.NestedScrollView>

    <!-- FAB normal -->
    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab_normal"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        app:srcCompat="@drawable/ic_add" />

    <!-- Extended FAB (décommenter pour tester) -->
    <!--
    <com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
        android:id="@+id/fab_extended"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        android:text="Ajouter une tâche"
        app:icon="@drawable/ic_add" />
    -->

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

### FabActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class FabActivity extends AppCompatActivity {

    private FloatingActionButton fabNormal;
    private boolean isRotated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab);

        fabNormal = findViewById(R.id.fab_normal);

        // FAB normal avec Toast
        fabNormal.setOnClickListener(v -> {
            Toast.makeText(this, "FAB cliqué", Toast.LENGTH_SHORT).show();
            rotateFab();
        });

        // FAB avec Snackbar
        fabNormal.setOnLongClickListener(v -> {
            Snackbar.make(v, "Action effectuée avec succès", Snackbar.LENGTH_LONG)
                .setAction("Annuler", view -> {
                    Toast.makeText(this, "Action annulée", Toast.LENGTH_SHORT).show();
                })
                .setAnchorView(fabNormal) // Positionner au-dessus du FAB
                .show();
            return true;
        });

        // Extended FAB (si décommenté)
        /*
        ExtendedFloatingActionButton fabExtended = findViewById(R.id.fab_extended);
        fabExtended.setOnClickListener(v -> {
            // Action
        });
        */
    }

    private void rotateFab() {
        float fromDegrees = isRotated ? 45f : 0f;
        float toDegrees = isRotated ? 0f : 45f;
        
        RotateAnimation rotate = new RotateAnimation(
            fromDegrees, toDegrees,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        
        fabNormal.startAnimation(rotate);
        isRotated = !isRotated;
        
        // Changer l'icône
        if (isRotated) {
            fabNormal.setImageResource(R.drawable.ic_close);
        } else {
            fabNormal.setImageResource(R.drawable.ic_add);
        }
    }
}
```

---

## Exercice 4 : Bottom Sheet

### Bottom Sheet Modal

### ModalBottomSheetFragment.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputEditText;

public class ModalBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, 
                             @Nullable ViewGroup container, 
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet_modal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText etName = view.findViewById(R.id.et_name);
        TextInputEditText etEmail = view.findViewById(R.id.et_email);
        Button btnValidate = view.findViewById(R.id.btn_validate);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnValidate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            
            if (!name.isEmpty() && !email.isEmpty()) {
                Toast.makeText(getContext(), "Données validées", Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Remplir tous les champs", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> dismiss());
    }
}
```

### res/layout/bottom_sheet_modal.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Formulaire"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="16dp" />

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Nom"
        android:layout_marginBottom="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_name"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:layout_marginBottom="24dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_email"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textEmailAddress" />

    </com.google.android.material.textfield.TextInputLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <Button
            android:id="@+id/btn_cancel"
            style="@style/Widget.Material3.Button.TextButton"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Annuler" />

        <Button
            android:id="@+id/btn_validate"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Valider"
            android:layout_marginStart="8dp" />

    </LinearLayout>
</LinearLayout>
```

### MainActivity.java (ouverture Bottom Sheet)

```java
Button btnOpenBottomSheet = findViewById(R.id.btn_open_bottom_sheet);
btnOpenBottomSheet.setOnClickListener(v -> {
    ModalBottomSheetFragment bottomSheet = new ModalBottomSheetFragment();
    bottomSheet.show(getSupportFragmentManager(), "ModalBottomSheet");
});
```

---

## Exercice 5 : Navigation Drawer

### activity_main.xml (avec DrawerLayout)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Contenu principal -->
    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <com.google.android.material.appbar.MaterialToolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                app:title="Navigation Drawer"
                app:navigationIcon="@drawable/ic_menu" />

        </com.google.android.material.appbar.AppBarLayout>

        <FrameLayout
            android:id="@+id/fragment_container"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

    <!-- Navigation Drawer -->
    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/drawer_menu" />

</androidx.drawerlayout.widget.DrawerLayout>
```

### res/layout/nav_header.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="176dp"
    android:background="?attr/colorPrimaryContainer"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="bottom">

    <ImageView
        android:layout_width="64dp"
        android:layout_height="64dp"
        android:src="@drawable/ic_person"
        android:background="@drawable/circle_background" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Ahmed Ben Ali"
        android:textSize="18sp"
        android:textStyle="bold"
        android:layout_marginTop="8dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="ahmed@isitcom.tn"
        android:textSize="14sp" />

</LinearLayout>
```

### res/menu/drawer_menu.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    
    <group android:checkableBehavior="single">
        
        <item
            android:id="@+id/nav_home"
            android:icon="@drawable/ic_home"
            android:title="Accueil" />
        
        <item
            android:id="@+id/nav_profile"
            android:icon="@drawable/ic_person"
            android:title="Profil" />
        
        <item
            android:id="@+id/nav_settings"
            android:icon="@drawable/ic_settings"
            android:title="Paramètres" />
        
        <item
            android:id="@+id/nav_help"
            android:icon="@drawable/ic_help"
            android:title="Aide" />
        
    </group>
    
    <item android:title="Compte">
        <menu>
            <item
                android:id="@+id/nav_logout"
                android:icon="@drawable/ic_logout"
                android:title="Déconnexion" />
        </menu>
    </item>
    
</menu>
```

### MainActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Toggle pour ouvrir/fermer le drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Gestion des clics sur le menu
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Accueil", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profil", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Paramètres", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_help) {
                Toast.makeText(this, "Aide", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Déconnexion", Toast.LENGTH_SHORT).show();
            }
            
            drawerLayout.closeDrawers();
            return true;
        });
    }
}
```

---

## Exercice 6 : Chips

### activity_chips.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Filtres"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginBottom="8dp" />

        <!-- ChipGroup avec sélection unique -->
        <com.google.android.material.chip.ChipGroup
            android:id="@+id/chip_group_filters"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:singleSelection="true"
            android:layout_marginBottom="24dp">

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_all"
                style="@style/Widget.Material3.Chip.Filter"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Tous"
                android:checked="true" />

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_work"
                style="@style/Widget.Material3.Chip.Filter"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Travail" />

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_personal"
                style="@style/Widget.Material3.Chip.Filter"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Personnel" />

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_urgent"
                style="@style/Widget.Material3.Chip.Filter"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Urgent" />

        </com.google.android.material.chip.ChipGroup>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Tags"
            android:textSize="18sp"
            android:textStyle="bold"
            android:layout_marginBottom="8dp" />

        <!-- Chips avec fermeture -->
        <com.google.android.material.chip.ChipGroup
            android:id="@+id/chip_group_tags"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="24dp">

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_tag1"
                style="@style/Widget.Material3.Chip.Input"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Android"
                app:closeIcon="@drawable/ic_close"
                app:closeIconVisible="true" />

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_tag2"
                style="@style/Widget.Material3.Chip.Input"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Java"
                app:closeIcon="@drawable/ic_close"
                app:closeIconVisible="true" />

            <com.google.android.material.chip.Chip
                android:id="@+id/chip_tag3"
                style="@style/Widget.Material3.Chip.Input"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Material Design"
                app:closeIcon="@drawable/ic_close"
                app:closeIconVisible="true" />

        </com.google.android.material.chip.ChipGroup>

        <!-- Bouton pour ajouter un chip -->
        <Button
            android:id="@+id/btn_add_chip"
            style="@style/Widget.Material3.Button.TonalButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Ajouter un tag" />

    </LinearLayout>
</ScrollView>
```

### ChipsActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class ChipsActivity extends AppCompatActivity {

    private ChipGroup chipGroupFilters;
    private ChipGroup chipGroupTags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chips);

        chipGroupFilters = findViewById(R.id.chip_group_filters);
        chipGroupTags = findViewById(R.id.chip_group_tags);
        Button btnAddChip = findViewById(R.id.btn_add_chip);

        // Listener pour les filtres
        chipGroupFilters.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (!checkedIds.isEmpty()) {
                int checkedId = checkedIds.get(0);
                Chip chip = findViewById(checkedId);
                if (chip != null) {
                    Toast.makeText(this, "Filtre: " + chip.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Listener pour fermeture des tags
        for (int i = 0; i < chipGroupTags.getChildCount(); i++) {
            Chip chip = (Chip) chipGroupTags.getChildAt(i);
            chip.setOnCloseIconClickListener(v -> {
                chipGroupTags.removeView(v);
                Toast.makeText(this, "Tag supprimé", Toast.LENGTH_SHORT).show();
            });
        }

        // Ajouter un chip dynamiquement
        btnAddChip.setOnClickListener(v -> {
            Chip newChip = new Chip(this);
            newChip.setText("Nouveau tag");
            newChip.setCloseIconVisible(true);
            newChip.setOnCloseIconClickListener(view -> {
                chipGroupTags.removeView(view);
            });
            chipGroupTags.addView(newChip);
        });
    }
}
```

---

**Suite des corrections disponible dans le dépôt GitHub**

👨‍🏫 **Corrections Module 9 - Partie 1/2** | ISITCOM 2025-2026
