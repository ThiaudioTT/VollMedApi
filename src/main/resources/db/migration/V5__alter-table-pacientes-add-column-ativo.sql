-- add column ativo to paciente

ALTER TABLE pacientes ADD COLUMN ativo BOOLEAN NOT NULL DEFAULT TRUE;
UPDATE pacientes SET ativo = TRUE;
