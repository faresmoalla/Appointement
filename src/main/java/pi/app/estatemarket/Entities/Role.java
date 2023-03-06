package pi.app.estatemarket.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "Role")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="role_id")
    private long roleId;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "date_created")
    private Date dateCreated;
    @Column(name = "date_modified")
    private Date dateModified;
    @Column(name = "permissions")
    private String permissions;
    @OneToMany(mappedBy = "role")
    private Set<User> listOfUsers;


}
