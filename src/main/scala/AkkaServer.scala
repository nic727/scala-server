
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.{Directives, RejectionError}
import spray.json._


final case class Equation(question: String, answer: Int, wrong1:Int, wrong2:Int, wrong3:Int)
final case class Range(min: Int, max:Int)

object Equation {

  def apply (min: Int, max:Int) : Equation = {
    val answer = getRandomNumber(min, max)

    return Equation(getQuestion(min, max, answer), answer, getWrong(min, max, answer),
                    getWrong(min, max, answer), getWrong(min, max, answer))
  }

  /** Generate question*/
  private def getQuestion(min:Int, max:Int, answer:Int): String = {
    val random = new scala.util.Random
    val a = min + random.nextInt((answer - min) + 1)
    val b = answer - a

    return s"$a + $b = ?"
  }

  /** Generate random number within given range*/
  private def getRandomNumber(min:Int, max:Int): Int = {
    val random = new scala.util.Random
    return min + random.nextInt((max - min) + 1)
  }

  /** Generate wrong answer*/
  private def getWrong(min:Int, max:Int, answer:Int): Int = {
    val random = getRandomNumber(min, max)

    if(random == answer) return getWrong(min, max, answer)

    return random

  }
}

trait JsonProtocol extends DefaultJsonProtocol {
  implicit val equationFormat = jsonFormat5(Equation.apply)
  implicit val rangeFormat = jsonFormat2(Range)
}

object AkkaServer extends Directives with JsonProtocol {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher


  def main(args: Array[String]) {

    val route =
        post {
          entity(as[Range]) { range =>
            if(range.min < 0 || range.max > 1000000) throw new Error()
            complete(Equation(range.min, range.max))
          }
        }

    Http().bindAndHandle(route, "localhost", 8080)
    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    }
}
