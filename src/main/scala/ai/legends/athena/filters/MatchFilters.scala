package ai.legends.athena.filters

import ai.legends.athena.matches.Match

case class MatchFilters(
  patch: String,
  duration: Int
)

object MatchFilters {

  def apply(m: Match): MatchFilters = {
    MatchFilters(
      patch = m.matchVersion,
      duration = m.matchDuration
    )
  }

}
