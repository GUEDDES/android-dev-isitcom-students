# TD01 : Découverte d'Android Studio et premier projet

## 🎯 Objectifs

- Prendre en main Android Studio.
- Créer un premier projet simple.
- Comprendre la structure d'un projet Android.
- Exécuter une application sur émulateur.

---

## Partie 1 : Installation et configuration (15 min)

### Étape 1 : Vérifier l'installation

1. Ouvrir Android Studio.
2. Vérifier que le SDK est installé (`File > Settings > Android SDK`).
3. Vérifier qu'au moins API 33, 34, 35 sont installées.

### Étape 2 : Créer un AVD

1. Ouvrir Device Manager.
2. Créer un Pixel 5 avec API 33.
3. Lancer l'AVD et vérifier qu'il fonctionne.

---

## Partie 2 : Premier projet "HelloISITCOM" (30 min)

### Consignes

1. Créer un nouveau projet :
   - Template : **Empty Views Activity**.
   - Name : `HelloISITCOM`.
   - Package : `tn.isitcom.td01`.
   - Language : Java.
   - Minimum SDK : API 24.

2. Modifier `activity_main.xml` :
   - Ajouter un `TextView` centré avec le texte "Bienvenue à l'ISITCOM".
   - Taille : 24sp, style : bold.

3. Ajouter un `Button` sous le TextView :
   - Texte : "Cliquez ici".
   - Au clic, changer le texte du TextView en "Bonjour [Votre Nom]".

4. Exécuter l'application sur l'AVD.

### Code attendu

**activity_main.xml** :

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/textWelcome"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Bienvenue à l'ISITCOM"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/btnClick"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Cliquez ici"
        app:layout_constraintTop_toBottomOf="@id/textWelcome"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

**MainActivity.java** :

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textWelcome = findViewById(R.id.textWelcome);
        Button btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(v -> {
            textWelcome.setText("Bonjour Ahmed"); // Remplacer par votre nom
        });
    }
}
```

---

## Partie 3 : Améliorations (30 min)

### Exercice 1 : Ajout d'un champ de saisie

1. Ajouter un `EditText` pour saisir un prénom.
2. Modifier le bouton pour afficher "Bonjour [prénom saisi]".

### Exercice 2 : Compteur de clics

1. Ajouter un deuxième `TextView` affichant "Clics : 0".
2. À chaque clic sur le bouton, incrémenter le compteur.

### Exercice 3 : Toast

1. Afficher un Toast "Bienvenue dans le TD01" au démarrage de l'application.

---

## 📄 Livrable

- Projet Android Studio complet.
- Captures d'écran de l'application en exécution.

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Projet créé correctement | 4 |
| Interface conforme | 4 |
| Bouton fonctionnel | 4 |
| Exercice 1 | 3 |
| Exercice 2 | 3 |
| Exercice 3 | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
