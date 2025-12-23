# 🎯 Exemples Pratiques : LinearLayout

## 📝 Exemple 1 : Formulaire de connexion (Vertical)

**Objectif** : Créer un écran de login simple

### 📑 Layout XML

**Fichier** : `res/layout/activity_login.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="32dp"
    android:gravity="center"
    android:background="#FAFAFA">
    
    <!-- Logo -->
    <ImageView
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_launcher_foreground"
        android:layout_marginBottom="32dp" />
    
    <!-- Titre -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Connexion"
        android:textSize="28sp"
        android:textStyle="bold"
        android:textColor="#212121"
        android:layout_marginBottom="24dp" />
    
    <!-- Champ Email -->
    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Adresse email"
        android:inputType="textEmailAddress"
        android:padding="16dp"
        android:background="@drawable/edittext_bg"
        android:layout_marginBottom="16dp" />
    
    <!-- Champ Mot de passe -->
    <EditText
        android:id="@+id/etPassword"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Mot de passe"
        android:inputType="textPassword"
        android:padding="16dp"
        android:background="@drawable/edittext_bg"
        android:layout_marginBottom="24dp" />
    
    <!-- Bouton Connexion -->
    <Button
        android:id="@+id/btnLogin"
        android:layout_width="match_parent"
        android:layout_height="56dp"
        android:text="Se connecter"
        android:textSize="16sp"
        android:backgroundTint="#2196F3" />
    
    <!-- Lien Mot de passe oublié -->
    <TextView
        android:id="@+id/tvForgotPassword"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Mot de passe oublié ?"
        android:textColor="#2196F3"
        android:layout_marginTop="16dp" />
        
</LinearLayout>
```

### 🎨 Drawable edittext_bg.xml

**Fichier** : `res/drawable/edittext_bg.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <solid android:color="#FFFFFF" />
    <corners android:radius="8dp" />
    <stroke
        android:width="1dp"
        android:color="#E0E0E0" />
</shape>
```

### ☕ Code Java

**Fichier** : `LoginActivity.java`

```java
package tn.isitcom.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        // Bouton Connexion
        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Logique de connexion
                Toast.makeText(this, "Connexion...", Toast.LENGTH_SHORT).show();
            }
        });

        // Mot de passe oublié
        tvForgotPassword.setOnClickListener(v -> {
            Toast.makeText(this, "Récupération mot de passe", Toast.LENGTH_SHORT).show();
        });
    }
}
```

---

## 📝 Exemple 2 : Barre d'actions (Horizontal)

**Objectif** : Créer une barre avec 3 boutons de même taille

### 📑 Layout XML

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="8dp"
    android:background="#FFFFFF"
    android:elevation="4dp">
    
    <!-- Bouton Partager (1/3) -->
    <Button
        android:id="@+id/btnShare"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Partager"
        android:drawableTop="@drawable/ic_share"
        android:background="?attr/selectableItemBackground"
        android:padding="12dp" />
    
    <!-- Bouton Sauvegarder (1/3) -->
    <Button
        android:id="@+id/btnSave"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Sauvegarder"
        android:drawableTop="@drawable/ic_save"
        android:background="?attr/selectableItemBackground"
        android:padding="12dp" />
    
    <!-- Bouton Supprimer (1/3) -->
    <Button
        android:id="@+id/btnDelete"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Supprimer"
        android:drawableTop="@drawable/ic_delete"
        android:background="?attr/selectableItemBackground"
        android:padding="12dp" />
        
</LinearLayout>
```

**Résultat** : 3 boutons également espacés avec icônes au-dessus du texte

---

## 📝 Exemple 3 : Liste de cartes (Vertical avec ScrollView)

**Objectif** : Liste d'éléments scrollable

### 📑 Layout XML

```xml
<ScrollView
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fillViewport="true">
    
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">
        
        <!-- Carte 1 -->
        <androidx.cardview.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:elevation="2dp"
            android:radius="8dp">
            
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="16dp">
                
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Article 1"
                    android:textSize="18sp"
                    android:textStyle="bold" />
                
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Description de l'article 1"
                    android:layout_marginTop="8dp" />
                    
            </LinearLayout>
            
        </androidx.cardview.widget.CardView>
        
        <!-- Carte 2 -->
        <androidx.cardview.widget.CardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            android:elevation="2dp"
            android:radius="8dp">
            
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="16dp">
                
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Article 2"
                    android:textSize="18sp"
                    android:textStyle="bold" />
                
                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="Description de l'article 2"
                    android:layout_marginTop="8dp" />
                    
            </LinearLayout>
            
        </androidx.cardview.widget.CardView>
        
        <!-- Ajouter plus de cartes... -->
        
    </LinearLayout>
    
</ScrollView>
```

---

## 📝 Exemple 4 : Disposition asymétrique (Horizontal + Weight)

**Objectif** : Image à gauche (30%), texte à droite (70%)

### 📑 Layout XML

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:padding="16dp">
    
    <!-- Image (30%) -->
    <ImageView
        android:id="@+id/ivProduct"
        android:layout_width="0dp"
        android:layout_height="100dp"
        android:layout_weight="3"
        android:src="@drawable/product_image"
        android:scaleType="centerCrop"
        android:layout_marginEnd="16dp" />
    
    <!-- Informations (70%) -->
    <LinearLayout
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="7"
        android:orientation="vertical">
        
        <TextView
            android:id="@+id/tvProductName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Nom du produit"
            android:textSize="18sp"
            android:textStyle="bold" />
        
        <TextView
            android:id="@+id/tvProductPrice"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="99.99 TND"
            android:textSize="16sp"
            android:textColor="#4CAF50"
            android:layout_marginTop="4dp" />
        
        <TextView
            android:id="@+id/tvProductDescription"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Description du produit"
            android:layout_marginTop="8dp" />
            
    </LinearLayout>
    
</LinearLayout>
```

**Explication weight** :
- Image : `layout_weight="3"` ⇒ 3/(3+7) = 30%
- Texte : `layout_weight="7"` ⇒ 7/(3+7) = 70%

---

## 🎯 Exercices pratiques

### 🟢 Exercice 1 : Calculatrice simple

Créer une calculatrice avec :
- Affichage en haut (TextView)
- Grille de boutons 4x4 (LinearLayout imbriqués)
- Boutons : 0-9, +, -, *, /, =

### 🟢 Exercice 2 : Profil utilisateur

Créer un écran profil avec :
- Photo en haut (centrée)
- Nom et email (vertical)
- Boutons Modifier/Déconnexion (horizontal)

### 🟢 Exercice 3 : Liste de tâches

Créer une to-do list avec :
- Champ de saisie + bouton Ajouter (horizontal)
- Liste de tâches (ScrollView + LinearLayout vertical)
- Chaque tâche : CheckBox + TextView + Bouton Supprimer

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 4 : Interfaces, Views et Layouts
