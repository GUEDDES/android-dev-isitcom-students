# 📏 Guide Complet : LinearLayout vs ConstraintLayout

## 🎯 Objectifs d'apprentissage

Comprendre et maîtriser les deux layouts fondamentaux d'Android :
- **LinearLayout** : Arrangement linéaire simple (horizontal/vertical)
- **ConstraintLayout** : Positionnement flexible par contraintes (recommandé 2025)

---

## 1️⃣ LinearLayout : Arrangement Linéaire

### 📖 Définition

**LinearLayout** arrange ses enfants **en ligne** (verticalement ou horizontalement).

### 🔑 Caractéristiques

✅ **Simplicité** : Facile à comprendre  
✅ **Orientation** : `vertical` ou `horizontal`  
✅ **Poids (weight)** : Répartition proportionnelle de l'espace  
⚠️ **Imbrication** : Peut devenir complexe avec plusieurs niveaux  
⚠️ **Performance** : Moins bon que ConstraintLayout avec hiérarchies profondes  

---

### 📝 Exemple 1 : LinearLayout Vertical

**Cas d'usage** : Formulaire simple (titre, champ, bouton)

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="center_horizontal">
    
    <!-- Titre -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Formulaire d'inscription"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="#212121"
        android:layout_marginBottom="24dp" />
    
    <!-- Champ Nom -->
    <EditText
        android:id="@+id/etName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        android:inputType="textPersonName"
        android:layout_marginBottom="16dp" />
    
    <!-- Champ Email -->
    <EditText
        android:id="@+id/etEmail"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:inputType="textEmailAddress"
        android:layout_marginBottom="16dp" />
    
    <!-- Bouton Valider -->
    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Valider"
        android:backgroundTint="#4CAF50" />
        
</LinearLayout>
```

**Résultat visuel** :
```
+----------------------------------+
|  Formulaire d'inscription        |
|                                  |
|  [____________Nom_____________]  |
|  [___________Email____________]  |
|  [         Valider           ]   |
+----------------------------------+
```

---

### 📝 Exemple 2 : LinearLayout Horizontal avec Poids

**Cas d'usage** : Boutons Annuler/Valider de même taille

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:layout_marginTop="16dp">
    
    <!-- Bouton Annuler (50% largeur) -->
    <Button
        android:id="@+id/btnCancel"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Annuler"
        android:backgroundTint="#F44336"
        android:layout_marginEnd="8dp" />
    
    <!-- Bouton Valider (50% largeur) -->
    <Button
        android:id="@+id/btnValidate"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_weight="1"
        android:text="Valider"
        android:backgroundTint="#4CAF50" />
        
</LinearLayout>
```

**Résultat visuel** :
```
+----------------------------------+
|  [ Annuler ]  |  [  Valider  ]  |
+----------------------------------+
```

**Explication `layout_weight`** :
- `android:layout_width="0dp"` : Largeur définie par le poids
- `android:layout_weight="1"` : Chaque bouton prend 1/2 de l'espace (1+1=2 parts)
- Si weights = 2 et 1 ⇒ premier prend 2/3, deuxième 1/3

---

### 📝 Exemple 3 : LinearLayout Imbriqué

**Cas d'usage** : Formulaire avec section horizontale

```xml
<LinearLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">
    
    <!-- Titre -->
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Calculatrice IMC"
        android:textSize="20sp"
        android:layout_marginBottom="16dp" />
    
    <!-- Ligne Poids/Taille (Horizontal) -->
    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_marginBottom="16dp">
        
        <EditText
            android:id="@+id/etWeight"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="Poids (kg)"
            android:inputType="numberDecimal"
            android:layout_marginEnd="8dp" />
        
        <EditText
            android:id="@+id/etHeight"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:hint="Taille (m)"
            android:inputType="numberDecimal" />
            
    </LinearLayout>
    
    <!-- Bouton Calculer -->
    <Button
        android:id="@+id/btnCalculate"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Calculer" />
    
    <!-- Résultat -->
    <TextView
        android:id="@+id/tvResult"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="IMC : -"
        android:textSize="18sp"
        android:layout_marginTop="16dp"
        android:gravity="center" />
        
</LinearLayout>
```

**Résultat visuel** :
```
+----------------------------------+
|  Calculatrice IMC                |
|  [___Poids___] [___Taille___]   |
|  [         Calculer          ]   |
|           IMC : -                |
+----------------------------------+
```

