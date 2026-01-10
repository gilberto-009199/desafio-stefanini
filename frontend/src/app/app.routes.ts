import { Routes } from '@angular/router';
import { RestaurantList } from './features/restaurant/pages/restaurant-list/restaurant-list';

import { RestaurantForm } from './features/restaurant/pages/restaurant-form/restaurant-form';
import { Catalog } from './features/catalog/catalog';

export const routes: Routes = [

  {
    path: "",
    component: Catalog,
    data: { 
      breadcrumb: [{ label: 'Início', path: '/' }] 
    }
  },
  {
    path: "restaurant",
    data: { 
      breadcrumb: [{ label: 'Restaurantes', path: '/restaurant' }] 
    },
    children: [
      { path: "", component: RestaurantList },
      { 
        path: "new", 
        component: RestaurantForm, 
        data: { 
          breadcrumb: [
            { label: 'Restaurantes', path: '/restaurant' }, 
            { label: 'Novo Restaurante', path: '/restaurant/new' }
          ] 
        } 
      },
      { 
        path: "edit/:id", 
        component: RestaurantForm, 
        data: { 
          breadcrumb: [
            { label: 'Restaurantes', path: '/restaurant' }, 
            { label: 'Editar Restaurante', path: '' } 
          ] 
        } 
      }
    ]
  }

];
