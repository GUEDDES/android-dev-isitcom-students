# 🎯 Exemples Pratiques : ConstraintLayout

## 📝 Exemple 1 : Écran de profil moderne

**Objectif** : Créer un profil avec photo, informations et boutons

### 📑 Layout XML

**Fichier** : `res/layout/activity_profile.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#FAFAFA">
    
    <!-- Bannière couleur en haut -->
    <View
        android:id="@+id/viewBanner"
        android:layout_width="0dp"
        android:layout_height="200dp"
        android:background="#2196F3"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
    
    <!-- Photo de profil circulaire -->
    <ImageView
        android:id="@+id/ivProfile"
        android:layout_width="120dp"
        android:layout_height="120dp"
        android:src="@drawable/ic_launcher_foreground"
        android:background="@drawable/circle_bg"
        android:elevation="4dp"
        app:layout_constraintTop_toTopOf="@id/viewBanner"
        app:layout_constraintBottom_toBottomOf="@id/viewBanner"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
    
    <!-- Nom -->
    <TextView
        android:id="@+id/tvName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Alice Martin"
        android:textSize="24sp"
        android:textStyle="bold"
        android:textColor="#212121"
        app:layout_constraintTop_toBottomOf="@id/viewBanner"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
    
    <!-- Email -->
    <TextView
        android:id="@+id/tvEmail"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="alice.martin@email.com"
        android:textSize="16sp"
        android:textColor="#757575"
        app:layout_constraintTop_toBottomOf="@id/tvName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="8dp" />
    
    <!-- Ligne de séparation -->
    <View
        android:id="@+id/divider"
        android:layout_width="0dp"
        android:layout_height="1dp"
        android:background="#E0E0E0"
        app:layout_constraintTop_toBottomOf="@id/tvEmail"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="16dp" />
    
    <!-- Statistiques : Guideline pour 3 colonnes -->
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline33"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.33" />
    
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guideline66"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.66" />
    
    <!-- Stat 1 : Publications -->
    <TextView
        android:id="@+id/tvPostsCount"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="42"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintTop_toBottomOf="@id/divider"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guideline33"
        android:layout_marginTop="24dp" />
    
    <TextView
        android:id="@+id/tvPostsLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Publications"
        android:textSize="14sp"
        android:textColor="#757575"
        app:layout_constraintTop_toBottomOf="@id/tvPostsCount"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guideline33"
        android:layout_marginTop="4dp" />
    
    <!-- Stat 2 : Abonnés -->
    <TextView
        android:id="@+id/tvFollowersCount"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1.2k"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="@id/tvPostsCount"
        app:layout_constraintStart_toEndOf="@id/guideline33"
        app:layout_constraintEnd_toStartOf="@id/guideline66" />
    
    <TextView
        android:id="@+id/tvFollowersLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Abonnés"
        android:textSize="14sp"
        android:textColor="#757575"
        app:layout_constraintTop_toBottomOf="@id/tvFollowersCount"
        app:layout_constraintStart_toEndOf="@id/guideline33"
        app:layout_constraintEnd_toStartOf="@id/guideline66"
        android:layout_marginTop="4dp" />
    
    <!-- Stat 3 : Abonnements -->
    <TextView
        android:id="@+id/tvFollowingCount"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="245"
        android:textSize="20sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="@id/tvPostsCount"
        app:layout_constraintStart_toEndOf="@id/guideline66"
        app:layout_constraintEnd_toEndOf="parent" />
    
    <TextView
        android:id="@+id/tvFollowingLabel"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Abonnements"
        android:textSize="14sp"
        android:textColor="#757575"
        app:layout_constraintTop_toBottomOf="@id/tvFollowingCount"
        app:layout_constraintStart_toEndOf="@id/guideline66"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="4dp" />
    
    <!-- Boutons d'action -->
    <Button
        android:id="@+id/btnEditProfile"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Modifier le profil"
        android:backgroundTint="#2196F3"
        app:layout_constraintTop_toBottomOf="@id/tvPostsLabel"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/btnShare"
        android:layout_marginTop="32dp"
        android:layout_marginStart="16dp"
        android:layout_marginEnd="8dp" />
    
    <Button
        android:id="@+id/btnShare"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Partager"
        android:backgroundTint="#4CAF50"
        app:layout_constraintTop_toTopOf="@id/btnEditProfile"
        app:layout_constraintStart_toEndOf="@id/btnEditProfile"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

### 🎨 Drawable circle_bg.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="#FFFFFF" />
    <stroke
        android:width="4dp"
        android:color="#2196F3" />
</shape>
```

