# TD03 : Navigation et Intents - VERSION CORRIGÉE

## 🎯 Objectifs

- Naviguer entre plusieurs Activities
- Passer des données avec Intent (extras)
- Utiliser Activity Result API pour récupérer des résultats
- Utiliser des Intents implicites (appel, partage)

---

## Partie 1 : Application multi-écrans (45 min)

### Consignes

Créer une application "Mon Profil" avec 4 écrans :

1. **MainActivity** :
   - Bouton "Voir Profil" → `ProfileActivity`
   - Bouton "Paramètres" → `SettingsActivity`

2. **ProfileActivity** :
   - Afficher : Nom, Âge, Ville
   - Bouton "Modifier" → `EditProfileActivity`
   - Bouton "Appeler"
   - Bouton "Partager"

3. **EditProfileActivity** :
   - Champs pour modifier Nom, Âge, Ville
   - Bouton "Enregistrer" → retour à `ProfileActivity` avec nouvelles données

4. **SettingsActivity** :
   - Options de paramètres basiques

---

### Code complet : activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bienvenue"
        android:textSize="28sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="64dp" />

    <Button
        android:id="@+id/btnProfile"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Voir mon profil"
        android:textSize="16sp"
        app:layout_constraintTop_toBottomOf="@id/textTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="48dp" />

    <Button
        android:id="@+id/btnSettings"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Paramètres"
        android:textSize="16sp"
        app:layout_constraintTop_toBottomOf="@id/btnProfile"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### Code complet : MainActivity.java

```java
package tn.isitcom.td03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnSettings = findViewById(R.id.btnSettings);

        // Navigation vers ProfileActivity avec données
        btnProfile.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("NAME", "Ahmed Ben Ali");
            intent.putExtra("AGE", 22);
            intent.putExtra("CITY", "Sousse");
            intent.putExtra("PHONE", "+216 12 345 678");
            startActivity(intent);
        });

        // Navigation vers SettingsActivity
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}
```

---

## Partie 2 : Activity Result API (45 min)

### Code complet : activity_profile.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textProfileTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Mon Profil"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <androidx.cardview.widget.CardView
        android:id="@+id/cardProfile"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:cardElevation="4dp"
        app:cardCornerRadius="8dp"
        app:layout_constraintTop_toBottomOf="@id/textProfileTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="16dp">

            <TextView
                android:id="@+id/textName"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Nom : "
                android:textSize="18sp"
                android:textStyle="bold" />

            <TextView
                android:id="@+id/textAge"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Âge : "
                android:textSize="16sp"
                android:layout_marginTop="8dp" />

            <TextView
                android:id="@+id/textCity"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Ville : "
                android:textSize="16sp"
                android:layout_marginTop="8dp" />

            <TextView
                android:id="@+id/textPhone"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Tél : "
                android:textSize="16sp"
                android:layout_marginTop="8dp" />

        </LinearLayout>

    </androidx.cardview.widget.CardView>

    <Button
        android:id="@+id/btnEdit"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Modifier"
        app:layout_constraintTop_toBottomOf="@id/cardProfile"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <Button
        android:id="@+id/btnCall"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Appeler"
        app:layout_constraintTop_toBottomOf="@id/btnEdit"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/btnShare"
        android:layout_marginTop="16dp"
        android:layout_marginEnd="8dp" />

    <Button
        android:id="@+id/btnShare"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Partager"
        app:layout_constraintTop_toBottomOf="@id/btnEdit"
        app:layout_constraintStart_toEndOf="@id/btnCall"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp"
        android:layout_marginStart="8dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### Code complet : ProfileActivity.java

```java
package tn.isitcom.td03;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName, textAge, textCity, textPhone;
    private Button btnEdit, btnCall, btnShare;
    private ActivityResultLauncher<Intent> editLauncher;
    
    // Stocker les données actuelles
    private String currentName;
    private int currentAge;
    private String currentCity;
    private String currentPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialisation des vues
        textName = findViewById(R.id.textName);
        textAge = findViewById(R.id.textAge);
        textCity = findViewById(R.id.textCity);
        textPhone = findViewById(R.id.textPhone);
        btnEdit = findViewById(R.id.btnEdit);
        btnCall = findViewById(R.id.btnCall);
        btnShare = findViewById(R.id.btnShare);

        // Récupérer les données de l'Intent
        Intent intent = getIntent();
        currentName = intent.getStringExtra("NAME");
        currentAge = intent.getIntExtra("AGE", 0);
        currentCity = intent.getStringExtra("CITY");
        currentPhone = intent.getStringExtra("PHONE");

        // Afficher les données
        updateDisplay();

        // Configuration Activity Result Launcher
        editLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    currentName = data.getStringExtra("NAME");
                    currentAge = data.getIntExtra("AGE", 0);
                    currentCity = data.getStringExtra("CITY");
                    currentPhone = data.getStringExtra("PHONE");
                    updateDisplay();
                    Toast.makeText(this, "Profil mis à jour", Toast.LENGTH_SHORT).show();
                }
            }
        );

        // Bouton Modifier
        btnEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(this, EditProfileActivity.class);
            editIntent.putExtra("NAME", currentName);
            editIntent.putExtra("AGE", currentAge);
            editIntent.putExtra("CITY", currentCity);
            editIntent.putExtra("PHONE", currentPhone);
            editLauncher.launch(editIntent);
        });

        // Bouton Appeler (Intent implicite)
        btnCall.setOnClickListener(v -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + currentPhone));
            startActivity(dialIntent);
        });

        // Bouton Partager (Intent implicite)
        btnShare.setOnClickListener(v -> {
            String shareText = "Profil de " + currentName + "\n" +
                               "Âge : " + currentAge + " ans\n" +
                               "Ville : " + currentCity + "\n" +
                               "Tél : " + currentPhone;
            
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Partager via"));
        });
    }

    private void updateDisplay() {
        textName.setText("Nom : " + currentName);
        textAge.setText("Âge : " + currentAge + " ans");
        textCity.setText("Ville : " + currentCity);
        textPhone.setText("Tél : " + currentPhone);
    }
}
```

