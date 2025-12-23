# 🔄 Projet Démo : Cycle de Vie Android Complet

> **Objectif** : Comprendre le cycle de vie des Activities en observant tous les callbacks lors de la navigation entre deux écrans.

## 📋 Description du projet

Ce projet démontre **tous les callbacks du cycle de vie** de deux Activities :
- **MainActivity** : Écran principal avec bouton pour lancer SecondActivity
- **SecondActivity** : Écran secondaire avec bouton retour

### 🎯 Fonctionnalités

✅ **Redéfinition de TOUS les callbacks** de cycle de vie  
✅ **Logs détaillés** dans Logcat pour suivre chaque étape  
✅ **Compteur de lancements** pour observer `onSaveInstanceState()`  
✅ **Interface Material Design** moderne  
✅ **Affichage en temps réel** de l'état actuel  

---

## 📱 Captures d'écran des états

```
[MainActivity] → onCreate() → onStart() → onResume() → ACTIF
       ↓ (clic bouton)
[MainActivity] → onPause() → onStop()
[SecondActivity] → onCreate() → onStart() → onResume() → ACTIF
       ↓ (bouton retour)
[SecondActivity] → onPause() → onStop() → onDestroy()
[MainActivity] → onRestart() → onStart() → onResume() → ACTIF
```

---

## 🗂️ Structure du projet

```
app/src/main/
├── java/tn/isitcom/cycledevie/
│   ├── MainActivity.java          # Activity principale
│   └── SecondActivity.java        # Activity secondaire
├── res/
│   ├── layout/
│   │   ├── activity_main.xml      # Layout MainActivity
│   │   └── activity_second.xml    # Layout SecondActivity
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── drawable/
│       └── ic_launcher_foreground.xml
└── AndroidManifest.xml
```

---

## 📝 Fichiers du projet

### 1️⃣ **MainActivity.java** - Activity principale

