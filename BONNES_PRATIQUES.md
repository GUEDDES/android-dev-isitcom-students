# Bonnes pratiques Android

## 🎯 Introduction

Ce guide regroupe les bonnes pratiques essentielles pour développer des applications Android de qualité.

---

## 📋 Conventions de nommage

### Packages

```java
// ✅ BON
tn.isitcom.monapp.data.model
tn.isitcom.monapp.ui.home

// ❌ MAUVAIS
tn.isitcom.MonApp.Data.Model
```

### Classes

```java
// ✅ BON - PascalCase
public class MainActivity extends AppCompatActivity {}
public class UserAdapter extends RecyclerView.Adapter<> {}
public class TaskViewModel extends ViewModel {}

// ❌ MAUVAIS
public class mainActivity {}
public class user_adapter {}
```

### Variables et méthodes

```java
// ✅ BON - camelCase
private String userName;
private int itemCount;
public void updateUserProfile() {}

// ❌ MAUVAIS
private String UserName;
private int item_count;
public void UpdateUserProfile() {}
```

### Constantes

```java
// ✅ BON - UPPER_SNAKE_CASE
public static final String TAG = "MainActivity";
public static final int MAX_ITEMS = 100;
private static final String DATABASE_NAME = "app_database";

// ❌ MAUVAIS
public static final String tag = "MainActivity";
public static final int maxItems = 100;
```

### Ressources XML

```xml
<!-- ✅ BON - snake_case avec préfixe -->
activity_main.xml
fragment_home.xml
item_user.xml
ic_email.xml
btn_submit
tv_title

<!-- ❌ MAUVAIS -->
ActivityMain.xml
home.xml
User.xml
button1
text
```

---

## 📚 Architecture

### Séparation des responsabilités

```
✅ BON - Architecture MVVM

View (Activity/Fragment)
  │
  └── ViewModel
        │
        └── Repository
              │
              ├── Room Database
              └── API Service
```

### Organisation des packages

```
✅ BON

app/
  └── src/main/java/tn/isitcom/monapp/
        ├── data/
        │   ├── model/
        │   ├── dao/
        │   ├── database/
        │   └── repository/
        ├── ui/
        │   ├── home/
        │   ├── detail/
        │   └── adapter/
        └── utils/
```

---

## ⚡ Performance

### Thread management

```java
// ❌ MAUVAIS - Opération Room sur UI thread
public void onClick(View v) {
    User user = new User("John", "Doe");
    userDao.insert(user); // Crash NetworkOnMainThreadException
}

// ✅ BON - Exécution en arrière-plan
public void onClick(View v) {
    User user = new User("John", "Doe");
    new Thread(() -> {
        userDao.insert(user);
    }).start();
}

// ✅ MIEUX - Avec Executors
public void onClick(View v) {
    User user = new User("John", "Doe");
    Executors.newSingleThreadExecutor().execute(() -> {
        userDao.insert(user);
    });
}
```

### RecyclerView

```java
// ✅ BON - ViewHolder pattern
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView avatarImageView;
        
        ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_name);
            avatarImageView = itemView.findViewById(R.id.iv_avatar);
        }
    }
    
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.nameTextView.setText(user.getName());
        // Pas de findViewById() ici !
    }
}

// ❌ MAUVAIS - findViewById dans onBindViewHolder
public void onBindViewHolder(ViewHolder holder, int position) {
    TextView nameTextView = holder.itemView.findViewById(R.id.tv_name); // Lent !
    nameTextView.setText(users.get(position).getName());
}
```

### Images

```java
// ✅ BON - Utiliser Glide pour images
Glide.with(context)
    .load(imageUrl)
    .placeholder(R.drawable.placeholder)
    .error(R.drawable.error)
    .into(imageView);

// ❌ MAUVAIS - Charger images lourdes directement
Bitmap bitmap = BitmapFactory.decodeFile(imagePath); // OutOfMemoryError possible
imageView.setImageBitmap(bitmap);
```

---

## 🛡️ Gestion des ressources

### Strings

```xml
<!-- ✅ BON - Externaliser dans strings.xml -->
<string name="welcome_message">Bienvenue %1$s !</string>
<string name="error_network">Erreur de connexion</string>
```

```java
// ✅ BON - Utiliser ressources
String message = getString(R.string.welcome_message, userName);
textView.setText(message);

// ❌ MAUVAIS - Texte en dur
textView.setText("Bienvenue " + userName + " !");
```

### Couleurs

```xml
<!-- ✅ BON - colors.xml -->
<color name="primary">#6200EE</color>
<color name="text_primary">#000000</color>
```

```xml
<!-- ✅ BON - Utiliser ressources -->
<TextView
    android:textColor="@color/text_primary" />

<!-- ❌ MAUVAIS - Couleur en dur -->
<TextView
    android:textColor="#000000" />
```

### Dimensions

```xml
<!-- ✅ BON - dimens.xml -->
<dimen name="margin_standard">16dp</dimen>
<dimen name="text_size_title">20sp</dimen>
```

---

## 🔒 Sécurité

### Ne pas hardcoder les clés

