package pl.pasterz.recipeshoppinglist.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "recipes")
@AllArgsConstructor
@Data
public class Recipe {

  @Id
  private Integer id;
  private String name;
  private String description;
  private String imagePath;

  @Embedded
  private Ingredient[] ingredients;
}