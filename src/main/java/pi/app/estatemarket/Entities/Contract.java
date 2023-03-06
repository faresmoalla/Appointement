package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "contract")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IdContract;
    @Temporal(TemporalType.DATE)
    private Date StartDateContract;
    @Temporal(TemporalType.DATE)
    private Date EndDateContract;
    private String TypeContract;

    @ManyToOne
    private User userContract;

}
