import { AreaTematicaPK } from './AreaTematicaPK';
import { SiglaTematica } from './SiglaTematica';

export class AreaTematica {
  Id: AreaTematicaPK;
  Nombre: string;
  Descripcion: string;
  siglaTematica: SiglaTematica;
}