```java
// ❌ MAUVAIS
public static final String API_KEY = "1234567890abcdef";

// ✅ BON - Utiliser gradle.properties ou BuildConfig
// Dans build.gradle
android {
    defaultConfig {
        buildConfigField "String", "API_KEY", "\"${apiKey}\""
    }
}

// Dans le code
String apiKey = BuildConfig.API_KEY;
```

### Permissions

```xml
<!-- ✅ BON - Demander uniquement les permissions nécessaires -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- ❌ MAUVAIS - Demander permissions inutiles -->
<uses-permission android:name="android.permission.READ_CONTACTS" />
<uses-permission android:name="android.permission.CAMERA" />
<!-- Si l'app n'en a pas besoin -->
```

---

## 🧠 Gestion des erreurs

### Try-catch approprié

```java
// ✅ BON - Gestion spécifique
try {
    int result = Integer.parseInt(input);
} catch (NumberFormatException e) {
    Toast.makeText(this, "Veuillez entrer un nombre valide", Toast.LENGTH_SHORT).show();
    Log.e(TAG, "Erreur parsing: " + e.getMessage());
}

// ❌ MAUVAIS - Catch trop général
try {
    // code
} catch (Exception e) {
    // Masque tous les problèmes
}
```

### Logs

```java
// ✅ BON - Logs informatifs
private static final String TAG = "MainActivity";

Log.d(TAG, "onCreate: Initialisation");
Log.w(TAG, "Liste vide");
Log.e(TAG, "Erreur réseau: " + e.getMessage());

// ❌ MAUVAIS
Log.d("test", "test");
e.printStackTrace(); // Ne pas utiliser en production
```

---

## 📱 Interface utilisateur

### Dimensions

```xml
<!-- ✅ BON - dp pour dimensions, sp pour textes -->
<Button
    android:layout_width="wrap_content"
    android:layout_height="48dp"
    android:textSize="16sp" />

<!-- ❌ MAUVAIS - px en dur -->
<Button
    android:layout_height="100px"
    android:textSize="30px" />
```

### Accessibilité

```xml
<!-- ✅ BON - Description pour accessibilité -->
<ImageView
    android:contentDescription="@string/user_avatar"
    android:src="@drawable/ic_user" />

<ImageButton
    android:contentDescription="@string/btn_delete"
    android:src="@drawable/ic_delete" />

<!-- ❌ MAUVAIS -->
<ImageView
    android:src="@drawable/ic_user" />
```

### Taille cliquable

```xml
<!-- ✅ BON - Minimum 48dp pour zone tactile -->
<Button
    android:layout_width="wrap_content"
    android:layout_height="48dp" />

<!-- ❌ MAUVAIS - Trop petit -->
<Button
    android:layout_height="20dp" />
```

---

## 📦 Cycle de vie

### Ne pas garder références Activity

```java
// ❌ MAUVAIS - Fuite mémoire
public class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    private MainActivity activity; // Garde référence
    
    public MyAsyncTask(MainActivity activity) {
        this.activity = activity;
    }
}

// ✅ BON - Utiliser ViewModel
public class MyViewModel extends ViewModel {
    private MutableLiveData<String> data = new MutableLiveData<>();
    
    public LiveData<String> getData() {
        return data;
    }
}
```

### Observer avec lifecycle

```java
// ✅ BON - Observer avec lifecycle
viewModel.getData().observe(getViewLifecycleOwner(), data -> {
    // Mise à jour UI
});

// ❌ MAUVAIS - Observer sans lifecycle
viewModel.getData().observeForever(data -> {
    // Risque de fuite mémoire
});
```

---

## 📝 Code propre

### Commentaires utiles

```java
// ✅ BON - Expliquer le "pourquoi"
// On limite à 10 items pour éviter surcharge mémoire
private static final int MAX_ITEMS = 10;

// Vérification nécessaire car l'API peut retourner null
if (response.getData() != null) {
    updateUI(response.getData());
}

// ❌ MAUVAIS - Commenter l'évident
// Incrémente i
i++;

// Déclare une variable
int count = 0;
```

### Méthodes courtes

```java
// ✅ BON - Méthodes courtes et focusées
private void validateEmail(String email) {
    if (!email.contains("@")) {
        showError("Email invalide");
    }
}

private void validatePassword(String password) {
    if (password.length() < 6) {
        showError("Mot de passe trop court");
    }
}

// ❌ MAUVAIS - Méthode trop longue (50+ lignes)
private void validateForm() {
    // 100 lignes de validation...
}
```

---

## ✅ Checklist avant commit

- [ ] Code compil sans erreur ni warning
- [ ] Pas de code commenté inutilisé
- [ ] Imports optimisés (Ctrl+Alt+O)
- [ ] Code formaté (Ctrl+Alt+L)
- [ ] Strings externalisés
- [ ] Logs de debug retirés
- [ ] App testée sur émulateur
- [ ] Pas d'erreur dans Logcat

---

## 📚 Références

- [Android Best Practices](https://developer.android.com/topic/architecture/recommendations)
- [Material Design Guidelines](https://m3.material.io/)
- [Kotlin Style Guide](https://developer.android.com/kotlin/style-guide)

---

👨‍🏫 **Bonnes Pratiques Android** | ISITCOM 2025-2026
