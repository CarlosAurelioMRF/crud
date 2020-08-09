import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SourceService {

  private baseUrl = `${environment.base}/source`;

  constructor(private http: HttpClient) { }

  find(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }
}
