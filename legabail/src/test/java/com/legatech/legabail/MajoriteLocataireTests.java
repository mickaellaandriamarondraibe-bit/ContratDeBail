package com.legatech.legabail;

import com.legatech.legabail.service.MajoriteLocataire;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MajoriteLocataireTests {
    @Test void controleAnniversaireEtDatesInvalides() {
        LocalDate seuil = LocalDate.now().minusYears(18);
        assertDoesNotThrow(() -> MajoriteLocataire.verifier(seuil));
        assertThrows(IllegalArgumentException.class, () -> MajoriteLocataire.verifier(seuil.plusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> MajoriteLocataire.verifier(LocalDate.now().plusDays(1)));
        assertThrows(IllegalArgumentException.class, () -> MajoriteLocataire.verifier(null));
    }
}
