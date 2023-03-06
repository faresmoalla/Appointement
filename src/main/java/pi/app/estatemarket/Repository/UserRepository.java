package pi.app.estatemarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pi.app.estatemarket.Entities.User;

public interface UserRepository extends JpaRepository<User,Long> {


}