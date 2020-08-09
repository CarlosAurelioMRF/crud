import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

import { Pessoa } from '../interface/pessoa.interface';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private baseUrl = `${environment.base}/api/pessoas`;

  constructor(private http: HttpClient) { }

  findAll(): Observable<Pessoa[]> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa("admin:admin")}`
      })
    };

    return this.http.get<Pessoa[]>(this.baseUrl, httpOptions);
  }

  findById(id: string): Observable<Pessoa> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa("admin:admin")}`
      })
    };

    return this.http.get<Pessoa>(`${this.baseUrl}/${id}`, httpOptions);
  }

  save(pessoa: Pessoa): Observable<Pessoa> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa("admin:admin")}`
      })
    };

    if (pessoa.id)
      return this.http.put<Pessoa>(`${this.baseUrl}/${pessoa.id}`, pessoa, httpOptions);

    return this.http.post<Pessoa>(this.baseUrl, pessoa, httpOptions);
  }

  delete(pessoa: Pessoa): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Basic ${btoa("admin:admin")}`
      })
    };

    return this.http.delete<any>(`${this.baseUrl}/${pessoa.id}`, httpOptions);
  }
}
