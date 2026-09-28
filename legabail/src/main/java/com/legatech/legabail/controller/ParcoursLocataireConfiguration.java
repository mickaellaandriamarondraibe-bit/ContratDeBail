package com.legatech.legabail.controller;

import jakarta.servlet.http.*;
import java.util.UUID;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class ParcoursLocataireConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                HttpSession session = request.getSession();
                if (session.getAttribute("csrfLocataire") == null) { session.setAttribute("csrfLocataire", UUID.randomUUID().toString()); }
                if ("POST".equals(request.getMethod()) && !session.getAttribute("csrfLocataire").equals(request.getParameter("_csrf"))) {
                    response.sendError(403, "Formulaire expire. Rechargez la page."); return false;
                }
                return true;
            }
        }).addPathPatterns("/inscription/locataire", "/locataire/**", "/espace-locataire", "/annonces/*/candidater",
                "/candidatures/**", "/propositions/**", "/contrats/**", "/bailleur/candidatures");
    }
}
