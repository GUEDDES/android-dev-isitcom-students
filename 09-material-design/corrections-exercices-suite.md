# Corrections - Module 9 (Suite)

## Exercice 7 : Snackbar

### SnackbarActivity.java

```java
package tn.isitcom.materialdesign;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SnackbarActivity extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        coordinatorLayout = findViewById(R.id.coordinator_layout);
        fab = findViewById(R.id.fab);

        Button btnSimple = findViewById(R.id.btn_simple);
        Button btnAction = findViewById(R.id.btn_action);
        Button btnSuccess = findViewById(R.id.btn_success);
        Button btnError = findViewById(R.id.btn_error);
        Button btnIndefinite = findViewById(R.id.btn_indefinite);

        // 1. Snackbar simple
        btnSimple.setOnClickListener(v -> {
            Snackbar.make(coordinatorLayout, "Message de confirmation", Snackbar.LENGTH_SHORT)
                .show();
        });

        // 2. Snackbar avec action
        btnAction.setOnClickListener(v -> {
            Snackbar.make(coordinatorLayout, "Elément supprimé", Snackbar.LENGTH_LONG)
                .setAction("ANNULER", view -> {
                    Snackbar.make(coordinatorLayout, "Suppression annulée", Snackbar.LENGTH_SHORT)
                        .show();
                })
                .setActionTextColor(Color.YELLOW)
                .show();
        });

        // 3. Snackbar succès (vert)
        btnSuccess.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, 
                "✓ Opération réussie", Snackbar.LENGTH_SHORT);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#4CAF50")); // Vert
            snackbar.show();
        });

        // 4. Snackbar erreur (rouge)
        btnError.setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(coordinatorLayout, 
                "✕ Erreur de connexion", Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(Color.parseColor("#F44336")); // Rouge
            snackbar.show();
        });

        // 5. Snackbar INDEFINITE (reste jusqu'à action)
        btnIndefinite.setOnClickListener(v -> {
            Snackbar.make(coordinatorLayout, "Connexion en cours...", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", view -> {})
                .setAnchorView(fab) // Positionner au-dessus du FAB
                .show();
        });

        // FAB avec Snackbar
        fab.setOnClickListener(v -> {
            Snackbar.make(coordinatorLayout, "Action FAB", Snackbar.LENGTH_SHORT)
                .setAnchorView(fab)
                .show();
        });
    }
}
```

### activity_snackbar.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.coordinatorlayout.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/coordinator_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <Button
                android:id="@+id/btn_simple"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Snackbar Simple"
                android:layout_marginBottom="8dp" />

            <Button
                android:id="@+id/btn_action"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Snackbar avec Action"
                android:layout_marginBottom="8dp" />

            <Button
                android:id="@+id/btn_success"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Snackbar Succès (Vert)"
                android:layout_marginBottom="8dp" />

            <Button
                android:id="@+id/btn_error"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Snackbar Erreur (Rouge)"
                android:layout_marginBottom="8dp" />

            <Button
                android:id="@+id/btn_indefinite"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Snackbar INDEFINITE" />

        </LinearLayout>
    </ScrollView>

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="16dp"
        app:srcCompat="@drawable/ic_add" />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

---

## Exercice 8 : Text Fields

### activity_text_fields.xml

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
            android:text="Formulaire d'inscription"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_marginBottom="24dp" />

        <!-- Champ Nom -->
        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/til_name"
            style="@style/Widget.Material3.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Nom complet"
            app:startIconDrawable="@drawable/ic_person"
            app:helperText="Nom et prénom"
            app:helperTextEnabled="true"
            android:layout_marginBottom="16dp">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_name"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textPersonName" />

        </com.google.android.material.textfield.TextInputLayout>

        <!-- Champ Email -->
        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/til_email"
            style="@style/Widget.Material3.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Email"
            app:startIconDrawable="@drawable/ic_email"
            app:errorEnabled="true"
            android:layout_marginBottom="16dp">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_email"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textEmailAddress" />

        </com.google.android.material.textfield.TextInputLayout>

        <!-- Champ Mot de passe -->
        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/til_password"
            style="@style/Widget.Material3.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Mot de passe"
            app:startIconDrawable="@drawable/ic_lock"
            app:endIconMode="password_toggle"
            app:errorEnabled="true"
            android:layout_marginBottom="16dp">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_password"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textPassword" />

        </com.google.android.material.textfield.TextInputLayout>

        <!-- Champ Description -->
        <com.google.android.material.textfield.TextInputLayout
            android:id="@+id/til_description"
            style="@style/Widget.Material3.TextInputLayout.OutlinedBox"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Description"
            app:counterEnabled="true"
            app:counterMaxLength="200"
            android:layout_marginBottom="24dp">

            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_description"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textMultiLine"
                android:minLines="3"
                android:maxLength="200"
                android:gravity="top" />

        </com.google.android.material.textfield.TextInputLayout>

        <Button
            android:id="@+id/btn_validate"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Valider" />

    </LinearLayout>
