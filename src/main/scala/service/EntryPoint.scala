package kapture.service

import cc.spray.Directives

trait Debugger {
  import org.slf4j._

  val log = LoggerFactory.getLogger("Debugger")
}



trait KaptureUI extends Directives with Debugger {



  val entryPoint = {
    pathPrefix(""){
      getFromDirectory("src/main/webapp/")
    }
  }
}