# Ressources du Projet Multi-Fragments

## 🎨 Fichiers Drawables requis

Voici tous les fichiers drawable nécessaires pour le projet.

### 1. ic_home.xml
**Emplacement** : `res/drawable/ic_home.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#000000"
        android:pathData="M10,20v-6h4v6h5v-8h3L12,3 2,12h3v8z" />
</vector>
```

### 2. ic_profile.xml
**Emplacement** : `res/drawable/ic_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#000000"
        android:pathData="M12,12c2.21,0 4,-1.79 4,-4s-1.79,-4 -4,-4 -4,1.79 -4,4 1.79,4 4,4zM12,14c-2.67,0 -8,1.34 -8,4v2h16v-2c0,-2.66 -5.33,-4 -8,-4z" />
</vector>
```

### 3. ic_settings.xml
**Emplacement** : `res/drawable/ic_settings.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#000000"
        android:pathData="M19.14,12.94c0.04,-0.3 0.06,-0.61 0.06,-0.94 0,-0.32 -0.02,-0.64 -0.07,-0.94l2.03,-1.58c0.18,-0.14 0.23,-0.41 0.12,-0.61l-1.92,-3.32c-0.12,-0.22 -0.37,-0.29 -0.59,-0.22l-2.39,0.96c-0.5,-0.38 -1.03,-0.7 -1.62,-0.94L14.4,2.81c-0.04,-0.24 -0.24,-0.41 -0.48,-0.41h-3.84c-0.24,0 -0.43,0.17 -0.47,0.41L9.25,5.35C8.66,5.59 8.12,5.92 7.63,6.29L5.24,5.33c-0.22,-0.08 -0.47,0 -0.59,0.22L2.74,8.87C2.62,9.08 2.66,9.34 2.86,9.48l2.03,1.58C4.84,11.36 4.8,11.69 4.8,12s0.02,0.64 0.07,0.94l-2.03,1.58c-0.18,0.14 -0.23,0.41 -0.12,0.61l1.92,3.32c0.12,0.22 0.37,0.29 0.59,0.22l2.39,-0.96c0.5,0.38 1.03,0.7 1.62,0.94l0.36,2.54c0.05,0.24 0.24,0.41 0.48,0.41h3.84c0.24,0 0.44,-0.17 0.47,-0.41l0.36,-2.54c0.59,-0.24 1.13,-0.56 1.62,-0.94l2.39,0.96c0.22,0.08 0.47,0 0.59,-0.22l1.92,-3.32c0.12,-0.22 0.07,-0.47 -0.12,-0.61L19.14,12.94zM12,15.6c-1.98,0 -3.6,-1.62 -3.6,-3.6s1.62,-3.6 3.6,-3.6s3.6,1.62 3.6,3.6S13.98,15.6 12,15.6z" />
</vector>
```

---

## 🎨 Fichier colors.xml

**Emplacement** : `res/values/colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    
    <!-- Couleurs personnalisées -->
    <color name="primary_blue">#2196F3</color>
    <color name="text_gray">#757575</color>
    <color name="background_gray">#F5F5F5</color>
</resources>
```

---

## 📑 Fichier strings.xml

**Emplacement** : `res/values/strings.xml`

```xml
<resources>
    <string name="app_name">Multi-Fragments</string>
    
    <!-- Navigation -->
    <string name="nav_home">Accueil</string>
    <string name="nav_profile">Profil</string>
    <string name="nav_settings">Paramètres</string>
    
    <!-- Home Fragment -->
    <string name="home_welcome">Bienvenue sur l\'Accueil</string>
    <string name="home_counter">Compteur : %d</string>
    <string name="btn_increment">Incrémenter</string>
    <string name="btn_go_profile">Aller au Profil</string>
    
    <!-- Profile Fragment -->
    <string name="profile_title">Profil Utilisateur</string>
    <string name="profile_hint_name">Entrez votre nom</string>
    <string name="btn_save">Sauvegarder</string>
    
    <!-- Settings Fragment -->
    <string name="settings_title">Paramètres</string>
    <string name="settings_counter">Compteur global : %d</string>
    <string name="btn_reset">Réinitialiser Compteur</string>
    <string name="settings_notifications">Notifications</string>
</resources>
```

---

## 🎨 Fichier themes.xml

**Emplacement** : `res/values/themes.xml`

```xml
<resources xmlns:tools="http://schemas.android.com/tools">
    <style name="Theme.MultiFragments" parent="Theme.Material3.Light">
        <item name="colorPrimary">@color/purple_500</item>
        <item name="colorPrimaryVariant">@color/purple_700</item>
        <item name="colorOnPrimary">@color/white</item>
        
        <item name="colorSecondary">@color/teal_200</item>
        <item name="colorSecondaryVariant">@color/teal_700</item>
        <item name="colorOnSecondary">@color/black</item>
        
        <item name="android:statusBarColor">@color/purple_700</item>
    </style>
</resources>
```

