package com.legatech.legabail.service;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.springframework.stereotype.Service;

@Service
public class MotDePasseService {

    private static final String PREFIXE = "pbkdf2-sha256";
    private static final int ITERATIONS = 600_000;
    private static final int TAILLE_CLE = 256;

    public String hacher(String motDePasse) {
        byte[] sel = new byte[16];
        new SecureRandom().nextBytes(sel);
        byte[] hash = calculer(motDePasse, sel, ITERATIONS);
        return PREFIXE + "$" + ITERATIONS + "$"
                + Base64.getEncoder().encodeToString(sel) + "$"
                + Base64.getEncoder().encodeToString(hash);
    }

    public boolean verifier(String motDePasse, String valeurStockee) {
        if (motDePasse == null || valeurStockee == null) {
            return false;
        }

        if (!valeurStockee.startsWith(PREFIXE + "$")) {
            return MessageDigest.isEqual(
                    motDePasse.getBytes(StandardCharsets.UTF_8),
                    valeurStockee.getBytes(StandardCharsets.UTF_8));
        }

        try {
            String[] parties = valeurStockee.split("\\$", 4);
            if (parties.length != 4) {
                return false;
            }
            int iterations = Integer.parseInt(parties[1]);
            byte[] sel = Base64.getDecoder().decode(parties[2]);
            byte[] attendu = Base64.getDecoder().decode(parties[3]);
            byte[] obtenu = calculer(motDePasse, sel, iterations);
            return MessageDigest.isEqual(attendu, obtenu);
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    private byte[] calculer(String motDePasse, byte[] sel, int iterations) {
        PBEKeySpec specification = new PBEKeySpec(
                motDePasse.toCharArray(), sel, iterations, TAILLE_CLE);
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
                    .generateSecret(specification)
                    .getEncoded();
        } catch (GeneralSecurityException exception) {
            throw new IllegalStateException("Impossible de proteger le mot de passe.", exception);
        } finally {
            specification.clearPassword();
        }
    }
}
