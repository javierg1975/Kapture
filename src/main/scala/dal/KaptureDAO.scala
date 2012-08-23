package dal

import com.novus.salat.dao._
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import core._
import org.bson.types.ObjectId
import com.mongodb.casbah.commons.MongoDBObject


object KaptureDAO extends SalatDAO[Kapture, ObjectId](collection = using("kapture"))

object UserDAO extends SalatDAO[User, ObjectId](collection = using("kapture-user"))

class UserDAO(user: User) {
  //def this(id: ObjectId)

  def kaptures: List[Kapture] = KaptureDAO.find(MongoDBObject("ownerId" -> user.id)).toList
}

object CommentDAO extends SalatDAO[Comment, ObjectId](collection = using("kapture-comment"))