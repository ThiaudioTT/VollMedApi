ALTER TABLE medicos ADD COLUMN ativo TINYINT; -- 1 = true, 0 = false
UPDATE medicos SET ativo = 1; -- Update all rows to true
