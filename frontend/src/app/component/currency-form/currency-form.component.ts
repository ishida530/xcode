import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CurrencyService, CurrencyRequest } from '../../service/currency.service';

@Component({
  selector: 'app-currency-form',
  standalone: true,
  templateUrl: './currency-form.component.html',
  styleUrls: ['./currency-form.component.css'],
  imports: [FormsModule, CommonModule]
})
export class CurrencyFormComponent {
  @Output() currencyValueFetched = new EventEmitter<void>();

  currency: string = '';
  name: string = '';
  value: number | null = null;
  error: string | null = null;

  constructor(private currencyService: CurrencyService) { }

  getCurrencyValue() {
    this.error = null;
    const request: CurrencyRequest = { currency: this.currency, name: this.name };
    this.currencyService.getCurrencyValue(this.currency, this.name, request).subscribe(
      (response) => {
        this.value = response.value;
        this.currencyValueFetched.emit();
        this.clearFormFields();

        setTimeout(() => {
          this.value = null;
        }, 3000);
      },
      (error) => {
        this.error = 'Error fetching currency value. Please try again.';
        console.error('Error:', error);
      }
    );
  }
  clearFormFields() {
    this.currency = '';
    this.name = '';
  }
}
