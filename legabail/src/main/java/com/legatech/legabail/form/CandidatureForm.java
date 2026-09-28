package com.legatech.legabail.form;

import jakarta.validation.constraints.*;

public class CandidatureForm {
    @NotNull @Positive
    private Integer nombreOccupants = 1;
    @NotBlank @Size(max = 50)
    private String usagePrevu;
    @Size(max = 5000)
    private String message;
    public Integer getNombreOccupants() { return nombreOccupants; }
    public void setNombreOccupants(Integer nombreOccupants) { this.nombreOccupants = nombreOccupants; }
    public String getUsagePrevu() { return usagePrevu; }
    public void setUsagePrevu(String usagePrevu) { this.usagePrevu = usagePrevu; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
