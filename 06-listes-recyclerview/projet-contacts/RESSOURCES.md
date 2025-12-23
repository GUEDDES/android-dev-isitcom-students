# Ressources du Projet Contacts

## 🎨 Fichiers Drawables requis

Voici tous les fichiers drawable nécessaires pour le projet.

### 1. circle_bg.xml
**Emplacement** : `res/drawable/circle_bg.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="#2196F3" />
    <size
        android:width="48dp"
        android:height="48dp" />
</shape>
```

### 2. ic_add.xml
**Emplacement** : `res/drawable/ic_add.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#FFFFFF"
        android:pathData="M19,13h-6v6h-2v-6H5v-2h6V5h2v6h6v2z" />
</vector>
```

### 3. ic_call.xml
**Emplacement** : `res/drawable/ic_call.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportWidth="24"
    android:viewportHeight="24">
    <path
        android:fillColor="#6200EE"
        android:pathData="M20.01,15.38c-1.23,0 -2.42,-0.2 -3.53,-0.56 -0.35,-0.12 -0.74,-0.03 -1.01,0.24l-1.57,1.97c-2.83,-1.35 -5.48,-3.9 -6.89,-6.83l1.95,-1.66c0.27,-0.28 0.35,-0.67 0.24,-1.02 -0.37,-1.11 -0.56,-2.3 -0.56,-3.53 0,-0.54 -0.45,-0.99 -0.99,-0.99H4.19C3.65,3 3,3.24 3,3.99 3,13.28 10.73,21 20.01,21c0.71,0 0.99,-0.63 0.99,-1.18v-3.45c0,-0.54 -0.45,-0.99 -0.99,-0.99z" />
</vector>
```

---

## 🎨 Fichier colors.xml

**Emplacement** : `res/values/colors.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="purple_200">#FFBB86FC</color>
    <color name="purple_500">#FF6200EE</color>
    <color name="purple_700">#FF3700B3</color>
    <color name="teal_200">#FF03DAC5</color>
    <color name="teal_700">#FF018786</color>
    <color name="black">#FF000000</color>
    <color name="white">#FFFFFFFF</color>
    
    <!-- Couleurs pour avatars -->
    <color name="avatar_blue">#1976D2</color>
    <color name="avatar_green">#388E3C</color>
    <color name="avatar_orange">#F57C00</color>
    <color name="avatar_red">#D32F2F</color>
    <color name="avatar_purple">#7B1FA2</color>
</resources>
```

---

## 📑 Fichier strings.xml

**Emplacement** : `res/values/strings.xml`

```xml
<resources>
    <string name="app_name">Contacts</string>
    <string name="search_hint">Rechercher un contact…</string>
    <string name="add_contact">Ajouter contact</string>
    <string name="delete_contact">Supprimer</string>
    <string name="call">Appeler</string>
    <string name="no_contacts">Aucun contact</string>
    <string name="name_required">Nom obligatoire</string>
    <string name="phone_required">Téléphone obligatoire</string>
    <string name="contact_added">Contact ajouté</string>
    <string name="contact_deleted">Contact supprimé</string>
    <string name="confirm_delete">Voulez-vous supprimer ce contact ?</string>
    <string name="yes">Oui</string>
    <string name="no">Non</string>
    <string name="cancel">Annuler</string>
</resources>
```

---

## 📦 Dépendances Gradle

**Emplacement** : `app/build.gradle`

```gradle
dependencies {
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.recyclerview:recyclerview:1.3.2'
    
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}
```

---

## 📝 Permissions AndroidManifest.xml

**Emplacement** : `app/src/main/AndroidManifest.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="tn.isitcom.contacts">

    <!-- Permission pour ouvrir le dialer (ACTION_DIAL ne nécessite pas de permission) -->
    <!-- Si vous utilisez ACTION_CALL, ajoutez : -->
    <!-- <uses-permission android:name="android.permission.CALL_PHONE" /> -->

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Material3.Light">
        
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        
    </application>

</manifest>
```

---

## 📌 Organisation des fichiers

```
app/src/main/
├── java/tn/isitcom/contacts/
│   ├── MainActivity.java
│   ├── ContactAdapter.java
│   └── Contact.java
├── res/
│   ├── layout/
│   │   ├── activity_main.xml
│   │   ├── item_contact.xml
│   │   └── dialog_add_contact.xml
│   ├── drawable/
│   │   ├── circle_bg.xml
│   │   ├── ic_add.xml
│   │   └── ic_call.xml
│   ├── values/
│   │   ├── strings.xml
│   │   ├── colors.xml
│   │   └── themes.xml
│   └── mipmap/
│       ├── ic_launcher.png
│       └── ic_launcher_round.png
└── AndroidManifest.xml
```

---

## ✅ Checklist avant exécution

- [ ] Tous les fichiers drawable créés
- [ ] colors.xml configuré
- [ ] strings.xml renseigné
- [ ] Dépendances Gradle ajoutées
- [ ] AndroidManifest.xml correct
- [ ] Sync Gradle réussi
- [ ] Aucune erreur de compilation

---

**👨‍🏫 Cours Android ISITCOM 2025-2026**
