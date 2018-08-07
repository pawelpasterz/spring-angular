import { Component, Input, OnInit } from '@angular/core';
import { Recipe } from '../../model/recipe.model';
import { RecipeService } from '../../recipe.service';

@Component({
  selector: 'app-recipe-item',
  templateUrl: './recipe-item.component.html',
  styleUrls: ['./recipe-item.component.css']
})
export class RecipeItemComponent implements OnInit {

  @Input()
  public recipe: Recipe;

  constructor(private recipeService: RecipeService) {
  }

  public ngOnInit() {
  }

  public onSelected(): void {
    this.recipeService.recipeSelected.emit(this.recipe);
  }
}
