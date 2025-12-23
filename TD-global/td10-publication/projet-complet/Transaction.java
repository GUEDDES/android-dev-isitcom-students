package tn.isitcom.budgettracker.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

import tn.isitcom.budgettracker.data.database.DateConverter;

/**
 * Entité Room représentant une transaction financière.
 * 
 * @Entity : Indique que cette classe représente une table dans la base de données
 * tableName : Nom de la table (optionnel, par défaut = nom de la classe)
 * 
 * PEDAGOGIE :
 * - Une Entity = Une table SQL
 * - Chaque champ = Une colonne
 * - Room génère automatiquement le code SQL
 */
@Entity(tableName = "transactions")
@TypeConverters({DateConverter.class})  // Pour convertir Date <-> Long
public class Transaction {
    
    /**
     * Clé primaire auto-incrémentée.
     * @PrimaryKey : Définit la clé primaire
     * autoGenerate = true : SQLite génère automatiquement l'ID
     */
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    /**
     * Description de la transaction.
     * @ColumnInfo : Définit le nom de la colonne (optionnel)
     * Si omis, Room utilise le nom de la variable
     */
    @ColumnInfo(name = "description")
    private String description;
    
    /**
     * Montant de la transaction.
     * Type double pour supporter les décimales (ex: 25.50 TND)
     */
    @ColumnInfo(name = "amount")
    private double amount;
    
    /**
     * Type de transaction : INCOME (revenu) ou EXPENSE (dépense).
     * Room stocke l'enum comme String automatiquement
     */
    @ColumnInfo(name = "type")
    private TransactionType type;
    
    /**
     * Date de la transaction.
     * Utilise TypeConverter pour convertir Date en Long (timestamp)
     */
    @ColumnInfo(name = "date")
    private Date date;
    
    /**
     * Catégorie optionnelle (ex: "Alimentation", "Transport")
     */
    @ColumnInfo(name = "category")
    private String category;
    
    // ========== CONSTRUCTEURS ==========
    
    /**
     * Constructeur complet.
     * IMPORTANT : Room nécessite un constructeur avec tous les champs (sauf ID auto)
     */
    public Transaction(String description, double amount, TransactionType type, Date date, String category) {
        this.description = description;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.category = category;
    }
    
    /**
     * Constructeur simplifié sans catégorie.
     */
    public Transaction(String description, double amount, TransactionType type, Date date) {
        this(description, amount, type, date, null);
    }
    
    // ========== GETTERS ET SETTERS ==========
    
    /**
     * IMPORTANT : Room nécessite des getters/setters publics pour tous les champs.
     * C'est ainsi que Room lit et écrit les données.
     */
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public TransactionType getType() {
        return type;
    }
    
    public void setType(TransactionType type) {
        this.type = type;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    // ========== MÉTHODES UTILITAIRES ==========
    
    /**
     * Vérifie si la transaction est un revenu.
     * @return true si INCOME, false si EXPENSE
     */
    public boolean isIncome() {
        return type == TransactionType.INCOME;
    }
    
    /**
     * Retourne le montant signé : positif pour revenu, négatif pour dépense.
     * Utile pour calculer le solde : solde += getSignedAmount()
     */
    public double getSignedAmount() {
        return isIncome() ? amount : -amount;
    }
    
    /**
     * toString() pour debug et logs.
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", date=" + date +
                ", category='" + category + '\'' +
                '}';
    }
}

/**
 * NOTES PÉDAGOGIQUES :
 * 
 * 1. POURQUOI @Entity ?
 *    - Indique à Room que cette classe doit être créée comme table SQL
 *    - Room génère automatiquement : CREATE TABLE transactions (...)
 * 
 * 2. POURQUOI autoGenerate = true ?
 *    - SQLite génère automatiquement l'ID (1, 2, 3...)
 *    - Pas besoin de gérer manuellement les IDs
 * 
 * 3. POURQUOI des getters/setters ?
 *    - Room utilise la réflexion pour lire/écrire les données
 *    - Sans eux, Room ne peut pas accéder aux champs privés
 * 
 * 4. TYPES SUPPORTÉS PAR ROOM :
 *    - Primitifs : int, long, double, boolean
 *    - String
 *    - Date (avec TypeConverter)
 *    - Enum (stocké comme String)
 * 
 * 5. CYCLE DE VIE D'UNE TRANSACTION :
 *    a) Création : new Transaction("Salaire", 2000, INCOME, new Date())
 *    b) Insertion : dao.insert(transaction) → ID généré automatiquement
 *    c) Récupération : dao.getAllTransactions() → Liste<Transaction>
 *    d) Modification : transaction.setAmount(2500); dao.update(transaction)
 *    e) Suppression : dao.delete(transaction)
 */
