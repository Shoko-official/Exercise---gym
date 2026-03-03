package model;

import java.time.LocalDate;

/**
 * Basic membership with no sauna and no coach credits.
 */
public class BasicMembership extends Membership {

    public BasicMembership(String reference, LocalDate startDate, int durationMonths, double monthlyPrice) {
        super(reference, startDate, durationMonths, monthlyPrice);
    }

    @Override
    public boolean allowsSaunaAccess() {
        return false;
    }

    @Override
    public int includedCoachCredits() {
        return 0;
    }

    @Override
    public String toString() {
        return "BasicMembership{" +
                "reference='" + getReference() + '\'' +
                ", allowsSauna=false" +
                ", coachCredits=0" +
                '}';
    }
}
