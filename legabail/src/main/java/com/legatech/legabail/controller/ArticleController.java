package com.legatech.legabail.controller;

import com.legatech.legabail.entity.ArticleJuridique;
import com.legatech.legabail.entity.Regle;
import com.legatech.legabail.repository.ArticleJuridiqueRepository;
import com.legatech.legabail.repository.RegleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleJuridiqueRepository articleJuridiqueRepository;
    private final RegleRepository regleRepository;

    public ArticleController(
            ArticleJuridiqueRepository articleJuridiqueRepository,
            RegleRepository regleRepository
    ) {
        this.articleJuridiqueRepository = articleJuridiqueRepository;
        this.regleRepository = regleRepository;
    }

    @GetMapping("/articles")
    public String afficherArticles(Model model) {
        model.addAttribute("articles", articleJuridiqueRepository.findAll());
        return "articles/articles";
    }

    @GetMapping("/articles/{id}")
    public String afficherDetailArticle(@PathVariable Long id, Model model) {
        ArticleJuridique article = articleJuridiqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article introuvable : " + id));

        List<Regle> regles = regleRepository.findByArticleJuridiqueIdOrderByIdAsc(id);

        model.addAttribute("article", article);
        model.addAttribute("regles", regles);

        return "articles/article-detail";
    }
}