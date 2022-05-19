import { Injectable } from '@angular/core';
import { ApiRequest } from '../models/api-request.model';
import { ApiResponse } from '../models/api-response.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { VendingMachine } from '../models/vending-machine.model';

@Injectable({
  providedIn: 'root'
})
export class VendingMachineService {

  private vendingMachine: VendingMachine;

  constructor(private http: HttpClient) {
    this.vendingMachine = new VendingMachine(
      "undefined",
      []
    )
  }

  loadStock(apiRequest: ApiRequest): Observable<ApiResponse> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.http.post<ApiResponse>(environment.apiUrl + 'loadStock/', apiRequest, {headers})
      .pipe(
        tap(data => console.log('createdStockResponse: ' + JSON.stringify(data))),
        tap(data => {
          this.vendingMachine.stock = data.items;
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

}
