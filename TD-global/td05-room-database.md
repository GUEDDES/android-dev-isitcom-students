# TD 05 : Room Database et CRUD

**Durée** : 2 heures  
**Objectif** : Implémenter une base de données locale avec Room et effectuer des opérations CRUD.

---

## Partie 1 : Configuration Room (30 min)

### Exercice 1.1 : Création de la base

Créer une base Room pour gérer des étudiants :

**Entity Student** :
```java
@Entity(tableName = "students")
public class Student {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String major;
    private int age;
    // Constructeur, getters, setters
}
```

**StudentDao** :
- `insert(Student)`
- `update(Student)`
- `delete(Student)`
- `getAllStudents()`
- `getStudentById(int id)`

**AppDatabase** :
- Créer la classe Database avec singleton.

**Livrable** : Code des 3 classes (Entity, DAO, Database).

---

## Partie 2 : Interface CRUD (60 min)

### Exercice 2.1 : Application de gestion d'étudiants

Créer une application complète :

**Écran principal** :
- RecyclerView affichant tous les étudiants
- FAB pour ajouter un étudiant
- Clic sur étudiant → écran de modification
- Clic long → suppression avec confirmation

**Écran ajout/modification** :
- Champs : nom, filière, âge
- Boutons : Enregistrer, Annuler

**Contraintes** :
- Valider que tous les champs sont remplis.
- Afficher un Snackbar après chaque opération.
- Rafraîchir automatiquement la liste.

**Livrable** : Projet complet fonctionnel.

---

## Partie 3 : Recherche et filtres (30 min)

### Exercice 3.1 : Recherche d'étudiants

1. Ajouter une barre de recherche dans le menu.
2. Implémenter une requête DAO :
   ```java
   @Query("SELECT * FROM students WHERE name LIKE '%' || :query || '%'")
   List<Student> searchStudents(String query);
   ```
3. Filtrer la liste en temps réel lors de la saisie.

**Livrable** : Code source de la recherche.

---

## Barème (/20)

| Exercice | Points |
|----------|--------|
| Configuration Room (Entity, DAO, Database) | 5 |
| Interface CRUD complète | 10 |
| Recherche et filtres | 5 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
