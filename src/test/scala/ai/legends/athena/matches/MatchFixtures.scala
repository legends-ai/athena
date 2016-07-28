package ai.legends.athena.matches

import org.json4s._
import org.json4s.jackson.JsonMethods._
import ai.legends.athena.aggregates.{ ParticipantItems, ParticipantKDA }

object MatchFixtures {

  val statsStr =
    """
    {
      "assists": 2,
      "champLevel": 11,
      "combatPlayerScore": 0,
      "deaths": 4,
      "doubleKills": 0,
      "firstBloodAssist": false,
      "firstBloodKill": true,
      "firstInhibitorAssist": false,
      "firstInhibitorKill": false,
      "firstTowerAssist": false,
      "firstTowerKill": false,
      "goldEarned": 7992,
      "goldSpent": 7225,
      "inhibitorKills": 0,
      "item0": 1409,
      "item1": 2033,
      "item2": 3009,
      "item3": 3742,
      "item4": 1029,
      "item5": 0,
      "item6": 3340,
      "killingSprees": 1,
      "kills": 6,
      "largestCriticalStrike": 0,
      "largestKillingSpree": 5,
      "largestMultiKill": 1,
      "magicDamageDealt": 45218,
      "magicDamageDealtToChampions": 7375,
      "magicDamageTaken": 3572,
      "minionsKilled": 11,
      "neutralMinionsKilled": 62,
      "neutralMinionsKilledEnemyJungle": 7,
      "neutralMinionsKilledTeamJungle": 55,
      "objectivePlayerScore": 0,
      "pentaKills": 0,
      "physicalDamageDealt": 17040,
      "physicalDamageDealtToChampions": 2002,
      "physicalDamageTaken": 17096,
      "quadraKills": 0,
      "sightWardsBoughtInGame": 0,
      "totalDamageDealt": 72640,
      "totalDamageDealtToChampions": 9944,
      "totalDamageTaken": 23109,
      "totalHeal": 6020,
      "totalPlayerScore": 0,
      "totalScoreRank": 0,
      "totalTimeCrowdControlDealt": 578,
      "totalUnitsHealed": 1,
      "towerKills": 0,
      "tripleKills": 0,
      "trueDamageDealt": 10381,
      "trueDamageDealtToChampions": 566,
      "trueDamageTaken": 2440,
      "unrealKills": 0,
      "visionWardsBoughtInGame": 0,
      "wardsKilled": 4,
      "wardsPlaced": 11,
      "winner": false
    }
    """

  val statsJSON = parse(statsStr).asInstanceOf[JObject]

  val participantStr = """
    {
      "championId": 57,
      "highestAchievedSeasonTier": "GOLD",
      "masteries": [
        {
          "masteryId": 6261,
          "rank": 1
        }
      ],
      "participantId": 1,
      "runes": [
        {
          "rank": 3,
          "runeId": 5365
        }
      ],
      "spell1Id": 4,
      "spell2Id": 11,
      "stats": {
        "assists": 2,
        "champLevel": 11,
        "combatPlayerScore": 0,
        "deaths": 4,
        "doubleKills": 0,
        "firstBloodAssist": false,
        "firstBloodKill": true,
        "firstInhibitorAssist": false,
        "firstInhibitorKill": false,
        "firstTowerAssist": false,
        "firstTowerKill": false,
        "goldEarned": 7992,
        "goldSpent": 7225,
        "inhibitorKills": 0,
        "item0": 1409,
        "item1": 2033,
        "item2": 3009,
        "item3": 3742,
        "item4": 1029,
        "item5": 0,
        "item6": 3340,
        "killingSprees": 1,
        "kills": 6,
        "largestCriticalStrike": 0,
        "largestKillingSpree": 5,
        "largestMultiKill": 1,
        "magicDamageDealt": 45218,
        "magicDamageDealtToChampions": 7375,
        "magicDamageTaken": 3572,
        "minionsKilled": 11,
        "neutralMinionsKilled": 62,
        "neutralMinionsKilledEnemyJungle": 7,
        "neutralMinionsKilledTeamJungle": 55,
        "objectivePlayerScore": 0,
        "pentaKills": 0,
        "physicalDamageDealt": 17040,
        "physicalDamageDealtToChampions": 2002,
        "physicalDamageTaken": 17096,
        "quadraKills": 0,
        "sightWardsBoughtInGame": 0,
        "totalDamageDealt": 72640,
        "totalDamageDealtToChampions": 9944,
        "totalDamageTaken": 23109,
        "totalHeal": 6020,
        "totalPlayerScore": 0,
        "totalScoreRank": 0,
        "totalTimeCrowdControlDealt": 578,
        "totalUnitsHealed": 1,
        "towerKills": 0,
        "tripleKills": 0,
        "trueDamageDealt": 10381,
        "trueDamageDealtToChampions": 566,
        "trueDamageTaken": 2440,
        "unrealKills": 0,
        "visionWardsBoughtInGame": 0,
        "wardsKilled": 4,
        "wardsPlaced": 11,
        "winner": false
      },
      "teamId": 100
    }
  """

  val participantJSON = parse(participantStr).asInstanceOf[JObject]

