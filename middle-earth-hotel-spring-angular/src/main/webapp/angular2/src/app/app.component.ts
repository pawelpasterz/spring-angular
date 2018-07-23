import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {Observable} from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private http: Http) {
  }

  private baseURL: string = 'http://localhost:8080';
  private getURL: string = `${this.baseURL}/room/reservation/v1`;
  private postURL: string = `${this.baseURL}/room/reservation/v1`;
  public submitted: boolean;
  roomsearch: FormGroup;
  rooms: Room[];
  currentCheckInValue: string;
  currentCheckOutValue: string;
  private request: any;

  ngOnInit() {
    this.roomsearch = new FormGroup({
      checkin: new FormControl(''),
      checkout: new FormControl('')
    });

    const roomsearchValueChanges$ = this.roomsearch.valueChanges;

    roomsearchValueChanges$.subscribe(valChanged => {
      this.currentCheckInValue = valChanged.checkin;
      this.currentCheckOutValue = valChanged.checkout;
    });
  }

  reserveRoom(value: string) {
    this.request = new ReserveRequest(
      value,
      this.currentCheckInValue,
      this.currentCheckOutValue
    );

    this.createReservation(this.request);
  }

  createReservation(body: ReserveRequest) {
    let bodyString = JSON.stringify(body);
    let headers = new Headers({'Content-Type': 'application/json'});
    let option = new RequestOptions({headers: headers});

    this.http.post(this.postURL, body, option)
      .subscribe(res => console.log(res));
  }

  onSubmit({value, valid}: { value: Roomsearch, valid: boolean }) {

    this.getAll()
      .subscribe(
        rooms => this.rooms = rooms,
        err => {
          console.log(err);
        });
  }

  getAll(): Observable<Room[]> {

    return this.http
      .get(`${this.getURL}?checkin=${this.currentCheckInValue}&checkout=${this.currentCheckOutValue}`)
      .map(this.mapRoom);
  }

  mapRoom(response: Response): Room[] {
    return response.json().content;
  }

}

export interface Roomsearch {
  checkin: string;
  checkout: string;
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export class ReserveRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string, checkin: string, checkout: string) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
