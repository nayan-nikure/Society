package Society.Management.System.Respository;

import Society.Management.System.Entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRespository extends JpaRepository<Maintenance,Long> {

    void deleteByResidentId(Long id);
}
