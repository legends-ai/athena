package io.asuna.asunasan.legends.riotmatch

case class ParticipantFrame (
  totalGold: Int,
  dominionScore: Int,
  currentGold: Int,
  jungleMinionsKilled: Int,
  participantId: Int,
  xp: Int,
  position: Position,
  teamScore: Int,
  minionsKilled: Int,
  level: Int
)

