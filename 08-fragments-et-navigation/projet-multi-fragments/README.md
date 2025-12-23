# 🧩 Projet Module 8 : Application Multi-Fragments avec Bottom Navigation

## 🎯 Objectif

Créer une application multi-onglets moderne avec **Fragments**, **Navigation Component** et **Bottom Navigation**.

**Concepts** : Fragments, Lifecycle Fragments, Navigation Graph, Safe Args, Bottom Navigation, Communication fragments.

---

## ✨ Fonctionnalités

✅ **3 Fragments** (Accueil, Profil, Paramètres)  
✅ **Bottom Navigation** : Navigation entre fragments  
✅ **Navigation Component** : Navigation moderne Jetpack  
✅ **Safe Args** : Passage de données typé  
✅ **Lifecycle** : onViewCreated, onDestroyView  
✅ **View Binding** : Accès aux vues sécurisé  
✅ **Shared ViewModel** : Communication fragments  

---

## 📂 Structure du projet

```
app/src/main/
├── java/tn/isitcom/multifragments/
│   ├── MainActivity.java           # Activity conteneur
│   ├── fragments/
│   │   ├── HomeFragment.java       # Fragment Accueil
│   │   ├── ProfileFragment.java    # Fragment Profil
│   │   └── SettingsFragment.java   # Fragment Paramètres
│   └── viewmodels/
│       └── SharedViewModel.java    # ViewModel partagé
├── res/
│   ├── layout/
│   │   ├── activity_main.xml       # Layout principal avec NavHostFragment
│   │   ├── fragment_home.xml       # Layout Accueil
│   │   ├── fragment_profile.xml    # Layout Profil
│   │   └── fragment_settings.xml   # Layout Paramètres
│   ├── navigation/
│   │   └── nav_graph.xml           # Graphe de navigation
│   ├── menu/
│   │   └── bottom_nav_menu.xml     # Menu Bottom Navigation
│   ├── drawable/
│   │   ├── ic_home.xml
│   │   ├── ic_profile.xml
│   │   └── ic_settings.xml
│   └── values/
│       ├── strings.xml
│       ├── colors.xml
│       └── themes.xml
└── AndroidManifest.xml
```

---

## 📝 Code complet du projet

### 1️⃣ **MainActivity.java** - Activity conteneur

**Emplacement** : `app/src/main/java/tn/isitcom/multifragments/MainActivity.java`

```java
package tn.isitcom.multifragments;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupérer NavHostFragment
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
            .findFragmentById(R.id.nav_host_fragment);
        
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
        }

        // Configurer Bottom Navigation avec Navigation Component
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNav, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}
```

---

### 2️⃣ **HomeFragment.java** - Fragment Accueil

**Emplacement** : `app/src/main/java/tn/isitcom/multifragments/fragments/HomeFragment.java`

```java
package tn.isitcom.multifragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import tn.isitcom.multifragments.R;
import tn.isitcom.multifragments.viewmodels.SharedViewModel;

public class HomeFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView tvWelcome, tvCounter;
    private Button btnNavigateToProfile, btnIncrement;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtenir ViewModel partagé (scope = Activity)
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialiser vues
        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvCounter = view.findViewById(R.id.tvCounter);
        btnNavigateToProfile = view.findViewById(R.id.btnNavigateToProfile);
        btnIncrement = view.findViewById(R.id.btnIncrement);

        // Observer le nom d'utilisateur depuis ViewModel
        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.isEmpty()) {
                tvWelcome.setText("Bienvenue " + name + " !");
            } else {
                tvWelcome.setText("👋 Bienvenue sur l'Accueil");
            }
        });

        // Observer le compteur
        viewModel.getCounter().observe(getViewLifecycleOwner(), count -> {
            tvCounter.setText("Compteur : " + count);
        });

        // Navigation vers Profil avec Safe Args
        btnNavigateToProfile.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("message", "Bonjour depuis l'Accueil !");
            Navigation.findNavController(v).navigate(R.id.action_home_to_profile, bundle);
        });

        // Incrémenter compteur partagé
        btnIncrement.setOnClickListener(v -> viewModel.incrementCounter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Libérer références aux vues
        tvWelcome = null;
        tvCounter = null;
        btnNavigateToProfile = null;
        btnIncrement = null;
    }
}
```

