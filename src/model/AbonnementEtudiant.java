package model;

import java.time.LocalDate;

/**
 * Abonnement spécial avec réduction pour les étudiants.
 */
public class AbonnementEtudiant extends Abonnement {
    private double reductionPourcent;

    public AbonnementEtudiant(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, double reductionPourcent) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.reductionPourcent = reductionPourcent;
    }

    @Override
    public double coutTotal() {
        double original = super.coutTotal();
        return original * (1 - reductionPourcent / 100.0);
    }

    @Override
    public boolean permetAccesSauna() {
        return false;
    }

    @Override
    public int creditsCoachInclus() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Étudiant: -%.0f%%]", reductionPourcent);
    }
}
