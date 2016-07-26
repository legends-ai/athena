package ai.legends.athena.matches

import org.json4s.JsonAST.JValue
import org.json4s.JsonDSL._

case class MatchTimeline (
  frameInterval: Int,
  frames: List[Frame]
)

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  timestamp: Int
)

case class Position (
  x: Int,
  y: Int
)

case class ParticipantIdentity (
  participantId: Int,
  player: Player
)

case class Player (
  matchHistoryUri: String,
  profileIcon: Int,
  summonerId: Int,
  summonerName: String
)

case class Rune (
  rank: Int,
  runeId: Int
)

case class Deltas (
  tenToTwenty: Option[Double],
  zeroToTen: Option[Double]
)

case class Mastery (
  masteryId: Int,
  rank: Int
)

case class Ban (
  championId: Int,
  pickTurn: Int
)

case class Match (
  matchDuration: Int,
  matchVersion: String,
  teams: List[Team],
  platformId: String,
  participants: List[Participant],
  matchMode: String,
  matchType: String,
  season: String,
  mapId: Int,
  participantIdentities: List[ParticipantIdentity],
  timeline: Option[MatchTimeline],
  region: String,
  matchCreation: Int,
  queueType: String,
  matchId: Int
)
