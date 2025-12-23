# Module 3 : Fiche de synthèse

## 🎯 Objectifs essentiels

- Créer un projet Android avec Android Studio
- Comprendre la structure d'un projet Android
- Modifier une interface en XML et en Java
- Exécuter une application sur émulateur ou appareil réel

---

## 📁 Structure d'un projet Android

```
HelloIsitcom/
├── app/
│   ├── manifests/
│   │   └── AndroidManifest.xml         # Configuration app
│   ├── java/
│   │   └── tn.isitcom.helloisitcom/
│   │       └── MainActivity.java       # Code Java
│   └── res/
│       ├── layout/
│       │   └── activity_main.xml       # Interface XML
│       ├── values/
│       │   ├── strings.xml             # Textes
│       │   └── colors.xml              # Couleurs
│       └── drawable/                   # Images
└── Gradle Scripts/
    └── build.gradle (Module: app)      # Configuration build
```

---

## 🔑 Concepts clés

### Activity
- Représente un écran de l'application
- Hérite de `AppCompatActivity`
- Point d'entrée : `onCreate()`

### onCreate()
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);  // Lie XML
}
```

### findViewById()
```java
TextView text = findViewById(R.id.textWelcome);
text.setText("Nouveau texte");
```

### Classe R
- Générée automatiquement par Android
- Contient les IDs de toutes les ressources
- `R.layout.activity_main`, `R.id.textWelcome`

---

## 📝 Attributs XML essentiels

```xml
android:id="@+id/monId"              <!-- Identifiant unique -->
android:layout_width="match_parent"  <!-- Largeur plein écran -->
android:layout_height="wrap_content" <!-- Hauteur contenu -->
android:text="Mon texte"             <!-- Texte affiché -->
android:textSize="18sp"              <!-- Taille police -->
android:textColor="#FF0000"          <!-- Couleur texte -->
```

---

## 🎨 Widgets de base

| Widget | Usage |
|--------|-------|
| `TextView` | Afficher du texte |
| `EditText` | Saisir du texte |
| `Button` | Bouton cliquable |
| `ImageView` | Afficher une image |
| `CheckBox` | Case à cocher |

---

## ⚡ Actions utilisateur

### Clic sur un bouton
```java
Button btn = findViewById(R.id.btnClick);
btn.setOnClickListener(v -> {
    // Code exécuté au clic
    Toast.makeText(this, "Clic!", Toast.LENGTH_SHORT).show();
});
```

### Toast (message temporaire)
```java
Toast.makeText(context, "Message", Toast.LENGTH_SHORT).show();
```

---

## 🔧 Gradle

### build.gradle (Module: app)
```gradle
android {
    compileSdk 35
    defaultConfig {
        applicationId "tn.isitcom.helloisitcom"
        minSdk 24
        targetSdk 35
    }
}
```

- **compileSdk** : Version SDK pour compiler
- **minSdk** : Version Android minimale supportée
- **targetSdk** : Version Android cible

---

## 🚀 Exécution

### Sur émulateur (AVD)
1. Device Manager → Create Device
2. Choisir modèle + API
3. Run ▶ → Choisir AVD

### Sur appareil réel
1. Activer Options développeur
2. Activer Débogage USB
3. Connecter par câble
4. Run ▶ → Choisir appareil

---

## ⚠️ Erreurs fréquentes

| Erreur | Solution |
|--------|----------|
| Cannot resolve symbol R | Clean + Rebuild Project |
| App crashes au lancement | Vérifier Logcat pour l'exception |
| findViewById retourne null | Vérifier ID dans XML |
| Émulateur lent | Réduire RAM ou résolution AVD |

---

## 📚 À retenir

✅ `setContentView()` lie Java et XML  
✅ `findViewById()` récupère une vue  
✅ `@+id/` crée un nouvel ID en XML  
✅ `R.id.` référence un ID en Java  
✅ Toujours appeler `super.` dans `onCreate()`  
✅ Synchroniser Gradle après modification build.gradle  

---

👨‍🏫 **Module 3 - Premier projet Android** | ISITCOM 2025-2026
