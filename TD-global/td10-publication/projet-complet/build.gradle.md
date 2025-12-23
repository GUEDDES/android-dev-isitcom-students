# Configuration Gradle - Budget Tracker

## build.gradle (Project level)

```gradle
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id 'com.android.application' version '8.2.0' apply false
}
```

---

## build.gradle (Module: app)

```gradle
plugins {
    id 'com.android.application'
}

android {
    namespace 'tn.isitcom.budgettracker'
    compileSdk 35

    defaultConfig {
        applicationId "tn.isitcom.budgettracker"
        minSdk 24        // Android 7.0 Nougat minimum
        targetSdk 35     // Android 15
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
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }
    
    buildFeatures {
        viewBinding true  // Active ViewBinding (optionnel mais recommandé)
    }
}

dependencies {
    // AndroidX Core
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.core:core-ktx:1.12.0'
    
    // Material Design 3
    implementation 'com.google.android.material:material:1.11.0'
    
    // Room Database
    def room_version = "2.6.1"
    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"
    
    // Lifecycle (ViewModel et LiveData)
    def lifecycle_version = "2.7.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata:$lifecycle_version"
    
    // Navigation Component
    def nav_version = "2.7.6"
    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"
    
    // RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    
    // Tests (optionnel)
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```

---

## 📝 Explications des dépendances

### 1. AndroidX Core
```gradle
implementation 'androidx.appcompat:appcompat:1.6.1'
```
- **Usage** : Compatibilité arrière avec anciennes versions Android
- **Fourni** : AppCompatActivity, Toolbar, themes

### 2. Material Design
```gradle
implementation 'com.google.android.material:material:1.11.0'
```
- **Usage** : Composants Material Design 3
- **Fourni** : MaterialButton, TextInputLayout, BottomNavigationView, FAB, CardView

### 3. Room Database
```gradle
implementation "androidx.room:room-runtime:2.6.1"
annotationProcessor "androidx.room:room-compiler:2.6.1"
```
- **Usage** : Base de données SQLite avec ORM
- **Annotations** : @Entity, @Dao, @Database, @Query
- **runtime** : Bibliothèque Room
- **compiler** : Génère le code à la compilation

### 4. Lifecycle
```gradle
implementation "androidx.lifecycle:lifecycle-viewmodel:2.7.0"
implementation "androidx.lifecycle:lifecycle-livedata:2.7.0"
```
- **ViewModel** : Gère les données qui survivent aux rotations
- **LiveData** : Données observables qui respectent le cycle de vie

### 5. Navigation Component
```gradle
implementation "androidx.navigation:navigation-fragment:2.7.6"
implementation "androidx.navigation:navigation-ui:2.7.6"
```
- **Usage** : Navigation entre fragments
- **Fourni** : NavController, NavHost, graphe de navigation

### 6. RecyclerView
```gradle
implementation 'androidx.recyclerview:recyclerview:1.3.2'
```
- **Usage** : Listes optimisées et recyclables
- **Fourni** : RecyclerView, Adapter, ViewHolder, LayoutManager

---

## 🔧 Configuration Android

### compileSdk
```gradle
compileSdk 35
```
- Version du SDK utilisée pour compiler
- Correspond à Android 15

### minSdk
```gradle
minSdk 24
```
- Version Android minimale supportée
- API 24 = Android 7.0 Nougat (2016)
- Couvre ~99% des appareils

### targetSdk
```gradle
targetSdk 35
```
- Version Android cible
- Application teste les comportements de cette version
- Google Play exige targetSdk récent

### Java Version
```gradle
compileOptions {
    sourceCompatibility JavaVersion.VERSION_17
    targetCompatibility JavaVersion.VERSION_17
}
```
- Utilise Java 17 (LTS)
- Support des features modernes (var, switch expressions, records...)

---

## 🛠️ ViewBinding (optionnel)

```gradle
buildFeatures {
    viewBinding true
}
```

**Avantages** :
- Élimine findViewById()
- Sécurité du type
- Auto-complétion IDE

**Exemple d'utilisation** :
```java
// Avant (sans ViewBinding)
TextView text = findViewById(R.id.textView);

// Après (avec ViewBinding)
ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
setContentView(binding.getRoot());
binding.textView.setText("Hello");
```

---

## ⚠️ Notes importantes

1. **Synchroniser Gradle** après modification
   - File > Sync Project with Gradle Files

2. **Versions** : Utiliser les versions stables les plus récentes
   - Vérifier sur [Maven Repository](https://mvnrepository.com/)

3. **Internet** : Ajouter dans AndroidManifest.xml si nécessaire
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   ```

4. **Multidex** : Si l'app dépasse 64K méthodes
   ```gradle
   android {
       defaultConfig {
           multiDexEnabled true
       }
   }
   implementation 'androidx.multidex:multidex:2.0.1'
   ```

---

👨‍🏫 **Configuration Gradle** | ISITCOM 2025-2026
