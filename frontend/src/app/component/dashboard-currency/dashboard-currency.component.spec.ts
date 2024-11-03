import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardCurrencyComponent } from './dashboard-currency.component';

describe('DashboardCurrencyComponent', () => {
  let component: DashboardCurrencyComponent;
  let fixture: ComponentFixture<DashboardCurrencyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardCurrencyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardCurrencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
