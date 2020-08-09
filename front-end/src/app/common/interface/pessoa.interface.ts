export interface Pessoa {
  id?: string;
  nome: string;
  sexo?: string;
  email?: string;
  dataNascimento: Date;
  naturalidade?: string;
  nacionalidade?: string;
  cpf: string;
  createAt?: Date;
  updateAt?: Date;
}