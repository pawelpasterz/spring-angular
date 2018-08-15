import { Injectable } from '@angular/core';
import * as firebase from 'firebase';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string;

  constructor(private router: Router) {
  }

  public signunpUser(email: string, password: string): void {
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .catch(console.log.bind(console));
  }

  public signinUser(email: string, password: string): void {
    firebase.auth().signInWithEmailAndPassword(email, password)
      .then(() => {
        this.router.navigate(['/']);
        firebase.auth().currentUser
          .getIdToken()
          .then((token: string) => this.token = token);
      })
      .catch(console.log.bind(console));
  }

  public getToken(): string {
    this.fetchToken();
    return this.token;
  }

  private async fetchToken(): Promise<any> {
    return await firebase.auth().currentUser
      .getIdToken()
      .then((token: string) => this.token = token);
  }

  public isAuthenticated(): boolean {
    return this.token !== undefined;
  }

  public logout(): void {
    firebase.auth().signOut();
    this.token = undefined;
  }
}
