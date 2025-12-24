package Society.Management.System.Controller;

import Society.Management.System.Entity.DTO.MaintenaceDto.MaintenaceResponst;
import Society.Management.System.Entity.DTO.ResidentDto.ResidentRequest;
import Society.Management.System.Entity.DTO.ResidentDto.Residentresponses;
import Society.Management.System.Service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resident")
public class ResidentController {
    @Autowired
    private ResidentService residentService;

    @PostMapping("/add")
    public ResponseEntity<Residentresponses> add(@RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.create(request));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Residentresponses>> getAll() {
        return ResponseEntity.ok(residentService.getAll());
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<Residentresponses> getById(@PathVariable Long id) {
        return ResponseEntity.ok(residentService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Residentresponses> update( @PathVariable Long id,   @RequestBody ResidentRequest request) {
        return ResponseEntity.ok(residentService.update(id, request));

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        residentService.delete(id);
        return ResponseEntity.ok("Resident deleted successfully");
    }



}
