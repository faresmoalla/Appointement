package pi.app.estatemarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.app.estatemarket.Entities.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
