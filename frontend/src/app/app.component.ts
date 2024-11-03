import { Component } from '@angular/core';
import { DashboardCurrencyComponent } from './component/dashboard-currency/dashboard-currency.component';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [DashboardCurrencyComponent
  ]
})
export class AppComponent {

}
