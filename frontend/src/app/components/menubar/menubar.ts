import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink, RouterLinkActive } from '@angular/router';
import { Accordion, AccordionModule } from 'primeng/accordion';
import { MenuItem } from 'primeng/api';
import { Breadcrumb } from 'primeng/breadcrumb';
import { Button } from 'primeng/button';
import { Divider } from 'primeng/divider';
import { DrawerModule } from 'primeng/drawer';
import { Menu } from 'primeng/menu';
import { PanelMenuModule } from 'primeng/panelmenu';
import { Toolbar } from 'primeng/toolbar';
import { filter } from 'rxjs';

@Component({
  selector: 'app-menubar',
  imports: [
    PanelMenuModule,
    Button,
    DrawerModule,
    Toolbar,
    Breadcrumb,
    Divider,
    AccordionModule
  ],
  templateUrl: './menubar.html',
  styleUrl: './menubar.css',
})
export class Menubar implements OnInit{

  isDarkMode = false;
  drawerVisible = false;
  
  home: MenuItem = { icon: 'pi pi-home', routerLink: '/' };
  breadcumbsItems = [];
  menuItems: MenuItem[] = [ 
      { 
        label: 'Início', 
        icon: 'pi pi-home', 
        command: () => {
          this.drawerVisible = false;
          this.router.navigate(['/']);
        }
      },
      { 
        label: 'Restaurantes', 
        icon: 'pi pi-shop', 
        command: () => {
          this.drawerVisible = false;
          this.router.navigate(['/restaurant']);
        },
        items:[
          { 
            label: 'Novo Restaurante', 
            icon: 'pi pi-plus', 
            command: () => {
              this.drawerVisible = false;
              this.router.navigate(['/restaurant/new']);
            }
          }
        ]
    }
  ];
  

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}


  toggleDarkMode(){
    
    const element = document.querySelector('html');
    
    element?.classList.toggle('my-app-dark');

  }

  

  

  ngOnInit() {
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe(() => {
      
      let currentRoute = this.activatedRoute.root;

      while (currentRoute.firstChild) {
        currentRoute = currentRoute.firstChild;
      }
      
      const breadcrumbData = currentRoute.snapshot.data['breadcrumb'] || [];
      
      this.breadcumbsItems = breadcrumbData.map((item: any) => ({
        label: item.label,
        routerLink: item.path
      }));

    });
  }
}
