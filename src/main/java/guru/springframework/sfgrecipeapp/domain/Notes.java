package guru.springframework.sfgrecipeapp.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // No cascade, if we delete notes we don't want to delete recipe object
    private Recipe recipe;

    @Lob // Large binary object - it is saved in blob in database
    private String recipeNotes;

}
