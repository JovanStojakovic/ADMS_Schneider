import { Component, OnInit } from '@angular/core';
import { ProblemUser } from './problemUser';
import { ProblemServiceService } from 'src/app/shared/problem-services/problem-service.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Problem } from '../problems/problem';

@Component({
  selector: 'app-user-problems',
  templateUrl: './user-problems.component.html',
  styleUrls: ['./user-problems.component.css']
})
export class UserProblemsComponent implements OnInit {
  processedUserProblems!: ProblemUser[]
  processForm: FormGroup = new FormGroup({})
  constructor(private problemService: ProblemServiceService) { }

  ngOnInit(): void {
    this.getProcessedUserProblems()

    this.processForm = new FormGroup({
      place: new FormControl(""),
      street: new FormControl(""),
      number: new FormControl("")
      
    })
  }
  getProcessedUserProblems(){
    this.problemService.getProblemsP().subscribe(
      response => {
        this.processedUserProblems = response
      }
    )
  }
  filter(status: string){
    var place = this.processForm.get('place')?.value;
    var street = this.processForm.get('street')?.value;
    var number = this.processForm.get('number')?.value;
    

    this.problemService.getFilteredProblems(status, place, street, number)
        .subscribe(response => {
          
            this.processedUserProblems = response
          
        })
  }

}
