package com.webblog.blog.Controllers;

import com.webblog.blog.Models.Article;
import com.webblog.blog.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/blog")
    public String getBlogPage(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "blog-main";
    }
}
