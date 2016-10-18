CREATE TABLE IF NOT EXISTS athena.matches_serialized (
  id text,
  region text,
  rank bigint,
  patch text,

  data blob,
  PRIMARY KEY(id, rank)
);
