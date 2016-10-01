CREATE TABLE IF NOT EXISTS athena.matches (
  id text,
  region text,
  body text,
  rank bigint,
  patch text,
  PRIMARY KEY(id, rank)
);

CREATE TABLE IF NOT EXISTS athena.parsed_matches (
  id text,
  proto blob,
  region text,
  rank bigint,
  patch text,
  PRIMARY KEY(id, (region, rank, patch))
);
