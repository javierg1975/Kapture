package core

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class Kapture(@Key("_id") id: ObjectId = new ObjectId, ownerId: ObjectId, title: String, text: String, content: String, contentUri: String)

case class Comment(@Key("_id") id: ObjectId = new ObjectId, commentStr: String, kaptureId: ObjectId, posterId: ObjectId)

case class User(@Key("_id") id: ObjectId = new ObjectId, name: String, email: String, pictureId: Option[String] = None)

case class KaptureResponse(kaptures: List[Kapture])
