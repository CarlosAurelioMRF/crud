import { Component, HostListener, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  opened = true;

  @ViewChild('sidenav') sidenav: MatSidenav;

  ngOnInit() {
    this.opened = window.innerWidth >= 768;
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.opened = window.innerWidth >= 768;
  }

  isBiggerScreen() {
    const width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
    if (width < 768) {
      return true;
    } else {
      return false;
    }
  }
}
