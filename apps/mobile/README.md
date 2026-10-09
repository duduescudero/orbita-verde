# OrbitaVerde · Aplicativo mobile

[← Projeto completo](../../README.md)

Interface de demonstração para cadastrar, consultar e resolver alertas ambientais. Desenvolvida em React Native, TypeScript e Expo Router, com armazenamento local e temas claro/escuro.

## Funcionalidades

- Cadastro e login locais de demonstração.
- Lista e filtros de alertas por tipo e gravidade.
- Cadastro de alertas com coordenadas, detalhe e resolução.
- Localização do dispositivo mediante permissão.
- Mapa ilustrativo, perfil e alternância de tema.

## Execução

Requisitos: Node.js 20 e npm. O projeto usa Expo SDK 52; para Android/iOS, utilize um development build ou uma versão compatível do Expo Go. O Expo Go atual pode não suportar esse SDK.

A partir da raiz:

```bash
cd apps/mobile
npm ci
npm start
```

Para a demonstração no navegador:

```bash
npm run web
```

Crie um usuário com **dados fictícios** na tela de registro. Não precisa iniciar nenhuma API. A localização depende do dispositivo, das permissões e do suporte do navegador.

## Organização

| Diretório | Responsabilidade |
|---|---|
| `app/` | Rotas e telas do Expo Router |
| `components/` | Cards, badges e mapa ilustrativo |
| `contexts/` | Estado do tema |
| `services/` | Sessão e persistência locais |
| `types/` | Modelos TypeScript |
| `constants/` | Cores e estilos |

## Verificação

```bash
npm run typecheck
npm run export:web
```

## Limites atuais

Alertas usam AsyncStorage e dados iniciais de exemplo. Não há consumo de NASA/ESA nem conexão com os backends. O mapa é ilustrativo, não um mapa de tiles. Cadastro e sessão são locais; a senha é persistida em texto simples para demonstração. Use somente credenciais fictícias.

Desenvolvido por **Eduardo Escudero** para o Global Solution 2026 da FIAP.

[Arquitetura](../../docs/architecture/README.md) · [Organização dos módulos](../../docs/consolidation.md)
