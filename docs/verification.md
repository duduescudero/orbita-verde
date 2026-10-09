# Verificação da consolidação

Verificações executadas localmente em 09/10/2026.

| Área | Verificação | Resultado |
|---|---|---|
| Backup | Versão anterior preservada em repositório privado, com branches e tags conferidas | Verificado antes da substituição do histórico |
| Autoria | Arquivos atuais revisados e versionamento iniciado com a conta duduescudero | Um novo commit inicial |
| Arquivos | Código dos quatro módulos mantido; documentação antiga substituída | Conferido |
| Documentação | Links locais dos READMEs atuais | Conferidos |
| Java | `mvn test` e `mvn package -DskipTests`, JDK 17 | 1 teste, sem falhas; pacote gerado |
| Java HTTP | Consulta REST de satélites, WSDL e consulta SOAP por ID | Respostas válidas |
| C# | `dotnet build`, SDK 8 | Sem avisos ou erros |
| C# HTTP | Painel, resolução de alerta e tentativa de segunda resolução | Respostas válidas; HTTP 409 na repetição |
| Mobile | Instalação de dependências, `tsc --noEmit`, exportação web | Aprovado |
| Mobile web | Abertura da exportação no Chromium | Dashboard renderizado, sem erros de JavaScript |
| Python | Processamento do vídeo incluído | 365 frames decodificados; candidatos detectados |

## Ajustes para permitir reprodução

- Alinhadas dependências Expo à linha do SDK 52, com atualização do lockfile.
- Adicionados comandos de checagem TypeScript e demonstração/exportação web.
- Corrigido nome de ícone incompatível com o conjunto instalado.
- Adicionado o ícone do app que a configuração referenciava, mas estava ausente.

Estas verificações comprovam os caminhos executados, não cobertura completa de regras de negócio. A versão nativa Android/iOS não foi executada nesta consolidação. O processamento do vídeo não mede acurácia do detector.

O workflow de GitHub Actions executa teste Java, build C#, checagem/exportação mobile e processamento headless do vídeo.
