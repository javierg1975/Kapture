package core

import com.novus.salat.annotations._
import org.bson.types.ObjectId

case class Kapture(@Key("_id") id: ObjectId = new ObjectId, owner: User, content: String, comments: List[Comment])



case class Comment(@Key("_id") id: ObjectId = new ObjectId, commentStr: String, poster: User)

case class User(@Key("_id") id: ObjectId = new ObjectId, name: String, email: String, pictureId: String)

