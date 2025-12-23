# Projet Module 3 : Application Calculatrice Simple

## 🎯 Objectif

Créer une calculatrice simple pour maîtriser les bases : interface XML, gestion des clics, manipulation de vues.

---

## 📋 Fonctionnalités

- Affichage du résultat
- Boutons de 0 à 9
- Opérations : +, -, ×, ÷
- Bouton = pour calculer
- Bouton C pour effacer

---

## 🗂️ Structure du projet

```
CalculatriceSimple/
├── app/src/main/
│   ├── java/tn/isitcom/calculatrice/
│   │   └── MainActivity.java
│   ├── res/
│   │   ├── layout/
│   │   │   └── activity_main.xml
│   │   ├── values/
│   │   │   ├── strings.xml
│   │   │   └── colors.xml
│   └── AndroidManifest.xml
```

---

## 📱 Interface (activity_main.xml)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    android:background="#F5F5F5">

    <!-- Écran d'affichage -->
    <TextView
        android:id="@+id/tvDisplay"
        android:layout_width="0dp"
        android:layout_height="120dp"
        android:background="@android:color/white"
        android:elevation="4dp"
        android:gravity="end|center_vertical"
        android:padding="24dp"
        android:text="0"
        android:textColor="#212121"
        android:textSize="48sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <!-- Grille de boutons -->
    <GridLayout
        android:id="@+id/gridButtons"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginTop="24dp"
        android:columnCount="4"
        android:rowCount="5"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tvDisplay">

        <!-- Ligne 1 : C, ÷ -->
        <Button
            android:id="@+id/btnClear"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="2"
            android:layout_margin="4dp"
            android:backgroundTint="#F44336"
            android:text="C"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btnDivide"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#FF9800"
            android:text="÷"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btnMultiply"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#FF9800"
            android:text="×"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <!-- Ligne 2 : 7, 8, 9, - -->
        <Button
            android:id="@+id/btn7"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="7"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn8"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="8"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn9"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="9"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btnMinus"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#FF9800"
            android:text="-"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <!-- Ligne 3 : 4, 5, 6, + -->
        <Button
            android:id="@+id/btn4"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="4"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn5"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="5"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn6"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="6"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btnPlus"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#FF9800"
            android:text="+"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <!-- Ligne 4 : 1, 2, 3, = -->
        <Button
            android:id="@+id/btn1"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="1"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn2"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="2"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btn3"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="1"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="3"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

        <Button
            android:id="@+id/btnEquals"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="2"
            android:layout_columnWeight="1"
            android:layout_rowSpan="2"
            android:layout_margin="4dp"
            android:backgroundTint="#4CAF50"
            android:text="="
            android:textColor="@android:color/white"
            android:textSize="32sp" />

        <!-- Ligne 5 : 0 -->
        <Button
            android:id="@+id/btn0"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:layout_rowWeight="1"
            android:layout_columnWeight="3"
            android:layout_margin="4dp"
            android:backgroundTint="#607D8B"
            android:text="0"
            android:textColor="@android:color/white"
            android:textSize="24sp" />

    </GridLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## ☕ Code Java (MainActivity.java)

```java
package tn.isitcom.calculatrice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Vues
    private TextView tvDisplay;
    
    // Variables de calcul
    private double operand1 = 0;
    private double operand2 = 0;
    private String operator = "";
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'affichage
        tvDisplay = findViewById(R.id.tvDisplay);

        // Attribution des listeners aux boutons numériques
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);

        // Attribution des listeners aux opérateurs
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnMultiply).setOnClickListener(this);
        findViewById(R.id.btnDivide).setOnClickListener(this);

        // Boutons spéciaux
        findViewById(R.id.btnEquals).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        int id = v.getId();

        // Gestion des chiffres
        if (id == R.id.btn0 || id == R.id.btn1 || id == R.id.btn2 ||
            id == R.id.btn3 || id == R.id.btn4 || id == R.id.btn5 ||
            id == R.id.btn6 || id == R.id.btn7 || id == R.id.btn8 ||
            id == R.id.btn9) {
            
            handleNumberClick(buttonText);
        }
        // Gestion des opérateurs
        else if (id == R.id.btnPlus || id == R.id.btnMinus ||
                 id == R.id.btnMultiply || id == R.id.btnDivide) {
            
            handleOperatorClick(buttonText);
        }
        // Calcul du résultat
        else if (id == R.id.btnEquals) {
            handleEqualsClick();
        }
        // Effacer
        else if (id == R.id.btnClear) {
            handleClearClick();
        }
    }

    /**
     * Gestion du clic sur un chiffre
     */
    private void handleNumberClick(String number) {
        String currentDisplay = tvDisplay.getText().toString();

        if (isNewOperation || currentDisplay.equals("0")) {
            tvDisplay.setText(number);
            isNewOperation = false;
        } else {
            tvDisplay.setText(currentDisplay + number);
        }
    }

    /**
     * Gestion du clic sur un opérateur
     */
    private void handleOperatorClick(String op) {
        String currentDisplay = tvDisplay.getText().toString();
        
        try {
            operand1 = Double.parseDouble(currentDisplay);
            operator = op;
            isNewOperation = true;
        } catch (NumberFormatException e) {
            showError("Nombre invalide");
        }
    }

    /**
     * Calcul du résultat
     */
    private void handleEqualsClick() {
        String currentDisplay = tvDisplay.getText().toString();
        
        try {
            operand2 = Double.parseDouble(currentDisplay);
            double result = 0;
            boolean validOperation = true;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "×":
                    result = operand1 * operand2;
                    break;
                case "÷":
                    if (operand2 == 0) {
                        showError("Division par zéro impossible");
                        validOperation = false;
                    } else {
                        result = operand1 / operand2;
                    }
                    break;
                default:
                    validOperation = false;
            }

            if (validOperation) {
                // Afficher le résultat (sans décimales inutiles)
                if (result == (long) result) {
                    tvDisplay.setText(String.valueOf((long) result));
                } else {
                    tvDisplay.setText(String.format("%.2f", result));
                }
                isNewOperation = true;
                operator = "";
            }
        } catch (NumberFormatException e) {
            showError("Erreur de calcul");
        }
    }

    /**
     * Effacer l'écran
     */
    private void handleClearClick() {
        tvDisplay.setText("0");
        operand1 = 0;
        operand2 = 0;
        operator = "";
        isNewOperation = true;
    }

    /**
     * Afficher un message d'erreur
     */
    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        handleClearClick();
    }
}
```

