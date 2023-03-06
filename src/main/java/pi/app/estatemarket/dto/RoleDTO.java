package pi.app.estatemarket.dto;

import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private long roleId;
    private String name;
    private String description;
    private Date dateCreated;
    private Date dateModified;
    private String permissions;
}