---

### 3️⃣ **ProfileFragment.java** - Fragment Profil

**Emplacement** : `app/src/main/java/tn/isitcom/multifragments/fragments/ProfileFragment.java`

```java
package tn.isitcom.multifragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import tn.isitcom.multifragments.R;
import tn.isitcom.multifragments.viewmodels.SharedViewModel;

public class ProfileFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView tvMessage, tvUserInfo;
    private EditText etName;
    private Button btnSaveName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvMessage = view.findViewById(R.id.tvMessage);
        tvUserInfo = view.findViewById(R.id.tvUserInfo);
        etName = view.findViewById(R.id.etName);
        btnSaveName = view.findViewById(R.id.btnSaveName);

        // Récupérer argument passé depuis HomeFragment
        if (getArguments() != null) {
            String message = getArguments().getString("message");
            if (message != null) {
                tvMessage.setText("📨 " + message);
            }
        }

        // Observer nom d'utilisateur
        viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            if (name != null && !name.isEmpty()) {
                tvUserInfo.setText("👤 Utilisateur : " + name);
                etName.setText(name);
            }
        });

        // Sauvegarder le nom
        btnSaveName.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (!name.isEmpty()) {
                viewModel.setUserName(name);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvMessage = null;
        tvUserInfo = null;
        etName = null;
        btnSaveName = null;
    }
}
```

---

### 4️⃣ **SettingsFragment.java** - Fragment Paramètres

**Emplacement** : `app/src/main/java/tn/isitcom/multifragments/fragments/SettingsFragment.java`

```java
package tn.isitcom.multifragments.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import tn.isitcom.multifragments.R;
import tn.isitcom.multifragments.viewmodels.SharedViewModel;

public class SettingsFragment extends Fragment {

    private SharedViewModel viewModel;
    private TextView tvCounter;
    private Switch switchNotifications;
    private Button btnResetCounter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvCounter = view.findViewById(R.id.tvCounterSettings);
        switchNotifications = view.findViewById(R.id.switchNotifications);
        btnResetCounter = view.findViewById(R.id.btnResetCounter);

        // Observer compteur global
        viewModel.getCounter().observe(getViewLifecycleOwner(), count -> {
            tvCounter.setText("🔢 Compteur global : " + count);
        });

        // Réinitialiser compteur
        btnResetCounter.setOnClickListener(v -> viewModel.resetCounter());

        // Switch notifications (exemple)
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String status = isChecked ? "activées" : "désactivées";
            // Toast ou autre action
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        tvCounter = null;
        switchNotifications = null;
        btnResetCounter = null;
    }
}
```

---

### 5️⃣ **SharedViewModel.java** - ViewModel partagé

**Emplacement** : `app/src/main/java/tn/isitcom/multifragments/viewmodels/SharedViewModel.java`

```java
package tn.isitcom.multifragments.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * ViewModel partagé entre tous les Fragments.
 * Scope = Activity, survit aux rotations et changements de fragments.
 */
public class SharedViewModel extends ViewModel {

    // Nom d'utilisateur
    private final MutableLiveData<String> userName = new MutableLiveData<>("");

    // Compteur partagé
    private final MutableLiveData<Integer> counter = new MutableLiveData<>(0);

    // Getters LiveData (immuables)
    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<Integer> getCounter() {
        return counter;
    }

    // Setters
    public void setUserName(String name) {
        userName.setValue(name);
    }

    public void incrementCounter() {
        Integer current = counter.getValue();
        if (current != null) {
            counter.setValue(current + 1);
        }
    }

    public void resetCounter() {
        counter.setValue(0);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // Libérer ressources si nécessaire
    }
}
```

---

## 📱 Layouts XML

### 6️⃣ **activity_main.xml** - Layout principal

