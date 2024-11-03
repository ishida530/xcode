import { CommonModule } from '@angular/common';
import { Component, ViewChild } from '@angular/core';
import { CurrencyFormComponent } from '../currency-form/currency-form.component';
import { TableLogsComponent } from '../table-logs/table-logs.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-dashboard-currency',
  standalone: true,
  imports: [CurrencyFormComponent, TableLogsComponent, CommonModule, FormsModule],
  templateUrl: './dashboard-currency.component.html',
  styleUrl: './dashboard-currency.component.css'
})
export class DashboardCurrencyComponent {
  @ViewChild(TableLogsComponent) tableLogs!: TableLogsComponent;

  constructor() { }

  refreshTable() {
    const tableLogsComponent = this.tableLogs;
    if (tableLogsComponent) {
      tableLogsComponent.fetchCurrencyRequests();
    }
  }
}
