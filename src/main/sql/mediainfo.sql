create table config (
  app_key varchar(16) not null,
  app_value text not null,
  primary key (app_key)
);

insert into config values
  ("user.username", "admin"),
  ("user.password", "nimda");