package ai.legends.athena.sum

import ai.legends.athena.data.Match
import ai.legends.athena.data.Participant
import io.asuna.proto.enums.Role
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum

case class MatchSumRow(filters: MatchFilters, sum: MatchSum)

object MatchSumRow {

  def apply(m: Match, p: Participant): MatchSumRow = {
    MatchSumRow(

      MatchFilters(
        championId = p.championId
      ),

      MatchSum(
        scalars = Some(MatchSum.Scalars(
          plays = 1,
          wins = (if (p.stats.winner) 1 else 0),
          goldEarned = p.stats.goldEarned,
          kills = p.stats.kills,
          deaths = p.stats.deaths,
          assists = p.stats.assists,
          damageDealt = p.stats.totalDamageDealtToChampions,
          damageTaken = p.stats.totalDamageTaken,
          minionsKilled = p.stats.minionsKilled,
          teamJungleMinionsKilled = p.stats.neutralMinionsKilledTeamJungle,
          enemyJungleMinionsKilled = p.stats.neutralMinionsKilledEnemyJungle,
          structureDamage = 1,
          killingSpree = p.stats.largestKillingSpree,
          wardsBought = p.stats.sightWardsBoughtInGame + p.stats.visionWardsBoughtInGame,
          wardsPlaced = p.stats.wardsPlaced,
          wardsKilled = p.stats.wardsKilled,
          crowdControl = p.stats.totalTimeCrowdControlDealt,
          firstBlood = (if (p.stats.firstBloodKill) 1 else 0),
          firstBloodAssist = (if (p.stats.firstBloodAssist) 1 else 0),

          // TODO(igm): differentials

          doubleKills = p.stats.doubleKills,
          tripleKills = p.stats.tripleKills,
          quadrakills = p.stats.quadraKills,
          pentakills = p.stats.pentaKills
        ))
      )

    )
  }

  def roleFromString(lane: String, role: String): Role = {
    (lane, role) match {
      case ("TOP", _) => Role.TOP
      case ("MID", _) => Role.MID
      case ("BOTTOM", "DUO_CARRY") => Role.BOT
      case ("BOTTOM", "DUO_SUPPORT") => Role.SUPPORT
      case ("JUNGLE", _) => Role.JUNGLE
      case _ => Role.UNKNOWN
    }
  }

}
