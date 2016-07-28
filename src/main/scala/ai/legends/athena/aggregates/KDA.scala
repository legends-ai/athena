package ai.legends.athena.aggregates

case class ChampionKDATotals (
  assists: Long,
  deaths: Long,
  kills: Long,
  killingSprees: Long,
  pentaKills: Long,
  quadraKills: Long,
  tripleKills: Long,
  unrealKills: Long,
  largestKillingSpree: Long,
  largestMultiKill: Long,
  inhibitorKills: Long,
  minionsKilled: Long,
  towerKills: Long,
  neutralMinionsKilled: Long,
  neutralMinionsKilledEnemyJungle: Long,
  neutralMinionsKilledTeamJungle: Long,
  doubleKills: Long
) {

  def +(kda: ChampionKDATotals): ChampionKDATotals = {
    ChampionKDATotals(
      assists + kda.assists,
      deaths + kda.deaths,
      kills + kda.kills,
      killingSprees + kda.killingSprees,
      pentaKills + kda.pentaKills,
      quadraKills + kda.quadraKills,
      tripleKills + kda.tripleKills,
      unrealKills + kda.unrealKills,
      largestKillingSpree + kda.largestKillingSpree,
      largestMultiKill + kda.largestMultiKill,
      inhibitorKills + kda.inhibitorKills,
      minionsKilled + kda.minionsKilled,
      towerKills + kda.towerKills,
      neutralMinionsKilled + kda.neutralMinionsKilled,
      neutralMinionsKilledEnemyJungle + kda.neutralMinionsKilledEnemyJungle,
      neutralMinionsKilledTeamJungle + kda.neutralMinionsKilledTeamJungle,
      doubleKills + kda.doubleKills
    )
  }

  def +(kda: ParticipantKDA): ChampionKDATotals = {
    ChampionKDATotals(
      assists + kda.assists,
      deaths + kda.deaths,
      kills + kda.kills,
      killingSprees + kda.killingSprees,
      pentaKills + kda.pentaKills,
      quadraKills + kda.quadraKills,
      tripleKills + kda.tripleKills,
      unrealKills + kda.unrealKills,
      largestKillingSpree + kda.largestKillingSpree,
      largestMultiKill + kda.largestMultiKill,
      inhibitorKills + kda.inhibitorKills,
      minionsKilled + kda.minionsKilled,
      towerKills + kda.towerKills,
      neutralMinionsKilled + kda.neutralMinionsKilled,
      neutralMinionsKilledEnemyJungle + kda.neutralMinionsKilledEnemyJungle,
      neutralMinionsKilledTeamJungle + kda.neutralMinionsKilledTeamJungle,
      doubleKills + kda.doubleKills
    )
  }

}

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
  val fields = classOf[ParticipantKDA].getDeclaredFields.map(_.getName).toList
}
