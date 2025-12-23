# Corrections - Exercices Module 5

## Exercice 1 : Logger le cycle de vie

### Solution MainActivity.java

```java
package tn.isitcom.lifecycle;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() appelé");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() appelé");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() appelé");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() appelé");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() appelé");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() appelé");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart() appelé");
    }
}
```

### Logs attendus par scénario

#### Scénario 1 : Lancement de l'app
```
D/Lifecycle: onCreate() appelé
D/Lifecycle: onStart() appelé
D/Lifecycle: onResume() appelé
```

#### Scénario 2 : Appui sur bouton Home
```
D/Lifecycle: onPause() appelé
D/Lifecycle: onStop() appelé
```
**Note :** L'app reste en mémoire, `onDestroy()` n'est pas appelé

#### Scénario 3 : Retour à l'app (depuis Recents)
```
D/Lifecycle: onRestart() appelé
D/Lifecycle: onStart() appelé
D/Lifecycle: onResume() appelé
```

#### Scénario 4 : Rotation de l'écran
```
D/Lifecycle: onPause() appelé
D/Lifecycle: onStop() appelé
D/Lifecycle: onDestroy() appelé
D/Lifecycle: onCreate() appelé
D/Lifecycle: onStart() appelé
D/Lifecycle: onResume() appelé
```
**Note :** L'Activity est détruite et recréée

#### Scénario 5 : Fermeture app (Back depuis MainActivity)
```
D/Lifecycle: onPause() appelé
D/Lifecycle: onStop() appelé
D/Lifecycle: onDestroy() appelé
```

### Schéma récapitulatif

```
        Lancement
            │
            ↓
      [onCreate()]
            │
            ↓
      [onStart()]
            │
            ↓
      [onResume()] ←──────── [onRestart()]
            │                       ↑
      (App visible)               │
            │                       │
            ↓                       │
      [onPause()]                 │
            │                       │
            ↓                       │
      [onStop()] ──────────────┘
            │
            ↓
     [onDestroy()]
            │
        Fin app
```

---

## Exercice 2 : Navigation basique

### MainActivity.java

```java
package tn.isitcom.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGoToSecond = findViewById(R.id.btn_go_to_second);
        btnGoToSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Écran principal"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_go_to_second"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aller à l'écran 2" />

</LinearLayout>
```

### SecondActivity.java

```java
package tn.isitcom.navigation;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }
}
```

### activity_second.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Vous êtes sur l'écran 2"
        android:textSize="20sp"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_back"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Retour" />

</LinearLayout>
```

### AndroidManifest.xml (extrait)

```xml
<application ...>
    <activity android:name=".MainActivity"
        android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    
    <activity android:name=".SecondActivity" />
</application>
```

---

## Exercice 3 : Passage de données

### MainActivity.java

```java
package tn.isitcom.passagedata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNom = findViewById(R.id.et_nom);
        Button btnSaluer = findViewById(R.id.btn_saluer);

        btnSaluer.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            
            if (nom.isEmpty()) {
                Toast.makeText(this, "Veuillez saisir un nom", 
                    Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, GreetingActivity.class);
            intent.putExtra("NOM", nom);
            startActivity(intent);
        });
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <EditText
        android:id="@+id/et_nom"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Entrez votre nom"
        android:inputType="textPersonName"
        android:textSize="18sp" />

    <Button
        android:id="@+id/btn_saluer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Saluer"
        android:layout_marginTop="16dp" />

</LinearLayout>
```

### GreetingActivity.java

```java
package tn.isitcom.passagedata;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GreetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greeting);

        // Récupérer les données de l'Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra("NOM");

        // Afficher le message
        TextView tvGreeting = findViewById(R.id.tv_greeting);
        tvGreeting.setText("Bonjour " + nom + " !");

        // Bouton retour
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());
    }
}
```

### activity_greeting.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:id="@+id/tv_greeting"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="@android:color/holo_blue_dark"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_back"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Retour" />

</LinearLayout>
```

### Passage de données multiples

```java
// Envoi
intent.putExtra("NOM", "Dupont");
intent.putExtra("PRENOM", "Jean");
intent.putExtra("AGE", 25);
intent.putExtra("EST_ETUDIANT", true);

// Réception
String nom = intent.getStringExtra("NOM");
String prenom = intent.getStringExtra("PRENOM");
int age = intent.getIntExtra("AGE", 0); // 0 = valeur par défaut
boolean estEtudiant = intent.getBooleanExtra("EST_ETUDIANT", false);
```

