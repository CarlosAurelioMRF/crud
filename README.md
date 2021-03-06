# CRUD Spring

Cadastro de pessoa utilizando Java Spring Boot, MongoDB e Angular.

### O que temos na aplicação

A aplicação possuí um cadastro de pessoa com basic authentication, usando Spring Boot para a construção da API Rest, conta com um banco de dados MongoDB para armazenamento dos dados em nuvem do [mLab](https://mlab.com/), no front-end está sendo utilizado o Angular 9 e está dockerizada com Docker Compose.

### Como usar

O projeto está publicado no Heroku acessivel através da URL
```
https://crud-spring-pessoa.herokuapp.com/
```

### Dados de Autenticação
```
Usuário: admin
Senha: admin
```

### Instalação

É possível baixar a imagem do [Docker Hub](https://hub.docker.com/r/carlosaurelio/crud-spring)
```
docker run -p 8080:8080 --name crud-spring -d carlosaurelio/crud-spring
```

## Autor

* **Carlos Aurélio** - [Linkedin](https://www.linkedin.com/in/carlos-aurelio-meireles-ribeiro-filho/)
