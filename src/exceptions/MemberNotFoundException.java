package exceptions;

/**
 * Exception thrown when a member is not found in the gym's database.
 */
public class MemberNotFoundException extends Exception {
    public MemberNotFoundException(int id) {
        super("Member with ID " + id + " not found.");
    }

    public MemberNotFoundException(String message) {
        super(message);
    }
}
