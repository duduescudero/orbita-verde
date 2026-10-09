package br.com.fiap.orbitaverde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * OrbitaVerde SOA - FIAP Global Solution 2026
 *
 * Plataforma de monitoramento ambiental com dados satelitais.
 * Demonstra arquitetura SOA com API REST + Web Service SOAP + Integracao.
 *
 * Autor: Eduardo Escudero
 */
@SpringBootApplication
public class OrbitaVerdeSoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrbitaVerdeSoaApplication.class, args);
        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║        OrbitaVerde SOA - FIAP GS 2026             ║");
        System.out.println("╠═══════════════════════════════════════════════════╣");
        System.out.println("║  REST API:  http://localhost:8085/api/alertas     ║");
        System.out.println("║  REST API:  http://localhost:8085/api/satelites   ║");
        System.out.println("║  SOAP WSDL: http://localhost:8085/ws/satelite.wsdl║");
        System.out.println("║  H2 Console:http://localhost:8085/h2-console      ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");
    }
}