---

## 📦 Dépendances Gradle complètes

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

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding true
    }
}

dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    
    // Navigation Component (Jetpack)
    implementation 'androidx.navigation:navigation-fragment:2.7.6'
    implementation 'androidx.navigation:navigation-ui:2.7.6'
    
    // ViewModel & LiveData (Architecture Components)
    implementation 'androidx.lifecycle:lifecycle-viewmodel:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-livedata:2.7.0'
    implementation 'androidx.lifecycle:lifecycle-runtime:2.7.0'
    
    // Fragment KTX (extensions utiles)
    implementation 'androidx.fragment:fragment:1.6.2'
    
    // Tests
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```

---

## 📌 Organisation des fichiers

```
app/src/main/
├── java/tn/isitcom/multifragments/
│   ├── MainActivity.java
│   ├── fragments/
│   │   ├── HomeFragment.java
│   │   ├── ProfileFragment.java
│   │   └── SettingsFragment.java
│   └── viewmodels/
│       └── SharedViewModel.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── fragment_home.xml
│   │   ├── fragment_profile.xml
│   │   └── fragment_settings.xml
│   ├── navigation/
│   │   └── nav_graph.xml
│   ├── menu/
│   │   └── bottom_nav_menu.xml
│   ├── drawable/
│   │   ├── ic_home.xml
│   │   ├── ic_profile.xml
│   │   └── ic_settings.xml
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

## 🛠️ Configuration AndroidManifest.xml

**Emplacement** : `app/src/main/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tn.isitcom.multifragments">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.MultiFragments">
        
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

## 💡 Conseils de débogage

### Erreur : "Cannot resolve symbol 'R'"

**Solutions** :
1. Sync Gradle : `File > Sync Project with Gradle Files`
2. Clean Build : `Build > Clean Project` puis `Build > Rebuild Project`
3. Invalidate Caches : `File > Invalidate Caches / Restart`

### Erreur : "NavHostFragment not found"

**Solutions** :
1. Vérifier dépendances Navigation dans `build.gradle`
2. Vérifier `android:name` dans `activity_main.xml` :
   ```xml
   android:name="androidx.navigation.fragment.NavHostFragment"
   ```

### Erreur : "Navigation graph required"

**Solutions** :
1. Créer dossier `res/navigation/`
2. Ajouter `nav_graph.xml`
3. Vérifier `app:navGraph="@navigation/nav_graph"` dans `activity_main.xml`

### Erreur : "Bottom Navigation not working"

**Solutions** :
1. Vérifier IDs menu = IDs fragments dans nav_graph
2. Vérifier `NavigationUI.setupWithNavController()` appelé

---

## ✅ Checklist d'installation

### Étape 1 : Créer projet
- [ ] Android Studio : New Project
- [ ] Template : Empty Views Activity
- [ ] Package : `tn.isitcom.multifragments`
- [ ] Min SDK : API 24

### Étape 2 : Ajouter dépendances
- [ ] Ouvrir `app/build.gradle`
- [ ] Ajouter Navigation Component
- [ ] Ajouter ViewModel & LiveData
- [ ] Sync Gradle

### Étape 3 : Créer structure
- [ ] Créer package `fragments/`
- [ ] Créer package `viewmodels/`
- [ ] Créer dossier `res/navigation/`
- [ ] Créer dossier `res/menu/`

### Étape 4 : Copier fichiers Java
- [ ] MainActivity.java
- [ ] HomeFragment.java
- [ ] ProfileFragment.java
- [ ] SettingsFragment.java
- [ ] SharedViewModel.java

### Étape 5 : Copier layouts XML
- [ ] activity_main.xml
- [ ] fragment_home.xml
- [ ] fragment_profile.xml
- [ ] fragment_settings.xml

### Étape 6 : Configurer Navigation
- [ ] nav_graph.xml
- [ ] bottom_nav_menu.xml

### Étape 7 : Ajouter ressources
- [ ] ic_home.xml
- [ ] ic_profile.xml
- [ ] ic_settings.xml
- [ ] colors.xml
- [ ] strings.xml
- [ ] themes.xml

### Étape 8 : Build & Run
- [ ] Clean Project
- [ ] Rebuild Project
- [ ] Run sur émulateur
- [ ] Tester navigation

---

## 🔗 Ressources supplémentaires

- [Navigation Component Codelab](https://developer.android.com/codelabs/android-navigation)
- [Fragment Communication](https://developer.android.com/guide/fragments/communicate)
- [ViewModel Guide](https://developer.android.com/topic/libraries/architecture/viewmodel)
- [LiveData Guide](https://developer.android.com/topic/libraries/architecture/livedata)
- [Bottom Navigation Guide](https://material.io/components/bottom-navigation)

---

**👨‍🏫 Cours Android ISITCOM 2025-2026**
