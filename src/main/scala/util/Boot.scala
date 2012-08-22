package kapture.util

import akka.actor.{Props, ActorSystem}
import cc.spray.{SprayCanRootService, HttpService}
import cc.spray.io.IoWorker
import cc.spray.can.server.HttpServer
import cc.spray.io.pipelines.MessageHandlerDispatch
import kapture.service.KaptureUI

object Boot extends App {

  val port = Option(System.getenv("PORT")).getOrElse("8080").toInt
  val system = ActorSystem("Kapture")

  val proxyModule = new KaptureUI {
    implicit def actorSystem = system
  }


  val httpService = system.actorOf(
    props = Props(new HttpService(proxyModule.entryPoint)),
    name = "proxy-service"
  )

  val rootService = system.actorOf(
    props = Props(new SprayCanRootService(httpService)),
    name = "root-service"
  )

  val ioWorker = new IoWorker(system).start()

  val server = system.actorOf(
    Props(new HttpServer(ioWorker, MessageHandlerDispatch.SingletonHandler(rootService))),
    name = "http-server"
  )

  server ! HttpServer.Bind("0.0.0.0", port)

  system.registerOnTermination{
    ioWorker.stop()
  }
}