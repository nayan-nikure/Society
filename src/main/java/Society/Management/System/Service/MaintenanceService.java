package Society.Management.System.Service;

import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceRequest;
import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceResponst;
import Society.Management.System.Entity.Maintenance;
import Society.Management.System.Entity.Resident;
import Society.Management.System.Respository.MaintenanceRespository;
import Society.Management.System.Respository.ResidentRespository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRespository maintenanceRespository;

    @Autowired
    private ResidentRespository residentRespository;


    public @Nullable MaintenaceResponst create(MaintenaceRequest request) {
        if (request.getResidentId() == null) {
            throw new RuntimeException("residentId must not be null");
        }

        Resident resident = residentRespository.findById(request.getResidentId())
                .orElseThrow(() ->
                        new RuntimeException("Resident not found"));

        Maintenance maintenance = new Maintenance();
        maintenance.setAmount(request.getAmount());
        maintenance.setMonth(request.getMonth());
        maintenance.setResident(resident);

        Maintenance saved = maintenanceRespository.save(maintenance);

        MaintenaceResponst response = new MaintenaceResponst();
        response.setId(saved.getId());
        response.setAmount(saved.getAmount());
        response.setMonth(saved.getMonth());
        response.setResidentId(resident.getId());
        response.setResidentName(resident.getName());

        return response;
    }

    private MaintenaceResponst convertToResponse(Maintenance m) {
        MaintenaceResponst response = new MaintenaceResponst();

        response.setId(m.getId());
        response.setAmount(m.getAmount());
        response.setMonth(m.getMonth());

        if (m.getResident() != null) {
            response.setResidentId(m.getResident().getId());
            response.setResidentName(m.getResident().getName());
        }
        return response;
    }


    public @Nullable List<MaintenaceResponst> getAll() {
        List<Maintenance> maintenances =maintenanceRespository.findAll();

        if (maintenances.isEmpty()) {
            return new ArrayList<>();   // return empty list, not null
        }

        List<MaintenaceResponst> responses = new ArrayList<>();

        for (Maintenance m : maintenances) {
            responses.add(convertToResponse(m));
        }

        return responses;
    }


    public MaintenaceResponst getById(Long id) {
        Maintenance maintenance = maintenanceRespository.findById(id).orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));
        return convertToResponse(maintenance);
    }

    public MaintenaceResponst update(Long id, MaintenaceRequest request) {
        Maintenance maintenance = maintenanceRespository.findById(id).orElseThrow(() -> new RuntimeException("Maintenance not found with id: " + id));

        maintenance.setAmount(request.getAmount());
        maintenance.setMonth(request.getMonth());

        if (request.getResidentId() != null) {
            Resident resident = residentRespository.findById(request.getResidentId()).orElseThrow(() -> new RuntimeException("Resident not found with id: " + request.getResidentId()));
            maintenance.setResident(resident);
        }
        Maintenance updated = maintenanceRespository.save(maintenance);
        return convertToResponse(updated);
    }


    public MaintenaceResponst delete(Long id) {
        Maintenance maintenance = maintenanceRespository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Maintenance not found with id: " + id));

        MaintenaceResponst response = convertToResponse(maintenance);
        maintenanceRespository.delete(maintenance);

        return response;
    }



    }




