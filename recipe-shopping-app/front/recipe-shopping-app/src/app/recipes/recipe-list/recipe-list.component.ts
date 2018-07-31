import { Component, OnInit } from '@angular/core';
import { Recipe } from '../model/recipe.model';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

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

}
