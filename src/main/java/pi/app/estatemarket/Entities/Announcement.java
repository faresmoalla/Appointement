package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "announcement")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Announcement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdAnnouncement;
    @Enumerated(EnumType.STRING)
    private GoodType goodType;
    @Enumerated(EnumType.STRING)
    private LeaseType leaseType;
    private String title;
    private String AreaAnn;
    private float PriceAnn;
    private String DescriptionAnn;
    private String AddressAnn;
    private String ImageAnn;
    @Temporal(TemporalType.DATE)
    private Date DisponibiliteAnn;

    @ManyToOne
    private User userAnnouncement;

    @OneToMany(mappedBy = "announcementApp")
    private Set<Appointment> appointments;

    @ManyToMany
    private Set<Sponsoring> sponsoringAnnouncement;

    @OneToMany(mappedBy ="promotion")
    private Set<Promotion> promotions;



}