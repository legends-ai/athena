package ai.legends.athena.utils

object Combiners {

  implicit class MapCombiners[K](val a: Map[K, Int]) {
    /** |+| merges maps by adding the value. */
    def |+|(b: Map[K, Int]): Map[K, Int] = {
      a.foldLeft(b) {
        case (dict, (k, v)) => dict + (k -> (v + dict.getOrElse(k, 0)))
      }
    }

    /** ++ increments the given key in the map. */
    def ++(b: K): Map[K, Int] = {
      a + (b -> (a.getOrElse(b, 0) + 1))
    }
  }

}
