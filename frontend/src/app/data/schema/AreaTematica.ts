import { AreaTematicaPK } from './AreaTematicaPK';
import { SiglaTematica } from './SiglaTematica';
import {Institucion} from './Institucion';

export class AreaTematica {
  id: AreaTematicaPK;
  nombre: string;
  descripcion: string;
  siglaTematica: SiglaTematica;
  foto: string;
  institucion: Institucion;
}
