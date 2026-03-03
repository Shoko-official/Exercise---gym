import dao.AdherantDAO;
import dao.SeanceDAO;
import exceptions.AdherentIntrouvableException;
import model.*;
import util.DatabaseInit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DatabaseInit.initialize();

        SalleDeSport maSalle = new SalleDeSport();
        AdherantDAO dao = new AdherantDAO();
        SeanceDAO seanceDao = new SeanceDAO();

		// ------------------- TP 1 -------------------

        Prestation sauna = new Prestation("SAUNA", "Accès Sauna", 5.0);
        Prestation coach = new Prestation("COACH", "Coaching individuel", 25.0);
        Prestation serviette = new Prestation("SERVIETTE", "Location Serviette", 2.0);

        Seance s1 = new Seance(1, "BodyPump", LocalDateTime.now().plusDays(2), 20);
        Seance s2 = new Seance(2, "Yoga", LocalDateTime.now().minusDays(1), 15);
        Seance s3 = new Seance(3, "CrossFit", LocalDateTime.now().plusWeeks(1), 10);

        maSalle.ajouterSeance(s1);
        maSalle.ajouterSeance(s2);
        maSalle.ajouterSeance(s3);

        Abonnement abBasic = new AbonnementBasic("BASIC-001", LocalDate.now(), 12, 30.0);
        Abonnement abPremium = new AbonnementPremium("PREM-001", LocalDate.now(), 6, 60.0, 5);

        Adherent alice = new Adherent(1, "Alice", abBasic);
        Adherent bob = new Adherent(2, "Bob", abPremium);

        maSalle.ajouterAdherent(alice);
        maSalle.ajouterAdherent(bob);

        Reservation r1 = alice.reserver(s1);
        r1.ajouterPrestation(serviette);

        Reservation r2 = alice.reserver(s2);
        r2.ajouterPrestation(sauna);

        Reservation r3 = bob.reserver(s3);
        r3.ajouterPrestation(coach);
        r3.ajouterPrestation(sauna);

        Reservation r4 = bob.reserver(s1);
        r4.ajouterPrestation(serviette);
        r4.annuler();

        System.out.println("Liste des adhérents et leurs abonnements");
        for (Adherent a : maSalle.getAdherents()) {
            System.out.println(a.getNom() + " : " + a.getAbonnement());
        }

        System.out.println("\nRéservations futures d'Alice");
        List<Reservation> futuresAlice = alice.reservationsFutures();
        for (Reservation r : futuresAlice) {
            System.out.println("- " + r);
        }

        System.out.println("\nAdhérents ayant accès au sauna (via abonnement)");
        List<Adherent> saunaAccess = maSalle.adherentsAvecSauna();
        for (Adherent a : saunaAccess) {
            System.out.println("- " + a.getNom());
        }

        System.out.println("\nChiffre d'affaires des prestations");
        System.out.printf("Total: %.2f €%n", maSalle.chiffreAffairesPrestations());

        System.out.println("\nTest de recherche d'adhérent (Exception)");
        try {
            maSalle.trouverAdherent(1);
        } catch (AdherentIntrouvableException e) {
            System.out.println("Exception capturée: " + e.getMessage());
        }


		// ------------------- TP 2 -------------------

        System.out.println("\nTP 2 : CRUD via DAO");
        
        dao.inserer(alice);
        dao.inserer(bob);

		System.out.println("Liste initiale :");
		System.out.println(dao.lister());

		alice.setNom("Robert");
        dao.modifier(alice);
        
		dao.supprimer(bob);

		System.out.println("Liste après modification et suppression :");
		System.out.println(dao.lister());


        // ------------------- BONUS 1/2 -------------------

        System.out.println("\nTests bonus 1 et 2");
        
        Adherent charlie = new Adherent(3, "Pierre", new AbonnementPremium("PREM-003", LocalDate.now(), 12, 55.0, 10));
        dao.inserer(charlie);
        
        System.out.println("Liste après insertion de Charlie (Premium) :");
        List<Adherent> fromDB = dao.lister();
        for (Adherent a : fromDB) {
            System.out.println(a);
        }

        System.out.println("\nRecherche d'un adhérent contenant 'Pier' :");
        List<Adherent> results = dao.rechercherParNom("Pier");
        for (Adherent a : results) {
            System.out.println("Trouvé : " + a.getNom());
        }

        // ------------------- BONUS 3 -------------------

        System.out.println("\n=== TESTS BONUS 3 : SeanceDAO ===");
        
        Seance zumba = new Seance(10, "Zumba Party", LocalDateTime.now().plusDays(5), 30);
        seanceDao.inserer(zumba);
        
        System.out.println("Liste des séances en base :");
        List<Seance> seancesDb = seanceDao.lister();
        for (Seance s : seancesDb) {
            System.out.println("- " + s);
        }

        Seance aModifier = seanceDao.trouverParId(10);
        if (aModifier != null) {
            System.out.println("Modification de la capacité de Zumba...");
            Seance maj = new Seance(aModifier.getId(), "Zumba Party (MAJ)", aModifier.getDateHeure(), 50);
            seanceDao.modifier(maj);
        }

        System.out.println("Liste des séances après modification :");
        for (Seance s : seanceDao.lister()) {
            System.out.println("- " + s);
        }
    }
}
