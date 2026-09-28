package com.legatech.legabail.service.controle;

public record ResultatControle(
        String codeRegle,
        String article,
        boolean conforme,
        boolean bloquante,
        String explicationSimple
) {
}