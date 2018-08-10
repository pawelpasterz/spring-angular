import { Component, Input, OnInit } from '@angular/core';
import { Recipe } from './model/recipe.model';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.css']
})
export class RecipesComponent implements OnInit {

  @Input()
  public selectedRecipe: Recipe;

  constructor() {
  }

  ngOnInit() {
  }
}
