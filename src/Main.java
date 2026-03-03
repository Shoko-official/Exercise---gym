import exceptions.MemberNotFoundException;
import model.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gym gym = new Gym();

        // Initialise the prestations
        Service saunaSvc = new Service("SAUNA", "Accès Sauna", 5.0);
        Service coachSvc = new Service("COACH", "Coaching individuel", 25.0);
        Service towelSvc = new Service("SERVIETTE", "Location Serviette", 2.0);

        // Initialise the sessions
        Session s1 = new Session(1, "BodyPump", LocalDateTime.now().plusDays(2), 20);
        Session s2 = new Session(2, "Yoga", LocalDateTime.now().minusDays(1), 15); // Past
        Session s3 = new Session(3, "CrossFit", LocalDateTime.now().plusWeeks(1), 10);

        gym.addSession(s1);
        gym.addSession(s2);
        gym.addSession(s3);

        // Initialise the members
        Membership basic = new BasicMembership("BASIC-001", LocalDate.now(), 12, 30.0);
        Membership premium = new PremiumMembership("PREM-001", LocalDate.now(), 6, 60.0, 2);

        Member member1 = new Member(1, "Alice", basic);
        Member member2 = new Member(2, "Bob", premium);

        gym.addMember(member1);
        gym.addMember(member2);

        // Initialise the reservations
        Reservation r1 = member1.bookSession(s1);
        r1.addService(towelSvc);

        Reservation r2 = member1.bookSession(s2);
        r2.addService(saunaSvc);

        Reservation r3 = member2.bookSession(s3);
        r3.addService(coachSvc);
        r3.addService(saunaSvc);

        Reservation r4 = member2.bookSession(s1);
        r4.addService(towelSvc);
        r4.cancel();

        // Display the members and their memberships

        System.out.println("Members and their memberships :");
        for (Member m : gym.getMembers()) {
            System.out.println(m.getName() + " = " + m.getMembership());
        }

        System.out.println("\nFuture reservations of Alice :");
        List<Reservation> futureAlice = member1.getFutureReservations();
        for (Reservation r : futureAlice) {
            System.out.println(r);
        }

        System.out.println("\nMembers having access to the Sauna :");
        List<Member> saunaMembers = gym.getMembersWithSaunaAccess();
        for (Member m : saunaMembers) {
            System.out.println("- " + m.getName() + " (" + m.getMembership().getReference() + ")");
        }

        System.out.println("\nRevenue from services :");
        System.out.println("Total Revenue: " + gym.getServiceRevenue() + " €");

        // Exception test
        System.out.println("\nMember search (Exception) :");
        try {
            gym.findMember(999);
        } catch (MemberNotFoundException e) {
            System.out.println("Success: " + e.getMessage());
        }
    }
}