</ScrollView>
```

### TextFieldsActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TextFieldsActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPassword, tilDescription;
    private TextInputEditText etName, etEmail, etPassword, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_fields);

        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        tilDescription = findViewById(R.id.til_description);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etDescription = findViewById(R.id.et_description);

        Button btnValidate = findViewById(R.id.btn_validate);

        // Validation en temps réel pour l'email
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isValidEmail(s.toString())) {
                    tilEmail.setError("Format email invalide");
                } else {
                    tilEmail.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Validation en temps réel pour le mot de passe
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() < 6) {
                    tilPassword.setError("Minimum 6 caractères");
                } else {
                    tilPassword.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Validation au clic sur Valider
        btnValidate.setOnClickListener(v -> {
            if (validateForm()) {
                Toast.makeText(this, "Formulaire valide !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateForm() {
        boolean isValid = true;

        // Validation nom
        String name = etName.getText().toString().trim();
        if (name.isEmpty()) {
            tilName.setError("Nom requis");
            isValid = false;
        } else {
            tilName.setError(null);
        }

        // Validation email
        String email = etEmail.getText().toString().trim();
        if (!isValidEmail(email)) {
            tilEmail.setError("Email invalide");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }

        // Validation mot de passe
        String password = etPassword.getText().toString();
        if (password.length() < 6) {
            tilPassword.setError("Minimum 6 caractères");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }

        return isValid;
    }

    private boolean isValidEmail(String email) {
        return !email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
```

---

## Exercice 9 : Dialogs

### DialogsActivity.java

```java
package tn.isitcom.materialdesign;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.Calendar;

public class DialogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        Button btnSimple = findViewById(R.id.btn_simple_dialog);
        Button btnList = findViewById(R.id.btn_list_dialog);
        Button btnRadio = findViewById(R.id.btn_radio_dialog);
        Button btnCustom = findViewById(R.id.btn_custom_dialog);
        Button btnDate = findViewById(R.id.btn_date_picker);

        // 1. Dialog simple (confirmation)
        btnSimple.setOnClickListener(v -> {
            new MaterialAlertDialogBuilder(this)
                .setTitle("Confirmer la suppression")
                .setMessage("Voulez-vous vraiment supprimer cet élément ?")
                .setPositiveButton("Supprimer", (dialog, which) -> {
                    Toast.makeText(this, "Elément supprimé", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Annuler", null)
                .show();
        });

        // 2. Dialog avec liste
        btnList.setOnClickListener(v -> {
            String[] options = {"Option 1", "Option 2", "Option 3", "Option 4"};
            
            new MaterialAlertDialogBuilder(this)
                .setTitle("Choisir une option")
                .setItems(options, (dialog, which) -> {
                    Toast.makeText(this, "Sélectionné: " + options[which], Toast.LENGTH_SHORT).show();
                })
                .show();
        });

        // 3. Dialog avec radio buttons
        btnRadio.setOnClickListener(v -> {
            String[] choices = {"Petite", "Moyenne", "Grande"};
            int checkedItem = 1; // Moyenne par défaut
            
            new MaterialAlertDialogBuilder(this)
                .setTitle("Taille")
                .setSingleChoiceItems(choices, checkedItem, (dialog, which) -> {
                    // L'utilisateur a sélectionné un item
                })
                .setPositiveButton("OK", (dialog, which) -> {
                    // Récupérer la sélection
                })
                .setNegativeButton("Annuler", null)
                .show();
        });

        // 4. Dialog personnalisé (formulaire)
        btnCustom.setOnClickListener(v -> {
            View dialogView = LayoutInflater.from(this)
                .inflate(R.layout.dialog_custom, null);
            
            EditText etTitle = dialogView.findViewById(R.id.et_title);
            EditText etDescription = dialogView.findViewById(R.id.et_description);
            
            new MaterialAlertDialogBuilder(this)
                .setTitle("Ajouter un élément")
                .setView(dialogView)
                .setPositiveButton("Ajouter", (dialog, which) -> {
                    String title = etTitle.getText().toString();
                    String description = etDescription.getText().toString();
                    Toast.makeText(this, "Ajouté: " + title, Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Annuler", null)
                .show();
        });

        // 5. DatePicker Material
        btnDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    Toast.makeText(this, "Date: " + date, Toast.LENGTH_SHORT).show();
                },
                year, month, day
            );
            datePickerDialog.show();
        });
    }
}
```

