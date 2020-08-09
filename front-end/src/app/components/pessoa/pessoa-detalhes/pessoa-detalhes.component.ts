import { Component, NgZone, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import * as moment from 'moment';
import { Pessoa } from 'src/app/common/interface/pessoa.interface';
import { PessoaService } from 'src/app/common/service/pessoa.service';
import { Validacoes } from 'src/app/common/validation/valicacoes';
import { HttpErrorResponse } from '@angular/common/http';

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

      this.pessoaService.save(pessoa).subscribe(
        () => this.ngZone.run(() => this.router.navigateByUrl('/pessoas')),
        ({ error }) => {
          if (error && error.errors) {
            const validationErrors = error.errors;

            validationErrors.forEach(erro => {
              this.pessoaForm.controls[erro.propriedade].setErrors({
                serverError: erro.mensagem
              });
            });
          }
        });
    }
  }

  private configurarFormulario(pessoa?: Pessoa): void {
    this.pessoaForm = this.fb.group({
      id: [pessoa ? pessoa.id : ''],
      nome: [pessoa ? pessoa.nome : '', [Validators.required]],
      cpf: [pessoa ? pessoa.cpf : '', [Validators.required, Validacoes.ValidaCpf]],
      dataNascimento: [pessoa ? pessoa.dataNascimento : '', [Validators.required]],
      email: [pessoa ? pessoa.email : '', [Validators.email]],
      sexo: [pessoa ? pessoa.sexo : ''],
      naturalidade: [pessoa ? pessoa.naturalidade : ''],
      nacionalidade: [pessoa ? pessoa.nacionalidade : '']
    })
  }

}
