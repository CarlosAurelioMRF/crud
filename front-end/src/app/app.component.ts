import { Component, OnInit } from '@angular/core';
import { SourceService } from 'src/app/common/service/source.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private sourceService: SourceService) {
  }

  ngOnInit() {
  }

  public abrirGithub(): void {
    this.sourceService.find().subscribe(({ url }) => window.open(url, "_blank"));
  }
}
