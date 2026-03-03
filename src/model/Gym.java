package model;

import exceptions.MemberNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the gym (SalleDeSport).
 */
public class Gym {
    private List<Member> members;
    private List<Session> sessions;

    public Gym() {
        this.members = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    public void addMember(Member member) {
        this.members.add(member);
    }

    public void addSession(Session session) {
        this.sessions.add(session);
    }

    /**
     * Lists all available sessions (currently lists all).
     */
    public List<Session> getAvailableSessions() {
        return new ArrayList<>(sessions);
    }

    /**
     * Returns members who have sauna access through their membership.
     */
    public List<Member> getMembersWithSaunaAccess() {
        return members.stream()
                .filter(m -> m.getMembership().allowsSaunaAccess())
                .collect(Collectors.toList());
    }

    /**
     * Calculates the total revenue from confirmed services.
     */
    public double getServiceRevenue() {
        return members.stream()
                .mapToDouble(Member::getTotalExpenses)
                .sum();
    }

    /**
     * Finds a member by their ID.
     * 
     * @param id The ID to search.
     * @return The found member.
     * @throws MemberNotFoundException if the ID doesn't exist.
     */
    public Member findMember(int id) throws MemberNotFoundException {
        return members.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new MemberNotFoundException(id));
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
