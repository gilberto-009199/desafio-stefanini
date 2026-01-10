import { CommonModule } from '@angular/common';
import { Component, OnInit, Signal, signal } from '@angular/core';
import { BadgeModule } from 'primeng/badge';
import { TableModule } from 'primeng/table';
import { Restaurant } from '../../models/restaurant';
import { Tag } from 'primeng/tag';
import { Rating } from 'primeng/rating';
import { ButtonModule } from 'primeng/button';
import { SelectButton } from 'primeng/selectbutton';
import { FormsModule } from '@angular/forms';
import { DataView } from 'primeng/dataview';
import { Card } from 'primeng/card';
import { RouterLink } from "@angular/router";
import { Toolbar } from "primeng/toolbar";
import { IconField } from 'primeng/iconfield';
import { InputIcon } from 'primeng/inputicon';
import { SplitButton } from 'primeng/splitbutton';
import { Fieldset } from 'primeng/fieldset';
import { TreeTableModule } from 'primeng/treetable';
import { TreeNode } from 'primeng/api';
import { Divider } from "primeng/divider";
import { RestaurantService } from '../../services/restaurant';
import { SkeletonModule } from 'primeng/skeleton';

@Component({
  selector: 'app-restaurant-list',
  imports: [
    Card,
    ButtonModule,
    CommonModule,
    FormsModule,
    RouterLink,
    Toolbar,
    Fieldset,
    Divider,
    TreeTableModule,
    SkeletonModule,
    Divider
],
  templateUrl: './restaurant-list.html',
  styleUrl: './restaurant-list.css',
})
export class RestaurantList implements OnInit{

  isLoading = true;
  restaurant = signal<TreeNode[]>([]);
  
  constructor(
    private restauranteService : RestaurantService
  ){}

  ngOnInit(){
    
    this.loadRestaurant()

  }

  loadRestaurant(){
    this.isLoading = true;
    this.restauranteService
    .getRestaurants()
    .subscribe(listRstaurant =>{
      this.isLoading = false;
      this.restaurant.set(
        listRstaurant.map(restaurant =>({
          data: restaurant,
          children: [], 
          expanded: false
        }))
      )
      
    })  
  }
}
