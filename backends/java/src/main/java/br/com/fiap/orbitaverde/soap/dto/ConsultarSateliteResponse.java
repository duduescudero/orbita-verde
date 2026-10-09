package br.com.fiap.orbitaverde.soap.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"satelite", "mensagem"})
@XmlRootElement(name = "consultarSateliteResponse",
        namespace = "http://fiap.com.br/orbitaverde/soap")
public class ConsultarSateliteResponse {

    protected SateliteType satelite;
    protected String mensagem;

    public SateliteType getSatelite() { return satelite; }
    public void setSatelite(SateliteType satelite) { this.satelite = satelite; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
