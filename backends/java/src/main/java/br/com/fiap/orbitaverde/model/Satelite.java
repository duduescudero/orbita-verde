package br.com.fiap.orbitaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade Satelite - representa satelites de monitoramento ambiental.
 * Exposta via Web Service SOAP.
 *
 * Herda de Monitoramento (Heranca + Polimorfismo).
 */
@Entity
@Table(name = "satelite")
public class Satelite extends Monitoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e obrigatorio")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotBlank(message = "Tipo e obrigatorio")
    @Column(nullable = false)
    private String tipo; // LEO, MEO, GEO, SSO

    @NotNull(message = "Altitude e obrigatoria")
    @Column(nullable = false)
    private Double altitude; // em km

    @NotBlank(message = "Agencia e obrigatoria")
    @Column(nullable = false)
    private String agencia; // NASA, ESA, INPE, NOAA

    // ======================== POLIMORFISMO ========================

    @Override
    public String getTipoMonitoramento() {
        return "SATELITE";
    }

    @Override
    public String gerarRelatorio() {
        return String.format(
            "[SATELITE] %s | Tipo: %s | Altitude: %.0f km | Agencia: %s | Status: %s",
            nome, tipo, altitude, agencia, getStatus()
        );
    }

    // ======================== GETTERS / SETTERS ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
}
