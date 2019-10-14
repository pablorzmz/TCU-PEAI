import {MaterialPK} from './MaterialPK';
import {UsuarioPK} from './UsuarioPK';

export class UsuarioMaterialComentaPK {
  material: MaterialPK;
  usuario: UsuarioPK;
  fecha: Date;
}
