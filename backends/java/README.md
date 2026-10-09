# OrbitaVerde · Backend Java

[← Projeto completo](../../README.md)

API para cadastro e acompanhamento de alertas e satélites. Explora REST, SOAP, orientação a objetos e persistência com Java 17, Spring Boot 3.2.5, Spring Data JPA e H2.

## Execução

Requisitos: JDK 17 e Maven 3.9. A partir da raiz:

```bash
cd backends/java
mvn test
mvn spring-boot:run
```

O serviço inicia em `http://localhost:8085`. O banco H2 é recriado a cada execução e recebe dados de exemplo de `src/main/resources/data.sql`.

## Experimente

No PowerShell:

```powershell
Invoke-RestMethod http://localhost:8085/api/satelites
Invoke-RestMethod http://localhost:8085/api/alertas
Invoke-RestMethod http://localhost:8085/api/alertas/relatorios
```

No Bash:

```bash
curl http://localhost:8085/api/satelites
curl http://localhost:8085/api/alertas/relatorios
```

O arquivo [requests.http](requests.http) contém exemplos para o HTTP Client do IntelliJ.

## Contrato SOAP

- WSDL: `http://localhost:8085/ws/satelite.wsdl`
- Endpoint: `http://localhost:8085/ws`
- Namespace: `http://fiap.com.br/orbitaverde/soap`
- Schema: [satelite.xsd](src/main/resources/wsdl/satelite.xsd)
- Operações: `consultarSatelite` e `cadastrarSatelite`.

REST e SOAP compartilham services. A classe `SateliteIntegrationService` chama a lógica Java diretamente; não envia uma requisição SOAP pela rede.

## Endpoints REST

| Recurso | Operações |
|---|---|
| `/api/satelites` | Listar, consultar por ID, criar, atualizar, excluir e gerar relatórios |
| `/api/alertas` | Listar, consultar por ID, criar, atualizar e excluir |
| `/api/alertas/ativos` | Consultar alertas não resolvidos |
| `/api/alertas/tipo/{tipo}` | Filtrar por tipo |
| `/api/alertas/nivel/{nivel}` | Filtrar por gravidade |
| `/api/alertas/{id}/resolver` | Resolver alerta via PATCH |
| `/api/alertas/relatorios` | Gerar relatórios de alertas |

## Engenharia

Controllers tratam HTTP; services concentram lógica; repositories JPA tratam persistência. `Monitoramento` é a base abstrata de `Alerta` e `Satelite`, com relatórios especializados por polimorfismo. Bean Validation e tratamento global de erros completam o fluxo.

## Limites atuais

Banco temporário, dados de exemplo e nenhuma ingestão externa. Não há autenticação configurada. O teste original verifica o carregamento do contexto Spring; não representa cobertura completa das regras de negócio. O console H2 está habilitado para desenvolvimento local.

Desenvolvido por **Eduardo Escudero** para o Global Solution 2026 da FIAP.

[Arquitetura](../../docs/architecture/README.md) · [Organização dos módulos](../../docs/consolidation.md)
