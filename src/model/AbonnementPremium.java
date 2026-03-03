package model;

import java.time.LocalDate;

/**
 * Abonnement VIP incluant l'accès au sauna et des crédits coach.
 */
public class AbonnementPremium extends Abonnement {
    private int creditsCoach;

    public AbonnementPremium(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel, int creditsCoach) {
        super(reference, dateDebut, dureeMois, prixMensuel);
        this.creditsCoach = creditsCoach;
    }

    @Override
    public boolean permetAccesSauna() {
        return true;
    }

    @Override
    public int creditsCoachInclus() {
        return creditsCoach;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" [Sauna: OK, Credits Coach: %d]", creditsCoach);
    }
}
