package com.legatech.legabail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.legatech.legabail.service.StockageImageService;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.mock.web.MockMultipartFile;

class StockageImageServiceTests {

    @TempDir
    Path repertoire;

    @Test
    void stockeUneImagePngValide() throws Exception {
        byte[] contenu = {
                (byte) 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a,
                0x00, 0x00, 0x00, 0x00
        };
        MockMultipartFile image = new MockMultipartFile(
                "image", "maison.png", "image/png", contenu);

        String url = new StockageImageService(repertoire.toString()).stocker(image);

        assertTrue(url.matches("/uploads/biens/[0-9a-f-]+\\.png"));
        assertTrue(Files.exists(repertoire.resolve(Path.of(url).getFileName())));
    }

    @Test
    void refuseUnFichierQuiNestPasUneImage() {
        MockMultipartFile fichier = new MockMultipartFile(
                "image", "document.png", "image/png", "pas une image".getBytes());

        IllegalArgumentException erreur = assertThrows(IllegalArgumentException.class,
                () -> new StockageImageService(repertoire.toString()).stocker(fichier));

        assertEquals("Choisissez une image JPG, PNG ou WebP valide.", erreur.getMessage());
    }
}
