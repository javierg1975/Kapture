package dal

import com.novus.salat.dao._
import com.novus.salat._
import com.novus.salat.global._
import com.novus.salat.annotations._
import com.novus.salat.dao._
import com.mongodb.casbah.Imports._
import core._
import org.bson.types.ObjectId


object KaptureDAO extends SalatDAO[Kapture, ObjectId](collection = using(""))