package guru.springframework.sfgrecipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @Lob
    private Byte[] image;

    // Recipe owns this entity (it contains the foreign key to notes)
    @OneToOne(cascade = CascadeType.ALL) // If we delete recipe we want to delete all notes related
    private Notes notes;

    // We won't be using ingredients or notes independently, we will only navigate to them through Recipe
    // so we don't need to create repositories for them

    // Recipe owns this entity (it contains the foreign key to ingredients)
    @OneToMany(cascade = CascadeType.ALL,   // If we delete recipe we want to delete all ingredients related
                mappedBy = "recipe")        // Every ingredient will have property recipe
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    // We define Join Table because JPA creates both types of connections in this e.g. category_recipes and recipe_categories
    // The owner side is the one which defines relationship
    // @JoinColumn defines the name of foreign key
    // @JoinTable defines name of join table and the names of foreign keys
    // Usage of @JoinTable and @JoinColumn is not required. JPA will generate the table and column names for us
    @JoinTable(name = "recipe_category",                    // Table name that comes out from connecting Recipe and Category
            joinColumns = @JoinColumn(name = "recipe_id"),          // Recipe is connected to new table recipe_category by attribute recipe_id
            inverseJoinColumns = @JoinColumn(name = "category_id")) // Category is connected to new table recipe_category by attribute category_id
    private Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
