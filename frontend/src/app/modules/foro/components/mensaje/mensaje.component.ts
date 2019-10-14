import {Component, Input, OnInit} from '@angular/core';
import {UsuarioMaterialComenta} from '../../../../data/schema/UsuarioMaterialComenta';
import {MaterialPK} from '../../../../data/schema/MaterialPK';
import {UsuarioPK} from '../../../../data/schema/UsuarioPK';
import {UsuarioMaterialComentaPK} from '../../../../data/schema/UsuarioMaterialComentaPK';
import {ForoService} from '../../../../data/services/foro.service';

@Component({
  selector: 'app-mensaje',
  templateUrl: './mensaje.component.html',
  styleUrls: ['./mensaje.component.css']
})
export class MensajeComponent implements OnInit {

  usuarioMaterialComenta: UsuarioMaterialComenta = new UsuarioMaterialComenta();

  usuarioMaterialComenta2: UsuarioMaterialComenta;

  fechaComentario: string;

  constructor(private foroService: ForoService) { }

  ngOnInit() {
    const materialPK = new MaterialPK();
    materialPK.nombre = '1';
    materialPK.subSeccionMaterialId = 1;

    const usuarioPK = new UsuarioPK();
    usuarioPK.nombreUsuario = 'steveen';

    const usuarioMaterialComentaPK = new UsuarioMaterialComentaPK();
    usuarioMaterialComentaPK.usuario = usuarioPK;

    usuarioMaterialComentaPK.material = materialPK;

    this.usuarioMaterialComenta.id = usuarioMaterialComentaPK;

    this.usuarioMaterialComenta.textoComentario = 'Madre miaaaa';

    this.usuarioMaterialComenta.visible = true;

    this.foroService.setComentarioMaterial(this.usuarioMaterialComenta).subscribe(
      res => {

        this.usuarioMaterialComenta2 = res;
        this.getFecha();
        console.log(res);

      }, err => {

        console.log(err);
    });
  }

  getFecha(): any {
       const fecha = new Date(this.usuarioMaterialComenta2.id.fecha);
    // tslint:disable-next-line:max-line-length
       this.fechaComentario = `${fecha.getDate()} de ${this.getMes(fecha.getMonth())} de ${fecha.getFullYear()}, ${this.getHora(fecha.getHours(), fecha.getMinutes())}`;
  }

  getMes(mes: number): string {
    switch (mes) {
      case 0: return 'Enero';
              break;
      case 1: return 'Febrero';
              break;
      case 2: return 'Marzo';
              break;
      case 3: return 'Abril';
              break;
      case 4: return 'Mayo';
              break;
      case 5: return 'Junio';
              break;
      case 6: return 'Julio';
              break;
      case 7: return 'Agosto';
              break;
      case 8: return 'Septiembre';
              break;
      case 9: return 'Octubre';
              break;
      case 10: return 'Noviembre';
               break;
      case 11: return 'Diciembre';
               break;
    }
  }

  getHora(hora: number, minutos: number): string {
    // Indica que medio dia es a las 12
    const medioDia = 12;

    // Es la hora en formato normal
    const horaNormal = hora % medioDia || medioDia;

    // Son los minutos en formato normal
    const minutosNormal = minutos >= 10 ? minutos : ('0' + minutos);

    // indica si es am o pm
    const tiempoNormal = hora < medioDia ? 'am' : 'pm';

    // Se retorna la hora en formato normal
    return `${horaNormal}:${minutosNormal} ${tiempoNormal}`;
  }

}
