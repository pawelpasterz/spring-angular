import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { Ingredient } from '../../shared/ingredient.model';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Store } from '@ngrx/store';
import * as ShoppingListActions from '../store/shopping-list.actions';
import * as ShoppingList from '../store/shopping-list.reducers';

@Component({
  selector: 'app-shopping-edit',
  templateUrl: './shopping-edit.component.html',
  styleUrls: ['./shopping-edit.component.css']
})
export class ShoppingEditComponent implements OnInit, OnDestroy {

  @ViewChild('f')
  public shoppingListForm: NgForm;
  private subscription: Subscription;
  public editMode = false;
  private editedItem: Ingredient;

  constructor(private store: Store<ShoppingList.AppState>) {
  }

  ngOnInit() {
    this.subscription = this.store.select('shoppingList')
      .subscribe((data) => {
          if (data.editedIngredientIndex > -1) {
            this.editedItem = data.editedIngredient;
            this.editMode = true;
            this.shoppingListForm.setValue({
              name: this.editedItem.name,
              amount: this.editedItem.amount
            });
          } else {
            this.editMode = false;
          }
        }
      );
  }

  public onSubmitItem(form: NgForm): void {
    const value = form.value;
    const newIngredient = new Ingredient(value.name, value.amount);
    if (this.editMode) {
      this.store.dispatch(new ShoppingListActions.UpdateIngredient({ingredient: newIngredient}));
    } else {
      this.store.dispatch(new ShoppingListActions.AddIngredient(newIngredient));
    }
    this.shoppingListForm.resetForm();
    this.editMode = false;
  }

  ngOnDestroy(): void {
    this.store.dispatch(new ShoppingListActions.StopEdit());
    this.subscription.unsubscribe();
  }

  public onClear(): void {
    this.shoppingListForm.resetForm();
    this.editMode = false;
  }

  public onDelete(): void {
    this.store.dispatch(new ShoppingListActions.DeleteIngredient());
    this.onClear();
  }
}
