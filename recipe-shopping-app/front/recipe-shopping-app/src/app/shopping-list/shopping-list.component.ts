import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {

  ingredients: Ingredient[] = [
    new Ingredient('Apples', 5),
    new Ingredient('Tomatoes', 2),
    new Ingredient('Water', 2)
  ];

  constructor() {
  }

  ngOnInit() {
  }

  public onIngredientAdded(ingredient: Ingredient): void {
    this.ingredients.push(ingredient);
  }
}
