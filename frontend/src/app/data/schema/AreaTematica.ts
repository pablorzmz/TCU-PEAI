import { AreaTematicaPK } from './AreaTematicaPK';
import { SiglaTematica } from './SiglaTematica';

export class AreaTematica {
  id: AreaTematicaPK;
  nombre: string;
  descripcion: string;
  siglaTematica: SiglaTematica;
  foto: string;
}
