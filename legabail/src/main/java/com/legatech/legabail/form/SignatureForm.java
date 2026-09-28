package com.legatech.legabail.form;

import jakarta.validation.constraints.AssertTrue;

public class SignatureForm {
    @AssertTrue(message = "Vous devez confirmer votre signature.")
    private boolean confirmation;
    public boolean isConfirmation() { return confirmation; }
    public void setConfirmation(boolean confirmation) { this.confirmation = confirmation; }
}
