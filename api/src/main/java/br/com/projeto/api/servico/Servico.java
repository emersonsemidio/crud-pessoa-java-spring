package br.com.projeto.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.projeto.api.model.Mensagem;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;

@Service
public class Servico {

  @Autowired
  private Mensagem mensagem;

  @Autowired
  private Repositorio repositorio;

  // O tipo de retorno ResponseEntity<?> é um tipo genérico que pode ser usado para representar qualquer tipo de resposta HTTP.
  // Nesse caso estamos fazendo uso do tipo genérico, pois o retorno pode ser tanto um objeto Pessoa, quanto um objeto Mensagem.
  // As respostas HTTP são representadas por um código de status e um corpo. O código de status é representado por um objeto da 
  // classe HttpStatus,

  // Cadastrar um registro
  public ResponseEntity<?> cadastrar(Pessoa pessoa){
    if(pessoa.getNome().equals("") || pessoa.getIdade() < 0){
      mensagem.setMensagem("Nome precisa ser preenchido e idade precisa ser válida");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    } else{      
      return new ResponseEntity<>(repositorio.save(pessoa), HttpStatus.CREATED);
    }
  }

  // Listar todos os registros
  public ResponseEntity<?> listar(){
    return new ResponseEntity<>(repositorio.findAll(), HttpStatus.OK);
  }

  //Busca por código
  public ResponseEntity<?> buscarPorCodigo(int codigo){
    if(repositorio.countByCodigo(codigo) == 0){
      mensagem.setMensagem("Pessoa não encontrada");
      return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    } else{
      return new ResponseEntity<>(repositorio.findByCodigo(codigo), HttpStatus.OK);
    }
  }

  public ResponseEntity<?> atualizar(Pessoa pessoa){

    if(repositorio.countByCodigo(pessoa.getCodigo()) == 0){
      mensagem.setMensagem("Código inexistente");
      return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);

    } else if(pessoa.getNome().equals("")){
      mensagem.setMensagem("Nome precisa ser preenchido");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

    } else if(pessoa.getIdade() < 0){
      mensagem.setMensagem("Idade precisa ser válida");
      return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);

    } else{
      return new ResponseEntity<>(repositorio.save(pessoa), HttpStatus.OK);      
    }
  }

  //Remover um registro
  public ResponseEntity<?> remover(int codigo){
    if(repositorio.countByCodigo(codigo) == 0){
      mensagem.setMensagem("Código não encontrado");
      return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    } else{
      Pessoa pessoa = repositorio.findByCodigo(codigo);
      repositorio.delete(pessoa);
      mensagem.setMensagem("Pessoa removida com sucesso");
      return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
  }
  
}
