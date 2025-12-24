package Society.Management.System.Controller;

import Society.Management.System.Entity.DTO.SocietyDto.SocietyRequest;
import Society.Management.System.Entity.DTO.SocietyDto.SocietyResponse;
import Society.Management.System.Service.SocietyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SocietyController {

    @Autowired
    private SocietyService societyService;

    @PostMapping("/add")
    public ResponseEntity<SocietyResponse> add(@RequestBody SocietyRequest request) {
        return ResponseEntity.ok(societyService.create(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SocietyResponse>> getAll() {
        return ResponseEntity.ok(societyService.getAll());
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<SocietyResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(societyService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<SocietyResponse> update(  @PathVariable Long id, @RequestBody SocietyRequest request) {
        return ResponseEntity.ok(societyService.update(id, request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        societyService.delete(id);
        return ResponseEntity.ok("Society deleted successfully");
    }

}
