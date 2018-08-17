package pl.pasterz.recipeshoppinglist.entity;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Ingredient {

  private String ingName;
  private int amount;
}
