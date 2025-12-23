# 🎯 Exercices Pratiques : Layouts Android

## 📝 Exercice 1 : Calculatrice Simple (LinearLayout)

### 🎯 Objectif

Créer une calculatrice basique avec disposition en grille.

### 📘 Consignes

1. Utiliser **LinearLayout vertical** comme conteneur principal
2. **Affichage** : TextView en haut (résultat)
3. **Grille de boutons** : 4 LinearLayout horizontaux imbriqués
4. **Boutons** :
   - Ligne 1 : 7, 8, 9, /
   - Ligne 2 : 4, 5, 6, *
   - Ligne 3 : 1, 2, 3, -
   - Ligne 4 : 0, C, =, +

### 🚩 Contraintes techniques

- Tous les boutons de **même taille** (layout_weight)
- Espacement uniforme entre boutons
- Affichage : textSize 32sp, aligné à droite
- Couleurs : 
  - Chiffres : bleu (#2196F3)
  - Opérateurs : orange (#FF9800)
  - Égal : vert (#4CAF50)
  - Clear : rouge (#F44336)

### 💡 Indices

```xml
<!-- Exemple ligne 1 -->
<LinearLayout
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    
    <Button
        android:layout_width="0dp"
        android:layout_weight="1"
        android:text="7" />
    <!-- ... autres boutons -->
</LinearLayout>
```

---

## 📝 Exercice 2 : Écran Login Moderne (ConstraintLayout)

### 🎯 Objectif

Créer un écran de connexion moderne avec logo, champs et boutons.

### 📘 Consignes

1. Utiliser **ConstraintLayout**
2. **Logo** : ImageView centré en haut (120x120dp)
3. **Titre** : "Bienvenue" sous le logo
4. **Champs** :
   - Email (inputType: textEmailAddress)
   - Mot de passe (inputType: textPassword)
5. **Boutons** :
   - "Se connecter" (largeur complète)
   - "Mot de passe oublié ?" (TextView cliquable en bas)

### 🚩 Contraintes techniques

- Logo centré horizontalement, margin top 48dp
- Champs : largeur 0dp avec contraintes Start/End au parent
- Espace entre champs : 16dp
- Bouton connexion : margin top 24dp
- Lien mot de passe : aligné center, margin bottom 16dp

### 💡 Indices

```xml
<!-- Logo centré -->
<ImageView
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    android:layout_marginTop="48dp" />
```

---

## 📝 Exercice 3 : Carte Produit (ConstraintLayout)

### 🎯 Objectif

Créer une carte produit e-commerce.

### 📘 Consignes

1. **Image produit** : Ratio 16:9, largeur complète
2. **Badge promo** : "-20%" en haut à droite de l'image
3. **Nom produit** : Sous l'image
4. **Prix** : À gauche sous le nom (vert, 20sp, bold)
5. **Ancien prix** : À côté du prix (barré, gris)
6. **Note** : 5 étoiles à droite de la même ligne
7. **Description** : Texte sous les prix
8. **Boutons** : "Ajouter au panier" et "Favoris" en bas

### 🚩 Contraintes techniques

- Image : `app:layout_constraintDimensionRatio="16:9"`
- Badge : TextView avec background rouge, padding 8dp
- Boutons : Chaîne horizontale spread
- Ancien prix : `android:textDecorationLine="strikethrough"`

---

## 📝 Exercice 4 : Profil Utilisateur (ConstraintLayout + Guidelines)

### 🎯 Objectif

Créer un écran de profil avec statistiques.

### 📘 Consignes

1. **Bannière** : View coloré en haut (200dp hauteur)
2. **Photo profil** : Centrée sur la bannière (moitié dessus, moitié dessous)
3. **Nom** : Sous la photo, centré
4. **Bio** : Sous le nom, centré
5. **Statistiques** : 3 colonnes (Publications, Abonnés, Abonnements)
   - Utiliser 2 Guidelines à 33% et 66%
6. **Boutons** : "Modifier profil" et "Partager" (chaîne horizontale)

### 🚩 Contraintes techniques

- Photo profil : Contraintes Top sur bannière ET Bottom sur bannière (centrée verticalement)
- Guidelines : `app:layout_constraintGuide_percent="0.33"` et `0.66`
- Stats : Nombre (bold, 20sp) et label (14sp, gris) empilés verticalement

---

## 📝 Exercice 5 : Interface Tchat (ConstraintLayout)

### 🎯 Objectif

Créer une interface de messagerie.

### 📘 Consignes

1. **Header** (en haut) :
   - Bouton retour à gauche
   - Photo contact (circulaire)
   - Nom contact
   - Statut ("En ligne" en vert)
   - Icône appel à droite
2. **Zone messages** (milieu) :
   - RecyclerView (pour messages) - occupe l'espace restant
3. **Footer** (en bas) :
   - EditText pour saisie (70% largeur)
   - Bouton envoyer (30% largeur)

### 🚩 Contraintes techniques

- Header : hauteur 72dp, élévation 4dp
- Photo contact : 40dp x 40dp, shape circulaire
- RecyclerView : 
  - `app:layout_constraintTop_toBottomOf="@id/header"`
  - `app:layout_constraintBottom_toTopOf="@id/footer"`
  - `android:layout_height="0dp"` (remplir l'espace)
- Footer : Chaîne horizontale pour EditText + Bouton

---

## 📝 Exercice 6 : Formulaire avec Labels (ConstraintLayout + Guideline)

### 🎯 Objectif

Formulaire avec labels alignés à gauche.

### 📘 Consignes

1. **Guideline** à 25% pour séparer labels et champs
2. **Champs** :
   - Nom
   - Prénom
   - Email
   - Téléphone
   - Adresse
3. **Labels** : À gauche de la guideline, alignés à droite
4. **Champs de saisie** : À droite de la guideline
5. **Bouton Valider** : En bas, aligné à droite

### 🚩 Contraintes techniques

- Labels : `android:gravity="end"` + `android:textStyle="bold"`
- Labels : Contraintes Top/Bottom sur le champ correspondant (vertical center)
- Champs : Espacement 16dp entre chaque

---

## 📝 Exercice 7 : Page Panier E-commerce (Mix)

### 🎯 Objectif

Créer un écran panier d'achat.

### 📘 Consignes

1. **Structure principale** : ConstraintLayout
2. **Liste produits** : RecyclerView (scrollable)
3. **Footer fixe** (bas de l'écran) :
   - Total (prix en grand) à gauche
   - Bouton "Commander" à droite
   - LinearLayout horizontal pour cette zone

**Item panier** (layout séparé pour RecyclerView) :
- Image produit (80x80dp) à gauche
- Nom + prix à côté (vertical)
- Boutons +/- et quantité à droite
- Bouton supprimer (icône poubelle) en haut à droite

### 🚩 Contraintes techniques

- RecyclerView : `android:layout_height="0dp"` + contraintes top/bottom
- Footer : `app:layout_constraintBottom_toBottomOf="parent"`
- Footer : Élévation 8dp pour shadow

---

## 📝 Exercice 8 : Écran Splash (ConstraintLayout)

### 🎯 Objectif

Écran de démarrage avec logo et progress bar.

### 📘 Consignes

1. **Background** : Dégradé bleu
2. **Logo** : Centré parfaitement (horizontal + vertical)
3. **Nom app** : Sous le logo, centré
4. **ProgressBar** : En bas, centré, margin bottom 48dp
5. **Version** : Tout en bas ("v1.0.0")

### 🚩 Contraintes techniques

- Logo : 
  - `app:layout_constraintTop_toTopOf="parent"`
  - `app:layout_constraintBottom_toBottomOf="parent"`
  - `app:layout_constraintVertical_bias="0.4"` (un peu plus haut)
- ProgressBar : Style `?android:attr/progressBarStyleHorizontal`

---

## 🎯 Exercices Bonus (Avancés)

### 🟠 Bonus 1 : Grille de photos Instagram-like

Créer une grille 3x3 avec images carrées (ratio 1:1).
**Astuce** : Flow helper ou GridLayout

### 🟠 Bonus 2 : Écran météo complet

- Ville et date en haut
- Grande icône météo + température centrée
- Prévisions horaires (horizontal scroll)
- Prévisions 7 jours (vertical scroll)
- Détails (humidité, vent, UV...)

### 🟠 Bonus 3 : Player audio/vidéo

- Guête vidéo en haut (16:9)
- Titre et artiste
- SeekBar (barre de progression)
- Boutons : Précédent, Play/Pause, Suivant
- Temps écoulé / durée totale

---

## 📊 Grille d'évaluation

| Critère | Points |
|----------|--------|
| **Structure** : Layout approprié choisi | /2 |
| **Contraintes** : Correctes et optimales | /3 |
| **Espacement** : Margins et paddings cohérents | /2 |
| **Responsive** : Adaptation à différentes tailles | /2 |
| **Nommage** : IDs clairs et conventions | /1 |
| **Design** : Couleurs, tailles, alignements | /2 |
| **Code** : Propreté et lisibilité XML | /2 |
| **Fonctionnalité** : Code Java/Kotlin fonctionnel | /3 |
| **Bonus** : Animations, Material Design | /3 |
| **TOTAL** | /20 |

---

## 📚 Ressources utiles

### Documentation

- [LinearLayout Guide](https://developer.android.com/guide/topics/ui/layout/linear)
- [ConstraintLayout Guide](https://developer.android.com/training/constraint-layout)
- [Material Design Components](https://material.io/components)

### Outils

- **Android Studio Layout Inspector** : Analyser la hiérarchie
- **Layout Editor** : Mode Design pour drag-drop
- **Blueprint Mode** : Voir les contraintes visuellement

---

## ✅ Checklist avant rendu

- [ ] Aucune erreur de compilation
- [ ] Tous les IDs uniques et nommés correctement
- [ ] Pas d'avertissements (hardcoded strings, missing contentDescription)
- [ ] Testé sur émulateur ET appareil réel si possible
- [ ] Testé en mode portrait ET paysage
- [ ] Espacement uniforme et cohérent
- [ ] Couleurs définies dans colors.xml
- [ ] Strings définies dans strings.xml
- [ ] Code Java/Kotlin commenté

---

👨‍🏫 **Cours Android ISITCOM 2025-2026**  
📚 Module 4 : Interfaces, Views et Layouts
