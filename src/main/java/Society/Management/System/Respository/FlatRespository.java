package Society.Management.System.Respository;

import Society.Management.System.Entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRespository extends JpaRepository<Flat,Long> {

}
