import { EventEmitter, Injectable } from '@angular/core';
import { Recipe } from './model/recipe.model';
import { Ingredient } from '../shared/ingredient.model';
import { ShoppingListService } from '../shopping-list/shopping-list.service';
import { Subject } from 'rxjs';
import { tick } from '@angular/core/testing';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  public recipesChanged = new Subject<Recipe[]>();

  private recipes: Recipe[] = [
    new Recipe(
      'Big Fat Burger', 'Just test bro',
      'assets/burger.jpg',
      [
        new Ingredient('Meat', 1),
        new Ingredient('French Fries', 20)
      ]
    ),
    new Recipe(
      'Something chinese', 'Just test bro and bla bla bla',
      'assets/chinese.jpg',
      [
        new Ingredient('Buns', 2),
        new Ingredient('Meat', 1)
      ]
    )
  ];

  constructor(private shoppingListService: ShoppingListService) {
  }

  public getRecipes(): Recipe[] {
    return this.recipes.slice();
  }

  public getRecipe(index: number): Recipe {
    return this.recipes[index];
  }

  public addIngredientsToShoppingList(ingredients: Ingredient[]): void {
    this.shoppingListService.addIngredients(ingredients);
  }

  public addRecipe(recipe: Recipe): void {
    this.recipes.push(recipe);
    this.recipesChanged.next(this.recipes.slice());
  }

  public updateRecipe(index: number, newRecipe: Recipe): void {
    this.recipes[index] = newRecipe;
    this.recipesChanged.next(this.recipes.slice());
  }

  public deleteRecipe(index: number): void {
    this.recipes.splice(index, 1);
    this.recipesChanged.next(this.recipes.slice());
  }

  public setRecipes(recipes: Recipe[]): void {
    this.recipes = recipes;
    this.recipesChanged.next(this.recipes);
  }
}
