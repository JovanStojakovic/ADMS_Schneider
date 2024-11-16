import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateProcessingComponent } from './update-processing.component';

describe('UpdateProcessingComponent', () => {
  let component: UpdateProcessingComponent;
  let fixture: ComponentFixture<UpdateProcessingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateProcessingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProcessingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
