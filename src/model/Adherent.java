package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un membre de la salle de sport.
 */
public class Adherent {
    private int id;
    private String nom;
    private Abonnement abonnement;
    private List<Reservation> reservations;

    public Adherent(int id, String nom) {
        this(id, nom, (Abonnement) null);
    }

    public Adherent(int id, String nom, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.abonnement = abonnement;
        this.reservations = new ArrayList<>();
    }

    public Adherent(int id, String nom, String typeAbonnement) {
        this(id, nom);
        if (typeAbonnement != null) {
            switch (typeAbonnement.toUpperCase()) {
                case "BASIC":
                    this.abonnement = new AbonnementBasic("REF-BASIC-" + id, java.time.LocalDate.now(), 12, 30.0);
                    break;
                case "PREMIUM":
                    this.abonnement = new AbonnementPremium("REF-PREM-" + id, java.time.LocalDate.now(), 6, 60.0, 5);
                    break;
            }
        }
    }

    public Reservation reserver(Seance s) {
        Reservation r = new Reservation(s);
        this.reservations.add(r);
        return r;
    }

    public double depensesTotales() {
        double total = 0;
        for (Reservation r : reservations) {
            if (r.getStatut() == StatutReservation.CONFIRMEE) {
                total += r.coutPrestations();
            }
        }
        return total;
    }

    public List<Reservation> reservationsFutures() {
        List<Reservation> futures = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (Reservation r : reservations) {
            if (r.getSeance().getDateHeure().isAfter(now)) {
                futures.add(r);
            }
        }
        return futures;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Abonnement getAbonnement() {
        return abonnement;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        String infoAbo = (abonnement != null) ? abonnement.toString() : "Aucun abonnement";
        return String.format("Adhérent #%d: %s | Abonnement: %s", id, nom, infoAbo);
    }
}
