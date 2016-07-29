package ai.legends.athena.aggregates

import org.json4s.JsonAST.JValue

case class WardsAggregate(
  visionWardsBoughtInGame: Long = 0,
  sightWardsBoughtInGame: Long = 0,
  wardsKilled: Long = 0,
  wardsPlaced: Long = 0
) {

  def +(wards: WardsAggregate): WardsAggregate = {
    WardsAggregate(
      visionWardsBoughtInGame + wards.visionWardsBoughtInGame,
      sightWardsBoughtInGame + wards.sightWardsBoughtInGame,
      wardsKilled + wards.wardsKilled,
      wardsPlaced + wards.wardsPlaced
    )
  }

  def +(wards: ParticipantWards): WardsAggregate = {
    WardsAggregate(
      visionWardsBoughtInGame + wards.visionWardsBoughtInGame,
      sightWardsBoughtInGame + wards.sightWardsBoughtInGame,
      wardsKilled + wards.wardsKilled,
      wardsPlaced + wards.wardsPlaced
    )
  }

}

case class ParticipantWards (
  visionWardsBoughtInGame: Int,
  sightWardsBoughtInGame: Int,
  wardsKilled: Int,
  wardsPlaced: Int
)
