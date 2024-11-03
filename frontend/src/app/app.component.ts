import { Component } from '@angular/core';
import { DashboardCurrencyComponent } from './component/dashboard-currency/dashboard-currency.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  imports: [DashboardCurrencyComponent,RouterModule

  ]
})
export class AppComponent {

}
