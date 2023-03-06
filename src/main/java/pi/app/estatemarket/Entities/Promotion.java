package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "promotion")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Promotion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int PromotionId;
    @Temporal(TemporalType.DATE)
    private Date PromotionDate;
    private String PromotionName;
    private float Pourcentage;
    private String Duration;

    @ManyToOne
    private Promotion promotion;
}
