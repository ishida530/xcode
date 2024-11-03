import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CurrencyService, RequestHistory } from '../../service/currency.service'; 

@Component({
  selector: 'app-table-logs',
  standalone: true,
  templateUrl: './table-logs.component.html',
  styleUrls: ['./table-logs.component.css'],
  imports: [CommonModule] 
})
export class TableLogsComponent {
  currencyRequests: RequestHistory[] = []; 

  constructor(private currencyService: CurrencyService) {
    this.fetchCurrencyRequests();
  }

  fetchCurrencyRequests() {
    this.currencyService.getRequestHistory().subscribe(
      (requests) => {
        this.currencyRequests = requests; 
      },
      (error) => {
        console.error('Error fetching currency requests', error);
      }
    );
  }
}
