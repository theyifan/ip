import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;
    public Event(String description, String time) throws DukeException {
        super(description);

        String[] timeDetails = time.split("\\s", 3);
        this.startDateTime = parseEventDateTime(timeDetails[0], timeDetails[1]);
        this.endDateTime = parseEventDateTime(timeDetails[0], timeDetails[2]);
    }

    private static LocalDateTime parseEventDateTime(String date, String time) throws DukeException {
        try {
            LocalDate datePart = LocalDate.parse(date);
            LocalTime timePart = LocalTime.parse(time);
            return LocalDateTime.of(datePart, timePart);
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS! Wrong Date/Time format! Type 'help' to see the correct format");
        }
    }

    @Override
    public String toString() {
        String formattedStartDateTime = startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        String formattedEndDateTime = endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        return "[E]" + super.toString()
                + String.format(" (at: %s to %s)", formattedStartDateTime, formattedEndDateTime);
    }
}