---

### 🔑 Attributs LinearLayout Essentiels

| Attribut | Valeurs | Description |
|----------|---------|-------------|
| `android:orientation` | `vertical` ❘ `horizontal` | Direction d'arrangement |
| `android:gravity` | `center` ❘ `top` ❘ `bottom` | Alignement du **contenu** |
| `android:layout_gravity` | `center` ❘ `start` ❘ `end` | Alignement de la **vue elle-même** |
| `android:weightSum` | Nombre (ex: `2`) | Somme totale des poids (optionnel) |
| `android:layout_weight` | Nombre (ex: `1`) | Poids pour répartition (sur enfants) |
| `android:padding` | `16dp` | Espacement interne |
| `android:layout_margin` | `16dp` | Espacement externe |

---

## 2️⃣ ConstraintLayout : Positionnement Flexible

### 📖 Définition

**ConstraintLayout** positionne ses enfants par **contraintes** (relations entre vues).

### 🔑 Caractéristiques

✅ **Flat hierarchy** : Pas d'imbrication nécessaire  
✅ **Performance** : Optimisé pour hiérarchies complexes  
✅ **Flexible** : Responsive design facile  
✅ **Éditeur visuel** : Drag-drop dans Android Studio  
✅ **Recommandé Google 2025** : Layout moderne  
⚠️ **Verbeux** : Plus de code XML que LinearLayout  

---

### 📝 Exemple 1 : ConstraintLayout Simple

**Cas d'usage** : Formulaire avec contraintes

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">
    
    <!-- Titre (en haut, centré horizontalement) -->
    <TextView
        android:id="@+id/tvTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Formulaire d'inscription"
        android:textSize="24sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
    
    <!-- Champ Nom (sous le titre, largeur complète) -->
    <EditText
        android:id="@+id/etName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Nom complet"
        app:layout_constraintTop_toBottomOf="@id/tvTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />
    
    <!-- Champ Email (sous le nom) -->
    <EditText
        android:id="@+id/etEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Email"
        android:inputType="textEmailAddress"
        app:layout_constraintTop_toBottomOf="@id/etName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
    
    <!-- Bouton Valider (sous email, à droite) -->
    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Valider"
        app:layout_constraintTop_toBottomOf="@id/etEmail"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Explication des contraintes** :

1. **tvTitle** :
   - `app:layout_constraintTop_toTopOf="parent"` : Collé au haut du parent
   - `app:layout_constraintStart_toStartOf="parent"` : Aligné à gauche
   - `app:layout_constraintEnd_toEndOf="parent"` : Aligné à droite
   - Résultat : Centré horizontalement

2. **etName** :
   - `app:layout_constraintTop_toBottomOf="@id/tvTitle"` : Sous le titre
   - `android:layout_width="0dp"` + contraintes Start/End : Prend toute la largeur

3. **btnSubmit** :
   - `app:layout_constraintEnd_toEndOf="parent"` : Aligné à droite

---

### 📝 Exemple 2 : ConstraintLayout avec Chaînes

**Cas d'usage** : Boutons de même taille alignés

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <!-- Bouton Annuler -->
    <Button
        android:id="@+id/btnCancel"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Annuler"
        android:backgroundTint="#F44336"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/btnValidate"
        app:layout_constraintHorizontal_chainStyle="spread"
        android:layout_marginTop="16dp"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="8dp" />
    
    <!-- Bouton Valider -->
    <Button
        android:id="@+id/btnValidate"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Valider"
        android:backgroundTint="#4CAF50"
        app:layout_constraintTop_toTopOf="@id/btnCancel"
        app:layout_constraintStart_toEndOf="@id/btnCancel"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Chaîne horizontale** :
- `app:layout_constraintHorizontal_chainStyle="spread"` : Écarte les boutons uniformément
- Autres styles : `spread_inside`, `packed`

---

### 📝 Exemple 3 : ConstraintLayout avec Guidelines

