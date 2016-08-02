package ai.legends.athena.filters

case class ChampionReportFilters(
  championId: Int,
  gamesPlayed: Range,
  patch: String,
  duration: Range
)

object ChampionReportFilters {

  def apply(filters: ChampionFilters): ChampionReportFilters = {
    ChampionReportFilters(
      filters.participant.championId,
      gamesPlayedRange(filters.participant.gamesPlayed),
      filters.m.patch,
      durationRange(filters.m.duration)
    )
  }

  def findRange(ranges: List[Range], n: Int): Range = {
    ranges.find(_.contains(n)).get
  }

  val gamesPlayedRanges =
    (0 until 5) ::
    (5 until 15) ::
    (15 until 50) ::
    (50 until 125) ::
    (125 until Int.MaxValue) :: Nil

  def gamesPlayedRange(n: Int): Range =
    findRange(gamesPlayedRanges, n)

  val durationRanges =
    (0 until 25 * 60) ::
    (25 * 60 until 30 * 60) ::
    (30 * 60 until 35 * 60) ::
    (35 * 60 until 40 * 60) ::
    (40 * 60 until Int.MaxValue) :: Nil

  def durationRange(n: Int): Range =
    findRange(durationRanges, n)

}
