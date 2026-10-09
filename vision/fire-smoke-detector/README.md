# OrbitaVerde · Detecção de fogo e fumaça

[← Projeto completo](../../README.md)

Protótipo de visão computacional em Python, OpenCV e NumPy. Detecta regiões candidatas a fogo e fumaça usando cor, movimento e área dos contornos; exibe anotações sobre o vídeo e logs por frame.

## Execução

Requisitos: Python 3.10+ e ambiente com suporte a janela gráfica. A partir da raiz:

```bash
cd vision/fire-smoke-detector
python -m venv .venv
```

Ative o ambiente:

```powershell
# Windows / PowerShell
.\.venv\Scripts\Activate.ps1
```

```bash
# Linux / macOS
source .venv/bin/activate
```

Instale e processe o exemplo:

```bash
python -m pip install -r requirements.txt
python main.py --video video_teste_real.mp4
```

Pressione **Q** para sair. Para câmera lenta, adicione `--slow`. Para webcam, use `python main.py --webcam`.

## Como o detector decide

1. Aplica Gaussian blur e converte BGR para HSV.
2. Segmenta cores compatíveis com fogo e fumaça.
3. Usa MOG2 para identificar movimento nas regiões de fumaça.
4. Limpa as máscaras com operações morfológicas.
5. Filtra contornos por área e extensão.
6. Desenha bounding boxes, rótulos e informações do processamento.

O fogo usa cor; a fumaça combina cor e movimento. As regiões candidatas e seus parâmetros podem ser examinados em [detector.py](detector.py).

![Frame processado pelo detector](../../docs/media/vision-demo.png)

## Arquivos

- `main.py`: leitura de vídeo/webcam e janela de demonstração.
- `detector.py`: algoritmo e parâmetros.
- `utils.py`: anotações e painel visual.
- `gerar_video_teste.py`: geração de cenário sintético para demonstração.
- `video_teste_real.mp4`: vídeo de exemplo incluído na entrega.

O nome do vídeo é o original; não implica validação com cenas reais de incêndios.

## Limites

Não há modelo treinado nem validação de precisão em uma base rotulada. `confidence` é uma heurística, não uma probabilidade calibrada. Luz, cores e movimento influenciam o resultado. O detector não envia eventos para as APIs e não substitui um sistema operacional de alerta de incêndio.

Desenvolvido por **Eduardo Escudero** para o Global Solution 2026 da FIAP.

[Arquitetura](../../docs/architecture/README.md) · [Organização dos módulos](../../docs/consolidation.md)
