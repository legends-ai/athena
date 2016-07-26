package ai.legends.athena.matches

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
