package ao.peteca.backend.event;

import org.springframework.stereotype.Service;

@Service
public class EventService {
    public String saveEvent() {
        return "";
    }
    public EventDTO findEventbyId(Long eventId) {
        return new EventDTO();
    }
    public EventDTO unactivedEvent(Long eventId) {
        return new EventDTO();
    }
}
