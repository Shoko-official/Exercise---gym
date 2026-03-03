package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a reservation for a session, including optional services.
 */
public class Reservation {
    private Session session;
    private List<Service> services;
    private ReservationStatus status;

    public enum ReservationStatus {
        CONFIRMED,
        CANCELLED
    }

    public Reservation(Session session) {
        this.session = session;
        this.services = new ArrayList<>();
        this.status = ReservationStatus.CONFIRMED;
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public double totalServicesCost() {
        return services.stream()
                .mapToDouble(Service::getPrice)
                .sum();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

    public Session getSession() {
        return session;
    }

    public List<Service> getServices() {
        return services;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "session=" + session.getName() +
                ", servicesCount=" + services.size() +
                ", totalServicesCost=" + totalServicesCost() +
                ", status=" + status +
                '}';
    }
}