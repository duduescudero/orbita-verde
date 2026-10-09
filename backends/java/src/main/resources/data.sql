-- Dados iniciais para Satelite
INSERT INTO satelite (nome, tipo, altitude, agencia, status, data_cadastro) VALUES
('TERRA', 'LEO', 705.0, 'NASA', 'ATIVO', CURRENT_TIMESTAMP),
('AQUA', 'LEO', 705.0, 'NASA', 'ATIVO', CURRENT_TIMESTAMP),
('CBERS-4A', 'SSO', 628.0, 'INPE', 'ATIVO', CURRENT_TIMESTAMP),
('SENTINEL-2A', 'SSO', 786.0, 'ESA', 'ATIVO', CURRENT_TIMESTAMP),
('GOES-16', 'GEO', 35786.0, 'NOAA', 'ATIVO', CURRENT_TIMESTAMP);

-- Dados iniciais para Alerta
INSERT INTO alerta (tipo, descricao, latitude, longitude, nivel, satelite_origem, resolvido, data_cadastro, status) VALUES
('QUEIMADA', 'Foco de incendio detectado na regiao amazonica', -3.4653, -62.2159, 'PERIGO', 'TERRA', false, CURRENT_TIMESTAMP, 'ATIVO'),
('FLARE_SOLAR', 'Atividade solar elevada detectada - classe M2', 0.0, 0.0, 'ALERTA', 'GOES-16', false, CURRENT_TIMESTAMP, 'ATIVO'),
('QUEIMADA', 'Deteccao de calor anormal no Cerrado', -15.7801, -47.9292, 'ALERTA', 'AQUA', false, CURRENT_TIMESTAMP, 'ATIVO'),
('DESMATAMENTO', 'Mudanca de cobertura vegetal identificada', -8.0522, -34.9286, 'NORMAL', 'SENTINEL-2A', true, CURRENT_TIMESTAMP, 'RESOLVIDO'),
('QUEIMADA', 'Foco de incendio na regiao sul da Bahia', -14.8619, -40.8444, 'PERIGO', 'CBERS-4A', false, CURRENT_TIMESTAMP, 'ATIVO');
