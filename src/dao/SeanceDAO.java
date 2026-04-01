package dao;

import model.Seance;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des séances dans la base de données.
 */
public class SeanceDAO {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public void inserer(Seance seance) {
        String sql = "INSERT INTO seance(id, nom, date_heure, capacite_max) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, seance.getId());
            pst.setString(2, seance.getNom());
            pst.setString(3, seance.getDateHeure().format(FORMATTER));
            pst.setInt(4, seance.getCapaciteMax());
            
            pst.executeUpdate();
            System.out.println("Séance insérée avec succès : " + seance.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de la séance : " + e.getMessage());
        }
    }

    public List<Seance> lister() {
        List<Seance> seances = new ArrayList<>();
        String sql = "SELECT * FROM seance";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                LocalDateTime ldt = LocalDateTime.parse(rs.getString("date_heure"), FORMATTER);
                Seance s = new Seance(rs.getInt("id"), rs.getString("nom"), ldt, rs.getInt("capacite_max"));
                seances.add(s);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des séances : " + e.getMessage());
        }
        return seances;
    }

    public void modifier(Seance seance) {
        String sql = "UPDATE seance SET nom = ?, date_heure = ?, capacite_max = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, seance.getNom());
            pst.setString(2, seance.getDateHeure().format(FORMATTER));
            pst.setInt(3, seance.getCapaciteMax());
            pst.setInt(4, seance.getId());
            
            pst.executeUpdate();
            System.out.println("Séance modifiée avec succès : " + seance.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de la séance : " + e.getMessage());
        }
    }

    public void supprimer(Seance seance) {
        String sql = "DELETE FROM seance WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, seance.getId());
            pst.executeUpdate();
            System.out.println("Séance supprimée avec succès : " + seance.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de la séance : " + e.getMessage());
        }
    }

    public Seance trouverParId(int id) {
        String sql = "SELECT * FROM seance WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    LocalDateTime ldt = LocalDateTime.parse(rs.getString("date_heure"), FORMATTER);
                    return new Seance(rs.getInt("id"), rs.getString("nom"), ldt, rs.getInt("capacite_max"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la séance : " + e.getMessage());
        }
        return null;
    }
}