**Emplacement** : `app/src/main/res/layout/activity_main.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- NavHostFragment : conteneur pour fragments -->
    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottom_navigation"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Bottom Navigation -->
    <com.google.android.material.bottomnavigation.BottomNavigationView
        android:id="@+id/bottom_navigation"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="?android:attr/windowBackground"
        app:menu="@menu/bottom_nav_menu"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### 7️⃣ **fragment_home.xml** - Layout Accueil

**Emplacement** : `app/src/main/res/layout/fragment_home.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvWelcome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="👋 Bienvenue sur l'Accueil"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <TextView
        android:id="@+id/tvCounter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Compteur : 0"
        android:textSize="18sp"
        app:layout_constraintTop_toBottomOf="@id/tvWelcome"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <Button
        android:id="@+id/btnIncrement"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Incrémenter"
        app:layout_constraintTop_toBottomOf="@id/tvCounter"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <Button
        android:id="@+id/btnNavigateToProfile"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Aller au Profil"
        app:layout_constraintTop_toBottomOf="@id/btnIncrement"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="🏠 Fragment Accueil"
        android:textColor="#9E9E9E"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="32dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### 8️⃣ **fragment_profile.xml** - Layout Profil

**Emplacement** : `app/src/main/res/layout/fragment_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="👤 Profil Utilisateur"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <TextView
        android:id="@+id/tvMessage"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="📨 Message"
        android:textSize="16sp"
        android:textColor="#2196F3"
        app:layout_constraintTop_toBottomOf="@id/tvTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <TextView
        android:id="@+id/tvUserInfo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="👤 Utilisateur : Inconnu"
        android:textSize="18sp"
        app:layout_constraintTop_toBottomOf="@id/tvMessage"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <EditText
        android:id="@+id/etName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Entrez votre nom"
        android:inputType="textPersonName"
        app:layout_constraintTop_toBottomOf="@id/tvUserInfo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <Button
        android:id="@+id/btnSaveName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Sauvegarder"
        app:layout_constraintTop_toBottomOf="@id/etName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="👤 Fragment Profil"
        android:textColor="#9E9E9E"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="32dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

### 9️⃣ **fragment_settings.xml** - Layout Paramètres

**Emplacement** : `app/src/main/res/layout/fragment_settings.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="⚙️ Paramètres"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <TextView
        android:id="@+id/tvCounterSettings"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="🔢 Compteur global : 0"
        android:textSize="18sp"
        app:layout_constraintTop_toBottomOf="@id/tvTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp" />

    <Button
        android:id="@+id/btnResetCounter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Réinitialiser Compteur"
        app:layout_constraintTop_toBottomOf="@id/tvCounterSettings"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

    <TextView
        android:id="@+id/tvNotifications"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Notifications"
        android:textSize="16sp"
        app:layout_constraintTop_toBottomOf="@id/btnResetCounter"
        app:layout_constraintStart_toStartOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginStart="16dp" />

    <Switch
        android:id="@+id/switchNotifications"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toBottomOf="@id/btnResetCounter"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginEnd="16dp" />

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="⚙️ Fragment Paramètres"
        android:textColor="#9E9E9E"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="32dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 🧭 Navigation Component

### 🔟 **nav_graph.xml** - Graphe de navigation

**Emplacement** : `app/src/main/res/navigation/nav_graph.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/nav_graph"
    app:startDestination="@id/homeFragment">

    <fragment
        android:id="@+id/homeFragment"
        android:name="tn.isitcom.multifragments.fragments.HomeFragment"
        android:label="Accueil">
        
        <action
            android:id="@+id/action_home_to_profile"
            app:destination="@id/profileFragment"
            app:enterAnim="@android:anim/slide_in_left"
            app:exitAnim="@android:anim/slide_out_right" />
            
    </fragment>

    <fragment
        android:id="@+id/profileFragment"
        android:name="tn.isitcom.multifragments.fragments.ProfileFragment"
        android:label="Profil">
        
        <argument
            android:name="message"
            app:argType="string"
            android:defaultValue="" />
            
    </fragment>

    <fragment
        android:id="@+id/settingsFragment"
        android:name="tn.isitcom.multifragments.fragments.SettingsFragment"
        android:label="Paramètres" />

</navigation>
```

