import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { OperatorPageComponent } from './components/operatorPage/operator-page.component';
import { ProblemsComponent } from './components/problems/problems.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { UserPageComponent } from './components/user-page/user-page.component';
import { UserProblemsComponent } from './components/user-problems/user-problems.component';
import { ProcessedProblemsComponent } from './components/processed-problems-page/processed-problems.component';
import { ProcessedProblemComponent } from './components/processed-problem/processed-problem.component';
import { SendForProcessingComponent } from './components/send-for-processing/send-for-processing.component';
import { UpdateProcessingComponent } from './components/update-processing/update-processing.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './shared/interceptor';
import { NavbarComponent } from './navbar/navbar.component';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OperatorPageComponent,
    ProblemsComponent,
    UserPageComponent,
    UserProblemsComponent,
    ProcessedProblemsComponent,
    ProcessedProblemComponent,
    SendForProcessingComponent,
    UpdateProcessingComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: JwtInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
