package tn.isitcom.budgettracker.data.model;

/**
 * Énumération représentant le type d'une transaction.
 * 
 * PEDAGOGIE :
 * - Enum = Liste de constantes nommées
 * - Plus sûr que String (pas d'erreur de frappe)
 * - Complétion automatique dans l'IDE
 * - Room stocke automatiquement comme String en base
 */
public enum TransactionType {
    
    /**
     * INCOME : Revenu (salaire, vente, cadeau reçu...)
     * Augmente le solde
     */
    INCOME,
    
    /**
     * EXPENSE : Dépense (achat, facture, abonnement...)
     * Diminue le solde
     */
    EXPENSE;
    
    /**
     * Retourne le label lisible en français.
     * Utile pour l'affichage dans l'UI.
     * 
     * @return "Revenu" ou "Dépense"
     */
    public String getDisplayName() {
        switch (this) {
            case INCOME:
                return "Revenu";
            case EXPENSE:
                return "Dépense";
            default:
                return "Inconnu";
        }
    }
    
    /**
     * Retourne la couleur associée au type.
     * Convention : vert pour revenu, rouge pour dépense.
     * 
     * @return Code couleur hex
     */
    public String getColor() {
        switch (this) {
            case INCOME:
                return "#4CAF50";  // Vert Material Design
            case EXPENSE:
                return "#F44336";  // Rouge Material Design
            default:
                return "#9E9E9E";  // Gris
        }
    }
    
    /**
     * Retourne l'emoji associé au type.
     * Optionnel : Améliore l'expérience visuelle.
     */
    public String getEmoji() {
        switch (this) {
            case INCOME:
                return "⬆️";  // Flèche haut
            case EXPENSE:
                return "⬇️";  // Flèche bas
            default:
                return "❓";
        }
    }
}

/**
 * NOTES PÉDAGOGIQUES :
 * 
 * 1. POURQUOI UN ENUM ?
 *    - Évite les erreurs : impossible d'écrire "REVENU" au lieu de "INCOME"
 *    - Complétion automatique dans l'IDE
 *    - Switch exhaustif (le compilateur vérifie tous les cas)
 * 
 * 2. STOCKAGE ROOM :
 *    Room stocke automatiquement l'enum comme String :
 *    - INCOME → "INCOME" en base de données
 *    - EXPENSE → "EXPENSE" en base de données
 * 
 * 3. UTILISATION :
 *    ```java
 *    Transaction t = new Transaction("Salaire", 2000, TransactionType.INCOME, new Date());
 *    
 *    if (t.getType() == TransactionType.INCOME) {
 *        // C'est un revenu
 *    }
 *    
 *    // Affichage
 *    textType.setText(t.getType().getDisplayName());  // "Revenu"
 *    ```
 * 
 * 4. ALTERNATIVE (DÉCONSEILLÉE) :
 *    ```java
 *    String type = "revenu";  // Risque d'erreur de frappe
 *    if (type.equals("revenue")) {  // Bug ! Faute de frappe
 *        // Ne sera jamais exécuté
 *    }
 *    ```
 * 
 * 5. EXTENSION FUTURE :
 *    Facile d'ajouter de nouveaux types :
 *    ```java
 *    enum TransactionType {
 *        INCOME,
 *        EXPENSE,
 *        TRANSFER,   // Virement entre comptes
 *        SAVING      // Épargne
 *    }
 *    ```
 */
