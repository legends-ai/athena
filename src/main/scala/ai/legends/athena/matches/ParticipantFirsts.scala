package ai.legends.athena.matches

import org.json4s.JsonAST.JValue

case class ParticipantFirsts (
  firstBloodAssist: Boolean,
  firstBloodKill: Boolean,
  firstInhibitorAssist: Boolean,
  firstInhibitorKill: Boolean,
  firstTowerAssist: Boolean,
  firstTowerKill: Boolean
)

object ParticipantFirsts {
  val fields =
    "firstBloodAssist" ::
    "firstBloodKill" ::
    "firstInhibitorAssist" ::
    "firstInhibitorKill" ::
    "firstTowerAssist" ::
    "firstTowerKill" :: Nil
}
