package Society.Management.System.Entity.DTO.MaintenaceDto;

import Society.Management.System.Entity.Resident;
import jakarta.persistence.*;

public class MaintenaceRequest {
    private Long id;
    private Double amount;
    private String month;
    private Long residentId;


        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }
}
