import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet, RouterLink, Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { Menubar } from "./components/menubar/menubar";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Menubar],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  

}
