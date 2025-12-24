package Society.Management.System.Controller;

import Society.Management.System.Entity.DTO.FlatDto.FlatRequest;
import Society.Management.System.Entity.DTO.FlatDto.FlatResponse;
import Society.Management.System.Service.FlatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flat")
public class FlatController {

    @Autowired
    private FlatService flatService;

    @PostMapping("/add")
    public ResponseEntity<FlatResponse> add(@RequestBody FlatRequest request) {
        return ResponseEntity.ok(flatService.create(request));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<FlatResponse>>getall(){
        return ResponseEntity.ok(flatService.getall());
    }
    @GetMapping("/getById{id}")
    public ResponseEntity<FlatResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(flatService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<FlatResponse>updateId(  @PathVariable Long id,  @RequestBody FlatRequest request) {
        return ResponseEntity.ok(flatService.update(id,request));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        flatService.delete(id);
        return ResponseEntity.ok("Flat deleted successfully");
    }


}





