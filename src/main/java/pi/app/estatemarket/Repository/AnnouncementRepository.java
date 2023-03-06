package pi.app.estatemarket.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.app.estatemarket.Entities.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {

}
