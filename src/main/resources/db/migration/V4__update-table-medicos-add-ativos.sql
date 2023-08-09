alter table medicos
add column ativo tinyint;
update medicos set ativo = 1;
alter table medicos
add constraint check(ativo is not null);