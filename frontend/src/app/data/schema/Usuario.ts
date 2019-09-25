import {UsuarioPK} from './UsuarioPK';

export class Usuario {
  usuarioPK: UsuarioPK;
  correo: string;
  nombre: string;
  apellidos: string;
  fechaNacimiento: string;
  salt: string;
  sexo: string;
  telefono: number;
  foto: string;
  habilitado: boolean;

  constructor() {
    this.usuarioPK = new UsuarioPK();
  }
}
