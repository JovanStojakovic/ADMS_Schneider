import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent } from './components/login/login.component';
import { OperatorPageComponent } from './components/operatorPage/operator-page.component';
import { UserPageComponent } from './components/user-page/user-page.component';
import { ProcessedProblemsComponent } from './components/processed-problems-page/processed-problems.component';
import { SendForProcessingComponent } from './components/send-for-processing/send-for-processing.component';
import { UpdateProcessingComponent } from './components/update-processing/update-processing.component';

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "operatorPage", component: OperatorPageComponent},
  {path: "", component: LoginComponent},
  {path: "userPage", component: UserPageComponent},
  {path: "problemProcessed", component: ProcessedProblemsComponent},
  {path: "sendForProcessing/:id", component: SendForProcessingComponent},
  {path: "updateProcessing/:id", component: UpdateProcessingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