---

## 📝 Exemple 2 : Carte produit avec ratio d'image

**Objectif** : Image 16:9 avec informations en dessous

### 📑 Layout XML

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="16dp">
    
    <!-- Image produit (ratio 16:9) -->
    <ImageView
        android:id="@+id/ivProductImage"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:src="@drawable/product_placeholder"
        android:scaleType="centerCrop"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintDimensionRatio="16:9" />
    
    <!-- Badge promo (en haut à droite de l'image) -->
    <TextView
        android:id="@+id/tvPromo"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="-30%"
        android:textColor="#FFFFFF"
        android:background="#F44336"
        android:padding="8dp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="@id/ivProductImage"
        app:layout_constraintEnd_toEndOf="@id/ivProductImage"
        android:layout_margin="8dp" />
    
    <!-- Nom produit -->
    <TextView
        android:id="@+id/tvProductName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Smartphone XYZ"
        android:textSize="18sp"
        android:textStyle="bold"
        android:textColor="#212121"
        app:layout_constraintTop_toBottomOf="@id/ivProductImage"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="12dp" />
    
    <!-- Prix -->
    <TextView
        android:id="@+id/tvPrice"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1299 TND"
        android:textSize="20sp"
        android:textStyle="bold"
        android:textColor="#4CAF50"
        app:layout_constraintTop_toBottomOf="@id/tvProductName"
        app:layout_constraintStart_toStartOf="parent"
        android:layout_marginTop="8dp" />
    
    <!-- Ancien prix barré -->
    <TextView
        android:id="@+id/tvOldPrice"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="1856 TND"
        android:textSize="16sp"
        android:textColor="#757575"
        android:textDecorationLine="strikethrough"
        app:layout_constraintTop_toTopOf="@id/tvPrice"
        app:layout_constraintBottom_toBottomOf="@id/tvPrice"
        app:layout_constraintStart_toEndOf="@id/tvPrice"
        android:layout_marginStart="8dp" />
    
    <!-- Bouton Ajouter au panier -->
    <Button
        android:id="@+id/btnAddToCart"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Ajouter au panier"
        android:backgroundTint="#FF9800"
        app:layout_constraintTop_toBottomOf="@id/tvPrice"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Points clés** :
- `app:layout_constraintDimensionRatio="16:9"` : Force le ratio de l'image
- Badge promo positionné **au-dessus** de l'image (overlay)

---

## 📝 Exemple 3 : Formulaire avec Guidelines

**Objectif** : Labels à gauche (30%), champs à droite (70%)

### 📑 Layout XML

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp">
    
    <!-- Guideline à 30% -->
    <androidx.constraintlayout.widget.Guideline
        android:id="@+id/guidelineLabels"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:layout_constraintGuide_percent="0.30" />
    
    <!-- Label Nom -->
    <TextView
        android:id="@+id/tvLabelName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Nom :"
        android:textStyle="bold"
        android:gravity="end"
        app:layout_constraintTop_toTopOf="@id/etName"
        app:layout_constraintBottom_toBottomOf="@id/etName"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guidelineLabels"
        android:layout_marginEnd="8dp" />
    
    <!-- Champ Nom -->
    <EditText
        android:id="@+id/etName"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="Votre nom"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toEndOf="@id/guidelineLabels"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginStart="8dp" />
    
    <!-- Label Email -->
    <TextView
        android:id="@+id/tvLabelEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Email :"
        android:textStyle="bold"
        android:gravity="end"
        app:layout_constraintTop_toTopOf="@id/etEmail"
        app:layout_constraintBottom_toBottomOf="@id/etEmail"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guidelineLabels"
        android:layout_marginEnd="8dp" />
    
    <!-- Champ Email -->
    <EditText
        android:id="@+id/etEmail"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="votre@email.com"
        android:inputType="textEmailAddress"
        app:layout_constraintTop_toBottomOf="@id/etName"
        app:layout_constraintStart_toEndOf="@id/guidelineLabels"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp"
        android:layout_marginStart="8dp" />
    
    <!-- Label Téléphone -->
    <TextView
        android:id="@+id/tvLabelPhone"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:text="Tél :"
        android:textStyle="bold"
        android:gravity="end"
        app:layout_constraintTop_toTopOf="@id/etPhone"
        app:layout_constraintBottom_toBottomOf="@id/etPhone"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/guidelineLabels"
        android:layout_marginEnd="8dp" />
    
    <!-- Champ Téléphone -->
    <EditText
        android:id="@+id/etPhone"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:hint="+216 12 345 678"
        android:inputType="phone"
        app:layout_constraintTop_toBottomOf="@id/etEmail"
        app:layout_constraintStart_toEndOf="@id/guidelineLabels"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp"
        android:layout_marginStart="8dp" />
    
    <!-- Bouton Valider (aligné à droite) -->
    <Button
        android:id="@+id/btnSubmit"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Valider"
        app:layout_constraintTop_toBottomOf="@id/etPhone"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="24dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

---

## 📝 Exemple 4 : Centrage absolu avec Bias

**Objectif** : Centrer un logo exactement au milieu de l'écran

### 📑 Layout XML

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#2196F3">
    
    <!-- Logo centré -->
    <ImageView
        android:id="@+id/ivLogo"
        android:layout_width="150dp"
        android:layout_height="150dp"
        android:src="@drawable/logo"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintVertical_bias="0.5"
        app:layout_constraintHorizontal_bias="0.5" />
    
    <!-- Texte sous le logo -->
    <TextView
        android:id="@+id/tvAppName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="MonApplication"
        android:textSize="24sp"
        android:textColor="#FFFFFF"
        android:textStyle="bold"
        app:layout_constraintTop_toBottomOf="@id/ivLogo"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_marginTop="16dp" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Bias** :
- `0.5` : Parfaitement centré (valeur par défaut)
- `0.0` : Tout à gauche/haut
- `1.0` : Tout à droite/bas

---

## 📝 Exemple 5 : Chaîne horizontale (Spread)

**Objectif** : 3 icônes également espacées

### 📑 Layout XML

```xml
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:padding="16dp">
    
    <!-- Icône 1 : Accueil -->
    <ImageView
        android:id="@+id/ivHome"
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:src="@drawable/ic_home"
        android:padding="8dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/ivSearch"
        app:layout_constraintHorizontal_chainStyle="spread" />
    
    <!-- Icône 2 : Recherche -->
    <ImageView
        android:id="@+id/ivSearch"
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:src="@drawable/ic_search"
        android:padding="8dp"
        app:layout_constraintTop_toTopOf="@id/ivHome"
        app:layout_constraintStart_toEndOf="@id/ivHome"
        app:layout_constraintEnd_toStartOf="@id/ivProfile" />
    
    <!-- Icône 3 : Profil -->
    <ImageView
        android:id="@+id/ivProfile"
        android:layout_width="48dp"
        android:layout_height="48dp"
        android:src="@drawable/ic_profile"
        android:padding="8dp"
        app:layout_constraintTop_toTopOf="@id/ivHome"
        app:layout_constraintStart_toEndOf="@id/ivSearch"
        app:layout_constraintEnd_toEndOf="parent" />
        
</androidx.constraintlayout.widget.ConstraintLayout>
```

**Chain styles** :
- `spread` : Écart uniforme
- `spread_inside` : Première/dernière collées aux bords
- `packed` : Groupées ensemble

---

## 🎯 Exercices pratiques

### 🟢 Exercice 1 : Écran login moderne

Créer avec ConstraintLayout :
- Logo centré en haut
- Champs email/password (largeur complète)
- Bouton connexion centré
- Lien "Mot de passe oublié" en bas

### 🟢 Exercice 2 : Carte météo

Créer une carte avec :
- Ville en haut à gauche
- Température (grande) centrée
- Icône météo en haut à droite
- Prévisions 3 jours (chaîne horizontale) en bas

### 🟢 Exercice 3 : Écran de tchat

Créer interface tchat avec :
- Photo profil en haut à gauche
- Nom et statut à côté de la photo
- Icône appel en haut à droite
- Zone de messages (au milieu)
- Champ saisie + bouton envoyer (en bas)

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 4 : Interfaces, Views et Layouts
