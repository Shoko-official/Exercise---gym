package model;

import java.time.LocalDateTime;

/**
 * Represents a session at the gym.
 */
public class Session {
    private int id;
    private String name;
    private LocalDateTime dateTime;
    private int maxCapacity;

    public Session(int id, String name, LocalDateTime dateTime, int maxCapacity) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.maxCapacity = maxCapacity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}
