import { Injectable } from '@angular/core';
import { ApiRequest } from '../models/api-request.model';
import { ApiResponse } from '../models/api-response.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, Subject, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { VendingMachine } from '../models/vending-machine.model';
import { Snack } from '../models/snack.model';

@Injectable({
  providedIn: 'root'
})
export class VendingMachineService {

  private vendingMachine: VendingMachine;

  public machineState$: Subject<VendingMachine> = new Subject<VendingMachine>();

  constructor(private http: HttpClient) {
    this.vendingMachine = new VendingMachine(
      "undefined",
      []
    )
  }

  getMachineState() {
    return this.http.get<VendingMachine>(environment.apiUrl + 'getStock?machine_id=' + this.vendingMachine.machine_id);
  }

  loadStock(apiRequest: ApiRequest): Observable<ApiResponse> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    return this.http.post<ApiResponse>(environment.apiUrl + 'loadStock/', apiRequest, {headers})
      .pipe(
        tap(data => console.log('createdStockResponse: ' + JSON.stringify(data))),
        tap(data => {
          this.vendingMachine.items = data.items;
          this.vendingMachine.machine_id = data.serialNumber;
        }),
        catchError(this.handleError)
      );
  }

  private handleError(err: any) {

    let errorMessage: string;
    if (err.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      // The backend returned an unsuccessful response code.
      errorMessage = `Backend returned code ${err.status}: ${err.body.error}`;
    }
    console.error(err);
    return throwError(errorMessage);
  }

  deposit(amount: number) {
    return this.http.get<number>(environment.apiUrl + 'deposit?machine_id=' + this.vendingMachine.machine_id + '&amount=' + amount);
  }

  buy(snack: Snack): Observable<any> {
    return this.http.get<number>(environment.apiUrl + 'purchase?machine_id=' + this.vendingMachine.machine_id + '&row=' + snack.row + '&col=' + snack.col);
  }

  refreshState() {
    this.http.get<VendingMachine>(environment.apiUrl + 'getStock?machine_id=' + this.vendingMachine.machine_id).subscribe(state => {
      this.machineState$.next(state);
    })
  }
}
