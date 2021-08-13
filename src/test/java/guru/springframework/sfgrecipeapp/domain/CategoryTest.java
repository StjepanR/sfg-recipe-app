package guru.springframework.sfgrecipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        Long id = 4L;

        category.setId(id);

        assertEquals(id, category.getId());
    }

    @Test
    public void getDescription() throws Exception {

    }

    @Test
    public void getRecipes() throws Exception {

    }
}
