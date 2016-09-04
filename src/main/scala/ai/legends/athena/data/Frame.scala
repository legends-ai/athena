package ai.legends.athena.data

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  events: List[Event],
  timestamp: Int
)