```java
package tn.isitcom.cycledevie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    // Tag pour les logs
    private static final String TAG = "MainActivity";
    
    // Clé pour sauvegarder l'état
    private static final String KEY_COUNTER = "counter";
    
    // Vues
    private TextView tvStatus;
    private TextView tvCounter;
    private TextView tvLogs;
    private MaterialCardView cardStatus;
    private Button btnLaunchSecond;
    
    // Compteur pour démontrer onSaveInstanceState
    private int launchCounter = 0;
    
    // StringBuilder pour accumuler les logs
    private StringBuilder logsBuilder = new StringBuilder();

    // ==================== CYCLE DE VIE ====================

    /**
     * 1. onCreate() - PREMIÈRE méthode appelée à la création
     * - Appelé UNE SEULE FOIS lors de la création de l'Activity
     * - Initialiser l'interface utilisateur (setContentView)
     * - Récupérer les références des vues (findViewById)
     * - Initialiser les variables
     * - Restaurer l'état sauvegardé si rotation
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        logLifecycle("onCreate()", "Activity créée - Initialisation UI");
        
        // Charger le layout
        setContentView(R.layout.activity_main);
        
        // Initialiser les vues
        initViews();
        
        // Restaurer l'état sauvegardé (rotation, kill processus)
        if (savedInstanceState != null) {
            launchCounter = savedInstanceState.getInt(KEY_COUNTER, 0);
            logLifecycle("onCreate()", "État restauré - Counter = " + launchCounter);
        }
        
        // Configurer les listeners
        setupListeners();
        
        // Mettre à jour l'affichage
        updateUI("CREATED", Color.parseColor("#FFC107")); // Orange
        updateCounter();
    }

    /**
     * 2. onStart() - Activity devient VISIBLE
     * - Appelé après onCreate() ou après onRestart()
     * - Activity visible mais pas encore au premier plan
     * - Préparer les ressources nécessaires
     */
    @Override
    protected void onStart() {
        super.onStart();
        logLifecycle("onStart()", "Activity visible (mais pas encore active)");
        updateUI("VISIBLE", Color.parseColor("#2196F3")); // Bleu
    }

    /**
     * 3. onResume() - Activity devient ACTIVE (au premier plan)
     * - Appelé juste avant que l'utilisateur puisse interagir
     * - L'Activity est au premier plan
     * - Démarrer animations, écouter capteurs, etc.
     */
    @Override
    protected void onResume() {
        super.onResume();
        logLifecycle("onResume()", "Activity ACTIVE - Utilisateur peut interagir");
        updateUI("ACTIVE (RESUMED)", Color.parseColor("#4CAF50")); // Vert
    }

    /**
     * 4. onPause() - Activity perd le focus
     * - Appelé quand une autre Activity passe au premier plan
     * - Activity encore partiellement visible
     * - Arrêter animations, libérer ressources consommatrices
     * - DOIT ÊTRE RAPIDE (< 1 seconde)
     */
    @Override
    protected void onPause() {
        super.onPause();
        logLifecycle("onPause()", "Activity perd le focus (partiellement visible)");
        updateUI("PAUSED", Color.parseColor("#FF9800")); // Orange foncé
    }

    /**
     * 5. onStop() - Activity n'est plus visible
     * - Appelé quand Activity complètement cachée
     * - Libérer ressources lourdes
     * - Sauvegarder données en cours
     */
    @Override
    protected void onStop() {
        super.onStop();
        logLifecycle("onStop()", "Activity cachée (invisible)");
        updateUI("STOPPED", Color.parseColor("#FF5722")); // Rouge-orange
    }

    /**
     * 6. onRestart() - Activity redémarre après avoir été stoppée
     * - Appelé UNIQUEMENT si Activity était stoppée (pas destroy)
     * - Suivi de onStart() puis onResume()
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        logLifecycle("onRestart()", "Activity redémarre (était stoppée)");
        updateUI("RESTARTING", Color.parseColor("#9C27B0")); // Violet
    }

    /**
     * 7. onDestroy() - Activity détruite
     * - Dernière méthode appelée avant destruction
     * - Libérer TOUTES les ressources
     * - Annuler threads, fermer connexions
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifecycle("onDestroy()", "Activity DÉTRUITE - Libération ressources");
        // Pas besoin de updateUI ici car Activity détruite
    }

    /**
     * 8. onSaveInstanceState() - Sauvegarder l'état temporaire
     * - Appelé AVANT onStop() ou onPause()
     * - Sauvegarder données UI temporaires (rotation, kill processus)
     * - Bundle restauré dans onCreate()
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        // Sauvegarder le compteur
        outState.putInt(KEY_COUNTER, launchCounter);
        
        logLifecycle("onSaveInstanceState()", "État sauvegardé - Counter = " + launchCounter);
    }

    /**
     * 9. onRestoreInstanceState() - Restaurer l'état (alternative à onCreate)
     * - Appelé APRÈS onStart()
     * - Alternative à restaurer dans onCreate()
     * - Bundle GARANTI non-null ici
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        
        // Alternative : on pourrait restaurer ici au lieu de onCreate()
        logLifecycle("onRestoreInstanceState()", "État restauré après onStart()");
    }

    // ==================== MÉTHODES UTILITAIRES ====================

    /**
     * Initialiser les références des vues
     */
    private void initViews() {
        tvStatus = findViewById(R.id.tvStatus);
        tvCounter = findViewById(R.id.tvCounter);
        tvLogs = findViewById(R.id.tvLogs);
        cardStatus = findViewById(R.id.cardStatus);
        btnLaunchSecond = findViewById(R.id.btnLaunchSecond);
    }

    /**
     * Configurer les listeners
     */
    private void setupListeners() {
        btnLaunchSecond.setOnClickListener(v -> {
            launchCounter++;
            updateCounter();
            
            logLifecycle("USER_ACTION", "Lancement de SecondActivity");
            
            // Créer Intent pour lancer SecondActivity
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("launch_number", launchCounter);
            startActivity(intent);
        });
    }

    /**
     * Mettre à jour l'affichage de l'état
     */
    private void updateUI(String status, int color) {
        tvStatus.setText("État : " + status);
        cardStatus.setCardBackgroundColor(color);
        tvLogs.setText(logsBuilder.toString());
    }

    /**
     * Mettre à jour le compteur
     */
    private void updateCounter() {
        tvCounter.setText("Lancements : " + launchCounter);
    }

    /**
     * Logger et afficher les événements du cycle de vie
     */
    private void logLifecycle(String method, String message) {
        String logMessage = method + " - " + message;
        
        // Log dans Logcat
        Log.d(TAG, logMessage);
        
        // Ajouter au TextView
        logsBuilder.insert(0, "📍 " + logMessage + "\n\n");
        
        // Limiter à 10 logs
        String[] lines = logsBuilder.toString().split("\n");
        if (lines.length > 20) {
            logsBuilder = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                logsBuilder.append(lines[i]).append("\n");
            }
        }
    }
}
```