---

## 📚 Explication détaillée

### 1. Structure de l'interface

**TextView (tvDisplay)** :
- Affiche le nombre en cours de saisie ou le résultat
- `gravity="end|center_vertical"` : alignement à droite
- `textSize="48sp"` : grande taille pour lisibilité

**GridLayout** :
- Organise les boutons en grille 4x5
- `columnWeight` et `rowWeight` : répartition équitable de l'espace
- `layout_rowSpan="2"` pour le bouton = qui occupe 2 lignes

**Boutons** :
- Couleurs différentes par fonction (chiffres gris, opérateurs orange, égal vert, clear rouge)
- `backgroundTint` pour la couleur de fond

### 2. Logique du code

**Variables d'état** :
```java
private double operand1 = 0;      // Premier nombre
private double operand2 = 0;      // Deuxième nombre
private String operator = "";     // Opérateur (+, -, ×, ÷)
private boolean isNewOperation = true; // Nouvelle opération ?
```

**Flux d'exécution** :

1. **Clic sur chiffre** → `handleNumberClick()`
   - Si nouvelle opération : remplace l'affichage
   - Sinon : concatène le chiffre

2. **Clic sur opérateur** → `handleOperatorClick()`
   - Stocke le premier nombre
   - Mémorise l'opérateur
   - Marque comme nouvelle opération

3. **Clic sur =** → `handleEqualsClick()`
   - Récupère le deuxième nombre
   - Effectue le calcul selon l'opérateur
   - Affiche le résultat
   - Gère la division par zéro

4. **Clic sur C** → `handleClearClick()`
   - Remet tout à zéro

### 3. Gestion des erreurs

- **NumberFormatException** : attrapée lors de la conversion String → double
- **Division par zéro** : vérification explicite avant le calcul
- **Toast** : affichage d'un message temporaire à l'utilisateur

---

## 🚀 Compilation et exécution

### Étapes

1. **Créer le projet** :
   - File > New > New Project
   - Empty Views Activity
   - Name: `CalculatriceSimple`
   - Package: `tn.isitcom.calculatrice`

2. **Copier les fichiers** :
   - Remplacer `activity_main.xml`
   - Remplacer `MainActivity.java`

3. **Synchroniser Gradle** :
   - File > Sync Project with Gradle Files

4. **Lancer** :
   - Run > Run 'app'
   - Choisir AVD ou téléphone

---

## 🎯 Tests à effectuer

### Test 1 : Addition simple
1. Appuyer sur `5`
2. Appuyer sur `+`
3. Appuyer sur `3`
4. Appuyer sur `=`
5. **Résultat attendu** : `8`

### Test 2 : Division par zéro
1. Appuyer sur `1`
2. Appuyer sur `÷`
3. Appuyer sur `0`
4. Appuyer sur `=`
5. **Résultat attendu** : Toast d'erreur + écran remis à 0

### Test 3 : Opérations multiples
1. Faire `10 + 5 = 15`
2. Puis `× 2 = 30`
3. Puis `- 10 = 20`
4. **Résultat attendu** : `20`

---

## 💡 Améliorations possibles

1. **Décimales** : ajouter un bouton `.` pour les nombres à virgule
2. **Historique** : afficher les opérations précédentes
3. **Parenthèses** : gérer les expressions complexes
4. **Mémoire** : boutons M+, M-, MR, MC
5. **Thème sombre** : ajouter un switch pour changer le thème
6. **Animations** : animer les clics de boutons
7. **Paysage** : adapter l'interface en mode paysage

---

## 📖 Concepts Android utilisés

✅ **Activity** : point d'entrée de l'application  
✅ **Layout XML** : définition de l'interface  
✅ **findViewById** : lien entre XML et Java  
✅ **OnClickListener** : gestion des événements  
✅ **Toast** : messages temporaires  
✅ **String manipulation** : concaténation, conversion  
✅ **Exception handling** : try-catch  

---

🎓 **Projet pédagogique** - Module 3 - ISITCOM 2025/2026
