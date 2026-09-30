package com.legatech.legabail.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StockageImageService {

    private static final long TAILLE_MAXIMALE = 5L * 1024 * 1024;
    private static final Map<String, String> EXTENSIONS = Map.of(
            "image/jpeg", ".jpg",
            "image/png", ".png",
            "image/webp", ".webp"
    );

    private final Path repertoire;

    public StockageImageService(@Value("${legabail.images.directory:uploads/biens}") String repertoire) {
        this.repertoire = Path.of(repertoire).toAbsolutePath().normalize();
    }

    public String stocker(MultipartFile fichier) {
        if (fichier == null || fichier.isEmpty()) {
            return null;
        }
        valider(fichier);
        String extension = EXTENSIONS.get(fichier.getContentType());

        try {
            Files.createDirectories(repertoire);
            String nomFichier = UUID.randomUUID() + extension;
            Path destination = repertoire.resolve(nomFichier).normalize();
            if (!destination.getParent().equals(repertoire)) {
                throw new IllegalArgumentException("Nom de fichier invalide.");
            }
            try (InputStream contenu = fichier.getInputStream()) {
                Files.copy(contenu, destination, StandardCopyOption.REPLACE_EXISTING);
            }
            return "/uploads/biens/" + nomFichier;
        } catch (IOException exception) {
            throw new IllegalStateException("Impossible d'enregistrer la photo du bien.", exception);
        }
    }

    public void valider(MultipartFile fichier) {
        if (fichier == null || fichier.isEmpty()) return;
        if (fichier.getSize() > TAILLE_MAXIMALE) {
            throw new IllegalArgumentException("La photo ne doit pas dépasser 5 Mo.");
        }

        String extension = EXTENSIONS.get(fichier.getContentType());
        if (extension == null || !signatureValide(fichier, extension)) {
            throw new IllegalArgumentException("Choisissez une image JPG, PNG ou WebP valide.");
        }

    }

    private boolean signatureValide(MultipartFile fichier, String extension) {
        try (InputStream contenu = fichier.getInputStream()) {
            byte[] entete = contenu.readNBytes(12);
            return switch (extension) {
                case ".jpg" -> entete.length >= 3
                        && (entete[0] & 0xff) == 0xff
                        && (entete[1] & 0xff) == 0xd8
                        && (entete[2] & 0xff) == 0xff;
                case ".png" -> entete.length >= 8
                        && (entete[0] & 0xff) == 0x89
                        && entete[1] == 0x50 && entete[2] == 0x4e && entete[3] == 0x47
                        && entete[4] == 0x0d && entete[5] == 0x0a
                        && entete[6] == 0x1a && entete[7] == 0x0a;
                case ".webp" -> entete.length >= 12
                        && entete[0] == 'R' && entete[1] == 'I'
                        && entete[2] == 'F' && entete[3] == 'F'
                        && entete[8] == 'W' && entete[9] == 'E'
                        && entete[10] == 'B' && entete[11] == 'P';
                default -> false;
            };
        } catch (IOException exception) {
            return false;
        }
    }
}
