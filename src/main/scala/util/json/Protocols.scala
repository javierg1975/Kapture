package util.json

import cc.spray.json._
import core.{Comment, User, Kapture}
import org.bson.types.ObjectId

object KaptureProtocol extends DefaultJsonProtocol {
  implicit object ObjectIdJsonFormat extends RootJsonFormat[ObjectId] {
    def write(c: ObjectId) = JsObject(
      "_id" -> JsString(c.toString)
    )
    def read(value: JsValue) = {
      value.asJsObject.getFields("_id") match {
        case Seq(JsString(id)) =>
          new ObjectId(id)
        case _ => throw new DeserializationException("ObjectId expected")
      }
    }
  }

  implicit val kaptureFormat = jsonFormat6(Kapture)
  implicit val userProtocol = jsonFormat4(User)
  implicit val commentProtocol = jsonFormat4(Comment)

}