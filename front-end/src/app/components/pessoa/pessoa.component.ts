import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { Pessoa } from 'src/app/common/interface/pessoa.interface';
import { PessoaService } from 'src/app/common/service/pessoa.service';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.scss']
})
export class PessoaComponent implements OnInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;

  public displayedColumns: string[] = ['nome', 'cpf', 'email', 'dataNascimento', 'sexo', 'naturalidade', 'nacionalidade', 'action'];
  public pessoas: Pessoa[] = [];
  public dataSource: MatTableDataSource<Pessoa>;

  constructor(private pessoaService: PessoaService) { }

  ngOnInit(): void {
    this.pessoaService.findAll().subscribe((pessoas: Pessoa[]) => {
      this.pessoas = pessoas;

      this.dataSource = new MatTableDataSource<Pessoa>(pessoas);
      this.dataSource.paginator = this.paginator;
    });
  }

  public obterDataFormatada(date: Date): string {
    return moment(date).locale('pt-br').format('L');
  }

  public remover(index: number, pessoa: Pessoa): void {
    if (window.confirm('Você tem certeza que deseja deletar?')) {
      this.pessoaService.delete(pessoa).subscribe(() => {
        const data = this.dataSource.data;
        data.splice(index, 1);
        this.dataSource.data = data;
      });
    }
  }
}
