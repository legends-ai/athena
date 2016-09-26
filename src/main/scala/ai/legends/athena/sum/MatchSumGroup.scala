package ai.legends.athena.sum

import io.asuna.proto.match_sum.MatchSum
import io.asuna.proto.match_sum.MatchSum.{Scalars => MatchSumScalars}
import io.asuna.proto.match_sum.MatchSum.{Deltas => MatchSumDeltas}
import io.asuna.proto.match_sum.MatchSum.{Subscalars => MatchSumSubscalars}

object MatchSumGroup {

  implicit class MatchSumCombiners(val a: MatchSum) {

    /** + adds match sums together. */
    def +(b: MatchSum): MatchSum = {
      MatchSum(
        scalars = Some(a.scalars.getOrElse(MatchSumScalars()) + b.scalars.getOrElse(MatchSumScalars())),
        deltas = a.deltas + b.deltas,
        masteries = a.masteries |+| b.masteries,
        runes = a.runes |+| b.runes,
        keystones = a.keystones |+| b.keystones,
        summoners = a.summoners |+| b.summoners,
        trinkets = a.trinkets |+| b.trinkets,
        skillOrders = a.skillOrders |+| b.skillOrders,
        durationDistribution = a.durationDistribution + b.durationDistribution,
        durations = a.durations |+| b.durations,
        bans = a.bans |+| b.bans,
        allies = a.allies |+| b.allies,
        enemies = a.enemies |+| b.enemies,
        starterItems = a.starterItems |+| b.starterItems,
        buildPath = a.buildPath |+| b.buildPath
      )
    }

  }

  implicit class ScalarsCombiners(val a: MatchSumScalars) {

    /** + adds match sum scalars together. */
    def +(b: MatchSumScalars): MatchSumScalars = {
      MatchSumScalars(
        plays = a.plays + b.plays,
        wins = a.wins + b.wins,
        goldEarned = a.goldEarned + b.goldEarned,
        kills = a.kills + b.kills,
        deaths = a.deaths + b.deaths,
        assists = a.assists + b.assists,
        damageDealt = a.damageDealt + b.damageDealt,
        damageTaken = a.damageTaken + b.damageTaken,
        minionsKilled = a.minionsKilled + b.minionsKilled,
        teamJungleMinionsKilled = a.teamJungleMinionsKilled + b.teamJungleMinionsKilled,
        enemyJungleMinionsKilled = a.enemyJungleMinionsKilled + b.enemyJungleMinionsKilled,
        structureDamage = a.structureDamage + b.structureDamage,
        killingSpree = a.killingSpree + b.killingSpree,
        wardsBought = a.wardsBought + b.wardsBought,
        wardsPlaced = a.wardsPlaced + b.wardsPlaced,
        wardsKilled = a.wardsKilled + b.wardsKilled,
        crowdControl = a.crowdControl + b.crowdControl,
        firstBlood = a.firstBlood + b.firstBlood,
        firstBloodAssist = a.firstBloodAssist + b.firstBloodAssist,
        doublekills = a.doublekills + b.doublekills,
        triplekills = a.triplekills + b.triplekills,
        quadrakills = a.quadrakills + b.quadrakills,
        pentakills = a.pentakills + b.pentakills
      )
    }

  }

  implicit class DeltasCombiners(val a: Option[MatchSumDeltas]) {

    /** + adds match sum scalars together. */
    def +(b: Option[MatchSumDeltas]): Option[MatchSumDeltas] = {
      val av = a.getOrElse(MatchSumDeltas())
      val bv = b.getOrElse(MatchSumDeltas())
      Some(MatchSumDeltas(
        csDiff = av.csDiff + bv.csDiff,
        xpDiff = av.xpDiff + bv.xpDiff,
        damageTakenDiff = av.damageTakenDiff + bv.damageTakenDiff,
        xpPerMin = av.xpPerMin + bv.xpPerMin,
        goldPerMin = av.goldPerMin + bv.goldPerMin,
        towersPerMin = av.towersPerMin + bv.towersPerMin,
        wardsPlaced = av.wardsPlaced + bv.wardsPlaced,
        damageTaken = av.damageTaken + bv.damageTaken
      ))
    }

  }

  implicit class DurationDistributionCombiners(val a: Option[MatchSum.DurationDistribution]) {

    /** + adds match sum scalars together. */
    def +(b: Option[MatchSum.DurationDistribution]): Option[MatchSum.DurationDistribution] = {
      val av = a.getOrElse(MatchSum.DurationDistribution())
      val bv = b.getOrElse(MatchSum.DurationDistribution())
      Some(MatchSum.DurationDistribution(
        zeroToTen = av.zeroToTen + bv.zeroToTen,
        tenToTwenty = av.tenToTwenty + bv.tenToTwenty,
        twentyToThirty = av.twentyToThirty + bv.twentyToThirty,
        thirtyToEnd = av.thirtyToEnd + bv.thirtyToEnd
      ))
    }

  }


  implicit class DeltaCombiners(val a: Option[MatchSumDeltas.Delta]) {

    /** + adds deltas together. */
    def +(b: Option[MatchSumDeltas.Delta]): Option[MatchSumDeltas.Delta] = {
      val av = a.getOrElse(MatchSumDeltas.Delta())
      val bv = b.getOrElse(MatchSumDeltas.Delta())
      Some(MatchSumDeltas.Delta(
        zeroToTen = av.zeroToTen + bv.zeroToTen,
        tenToTwenty = av.tenToTwenty + bv.tenToTwenty,
        twentyToThirty = av.twentyToThirty + bv.twentyToThirty,
        thirtyToEnd = av.thirtyToEnd + bv.thirtyToEnd
      ))
    }

  }

  implicit class SubscalarsCombiners(val a: MatchSumSubscalars) {

    /** + adds match sum scalars together. */
    def +(b: MatchSumSubscalars): MatchSumSubscalars = {
      MatchSumSubscalars(
        plays = a.plays + b.plays,
        wins = a.wins + b.wins
      )
    }

  }

  implicit class SubscalarsMapCombiners[K](val a: Map[K, MatchSumSubscalars]) {

    /** |+| adds map values together by key. */
    def |+|(b: Map[K, MatchSumSubscalars]): Map[K, MatchSumSubscalars] = {
      a.foldLeft(b) {
        case (dict, (k, v)) => dict + (k -> (v + dict.getOrElse(k, MatchSumSubscalars())))
      }
    }

  }

}
