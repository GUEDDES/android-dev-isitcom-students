# 📝 Fiche de Synthèse : Environnement Android

## 💡 Points Clés à Retenir

### 1. Marché Mobile 2025

```
Android : 72.2% ███████████████
iOS     : 27.1% █████
Autres  : 0.7%  █
```

### 2. Types d'Applications

| Type | Techno | Perf | Coût | Exemples |
|------|--------|------|------|----------|
| **Native** | Java/Kotlin | ⭐⭐⭐⭐⭐ | €€€€€ | Instagram |
| **Hybride** | Flutter/RN | ⭐⭐⭐⭐ | €€€ | Alibaba |
| **PWA** | HTML/JS | ⭐⭐⭐ | € | Twitter Lite |

### 3. Versions Android Importantes

- **API 24** (Android 7.0) : Base recommandée - 95% couverture
- **API 29** (Android 10) : Dark theme
- **API 33** (Android 13) : Photo picker
- **API 35** (Android 15) : Dernière stable

### 4. Architecture en Couches

```
Applications (Java/Kotlin)
      ↓
Android Framework (SDK)
      ↓
Bibliothèques natives (C/C++)
      ↓
Runtime ART
      ↓
Linux Kernel
```

### 5. Formule de Choix Rapide

**SI** performances critiques **ALORS** Native  
**SI** budget modéré **ALORS** Hybride  
**SI** contenu web **ALORS** PWA

## ✅ Checklist de Compréhension

- [ ] Je connais la part de marché d'Android
- [ ] Je comprends les différences Native/Hybride/PWA
- [ ] Je sais ce qu'est un numéro d'API
- [ ] Je connais l'architecture Android
- [ ] Je peux justifier le choix d'un type d'app

## 💬 Termes Essentiels

- **AOSP** : Android Open Source Project
- **API Level** : Numéro identifiant une version Android
- **ART** : Android Runtime (machine virtuelle)
- **SDK** : Software Development Kit
- **minSdk** : Version Android minimale supportée
- **targetSdk** : Version Android ciblée

## ⚡ Erreurs Fréquentes à Éviter

❌ Confondre version Android (14) et API (34)  
❌ Penser que Java = Android  
❌ Choisir Native par défaut sans analyser  
❌ Ignorer la compatibilité des APIs  

✅ Vérifier le dashboard des versions  
✅ Évaluer le budget avant de choisir  
✅ Tester sur plusieurs versions Android  

---

📌 **Astuce** : Bookmarquez [developer.android.com](https://developer.android.com) !