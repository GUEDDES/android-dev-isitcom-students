package tn.isitcom.budgettracker.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tn.isitcom.budgettracker.data.model.Transaction;
import tn.isitcom.budgettracker.data.model.TransactionType;

/**
 * Data Access Object (DAO) pour les transactions.
 * 
 * @Dao : Interface définissant les opérations sur la base de données.
 * Room génère automatiquement l'implémentation à la compilation.
 * 
 * PEDAGOGIE :
 * - DAO = Interface entre l'application et la base de données
 * - Chaque méthode = Une opération SQL
 * - Room génère le code SQL automatiquement
 */
@Dao
public interface TransactionDao {
    
    // ========== CREATE (Insertion) ==========
    
    /**
     * Insère une nouvelle transaction.
     * 
     * @Insert : Génère automatiquement INSERT INTO transactions (...)
     * @param transaction Transaction à insérer
     * @return ID de la transaction insérée (long)
     * 
     * IMPORTANT : L'ID est auto-généré et retourné automatiquement
     */
    @Insert
    long insert(Transaction transaction);
    
    /**
     * Insère plusieurs transactions en une seule opération.
     * Plus performant que d'insérer en boucle.
     * 
     * @param transactions Liste de transactions
     * @return Tableau des IDs insérés
     */
    @Insert
    long[] insertAll(Transaction... transactions);
    
    // ========== READ (Lecture) ==========
    
    /**
     * Récupère toutes les transactions, triées par date décroissante.
     * 
     * @Query : Permet d'écrire des requêtes SQL personnalisées
     * @return LiveData : Données observables qui se mettent à jour automatiquement
     * 
     * PEDAGOGIE :
     * - LiveData notifie l'UI automatiquement quand les données changent
     * - ORDER BY date DESC : Plus récent en premier
     * - Room exécute automatiquement sur un thread secondaire
     */
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    LiveData<List<Transaction>> getAllTransactions();
    
    /**
     * Récupère toutes les transactions (version synchrone).
     * ATTENTION : Ne pas appeler sur le thread principal !
     * 
     * @return Liste de transactions
     */
    @Query("SELECT * FROM transactions ORDER BY date DESC")
    List<Transaction> getAllTransactionsSync();
    
    /**
     * Récupère une transaction par son ID.
     * 
     * @param transactionId ID de la transaction
     * @return Transaction ou null si non trouvée
     */
    @Query("SELECT * FROM transactions WHERE id = :transactionId")
    Transaction getTransactionById(int transactionId);
    
    /**
     * Récupère les transactions par type (INCOME ou EXPENSE).
     * 
     * @param type Type de transaction
     * @return Liste de transactions filtrées
     * 
     * EXEMPLE SQL généré :
     * SELECT * FROM transactions WHERE type = 'INCOME' ORDER BY date DESC
     */
    @Query("SELECT * FROM transactions WHERE type = :type ORDER BY date DESC")
    LiveData<List<Transaction>> getTransactionsByType(TransactionType type);
    
    /**
     * Recherche des transactions par description (LIKE).
     * 
     * @param query Terme de recherche
     * @return Transactions dont la description contient le terme
     * 
     * EXEMPLE : searchTransactions("salaire") trouve "Salaire mensuel"
     */
    @Query("SELECT * FROM transactions WHERE description LIKE '%' || :query || '%' ORDER BY date DESC")
    LiveData<List<Transaction>> searchTransactions(String query);
    
    // ========== STATISTIQUES ==========
    
    /**
     * Calcule le total des revenus.
     * 
     * @return Somme de tous les montants de type INCOME
     * 
     * COALESCE : Retourne 0 si la somme est NULL (aucune transaction)
     */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 'INCOME'")
    LiveData<Double> getTotalIncome();
    
    /**
     * Calcule le total des dépenses.
     * 
     * @return Somme de tous les montants de type EXPENSE
     */
    @Query("SELECT COALESCE(SUM(amount), 0) FROM transactions WHERE type = 'EXPENSE'")
    LiveData<Double> getTotalExpense();
    
