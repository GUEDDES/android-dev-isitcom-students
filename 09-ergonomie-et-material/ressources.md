# Module 9 : Ressources complémentaires

## 📚 Documentation officielle

### Material Design
- [Material Design 3](https://m3.material.io/) - Nouvelle version avec You
- [Material Guidelines](https://material.io/design) - Principes de design
- [Material Components Android](https://github.com/material-components/material-components-android) - Bibliothèque officielle
- [Material Icons](https://fonts.google.com/icons) - Icônes gratuites

### Android Developers
- [Material Design pour Android](https://developer.android.com/design/ui/mobile/guides/foundations/system-bars)
- [Dark theme](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme)
- [Animations](https://developer.android.com/training/animation)

---

## 🎨 Outils de design

### Générateurs de couleurs
- [Material Color Tool](https://material.io/resources/color/) - Générateur palette Material
- [Coolors](https://coolors.co/) - Générateur de palettes
- [Adobe Color](https://color.adobe.com/) - Roue chromatique
- [Color Hunt](https://colorhunt.co/) - Palettes prêtes à l'emploi

### Vérification contraste
- [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/) - Accessibilité
- [Contrast Ratio](https://contrast-ratio.com/) - Calcul ratio

### Icônes
- [Material Icons](https://fonts.google.com/icons) - Icônes Google
- [Flaticon](https://www.flaticon.com/) - Millions d'icônes
- [Icons8](https://icons8.com/) - Icônes gratuites
- [Font Awesome](https://fontawesome.com/) - Bibliothèque d'icônes

### Images
- [Unsplash](https://unsplash.com/) - Photos haute qualité
- [Pexels](https://www.pexels.com/) - Photos et vidéos gratuites
- [Pixabay](https://pixabay.com/) - Images libres de droits

---

## 🛠️ Bibliothèques utiles

### Images circulaires
```gradle
implementation 'de.hdodenhof:circleimageview:3.1.0'
```

Utilisation :
```xml
<de.hdodenhof.circleimageview.CircleImageView
    android:layout_width="96dp"
    android:layout_height="96dp"
    android:src="@drawable/profile"
    app:civ_border_width="2dp"
    app:civ_border_color="#FF000000"/>
```

### Chargement d'images
```gradle
implementation 'com.github.bumptech.glide:glide:4.16.0'
```

Utilisation :
```java
Glide.with(context)
    .load(imageUrl)
    .placeholder(R.drawable.placeholder)
    .into(imageView);
```

### Animations
```gradle
implementation 'com.airbnb.android:lottie:6.1.0'
```

Utilisation :
```xml
<com.airbnb.android.lottie.LottieAnimationView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:lottie_rawRes="@raw/animation"
    app:lottie_autoPlay="true"
    app:lottie_loop="true"/>
```

---

## 🎬 Tutoriels vidéo

### YouTube - Chaînes recommandées
- [Coding in Flow](https://www.youtube.com/@codinginflow) - Tutoriels Android complets
- [Philipp Lackner](https://www.youtube.com/@PhilippLackner) - Pratiques modernes
- [Android Developers](https://www.youtube.com/@AndroidDevelopers) - Chaîne officielle
- [Simplified Coding](https://www.youtube.com/@SimplifiedCoding) - Débutant friendly

### Playlists spécifiques
- Material Design Android Tutorial
- Android Dark Mode Implementation
- Navigation Component Tutorial
- Bottom Navigation View Android

---

## 📝 Articles et blogs

### Blogs Android
- [Android Developers Blog](https://android-developers.googleblog.com/)
- [ProAndroidDev](https://proandroiddev.com/) - Medium
- [AndroidPub](https://android.jlelse.eu/) - Medium
- [Raywenderlich](https://www.raywenderlich.com/android) - Tutoriels

### Articles Material Design
- [Implementing Material Design 3](https://medium.com/androiddevelopers)
- [Dark Theme Best Practices](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme)
- [Material Motion System](https://material.io/design/motion)

---

## 📖 Livres recommandés

1. **"Android UI Design"** - Jessica Thornsby
2. **"Material Design Implementation"** - Kyle Mew
3. **"Android User Interface Design"** - Ian G. Clifton

---

## 💻 Exemples de code

### GitHub Repositories
- [Material Components Examples](https://github.com/material-components/material-components-android-examples)
- [Android Architecture Components](https://github.com/android/architecture-components-samples)
- [Plaid](https://github.com/android/plaid) - App showcase Material Design

---

## ⚙️ Configurations avancées

### Thèmes dynamiques (Android 12+)

```xml
<style name="AppTheme" parent="Theme.Material3.DynamicColors.Light">
    <!-- Utilise couleurs système de l'utilisateur -->
</style>
```

### Splash Screen moderne (Android 12+)

```xml
<!-- res/values/themes.xml -->
<style name="Theme.App.SplashScreen" parent="Theme.SplashScreen">
    <item name="windowSplashScreenBackground">@color/primary</item>
    <item name="windowSplashScreenAnimatedIcon">@drawable/ic_launcher</item>
    <item name="postSplashScreenTheme">@style/AppTheme</item>
</style>
```

### Edge-to-Edge (Plein écran)

```java
WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
    return insets;
});
```

---

## 🎯 Bonnes pratiques UI/UX

### Hiérarchie visuelle
1. Utiliser tailles de police appropriées (headline, body, caption)
2. Contraste suffisant pour lisibilité (ratio 4.5:1 minimum)
3. Espacement cohérent (8dp, 16dp, 24dp...)

### Feedback utilisateur
1. **Loader** : Afficher pendant opérations longues
2. **Snackbar** : Confirmation actions
3. **Toast** : Messages courts
4. **Dialog** : Confirmations importantes

### Accessibilité
1. **contentDescription** sur toutes les images
2. **Taille minimale** des zones tactiles : 48dp
3. **Labels clairs** sur formulaires
4. **Support TalkBack** (lecteur d'écran)

---

## 🧪 Tests et débogage UI

### Layout Inspector

Android Studio → View → Tool Windows → Layout Inspector

Permet de :
- Visualiser hiérarchie des vues
- Inspecter propriétés
- Mesurer espacements

### Tester dark mode

```bash
# Activer dark mode
adb shell "cmd uimode night yes"

# Désactiver dark mode
adb shell "cmd uimode night no"
```

---

## 🔗 Liens utiles supplémentaires

- [Material Design Awards](https://design.google/library/material-design-awards-2021/) - Apps exemplaires
- [Material Studies](https://material.io/design/material-studies) - Études de cas
- [Android Design Patterns](https://www.androiddesignpatterns.com/) - Patterns courants
- [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/) - Générateur thèmes

---

## 📊 Checklist Material Design

☐ Utiliser Material Components (pas widgets standard)  
☐ Thème Material3 configuré  
☐ Couleurs définies (primary, secondary, surface...)  
☐ Dark mode supporté  
☐ Espacements multiples de 8dp  
☐ Élévations appropriées  
☐ Icônes vectorielles  
☐ Typographie cohérente  
☐ Animations fluides  
☐ Feedback utilisateur sur actions  

---

👨‍🏫 **Module 9 - Material Design** | ISITCOM 2025-2026
