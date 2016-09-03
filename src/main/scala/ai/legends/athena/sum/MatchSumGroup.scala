package ai.legends.athena.sum

import io.asuna.proto.match_sum.MatchSum
import io.asuna.proto.match_sum.MatchSum.{Scalars => MatchSumScalars}
import io.asuna.proto.match_sum.MatchSum.{Subscalars => MatchSumSubscalars}

object MatchSumGroup {

  implicit class MatchSumCombiners(val a: MatchSum) {

    /** + adds match sums together. */
    def +(b: MatchSum): MatchSum = {
      MatchSum(
        scalars = for (x <- a.scalars; y <- b.scalars) yield x + y,
        masteries = a.masteries |+| b.masteries,
        runes = a.runes |+| b.runes,
        keystones = a.keystones |+| b.keystones,
        summoners = a.summoners |+| b.summoners,
        trinkets = a.trinkets |+| b.trinkets,
        skillOrders = a.skillOrders |+| b.skillOrders
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
        csDiff = a.csDiff + b.csDiff,
        damageTakenDiff = a.damageTakenDiff + b.damageTakenDiff,
        xpDiff = a.xpDiff + b.xpDiff,
        doubleKills = a.doubleKills + b.doubleKills,
        tripleKills = a.tripleKills + b.tripleKills,
        quadrakills = a.quadrakills + b.quadrakills,
        pentakills = a.pentakills + b.pentakills
      )
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
