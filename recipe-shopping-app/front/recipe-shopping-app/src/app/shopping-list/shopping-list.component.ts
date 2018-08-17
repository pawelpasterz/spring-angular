import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../shared/ingredient.model';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';
import * as ShoppingList from './store/shopping-list.reducers';
import * as ShoppingListAction from './store/shopping-list.actions';

@Component({
  selector: 'app-shopping-list',
  templateUrl: './shopping-list.component.html',
  styleUrls: ['./shopping-list.component.css']
})
export class ShoppingListComponent implements OnInit {

  public shoppingListState: Observable<{ ingredients: Ingredient[] }>;

  constructor(private store: Store<ShoppingList.AppState>) {
  }

  ngOnInit() {
    this.shoppingListState = this.store.select('shoppingList');
  }

  public onEditItem(index: number): void {
    this.store.dispatch(new ShoppingListAction.StartEdit(index));
  }
}