**Cas d'usage** : Diviser l'écran en zones

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <!-- Guideline verticale à 50% -->
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guidelineVertical"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.5" />
    
    <!-- Image à gauche de la guideline -->
    <ImageView
        android:id="@+id/ivProfile"
        android:layout_width="0dp"
        android:layout_height="200dp"
        android:src="@drawable/ic_launcher_foreground"
        android:scaleType="centerCrop"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guidelineVertical" />
    
    <!-- Texte à droite de la guideline -->
    <TextView
        android:id="@+id/tvInfo"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Informations du profil"
        android:padding="16dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toEndOf="@id/guidelineVertical"
        app:layout_constraintEnd_toEndOf="parent" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Guideline** :
- Ligne invisible pour positionner d'autres vues
- `app:layout_constraintGuide_percent="0.5"` : À 50% de la largeur

---

### 🔑 Contraintes ConstraintLayout Essentielles

#### Contraintes de base

| Contrainte | Signification |
|------------|---------------|
| `layout_constraintTop_toTopOf` | Aligner le **haut** de cette vue avec le **haut** de... |
| `layout_constraintTop_toBottomOf` | Placer le **haut** de cette vue sous le **bas** de... |
| `layout_constraintBottom_toBottomOf` | Aligner le **bas** de cette vue avec le **bas** de... |
| `layout_constraintStart_toStartOf` | Aligner le **début** (gauche LTR) avec le **début** de... |
| `layout_constraintStart_toEndOf` | Placer le **début** à droite de la **fin** de... |
| `layout_constraintEnd_toEndOf` | Aligner la **fin** (droite LTR) avec la **fin** de... |

#### Valeurs de contraintes

| Valeur | Signification |
|--------|---------------|
| `parent` | Le conteneur ConstraintLayout lui-même |
| `@id/elementId` | Un autre élément spécifique |
| `0dp` | **MATCH_CONSTRAINT** : Remplir l'espace entre les contraintes |

#### Attributs avancés

| Attribut | Description |
|----------|-------------|
| `layout_constraintHorizontal_chainStyle` | Style de chaîne : `spread`, `spread_inside`, `packed` |
| `layout_constraintDimensionRatio` | Ratio largeur:hauteur (ex: `"16:9"`) |
| `layout_constraintGuide_percent` | Position de Guideline en % |
| `layout_constraintHorizontal_bias` | Biais horizontal (0.0 = gauche, 1.0 = droite) |
| `layout_constraintVertical_bias` | Biais vertical (0.0 = haut, 1.0 = bas) |

---

## 🎯 Comparaison : LinearLayout vs ConstraintLayout

| Critère | LinearLayout | ConstraintLayout |
|---------|--------------|------------------|
| **Simplicité** | ⭐⭐⭐⭐⭐ Très simple | ⭐⭐⭐ Verbeux |
| **Performance** | ⭐⭐⭐ Décroît avec imbrications | ⭐⭐⭐⭐⭐ Excellente (flat) |
| **Flexibilité** | ⭐⭐ Limitée | ⭐⭐⭐⭐⭐ Très flexible |
| **Responsive** | ⭐⭐⭐ Avec weight | ⭐⭐⭐⭐⭐ Natif |
| **Éditeur visuel** | ⭐⭐ Basique | ⭐⭐⭐⭐⭐ Puissant |
| **Recommandation 2025** | Non | ✅ Oui |

---

## 🚀 Quand utiliser quel layout ?

### 🟢 Utiliser **LinearLayout** quand :

✅ Layout **très simple** (3-4 vues maximum)  
✅ Arrangement **purement linéaire** (vertical ou horizontal)  
✅ **Pas d'imbrication** nécessaire  
✅ Exemple : Dialog simple, liste de boutons  

### 🟢 Utiliser **ConstraintLayout** quand :

✅ Layout **complexe** avec plusieurs vues  
✅ Positionnement **relatif** entre vues  
✅ **Responsive design** (multi-écrans)  
✅ **Recommandé par défaut** pour nouveaux projets  
✅ Exemple : Écran de profil, formulaire avancé  

---

## 📚 Ressources supplémentaires

### Documentation officielle

- [LinearLayout Guide](https://developer.android.com/guide/topics/ui/layout/linear)
- [ConstraintLayout Guide](https://developer.android.com/training/constraint-layout)

### Exemples pratiques

- [Exemples LinearLayout](./exemples-linearlayout.md)
- [Exemples ConstraintLayout](./exemples-constraintlayout.md)
- [Exercices pratiques](./exercices-layouts.md)

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 4 : Interfaces, Views et Layouts
