package ai.legends.athena.matches

case class ParticipantTimeline (
  goldPerMinDeltas: Deltas,
  damageTakenPerMinDeltas: Deltas,
  role: String,
  xpDiffPerMinDeltas: Deltas,
  lane: String,
  xpPerMinDeltas: Deltas,
  creepsPerMinDeltas: Deltas,
  damageTakenDiffPerMinDeltas: Deltas,
  csDiffPerMinDeltas: Deltas
)
