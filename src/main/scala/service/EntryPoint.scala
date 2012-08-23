package service

import cc.spray.Directives
import cc.spray.http.FormData
import util.web.WebImageRenderer
import cc.spray.directives.PathElement
import cc.spray.typeconversion.SprayJsonSupport
import dal.{UserDAO, KaptureDAO}
import core.Kapture
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject

trait Debugger {
  import org.slf4j._

  val log = LoggerFactory.getLogger("Debugger")
}

trait KaptureUI extends Directives with SprayJsonSupport {

  //Cheat!
  val user = UserDAO.findOne(MongoDBObject("name" -> "Lauren"))

  //

  val entryPoint = {
    pathPrefix(""){
      getFromDirectory("src/main/webapp/")
    }~
    pathPrefix("api"){
      path("kapture"){
        post{
          content(as[FormData]) { formData =>

            val target = formData.fields("target")
            val text = formData.fields("text")
            val title = formData.fields("title")

            val kapture = WebImageRenderer.imageUrl(target)

            // save `kapture` in database for current user   (right now only works for Lauren)
            val _id = KaptureDAO.insert(Kapture(ownerId = user.get.id, text = text, title = title, content = kapture, contentUri = target))

            // post kapture in twitter
            completeWith(kapture)
          }

        }
      }~
      path("user" / PathElement / "kaptures"){ userName =>
        get{
          completeWith{
            //ignore user for now, use Lauren as default
            //UserDAO.findOne(MongoDBObject("name" -> userName))

            KaptureDAO.find(MongoDBObject("ownerId" -> user.get.id)).toList
            ""
          }
        }
      }

    }
  }
}