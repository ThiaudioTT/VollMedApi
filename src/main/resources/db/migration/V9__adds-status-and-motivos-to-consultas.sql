ALTER TABLE consultas ADD COLUMN status VARCHAR(20) DEFAULT 'AGENDADA';

-- Limit is 510, in the future we can change it to wherever we want or use TEXT
ALTER TABLE consultas ADD COLUMN motivo_cancelamento VARCHAR(510);