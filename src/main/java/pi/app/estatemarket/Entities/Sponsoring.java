package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "sponsoring")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sponsoring implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdSponsoring;
    private String SponsoringType;
    private float PriceS;
    private String DescriptionS;
    private String imageS;

    @ManyToMany(mappedBy = "sponsoringAnnouncement")
    private Set<Announcement>announcement;
}
