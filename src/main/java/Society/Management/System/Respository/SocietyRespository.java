package Society.Management.System.Respository;

import Society.Management.System.Entity.Society;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocietyRespository extends JpaRepository< Society,Long> {

}