    /**
     * Calcule le solde (revenus - dépenses).
     * 
     * @return Différence entre revenus et dépenses
     * 
     * EXPLICATION SQL :
     * - Somme des INCOME (positifs)
     * - Moins somme des EXPENSE (négatifs)
     * - COALESCE pour éviter NULL
     */
    @Query("SELECT COALESCE(" +
           "(SELECT SUM(amount) FROM transactions WHERE type = 'INCOME'), 0) - " +
           "COALESCE((SELECT SUM(amount) FROM transactions WHERE type = 'EXPENSE'), 0)")
    LiveData<Double> getBalance();
    
    /**
     * Compte le nombre total de transactions.
     * 
     * @return Nombre de lignes dans la table
     */
    @Query("SELECT COUNT(*) FROM transactions")
    LiveData<Integer> getTransactionCount();
    
    // ========== UPDATE (Modification) ==========
    
    /**
     * Met à jour une transaction existante.
     * 
     * @Update : Génère UPDATE transactions SET ... WHERE id = ?
     * @param transaction Transaction avec modifications
     * @return Nombre de lignes modifiées (1 si succès)
     * 
     * IMPORTANT : L'ID de la transaction doit exister en base
     */
    @Update
    int update(Transaction transaction);
    
    // ========== DELETE (Suppression) ==========
    
    /**
     * Supprime une transaction.
     * 
     * @Delete : Génère DELETE FROM transactions WHERE id = ?
     * @param transaction Transaction à supprimer
     * @return Nombre de lignes supprimées (1 si succès)
     */
    @Delete
    int delete(Transaction transaction);
    
    /**
     * Supprime une transaction par son ID.
     * 
     * @param transactionId ID de la transaction
     * @return Nombre de lignes supprimées
     */
    @Query("DELETE FROM transactions WHERE id = :transactionId")
    int deleteById(int transactionId);
    
    /**
     * Supprime toutes les transactions.
     * ATTENTION : Opération irréversible !
     * 
     * @return Nombre de transactions supprimées
     */
    @Query("DELETE FROM transactions")
    int deleteAll();
    
    /**
     * Supprime les transactions d'un type spécifique.
     * 
     * @param type Type à supprimer (INCOME ou EXPENSE)
     * @return Nombre de transactions supprimées
     */
    @Query("DELETE FROM transactions WHERE type = :type")
    int deleteByType(TransactionType type);
}

/**
 * NOTES PÉDAGOGIQUES :
 * 
 * 1. POURQUOI UNE INTERFACE ?
 *    - Room génère automatiquement l'implémentation à la compilation
 *    - Pas besoin d'écrire le code SQL manuellement
 *    - Vérification des erreurs SQL à la compilation
 * 
 * 2. LIVEDATA VS LIST ?
 *    - LiveData : Observable, met à jour l'UI automatiquement
 *    - List : Synchrone, nécessite rafraîchissement manuel
 *    - Préférer LiveData pour l'UI, List pour les opérations ponctuelles
 * 
 * 3. THREADING :
 *    - LiveData : Room exécute automatiquement sur thread secondaire
 *    - Insert/Update/Delete : DOIVENT être exécutés sur thread secondaire
 *    - Ne JAMAIS appeler insert/update/delete sur le thread principal
 * 
 * 4. TRANSACTIONS SQL :
 *    Room supporte les transactions pour garantir l'intégrité :
 *    ```java
 *    @Transaction
 *    public void transferMoney(int fromId, int toId, double amount) {
 *        // Tout ou rien : si une opération échoue, tout est annulé
 *    }
 *    ```
 * 
 * 5. EXEMPLE D'UTILISATION :
 *    ```java
 *    // Dans un Fragment/Activity
 *    TransactionDao dao = AppDatabase.getInstance(context).transactionDao();
 *    
 *    // Observation des données
 *    dao.getAllTransactions().observe(this, transactions -> {
 *        adapter.setTransactions(transactions);  // UI mise à jour auto
 *    });
 *    
 *    // Insertion (thread secondaire)
 *    new Thread(() -> {
 *        Transaction t = new Transaction("Salaire", 2000, INCOME, new Date());
 *        long id = dao.insert(t);
 *        Log.d("DB", "Transaction insérée avec ID: " + id);
 *    }).start();
 *    ```
 */
