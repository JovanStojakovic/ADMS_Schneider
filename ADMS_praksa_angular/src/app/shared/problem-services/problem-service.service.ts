import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ProcessRequest } from 'src/app/components/send-for-processing/process-request';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProblemServiceService {

  private apiUrl = environment.apiUrl
  constructor(private httpClient: HttpClient) { }

  getProblems(){
    return this.httpClient.get<any>(`${this.apiUrl}/problem/pending`)
  }
  getProblemsP (){
    return this.httpClient.get<any>(`${this.apiUrl}/problem/processed`)
  }
  getProblemC(){
    return this.httpClient.get<any>(`${this.apiUrl}/problem/completed`)
  }
  getProblemById(id: string){
    return this.httpClient.get<any>(`${this.apiUrl}/problem/` + id)
  }
  processProblem(processRequest: ProcessRequest, id: string): Observable <any>{
    return this.httpClient.put(`${this.apiUrl}/operator/addProcessed/` + id, processRequest, {responseType: 'text'})
   }
  getProblemProcessedById(id: string){
    return this.httpClient.get<any>(`${this.apiUrl}/problem/processed/` + id)
  }
  completeProblem(id: string): Observable <any>{
    return this.httpClient.put(`${this.apiUrl}/operator/completeProblem/` + id, {responseType: 'text'})
   }
   getFilteredProblems(status?: string, place?: string, street?: string, number?: string, problemType?: string){
    let url = `${this.apiUrl}/problem/all?`;

    if (status) {
        url += `status=${status}&`;
    }
    if (place) {
        url += `place=${place}&`;
    }
    if (street) {
        url += `street=${street}&`;
    }
    if (number) {
        url += `number=${number}&`;
    }
    if (problemType) {
        url += `problemType=${problemType}&`;
    }

    // Remove the trailing '&' if there are any query parameters
    if (url.endsWith('&')) {
        url = url.slice(0, -1);
    }

    return this.httpClient.get<any>(url);
  }
}
