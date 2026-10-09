package br.com.fiap.orbitaverde.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import java.time.LocalDateTime;

/**
 * Classe abstrata base para todos os objetos monitorados pela plataforma OrbitaVerde.
 *
 * Demonstra os pilares de OOP:
 * - ABSTRACAO: define contrato via metodos abstratos
 * - ENCAPSULAMENTO: atributos privados com getters/setters
 * - HERANCA: Alerta e Satelite estendem esta classe
 * - POLIMORFISMO: getTipoMonitoramento() e gerarRelatorio() sao sobrescritos
 */
@MappedSuperclass
public abstract class Monitoramento {

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "status", nullable = false)
    private String status = "ATIVO";

    @PrePersist
    protected void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }

    // ======================== METODOS ABSTRATOS (Polimorfismo) ========================

    /**
     * Retorna o tipo de monitoramento (ex: "ALERTA", "SATELITE").
     * Cada subclasse implementa de forma especifica.
     */
    public abstract String getTipoMonitoramento();

    /**
     * Gera um relatorio textual do objeto monitorado.
     * Cada subclasse formata o relatorio conforme seus dados.
     */
    public abstract String gerarRelatorio();

    // ======================== GETTERS / SETTERS (Encapsulamento) ========================

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