---

## Exercice 4 : Intent implicite

### MainActivity.java

```java
package tn.isitcom.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWeb = findViewById(R.id.btn_web);
        Button btnCall = findViewById(R.id.btn_call);
        Button btnEmail = findViewById(R.id.btn_email);
        Button btnShare = findViewById(R.id.btn_share);

        // 1. Ouvrir site web
        btnWeb.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://isitcom.rnu.tn"));
            startActivity(intent);
        });

        // 2. Appeler
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:+21612345678"));
            startActivity(intent);
        });

        // 3. Envoyer email
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:contact@isitcom.tn"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Contact depuis l'app");
            intent.putExtra(Intent.EXTRA_TEXT, "Bonjour,\n\n");
            
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, 
                    "Aucune application email trouvée", 
                    Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Partager texte
        btnShare.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "Cours Android ISITCOM");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Partage");
            
            Intent chooser = Intent.createChooser(intent, "Partager via");
            startActivity(chooser);
        });
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Intents implicites"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_web"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Ouvrir site web"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_call"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Appeler"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_email"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Envoyer email"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_share"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Partager texte" />

</LinearLayout>
```

### Intents implicites supplémentaires

```java
// Ouvrir Google Maps
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("geo:0,0?q=Sousse,Tunisia"));
startActivity(intent);

// Prendre photo
Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

// Ouvrir Play Store
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("market://details?id=com.whatsapp"));
startActivity(intent);

// Envoyer SMS
Intent intent = new Intent(Intent.ACTION_SENDTO);
intent.setData(Uri.parse("smsto:+21612345678"));
intent.putExtra("sms_body", "Bonjour");
startActivity(intent);
```

---

## Exercice 5 : Activity Result API

### MainActivity.java

```java
package tn.isitcom.activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout layout;
    private ActivityResultLauncher<Intent> colorPickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.main_layout);
        Button btnChangeColor = findViewById(R.id.btn_change_color);

        // Enregistrer le launcher
        colorPickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        int color = data.getIntExtra("COLOR", Color.WHITE);
                        layout.setBackgroundColor(color);
                    }
                }
            }
        );

        // Lancer ColorPickerActivity
        btnChangeColor.setOnClickListener(v -> {
            Intent intent = new Intent(this, ColorPickerActivity.class);
            colorPickerLauncher.launch(intent);
        });
    }
}
```

### activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp">

    <Button
        android:id="@+id/btn_change_color"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Changer couleur"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

### ColorPickerActivity.java

```java
package tn.isitcom.activityresult;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ColorPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_picker);

        Button btnRed = findViewById(R.id.btn_red);
        Button btnGreen = findViewById(R.id.btn_green);
        Button btnBlue = findViewById(R.id.btn_blue);

        btnRed.setOnClickListener(v -> returnColor(Color.RED));
        btnGreen.setOnClickListener(v -> returnColor(Color.GREEN));
        btnBlue.setOnClickListener(v -> returnColor(Color.BLUE));
    }

    private void returnColor(int color) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("COLOR", color);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
```

### activity_color_picker.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Choisissez une couleur"
        android:textSize="20sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <Button
        android:id="@+id/btn_red"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="Rouge"
        android:backgroundTint="@android:color/holo_red_dark"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_green"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="Vert"
        android:backgroundTint="@android:color/holo_green_dark"
        android:layout_marginBottom="16dp" />

    <Button
        android:id="@+id/btn_blue"
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:text="Bleu"
        android:backgroundTint="@android:color/holo_blue_dark" />

