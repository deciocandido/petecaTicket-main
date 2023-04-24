package ao.peteca.backend.venue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VenueDTO {
    @Id
    @GeneratedValue
    Long venueId;
    String venueName;
    String venueDescription;
    String venueLocation;
    String venueAddress;
    String venueCity;
    String venueCountry;
    String venuePostCode;
    String venueCapability;
    String venueCategory;
    String venueStatus;
}
