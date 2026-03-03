package model;

import java.time.LocalDate;

/**
 * Abonnement standard sans accès sauna ni crédits coach.
 */
public class AbonnementBasic extends Abonnement {
    public AbonnementBasic(String reference, LocalDate dateDebut, int dureeMois, double prixMensuel) {
        super(reference, dateDebut, dureeMois, prixMensuel);
    }

    @Override
    public boolean permetAccesSauna() {
        return false;
    }

    @Override
    public int creditsCoachInclus() {
        return 0;
    }
}
