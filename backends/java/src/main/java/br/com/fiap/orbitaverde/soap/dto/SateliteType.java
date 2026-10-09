package br.com.fiap.orbitaverde.soap.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SateliteType", namespace = "http://fiap.com.br/orbitaverde/soap",
        propOrder = {"id", "nome", "tipo", "altitude", "agencia", "status", "dataCadastro", "relatorio"})
public class SateliteType {

    protected long id;
    protected String nome;
    protected String tipo;
    protected double altitude;
    protected String agencia;
    protected String status;
    protected String dataCadastro;
    protected String relatorio;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

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

    public String getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(String dataCadastro) { this.dataCadastro = dataCadastro; }

    public String getRelatorio() { return relatorio; }
    public void setRelatorio(String relatorio) { this.relatorio = relatorio; }
}
