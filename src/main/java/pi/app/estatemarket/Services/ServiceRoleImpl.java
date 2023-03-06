package pi.app.estatemarket.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.app.estatemarket.Entities.Role;
import pi.app.estatemarket.Repository.RoleRepository;
import pi.app.estatemarket.dto.RoleDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ServiceRoleImpl implements IRoleService {
    @Autowired
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles= roleRepository.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {roleRepository.deleteById(id);}

    @Override
    public RoleDTO getRoleById(long id) {
        Role role=roleRepository.findById(id).orElse(null);
        return modelMapper.map(role, RoleDTO.class);
    }
}
