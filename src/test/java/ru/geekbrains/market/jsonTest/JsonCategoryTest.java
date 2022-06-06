package ru.geekbrains.market.jsonTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.market.model.Category;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@ActiveProfiles("h2")
public class JsonCategoryTest {
    @Autowired
    private JacksonTester<Category> jackson;

    @Test
    public void jsonSerializationTest() throws IOException {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Laptop");

        assertThat(this.jackson.write(category)).hasJsonPathNumberValue("$.id");
        assertThat(this.jackson.write(category)).extractingJsonPathStringValue("$.title").isEqualTo("Laptop");
    }

    @Test
    public void jsonDeserializationTest() throws IOException {
        String content = "{\"id\": 1,\"title\":\"Laptop\"}";
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Laptop");

        assertThat(this.jackson.parse(content)).isEqualTo(category);
        assertThat(this.jackson.parseObject(content).getTitle()).isEqualTo("Laptop");
    }
}
