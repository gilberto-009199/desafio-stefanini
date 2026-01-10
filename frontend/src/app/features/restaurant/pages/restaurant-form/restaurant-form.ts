import { CommonModule } from '@angular/common';
import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import * as L from 'leaflet';
import { Button } from 'primeng/button';
import { Card } from 'primeng/card';
import { InputMask } from 'primeng/inputmask';
import { InputNumber } from 'primeng/inputnumber';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.html',
  imports:[
    CommonModule,
    ReactiveFormsModule,
    Card,
    InputNumber,
    InputMask,
    Button
  ],
  styleUrls: ['./restaurant-form.css']
})
export class RestaurantForm implements OnInit, AfterViewInit {

  restaurantForm!: FormGroup;
  map!: L.Map;
  marker!: L.Marker;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.restaurantForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      cep: ['', Validators.required],
      latitude: [null, Validators.required],
      longitude: [null, Validators.required]
    });
  }

  ngAfterViewInit(): void {
    this.initMap();
  }

  private initMap(): void {
    // Coordenadas iniciais (ex: Brasil)
    const initialCoords: L.LatLngExpression = [-23.545143, -766.758489];

    this.map = L.map('map').setView(initialCoords, 0.4)

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors'
    }).addTo(this.map);

    // Evento de clique para capturar lat/lng
    this.map.on('click', (e: L.LeafletMouseEvent) => {
      const { lat, lng } = e.latlng;
      
      this.updateMarker(lat, lng);
      
      // Atualiza o formulário
      this.restaurantForm.patchValue({
        latitude: lat,
        longitude: lng
      });
    });
  }

  private updateMarker(lat: number, lng: number) {
    if (this.marker) {
      this.marker.setLatLng([lat, lng]);
    } else {
      this.marker = L.marker([lat, lng]).addTo(this.map);
    }
  }

  onSubmit() {
    console.log('Dados do Restaurante:', this.restaurantForm.value);
  }
}