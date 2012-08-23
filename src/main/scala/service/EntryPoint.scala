package service

import cc.spray.Directives
import cc.spray.http.FormData
import util.web.{RSSGenerator, WebImageRenderer}
import cc.spray.directives.PathElement
import cc.spray.typeconversion.SprayJsonSupport
import dal.{CommentDAO, UserDAO, KaptureDAO}
import core.{Comment, Kapture}
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject
import util.json.KaptureProtocol._

trait Debugger {
  import org.slf4j._

  val log = LoggerFactory.getLogger("Debugger")
}

trait KaptureUI extends Directives with SprayJsonSupport {

  //Cheat!
  val user = UserDAO.findOne(MongoDBObject("name" -> "Lauren"))
  val poster = UserDAO.findOne(MongoDBObject("name" -> "Scott"))
  //

  val entryPoint = {
    pathPrefix(""){
      getFromDirectory("src/main/webapp/")
    }~
    pathPrefix("api"){
      pathPrefix("kapture" / PathElement) {kaptureId =>
        path("comment"){
          post{
            content(as[FormData]) { formData =>
              val comment = formData.fields("comment")

              completeWith{
                CommentDAO.insert(Comment(commentStr = comment, kaptureId = new ObjectId(kaptureId), posterId = poster.get.id))
              }
            }
          }~
            get{
              completeWith{
                CommentDAO.find(MongoDBObject("kaptureId" -> kaptureId)).toList
              }
            }
        }
      }~
      pathPrefix("user" / PathElement) {userName =>
        path("kaptures"){
          get{
            completeWith{
              //ignore user for now, use Lauren as default
              //UserDAO.findOne(MongoDBObject("name" -> userName))

              new UserDAO(user.get).kaptures
            }
          }~
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
        path("rss"){
          get{
            completeWith(RSSGenerator.feedForUser(user.get))
          }
        }
      }
    }
  }
}