---

### 🖌️ **bottom_nav_menu.xml** - Menu Bottom Navigation

**Emplacement** : `app/src/main/res/menu/bottom_nav_menu.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/homeFragment"
        android:icon="@drawable/ic_home"
        android:title="Accueil" />

    <item
        android:id="@+id/profileFragment"
        android:icon="@drawable/ic_profile"
        android:title="Profil" />

    <item
        android:id="@+id/settingsFragment"
        android:icon="@drawable/ic_settings"
        android:title="Paramètres" />

</menu>
```

**🔑 Important** : Les `android:id` du menu **doivent correspondre** aux IDs des fragments dans `nav_graph.xml` pour que `NavigationUI.setupWithNavController()` fonctionne automatiquement.

---

## 🎨 Fichiers Drawable (Icônes)

Voir le fichier **[RESSOURCES.md](RESSOURCES.md)** pour les icônes :

- ✅ `ic_home.xml`
- ✅ `ic_profile.xml`
- ✅ `ic_settings.xml`

---

## 📦 Dépendances Gradle

**Emplacement** : `app/build.gradle`

```gradle
plugins {
    id 'com.android.application'
}

android {
    namespace 'tn.isitcom.multifragments'
    compileSdk 34

    defaultConfig {
        applicationId "tn.isitcom.multifragments"
        minSdk 24
        targetSdk 34
        versionCode 1
        versionName "1.0"
    }

    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // Navigation Component
    implementation 'androidx.navigation:navigation-fragment:2.7.6'
    implementation 'androidx.navigation:navigation-ui:2.7.6'
    
    // ViewModel & LiveData
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
}
```

---

## 🔍 AndroidManifest.xml

**Emplacement** : `app/src/main/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tn.isitcom.multifragments">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="Multi-Fragments"
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

### 1. Qu'est-ce qu'un Fragment ?

**Fragment** = Portion d'interface UI réutilisable avec son propre lifecycle.

**Avantages** :
- **Modularité** : Code réutilisable
- **Tablettes** : Plusieurs fragments sur même écran
- **Navigation** : Transitions fluides sans changer d'Activity
- **Lifecycle** : Indépendant de l'Activity

### 2. Lifecycle d'un Fragment

```
onAttach() → Activity liée
onCreate() → Création (pas de vues)
onCreateView() → Création layout (retourne View)
onViewCreated() → Vues disponibles (init vues ici !)
onStart() → Visible
onResume() → Actif
onPause() → Perd focus
onStop() → Invisible
onDestroyView() → Vues détruites (libérer références)
onDestroy() → Fragment détruit
onDetach() → Détaché Activity
```

**💡 Best Practice** : Init vues dans `onViewCreated()`, libérer dans `onDestroyView()`.

### 3. Navigation Component

**3 composants** :

1. **Navigation Graph** (`nav_graph.xml`) : Définit destinations et actions
2. **NavHostFragment** : Conteneur dans layout
3. **NavController** : Contrôle navigation programmatique

**Navigation** :
```java
// Méthode 1 : Par action
Navigation.findNavController(view).navigate(R.id.action_home_to_profile);

// Méthode 2 : Par destination directe
Navigation.findNavController(view).navigate(R.id.profileFragment);

// Méthode 3 : Avec arguments
Bundle bundle = new Bundle();
bundle.putString("key", "value");
Navigation.findNavController(view).navigate(R.id.profileFragment, bundle);
```

### 4. Communication entre Fragments

**3 méthodes** :

**A. Via ViewModel partagé** (✅ Recommandé) :
```java
// Dans Fragment
viewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
```

**B. Via Bundle (arguments)** :
```java
// Envoi
Bundle bundle = new Bundle();
bundle.putString("message", "Hello");
Navigation.findNavController(view).navigate(R.id.dest, bundle);

