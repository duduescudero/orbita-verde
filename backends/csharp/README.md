# OrbitaVerde · Backend C#

[← Projeto completo](../../README.md)

API HTTP em ASP.NET Core 8 com Entity Framework e SQLite. Modela satélites, sensores de solo, regiões e alertas, com herança, interfaces, regras de domínio e consultas assíncronas.

## Execução

Requisito: .NET SDK 8. A partir da raiz:

```bash
cd backends/csharp/OrbitaVerde.API
dotnet restore
dotnet build
dotnet run --launch-profile http
```

A API inicia em `http://localhost:5047`. O banco SQLite é criado automaticamente por `EnsureCreated()`, com dados de exemplo. Não há migrações versionadas.

## Experimente

No PowerShell:

```powershell
Invoke-RestMethod http://localhost:5047/
Invoke-RestMethod http://localhost:5047/api/satelites
Invoke-RestMethod http://localhost:5047/api/alertas/painel
```

No Bash:

```bash
curl http://localhost:5047/api/alertas/painel
```

Há exemplos adicionais em [OrbitaVerde.API.http](OrbitaVerde.API/OrbitaVerde.API.http). Não há interface Swagger configurada.

## Endpoints

| Recurso | Operações |
|---|---|
| `/api/satelites` | CRUD e monitoramento |
| `/api/sensores` | CRUD de sensores |
| `/api/regioesativas` | CRUD de regiões |
| `/api/alertas` | Consulta, filtros e exclusão |
| `/api/alertas/flare` | Criação de alerta de flare via POST |
| `/api/alertas/queimada` | Criação de alerta de queimada via POST |
| `/api/alertas/{id}/resolver` | Resolução via PATCH; HTTP 409 se já resolvido |
| `/api/alertas/painel` | Contagens por situação, categoria e nível |

## Engenharia

A classe abstrata `Alerta` centraliza estado e resolução; `AlertaFlare` e `AlertaQueimada` especializam categorias e mensagens. Entidades de equipamentos exploram interfaces e herança. Consultas usam `AsNoTracking()` quando não precisam alterar os dados.

## Evidências da entrega

[![Painel de alertas](evidencias/print_04_painel.png)](evidencias/print_04_painel.png)

[Satélites](evidencias/print_01_satelites.png) · [Sensores](evidencias/print_02_sensores.png) · [Regiões](evidencias/print_03_regioes.png) · [Monitoramento](evidencias/print_05_monitoramento.png) · [Resposta 404](evidencias/print_06_404.png)

## Limites atuais

Dados de exemplo, sem integração externa nem autenticação configurada. Alguns endpoints expõem mensagens internas de exceções; revisar antes de uma implantação pública. A implementação Java possui um contrato próprio e não substitui esta API diretamente.

Desenvolvido por **Eduardo Escudero** para o Global Solution 2026 da FIAP.

[Arquitetura](../../docs/architecture/README.md) · [Organização dos módulos](../../docs/consolidation.md)
