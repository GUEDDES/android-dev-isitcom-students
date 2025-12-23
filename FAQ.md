# FAQ - Questions fréquentes

## 🎯 Introduction

Ce document répond aux questions les plus fréquentes des étudiants.

---

## 🛠️ Installation et configuration

### Q : Android Studio est trop lent, comment accélérer ?

**R :** Plusieurs solutions :

1. **Augmenter la mémoire allouée**
   - `Help > Edit Custom VM Options`
   - Changer `-Xmx2048m` en `-Xmx4096m`

2. **Désactiver plugins inutiles**
   - `File > Settings > Plugins`
   - Décocher plugins non utilisés

3. **Activer Offline Mode**
   - `File > Settings > Build > Gradle`
   - Cocher "Offline work"

4. **Utiliser un SSD** pour le projet

### Q : "SDK not found" après installation ?

**R :** 
1. `File > Project Structure > SDK Location`
2. Vérifier le chemin du SDK
3. Si vide, installer via `Tools > SDK Manager`
4. Redémarrer Android Studio

### Q : L'émulateur ne démarre pas ?

**R :**
- **Vérifier virtualisation activée** dans BIOS
- **Windows** : Désactiver Hyper-V
- **Alternative** : Utiliser appareil physique via USB

---

## 🐞 Débogage

### Q : "App keeps stopping" - Comment déboguer ?

**R :**
1. **Lire Logcat** : `Alt + 6`
2. Chercher ligne en rouge avec l'erreur
3. Identifier la classe et ligne du crash
4. Erreurs courantes :
   - `NullPointerException` : Variable null
   - `NetworkOnMainThreadException` : Opération réseau sur UI thread
   - `SQLiteException` : Problème base de données

### Q : Mon TextView ne s'affiche pas ?

**R :** Vérifier :
- [ ] `android:visibility="visible"`
- [ ] Couleur texte différente du fond
- [ ] Contraintes ConstraintLayout correctes
- [ ] `setText()` appelé dans le code
- [ ] ID correct dans `findViewById()`

### Q : findViewById() retourne null ?

**R :**
- **Vérifier** que l'ID existe dans le layout actif
- **Vérifier** `setContentView()` appelé avant `findViewById()`
- **Clean & Rebuild** : `Build > Clean Project`

---

## 📱 Interface utilisateur

### Q : Comment centrer un élément dans ConstraintLayout ?

**R :**
```xml
<TextView
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent" />
```

### Q : Quelle différence entre dp et sp ?

**R :**
- **dp** : Dimensions (margins, paddings, largeurs...)
- **sp** : Tailles de texte (respecte préférences utilisateur)

### Q : RecyclerView vide ne s'affiche pas ?

**R :**
```java
// Vérifier :
recyclerView.setLayoutManager(new LinearLayoutManager(this)); // ✅
recyclerView.setAdapter(adapter); // ✅
adapter.notifyDataSetChanged(); // Après changement données
```

---

## 📦 Base de données Room

### Q : "Cannot access database on the main thread" ?

**R :** Room interdit opérations sur UI thread.

```java
// ❌ MAUVAIS
userDao.insert(user);

// ✅ BON
new Thread(() -> {
    userDao.insert(user);
}).start();

// ✅ MIEUX
Executors.newSingleThreadExecutor().execute(() -> {
    userDao.insert(user);
});
```

### Q : Données ne s'affichent pas après insertion ?

**R :** Utiliser **LiveData** :

```java
// DAO
@Query("SELECT * FROM users")
LiveData<List<User>> getAllUsers();

// Activity
viewModel.getAllUsers().observe(this, users -> {
    adapter.setUsers(users);
});
```

### Q : Comment modifier le schéma de la base ?

**R :**
1. Modifier Entity
2. **Incrémenter version** dans `@Database`
3. Désinstaller app (ou implémenter Migration)

```java
@Database(entities = {User.class}, version = 2) // ✅ Incrémenter
public abstract class AppDatabase extends RoomDatabase {
    // ...
}
```

---

## 🧩 Navigation

### Q : Passer des données entre Activities ?

**R :**

**Activity 1 :**
```java
Intent intent = new Intent(this, DetailActivity.class);
intent.putExtra("USER_ID", userId);
intent.putExtra("USER_NAME", userName);
startActivity(intent);
```

**Activity 2 :**
```java
int userId = getIntent().getIntExtra("USER_ID", -1);
String userName = getIntent().getStringExtra("USER_NAME");
```

### Q : Passer objet complexe entre Activities ?

**R :** Implémenter `Parcelable` ou passer uniquement l'ID.

```java
// ✅ Recommandé - Passer ID
intent.putExtra("USER_ID", user.getId());

// Dans Activity 2, récupérer depuis Room
int userId = getIntent().getIntExtra("USER_ID", -1);
viewModel.getUserById(userId).observe(this, user -> {
    // Utiliser user
});
```

### Q : Fragment ne s'affiche pas ?

