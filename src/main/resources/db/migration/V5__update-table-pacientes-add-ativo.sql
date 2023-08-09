alter table pacientes
add column ativo tinyint;
update pacientes
set ativo = 1;
alter table pacientes
add constraint check(ativo is not null);