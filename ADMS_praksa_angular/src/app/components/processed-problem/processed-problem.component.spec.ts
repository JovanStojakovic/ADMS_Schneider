import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProcessedProblemComponent } from './processed-problem.component';

describe('ProcessedProblemComponent', () => {
  let component: ProcessedProblemComponent;
  let fixture: ComponentFixture<ProcessedProblemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProcessedProblemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProcessedProblemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
