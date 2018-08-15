import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecipeService } from '../recipes/recipe.service';
import { Recipe } from '../recipes/model/recipe.model';
import { AuthService } from '../auth/auth.service';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor(private http: HttpClient,
              private recipeService: RecipeService,
              private authService: AuthService) {
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
    const token = this.authService.getToken();
    this.http.get<Recipe[]>(`https://ng-recipe-book-pawel.firebaseio.com/recipes.json?auth=${token}`)
      .subscribe((response: Recipe[]) => {
        this.recipeService.setRecipes(response);
      });
  }
}
