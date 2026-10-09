# Estado atual e evolução

[← Projeto completo](../../README.md)

## Entregue

- Interface mobile com alertas e sessão locais.
- Backend Java REST/SOAP com JPA e H2.
- Backend C# com domínio, SQLite e painel.
- Detector visual por heurísticas.
- Código e documentação centralizados em um repositório.

## Próximos passos

1. Definir contrato de alerta e conectar o mobile a um backend.
2. Adicionar autenticação segura e configuração de ambiente.
3. Testar regras, validações e integração das APIs.
4. Implementar ingestão externa com rastreabilidade da fonte.
5. Avaliar o detector em vídeos rotulados e medir falsos positivos e negativos.

## Limitações atuais

Os módulos não trocam dados entre si. Não há ingestão verificada de NASA/ESA/INPE. H2 é temporário; SQLite local é persistente. O login mobile é local, e as APIs não possuem autenticação. C# expõe detalhes internos em alguns erros. O teste Java original verifica carga do contexto. Builds e verificações de execução não equivalem a cobertura de regras de negócio.

Este roteiro registra propostas; não descreve funcionalidades já implementadas.

