import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Institucion} from '../../data/schema/Institucion';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InstitucionService {
  private urlEndPoint = 'http://localhost:8080/api/instituciones';

  constructor(private http: HttpClient) { }

  getInstituciones2(page: number): Observable<any> {
    return this.http.get(this.urlEndPoint + '/page/' + page);
  }

}
