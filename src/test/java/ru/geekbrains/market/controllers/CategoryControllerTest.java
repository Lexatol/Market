package ru.geekbrains.market.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.market.model.Category;
import ru.geekbrains.market.services.CategoryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CategoryService categoryService;

   @Test
   @WithMockUser (username = "user", authorities = "USER")
   public void findAllTest() throws Exception {
    Category category = new Category();
    category.setId(1L);
    category.setTitle("Laptop");
    List<Category> categoryList = new ArrayList<>(Arrays.asList(category));
    given(categoryService.findAll()).willReturn(categoryList);

    mockMvc.perform(get("/api/v1/categories")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].title", is(categoryList.get(0).getTitle())));
   }
}
