# Projet Module 5 : Application Quiz Multi-Écrans

## 🎯 Objectif

Créer une application de quiz avec navigation entre plusieurs écrans, passage de données et gestion du cycle de vie.

Concepts : Activities multiples, Intents, Bundle, Activity Result API, onSaveInstanceState.

---

## 📋 Fonctionnalités

- **Écran d'accueil** : saisie du nom, choix de la difficulté
- **Écran de quiz** : 5 questions avec 4 choix
- **Écran de résultat** : score final personnalisé
- **Sauvegarde de l'état** lors de la rotation

---

## 🗂️ Structure du projet

```
QuizApp/
├── MainActivity.java         (Écran d'accueil)
├── QuizActivity.java         (Questions)
├── ResultActivity.java       (Résultat)
├── Question.java             (Modèle de données)
└── QuizData.java             (Base de questions)
```

---

## 📱 Interface - Écran d'accueil (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp"
    android:background="@color/purple_500">

    <ImageView
        android:id="@+id/ivLogo"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_quiz"
        android:contentDescription="Logo"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="48dp" />

    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Quiz Général"
        android:textSize="36sp"
        android:textStyle="bold"
        android:textColor="@android:color/white"
        app:layout_constraintTop_toBottomOf="@id/ivLogo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />

    <com.google.android.material.card.MaterialCardView
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:cardCornerRadius="16dp"
        app:cardElevation="8dp"
        app:layout_constraintTop_toBottomOf="@id/tvTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        android:layout_marginTop="32dp"
        android:layout_marginBottom="48dp">

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="24dp">

            <com.google.android.material.textfield.TextInputLayout
                android:id="@+id/tilName"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:hint="Votre nom"
                app:boxBackgroundMode="outline">

                <com.google.android.material.textfield.TextInputEditText
                    android:id="@+id/etName"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:inputType="textPersonName"
                    android:maxLines="1" />
            </com.google.android.material.textfield.TextInputLayout>

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Difficulté"
                android:textSize="16sp"
                android:textStyle="bold"
                android:layout_marginTop="24dp" />

            <RadioGroup
                android:id="@+id/rgDifficulty"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="8dp">

                <RadioButton
                    android:id="@+id/rbEasy"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Facile"
                    android:checked="true" />

                <RadioButton
                    android:id="@+id/rbMedium"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Moyen" />

                <RadioButton
                    android:id="@+id/rbHard"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Difficile" />
            </RadioGroup>

            <Button
                android:id="@+id/btnStart"
                android:layout_width="match_parent"
                android:layout_height="60dp"
                android:text="Commencer"
                android:textSize="18sp"
                android:layout_marginTop="24dp" />

        </LinearLayout>
    </com.google.android.material.card.MaterialCardView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 📱 Interface - Quiz (activity_quiz.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">

    <!-- Barre de progression -->
    <ProgressBar
        android:id="@+id/progressBar"
        style="?android:attr/progressBarStyleHorizontal"
        android:layout_width="0dp"
        android:layout_height="8dp"
        android:max="5"
        android:progress="1"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/tvProgress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Question 1/5"
        android:textSize="16sp"
        android:textStyle="bold"
        app:layout_constraintTop_toBottomOf="@id/progressBar"
        app:layout_constraintStart_toStartOf="parent"
        android:layout_marginTop="16dp" />

    <!-- Carte Question -->
    <com.google.android.material.card.MaterialCardView
        android:id="@+id/cvQuestion"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:cardCornerRadius="12dp"
        app:cardElevation="4dp"
        app:layout_constraintTop_toBottomOf="@id/tvProgress"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp">

        <TextView
            android:id="@+id/tvQuestion"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Quelle est la capitale de la France ?"
            android:textSize="20sp"
            android:textStyle="bold"
            android:padding="24dp"
            android:textColor="@color/purple_700" />
    </com.google.android.material.card.MaterialCardView>

    <!-- Choix de réponses -->
    <RadioGroup
        android:id="@+id/rgChoices"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toBottomOf="@id/cvQuestion"
        app:layout_constraintBottom_toTopOf="@id/btnNext"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp"
        android:layout_marginBottom="16dp">

        <com.google.android.material.radiobutton.MaterialRadioButton
            android:id="@+id/rbChoice1"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Paris"
            android:textSize="16sp"
            android:padding="16dp"
            android:background="@drawable/choice_bg"
            android:layout_marginBottom="12dp" />

        <com.google.android.material.radiobutton.MaterialRadioButton
            android:id="@+id/rbChoice2"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Londres"
            android:textSize="16sp"
            android:padding="16dp"
            android:background="@drawable/choice_bg"
            android:layout_marginBottom="12dp" />

        <com.google.android.material.radiobutton.MaterialRadioButton
            android:id="@+id/rbChoice3"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Berlin"
            android:textSize="16sp"
            android:padding="16dp"
            android:background="@drawable/choice_bg"
            android:layout_marginBottom="12dp" />

        <com.google.android.material.radiobutton.MaterialRadioButton
            android:id="@+id/rbChoice4"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="Madrid"
            android:textSize="16sp"
            android:padding="16dp"
            android:background="@drawable/choice_bg" />
    </RadioGroup>

    <Button
        android:id="@+id/btnNext"
        android:layout_width="0dp"
        android:layout_height="60dp"
        android:text="Suivant"
        android:textSize="18sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginBottom="16dp" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## ☕ Code Java - MainActivity.java

```java
package tn.isitcom.quizapp;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etName;
    private RadioGroup rgDifficulty;
    private Button btnStart;

    // Activity Result Launcher (nouvelle API)
    private final ActivityResultLauncher<Intent> quizLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                int score = result.getData().getIntExtra("score", 0);
                Toast.makeText(this, "Score obtenu : " + score + "/5", Toast.LENGTH_LONG).show();
            }
        }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnStart.setOnClickListener(v -> startQuiz());
    }

    private void initViews() {
        etName = findViewById(R.id.etName);
        rgDifficulty = findViewById(R.id.rgDifficulty);
        btnStart = findViewById(R.id.btnStart);
    }

    private void startQuiz() {
        String name = etName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Veuillez saisir votre nom", Toast.LENGTH_SHORT).show();
            return;
        }

        String difficulty = "easy";
        int selectedId = rgDifficulty.getCheckedRadioButtonId();
        if (selectedId == R.id.rbMedium) {
            difficulty = "medium";
        } else if (selectedId == R.id.rbHard) {
            difficulty = "hard";
        }

        // Créer Intent et passer les données
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("playerName", name);
        intent.putExtra("difficulty", difficulty);

        // Lancer l'Activity et attendre le résultat
        quizLauncher.launch(intent);
    }
}
```

---

## ☕ Code Java - Question.java (Modèle)

```java
package tn.isitcom.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private String questionText;
    private String[] choices;
    private int correctAnswerIndex;

    public Question(String questionText, String[] choices, int correctAnswerIndex) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Getters
    public String getQuestionText() { return questionText; }
    public String[] getChoices() { return choices; }
    public int getCorrectAnswerIndex() { return correctAnswerIndex; }

    // Parcelable implementation pour passer via Intent
    protected Question(Parcel in) {
        questionText = in.readString();
        choices = in.createStringArray();
        correctAnswerIndex = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeStringArray(choices);
        dest.writeInt(correctAnswerIndex);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
```

---

## ☕ Code Java - QuizActivity.java

```java
package tn.isitcom.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    private TextView tvProgress, tvQuestion;
    private RadioGroup rgChoices;
    private RadioButton rbChoice1, rbChoice2, rbChoice3, rbChoice4;
    private Button btnNext;
    private ProgressBar progressBar;

    private String playerName;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Récupérer les données de l'Intent
        playerName = getIntent().getStringExtra("playerName");
        String difficulty = getIntent().getStringExtra("difficulty");

        // Restaurer l'état si rotation
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("currentQuestion");
            score = savedInstanceState.getInt("score");
        }

        initViews();
        loadQuestions(difficulty);
        displayQuestion();

        btnNext.setOnClickListener(v -> checkAnswerAndNext());
    }

    private void initViews() {
        tvProgress = findViewById(R.id.tvProgress);
        tvQuestion = findViewById(R.id.tvQuestion);
        rgChoices = findViewById(R.id.rgChoices);
        rbChoice1 = findViewById(R.id.rbChoice1);
        rbChoice2 = findViewById(R.id.rbChoice2);
        rbChoice3 = findViewById(R.id.rbChoice3);
        rbChoice4 = findViewById(R.id.rbChoice4);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);
    }

    private void loadQuestions(String difficulty) {
        questions = QuizData.getQuestions(difficulty);
    }

    private void displayQuestion() {
        if (currentQuestionIndex >= questions.size()) {
            finishQuiz();
            return;
        }

        Question q = questions.get(currentQuestionIndex);

        // Mettre à jour l'interface
        tvProgress.setText(String.format("Question %d/%d", currentQuestionIndex + 1, questions.size()));
        progressBar.setProgress(currentQuestionIndex + 1);
        tvQuestion.setText(q.getQuestionText());

        String[] choices = q.getChoices();
        rbChoice1.setText(choices[0]);
        rbChoice2.setText(choices[1]);
        rbChoice3.setText(choices[2]);
        rbChoice4.setText(choices[3]);

        rgChoices.clearCheck();
    }

    private void checkAnswerAndNext() {
        int selectedId = rgChoices.getCheckedRadioButtonId();

        if (selectedId == -1) {
            Toast.makeText(this, "Veuillez sélectionner une réponse", Toast.LENGTH_SHORT).show();
            return;
        }

        // Déterminer l'index de la réponse sélectionnée
        int selectedIndex = 0;
        if (selectedId == R.id.rbChoice2) selectedIndex = 1;
        else if (selectedId == R.id.rbChoice3) selectedIndex = 2;
        else if (selectedId == R.id.rbChoice4) selectedIndex = 3;

        // Vérifier si c'est correct
        Question q = questions.get(currentQuestionIndex);
        if (selectedIndex == q.getCorrectAnswerIndex()) {
            score++;
            Toast.makeText(this, "✅ Correct !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "❌ Incorrect", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;
        displayQuestion();
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent(this, ResultActivity.class);
        resultIntent.putExtra("playerName", playerName);
        resultIntent.putExtra("score", score);
        resultIntent.putExtra("total", questions.size());
        startActivity(resultIntent);
        finish();
    }

    // Sauvegarder l'état lors de la rotation
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentQuestion", currentQuestionIndex);
        outState.putInt("score", score);
    }
}
```

---

## 📚 Explication détaillée

### 1. Activity Result API (moderne)

**Ancienne méthode** (dépréciée) :
```java
startActivityForResult(intent, REQUEST_CODE);
onActivityResult(requestCode, resultCode, data) { ... }
```

**Nouvelle méthode** :
```java
ActivityResultLauncher<Intent> launcher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> {
        // Traiter le résultat
    }
);
launcher.launch(intent);
```

### 2. Passage de données entre Activities

**Envoyer** :
```java
Intent intent = new Intent(this, QuizActivity.class);
intent.putExtra("playerName", name);  // String
intent.putExtra("score", 10);          // int
startActivity(intent);
```

**Recevoir** :
```java
String name = getIntent().getStringExtra("playerName");
int score = getIntent().getIntExtra("score", 0); // 0 = valeur par défaut
```

### 3. Sauvegarde de l'état (rotation écran)

**Problème** : La rotation détruit et recrée l'Activity.

**Solution** :
```java
@Override
protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putInt("score", score);
}

@Override
protected void onCreate(Bundle savedInstanceState) {
    if (savedInstanceState != null) {
        score = savedInstanceState.getInt("score");
    }
}
```

### 4. Parcelable pour objets complexes

Permet de passer des objets personnalisés via Intent :
```java
Question q = new Question("...", choices, 0);
intent.putExtra("question", q);

Question q = intent.getParcelableExtra("question");
```

---

## 🎯 Tests à effectuer

### Test 1 : Navigation complète
1. Saisir nom "Alice"
2. Sélectionner difficulté "Facile"
3. Répondre aux 5 questions
4. Vérifier l'affichage du score

### Test 2 : Rotation écran
1. Répondre à 2 questions
2. Tourner l'écran
3. **Attendu** : le score et la question actuelle sont conservés

### Test 3 : Validation
1. Cliquer "Suivant" sans sélectionner de réponse
2. **Attendu** : Toast "Veuillez sélectionner une réponse"

---

## 📖 Concepts Android utilisés

✅ **Intents explicites** : Navigation entre Activities  
✅ **Extras** : Passage de données primitives  
✅ **Activity Result API** : Récupération de résultats  
✅ **onSaveInstanceState** : Sauvegarde lors de la rotation  
✅ **Parcelable** : Passage d'objets complexes  
✅ **RadioGroup** : Choix unique  
✅ **ProgressBar** : Indication de progression  

---

🎓 **Projet pédagogique** - Module 5 - ISITCOM 2025/2026
