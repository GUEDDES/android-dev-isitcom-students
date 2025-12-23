# Module 10 : Ressources complémentaires

## 📚 Architecture Android

### Documentation officielle
- [Guide Architecture Android](https://developer.android.com/topic/architecture) - Recommandations Google
- [MVVM Pattern](https://developer.android.com/topic/libraries/architecture/viewmodel) - ViewModel
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Données observables
- [Repository Pattern](https://developer.android.com/codelabs/android-room-with-a-view) - Codelab officiel

### Articles architecture
- [Android Architecture Components](https://medium.com/androiddevelopers/viewmodels-and-livedata-patterns-antipatterns-21efaef74a54)
- [MVVM vs MVP vs MVC](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture)
- [Clean Architecture Android](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

## 🛠️ Bibliothèques essentielles

### Architecture Components
```gradle
// ViewModel et LiveData
implementation "androidx.lifecycle:lifecycle-viewmodel:2.7.0"
implementation "androidx.lifecycle:lifecycle-livedata:2.7.0"

// Navigation
implementation "androidx.navigation:navigation-fragment:2.7.6"
implementation "androidx.navigation:navigation-ui:2.7.6"

// Room
implementation "androidx.room:room-runtime:2.6.1"
annotationProcessor "androidx.room:room-compiler:2.6.1"
```

### Injection de dépendances

#### Hilt (recommandé)
```gradle
implementation "com.google.dagger:hilt-android:2.50"
annotationProcessor "com.google.dagger:hilt-compiler:2.50"
```

Exemple :
```java
@HiltAndroidApp
public class MyApplication extends Application { }

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity { 
    @Inject
    TaskRepository repository;
}
```

### Réseau

#### Retrofit
```gradle
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```

#### OkHttp (logging)
```gradle
implementation 'com.squareup.okhttp3:logging-interceptor:4.12.0'
```

---

## 🎬 Tutoriels vidéo

### MVVM Architecture
- [Coding in Flow - MVVM Tutorial](https://www.youtube.com/playlist?list=PLrnPJCHvNZuDihTpkRs6SpZhqgBqPU118)
- [Philipp Lackner - Clean Architecture](https://www.youtube.com/watch?v=gI2sPFz6HjQ)
- [Android Developers - Architecture Components](https://www.youtube.com/watch?v=ARpn-1FPNE4)

### Room Database
- [Room Database Tutorial](https://www.youtube.com/watch?v=ONb_MuPBLP8)
- [Room + ViewModel + LiveData](https://www.youtube.com/watch?v=HhmA9S53XV8)

### Navigation Component
- [Navigation Component Tutorial](https://www.youtube.com/watch?v=IEO2X5OU3MY)
- [Bottom Navigation with Fragments](https://www.youtube.com/watch?v=tPV8xA7m-iw)

---

## 📝 Articles avancés

### Architecture
- [Android Architecture Blueprints](https://github.com/android/architecture-samples) - Exemples officiels
- [SOLID Principles in Android](https://www.raywenderlich.com/books/advanced-android-app-architecture)
- [Dependency Injection Best Practices](https://developer.android.com/training/dependency-injection)

### Patterns
- [Repository Pattern Explained](https://proandroiddev.com/the-real-repository-pattern-in-android-efba8662b754)
- [UseCase Pattern](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Observer Pattern with LiveData](https://medium.com/androiddevelopers/livedata-with-coroutines-and-flow-part-i-reactive-uis-b20f676d25d7)

---

## 📖 Livres recommandés

1. **"Android Architecture Components"** - Razeware Team
2. **"Clean Architecture"** - Robert C. Martin (Uncle Bob)
3. **"Head First Design Patterns"** - Eric Freeman
4. **"Effective Java"** - Joshua Bloch

---

## 💻 Exemples de projets GitHub

### Architecture exemplaire
- [Now in Android](https://github.com/android/nowinandroid) - App officielle Google
- [Sunflower](https://github.com/android/sunflower) - Best practices Android
- [Architecture Samples](https://github.com/android/architecture-samples) - Différentes architectures
- [Plaid](https://github.com/android/plaid) - Material Design + Architecture

### Projets complets
- [Corona-Warn-App](https://github.com/corona-warn-app/cwa-app-android) - App COVID allemande
- [Tivi](https://github.com/chrisbanes/tivi) - Tracking séries TV
- [Simple Mobile Tools](https://github.com/SimpleMobileTools) - Suite d'apps utilitaires

---

## 🧩 Testing

### Unit Tests
```gradle
testImplementation 'junit:junit:4.13.2'
testImplementation 'org.mockito:mockito-core:5.8.0'
```

Exemple :
```java
@Test
public void testAddTask() {
    Task task = new Task("Test", "Description");
    repository.insert(task);
    verify(dao).insert(task);
}
```

### Instrumented Tests
```gradle
androidTestImplementation 'androidx.test.ext:junit:1.1.5'
androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
```

### Room Testing
```java
@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private AppDatabase database;
    
    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }
    
    @After
    public void closeDb() {
        database.close();
    }
}
```

---

## ⚙️ Outils de développement

### Android Studio Plugins
- **ADB Idea** : Commandes ADB rapides
- **Key Promoter X** : Apprendre raccourcis
- **Rainbow Brackets** : Colorer parenthèses
- **SonarLint** : Détecter bugs

### Débogage
```bash
# Voir logs Room
adb shell setprop log.tag.RoomDatabase DEBUG

# Exporter base de données
adb exec-out run-as com.example.app cat /databases/app.db > app.db

# Viewer SQLite
# Télécharger DB Browser for SQLite
```

### Profiling
- **Memory Profiler** : Détecter fuites mémoire
- **CPU Profiler** : Analyser performances
- **Network Profiler** : Surveiller requêtes

---

## 💡 Bonnes pratiques architecture

### Séparation des responsabilités

```
View (UI)
  │
  └── ViewModel (Logique présentation)
        │
        └── UseCase/Interactor (Logique métier) [Optionnel]
              │
              └── Repository (Source de données)
                    │
                    ├── Local Data Source (Room)
                    └── Remote Data Source (API)
```

### Principes SOLID

1. **Single Responsibility** : Une classe = une responsabilité
2. **Open/Closed** : Ouvert extension, fermé modification
3. **Liskov Substitution** : Sous-classes substituables
4. **Interface Segregation** : Interfaces spécifiques
5. **Dependency Inversion** : Dépendre d'abstractions

### Gestion d'erreurs

```java
public class Resource<T> {
    public enum Status { SUCCESS, ERROR, LOADING }
    
    private Status status;
    private T data;
    private String message;
    
    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }
    
    public static <T> Resource<T> error(String message) {
        return new Resource<>(Status.ERROR, null, message);
    }
    
    public static <T> Resource<T> loading() {
        return new Resource<>(Status.LOADING, null, null);
    }
}
```

Utilisation :
```java
viewModel.getUsers().observe(this, resource -> {
    switch (resource.status) {
        case SUCCESS:
            adapter.setData(resource.data);
            break;
        case ERROR:
            Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show();
            break;
        case LOADING:
            progressBar.setVisibility(View.VISIBLE);
            break;
    }
});
```

---

## 🔍 Code review checklist

### Architecture
☐ Séparation claire View/ViewModel/Repository  
☐ Pas de logique métier dans la View  
☐ ViewModel ne référence pas de contexte Android  
☐ Repository unique source de vérité  

### Room
☐ Entités correctement annotées  
☐ Indices sur colonnes fréquemment interrogées  
☐ Opérations sur thread secondaire  
☐ Migrations prévues pour changements schéma  

### LiveData
☐ Observation dans onStart/onCreate  
☐ Pas d'observations multiples du même LiveData  
☐ Méthodes setValue/postValue utilisées correctement  

### Code quality
☐ Nommage clair et cohérent  
☐ Pas de code dupliqué  
☐ Gestion d'erreurs appropriée  
☐ Commentaires uniquement si nécessaire  

---

## 🎓 Cours en ligne

### Gratuits
- [Google Codelabs Android](https://codelabs.developers.google.com/?cat=Android)
- [Android Basics by Google](https://developer.android.com/courses/android-basics-compose/course)
- [Kotlin for Android](https://developer.android.com/courses/android-basics-kotlin/course)

### Payants (qualité)
- [Udacity - Developing Android Apps](https://www.udacity.com/course/developing-android-apps--ud853)
- [Udemy - Complete Android Development](https://www.udemy.com/course/complete-android-n-developer-course/)
- [Pluralsight - Android Path](https://www.pluralsight.com/paths/android)

---

## 🌐 Communautés

### Forums
- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)
- [Reddit - r/androiddev](https://www.reddit.com/r/androiddev/)
- [Android Developers Discord](https://discord.gg/android-dev)

### Newsletters
- [Android Weekly](https://androidweekly.net/)
- [Kotlin Weekly](http://www.kotlinweekly.net/)
- [AndroidSweets](https://androidsweets.ongoodbits.com/)

---

👨‍🏫 **Module 10 - Projets de synthèse** | ISITCOM 2025-2026
