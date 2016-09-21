CREATE TABLE IF NOT EXISTS athena_out.match_sums (
  champion_id int,
  enemy_id int,
  patch text,
  tier int,

  -- Region and Role enums
  region int,
  role int,

  -- MatchFilters message
  match_filters blob,

  -- MatchSum message
  match_sum blob,

  -- the whole enchilada
  PRIMARY KEY((champion_id, enemy_id, patch, tier, region, role))
);
