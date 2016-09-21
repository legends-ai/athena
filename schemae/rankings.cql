CREATE TABLE IF NOT EXISTS athena_out.rankings (
  id text,
  rank bigint,
  time timestamp,
  PRIMARY KEY(id, rank)
);
