package com.webblog.blog.Controllers;

import com.webblog.blog.Models.Article;
import com.webblog.blog.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/blog")
    public String getArticles(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "blog-main";
    }

    @GetMapping("/blog/{id}")
    public String getArticle(@PathVariable(value="id") long id, Model model) {
        if(!articleRepository.existsById(id)) {
            return "home";
        }

        Optional<Article> article = articleRepository.findById(id);
        ArrayList<Article> res = new ArrayList<>();
        article.ifPresent(res::add);
        model.addAttribute("article", res);

        return "blog-details";
    }

    @GetMapping("/blog/create")
    public String getCreateArticle(Model model) {
        return "blog-add";
    }

    @PostMapping("/blog/create")
    public String postCreatePostArticle(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Article article = new Article(title, anons, full_text);
        articleRepository.save(article);
        return "redirect:/blog";
    }


}
