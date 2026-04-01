package util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

/**
 * Initialise le schéma de la base de données.
 */
public class DatabaseInit {
    public static void initialize() {
        String sqlAdherent = "CREATE TABLE IF NOT EXISTS adherent (" +
                             "id INTEGER PRIMARY KEY," +
                             "nom TEXT NOT NULL," +
                             "abonnement_type TEXT" +
                             ");";
                             
        String sqlSeance = "CREATE TABLE IF NOT EXISTS seance (" +
                           "id INTEGER PRIMARY KEY," +
                           "nom TEXT NOT NULL," +
                           "date_heure TEXT NOT NULL," +
                           "capacite_max INTEGER NOT NULL" +
                           ");";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlAdherent);
            stmt.execute(sqlSeance);
            
            System.out.println("Base de données initialisée avec succès.");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'initialisation de la base : " + e.getMessage());
        }
    }
}
