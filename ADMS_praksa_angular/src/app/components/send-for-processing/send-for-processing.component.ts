import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProblemServiceService } from 'src/app/shared/problem-services/problem-service.service';
import { Problem } from '../problems/problem';
import { FormControl, FormGroup } from '@angular/forms';
import { ProcessRequest } from './process-request';

@Component({
  selector: 'app-send-for-processing',
  templateUrl: './send-for-processing.component.html',
  styleUrls: ['./send-for-processing.component.css']
})
export class SendForProcessingComponent implements OnInit {

  id!:string
  problem!: Problem
  processForm: FormGroup = new FormGroup({})
  processRequest!: ProcessRequest
  showAlert: boolean = false;
  showAlertSuccess: boolean = false
 
  constructor(private httpClient: HttpClient, private problemService: ProblemServiceService, private route: ActivatedRoute, private router: Router) { 
    this.processRequest ={
      brWorkers: 0,
      predictedDate: new Date,
      predictedTime:  "",
    }
  }



  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.id = params.get('id')!;
    });
    this.processForm = new FormGroup({
      brWorkers: new FormControl(""),
      predictedDate: new FormControl(""),
      predictedTime: new FormControl(""),

    })
  }
  getProblem(){
    this.problemService.getProblemById(this.id).subscribe(
      response => {
        this.problem = response;
      }
    );
  }
  process() {
    this.processRequest.brWorkers = Number( this.processForm.get('brWorkers')?.value);
    this.processRequest.predictedDate = this.processForm.get('predictedDate')?.value;

    // Directly use the time string from the form control
    const timeString = this.processForm.get('predictedTime')?.value;

    // Check if the time string is in the expected format (HH:mm) and convert it to the desired format if necessary
    const [hours, minutes] = timeString.split(':');
    const formattedTime = `${hours}:${minutes}:00`;

    this.processRequest.predictedTime = formattedTime;

    this.problemService.processProblem(this.processRequest, this.id)
        .subscribe(response => {
            this.showAlertSuccess = true;
        }, (error: HttpErrorResponse) => {
            if (error) {
                this.showAlert = true;
            }
        });
        this.router.navigate(['/operatorPage'])
}

closeAlert() {
    this.showAlert = false;
}

closeAlertSuccess() {
    this.showAlertSuccess = false;
}


}
