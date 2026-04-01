package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gère la connexion à la base de données SQLite.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:salle_sport.db";
	private static final String USER = "root";
	private static final String PASSWORD = "";

    public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