</LinearLayout>
```

---

## Mini-projet : Application de profil

### InputActivity.java

```java
package tn.isitcom.profilapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class InputActivity extends AppCompatActivity {

    private EditText etNom, etPrenom, etAge, etVille;
    private ActivityResultLauncher<Intent> profileLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        etNom = findViewById(R.id.et_nom);
        etPrenom = findViewById(R.id.et_prenom);
        etAge = findViewById(R.id.et_age);
        etVille = findViewById(R.id.et_ville);
        Button btnValider = findViewById(R.id.btn_valider);

        // Enregistrer launcher pour modification
        profileLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Pré-remplir les champs
                        etNom.setText(data.getStringExtra("NOM"));
                        etPrenom.setText(data.getStringExtra("PRENOM"));
                        etAge.setText(data.getStringExtra("AGE"));
                        etVille.setText(data.getStringExtra("VILLE"));
                    }
                }
            }
        );

        btnValider.setOnClickListener(v -> validerFormulaire());
    }

    private void validerFormulaire() {
        String nom = etNom.getText().toString().trim();
        String prenom = etPrenom.getText().toString().trim();
        String age = etAge.getText().toString().trim();
        String ville = etVille.getText().toString().trim();

        // Validation
        if (nom.isEmpty()) {
            etNom.setError("Nom requis");
            etNom.requestFocus();
            return;
        }
        if (prenom.isEmpty()) {
            etPrenom.setError("Prénom requis");
            etPrenom.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            etAge.setError("Âge requis");
            etAge.requestFocus();
            return;
        }
        if (ville.isEmpty()) {
            etVille.setError("Ville requise");
            etVille.requestFocus();
            return;
        }

        // Lancer ProfileActivity
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("NOM", nom);
        intent.putExtra("PRENOM", prenom);
        intent.putExtra("AGE", age);
        intent.putExtra("VILLE", ville);
        profileLauncher.launch(intent);
    }
}
```

### activity_input.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="24dp">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Saisie du profil"
            android:textSize="24sp"
            android:textStyle="bold"
            android:layout_gravity="center"
            android:layout_marginBottom="32dp" />

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Nom"
            android:layout_marginBottom="16dp">
            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_nom"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textPersonName" />
        </com.google.android.material.textfield.TextInputLayout>

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Prénom"
            android:layout_marginBottom="16dp">
            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_prenom"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="textPersonName" />
        </com.google.android.material.textfield.TextInputLayout>

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Âge"
            android:layout_marginBottom="16dp">
            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_age"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="number" />
        </com.google.android.material.textfield.TextInputLayout>

        <com.google.android.material.textfield.TextInputLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="Ville"
            android:layout_marginBottom="32dp">
            <com.google.android.material.textfield.TextInputEditText
                android:id="@+id/et_ville"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:inputType="text" />
        </com.google.android.material.textfield.TextInputLayout>

        <com.google.android.material.button.MaterialButton
            android:id="@+id/btn_valider"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Valider"
            android:padding="16dp" />

    </LinearLayout>
</ScrollView>
```

### ProfileActivity.java

```java
package tn.isitcom.profilapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private String nom, prenom, age, ville;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Récupérer les données
        Intent intent = getIntent();
        nom = intent.getStringExtra("NOM");
        prenom = intent.getStringExtra("PRENOM");
        age = intent.getStringExtra("AGE");
        ville = intent.getStringExtra("VILLE");

        // Afficher
        TextView tvProfil = findViewById(R.id.tv_profil);
        String profil = String.format(
            "Nom: %s\nPrénom: %s\nÂge: %s ans\nVille: %s",
            nom, prenom, age, ville
        );
        tvProfil.setText(profil);

        // Bouton modifier
        Button btnModifier = findViewById(R.id.btn_modifier);
        btnModifier.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NOM", nom);
            resultIntent.putExtra("PRENOM", prenom);
            resultIntent.putExtra("AGE", age);
            resultIntent.putExtra("VILLE", ville);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
```

### activity_profile.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    android:orientation="vertical"
    android:padding="24dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Profil"
        android:textSize="28sp"
        android:textStyle="bold"
        android:layout_marginBottom="32dp" />

    <androidx.cardview.widget.CardView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginBottom="24dp"
        android:elevation="4dp">

        <TextView
            android:id="@+id/tv_profil"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:padding="24dp"
            android:textSize="18sp"
            android:lineSpacingExtra="8dp" />
    </androidx.cardview.widget.CardView>

    <com.google.android.material.button.MaterialButton
        android:id="@+id/btn_modifier"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Modifier"
        android:padding="16dp" />

</LinearLayout>
```

### Grille d'évaluation

| Critère | Points | Évaluation |
|---------|--------|-------------|
| Navigation fonctionnelle | 4 | InputActivity ↔ ProfileActivity |
| Passage de données correct | 4 | 4 champs transmis correctement |
| Activity Result API | 4 | Launcher enregistré, données retournées |
| Validation champs | 3 | Tous les champs vérifiés |
| Interface soignée | 3 | Material Components, CardView |
| Code propre | 2 | Commentaires, nommage |
| **Total** | **/20** | |

---

👨‍🏫 **Corrections Module 5** | ISITCOM 2025-2026
