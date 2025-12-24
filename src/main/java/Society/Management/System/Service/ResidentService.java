package Society.Management.System.Service;

import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceResponst;
import Society.Management.System.Entity.DTO.ResidentDto.ResidentRequest;
import Society.Management.System.Entity.DTO.ResidentDto.Residentresponses;
import Society.Management.System.Entity.Flat;
import Society.Management.System.Entity.Maintenance;
import Society.Management.System.Entity.Resident;
import Society.Management.System.Respository.FlatRespository;
import Society.Management.System.Respository.MaintenanceRespository;
import Society.Management.System.Respository.ResidentRespository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResidentService {

    @Autowired
    private ResidentRespository residentRespository;
    @Autowired
    private FlatRespository flatRespository;
    @Autowired
    private MaintenanceRespository maintenanceRespository;

    public Residentresponses create(ResidentRequest request) {
        if (request.getId() == null) {
            throw new RuntimeException("flatId must not be null");
        }

        Flat flat = flatRespository.findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Flat not found with id: " + request.getId()));

        // 2. Create Resident
        Resident resident = new Resident();
        resident.setName(request.getName());
        resident.setMobile(request.getMobile());
        resident.setEmail(request.getEmail());
        resident.setFlat(flat);
        // 3. Save
        Resident saved = residentRespository.save(resident);
        // 4. Convert to Response
        Residentresponses response = new Residentresponses();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setMobile(saved.getMobile());
        response.setEmail(saved.getEmail());
        response.setFlatId(flat.getId());
        // response.setFlatNo(saved.getId());
        response.setFlatNo(resident.getFlat().getFlatNo());
        response.setSocietyName(flat.getSociety().getName());

        return response;
    }

    public List<Residentresponses> getAll() {
        return residentRespository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private Residentresponses convertToResponse(Resident resident) {

        Residentresponses response = new Residentresponses();
        response.setId(resident.getId());
        response.setName(resident.getName());
        response.setMobile(resident.getMobile());
        response.setEmail(resident.getEmail());

        if (resident.getFlat() != null) {
            response.setFlatId(resident.getFlat().getId());
            response.setFlatNo(resident.getFlat().getFlatNo());
            response.setSocietyName(resident.getFlat().getSociety().getName());
        }
        return response;
    }

    public Residentresponses getById(Long id) {
        Resident resident = residentRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found with id: " + id));

        return convertToResponse(resident);
    }

    public Residentresponses update(Long id, ResidentRequest request) {
        Resident resident = residentRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resident not found"));

        resident.setName(request.getName());
        resident.setMobile(request.getMobile());
        resident.setEmail(request.getEmail());

        if (request.getId() != null) {
            Flat flat = flatRespository.findById(request.getId())
                    .orElseThrow(() -> new RuntimeException("Flat not found"));
            resident.setFlat(flat);
        }
        return convertToResponse(residentRespository.save(resident));
    }


    public void delete(Long id) {
        if (!residentRespository.existsById(id)) {
            throw new RuntimeException("Resident not found");
        }
        maintenanceRespository.deleteByResidentId(id);
        residentRespository.deleteById(id);
    }

}

