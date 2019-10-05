import {CursoPK} from './CursoPK';
import {AreaTematica} from './AreaTematica';

export class Curso {
  id: CursoPK;
  nombre: string;
  descripcion: string;
  foto: string;
  areaTematica: AreaTematica;
}
