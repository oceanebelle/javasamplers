create table user (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 100, INCREMENT BY 1) PRIMARY KEY,
  name VARCHAR(1000) not null,
  created_at DATE not null
);




