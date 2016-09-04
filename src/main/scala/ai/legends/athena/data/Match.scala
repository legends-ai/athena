package ai.legends.athena.data

case class Match (
  matchDuration: Int,
  matchVersion: String,
  teams: List[Teams],
  platformId: String,
  participants: List[Participant],
  matchMode: String,
  matchType: String,
  season: String,
  mapId: Int,
  participantIdentities: List[ParticipantIdentity],
  timeline: Option[Timeline],
  region: String,
  matchCreation: Int,
  queueType: String,
  matchId: Int
) {

  /** Get all events associated with this match. */
  def events: List[Event] = {
    timeline.map(_.frames).map(_.map(_.events)).getOrElse(List()).flatten
  }

}
