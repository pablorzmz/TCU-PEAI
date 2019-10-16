import {Component, Input, OnInit} from '@angular/core';
import {Material} from '../../../../data/schema/Material';

@Component({
  selector: 'app-ver-material',
  templateUrl: './ver-material.component.html',
  styleUrls: ['./ver-material.component.css']
})
export class VerMaterialComponent implements OnInit {

  constructor() { }

  // Es el material que será mostrado
  @Input() material: Material;

  titulo = 'Electromagnetismo';


  ngOnInit() {
  }
}
