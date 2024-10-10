package br.com.projeto.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// O Entity especifica que a classe é uma entidade. Uma entidade é uma classe que representa uma tabela no banco de dados. 
// Cada instância de uma entidade corresponde a uma linha na tabela.
@Entity
@Table(name = "pessoas")
public class Pessoa {
  // O Id especifica a chave primária de uma entidade. A chave primária é uma propriedade (ou propriedades) 
  // cujos valores garantem a unicidade de cada linha de uma tabela de banco de dados.
  @Id
  // O GeneratedValue fornece a estratégia de geração de valores para o valor da chave primária.
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int codigo;
  private String nome;
  private int idade;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }
  
}
