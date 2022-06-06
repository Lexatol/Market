package ru.geekbrains.market.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.Category;
import ru.geekbrains.market.repositories.CategoryRepository;

import java.util.List;

@SpringBootTest
@ActiveProfiles("h2")
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Spy
    private CategoryRepository categoryRepository;


    @Test
    public void categoryFindAllTest() {

        List<Category> categoryList = categoryService.findAll();
        Assertions.assertEquals(3, categoryList.size());

    }
}
