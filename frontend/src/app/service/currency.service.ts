import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface CurrencyRequest {
    currency: string;
    name: string;
}

export interface CurrencyResponse {
    value: number;
}

export interface RequestHistory {
    currency: string;
    name: string;
    date: string;
    value: number;
}

@Injectable({
    providedIn: 'root'
})
export class CurrencyService {
    private apiUrl = 'http://localhost:8080/currencies';

    constructor(private http: HttpClient) { }

    getCurrencyValue(currency: string, name: string, request: CurrencyRequest): Observable<CurrencyResponse> {
        return this.http.post<CurrencyResponse>(`${this.apiUrl}/get-current-currency-value-command`, request)
            .pipe(catchError(this.handleError));
    }

    getRequestHistory(): Observable<RequestHistory[]> {
        return this.http.get<RequestHistory[]>(`${this.apiUrl}/requests`)
            .pipe(catchError(this.handleError));
    }

    private handleError(error: HttpErrorResponse) {
        let errorMessage = 'Wystąpił nieznany błąd!';
        if (error.error instanceof ErrorEvent) {
            errorMessage = `Błąd: ${error.error.message}`;
        } else {
            errorMessage = `Kod błędu: ${error.status}, komunikat: ${error.message}`;
        }
        return throwError(() => new Error(errorMessage));
    }
}
