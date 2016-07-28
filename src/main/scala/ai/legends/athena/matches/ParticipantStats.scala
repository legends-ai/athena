package ai.legends.athena.matches

import ai.legends.athena.aggregates.ParticipantKDA
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

case class ParticipantStats (
  winner: Boolean,
  champLevel: Int,
  combatPlayerScore: Int,
  goldEarned: Int,
  goldSpent: Int,
  objectivePlayerScore: Int,
  totalHeal: Int,
  totalPlayerScore: Int,
  totalScoreRank: Int,
  totalTimeCrowdControlDealt: Int,
  totalUnitsHealed: Int,
  damage: ParticipantDamage,
  firsts: ParticipantFirsts,
  items: ParticipantItems,
  kda: ParticipantKDA,
  wards: ParticipantWards
)

object ParticipantStats {
  def jsonFromParticipants(obj: JObject): JValue = {
    obj ~
      JField("damage", extract(obj, ParticipantDamage.fields)) ~
      JField("firsts", extract(obj, ParticipantFirsts.fields)) ~
      JField("items", extract(obj, ParticipantItems.fields)) ~
      JField("kda", extract(obj, ParticipantKDA.fields)) ~
      JField("wards", extract(obj, ParticipantWards.fields))
  }

  def extract(obj: JObject, keys: List[String]): JValue = {
    keys.map(key => (key -> (obj \ key))).foldLeft(JObject())(_ ~ _)
  }
}
