import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Recipe } from '../model/recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  @Output()
  recipeWasSelected = new EventEmitter<Recipe>();

  recipes: Recipe[] = [
    new Recipe(
      'Test recipe1', 'Just test bro',
      'assets/default_recipe.jpg'
    ),
    new Recipe(
      'Test recipe2', 'Just test bro and bla bla bla',
      'assets/default_recipe.jpg'
    )
  ];

  constructor() {
  }

  ngOnInit() {
  }

  public onRecipeSelected(recipe: Recipe): void {
    this.recipeWasSelected.emit(recipe);
  }
}
