/**
 * En esta clase se definen constantes importantes en la lógica de negocio como
 * Perfiles con sus nombres
 * Permisos con sus nombres
 */
export class CONSTANTES {
  // Perfiles
  public readonly ROLE_PROFESOR: any = {ID: 1, NOMBRE: 'ROLE_Profesor'};
  public readonly ROLE_ESTUDIANTE: any = {ID: 2, NOMBRE: 'ROLE_Estudiante'};

  // Permisos
  public readonly PERMISO_IMPARTIR_UN_CURSO: any = { ID: 1, NOMBRE: 'Impartir un curso' };
  public readonly PERMISO_RECIBIR_UN_CURSO: any = { ID: 2, NOMBRE: 'Recibir un curso' };
}

