# Organização dos módulos

[← Projeto completo](../README.md)

O OrbitaVerde reúne quatro implementações do mesmo domínio acadêmico, desenvolvidas por **Eduardo Escudero** para o Global Solution 2026 da FIAP.

| Diretório | Responsabilidade | Documentação |
|---|---|---|
| `apps/mobile/` | Interface e armazenamento local | [Mobile](../apps/mobile/README.md) |
| `backends/java/` | API REST/SOAP com Spring Boot | [Java](../backends/java/README.md) |
| `backends/csharp/` | API HTTP com ASP.NET Core | [C#](../backends/csharp/README.md) |
| `vision/fire-smoke-detector/` | Processamento de vídeo com OpenCV | [Visão computacional](../vision/fire-smoke-detector/README.md) |

Os módulos são independentes e têm seus próprios requisitos. A documentação atual descreve o comportamento presente no código e os limites de cada implementação.

## Documentação

- [Arquitetura](architecture/README.md)
- [Decisões técnicas](architecture/decisions.md)
- [Estado atual e evolução](architecture/roadmap.md)
- [Verificações](verification.md)

## Versionamento

A versão pública começa com o commit **Versão consolidada do OrbitaVerde**, que registra o projeto organizado. As próximas melhorias recebem commits próprios, sem datas retroativas ou simulação de etapas anteriores.

A versão anterior foi preservada em um repositório privado antes da substituição do histórico público.
