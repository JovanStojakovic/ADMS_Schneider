import { Component, OnInit } from '@angular/core';
import { Problem } from './problem';
import { ProblemServiceService } from 'src/app/shared/problem-services/problem-service.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Stats } from 'fs';

@Component({
  selector: 'app-problems',
  templateUrl: './problems.component.html',
  styleUrls: ['./problems.component.css']
})
export class ProblemsComponent implements OnInit {

  //ovo je lista 
  pendingProblems!: Problem[]
  processedProblems!: Problem[]
  completedProblems!: Problem[]
  processForm: FormGroup = new FormGroup({})
  constructor(private problemService: ProblemServiceService) {
    
   }

  ngOnInit(): void {
    this.getPendingProblems()
    this.getProcessedProblems()
    this.getCompletedProblems()

    this.processForm = new FormGroup({
      place: new FormControl(""),
      street: new FormControl(""),
      number: new FormControl(""),
      problemType: new FormControl("")
    })
  }
  getPendingProblems(){
    this.problemService.getProblems().subscribe(
      response => {
        this.pendingProblems = response
      }
    )
  }
  getProcessedProblems(){
    this.problemService.getProblemsP().subscribe(
      response => {
        this.processedProblems = response
      }
    )
  }
  getCompletedProblems(){
    this.problemService.getProblemC().subscribe(
      response => {
        this.completedProblems = response
      }
    )
  }
  complete(id: number){
    this.problemService.completeProblem(id.toString()).subscribe(
      response => {
        console.log(response)
      }
    )
  }

  filter(status: string){
    var place = this.processForm.get('place')?.value;
    var street = this.processForm.get('street')?.value;
    var number = this.processForm.get('number')?.value;
    var problemType = this.processForm.get('problemType')?.value;

    this.problemService.getFilteredProblems(status, place, street, number, problemType)
        .subscribe(response => {
          if(status == "PENDING"){
            this.pendingProblems = response
          }if(status == "PROCESSED"){
            this.processedProblems = response
          }if(status == "COMPLETED"){
            this.completedProblems = response
          }

          
        })
  }
  getColor(value: String): string {
    switch (value) {
      case "ELECTIRICITY":
        return '#F5FBA9';
      case "WEATHER":
        return '#A9FBEA';
      case "OTHER":
        return '#DCE0F6';
      default:
        return 'white'; 
    }
  }
}
