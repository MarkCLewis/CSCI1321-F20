package networking2

import akka.actor.ActorSystem
import akka.actor.Props
import scala.concurrent.duration._
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream

object ActorChat extends App {
  val system = ActorSystem("ActorChat")

  val chatRoom = system.actorOf(Props(new ChatRoom), "ChatRoom")

  implicit val ec = system.dispatcher

  system.scheduler.scheduleAtFixedRate(0.1.seconds, 0.1.second, chatRoom, ChatRoom.CheckAllInput)

  val ss = new ServerSocket(4040)
  val socket = ss.accept()
  val in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
  val out = new PrintStream(socket.getOutputStream())
  out.println("Working?")
}