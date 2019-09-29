import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Institucion} from '../../data/schema/Institucion';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InstitucionService {
  private urlEndPoint2 = 'http://localhost:8080/api/instituciones2';
  private urlEndPoint = 'http://localhost:8080/api/instituciones';
  // private httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  getInstituciones2(): Observable<string[]> {
    return this.http.get(this.urlEndPoint2).pipe(
      map((response) => response as string[] )
    );
  }

  getInstituciones(): Observable<Institucion[]> {
    return this.http.get(this.urlEndPoint2).pipe(
      map((response) => response as Institucion[] )
    );
  }

}
