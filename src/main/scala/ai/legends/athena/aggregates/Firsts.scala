package ai.legends.athena.aggregates

import org.json4s.JsonAST.JValue

case class FirstsAggregate (
  firstBloodAssist: Long = 0,
  firstBloodKill: Long = 0,
  firstInhibitorAssist: Long = 0,
  firstInhibitorKill: Long = 0,
  firstTowerAssist: Long = 0,
  firstTowerKill: Long = 0
) {

  def +(firsts: FirstsAggregate): FirstsAggregate = {
    FirstsAggregate(
      firstBloodAssist + firsts.firstBloodAssist,
      firstBloodKill + firsts.firstBloodKill,
      firstInhibitorAssist + firsts.firstInhibitorAssist,
      firstInhibitorKill + firsts.firstInhibitorKill,
      firstTowerAssist + firsts.firstTowerAssist,
      firstTowerKill + firsts.firstTowerKill
    )
  }

  def +(firsts: ParticipantFirsts): FirstsAggregate = {
    FirstsAggregate(
      firstBloodAssist + (if (firsts.firstBloodAssist) 1 else 0),
      firstBloodKill + (if (firsts.firstBloodKill) 1 else 0),
      firstInhibitorAssist + (if (firsts.firstInhibitorAssist) 1 else 0),
      firstInhibitorKill + (if (firsts.firstInhibitorKill) 1 else 0),
      firstTowerAssist + (if (firsts.firstTowerAssist) 1 else 0),
      firstTowerKill + (if (firsts.firstTowerKill) 1 else 0)
    )
  }

}

case class ParticipantFirsts (
  firstBloodAssist: Boolean,
  firstBloodKill: Boolean,
  firstInhibitorAssist: Boolean,
  firstInhibitorKill: Boolean,
  firstTowerAssist: Boolean,
  firstTowerKill: Boolean
)
