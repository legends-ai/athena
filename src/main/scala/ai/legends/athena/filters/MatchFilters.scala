package ai.legends.athena.filters

case class MatchFilters(
  patch: Option[String] = None,
  duration: Option[Range] = None,
  tier: Option[Int] = None,
  region: Option[String] = None
)
