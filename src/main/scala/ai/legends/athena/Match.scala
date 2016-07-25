package ai.legends.athena

import org.json4s.JsonAST.JValue

case class MatchTimeline (
  frameInterval: Int,
  frames: List[Frame]
)

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  timestamp: Int
)

case class ParticipantFrame (
  totalGold: Int,
  dominionScore: Int,
  currentGold: Int,
  jungleMinionsKilled: Int,
  participantId: Int,
  xp: Int,
  position: Position,
  teamScore: Int,
  minionsKilled: Int,
  level: Int
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

case class Participant (
//  stats: Stats,
  highestAchievedSeasonTier: String,
  teamId: Int,
  spell1Id: Int,
  spell2Id: Int,
  masteries: List[Mastery],
  participantId: Int,
  timeline: ParticipantTimeline,
  championId: Int,
  runes: List[Rune]
)

case class Rune (
  rank: Int,
  runeId: Int
)

case class ParticipantTimeline (
  goldPerMinDeltas: Deltas,
  damageTakenPerMinDeltas: Deltas,
  role: String,
  xpDiffPerMinDeltas: Deltas,
  lane: String,
  xpPerMinDeltas: Deltas,
  creepsPerMinDeltas: Deltas,
  damageTakenDiffPerMinDeltas: Deltas,
  csDiffPerMinDeltas: Deltas
)

case class Deltas (
  tenToTwenty: Option[Double],
  zeroToTen: Option[Double]
)

case class Mastery (
  masteryId: Int,
  rank: Int
)

// TODO(igm): break up class and transform
// case class Stats (
//   firstInhibitorAssist: Boolean,
//   kills: Int,
//   winner: Boolean,
//   unrealKills: Int,
//   item2: Int,
//   neutralMinionsKilledEnemyJungle: Int,
//   inhibitorKills: Int,
//   item4: Int,
//   quadraKills: Int,
//   magicDamageDealt: Int,
//   physicalDamageDealtToChampions: Int,
//   neutralMinionsKilled: Int,
//   item5: Int,
//   trueDamageDealt: Int,
//   wardsKilled: Int,
//   deaths: Int,
//   item1: Int,
//   doubleKills: Int,
//   champLevel: Int,
//   totalScoreRank: Int,
//   tripleKills: Int,
//   visionWardsBoughtInGame: Int,
//   neutralMinionsKilledTeamJungle: Int,
//   assists: Int,
//   combatPlayerScore: Int,
//   objectivePlayerScore: Int,
//   goldEarned: Int,
//   largestMultiKill: Int,
//   wardsPlaced: Int,
//   firstInhibitorKill: Boolean,
//   firstTowerKill: Boolean,
//   trueDamageTaken: Int,
//   totalPlayerScore: Int,
//   physicalDamageDealt: Int,
//   towerKills: Int,
//   totalTimeCrowdControlDealt: Int,
//   totalDamageDealtToChampions: Int,
//   magicDamageTaken: Int,
//   magicDamageDealtToChampions: Int,
//   firstBloodKill: Boolean,
//   goldSpent: Int,
//   largestKillingSpree: Int,
//   totalDamageTaken: Int,
//   item3: Int,
//   totalUnitsHealed: Int,
//   largestCriticalStrike: Int,
//   minionsKilled: Int,
//   killingSprees: Int,
//   trueDamageDealtToChampions: Int,
//   totalDamageDealt: Int,
//   pentaKills: Int,
//   item6: Int,
//   physicalDamageTaken: Int,
//   item0: Int,
//   firstBloodAssist: Boolean,
//   totalHeal: Int,
//   firstTowerAssist: Boolean,
//   sightWardsBoughtInGame: Int
// )

case class Team (
  firstBaron: Boolean,
  winner: Boolean,
  inhibitorKills: Int,
  dragonKills: Int,
  teamId: Int,
  firstBlood: Boolean,
  firstDragon: Boolean,
  bans: List[Ban],
  towerKills: Int,
  dominionVictoryScore: Int,
  vilemawKills: Int,
  riftHeraldKills: Int,
  firstRiftHerald: Boolean,
  baronKills: Int,
  firstTower: Boolean,
  firstInhibitor: Boolean
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
