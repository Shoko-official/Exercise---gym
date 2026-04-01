package dao;

import model.*;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO pour la gestion des adhérents dans la base de données.
 */
public class AdherentDAO {

    /**
     * Insère un nouvel adhérent dans la base de données.
     * @param adherent L'adhérent à insérer.
     */
    public void inserer(Adherent adherent) {
        String sql = "INSERT INTO adherent(id, nom, abonnement_type) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, adherent.getId());
            pst.setString(2, adherent.getNom());
            
            String type = null;
            if (adherent.getAbonnement() != null) {
                type = (adherent.getAbonnement() instanceof AbonnementPremium) ? "PREMIUM" : "BASIC";
            }
            pst.setString(3, type);
            
            pst.executeUpdate();
            System.out.println("Adhérent inséré avec succès : " + adherent.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'insertion de l'adhérent : " + e.getMessage());
        }
    }

	public List<Adherent> lister(){
		List<Adherent> adherents = new ArrayList<>();
		String sql = "SELECT * FROM adherent";
		try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                Adherent adherent = new Adherent(rs.getInt("id"), rs.getString("nom"), rs.getString("abonnement_type"));
                adherents.add(adherent);
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des adhérents : " + e.getMessage());
        }
		return adherents;
	}

	public void modifier(Adherent adherent){
		String sql = "UPDATE adherent SET nom = ?, abonnement_type = ? WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, adherent.getNom());
            
            String type = null;
            if (adherent.getAbonnement() != null) {
                type = (adherent.getAbonnement() instanceof AbonnementPremium) ? "PREMIUM" : "BASIC";
            }
            pst.setString(2, type);
            pst.setInt(3, adherent.getId());
            
            pst.executeUpdate();
            System.out.println("Adhérent modifié avec succès : " + adherent.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la modification de l'adhérent : " + e.getMessage());
        }
	}

	public void supprimer(Adherent adherent){
		String sql = "DELETE FROM adherent WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, adherent.getId());
            
            pst.executeUpdate();
            System.out.println("Adhérent supprimé avec succès : " + adherent.getNom());
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'adhérent : " + e.getMessage());
        }
	}

	public Adherent trouverParId(int id){
		String sql = "SELECT * FROM adherent WHERE id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setInt(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Adherent(rs.getInt("id"), rs.getString("nom"), rs.getString("abonnement_type"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'adhérent : " + e.getMessage());
        }
		return null;
	}

    public List<Adherent> rechercherParNom(String nom) {
        List<Adherent> adherents = new ArrayList<>();
        String sql = "SELECT * FROM adherent WHERE nom LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, "%" + nom + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    adherents.add(new Adherent(rs.getInt("id"), rs.getString("nom"), rs.getString("abonnement_type")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche par nom : " + e.getMessage());
        }
        return adherents;
    }
}