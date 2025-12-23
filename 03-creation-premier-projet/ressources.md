# Module 3 : Ressources complémentaires

## 📚 Tutoriels vidéo

### En français
- [Créer sa première app Android - Grafikart](https://www.youtube.com/watch?v=sSz0pE1XKBI)
- [Android Studio : Premier projet - Developpez.com](https://www.youtube.com/results?search_query=android+studio+premier+projet)

### En anglais
- [Build Your First Android App - Android Developers](https://developer.android.com/codelabs/build-your-first-android-app)
- [Android Development for Beginners - freeCodeCamp](https://www.youtube.com/watch?v=fis26HvvDII)

---

## 💻 Outils recommandés

### Générateurs d'icônes
- [Android Asset Studio](https://romannurik.github.io/AndroidAssetStudio/) - Icônes launcher
- [Material Icons](https://fonts.google.com/icons) - Icônes Material Design
- [Flaticon](https://www.flaticon.com/) - Icônes gratuites

### Générateurs de couleurs
- [Material Design Color Tool](https://material.io/resources/color/)
- [Coolors](https://coolors.co/) - Palettes de couleurs
- [Adobe Color](https://color.adobe.com/)

### Mockups et prototypage
- [Figma](https://www.figma.com/) - Design collaboratif
- [Adobe XD](https://www.adobe.com/products/xd.html)
- [Sketch](https://www.sketch.com/)

---

## 📖 Documentation officielle

- [Guide de démarrage Android](https://developer.android.com/guide)
- [Créer votre premier projet](https://developer.android.com/training/basics/firstapp)
- [Anatomie d'une app Android](https://developer.android.com/guide/components/fundamentals)
- [Structure de projet](https://developer.android.com/studio/projects)

---

## 📝 Articles recommandés

### Concepts de base
- [Understanding Android Project Structure](https://medium.com/@vatsaldesai/android-project-structure-explained-5e3e0d9c1e1d)
- [AndroidManifest.xml Explained](https://developer.android.com/guide/topics/manifest/manifest-intro)
- [What is R.java?](https://stackoverflow.com/questions/4953077/what-is-r-java-in-android)

### Bonnes pratiques
- [Android Best Practices](https://developer.android.com/topic/best-practices)
- [Clean Code in Android](https://medium.com/@cesarferreira/clean-code-for-android-10b9f028493a)

---

## ❓ FAQ

### Pourquoi mon app crash au lancement ?
- Vérifier Logcat pour l'exception
- Vérifier AndroidManifest.xml (activity déclarée ?)
- Vérifier setContentView() appelé

### findViewById() retourne null ?
- S'assurer que setContentView() est appelé AVANT findViewById()
- Vérifier l'ID dans le XML (faute de frappe ?)
- Clean + Rebuild Project

### Gradle sync fails ?
- Vérifier connexion internet
- File > Invalidate Caches / Restart
- Supprimer .gradle dans le projet et resynchroniser

### Émulateur trop lent ?
- Réduire résolution AVD (720p au lieu de 1080p)
- Réduire RAM (2GB au lieu de 4GB)
- Activer l'accélération matérielle (HAXM/KVM)
- Utiliser appareil physique

---

## 🛠️ Raccourcis Android Studio

| Action | Windows/Linux | macOS |
|--------|---------------|-------|
| Exécuter app | Shift + F10 | Ctrl + R |
| Déboguer | Shift + F9 | Ctrl + D |
| Rechercher fichier | Ctrl + Shift + N | Cmd + Shift + O |
| Auto-format code | Ctrl + Alt + L | Cmd + Option + L |
| Auto-import | Alt + Enter | Option + Enter |
| Dupliquer ligne | Ctrl + D | Cmd + D |
| Commenter ligne | Ctrl + / | Cmd + / |

---

## 💡 Astuces

### Générer code automatiquement
- `Alt + Insert` (Windows) ou `Cmd + N` (Mac) dans une classe
- Génère getters/setters, constructeurs, toString()...

### Live Templates
- Taper `fori` puis Tab → Génère boucle for
- Taper `sout` puis Tab → System.out.println()
- Taper `psvm` puis Tab → public static void main()

### Extraire ressource
- Clic droit sur texte/couleur en dur
- Refactor > Extract > String/Color Resource

---

## 🎯 Checklist projet HelloWorld

✅ Projet créé avec "Empty Views Activity"  
✅ Nom de package valide (tn.isitcom.monapp)  
✅ minSdk 24 (Android 7.0)  
✅ targetSdk 35 (Android 15)  
✅ Gradle sync réussi  
✅ AVD créé ou appareil connecté  
✅ App se lance sans erreur  
✅ "Hello World!" visible à l'écran  

---

## 🔗 Liens utiles

- [Stack Overflow - Android](https://stackoverflow.com/questions/tagged/android)
- [Reddit - r/androiddev](https://www.reddit.com/r/androiddev/)
- [Android Developers Blog](https://android-developers.googleblog.com/)
- [Vogella Android Tutorials](https://www.vogella.com/tutorials/android.html)

---

👨‍🏫 **Module 3 - Création premier projet** | ISITCOM 2025-2026
