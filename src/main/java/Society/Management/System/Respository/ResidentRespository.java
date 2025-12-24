package Society.Management.System.Respository;

import Society.Management.System.Entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRespository extends JpaRepository<Resident,Long> {

}