---

## Partie 3 : Édition du profil

### Code complet : activity_edit_profile.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/textEditTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Modifier le profil"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <EditText
        android:id="@+id/editName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        android:inputType="textPersonName"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/textEditTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <EditText
        android:id="@+id/editAge"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Âge"
        android:inputType="number"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <EditText
        android:id="@+id/editCity"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Ville"
        android:inputType="text"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editAge"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <EditText
        android:id="@+id/editPhone"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Téléphone"
        android:inputType="phone"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editCity"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <Button
        android:id="@+id/btnSave"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Enregistrer"
        app:layout_constraintTop_toBottomOf="@id/editPhone"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/btnCancel"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="8dp" />

    <Button
        android:id="@+id/btnCancel"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Annuler"
        app:layout_constraintTop_toBottomOf="@id/editPhone"
        app:layout_constraintStart_toEndOf="@id/btnSave"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginStart="8dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### Code complet : EditProfileActivity.java

```java
package tn.isitcom.td03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editName, editAge, editCity, editPhone;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialisation des vues
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editCity = findViewById(R.id.editCity);
        editPhone = findViewById(R.id.editPhone);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        // Récupérer et afficher les données actuelles
        Intent intent = getIntent();
        editName.setText(intent.getStringExtra("NAME"));
        editAge.setText(String.valueOf(intent.getIntExtra("AGE", 0)));
        editCity.setText(intent.getStringExtra("CITY"));
        editPhone.setText(intent.getStringExtra("PHONE"));

        // Bouton Enregistrer
        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String ageStr = editAge.getText().toString().trim();
            String city = editCity.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();

            // Validation
            if (name.isEmpty() || ageStr.isEmpty() || city.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
                return;
            }

            int age;
            try {
                age = Integer.parseInt(ageStr);
                if (age < 1 || age > 120) {
                    Toast.makeText(this, "Âge invalide", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Âge invalide", Toast.LENGTH_SHORT).show();
                return;
            }

            // Renvoyer les données
            Intent resultIntent = new Intent();
            resultIntent.putExtra("NAME", name);
            resultIntent.putExtra("AGE", age);
            resultIntent.putExtra("CITY", city);
            resultIntent.putExtra("PHONE", phone);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Bouton Annuler
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}
```

---

## Partie 4 : Activity Paramètres (Bonus)

### Code : activity_settings.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Paramètres"
        android:textSize="24sp"
        android:textStyle="bold"
        android:layout_marginBottom="24dp" />

    <Switch
        android:id="@+id/switchNotif"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Notifications"
        android:checked="true" />

    <Switch
        android:id="@+id/switchDark"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Mode sombre"
        android:layout_marginTop="16dp" />

</LinearLayout>
```

---

## 📝 Checklist AndroidManifest.xml

N'oubliez pas de déclarer toutes les Activities :

```xml
<application ...>
    <activity android:name=".MainActivity"
              android:exported="true">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
    </activity>
    
    <activity android:name=".ProfileActivity" />
    <activity android:name=".EditProfileActivity" />
    <activity android:name=".SettingsActivity" />
</application>
```

---

## 📝 Livrable

- Projet Android Studio avec 4 Activities fonctionnelles
- Vidéo de démonstration (1-2 min) montrant :
  - Navigation entre écrans
  - Modification du profil
  - Appel téléphonique
  - Partage du profil

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Navigation fonctionnelle (MainActivity vers autres) | 3 |
| Passage de données (Intent extras) | 4 |
| Activity Result API implémentée correctement | 5 |
| Validation des données en édition | 2 |
| Intents implicites (appel + partage) | 4 |
| Code propre et Activities déclarées | 2 |

---

## 📚 Ressources

- [Activity Result API](https://developer.android.com/training/basics/intents/result)
- [Intents implicites](https://developer.android.com/guide/components/intents-common)
- [Intent extras](https://developer.android.com/reference/android/content/Intent#putExtra(java.lang.String,%20java.lang.String))

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
