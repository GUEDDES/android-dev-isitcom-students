# TD 08 : Projet complet - Application de gestion de tâches

## 🎯 Objectifs

- Mobiliser toutes les compétences acquises.
- Créer une application complète et fonctionnelle.
- Appliquer les bonnes pratiques.

---

## Cahier des charges

### Fonctionnalités attendues

#### 1. Écran principal (HomeFragment)

- RecyclerView affichant toutes les tâches.
- Chaque tâche affiche : titre, description, date limite, statut.
- FAB pour ajouter une nouvelle tâche.
- Filtres : Toutes, Actives, Terminées (Chips).

#### 2. Écran ajout/modification (AddTaskActivity ou Fragment)

- TextInputLayout pour titre et description.
- DatePicker pour date limite.
- Spinner pour catégorie (Travail, Personnel, Études).
- Bouton "Enregistrer".

#### 3. Écran détail (DetailFragment)

- Affichage complet de la tâche.
- Bouton "Modifier".
- Bouton "Supprimer" (avec confirmation).
- Checkbox "Terminée".

#### 4. Écran statistiques (StatsFragment)

- Nombre total de tâches.
- Tâches terminées vs actives.
- Répartition par catégorie.

#### 5. Bottom Navigation

- 3 onglets : Tâches, Statistiques, Profil.

---

## Spécifications techniques

### Base de données Room

**Entity Task** :
- id (PrimaryKey, autoGenerate)
- title (String)
- description (String)
- deadline (String)
- category (String)
- completed (boolean)

**DAO TaskDao** :
- insert, update, delete
- getAllTasks()
- getTasksByStatus(boolean completed)
- getTasksByCategory(String category)

### Architecture

- Activity principale avec Bottom Navigation.
- 3 Fragments (Home, Stats, Profile).
- Navigation Component.
- Material Design 3.
- Mode sombre supporté.

---

## Consignes de rendu

### 1. Code source

- Projet Android Studio complet (zip).
- Code bien structuré (packages séparés).
- Commentaires sur parties complexes.

### 2. APK

- Fichier APK fonctionnel.

### 3. Documentation

- README.md avec :
  - Description de l'application.
  - Captures d'écran.
  - Guide d'utilisation.
  - Difficultés rencontrées.
  - Améliorations possibles.

### 4. Vidéo de démonstration (optionnel)

- 2-3 minutes montrant les fonctionnalités.

---

## Barème (/30)

| Critère | Points |
|---------|--------|
| Base de données Room | 5 |
| CRUD complet | 5 |
| RecyclerView fonctionnel | 4 |
| Navigation (Fragments + Bottom Nav) | 4 |
| Filtres et recherche | 3 |
| Statistiques | 3 |
| Material Design | 3 |
| Mode sombre | 2 |
| Qualité du code | 3 |
| Documentation | 3 |
| **Total** | **/35** |

---

## Conseils

- Commencer par la base de données.
- Implémenter les fonctionnalités de base avant les bonus.
- Tester régulièrement.
- Soigner l'interface utilisateur.
- Gérer les cas limites (liste vide, champs vides).

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
