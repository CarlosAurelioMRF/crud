import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { PessoaDetalhesComponent } from './pessoa/pessoa-detalhes/pessoa-detalhes.component';
import { PessoaComponent } from './pessoa/pessoa.component';

const routes: Routes = [
  {
    path: 'pessoas',
    component: PessoaComponent,
    data: { title: 'Lista de Pessoas' }
  },
  {
    path: 'pessoas/adicionar',
    component: PessoaDetalhesComponent,
    data: { title: 'Adicionar de Pessoa' }
  },
  {
    path: 'pessoas/editar/:id',
    component: PessoaDetalhesComponent,
    data: { title: 'Editar de Pessoa' }
  },
  {
    path: '',
    redirectTo: '/pessoas',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
