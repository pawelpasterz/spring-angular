package pl.pasterz.recipeshoppinglist.autoconfig;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pasterz.recipeshoppinglist.entity.Ingredient;
import pl.pasterz.recipeshoppinglist.entity.Recipe;
import pl.pasterz.recipeshoppinglist.repository.RecipesRepository;

@Component
@RequiredArgsConstructor
public class AutoPopulate implements CommandLineRunner {

  private final RecipesRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.saveAndFlush(
        new Recipe(
            1,
            "Big Fat Burger",
            "Just test bro",
            "assets/burger.jpg",
            new Ingredient[]{
                new Ingredient("Meat", 1),
                new Ingredient("French Fries", 20)
            }
        ));

    repository.saveAndFlush(
        new Recipe(
            2,
            "Something chinese",
            "Just test bro and bla bla bla",
            "assets/chinese.jpg",
            new Ingredient[]{
                new Ingredient("Buns", 2),
                new Ingredient("Meat", 1)
            }
        ));
  }
}