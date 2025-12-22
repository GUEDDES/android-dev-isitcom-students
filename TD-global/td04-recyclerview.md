# TD04 : RecyclerView et listes

## 🎯 Objectifs

- Créer et configurer un RecyclerView.
- Implémenter un Adapter personnalisé.
- Gérer les clics sur les éléments.

---

## Partie 1 : Liste simple (60 min)

### Exercice 1.1 : Configuration de base

1. Ajouter la dépendance dans `build.gradle` :

```gradle
implementation 'androidx.recyclerview:recyclerview:1.3.2'
```

2. Ajouter RecyclerView dans `activity_main.xml` :

```xml
<androidx.recyclerview.widget.RecyclerView
    android:id="@+id/recyclerView"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

### Exercice 1.2 : Classe de données

Créer `Student.java` :

```java
public class Student {
    private String name;
    private String group;
    private int age;

    public Student(String name, String group, int age) {
        this.name = name;
        this.group = group;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public String getGroup() { return group; }
    public int getAge() { return age; }
}
```

### Exercice 1.3 : Layout de l'élément

Créer `item_student.xml` :

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    app:cardCornerRadius="8dp"
    app:cardElevation="4dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        android:padding="16dp">

        <TextView
            android:id="@+id/textName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Nom"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/textGroup"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Groupe"
            android:layout_marginTop="4dp" />

        <TextView
            android:id="@+id/textAge"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Âge"
            android:layout_marginTop="4dp" />

    </LinearLayout>

</androidx.cardview.widget.CardView>
```

### Exercice 1.4 : Adapter

Créer `StudentAdapter.java` :

```java
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.textName.setText(student.getName());
        holder.textGroup.setText("Groupe: " + student.getGroup());
        holder.textAge.setText("Âge: " + student.getAge());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textGroup, textAge;

        public StudentViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textGroup = itemView.findViewById(R.id.textGroup);
            textAge = itemView.findViewById(R.id.textAge);
        }
    }
}
```

### Exercice 1.5 : Configuration dans l'Activity

```java
RecyclerView recyclerView = findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

List<Student> students = new ArrayList<>();
students.add(new Student("Ahmed Ben Ali", "L3-GL-A", 22));
students.add(new Student("Fatma Gharbi", "L3-GL-B", 21));
students.add(new Student("Mohamed Slimi", "L3-GL-A", 23));
students.add(new Student("Amira Mansour", "L3-GL-B", 22));

StudentAdapter adapter = new StudentAdapter(students);
recyclerView.setAdapter(adapter);
```

---

## Partie 2 : Gestion des clics (30 min)

### Exercice 2.1 : Interface de callback

Créer `OnStudentClickListener.java` :

```java
public interface OnStudentClickListener {
    void onStudentClick(Student student);
}
```

### Exercice 2.2 : Modifier l'Adapter

```java
private OnStudentClickListener listener;

public StudentAdapter(List<Student> studentList, OnStudentClickListener listener) {
    this.studentList = studentList;
    this.listener = listener;
}

@Override
public void onBindViewHolder(StudentViewHolder holder, int position) {
    Student student = studentList.get(position);
    holder.textName.setText(student.getName());
    holder.textGroup.setText("Groupe: " + student.getGroup());
    holder.textAge.setText("Âge: " + student.getAge());

    holder.itemView.setOnClickListener(v -> listener.onStudentClick(student));
}
```

### Exercice 2.3 : Utilisation

```java
StudentAdapter adapter = new StudentAdapter(students, student -> {
    Toast.makeText(this, "Clic sur " + student.getName(), Toast.LENGTH_SHORT).show();
});
```

---

## Partie 3 : Affichage en grille (15 min)

### Exercice 3.1 : GridLayoutManager

Remplacer le LinearLayoutManager :

```java
recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 colonnes
```

---

## 🎯 TP Noté : Liste de produits (/20)

### Consignes

Créer une application "Catalogue de produits" :

1. **Classe Product** :
   - Nom, prix, catégorie, image (drawable).

2. **Liste de produits** :
   - RecyclerView avec CardView.
   - Afficher au moins 8 produits.
   - Image, nom, prix, catégorie.

3. **Fonctionnalités** :
   - Clic sur un produit → ouvre un écran de détail.
   - Écran de détail affiche toutes les informations.
   - Bouton "Ajouter au panier" → Toast de confirmation.

4. **Affichage** :
   - Toggle entre liste et grille (2 colonnes).

### Barème

| Critère | Points |
|---------|--------|
| Classe Product correcte | 2 |
| RecyclerView fonctionnel | 4 |
| Adapter complet | 4 |
| Gestion des clics | 3 |
| Écran de détail | 3 |
| Toggle liste/grille | 2 |
| Interface soignée | 2 |

---

👨‍🏫 **Enseignant** : A. GUEDDES – ISITCOM 2025-2026