### res/layout/dialog_custom.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:padding="16dp">

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Titre"
        android:layout_marginBottom="16dp">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </com.google.android.material.textfield.TextInputLayout>

    <com.google.android.material.textfield.TextInputLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Description">

        <com.google.android.material.textfield.TextInputEditText
            android:id="@+id/et_description"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:minLines="3" />

    </com.google.android.material.textfield.TextInputLayout>

</LinearLayout>
```

---

## Exercice 10 : Animations

### Shared Element Transition

### MainActivity.java

```java
package tn.isitcom.materialdesign;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.image_view);
        imageView.setTransitionName("image_transition");

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                this,
                imageView,
                "image_transition"
            );
            
            startActivity(intent, options.toBundle());
        });
    }
}
```

### DetailActivity.java

```java
package tn.isitcom.materialdesign;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detail_image);
        imageView.setTransitionName("image_transition");
    }
}
```

### Animations RecyclerView

```java
public class AnimatedAdapter extends RecyclerView.Adapter<AnimatedAdapter.ViewHolder> {

    private int lastPosition = -1;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data
        
        // Animation
        setAnimation(holder.itemView, position);
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(
                viewToAnimate.getContext(), 
                android.R.anim.slide_in_left
            );
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
```

### res/anim/slide_up.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate
        android:fromYDelta="100%"
        android:toYDelta="0%"
        android:duration="300" />
    <alpha
        android:fromAlpha="0.0"
        android:toAlpha="1.0"
        android:duration="300" />
</set>
```

### FAB Animation

```java
private void animateFab() {
    fab.animate()
        .scaleX(0f)
        .scaleY(0f)
        .setDuration(300)
        .withEndAction(() -> {
            fab.setImageResource(R.drawable.ic_check);
            fab.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(300)
                .start();
        })
        .start();
}
```

---

## Mini-projet : Application Profil Material

### Structure du projet

```
app/
├── java/
│   └── tn.isitcom.profileapp/
│       ├── MainActivity.java
│       ├── EditProfileActivity.java
│       ├── SettingsFragment.java
│       └── ShareBottomSheet.java
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── activity_edit_profile.xml
    │   ├── fragment_settings.xml
    │   └── bottom_sheet_share.xml
    ├── menu/
    │   └── drawer_menu.xml
    └── navigation/
        └── nav_graph.xml
```

### activity_main.xml (Profil complet)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.coordinatorlayout.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <com.google.android.material.appbar.CollapsingToolbarLayout
                android:layout_width="match_parent"
                android:layout_height="250dp"
                app:layout_scrollFlags="scroll|exitUntilCollapsed"
                app:contentScrim="?attr/colorPrimary">

                <ImageView
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:scaleType="centerCrop"
                    android:src="@drawable/cover_photo"
                    app:layout_collapseMode="parallax" />

                <com.google.android.material.appbar.MaterialToolbar
                    android:id="@+id/toolbar"
                    android:layout_width="match_parent"
                    android:layout_height="?attr/actionBarSize"
                    app:layout_collapseMode="pin"
                    app:navigationIcon="@drawable/ic_menu" />

            </com.google.android.material.appbar.CollapsingToolbarLayout>

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

