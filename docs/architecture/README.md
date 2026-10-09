# Arquitetura

[← Projeto completo](../../README.md)

## Módulos independentes

| Módulo | Entrada | Persistência/saída | Integrações presentes |
|---|---|---|---|
| Mobile | Formulários e interação | AsyncStorage no dispositivo | Expo Location |
| Java | HTTP REST e XML SOAP | H2 em memória | REST e SOAP compartilham services |
| C# | HTTP/JSON | SQLite em arquivo | Entity Framework e entidades de domínio |
| Python | Frames de vídeo/webcam | Janela anotada e logs | OpenCV e NumPy |

## Mobile

As rotas organizam login, registro, tabs e detalhes. `services/storage.ts` concentra dados locais; `services/auth.ts` simula cadastro e sessão. O contexto fornece o tema. O mapa é ilustrativo e pode mostrar coordenadas do dispositivo; não usa tiles de um provedor cartográfico.

A senha da demonstração é persistida em texto simples. Use dados fictícios. Esse mecanismo precisa ser substituído antes de uma publicação para usuários reais.

## Java

Controllers REST e endpoints SOAP compartilham services que acessam repositories JPA. O H2 recebe dados iniciais e é recriado a cada execução. `SateliteIntegrationService` consulta o serviço interno diretamente, sem comunicação SOAP pela rede.

## C#

Controllers usam o contexto Entity Framework. Entidades implementam regras, herança e interfaces. SQLite mantém dados entre execuções. O banco é criado com `EnsureCreated()`; não há migrações versionadas. `AddEndpointsApiExplorer()` não configura uma interface Swagger por si só.

## Python

Blur e conversão HSV precedem a segmentação de cor. Fumaça combina baixa saturação com movimento MOG2. Operações morfológicas e filtros de contorno reduzem ruído. Fogo usa cor, sem depender da máscara de movimento.

Não há rede neural treinada, integração com sensores físicos nem envio de alertas ao backend.

## Integração futura

Selecionar um backend e definir um contrato comum de alertas antes de conectar o mobile. O detector poderá produzir eventos para esse contrato. Dados externos e autenticação exigem implementação própria.

[Decisões](decisions.md) · [Evolução](roadmap.md)

