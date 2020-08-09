package br.com.carlosaurelio.crud.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import br.com.carlosaurelio.crud.entity.Pessoa;

public class PessoaToCreateDto {

    @CPF(message = "CPF deve ser valido")
    @NotBlank(message = "CPF é obrigatório")
	private String cpf;

	@NotBlank(message = "Nome é obrigatório")
    private String nome;

	@Email(message = "Email deve ser valido")
    private String email;

    @NotNull(message = "Data de Nascimento é obrigatória")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataNascimento;

    private String sexo;
    private String naturalidade;
    private String nacionalidade;

    public Pessoa transformaParaObjeto() {
        String inscricaoCpf = cpf != null ? cpf.replaceAll("[^0-9]", "") : "";

        return new Pessoa(nome, sexo, email, dataNascimento, naturalidade, nacionalidade, inscricaoCpf);
    }

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
}
