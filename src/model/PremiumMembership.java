package model;

import java.time.LocalDate;

/**
 * Premium membership with sauna access and included coach credits.
 */
public class PremiumMembership extends Membership {
    private int coachCredits;

    public PremiumMembership(String reference, LocalDate startDate, int durationMonths, double monthlyPrice,
            int coachCredits) {
        super(reference, startDate, durationMonths, monthlyPrice);
        this.coachCredits = coachCredits;
    }

    @Override
    public boolean allowsSaunaAccess() {
        return true;
    }

    @Override
    public int includedCoachCredits() {
        return coachCredits;
    }

    @Override
    public String toString() {
        return "PremiumMembership{" +
                "reference='" + getReference() + '\'' +
                ", allowsSauna=true" +
                ", coachCredits=" + coachCredits +
                '}';
    }
}
