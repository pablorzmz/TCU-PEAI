import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Material} from '../../../../data/schema/Material';
import {MaterialService} from '../../../../data/services/material.service';

@Component({
  selector: 'app-ver-material',
  templateUrl: './ver-material.component.html',
  styleUrls: ['./ver-material.component.css']
})
export class VerMaterialComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private materialService: MaterialService, private router: Router) { }

  material: Material;

  // Es el material que será mostrado
  idMaterial: string;

  // Es la subseccion a la que pertenece
  idSubSeccion: number;

  // Es la ruta para obtener el archivo
  ruta: string;

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
        // Se obtiene los parametros del id del material
        this.idMaterial = params.get('idMaterial');
        this.idSubSeccion = +params.get('idSubSeccion');

        const solicitud = this.materialService.obtenerMaterial(this.idMaterial, this.idSubSeccion).subscribe(
          response => {
            this.material = response;
            // Se espera que las conulas se hagan con esta ruta
            this.ruta =  `${this.material.url}`
            // Se espera que las conulas se hagan con esta ruta
            console.log(this.material);
            solicitud.unsubscribe();
          },
          error => {
            // Si falla se devuelve a la vista del curso
            let url;
            this.activatedRoute.parent.url.subscribe(res => {
              url = res.toString().replace(',', '/');
              this.router.navigateByUrl(url);
            });
          });
      });
  }
}
