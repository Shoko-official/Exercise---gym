package model;

import exceptions.AdherentIntrouvableException;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestionnaire principal de la salle de sport.
 */
public class SalleDeSport {
    private List<Adherent> adherents;
    private List<Seance> seances;

    public SalleDeSport() {
        this.adherents = new ArrayList<>();
        this.seances = new ArrayList<>();
    }

    public void ajouterAdherent(Adherent a) {
        this.adherents.add(a);
    }

    public void ajouterSeance(Seance s) {
        this.seances.add(s);
    }

    public List<Seance> seancesDisponibles() {
        return new ArrayList<>(seances);
    }

    public List<Adherent> adherentsAvecSauna() {
        List<Adherent> avecSauna = new ArrayList<>();
        for (Adherent a : adherents) {
            if (a.getAbonnement().permetAccesSauna()) {
                avecSauna.add(a);
            }
        }
        return avecSauna;
    }

    public double chiffreAffairesPrestations() {
        double total = 0;
        for (Adherent a : adherents) {
            total += a.depensesTotales();
        }
        return total;
    }

    public Adherent trouverAdherent(int id) throws AdherentIntrouvableException {
        for (Adherent a : adherents) {
            if (a.getId() == id) {
                return a;
            }
        }
        throw new AdherentIntrouvableException("L'adhérent avec l'ID " + id + " est introuvable.");
    }

    public List<Adherent> getAdherents() {
        return adherents;
    }

    public List<Seance> getSeances() {
        return seances;
    }
}
