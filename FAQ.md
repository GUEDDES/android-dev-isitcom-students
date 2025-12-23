# FAQ - Questions Fréquentes

## 👤 Général

### Puis-je suivre ce cours si je suis débutant en Java ?

Oui, mais des bases en Java sont recommandées. Si vous êtes débutant complet, commencez par :
1. Variables, types, opérateurs
2. Conditions (if/else, switch)
3. Boucles (for, while)
4. Méthodes
5. Classes et objets

**Ressources** : [Learn Java](https://www.learnjavaonline.org/)

### Quel est le prérequis matériel ?

**Minimum** :
- Windows 10/11, macOS 10.14+, ou Linux
- 8 GB RAM
- 8 GB espace disque
- Processeur Intel i5 ou équivalent

**Recommandé** :
- 16 GB RAM
- SSD avec 20 GB libre
- Processeur Intel i7 ou équivalent

### Combien de temps prend le cours ?

Le cours est conçu pour **14 semaines** :
- 3h de cours/semaine
- 2-4h de TD/semaine
- Total : ~70 heures

---

## 🛠️ Installation et configuration

### Android Studio est très lent, que faire ?

1. **Augmenter mémoire allouée** :
   - Help → Edit Custom VM Options
   - Modifier : `-Xmx4096m` (4 GB)

2. **Désactiver plugins inutiles** :
   - File → Settings → Plugins
   - Désactiver ceux non utilisés

3. **Activer Offline Mode** (si pas besoin de Gradle sync) :
   - File → Settings → Build, Execution, Deployment → Gradle
   - Cocher "Offline work"

4. **Vider cache** :
   - File → Invalidate Caches → Invalidate and Restart

### L'émulateur ne démarre pas

**Vérifier virtualisation** :

**Windows** :
- Ouvrir Gestionnaire des tâches → Performance
- Vérifier "Virtualisation : Activée"
- Si désactivée : activer dans BIOS (Intel VT-x / AMD-V)

**macOS** : Virtualisation toujours active

**Linux** :
```bash
grep -E 'vmx|svm' /proc/cpuinfo
```

**Alternative** : Utiliser un appareil physique en USB debugging

### Erreur "SDK not found"

File → Project Structure → SDK Location  
Définir manuellement le chemin vers Android SDK

---

## 💻 Erreurs fréquentes

### "R cannot be resolved"

**Causes** :
1. Erreur dans fichier XML (layout, colors, strings)
2. Import incorrect : `import android.R;` (supprimer)
3. Gradle sync pas terminé

**Solutions** :
1. Vérifier erreurs XML (red underlines)
2. Build → Clean Project + Rebuild Project
3. Supprimer ligne `import android.R;`
4. Gradle sync

### "Unable to resolve dependency"

**Cause** : Problème de connexion ou dépendance introuvable

**Solutions** :
1. Vérifier connexion Internet
2. File → Settings → Gradle → Décocher "Offline work"
3. Essayer repository alternatif :
   ```gradle
   repositories {
       google()
       mavenCentral()
       maven { url 'https://jitpack.io' }
   }
   ```

### "App keeps stopping" sur l'émulateur

**Cause** : Exception non gérée

**Solution** :
1. Ouvrir Logcat (View → Tool Windows → Logcat)
2. Chercher ligne rouge avec "Exception"
3. Lire le message d'erreur et stack trace
4. Corriger l'erreur dans le code

### NullPointerException

**Cause** : Tentative d'utiliser un objet `null`

**Exemple** :
```java
TextView text = findViewById(R.id.text);
text.setText("Hello"); // Erreur si text == null
```

**Solution** :
```java
TextView text = findViewById(R.id.text);
if (text != null) {
    text.setText("Hello");
}
```

---

## 📊 Layouts et UI

### Mon layout ne s'affiche pas correctement

1. **Preview vs Device** : Tester sur émulateur/device réel
2. **ConstraintLayout** : Vérifier toutes contraintes définies
3. **match_parent vs wrap_content** : Vérifier tailles
4. **Orientation** : Tester portrait ET paysage

### Comment centrer un élément ?

**ConstraintLayout** :
```xml
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"
```

**LinearLayout** :
```xml
android:layout_gravity="center"
```

### Les icônes ne s'affichent pas

1. Vérifier chemin : `android:src="@drawable/ic_name"`
2. Vérifier que fichier existe dans `res/drawable/`
3. Utiliser VectorDrawable (SVG) plutôt que PNG
4. Gradle sync

---

## 💾 Room Database

### "Cannot access database on the main thread"

**Erreur** : Opération Room sur UI thread

**Solution** :
```java
// Mauvais
dao.insert(item);

// Bon
new Thread(() -> dao.insert(item)).start();

// Ou avec Executor
Executors.newSingleThreadExecutor().execute(() -> {
    dao.insert(item);
});
```

### Les données ne se sauvegardent pas

**Vérifications** :
1. Méthode `insert()` appelée ?
2. Opération sur thread secondaire ?
3. Database singleton correctement implémenté ?
4. @PrimaryKey défini ?

### Comment voir la base de données ?

**Méthode 1 : Database Inspector** (Android Studio Bumblebee+)
- View → Tool Windows → App Inspection → Database Inspector

**Méthode 2 : Export manuel**
```bash
adb exec-out run-as com.example.app cat /databases/app.db > app.db
```
Puis ouvrir avec [DB Browser for SQLite](https://sqlitebrowser.org/)

---

## 🧵 Navigation et Fragments

### Fragment s'affiche en double

**Cause** : Transaction ajoutée plusieurs fois

**Solution** :
```java
if (savedInstanceState == null) {
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, new HomeFragment())
        .commit();
}
```

### Navigation ne fonctionne pas

**Vérifications** :
1. `nav_graph.xml` correctement configuré
2. `NavHostFragment` dans layout Activity
3. ID des fragments correspondent
4. Dépendances Navigation ajoutées

---

## 🔌 Intents et Activities

### Intent ne passe pas de données

**Vérifier** :
```java
// Activity 1
Intent intent = new Intent(this, SecondActivity.class);
intent.putExtra("key", "value");
startActivity(intent);

// Activity 2 - Bonne clé
String value = getIntent().getStringExtra("key");
```

⚠️ Les clés doivent correspondre exactement !

### Activity redémarre lors de rotation écran

**Normal** : Comportement par défaut Android

**Solutions** :

1. **Sauvegarder état** (recommandé) :
```java
@Override
protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString("key", value);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
        value = savedInstanceState.getString("key");
    }
}
```

2. **Verrouiller orientation** (déconseillé) :
```xml
<activity
    android:name=".MainActivity"
    android:screenOrientation="portrait" />
```

---

## 🎨 Material Design

### Dark mode ne fonctionne pas

**Vérifier** :
1. `values-night/themes.xml` existe
2. Thème hérite de `Theme.Material3.Dark`
3. Couleurs adaptées définies
4. Redémarrer app après changement

### Bouton Material ne s'affiche pas

**Cause** : Thème non Material

**Solution** :
```xml
<!-- themes.xml -->
<style name="AppTheme" parent="Theme.Material3.Light">
    <!-- ... -->
</style>
```

---

## 🛡️ Débogage

### Comment ajouter des logs ?

```java
import android.util.Log;

private static final String TAG = "MainActivity";

Log.d(TAG, "Debug message");
Log.i(TAG, "Info message");
Log.w(TAG, "Warning message");
Log.e(TAG, "Error message");
```

Voir dans Logcat : Filtrer par TAG

### Comment utiliser le débogueur ?

1. Ajouter **breakpoint** : Clic gauche dans marge code
2. Lancer en mode debug : 🐞 (Debug icon)
3. Exécution s'arrête au breakpoint
4. **Step Over** (F8) : Ligne suivante
5. **Step Into** (F7) : Entrer dans méthode
6. **Resume** (F9) : Continuer jusqu'au prochain breakpoint

---

## 📚 Ressources complémentaires

### Où trouver de l'aide ?

1. **Documentation officielle** : [developer.android.com](https://developer.android.com/)
2. **Stack Overflow** : [stackoverflow.com/questions/tagged/android](https://stackoverflow.com/questions/tagged/android)
3. **Reddit** : [r/androiddev](https://www.reddit.com/r/androiddev/)
4. **Discord Android Developers** : [discord.gg/android-dev](https://discord.gg/android-dev)

### Tutoriels vidéo recommandés

- [Coding in Flow](https://www.youtube.com/@codinginflow)
- [Philipp Lackner](https://www.youtube.com/@PhilippLackner)
- [Android Developers](https://www.youtube.com/@AndroidDevelopers)

### Puis-je utiliser Kotlin au lieu de Java ?

Oui ! Kotlin est recommandé par Google. La syntaxe est différente mais les concepts sont identiques.

**Exemple comparaison** :

```java
// Java
public class User {
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
```

```kotlin
// Kotlin
data class User(val name: String)
```

---

## 📞 Contact

### Comment signaler une erreur dans le cours ?

Voir [CONTRIBUTING.md](CONTRIBUTING.md)

### Puis-je contribuer au cours ?

Oui ! Pull requests bienvenues. Voir [CONTRIBUTING.md](CONTRIBUTING.md)

---

## 🎯 Conseils de réussite

1. **Pratiquer régulièrement** : Coder tous les jours
2. **Lire les messages d'erreur** : Ils contiennent souvent la solution
3. **Utiliser documentation** : developer.android.com
4. **Copier/coller avec compréhension** : Ne pas copier aveuglément
5. **Tester fréquemment** : Sur émulateur ET device réel
6. **Versionner code** : Utiliser Git (GitHub, GitLab...)

---

👨‍🏫 **Cours Android** | ISITCOM 2025-2026

_Dernière mise à jour : Décembre 2025_
