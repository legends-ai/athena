package ai.legends.athena.data

case class ParticipantTimeline (
  role: String,
  lane: String,
  creepsPerMinDeltas: Option[Deltas],
  damageTakenPerMinDeltas: Option[Deltas],
  goldPerMinDeltas: Option[Deltas],
  towerKillsPerMinDeltas: Option[Deltas],
  wardsPerMinDeltas: Option[Deltas],
  xpDiffPerMinDeltas: Option[Deltas],
  xpPerMinDeltas: Option[Deltas]
)
