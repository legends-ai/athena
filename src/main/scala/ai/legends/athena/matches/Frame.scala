package ai.legends.athena.matches

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  timestamp: Int
)
