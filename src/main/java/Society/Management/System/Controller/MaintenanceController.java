package Society.Management.System.Controller;

import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceRequest;
import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceResponst;
import Society.Management.System.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/add")
    public ResponseEntity<MaintenaceResponst> add(@RequestBody MaintenaceRequest request) {
        return ResponseEntity.ok(maintenanceService.create(request));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<MaintenaceResponst>> getAll() {
        return ResponseEntity.ok(maintenanceService.getAll());
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<MaintenaceResponst> getById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.getById(id));
    }
    @PutMapping("/update{id}")
    public ResponseEntity<MaintenaceResponst> update(  @PathVariable Long id, @RequestBody MaintenaceRequest request) {
        return ResponseEntity.ok(maintenanceService.update(id, request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MaintenaceResponst> delete(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceService.delete(id));
    }

}