                <!-- Photo de profil -->
                <com.google.android.material.card.MaterialCardView
                    android:layout_width="120dp"
                    android:layout_height="120dp"
                    android:layout_gravity="center_horizontal"
                    android:layout_marginTop="-60dp"
                    app:cardCornerRadius="60dp"
                    app:cardElevation="8dp">

                    <ImageView
                        android:id="@+id/profile_image"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:scaleType="centerCrop"
                        android:src="@drawable/profile_picture" />

                </com.google.android.material.card.MaterialCardView>

                <!-- Nom et bio -->
                <TextView
                    android:id="@+id/tv_name"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Ahmed Ben Ali"
                    android:textSize="24sp"
                    android:textStyle="bold"
                    android:layout_gravity="center_horizontal"
                    android:layout_marginTop="16dp" />

                <TextView
                    android:id="@+id/tv_bio"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Développeur Android | ISITCOM"
                    android:textSize="14sp"
                    android:layout_gravity="center_horizontal"
                    android:layout_marginTop="8dp"
                    android:textColor="?attr/colorOnSurfaceVariant" />

                <!-- Statistiques -->
                <LinearLayout
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:orientation="horizontal"
                    android:layout_marginTop="24dp">

                    <com.google.android.material.card.MaterialCardView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:layout_margin="4dp"
                        app:cardElevation="2dp"
                        app:cardCornerRadius="12dp">

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:orientation="vertical"
                            android:padding="16dp"
                            android:gravity="center">

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="128"
                                android:textSize="24sp"
                                android:textStyle="bold" />

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="Posts"
                                android:textSize="12sp" />

                        </LinearLayout>
                    </com.google.android.material.card.MaterialCardView>

                    <com.google.android.material.card.MaterialCardView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:layout_margin="4dp"
                        app:cardElevation="2dp"
                        app:cardCornerRadius="12dp">

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:orientation="vertical"
                            android:padding="16dp"
                            android:gravity="center">

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="1.2K"
                                android:textSize="24sp"
                                android:textStyle="bold" />

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="Followers"
                                android:textSize="12sp" />

                        </LinearLayout>
                    </com.google.android.material.card.MaterialCardView>

                    <com.google.android.material.card.MaterialCardView
                        android:layout_width="0dp"
                        android:layout_height="wrap_content"
                        android:layout_weight="1"
                        android:layout_margin="4dp"
                        app:cardElevation="2dp"
                        app:cardCornerRadius="12dp">

                        <LinearLayout
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:orientation="vertical"
                            android:padding="16dp"
                            android:gravity="center">

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="456"
                                android:textSize="24sp"
                                android:textStyle="bold" />

                            <TextView
                                android:layout_width="wrap_content"
                                android:layout_height="wrap_content"
                                android:text="Following"
                                android:textSize="12sp" />

                        </LinearLayout>
                    </com.google.android.material.card.MaterialCardView>

                </LinearLayout>

            </LinearLayout>
        </androidx.core.widget.NestedScrollView>

        <com.google.android.material.floatingactionbutton.FloatingActionButton
            android:id="@+id/fab_edit"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="bottom|end"
            android:layout_margin="16dp"
            app:srcCompat="@drawable/ic_edit" />

    </androidx.coordinatorlayout.widget.CoordinatorLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/drawer_menu" />

</androidx.drawerlayout.widget.DrawerLayout>
```

### Grille d'évaluation détaillée

| Critère | Points | Détails |
|---------|--------|----------|
| **Thème Material 3** | 3 | colors.xml, themes.xml clair/sombre |
| **Navigation Drawer** | 3 | Menu, header, navigation |
| **Cards stylisées** | 2 | Elevation, coins, ripple |
| **FAB + Snackbar** | 2 | Position, anchor, actions |
| **TextFields validation** | 3 | Icônes, erreurs, compteur |
| **Bottom Sheet** | 2 | Modal, formulaire |
| **Thème clair/sombre** | 2 | Switch dynamique |
| **Animations** | 2 | Transitions, FAB, RecyclerView |
| **Code propre** | 3 | Packages, nommage, commentaires |
| **Total** | **/22** | |

---

👨‍🏫 **Corrections Module 9 - Complètes** | ISITCOM 2025-2026
