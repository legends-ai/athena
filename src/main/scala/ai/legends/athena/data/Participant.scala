package ai.legends.athena.data

case class Participant (
  stats: Stats,
  highestAchievedSeasonTier: String,
  teamId: Int,
  spell1Id: Int,
  spell2Id: Int,
  masteries: List[Mastery],
  participantId: Int,
  timeline: ParticipantTimeline,
  championId: Int,
  runes: List[Rune]
) {

  def summonersString: String = {
    List(spell1Id, spell2Id).sorted.mkString("|")
  }

}