  val participant2Str = """
  {
    "championId": 56,
    "highestAchievedSeasonTier": "GOLD",
    "masteries": [
      {
        "masteryId": 6261,
        "rank": 1
      }
    ],
    "participantId": 1,
    "runes": [
      {
        "rank": 3,
        "runeId": 5365
      }
    ],
    "spell1Id": 4,
    "spell2Id": 11,
    "stats": {
      "assists": 2,
      "champLevel": 11,
      "combatPlayerScore": 0,
      "deaths": 4,
      "doubleKills": 0,
      "firstBloodAssist": false,
      "firstBloodKill": true,
      "firstInhibitorAssist": false,
      "firstInhibitorKill": false,
      "firstTowerAssist": false,
      "firstTowerKill": false,
      "goldEarned": 7992,
      "goldSpent": 7225,
      "inhibitorKills": 0,
      "item0": 1409,
      "item1": 2033,
      "item2": 3009,
      "item3": 3742,
      "item4": 1029,
      "item5": 0,
      "item6": 3340,
      "killingSprees": 1,
      "kills": 6,
      "largestCriticalStrike": 0,
      "largestKillingSpree": 5,
      "largestMultiKill": 1,
      "magicDamageDealt": 45218,
      "magicDamageDealtToChampions": 7375,
      "magicDamageTaken": 3572,
      "minionsKilled": 11,
      "neutralMinionsKilled": 62,
      "neutralMinionsKilledEnemyJungle": 7,
      "neutralMinionsKilledTeamJungle": 55,
      "objectivePlayerScore": 0,
      "pentaKills": 0,
      "physicalDamageDealt": 17040,
      "physicalDamageDealtToChampions": 2002,
      "physicalDamageTaken": 17096,
      "quadraKills": 0,
      "sightWardsBoughtInGame": 0,
      "totalDamageDealt": 72640,
      "totalDamageDealtToChampions": 9944,
      "totalDamageTaken": 23109,
      "totalHeal": 6020,
      "totalPlayerScore": 0,
      "totalScoreRank": 0,
      "totalTimeCrowdControlDealt": 578,
      "totalUnitsHealed": 1,
      "towerKills": 0,
      "tripleKills": 0,
      "trueDamageDealt": 10381,
      "trueDamageDealtToChampions": 566,
      "trueDamageTaken": 2440,
      "unrealKills": 0,
      "visionWardsBoughtInGame": 0,
      "wardsKilled": 4,
      "wardsPlaced": 11,
      "winner": true
    },
    "teamId": 100
  }
  """

  val participantsStr = s"""
  [
    ${participantStr},
    ${participant2Str}
  ]
  """

  val participantsJSON = parse(participantsStr).asInstanceOf[JArray]

  val matchStr = s"""
  {
     "asdf": 123,
     "participants": ${participantsStr}
  }
  """

  val matchJSON = parse(matchStr).asInstanceOf[JValue]

  val stats = ParticipantStats(
    winner = false,
    champLevel = 11,
    combatPlayerScore = 0,
    goldEarned = 7992,
    goldSpent = 7225,
    objectivePlayerScore = 0,
    totalHeal = 6020,
    totalPlayerScore = 0,
    totalScoreRank = 0,
    totalTimeCrowdControlDealt = 578,
    totalUnitsHealed = 1,
    damage = ParticipantDamage(
      magicDamageDealt = 45218,
      magicDamageDealtToChampions = 7375,
      magicDamageTaken = 3572,
      physicalDamageDealt = 17040,
      physicalDamageDealtToChampions = 2002,
      physicalDamageTaken = 17096,
      totalDamageDealt = 72640,
      totalDamageDealtToChampions = 9944,
      trueDamageDealt = 10381,
      trueDamageDealtToChampions = 566,
      trueDamageTaken = 2440,
      largestCriticalStrike = 0,
      totalDamageTaken = 23109
    ),
    firsts = ParticipantFirsts(
      firstBloodAssist = false,
      firstBloodKill = true,
      firstInhibitorAssist = false,
      firstInhibitorKill = false,
      firstTowerAssist = false,
      firstTowerKill = false
    ),
    items = ParticipantItems(
      item0 = 1409,
      item1 = 2033,
      item2 = 3009,
      item3 = 3742,
      item4 = 1029,
      item5 = 0,
      item6 = 3340
    ),
    kda = ParticipantKDA(
      assists = 2,
      deaths = 4,
      kills = 6,
      killingSprees = 1,
      pentaKills = 0,
      quadraKills = 0,
      tripleKills = 0,
      unrealKills = 0,
      largestKillingSpree = 5,
      largestMultiKill = 1,
      inhibitorKills = 0,
      minionsKilled = 11,
      towerKills = 0,
      neutralMinionsKilled = 62,
      neutralMinionsKilledEnemyJungle = 7,
      neutralMinionsKilledTeamJungle = 55,
      doubleKills = 0
    ),
    wards = ParticipantWards(
      visionWardsBoughtInGame = 0,
      sightWardsBoughtInGame = 0,
      wardsKilled = 4,
      wardsPlaced = 11
    )
  )

  val participant = Participant(
    stats = stats,
    highestAchievedSeasonTier = "GOLD",
    teamId = 100,
    spell1Id = 4,
    spell2Id = 11,
    masteries = Mastery(
      masteryId = 6261,
      rank = 1
    ) :: Nil,
    participantId = 1,
    timeline = None,
    championId = 57,
    runes = Rune(
      rank = 3,
      runeId = 5365
    ) :: Nil
  )

}
