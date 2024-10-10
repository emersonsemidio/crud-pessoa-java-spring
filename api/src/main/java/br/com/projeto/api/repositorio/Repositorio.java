package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {

  List<Pessoa> findAll();

  Pessoa findByCodigo(int codigo);

  void deleteByCodigo(int codigo);

  List<Pessoa> findByOrderByNomeAsc();

  List<Pessoa> findByNomeOrderByIdadeDesc(String nome);

  List<Pessoa> findByNomeContaining(String termo);

  List<Pessoa> findByNomeStartingWith(String termo);

  List<Pessoa> findByNomeEndsWith(String termo);

  // A query tem como primeiro parametro a query que será executada, e o segundo parametro é o tipo de retorno que será retornado.
  @Query(value = "SELECT SUM(idade) FROM pessoas", nativeQuery = true)
  int somaIdades();

  // para fazer referência a um parâmetro, basta colocar o nome do parâmetro entre dois pontos (:)
  @Query(value = "SELECT * FROM pessoas WHERE idade > :idade", nativeQuery = true)
  List<Pessoa> maioresDeIdade(int idade);

  // Método para validar se um registro existe
  int countByCodigo(int codigo);

  
  
}
