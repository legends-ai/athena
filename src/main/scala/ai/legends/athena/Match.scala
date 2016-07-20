package ai.legends.athena

import org.json4s.JsonAST.JValue

class Match (
  val matchDuration: Int
) {
}

object Match {
  def apply(json: JValue): Match = {
    return new Match(1)
  }
}
