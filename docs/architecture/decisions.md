# Decisões de projeto

[← Arquitetura](README.md)

## Organizar os módulos em um repositório

Os quatro módulos estão organizados em diretórios próprios, com documentação e requisitos de execução independentes. O README principal oferece uma visão comum do domínio e aponta para cada implementação.

Consulte a [organização dos módulos](../consolidation.md). A versão pública começa pela versão consolidada do projeto; melhorias posteriores são registradas em novos commits.

## Manter dois backends

Java e C# demonstram contratos, persistência e modelagens diferentes. A organização não força a união dos contratos. Cada implementação tem banco, porta e documentação próprios.

## Separar demonstração de integração

O mobile funciona com dados locais; no Java, protocolos compartilham serviços internos; no Python, a saída é visual. A apresentação descreve esses comportamentos e coloca sincronização, ingestão externa e autenticação real na evolução futura.

## Detector interpretável

Parâmetros explícitos de HSV, área e movimento permitem examinar as decisões do algoritmo. A abordagem depende da cena e precisa de avaliação com vídeos rotulados antes de qualquer alegação de confiabilidade operacional.

