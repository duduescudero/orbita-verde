package br.com.fiap.orbitaverde.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade principal da plataforma OrbitaVerde.
 * Representa alertas ambientais detectados via satelite.
 *
 * Herda de Monitoramento (Heranca + Polimorfismo).
 * CRUD completo exposto via API REST.
 */
@Entity
@Table(name = "alerta")
public class Alerta extends Monitoramento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo e obrigatorio")
    @Column(nullable = false)
    private String tipo; // QUEIMADA, FLARE_SOLAR, DESMATAMENTO, ENCHENTE

    @NotBlank(message = "Descricao e obrigatoria")
    @Column(nullable = false, length = 500)
    private String descricao;

    @NotNull(message = "Latitude e obrigatoria")
    @Column(nullable = false)
    private Double latitude;

    @NotNull(message = "Longitude e obrigatoria")
    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String nivel = "NORMAL"; // NORMAL, ALERTA, PERIGO

    @Column(name = "satelite_origem")
    private String sateliteOrigem;

    @Column(nullable = false)
    private boolean resolvido = false;

    // ======================== POLIMORFISMO ========================

    @Override
    public String getTipoMonitoramento() {
        return "ALERTA";
    }

    @Override
    public String gerarRelatorio() {
        return String.format(
            "[ALERTA #%d] Tipo: %s | Nivel: %s | Coords: (%.4f, %.4f) | Satelite: %s | Resolvido: %s",
            id, tipo, nivel, latitude, longitude,
            sateliteOrigem != null ? sateliteOrigem : "N/A",
            resolvido ? "SIM" : "NAO"
        );
    }

    // ======================== GETTERS / SETTERS ========================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getSateliteOrigem() { return sateliteOrigem; }
    public void setSateliteOrigem(String sateliteOrigem) { this.sateliteOrigem = sateliteOrigem; }

    public boolean isResolvido() { return resolvido; }
    public void setResolvido(boolean resolvido) { this.resolvido = resolvido; }
}
