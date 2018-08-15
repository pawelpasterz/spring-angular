import { Injectable } from '@angular/core';
import * as firebase from 'firebase';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private token: string;

  public signunpUser(email: string, password: string): void {
    firebase.auth().createUserWithEmailAndPassword(email, password)
      .catch(console.log.bind(console));
  }

  public signinUser(email: string, password: string): void {
    firebase.auth().signInWithEmailAndPassword(email, password)
      .then(() => firebase.auth().currentUser
        .getIdToken()
        .then((token: string) => this.token = token))
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
}
