package ai.legends.athena.sum

import ai.legends.athena.data.Match
import ai.legends.athena.data.Participant
import ai.legends.athena.data.Deltas
import ai.legends.athena.data.{ Mastery, Rune, Event }
import io.asuna.proto.enums.Region
import io.asuna.proto.enums.Role
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum

case class MatchSumRow(filters: MatchFilters, sum: MatchSum)

object MatchSumRow {

  def fromData(m: Match, p: Participant, rank: Long): MatchSumRow = {
    val subscalars = MatchSum.Subscalars(
      plays = 1,
      wins = (if (p.stats.winner) 1 else 0)
    )

    val enemyId = m.participants.find { other =>
      other.timeline.role == p.timeline.role &&
      other.timeline.lane == p.timeline.lane &&
      other != p
    }.map(_.championId).getOrElse(0)

    MatchSumRow(

      MatchFilters(
        championId = p.championId,
        enemyId = enemyId,
        patch = patchFromVersion(m.matchVersion),
        tier = tierFromRank(rank),
        region = regionFromString(m.region),
        role = roleFromString(p.timeline.lane, p.timeline.role)
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
          doublekills = p.stats.doubleKills,
          triplekills = p.stats.tripleKills,
          quadrakills = p.stats.quadraKills,
          pentakills = p.stats.pentaKills
        )),

        deltas = Some(MatchSum.Deltas(
          csDiff = deltaFromDeltas(p.timeline.creepsPerMinDeltas),
          xpDiff = deltaFromDeltas(p.timeline.xpDiffPerMinDeltas),
          damageTakenDiff = deltaFromDeltas(p.timeline.damageTakenPerMinDeltas),
          xpPerMin = deltaFromDeltas(p.timeline.xpPerMinDeltas),
          goldPerMin = deltaFromDeltas(p.timeline.goldPerMinDeltas),
          towersPerMin = deltaFromDeltas(p.timeline.towerKillsPerMinDeltas),
          wardsPlaced = deltaFromDeltas(p.timeline.wardsPerMinDeltas),
          damageTaken = deltaFromDeltas(p.timeline.damageTakenPerMinDeltas)
        )),

        masteries = Map(Mastery.listToString(p.masteries) -> subscalars),

        runes = Map(Rune.listToString(p.runes) -> subscalars),

        keystones = Map(Mastery.listToKeystoneString(p.masteries) -> subscalars),

        summoners = Map(p.summonersString -> subscalars),

        trinkets = Map(p.stats.item6 -> subscalars),

        skillOrders = Map(Event.buildSkillOrder(m.events, p.participantId) -> subscalars),

        durationDistribution = Some(MatchSum.DurationDistribution(
          zeroToTen = 1,
          tenToTwenty = (if (m.matchDuration > 60 * 10) 1 else 0),
          twentyToThirty = (if (m.matchDuration > 60 * 20) 1 else 0),
          thirtyToEnd = (if (m.matchDuration > 60 * 30) 1 else 0)
        ))

      )

    )
  }

  def deltaFromDeltas(delta: Option[Deltas]): Option[MatchSum.Deltas.Delta] = {
    delta match {
      case Some(d) => Some(MatchSum.Deltas.Delta(
        zeroToTen = d.zeroToTen.getOrElse(0),
        tenToTwenty = d.tenToTwenty.getOrElse(0),
        twentyToThirty = d.twentyToThirty.getOrElse(0),
        thirtyToEnd = d.thirtyToEnd.getOrElse(0)
      ))
      case None => None
    }
  }

  def regionFromString(str: String): Region = {
    Region.values.find(_.name == str).getOrElse(Region.UNKNOWN_REGION)
  }

  def roleFromString(lane: String, role: String): Role = {
    (lane, role) match {
      case ("TOP", _) => Role.TOP
      case ("MID", _) => Role.MID
      case ("BOTTOM", "DUO_CARRY") => Role.BOT
      case ("BOTTOM", "DUO_SUPPORT") => Role.SUPPORT
      case ("JUNGLE", _) => Role.JUNGLE
      case _ => Role.UNKNOWN_ROLE
    }
  }

  def patchFromVersion(version: String): String = {
    version.split("\\.").slice(0, 2).mkString(".")
  }

  def tierFromRank(rank: Long): Int = {
    ((rank >> 16) & 0xffff).toInt
  }

}
