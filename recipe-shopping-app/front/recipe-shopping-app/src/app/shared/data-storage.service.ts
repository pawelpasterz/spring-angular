import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecipeService } from '../recipes/recipe.service';
import { Recipe } from '../recipes/model/recipe.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor(private http: HttpClient,
              private recipeService: RecipeService) {
  }

  public storeRecipes(): void {
    this.http.put(
      'https://ng-recipe-book-pawel.firebaseio.com/recipes.json',
      this.recipeService.getRecipes()
    ).subscribe((response: Response) => {
      console.log(response);
    });
  }

  public getRecipes(): void {
    this.http.get<Recipe[]>('https://ng-recipe-book-pawel.firebaseio.com/recipes.json')
      .subscribe((response: Recipe[]) => {
        this.recipeService.setRecipes(response);
      });
  }
}
