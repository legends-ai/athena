package ai.legends.athena.matches

import org.json4s.JsonAST.JObject
import org.json4s.JsonDSL._
import org.json4s.JsonAST.JValue

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
  def jsonFromParticipants(obj: JValue): JValue = {
    ("damage" -> extract(obj, ParticipantDamage.fields)) ~
    ("firsts" -> extract(obj, ParticipantFirsts.fields)) ~
    ("items" -> extract(obj, ParticipantItems.fields)) ~
    ("kda" -> extract(obj, ParticipantKDA.fields)) ~
    ("wards" -> extract(obj, ParticipantWards.fields))
  }
  def extract(obj: JValue, vals: List[String]): JValue = {
    vals.map(x => (x -> (obj \ x))).foldLeft(JObject())(_ ~ _)
  }
}
