package pl.pasterz.recipeshoppinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pasterz.recipeshoppinglist.entity.Recipe;

public interface RecipesRepository extends JpaRepository<Recipe, Integer> {

}
