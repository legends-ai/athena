package ai.legends.athena.aggregates

case class KDAAggregate (
  assists: Long = 0,
  deaths: Long = 0,
  kills: Long = 0,
  killingSprees: Long = 0,
  pentaKills: Long = 0,
  quadraKills: Long = 0,
  tripleKills: Long = 0,
  unrealKills: Long = 0,
  largestKillingSpree: Long = 0,
  largestMultiKill: Long = 0,
  inhibitorKills: Long = 0,
  minionsKilled: Long = 0,
  towerKills: Long = 0,
  neutralMinionsKilled: Long = 0,
  neutralMinionsKilledEnemyJungle: Long = 0,
  neutralMinionsKilledTeamJungle: Long = 0,
  doubleKills: Long = 0
) {

  def +(kda: KDAAggregate): KDAAggregate = {
    KDAAggregate(
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

  def +(kda: ParticipantKDA): KDAAggregate = {
    KDAAggregate(
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
