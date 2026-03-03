package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a gym member.
 */
public class Member {
    private int id;
    private String name;
    private Membership membership;
    private List<Reservation> reservations;

    public Member(int id, String name, Membership membership) {
        this.id = id;
        this.name = name;
        this.membership = membership;
        this.reservations = new ArrayList<>();
    }

    /**
     * Books a session for the member.
     * 
     * @param session The session to book.
     */
    public void bookSession(Session session) {
        Reservation reservation = new Reservation(session);
        this.reservations.add(reservation);
    }

    /**
     * Calculates the total expenses for confirmed services in all reservations.
     * 
     * @return Total services cost.
     */
    public double getTotalExpenses() {
        return reservations.stream()
                .filter(r -> r.getStatus() == Reservation.ReservationStatus.CONFIRMED)
                .mapToDouble(Reservation::totalServicesCost)
                .sum();
    }

    /**
     * Returns a list of reservations for sessions scheduled in the future.
     * 
     * @return List of future reservations.
     */
    public List<Reservation> getFutureReservations() {
        LocalDateTime now = LocalDateTime.now();
        return reservations.stream()
                .filter(r -> r.getSession().getDateTime().isAfter(now))
                .collect(Collectors.toList());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Membership getMembership() {
        return membership;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", membership=" + membership.getReference() +
                ", reservationsCount=" + reservations.size() +
                '}';
    }
}
