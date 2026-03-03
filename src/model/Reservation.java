package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente la réservation d'une séance par un adhérent.
 */
public class Reservation {
    private Seance seance;
    private List<Prestation> prestations;
    private StatutReservation statut;

    public Reservation(Seance seance) {
        this.seance = seance;
        this.prestations = new ArrayList<>();
        this.statut = StatutReservation.CONFIRMEE;
    }

    public void ajouterPrestation(Prestation p) {
        if (this.statut == StatutReservation.CONFIRMEE) {
            this.prestations.add(p);
        }
    }

    public double coutPrestations() {
        double total = 0;
        for (Prestation p : prestations) {
            total += p.getPrix();
        }
        return total;
    }

    public void annuler() {
        this.statut = StatutReservation.ANNULEE;
    }

    public Seance getSeance() {
        return seance;
    }

    public List<Prestation> getPrestations() {
        return prestations;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    @Override
    public String toString() {
        return String.format("Réservation pour %s | Statut: %s | Coût Prestations: %.2f €", 
                seance.getNom(), statut, coutPrestations());
    }
}