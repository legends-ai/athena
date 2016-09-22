package ai.legends.athena.data

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
  riftHeraldKills: Option[Int],
  firstRiftHerald: Option[Boolean],
  baronKills: Int,
  firstTower: Boolean,
  firstInhibitor: Boolean
)
