package model;

import java.time.LocalDate;

/**
 * Student membership with a discount.
 */
public class StudentMembership extends Membership {
    private double discountPercentage;

    public StudentMembership(String reference, LocalDate startDate, int durationMonths, double monthlyPrice,
            double discountPercentage) {
        super(reference, startDate, durationMonths, monthlyPrice);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getTotalCost() {
        double baseCost = super.getTotalCost();
        return baseCost * (1 - discountPercentage / 100);
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
        return "StudentMembership{" +
                "reference='" + getReference() + '\'' +
                ", discount=" + discountPercentage + "%" +
                ", totalCost=" + getTotalCost() +
                '}';
    }
}
