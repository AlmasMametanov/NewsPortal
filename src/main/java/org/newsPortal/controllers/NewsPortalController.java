package org.newsPortal.controllers;

import org.modelmapper.ModelMapper;
import org.newsPortal.dto.ArticleDTO;
import org.newsPortal.dto.CategoryDTO;
import org.newsPortal.dto.UserDTO;
import org.newsPortal.models.Article;
import org.newsPortal.models.Category;
import org.newsPortal.models.User;
import org.newsPortal.services.ArticleService;
import org.newsPortal.services.CategoryService;
import org.newsPortal.services.UserService;
import org.newsPortal.util.EmailAlreadyExistsException;
import org.newsPortal.util.ErrorResponse;
import org.newsPortal.util.PageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NewsPortalController {
    private final CategoryService categoryService;
    private final ArticleService articleService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public NewsPortalController(CategoryService categoryService, ArticleService articleService,
                                UserService userService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategory() {
        return ResponseEntity.ok(categoryService.findAllCategory().stream().map(this::mapToCategoryDTO)
                .collect(Collectors.toList()));
    }

    @PostMapping("/category")
    public ResponseEntity<Object> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.createCategory(mapToCategory(categoryDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/category")
    public ResponseEntity<Object> updateCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        categoryService.updateCategory(mapToCategory(categoryDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.deleteById(categoryId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/articles")
    public ResponseEntity<List<ArticleDTO>> getAllArticleByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(articleService.findAllArticleByCategoryId(categoryId).stream().map(this::mapToArticleDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable("articleId") Long articleId) {
        return ResponseEntity.ok(mapToArticleDTO(articleService.findById(articleId)));
    }

    @PostMapping("/article")
    public ResponseEntity<Object> createArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        articleService.createArticle(mapToArticle(articleDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/article")
    public ResponseEntity<Object> updateArticle(@RequestBody @Valid ArticleDTO articleDTO) {
        articleService.updateById(mapToArticle(articleDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/articles/{articleId}")
    public ResponseEntity<Object> deleteArticle(@PathVariable("articleId") Long articleId) {
        articleService.deleteById(articleId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        return ResponseEntity.ok(userService.findAll().stream().map(this::mapToUserDTO)
                .collect(Collectors.toList()));
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(mapToUserDTO(userService.findByEmail(email)));
    }

    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(mapToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/user/email")
    public ResponseEntity<Object> updateUserEmail(@RequestBody @Valid UserDTO userDTO) {
        userService.updateEmailById(mapToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/user/password")
    public ResponseEntity<Object> updateUserPassword(@RequestBody @Valid UserDTO userDTO) {
        userService.updatePasswordById(mapToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler(value = PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ErrorResponse handlePageNotFound(PageNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    private ErrorResponse handleEmailAlreadyExists(EmailAlreadyExistsException exception) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), exception.getMessage());
    }

    private Category mapToCategory(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    private CategoryDTO mapToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    private ArticleDTO mapToArticleDTO(Article article) {
        return modelMapper.map(article, ArticleDTO.class);
    }

    private Article mapToArticle(ArticleDTO articleDTO) {
        return modelMapper.map(articleDTO, Article.class);
    }

    private UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User mapToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
