CREATE TABLE IF NOT EXISTS athena.rankings (
  id text,
  rank bigint,
  ranking blob,
  PRIMARY KEY(id, rank)
);
