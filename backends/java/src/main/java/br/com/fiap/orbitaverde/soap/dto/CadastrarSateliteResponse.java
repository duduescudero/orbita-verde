package br.com.fiap.orbitaverde.soap.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id", "mensagem"})
@XmlRootElement(name = "cadastrarSateliteResponse",
        namespace = "http://fiap.com.br/orbitaverde/soap")
public class CadastrarSateliteResponse {

    protected long id;
    protected String mensagem;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
