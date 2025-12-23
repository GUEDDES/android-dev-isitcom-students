# TD09 : API REST et Retrofit

## 🎯 Objectifs

- Consommer une API REST.
- Utiliser Retrofit pour les appels réseau.
- Afficher des données distantes dans RecyclerView.

---

## Partie 1 : Configuration Retrofit (20 min)

### Étape 1 : Ajouter les dépendances

```gradle
dependencies {
    // Retrofit
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    
    // Glide pour les images
    implementation 'com.github.bumptech.glide:glide:4.16.0'
}
```

### Étape 2 : Permission Internet

Dans `AndroidManifest.xml` :

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

### Étape 3 : Modèle de données

```java
public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    
    // Getters et setters
}
```

### Étape 4 : Interface API

```java
public interface JsonPlaceholderApi {
    
    @GET("users")
    Call<List<User>> getUsers();
    
    @GET("users/{id}")
    Call<User> getUser(@Path("id") int userId);
    
    @POST("users")
    Call<User> createUser(@Body User user);
}
```

### Étape 5 : Retrofit Client

```java
public class RetrofitClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
}
```

---

## Partie 2 : Afficher des utilisateurs (45 min)

### Consignes

Créer une application affichant la liste des utilisateurs de JSONPlaceholder.

### Code exemple : MainActivity.java

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

        JsonPlaceholderApi api = RetrofitClient.getClient().create(JsonPlaceholderApi.class);
        Call<List<User>> call = api.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                progressBar.setVisibility(View.GONE);
                
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();
                    adapter = new UserAdapter(users, MainActivity.this::showUserDetail);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MainActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Erreur : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showUserDetail(User user) {
        Intent intent = new Intent(this, UserDetailActivity.class);
        intent.putExtra("USER_ID", user.getId());
        startActivity(intent);
    }
}
```

---

## Partie 3 : Application météo (60 min)

### Consignes

Créer une application météo utilisant OpenWeatherMap API.

### Étape 1 : S'inscrire sur OpenWeatherMap

1. Aller sur <https://openweathermap.org/api>.
2. Créer un compte gratuit.
3. Générer une clé API.

### Étape 2 : Modèle Weather

```java
public class Weather {
    private Main main;
    private List<WeatherDescription> weather;
    private String name;
    
    public static class Main {
        private double temp;
        private double feels_like;
        private int humidity;
        
        // Getters et setters
    }
    
    public static class WeatherDescription {
        private String main;
        private String description;
        private String icon;
        
        // Getters et setters
    }
    
    // Getters et setters
}
```

### Étape 3 : Interface API

```java
public interface WeatherApi {
    
    @GET("weather")
    Call<Weather> getCurrentWeather(
        @Query("q") String city,
        @Query("appid") String apiKey,
        @Query("units") String units
    );
}
```

### Étape 4 : Afficher la météo

```java
private void loadWeather(String city) {
    String API_KEY = "VOTRE_CLE_API";
    
    WeatherApi api = RetrofitClient.getWeatherClient().create(WeatherApi.class);
    Call<Weather> call = api.getCurrentWeather(city, API_KEY, "metric");

    call.enqueue(new Callback<Weather>() {
        @Override
        public void onResponse(Call<Weather> call, Response<Weather> response) {
            if (response.isSuccessful() && response.body() != null) {
                Weather weather = response.body();
                textCity.setText(weather.getName());
                textTemp.setText(weather.getMain().getTemp() + "°C");
                textDescription.setText(weather.getWeather().get(0).getDescription());
                
                // Charger l'icône avec Glide
                String iconUrl = "https://openweathermap.org/img/wn/" + 
                                 weather.getWeather().get(0).getIcon() + "@2x.png";
                Glide.with(MainActivity.this).load(iconUrl).into(imageIcon);
            }
        }

        @Override
        public void onFailure(Call<Weather> call, Throwable t) {
            Toast.makeText(MainActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
        }
    });
}
```

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Configuration Retrofit | 4 |
| Affichage liste utilisateurs | 6 |
| Application météo fonctionnelle | 8 |
| Gestion des erreurs | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
