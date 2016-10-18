package ai.legends.athena.sum

import io.asuna.proto.enums.Region
import io.asuna.proto.enums.Role
import io.asuna.proto.match_filters.MatchFilters
import io.asuna.proto.match_sum.MatchSum
import io.asuna.proto.charon.CharonData.Match
import io.asuna.proto.charon.CharonData.Match.ParticipantInfo
import io.asuna.proto.charon.CharonData.Match.ParticipantInfo.Timeline.Delta
import io.asuna.proto.charon.CharonData.Match.ParticipantInfo.{ Mastery, Rune }
import io.asuna.proto.charon.CharonData.Match.Timeline
import io.asuna.proto.charon.CharonData.Match.Timeline.Frame.Event
import io.asuna.asunasan.legends.MatchSumOperators._
import io.asuna.asunasan.legends.RiotMatchHelpers._

case class MatchSumRow(filters: MatchFilters, sum: MatchSum)

object MatchSumRow {

  def fromData(m: Match, p: ParticipantInfo, rank: Long): MatchSumRow = {
    // Should never be null, but we lazy
    val stats = p.stats.get
    val timeline = p.timeline.get

    val subscalars = MatchSum.Subscalars(
      plays = 1,
      wins = (if (stats.winner) 1 else 0)
    )

    val enemyId = m.participantInfo.find { other =>
      other.role == p.role && other != p
    }.map(_.championId).getOrElse(0)

    MatchSumRow(

      MatchFilters(
        championId = p.championId,
        enemyId = enemyId,
        patch = m.patch,
        tier = tierFromRank(rank),
        region = m.region,
        role = p.role
      ),

      MatchSum(
        scalars = Some(MatchSum.Scalars(
          plays = 1,
          wins = (if (stats.winner) 1 else 0),
          goldEarned = stats.goldEarned,
          kills = stats.kills,
          deaths = stats.deaths,
          assists = stats.assists,
          damageDealt = stats.totalDamageDealtToChampions,
          damageTaken = stats.totalDamageTaken,
          minionsKilled = stats.minionsKilled,
          teamJungleMinionsKilled = stats.neutralMinionsKilledTeamJungle,
          enemyJungleMinionsKilled = stats.neutralMinionsKilledEnemyJungle,
          structureDamage = 1,
          killingSpree = stats.largestKillingSpree,
          wardsBought = stats.sightWardsBought + stats.visionWardsBought,
          wardsPlaced = stats.wardsPlaced,
          wardsKilled = stats.wardsKilled,
          crowdControl = stats.totalTimeCrowdControlDealt,
          firstBlood = (if (stats.firstBloodKill) 1 else 0),
          firstBloodAssist = (if (stats.firstBloodAssist) 1 else 0),
          doublekills = stats.doubleKills,
          triplekills = stats.tripleKills,
          quadrakills = stats.quadraKills,
          pentakills = stats.pentaKills,

          physicalDamage = stats.physicalDamageDealt,
          magicDamage = stats.magicDamageDealt,
          trueDamage = stats.trueDamageDealt
        )),

        deltas = Some(MatchSum.Deltas(
          csDiff = deltaFromDeltas(timeline.creepsPerMin),
          xpDiff = deltaFromDeltas(timeline.xpDiffPerMin),
          damageTakenDiff = deltaFromDeltas(timeline.damageTakenPerMin),
          xpPerMin = deltaFromDeltas(timeline.xpPerMin),
          goldPerMin = deltaFromDeltas(timeline.goldPerMin),
          towersPerMin = deltaFromDeltas(timeline.towerKillsPerMin),
          wardsPlaced = deltaFromDeltas(timeline.wardsPerMin),
          damageTaken = deltaFromDeltas(timeline.damageTakenPerMin)
        )),

        masteries = Map(p.masteries.toMasteriesString -> subscalars),

        runes = Map(p.runes.toRunesString -> subscalars),

        keystones = Map(p.masteries.toKeystoneString -> subscalars),

        summoners = Map(p.summonersString -> subscalars),

        trinkets = Map(p.trinket.toInt -> subscalars),

        skillOrders = Map(m.events.buildSkillOrder(p.participantId) -> subscalars),

        durationDistribution = Some(MatchSum.DurationDistribution(
          zeroToTen = 1,
          tenToTwenty = (if (m.duration > 60 * 10) 1 else 0),
          twentyToThirty = (if (m.duration > 60 * 20) 1 else 0),
          thirtyToEnd = (if (m.duration > 60 * 30) 1 else 0)
        )),

        durations = Map((m.duration % 60).toInt -> subscalars),

        bans = m.teamInfo.flatMap(_.bans).map((ban) => (ban.championId, subscalars)).toMap,

        allies = m.participantInfo.filter(_.teamId == p.teamId).map((ally) => (ally.championId, subscalars)).toMap,

        enemies = m.participantInfo.filter(_.teamId != p.teamId).map((enemy) => (enemy.championId, subscalars)).toMap,

        starterItems = m.timeline match {
          case Some(t) => Map(m.events.findStarterItems(p) -> subscalars)
          case None => Map()
        },

        buildPath = m.timeline match {
          case Some(t) => Map(m.events.findBuildPath(p) -> subscalars)
          case None => Map()
        }

      )

    )
  }

  def deltaFromDeltas(delta: Option[Delta]): Option[MatchSum.Deltas.Delta] = {
    delta match {
      case Some(d) => Some(MatchSum.Deltas.Delta(
        zeroToTen = d.zeroToTen,
        tenToTwenty = d.tenToTwenty,
        twentyToThirty = d.twentyToThirty,
        thirtyToEnd = d.thirtyToEnd
      ))
      case None => None
    }
  }

  def tierFromRank(rank: Long): Int = {
    ((rank >> 16) & 0xffff).toInt
  }

}
