package ai.legends.athena.champions

case class ChampionScalars(
  winRate: Double,
  playRate: Double,
  banRate: Double,
  avgGamesPlayed: Double,
  goldEarned: Double,
  assists: Double,
  deaths: Double,
  kills: Double,
  heal: Double,
  damageDealt: Double,
  damageTaken: Double,
  minionsKilled: Double,
  towersTaken: Double,
  pinkWardsBought: Double,
  wardsPlaced: Double,
  wardsKilled: Double,
  averageMultiKill: Double,
  enemyJungleMinionsKilled: Double
)

object ChampionScalars {

  def apply(data: ChampionData): ChampionScalars = {
    val plays = data.participantAgg.stats.plays
    ChampionScalars(
      winRate = data.participantAgg.stats.winner.toDouble / plays,
      playRate = data.participantAgg.stats.plays.toDouble / data.count,
      banRate = data.bans.toDouble / data.count,
      avgGamesPlayed = data.participantAgg.playsByPlayer.size.toDouble / plays,
      goldEarned = data.participantAgg.stats.goldEarned.toDouble / plays,
      assists = data.participantAgg.stats.kda.assists.toDouble / plays,
      deaths = data.participantAgg.stats.kda.deaths.toDouble / plays,
      kills = data.participantAgg.stats.kda.kills.toDouble / plays,
      heal = data.participantAgg.stats.totalHeal.toDouble / plays,
      damageDealt = data.participantAgg.stats.damage.totalDamageDealt.toDouble / plays,
      damageTaken = data.participantAgg.stats.damage.totalDamageTaken.toDouble / plays,
      minionsKilled = data.participantAgg.stats.kda.minionsKilled.toDouble / plays,
      towersTaken = data.participantAgg.stats.kda.towerKills.toDouble / plays,
      pinkWardsBought = data.participantAgg.stats.wards.visionWardsBoughtInGame.toDouble / plays,
      wardsPlaced = data.participantAgg.stats.wards.wardsPlaced.toDouble / plays,
      wardsKilled = data.participantAgg.stats.wards.wardsKilled.toDouble / plays,
      averageMultiKill = averageMultiKill(data),
      enemyJungleMinionsKilled = data.participantAgg.stats.kda.neutralMinionsKilledEnemyJungle.toDouble / plays
    )
  }

  def averageMultiKill(data: ChampionData): Double = {
    val kda = data.participantAgg.stats.kda
    (kda.unrealKills * 6L + kda.pentaKills * 5L + kda.quadraKills * 4L + kda.tripleKills * 3L + kda.doubleKills * 2L).toDouble / (kda.unrealKills + kda.pentaKills + kda.quadraKills + kda.tripleKills + kda.doubleKills)
  }

}


