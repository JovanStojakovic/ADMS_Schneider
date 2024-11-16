import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SendForProcessingComponent } from './send-for-processing.component';

describe('SendForProcessingComponent', () => {
  let component: SendForProcessingComponent;
  let fixture: ComponentFixture<SendForProcessingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SendForProcessingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SendForProcessingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
