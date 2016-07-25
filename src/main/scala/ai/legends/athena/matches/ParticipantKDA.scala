package ai.legends.athena.matches

import org.json4s.JsonAST.JValue

case class ParticipantKDA (
  assists: Int,
  deaths: Int,
  kills: Int,
  killingSprees: Int,
  pentaKills: Int,
  quadraKills: Int,
  tripleKills: Int,
  unrealKills: Int,
  largestKillingSpree: Int,
  largestMultiKill: Int,
  inhibitorKills: Int,
  minionsKilled: Int,
  towerKills: Int,
  neutralMinionsKilled: Int,
  neutralMinionsKilledEnemyJungle: Int,
  neutralMinionsKilledTeamJungle: Int,
  doubleKills: Int
)

object ParticipantKDA {
  val fields =
    "assists" ::
    "deaths" ::
    "kills" ::
    "killingSprees" ::
    "pentaKills" ::
    "quadraKills" ::
    "tripleKills" ::
    "unrealKills" ::
    "largestKillingSpree" ::
    "largestMultiKill" ::
    "inhibitorKills" ::
    "minionsKilled" ::
    "towerKills" ::
    "neutralMinionsKilled" ::
    "neutralMinionsKilledEnemyJungle" ::
    "neutralMinionsKilledTeamJungle" ::
    "doubleKills" :: Nil
}
