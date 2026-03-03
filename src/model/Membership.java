package model;

import java.time.LocalDate;

/**
 * Abstract class representing a gym membership.
 */
public abstract class Membership {
    private String reference;
    private LocalDate startDate;
    private int durationMonths;
    private double monthlyPrice;

    public Membership(String reference, LocalDate startDate, int durationMonths, double monthlyPrice) {
        this.reference = reference;
        this.startDate = startDate;
        this.durationMonths = durationMonths;
        this.monthlyPrice = monthlyPrice;
    }

    public LocalDate getEndDate() {
        return startDate.plusMonths(durationMonths);
    }

    public double getTotalCost() {
        return monthlyPrice * durationMonths;
    }

    public abstract boolean allowsSaunaAccess();

    public abstract int includedCoachCredits();

    public String getReference() {
        return reference;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public double getMonthlyPrice() {
        return monthlyPrice;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "reference='" + reference + '\'' +
                ", endDate=" + getEndDate() +
                ", totalCost=" + getTotalCost() +
                '}';
    }
}
