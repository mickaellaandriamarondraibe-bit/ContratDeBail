package com.legatech.legabail.form;

import jakarta.validation.constraints.*;

public class CandidatureForm {

    @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE)
    private java.time.LocalDate dateNaissance;
    public java.time.LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(java.time.LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }

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
