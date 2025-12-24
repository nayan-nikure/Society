package Society.Management.System.Service;

import Society.Management.System.Entity.DTO.FlatDto.FlatRequest;
import Society.Management.System.Entity.DTO.FlatDto.FlatResponse;
import Society.Management.System.Entity.Flat;
import Society.Management.System.Entity.Resident;
import Society.Management.System.Entity.Society;
import Society.Management.System.Respository.FlatRespository;
import Society.Management.System.Respository.MaintenanceRespository;
import Society.Management.System.Respository.ResidentRespository;
import Society.Management.System.Respository.SocietyRespository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlatService {

    @Autowired
    private FlatRespository flatRespository;
    @Autowired
    private SocietyRespository societyRespository;
    @Autowired
    private MaintenanceRespository maintenanceRespository;
    @Autowired
    private ResidentRespository residentRespository;


    public FlatResponse create(FlatRequest request) {


        // 1. Find Society
        Society society = societyRespository.findById(request.getId())
                .orElseThrow(() ->
                        new RuntimeException("Society not found with id: " + request.getId()));

        // 2. Create Flat
        Flat flat = new Flat();
        flat.setFlatNo(request.getFlatNo());
        flat.setSociety(society);

        // 3. Save Flat
        Flat saved = flatRespository.save(flat);

        // 4. Convert to Response
        FlatResponse response = new FlatResponse();
        response.setId(saved.getId());
        response.setFlatNo(saved.getFlatNo());

        return response;
    }

    public List<FlatResponse> getall() {
        List<FlatResponse> responses = new ArrayList<>();

        for (Flat flat : flatRespository.findAll()) {
            responses.add(convertToResponse(flat));
        }
        return responses;
    }

    private FlatResponse convertToResponse(Flat flat) {
        FlatResponse response = new FlatResponse();
        response.setId(flat.getId());
        response.setFlatNo(flat.getFlatNo());


        response.setSocietyId(flat.getSociety().getId());
        response.setSocietyName(flat.getSociety().getName());

        return response;
    }

    public FlatResponse getById(Long id) {
        Flat flat = flatRespository.findById(id).orElseThrow(() -> new RuntimeException("Flat not found with id: " + id));
        return convertToResponse(flat);
    }


    public FlatResponse update(Long id, FlatRequest request) {
        Flat flat = flatRespository.findById(id).orElseThrow(() -> new RuntimeException("Flat not found with id: " + id));

        Society society = flatRespository.findById(request.getId()).orElseThrow(() -> new RuntimeException("flat not found with id: " + request.getId())).getSociety();
        flat.setFlatNo(request.getFlatNo());
        flat.setSociety(society);

        Flat updated = flatRespository.save(flat);
        return convertToResponse(updated);
    }

    public void delete(Long id) {
        Flat flat = flatRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flat not found"));
        flatRespository.delete(flat);
    }
    }
