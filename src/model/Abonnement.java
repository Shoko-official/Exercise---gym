package model;

import java.time.LocalDate;

/**
 * Classe de base pour les abonnements.
 */
public abstract class Abonnement {
    private String reference;
    private LocalDate dateDebut;
    private int dureeMois;
    private double prixMensuel;

    public Abonnement(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        this.reference = reference;
        this.dateDebut = dateDebut;
        this.dureeMois = dureeMois;
        this.prixMensuel = prixMensuel;
    }

    public LocalDate dateFin() {
        return dateDebut.plusMonths(dureeMois);
    }

    public double coutTotal() {
        return prixMensuel * dureeMois;
    }

    public abstract boolean permetAccesSauna();

    public abstract int creditsCoachInclus();

    public String getReference() {
        return reference;
    }

    @Override
    public String toString() {
        return String.format("%s (Réf: %s, Début: %s, Fin: %s, Coût total: %.2f €)", 
                this.getClass().getSimpleName(), reference, dateDebut, dateFin(), coutTotal());
    }
}
