package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "agency")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Agency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AgencyId;
    private String AgencyName;
    private String AgencyType;
    private String AgencyAdress;
    private String AgencyDescription;
    private String AgencyEmail;
    private int AgencyPhone;


    @ManyToOne
    private User userAgency;
}