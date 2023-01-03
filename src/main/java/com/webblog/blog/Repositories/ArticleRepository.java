package com.webblog.blog.Repositories;

import com.webblog.blog.Models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
