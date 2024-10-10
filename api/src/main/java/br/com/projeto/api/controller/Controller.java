package br.com.projeto.api.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.api.model.Cliente;
import br.com.projeto.api.model.Pessoa;
import br.com.projeto.api.repositorio.Repositorio;
import br.com.projeto.api.servico.Servico;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class Controller {

  // Realizada a injecção de dependência do Repositorio, então não precisamos fazer
  // um new Repositorio(). para acessar acessar os métodos do Repositorio.
  @Autowired
  private Repositorio repositorio;

  @Autowired
  private Servico servico;

  // Toda vez que fizermos uma requisição POST para a URL localhost:8080/api, e fizermos uma requisição
  // do tipo post podemos cadastrar os dados
  @PostMapping("/api")
  public ResponseEntity<?> cadastrar(@RequestBody Pessoa pessoa) {
    return servico.cadastrar(pessoa);
  }

    // Toda vez que fizermos uma requisição GET para a URL localhost:8080/api, e fizermos uma requisição
  // do tipo GET iremos listar todos os dados da tabela pessoas
  @GetMapping("/api")
  public ResponseEntity<?> listar() {
    return servico.listar();
  }

  // Toda vez que fizermos uma requisição GET para a URL localhost:8080/api/{codigo}, e fizermos uma requisição
  // no caso, o código é o que tá sendo recebido como parâmetro e ele terá que ser passado na URL
  @GetMapping("/api/{codigo}")
  public ResponseEntity<?> buscarPorCodigo(@PathVariable int codigo) {
    return servico.buscarPorCodigo(codigo);
  }

  // Por ser uma atualização e já existir um ID, o objeto identifica que o registro já existe e atualiza,
  // caso não exista, ele insere um novo registro, como feito no cadastro.
  @PutMapping("/api")
  public ResponseEntity<?> atualizar(@RequestBody Pessoa pessoa) {
    return servico.atualizar(pessoa);
  }

  // Toda vez que fizermos uma requisição DELETE para a URL localhost:8080/api/{codigo}, e fizermos uma requisição
  // do tipo DELETE iremos deletar o registro que tem o código passado como parâmetro
  @DeleteMapping("/api/{codigo}")
  public ResponseEntity<?> remover(@PathVariable int codigo) {
    return servico.remover(codigo);
  }

  //Conta o numero de registros da tabela
  @GetMapping("/api/contador")
  public Long contador() {
    return repositorio.count();
  }

  //Ordena os nomes em ordem alfabética
  @GetMapping("/api/nomesOrdenados")
  public List<Pessoa> ordenarPorNomes(){
    return repositorio.findByOrderByNomeAsc();
  }

  //Ordena as idades em ordem decrescente do nome passado como parâmetro
  @GetMapping("/api/idadeOrdenada/{nome}")
  public List<Pessoa> ordenarPorIdade(@PathVariable String nome){
    return repositorio.findByNomeOrderByIdadeDesc(nome);
  }

  @GetMapping("/api/buscarPorTermo")
  public List<Pessoa> buscarPorTermo(){
    return repositorio.findByNomeContaining("l");
  }

  @GetMapping("/api/iniciaCom")
  public List<Pessoa> buscarPorInicio(){
    return repositorio.findByNomeStartingWith("L");
  }

  @GetMapping("/api/terminaCom")
  public List<Pessoa> buscarPorFim(){
    return repositorio.findByNomeEndsWith("L");
  }

  @GetMapping("/api/somaIdades")
  public int somaIdades(){
    return repositorio.somaIdades();
  }

  @GetMapping("/api/maioresDeIdade")
  public List<Pessoa> maioresDeIdade(){
    return repositorio.maioresDeIdade(18);
  }

  @GetMapping("")
  public String hello() {
    return "Hello, World!";
  }

  // O GetMapping é uma anotação que mapeia solicitações HTTP GET para, basicamente é o caminho
  // da url que você deseja acessar.
  @GetMapping("/hello/{name}")
  // O @PathVariable é uma anotação que é usada para vincular o valor da variável de caminho da URL
  public String boasVindas(@PathVariable String name) {
    return "Seja Bem vindo " + name + "!";
  }

  @PostMapping("/pessoa") 
  public Pessoa pessoa(@RequestBody Pessoa pessoa) {
    return pessoa;
    
  }

  @GetMapping("/status")
  // O ResponseEntity é uma classe que representa toda a resposta HTTP: código de status, cabeçalhos e corpo.
  // Usamos o método ok() para criar uma resposta com o código de status 200 OK.
  public ResponseEntity<?> status(){
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // O @valid é uma anotação que serve para validar os dados que estão sendo passados no corpo da requisição, no caso
  // as validações de nome e mail que estão sendo feitas na classe Cliente.
  @PostMapping("/cliente")
  public void cliente(@Valid @RequestBody Cliente cliente){
  }

  


}