---

### 2️⃣ **SecondActivity.java** - Activity secondaire

```java
package tn.isitcom.cycledevie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class SecondActivity extends AppCompatActivity {

    // Tag pour les logs
    private static final String TAG = "SecondActivity";
    
    // Vues
    private TextView tvStatus;
    private TextView tvInfo;
    private TextView tvLogs;
    private MaterialCardView cardStatus;
    private Button btnBack;
    
    // StringBuilder pour les logs
    private StringBuilder logsBuilder = new StringBuilder();
    
    // Numéro de lancement reçu
    private int launchNumber = 0;

    // ==================== CYCLE DE VIE ====================

    /**
     * 1. onCreate() - Création de l'Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        logLifecycle("onCreate()", "SecondActivity créée");
        
        // Charger le layout
        setContentView(R.layout.activity_second);
        
        // Initialiser les vues
        initViews();
        
        // Récupérer les données de l'Intent
        Intent intent = getIntent();
        if (intent != null) {
            launchNumber = intent.getIntExtra("launch_number", 0);
            tvInfo.setText("Lancement n°" + launchNumber + " depuis MainActivity");
            logLifecycle("onCreate()", "Données reçues : launch_number = " + launchNumber);
        }
        
        // Configurer les listeners
        setupListeners();
        
        // Mettre à jour l'UI
        updateUI("CREATED", Color.parseColor("#FFC107"));
    }

    /**
     * 2. onStart() - Activity visible
     */
    @Override
    protected void onStart() {
        super.onStart();
        logLifecycle("onStart()", "SecondActivity visible");
        updateUI("VISIBLE", Color.parseColor("#2196F3"));
    }

    /**
     * 3. onResume() - Activity active
     */
    @Override
    protected void onResume() {
        super.onResume();
        logLifecycle("onResume()", "SecondActivity ACTIVE");
        updateUI("ACTIVE (RESUMED)", Color.parseColor("#4CAF50"));
    }

    /**
     * 4. onPause() - Activity perd le focus
     */
    @Override
    protected void onPause() {
        super.onPause();
        logLifecycle("onPause()", "SecondActivity perd le focus");
        updateUI("PAUSED", Color.parseColor("#FF9800"));
    }

    /**
     * 5. onStop() - Activity invisible
     */
    @Override
    protected void onStop() {
        super.onStop();
        logLifecycle("onStop()", "SecondActivity invisible");
        updateUI("STOPPED", Color.parseColor("#FF5722"));
    }

    /**
     * 6. onRestart() - Activity redémarre
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        logLifecycle("onRestart()", "SecondActivity redémarre");
        updateUI("RESTARTING", Color.parseColor("#9C27B0"));
    }

    /**
     * 7. onDestroy() - Activity détruite
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        logLifecycle("onDestroy()", "SecondActivity DÉTRUITE");
    }

    /**
     * 8. onSaveInstanceState() - Sauvegarder état
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("launch_number", launchNumber);
        logLifecycle("onSaveInstanceState()", "État sauvegardé");
    }

    /**
     * 9. onRestoreInstanceState() - Restaurer état
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        launchNumber = savedInstanceState.getInt("launch_number", 0);
        logLifecycle("onRestoreInstanceState()", "État restauré - launch = " + launchNumber);
    }

    // ==================== MÉTHODES UTILITAIRES ====================

    private void initViews() {
        tvStatus = findViewById(R.id.tvStatus);
        tvInfo = findViewById(R.id.tvInfo);
        tvLogs = findViewById(R.id.tvLogs);
        cardStatus = findViewById(R.id.cardStatus);
        btnBack = findViewById(R.id.btnBack);
    }

    private void setupListeners() {
        btnBack.setOnClickListener(v -> {
            logLifecycle("USER_ACTION", "Retour vers MainActivity");
            finish(); // Détruit cette Activity et retourne à la précédente
        });
    }

    private void updateUI(String status, int color) {
        tvStatus.setText("État : " + status);
        cardStatus.setCardBackgroundColor(color);
        tvLogs.setText(logsBuilder.toString());
    }

    private void logLifecycle(String method, String message) {
        String logMessage = method + " - " + message;
        
        // Log dans Logcat
        Log.d(TAG, logMessage);
        
        // Ajouter au TextView
        logsBuilder.insert(0, "📍 " + logMessage + "\n\n");
        
        // Limiter à 10 logs
        String[] lines = logsBuilder.toString().split("\n");
        if (lines.length > 20) {
            logsBuilder = new StringBuilder();
            for (int i = 0; i < 20; i++) {
                logsBuilder.append(lines[i]).append("\n");
            }
        }
    }
}
```

