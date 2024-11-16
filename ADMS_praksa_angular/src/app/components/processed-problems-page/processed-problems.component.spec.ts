import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedProblemsComponent } from './processed-problems.component';

describe('ProcessedProblemsComponent', () => {
  let component: ProcessedProblemsComponent;
  let fixture: ComponentFixture<ProcessedProblemsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessedProblemsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedProblemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
