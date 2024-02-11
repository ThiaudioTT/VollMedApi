-- Created to add column telefone to medicos table
-- Migrations who have already been executed will not be executed again, Migrations are immutable,
-- if you need to change a migration, create a new one with a new version number

ALTER TABLE medicos ADD COLUMN telefone varchar(20) not null;
