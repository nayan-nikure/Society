package gite.Management.System.Entity;

import Society.Management.System.Entity.Flat;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Society {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "society", cascade = CascadeType.ALL)
    private List<Flat> flats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }
}