---

### 3️⃣ **activity_main.xml** - Layout MainActivity

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#F5F5F5">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <!-- En-tête -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="20dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="🔄 MainActivity"
                    android:textSize="24sp"
                    android:textStyle="bold"
                    android:textColor="#212121" />

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="8dp"
                    android:text="Observez le cycle de vie dans Logcat"
                    android:textSize="14sp"
                    android:textColor="#757575" />

            </LinearLayout>

        </com.google.android.material.card.MaterialCardView>

        <!-- État actuel -->
        <com.google.android.material.card.MaterialCardView
            android:id="@+id/cardStatus"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp"
            app:cardBackgroundColor="#4CAF50">

            <TextView
                android:id="@+id/tvStatus"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="20dp"
                android:text="État : CREATED"
                android:textSize="20sp"
                android:textStyle="bold"
                android:textColor="#FFFFFF" />

        </com.google.android.material.card.MaterialCardView>

        <!-- Compteur -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <TextView
                android:id="@+id/tvCounter"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="20dp"
                android:text="Lancements : 0"
                android:textSize="18sp"
                android:textColor="#212121" />

        </com.google.android.material.card.MaterialCardView>

        <!-- Bouton lancer SecondActivity -->
        <Button
            android:id="@+id/btnLaunchSecond"
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginBottom="16dp"
            android:text="🚀 Lancer SecondActivity"
            android:textSize="16sp"
            android:textStyle="bold"
            app:cornerRadius="12dp" />

        <!-- Logs -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="16dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="📋 Logs du cycle de vie"
                    android:textSize="16sp"
                    android:textStyle="bold"
                    android:textColor="#212121"
                    android:layout_marginBottom="12dp" />

                <TextView
                    android:id="@+id/tvLogs"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Aucun log pour le moment"
                    android:textSize="12sp"
                    android:fontFamily="monospace"
                    android:textColor="#424242"
                    android:background="#F5F5F5"
                    android:padding="12dp" />

            </LinearLayout>

        </com.google.android.material.card.MaterialCardView>

    </LinearLayout>

</ScrollView>
```

---

### 4️⃣ **activity_second.xml** - Layout SecondActivity

```xml
<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#E3F2FD">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <!-- En-tête -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="20dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="🎯 SecondActivity"
                    android:textSize="24sp"
                    android:textStyle="bold"
                    android:textColor="#212121" />

                <TextView
                    android:id="@+id/tvInfo"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="8dp"
                    android:text="Lancé depuis MainActivity"
                    android:textSize="14sp"
                    android:textColor="#757575" />

            </LinearLayout>

        </com.google.android.material.card.MaterialCardView>

        <!-- État actuel -->
        <com.google.android.material.card.MaterialCardView
            android:id="@+id/cardStatus"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginBottom="16dp"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp"
            app:cardBackgroundColor="#2196F3">

            <TextView
                android:id="@+id/tvStatus"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:padding="20dp"
                android:text="État : CREATED"
                android:textSize="20sp"
                android:textStyle="bold"
                android:textColor="#FFFFFF" />

        </com.google.android.material.card.MaterialCardView>

        <!-- Bouton retour -->
        <Button
            android:id="@+id/btnBack"
            android:layout_width="match_parent"
            android:layout_height="60dp"
            android:layout_marginBottom="16dp"
            android:text="⬅️ Retour à MainActivity"
            android:textSize="16sp"
            android:textStyle="bold"
            app:cornerRadius="12dp" />

        <!-- Logs -->
        <com.google.android.material.card.MaterialCardView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:cardCornerRadius="12dp"
            app:cardElevation="4dp">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="16dp">

                <TextView
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:text="📋 Logs du cycle de vie"
                    android:textSize="16sp"
                    android:textStyle="bold"
                    android:textColor="#212121"
                    android:layout_marginBottom="12dp" />

                <TextView
                    android:id="@+id/tvLogs"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Aucun log pour le moment"
                    android:textSize="12sp"
                    android:fontFamily="monospace"
                    android:textColor="#424242"
                    android:background="#FFF3E0"
                    android:padding="12dp" />

            </LinearLayout>

        </com.google.android.material.card.MaterialCardView>

    </LinearLayout>

