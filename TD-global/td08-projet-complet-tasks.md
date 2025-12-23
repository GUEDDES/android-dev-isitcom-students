# TD08 : Application complète de gestion de tâches

## 🎯 Objectifs

Réaliser une application complète intégrant tous les concepts vus.

---

## Description du projet

Créer "TaskMaster", une application de gestion de tâches avancée.

---

## Fonctionnalités requises

### 1. Base de données (Room)

**Entity Task** :
```java
@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String title;
    private String description;
    private String category; // Travail, Personnel, Urgences
    private String priority; // Haute, Moyenne, Basse
    private long dueDate;
    private boolean completed;
    
    // Constructeur, getters, setters
}
```

**DAO** avec requêtes :
- `getAllTasks()`
- `getTasksByCategory(String category)`
- `getTasksByPriority(String priority)`
- `getCompletedTasks()`
- `getActiveTasks()`
- `searchTasks(String query)`

### 2. Interface utilisateur

**MainActivity** avec 3 onglets (Bottom Navigation) :

1. **Toutes** : afficher toutes les tâches.
2. **Actives** : tâches non terminées.
3. **Terminées** : tâches complétées.

**AddTaskActivity** :
- Champs : titre, description.
- Spinners : catégorie, priorité.
- DatePicker pour la date d'échéance.
- Bouton "Enregistrer".

### 3. Fonctionnalités

- **Ajout** : FAB.
- **Modification** : clic sur une tâche.
- **Suppression** : swipe to delete avec Snackbar "Annuler".
- **Compléter** : checkbox.
- **Recherche** : SearchView dans la toolbar.
- **Filtres** : menu avec filtres par catégorie/priorité.
- **Tri** : par date, priorité, nom.

### 4. Design

- Material Design complet.
- Dark Mode.
- Couleurs selon priorité (rouge=haute, jaune=moyenne, vert=basse).
- Animations.

---

## Code exemple : Swipe to Delete

```java
public class MainActivity extends AppCompatActivity {

    private void setupSwipeToDelete() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Task deletedTask = taskList.get(position);
                
                database.taskDao().delete(deletedTask);
                taskList.remove(position);
                adapter.notifyItemRemoved(position);

                Snackbar.make(recyclerView, "Tâche supprimée", Snackbar.LENGTH_LONG)
                    .setAction("Annuler", v -> {
                        database.taskDao().insert(deletedTask);
                        taskList.add(position, deletedTask);
                        adapter.notifyItemInserted(position);
                    })
                    .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
```

---

## Code exemple : DatePicker

```java
private void showDatePicker() {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog datePickerDialog = new DatePickerDialog(
        this,
        (view, selectedYear, selectedMonth, selectedDay) -> {
            calendar.set(selectedYear, selectedMonth, selectedDay);
            selectedDate = calendar.getTimeInMillis();
            textDate.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        },
        year, month, day
    );
    datePickerDialog.show();
}
```

---

## Architecture du projet

```
TaskMaster/
├── data/
│   ├── database/
│   │   ├── AppDatabase.java
│   │   ├── Task.java
│   │   └── TaskDao.java
│   └── model/
├── ui/
│   ├── main/
│   │   ├── MainActivity.java
│   │   └── fragments/
│   └── add/
│       └── AddTaskActivity.java
├── adapter/
│   └── TaskAdapter.java
└── utils/
    └── DateUtils.java
```

---

## 📄 Livrable

1. Projet Android Studio complet.
2. APK compilé.
3. Vidéo de démonstration (3 min).
4. README avec :
   - Fonctionnalités implémentées.
   - Captures d'écran.
   - Difficultés rencontrées.

---

## 🏆 Barème (/20)

| Critère | Points |
|---------|--------|
| Base de données fonctionnelle | 4 |
| CRUD complet | 4 |
| Interface Material Design | 3 |
| Navigation et Fragments | 3 |
| Fonctionnalités avancées (recherche, filtres, swipe) | 4 |
| Code propre et architecture | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES
