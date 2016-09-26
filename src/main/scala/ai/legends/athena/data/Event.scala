package ai.legends.athena.data

case class Event (
  eventType: String,
  timestamp: Double,

  // SKILL_LEVEL_UP
  participantId: Option[Int],
  skillSlot: Option[Int],
  levelUpType: Option[String],

  // ITEM_PURCHASED
  itemId: Option[Int],

  // ITEM_UNDO
  itemBefore: Option[Int],
  itemAfter: Option[Int]
)

object Event {

  def buildSkillOrder(events: List[Event], participantId: Int): String = {
    // Filter only relevant events
    val skillEvents = events.filter((event) =>
      event.eventType == "SKILL_LEVEL_UP" &&
      event.participantId.map(_ == participantId).get &&
      event.levelUpType.map(_ == "NORMAL").get
    ).sortBy(_.timestamp)

    // Build order
    val skillOrder = skillEvents.filter(_.skillSlot.isDefined).map(_.skillSlot.get)

    // Build order string
    skillOrder.map {
      case 1 => "Q"
      case 2 => "W"
      case 3 => "E"
      case 4 => "R"
      case _ => "?"
    }.mkString
  }

  def myEvents(events: List[Event], p: Participant): List[Event] = {
    return events.filter(_.participantId == Some(p.participantId))
  }

  def findBuildPaths(events: List[Event]): String = {
    val items = events.foldLeft(Set[Int]()) { (itemSet, event) =>
      event.eventType match {
        case "ITEM_PURCHASED" => itemSet + event.itemId.get
        case "ITEM_DESTROYED" => itemSet - event.itemId.get
        case "ITEM_UNDO" => itemSet - event.itemBefore.get + event.itemAfter.get

        case _ => itemSet
      }
    }
    return items.map(_.toString).mkString("|")
  }

  def findStarterItems(events: List[Event], p: Participant): String = {
    findBuildPaths(myEvents(events, p).filter(_.timestamp <= 60000))
  }

  def findBuildPath(events: List[Event], p: Participant): String = {
    findBuildPaths(myEvents(events, p))
  }

}
