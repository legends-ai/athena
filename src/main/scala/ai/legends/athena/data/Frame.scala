package ai.legends.athena.data

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  timestamp: Int
)

