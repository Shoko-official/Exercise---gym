package model;

/**
 * Représente un service additionnel pour une réservation.
 */
public class Prestation {
    private String code;
    private String libelle;
    private double prix;

    public Prestation(String code, String libelle, double prix) {
        this.code = code;
        this.libelle = libelle;
        this.prix = prix;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrix() {
        return prix;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) : %.2f €", libelle, code, prix);
    }
}
