CREATE TABLE IF NOT EXISTS athena.match_sums (
  champion_id bigint,
  enemy_id bigint,
  patch text,
  tier text,
  region text,
  role text,

  -- MatchFilters message
  match_filters blob,

  -- MatchSum message
  match_sum blob,

  -- the whole enchilada
  PRIMARY KEY((champion_id, enemy_id, patch, tier, region, role))
);
