package br.com.projeto.api.model;

import org.springframework.stereotype.Component;

// O @Component é uma anotação que indica que uma classe é um "componente". 
// Essa anotação serve como um ponto de partida para o Spring, que irá escanear o pacote onde a aplicação 
// está localizada e registrar todos os beans anotados com @Component. Em suma, vai varrer toda a classe
// componente e registrar todos os beans anotados com @Component.
@Component
public class Mensagem {
  private String mensagem;

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }
}
