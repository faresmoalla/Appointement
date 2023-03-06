package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "publication")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdPublication;
    @Temporal(TemporalType.DATE)
    private Date DatePublication;
    private String DescriptionPublication;

    @ManyToOne
    private User userPub;

    @OneToMany(mappedBy = "commPub")
    private Set<Comment>commentsPub;

}
