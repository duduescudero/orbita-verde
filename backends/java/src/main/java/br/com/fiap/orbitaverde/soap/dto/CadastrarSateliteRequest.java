package br.com.fiap.orbitaverde.soap.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"nome", "tipo", "altitude", "agencia", "status"})
@XmlRootElement(name = "cadastrarSateliteRequest",
        namespace = "http://fiap.com.br/orbitaverde/soap")
public class CadastrarSateliteRequest {

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap", required = true)
    protected String nome;

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap", required = true)
    protected String tipo;

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap", required = true)
    protected double altitude;

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap", required = true)
    protected String agencia;

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap")
    protected String status;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getAltitude() { return altitude; }
    public void setAltitude(double altitude) { this.altitude = altitude; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
