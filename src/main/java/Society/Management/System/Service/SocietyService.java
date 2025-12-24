package Society.Management.System.Service;

import Society.Management.System.Entity.DTO.SocietyDto.SocietyRequest;
import Society.Management.System.Entity.DTO.SocietyDto.SocietyResponse;
import Society.Management.System.Entity.Society;
import Society.Management.System.Respository.SocietyRespository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SocietyService {
    @Autowired
    private SocietyRespository societyRespository;

    public  SocietyResponse create(SocietyRequest request) {
            Society society = new Society();
            society.setName(request.getName());
            society.setAddress(request.getAddress());

            Society saved = societyRespository.save(society);

            SocietyResponse response = new SocietyResponse();
            response.setId(saved.getId());
            response.setName(saved.getName());
            response.setAddress(saved.getAddress());
            return response;
        }


    public @Nullable List<SocietyResponse> getAll() {
        List<Society> list = societyRespository.findAll();
        List<SocietyResponse> responses = new ArrayList<>();

        for (Society society : list) {
            SocietyResponse societyResponse = new SocietyResponse();
            societyResponse.setId(society.getId());
            societyResponse.setName(society.getName());
            societyResponse.setAddress(society.getAddress());
            societyResponse.setAddress(society.getAddress());
            responses.add(societyResponse);
        }
        return responses;
    }

    public @Nullable SocietyResponse getById(Long id) {
            Society society =societyRespository.findById(id).orElseThrow(() -> new RuntimeException("Society not found"));
            return convertToResponse(society);
        }

    private @Nullable SocietyResponse convertToResponse(Society society) {
        SocietyResponse response = new SocietyResponse();
        response.setId(society.getId());
        response.setName(society.getName());
        response.setAddress(society.getAddress());
        return response;
    }

    public @Nullable SocietyResponse update(Long id, SocietyRequest request) {
        Society society = societyRespository.findById(id) .orElseThrow(() -> new RuntimeException("Society not found"));
        society.setName(request.getName());
        society.setAddress(request.getAddress());
        return convertToResponse(societyRespository.save(society));
    }

    public void delete(Long id) {
        Society society = societyRespository.findById(id) .orElseThrow(() -> new RuntimeException("Society not found"));
        societyRespository.delete(society);
    }
    }