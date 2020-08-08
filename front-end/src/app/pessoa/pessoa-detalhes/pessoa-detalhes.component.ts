import { Component, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { Router, Params, ActivatedRoute } from '@angular/router';
import { PessoaService } from 'src/app/service/pessoa.service';
import * as moment from 'moment';
import { Pessoa } from 'src/app/interface/pessoa.interface';

@Component({
  selector: 'app-pessoa-detalhes',
  templateUrl: './pessoa-detalhes.component.html',
  styleUrls: ['./pessoa-detalhes.component.scss']
})
export class PessoaDetalhesComponent implements OnInit {

  @ViewChild('resetPessoaForm', { static: true }) resetPessoaForm;

  public pessoaForm: FormGroup;

  constructor(
    public fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private ngZone: NgZone,
    private pessoaService: PessoaService,
  ) { }


  ngOnInit() {
    this.configurarFormulario();

    this.route.params.subscribe((params: Params) => {
      if (params.id)
        this.pessoaService.findById(params.id).subscribe((pessoa: Pessoa) => this.configurarFormulario(pessoa));
    });
  }

  /* Get errors */
  public handleError = (controlName: string, errorName: string) => {
    return this.pessoaForm.controls[controlName].hasError(errorName);
  }

  public submitPessoaForm() {
    if (this.pessoaForm.valid) {
      const pessoa: Pessoa = {
        ...this.pessoaForm.value,
        dataNascimento: moment(this.pessoaForm.value.dataNascimento).locale('pt-br').toDate()
      }

      this.pessoaService.save(pessoa).subscribe(() => this.ngZone.run(() => this.router.navigateByUrl('/pessoas')));
    }
  }

  private configurarFormulario(pessoa?: Pessoa): void {
    this.pessoaForm = this.fb.group({
      id: [pessoa ? pessoa.id : ''],
      nome: [pessoa ? pessoa.nome : '', [Validators.required]],
      cpf: [pessoa ? pessoa.cpf : '', [Validators.required]],
      dataNascimento: [pessoa ? pessoa.dataNascimento : '', [Validators.required]],
      email: [pessoa ? pessoa.email : '', [Validators.email]],
      sexo: [pessoa ? pessoa.sexo : ''],
      naturalidade: [pessoa ? pessoa.naturalidade : ''],
      nacionalidade: [pessoa ? pessoa.nacionalidade : '']
    })
  }

}
