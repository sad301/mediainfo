create table tbl_content (
  id varchar(16) not null,
  duration int not null default 5,
  content text not null,
  type varchar(32) not null check(type in ("TEXT", "IMAGE", "VIDEO")),
  primary key (id)
);

create table config (
  app_key varchar(16) not null,
  app_value text not null,
  primary key (app_key)
);

insert into config values
  ("company.name", "PT Maju Mundur"),
  ("company.address", "Jln. Saranani No 13 A"),
  ("user.username", "admin"),
  ("user.password", "nimda");