// Réception
if (getArguments() != null) {
    String msg = getArguments().getString("message");
}
```

**C. Via Interface callback** (dépassé, éviter).

### 5. LiveData + ViewModel

**LiveData** : Observable lifecycle-aware (met à jour UI automatiquement).

```java
// Observer
viewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
    textView.setText(name);
});
```

**💡 Clé** : `getViewLifecycleOwner()` dans fragments (pas `this` !)

### 6. Bottom Navigation + Navigation Component

**Setup automatique** :
```java
BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
NavigationUI.setupWithNavController(bottomNav, navController);
```

**Condition** : IDs du menu = IDs des fragments dans nav_graph.

---

## 🧪 Tests à effectuer

### Test 1 : Navigation Bottom Nav
1. Lancer app
2. Cliquer onglets Accueil/Profil/Paramètres
3. **Attendu** : Fragments changent, icônes s'éclairent

### Test 2 : Communication ViewModel
1. Aller sur **Profil**
2. Entrer nom "Alice"
3. Cliquer **Sauvegarder**
4. Aller sur **Accueil**
5. **Attendu** : "Bienvenue Alice !"

### Test 3 : Compteur partagé
1. Aller **Accueil**
2. Cliquer **Incrémenter** 5 fois
3. Aller **Paramètres**
4. **Attendu** : "Compteur global : 5"

### Test 4 : Navigation programmatique
1. Aller **Accueil**
2. Cliquer **Aller au Profil**
3. **Attendu** : Message "📨 Bonjour depuis l'Accueil !"

### Test 5 : Rotation
1. Incrémenter compteur à 10
2. Tourner écran
3. **Attendu** : Compteur toujours à 10 (ViewModel survit)

---

## 💡 Améliorations possibles

1. **Safe Args** : Plugin Gradle pour arguments typés
2. **ViewPager2** : Swipe entre fragments
3. **Deep Links** : Ouvrir fragment spécifique depuis notification
4. **Animations** : Transitions personnalisées
5. **DialogFragment** : Dialogues en fragment
6. **Fragment Result API** : Communication moderne
7. **Navigation Drawer** : Menu latéral

---

## 📚 Concepts Android utilisés

✅ **Fragment** : UI modulaire  
✅ **Fragment Lifecycle** : onViewCreated, onDestroyView  
✅ **Navigation Component** : Navigation moderne Jetpack  
✅ **NavController** : Navigation programmatique  
✅ **NavHostFragment** : Conteneur fragments  
✅ **Bottom Navigation** : Navigation par onglets  
✅ **ViewModel** : Données survie rotation  
✅ **LiveData** : Observable lifecycle-aware  
✅ **Shared ViewModel** : Communication fragments  
✅ **Bundle** : Passage arguments  
✅ **getViewLifecycleOwner()** : Observer correct dans fragments  

---

## ✅ Checklist avant exécution

- [ ] Tous les fichiers Java copiés
- [ ] Tous les layouts XML créés
- [ ] nav_graph.xml configuré
- [ ] bottom_nav_menu.xml avec bons IDs
- [ ] Fichiers drawable ajoutés (voir RESSOURCES.md)
- [ ] Dépendances Gradle (Navigation, ViewModel)
- [ ] AndroidManifest.xml correct
- [ ] Package name : `tn.isitcom.multifragments`
- [ ] Sync Gradle réussi
- [ ] Aucune erreur de compilation

---

## 🚀 Exécution

1. Créer nouveau projet Android Studio
   - Empty Views Activity
   - Package : `tn.isitcom.multifragments`
   - Min SDK : API 24

2. Ajouter dépendances Navigation dans `build.gradle`

3. Copier tous les fichiers selon la structure

4. Créer dossiers `navigation/` et `menu/` dans `res/`

5. Sync Gradle

6. Run sur émulateur ou appareil

---

## 🔗 Liens utiles

- [Navigation Component Doc](https://developer.android.com/guide/navigation)
- [Fragment Lifecycle](https://developer.android.com/guide/fragments/lifecycle)
- [ViewModel Overview](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData Overview](https://developer.android.com/topic/libraries/architecture/livedata)

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 8 : Fragments et Navigation Component
