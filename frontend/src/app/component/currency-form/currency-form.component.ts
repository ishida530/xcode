import { Component, EventEmitter, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CurrencyService, CurrencyRequest } from '../../service/currency.service';
import { of } from 'rxjs';
import { switchMap, delay } from 'rxjs/operators';

@Component({
  selector: 'app-currency-form',
  standalone: true,
  templateUrl: './currency-form.component.html',
  styleUrls: ['./currency-form.component.css'],
  imports: [ReactiveFormsModule, CommonModule]
})
export class CurrencyFormComponent {
  @Output() currencyValueFetched = new EventEmitter<void>();

  currencyForm: FormGroup;
  value: number | null = null;
  error: string | null = null;

  constructor(private currencyService: CurrencyService) {
    this.currencyForm = new FormGroup({
      currency: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required)
    });
  }

  getCurrencyValue() {
    if (this.currencyForm.invalid) {
      this.error = 'Please fill in all required fields.';
      return; 
    }

    this.error = null;
    const request: CurrencyRequest = {
      currency: this.currencyForm.value.currency,
      name: this.currencyForm.value.name
    };

    this.currencyService.getCurrencyValue(request.currency, request.name, request).pipe(
      switchMap((response) => {
        this.value = response.value;
        this.currencyValueFetched.emit();
        this.clearFormFields();

        return of(null).pipe(delay(3000));
      })
    ).subscribe(() => {
      this.value = null;
    },
    (error) => {
      this.error = 'Error fetching currency value. Please try again.';
      console.error('Error:', error);
    });
  }

  clearFormFields() {
    this.currencyForm.reset();
  }
}
