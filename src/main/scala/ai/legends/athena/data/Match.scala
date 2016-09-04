package ai.legends.athena.data

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

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  timestamp: Int
)

case class Timeline (
  frameInterval: Int,
  frames: List[Frame]
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
)

case class Rune (
  rank: Int,
  runeId: Int
)

case class ParticipantTimeline (
  role: String,
  lane: String,
  creepsPerMinDeltas: Option[Deltas],
  damageTakenPerMinDeltas: Option[Deltas],
  goldPerMinDeltas: Option[Deltas],
  towerKillsPerMinDeltas: Option[Deltas],
  wardsPerMinDeltas: Option[Deltas],
  xpDiffPerMinDeltas: Option[Deltas],
  xpPerMinDeltas: Option[Deltas]
)

case class Deltas (
  zeroToTen: Option[Double],
  tenToTwenty: Option[Double],
  twentyToThirty: Option[Double],
  thirtyToEnd: Option[Double]
)

case class Mastery (
  masteryId: Int,
  rank: Int
)

case class Stats (
  assists: Int,
  champLevel: Int,
  combatPlayerScore: Int,
  deaths: Int,
  doubleKills: Int,
  firstBloodAssist: Boolean,
  firstBloodKill: Boolean,
  firstInhibitorAssist: Boolean,
  firstInhibitorKill: Boolean,
  firstTowerAssist: Boolean,
  firstTowerKill: Boolean,
  goldEarned: Int,
  goldSpent: Int,
  inhibitorKills: Int,
  item0: Int,
  item1: Int,
  item2: Int,
  item3: Int,
  item4: Int,
  item5: Int,
  item6: Int,
  killingSprees: Int,
  kills: Int,
  largestCriticalStrike: Int,
  largestKillingSpree: Int,
  largestMultiKill: Int,
  magicDamageDealt: Int,
  magicDamageDealtToChampions: Int,
  magicDamageTaken: Int,
  minionsKilled: Int,
  neutralMinionsKilled: Int,
  neutralMinionsKilledEnemyJungle: Int,
  neutralMinionsKilledTeamJungle: Int,
  objectivePlayerScore: Int,
  pentaKills: Int,
  physicalDamageDealt: Int,
  physicalDamageDealtToChampions: Int,
  physicalDamageTaken: Int,
  quadraKills: Int,
  sightWardsBoughtInGame: Int,
  totalDamageDealt: Int,
  totalDamageDealtToChampions: Int,
  totalDamageTaken: Int,
  totalHeal: Int,
  totalPlayerScore: Int,
  totalScoreRank: Int,
  totalTimeCrowdControlDealt: Int,
  totalUnitsHealed: Int,
  towerKills: Int,
  tripleKills: Int,
  trueDamageDealt: Int,
  trueDamageDealtToChampions: Int,
  trueDamageTaken: Int,
  unrealKills: Int,
  visionWardsBoughtInGame: Int,
  wardsKilled: Int,
  wardsPlaced: Int,
  winner: Boolean
)

case class Teams (
  firstBaron: Boolean,
  winner: Boolean,
  inhibitorKills: Int,
  dragonKills: Int,
  teamId: Int,
  firstBlood: Boolean,
  firstDragon: Boolean,
  bans: List[Bans],
  towerKills: Int,
  dominionVictoryScore: Int,
  vilemawKills: Int,
  riftHeraldKills: Int,
  firstRiftHerald: Boolean,
  baronKills: Int,
  firstTower: Boolean,
  firstInhibitor: Boolean
)

case class Bans (
  championId: Int,
  pickTurn: Int
)

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
)
