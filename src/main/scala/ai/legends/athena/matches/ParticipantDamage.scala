package ai.legends.athena.matches

import org.json4s.JsonAST.JValue

case class ParticipantDamage (
  magicDamageDealt: Int,
  magicDamageDealtToChampions: Int,
  magicDamageTaken: Int,
  physicalDamageDealt: Int,
  physicalDamageDealtToChampions: Int,
  physicalDamageTaken: Int,
  totalDamageDealt: Int,
  totalDamageDealtToChampions: Int,
  trueDamageDealt: Int,
  trueDamageDealtToChampions: Int,
  trueDamageTaken: Int,
  largestCriticalStrike: Int,
  totalDamageTaken: Int
)

object ParticipantDamage {
  val fields =
    "magicDamageDealt" ::
    "magicDamageDealtToChampions" ::
    "magicDamageTaken" ::
    "physicalDamageDealt" ::
    "physicalDamageDealtToChampions" ::
    "physicalDamageTaken" ::
    "totalDamageDealt" ::
    "totalDamageDealtToChampions" ::
    "trueDamageDealt" ::
    "trueDamageDealtToChampions" ::
    "trueDamageTaken" ::
    "largestCriticalStrike" ::
    "totalDamageTaken" :: Nil
}
