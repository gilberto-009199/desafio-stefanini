import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { Restaurant } from '../models/restaurant';

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {
  
  private readonly API_URL = `${environment.apiUrl}/restaurant`; 

  constructor(
    private http: HttpClient
  ){}
  
  getRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.API_URL);
  }
}