</ScrollView>
```

---

### 5️⃣ **AndroidManifest.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tn.isitcom.cycledevie">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.CycleDeVie">

        <!-- Activity principale - Point d'entrée -->
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Activity secondaire -->
        <activity
            android:name=".SecondActivity"
            android:exported="false"
            android:label="Second Activity" />

    </application>

</manifest>
```

---

### 6️⃣ **strings.xml**

```xml
<resources>
    <string name="app_name">Cycle de Vie</string>
</resources>
```

---

### 7️⃣ **colors.xml**

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
</resources>
```

---

## 🧪 Tests à effectuer

### Test 1 : Navigation basique
```
1. Lancer l'app
   ✅ Observer : onCreate() → onStart() → onResume()
   
2. Cliquer sur "Lancer SecondActivity"
   ✅ MainActivity : onPause() → onStop()
   ✅ SecondActivity : onCreate() → onStart() → onResume()
   
3. Cliquer sur "Retour"
   ✅ SecondActivity : onPause() → onStop() → onDestroy()
   ✅ MainActivity : onRestart() → onStart() → onResume()
```

### Test 2 : Rotation écran
```
1. Dans MainActivity, tourner l'écran
   ✅ Observer : onPause() → onStop() → onSaveInstanceState() → onDestroy()
   ✅ Puis : onCreate() → onStart() → onRestoreInstanceState() → onResume()
   ✅ Vérifier : le compteur est restauré
```

### Test 3 : Bouton Home
```
1. Lancer SecondActivity
2. Appuyer sur bouton Home
   ✅ Observer : onPause() → onStop()
   
3. Revenir à l'app (Recents)
   ✅ Observer : onRestart() → onStart() → onResume()
```

### Test 4 : Dialog par-dessus
```
1. Dans MainActivity, afficher dialog système
   ✅ Observer : onPause() seulement (Activity encore visible)
```

---

## 📊 Tableau récapitulatif du cycle de vie

| Callback | Quand ? | Que faire ? | Suivi par |
|----------|---------|-------------|-----------|
| **onCreate()** | Création | Init UI, findViewById, restaurer état | onStart() |
| **onStart()** | Devient visible | Préparer ressources | onResume() |
| **onResume()** | Devient actif | Démarrer animations, capteurs | - |
| **onPause()** | Perd focus | Arrêter animations, sauver draft | onStop() ou onResume() |
| **onStop()** | Invisible | Libérer ressources lourdes | onRestart() ou onDestroy() |
| **onRestart()** | Redémarre | Ré-initialiser | onStart() |
| **onDestroy()** | Destruction | Libérer TOUT | - |
| **onSaveInstanceState()** | Avant stop | Sauver état UI | - |
| **onRestoreInstanceState()** | Après start | Restaurer état | - |

---

## 🎓 Points clés à retenir

✅ **onCreate()** : Obligatoire, init UI  
✅ **onResume()** : Activity active  
✅ **onPause()** : Doit être rapide  
✅ **onSaveInstanceState()** : Survit rotation  
✅ **finish()** : Détruit Activity  
✅ **Intent.putExtra()** : Passer données  

---

## 📦 Dépendances (build.gradle)

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
}
```

---

## 🚀 Exécution

1. Créer nouveau projet Android Studio
2. Copier tous les fichiers
3. Sync Gradle
4. Run sur émulateur ou appareil
5. **Ouvrir Logcat** et filtrer par "MainActivity" et "SecondActivity"

---

**👨‍🏫 Cours Android ISITCOM 2025-2026**
