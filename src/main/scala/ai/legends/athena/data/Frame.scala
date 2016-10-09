package io.asuna.asunasan.legends.riotmatch

case class Frame (
  participantFrames: Map[String, ParticipantFrame],
  events: List[Event],
  timestamp: Int
)