**R :** Vérifier :

```java
// Navigation Component correctement configuré
// NavHostFragment défini dans XML
<fragment
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    app:navGraph="@navigation/nav_graph" />

// Navigation correcte
NavController navController = Navigation.findNavController(view);
navController.navigate(R.id.detailFragment);
```

---

## ⚡ Performance

### Q : App lente, comment optimiser ?

**R :**

1. **Profiler** : `View > Tool Windows > Profiler`
2. **Identifier** goulots d'étranglement
3. **Solutions courantes** :
   - Charger images avec Glide
   - Utiliser RecyclerView au lieu ListView
   - Pas d'opérations lourdes sur UI thread
   - Limiter requêtes réseau

### Q : OutOfMemoryError avec images ?

**R :** Utiliser **Glide** :

```java
Glide.with(context)
    .load(imageUrl)
    .override(300, 300) // Redimensionner
    .into(imageView);
```

---

## 🔧 Gradle et dépendances

### Q : "Failed to resolve" lors du sync Gradle ?

**R :**

1. **Vérifier connexion internet**
2. **Invalider cache** : `File > Invalidate Caches > Invalidate and Restart`
3. **Vérifier version** de la dépendance existe
4. **Ajouter repository** dans `settings.gradle` :

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
```

### Q : Quelle version utiliser pour une dépendance ?

**R :** Vérifier sur :
- [Maven Central](https://search.maven.org/)
- [Android Developers](https://developer.android.com/jetpack/androidx/versions)

---

## 🔒 Permissions

### Q : Comment demander une permission à l'exécution ?

**R :** Pour Android 6.0+ :

```java
if (ContextCompat.checkSelfPermission(this, 
        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
    
    ActivityCompat.requestPermissions(this,
            new String[]{Manifest.permission.CAMERA},
            REQUEST_CODE);
} else {
    // Permission déjà accordée
    openCamera();
}

@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    if (requestCode == REQUEST_CODE) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera();
        }
    }
}
```

---

## 🎨 Material Design

### Q : Comment ajouter Material Design à mon projet ?

**R :** 

**build.gradle (app)** :
```gradle
dependencies {
    implementation 'com.google.android.material:material:1.11.0'
}
```

**themes.xml** :
```xml
<style name="AppTheme" parent="Theme.Material3.Light">
    <!-- Couleurs -->
</style>
```

### Q : FloatingActionButton ne s'affiche pas ?

**R :** Vérifier contraintes :

```xml
<com.google.android.material.floatingactionbutton.FloatingActionButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_margin="16dp" />
```

---

## 🌐 Réseau et API

### Q : Comment appeler une API REST ?

**R :** Utiliser **Retrofit** :

**1. build.gradle** :
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

**2. Interface API** :
```java
public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
```

**3. Appel** :
```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("https://api.example.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

ApiService apiService = retrofit.create(ApiService.class);
apiService.getUsers().enqueue(new Callback<List<User>>() {
    @Override
    public void onResponse(Call<List<User>> call, Response<List<User>> response) {
        if (response.isSuccessful()) {
            List<User> users = response.body();
        }
    }
    
    @Override
    public void onFailure(Call<List<User>> call, Throwable t) {
        Log.e(TAG, "Erreur: " + t.getMessage());
    }
});
```

---

## 📱 Tests

### Q : Comment tester sur appareil physique ?

**R :**

1. **Activer mode développeur** :
   - Paramètres > À propos > Taper 7x "Numéro de build"

2. **Activer débogage USB** :
   - Paramètres > Options développeur > Débogage USB

3. **Connecter USB** et autoriser ordinateur

4. **Vérifier** : `adb devices` dans Terminal

### Q : "Device unauthorized" dans adb ?

**R :**
- **Autoriser** sur téléphone
- **Redémarrer adb** : `adb kill-server` puis `adb start-server`

---

## 📦 Déploiement

### Q : Comment générer un APK ?

**R :**

1. `Build > Build Bundle(s) / APK(s) > Build APK(s)`
2. Attendre fin compilation
3. Cliquer "locate" dans notification
4. APK dans `app/build/outputs/apk/debug/`

### Q : Différence entre debug et release APK ?

**R :**

| Debug | Release |
|-------|----------|
| Non signé | Signé avec clé |
| Debuggable | Optimisé (ProGuard) |
| Test uniquement | Production |
| Plus gros | Plus petit |

---

## 👥 Aide supplémentaire

### Q : Où trouver de l'aide ?

**R :**

1. **Documentation officielle** : [developer.android.com](https://developer.android.com)
2. **Stack Overflow** : [stackoverflow.com/questions/tagged/android](https://stackoverflow.com/questions/tagged/android)
3. **GitHub du cours** : Issues et Discussions
4. **Forums** : Reddit r/androiddev
5. **Enseignant** : abdelwaheb.gueddes@isitc.u-sousse.tn

---

👨‍🏫 **FAQ Android** | ISITCOM 2025-2026
