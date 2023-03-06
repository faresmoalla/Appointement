package pi.app.estatemarket.Controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pi.app.estatemarket.Entities.Role;
import pi.app.estatemarket.Services.IRoleService;
import pi.app.estatemarket.dto.RoleDTO;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
@Transactional
public class RoleController {
    @Autowired
    private final IRoleService roleService;

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return roleService.getAllRoles();
    }
    @PostMapping("add")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRole(@RequestBody Role role){ roleService.createRole(role);}
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("update")
    public void updateRole( @RequestBody Role role){roleService.updateRole(role);}

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("delete/{id}")
    public void deleteRole(@PathVariable long id) {
        roleService.deleteRole(id);
    }
    @GetMapping("getbyid/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }
}