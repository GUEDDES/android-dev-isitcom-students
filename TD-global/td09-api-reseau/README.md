# TD09 : Appels API avec Retrofit

## 🎯 Objectifs

- Comprendre les requêtes HTTP dans Android
- Configurer Retrofit pour consommer une API REST
- Gérer les réponses JSON avec Gson
- Afficher des données distantes dans RecyclerView
- Gérer les erreurs réseau

---

## 📋 Prérequis

- Modules 1-8 maîtrisés
- Connaissance des API REST
- Internet actif sur l'émulateur/téléphone

---

## Partie 1 : Configuration Retrofit

### 1.1 Ajouter les dépendances

Dans `build.gradle (Module: app)` :

```gradle
dependencies {
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // OkHttp (logging)
    implementation 'com.squareup.okhttp3:logging-interceptor:4.11.0'
}
```

### 1.2 Permissions réseau

Dans `AndroidManifest.xml` :

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## Partie 2 : API publique JSONPlaceholder

Nous utiliserons https://jsonplaceholder.typicode.com/

### 2.1 Structure d'un utilisateur

```json
{
  "id": 1,
  "name": "Leanne Graham",
  "email": "Sincere@april.biz",
  "phone": "1-770-736-8031"
}
```

### 2.2 Classe de modèle

```java
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
```

---

## Partie 3 : Interface API

Créer `ApiService.java` :

```java
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import java.util.List;

public interface ApiService {
    
    @GET("users")
    Call<List<User>> getUsers();
    
    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);
}
```

---

## Partie 4 : Client Retrofit

Créer `RetrofitClient.java` :

```java
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;
    
    public static Retrofit getClient() {
        if (retrofit == null) {
            // Logging interceptor pour déboguer
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            
            OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
            
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
```

---

## Partie 5 : Effectuer un appel

Dans `MainActivity.java` :

```java
public class MainActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private ProgressBar progressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        loadUsers();
    }
    
    private void loadUsers() {
        progressBar.setVisibility(View.VISIBLE);
        
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<List<User>> call = apiService.getUsers();
        
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
                
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    adapter = new UserAdapter(users);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Erreur API", Toast.LENGTH_SHORT).show();
                }
            }
            
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Erreur réseau: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("API", "Erreur", t);
            }
        });
    }
}
```

---

## Exercice 1 : Liste d'utilisateurs

**Objectif** : Afficher tous les utilisateurs de l'API.

1. Configurer Retrofit comme ci-dessus.
2. Créer un `UserAdapter` pour RecyclerView.
3. Afficher nom + email de chaque utilisateur.
4. Ajouter un ProgressBar pendant le chargement.

---

## Exercice 2 : Détail utilisateur

**Objectif** : Afficher les détails d'un utilisateur au clic.

1. Au clic sur un utilisateur, lancer `UserDetailActivity`.
2. Passer l'ID de l'utilisateur via Intent.
3. Appeler `getUser(id)` dans la nouvelle Activity.
4. Afficher toutes les infos (nom, email, téléphone).

---

## Exercice 3 : Liste de posts

**API** : `https://jsonplaceholder.typicode.com/posts`

**Structure** :
```json
{
  "id": 1,
  "userId": 1,
  "title": "...",
  "body": "..."
}
```

**Consignes** :
1. Créer classe `Post`.
2. Ajouter endpoint dans `ApiService`.
3. Afficher la liste des posts dans RecyclerView.
4. Au clic, afficher le contenu complet.

---

## Exercice 4 : Gestion d'erreurs

**Objectif** : Améliorer la gestion des erreurs.

1. Vérifier la connexion internet avant l'appel.
2. Afficher un message différent selon l'erreur (404, 500, timeout).
3. Ajouter un bouton "Réessayer" en cas d'échec.

**Helper réseau** :
```java
public static boolean isNetworkAvailable(Context context) {
    ConnectivityManager cm = (ConnectivityManager) 
        context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
}
```

---

## Mini-TP : Application News (articles)

**API** : https://newsapi.org (créer un compte gratuit)

### Consignes

1. **Configuration** :
   - Obtenir une clé API sur newsapi.org.
   - Configurer Retrofit avec base URL `https://newsapi.org/v2/`.

2. **Modèles** :
   - `Article` : titre, description, URL, image.
   - `NewsResponse` : liste d'articles.

3. **Fonctionnalités** :
   - Afficher les top headlines (France).
   - RecyclerView avec images (utiliser Glide).
   - Au clic, ouvrir l'article dans un WebView ou navigateur.

4. **Bonus** :
   - Catégories (sport, tech, business).
   - Recherche par mot-clé.
   - SwipeRefreshLayout pour actualiser.

### Grille d'évaluation

| Critère | Points |
|--------|--------|
| Retrofit configuré correctement | 4 |
| Affichage liste articles | 4 |
| Images chargées (Glide) | 3 |
| Clic ouvre article | 3 |
| Gestion erreurs | 3 |
| Interface soignée | 3 |

**Total** : /20

---

## Ressources

- [Documentation Retrofit](https://square.github.io/retrofit/)
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/)
- [NewsAPI](https://newsapi.org/)
- [Glide pour images](https://github.com/bumptech/glide)

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
