package br.com.fiap.orbitaverde.soap.dto;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"id"})
@XmlRootElement(name = "consultarSateliteRequest",
        namespace = "http://fiap.com.br/orbitaverde/soap")
public class ConsultarSateliteRequest {

    @XmlElement(namespace = "http://fiap.com.br/orbitaverde/soap", required = true)
    protected long id;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
}
