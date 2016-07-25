package ai.legends.athena.matches

import org.json4s.JsonAST.JValue

case class ParticipantWards (
  visionWardsBoughtInGame: Int,
  sightWardsBoughtInGame: Int,
  wardsKilled: Int,
  wardsPlaced: Int
)

object ParticipantWards {
  val fields =
    "visionWardsBoughtInGame" ::
    "sightWardsBoughtInGame" ::
    "wardsKilled" ::
    "wardsPlaced" :: Nil
}
