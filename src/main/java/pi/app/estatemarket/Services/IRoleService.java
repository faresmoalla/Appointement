package pi.app.estatemarket.Services;

import pi.app.estatemarket.Entities.Role;
import pi.app.estatemarket.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    List<RoleDTO> getAllRoles();

    Role createRole(Role role);

    Role updateRole(Role role);

    void deleteRole(long id);
    RoleDTO getRoleById(long id);
}
