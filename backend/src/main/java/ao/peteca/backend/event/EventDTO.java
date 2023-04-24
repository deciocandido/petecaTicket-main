package ao.peteca.backend.event;

import java.util.Date;

import ao.peteca.backend.venue.VenueDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class EventDTO {
    @Id
    @GeneratedValue
    Long eventId;
    String eventName;
    String eventDescription;
    String eventType;
    String eventStatus;
    //VenueDTO eventLocation;
    String eventLocation;
    Integer eventTicketLimited;
    Date eventStartDate;
    Date eventEndDate;
    Date eventStartTime;
    Date eventEndTime;
}
