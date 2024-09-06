alter table books add column active tinyint;
update books set active = 1;