import com.mongodb.casbah.MongoConnection
import util.configuration.Configuration

package object dal {
  object DefaultMongoLabConnection {
    val url = Configuration("mongoLabs.url")
    val port = Configuration("mongoLabs.port").toInt
    val db = Configuration("mongoLabs.db")
    val user = Configuration("mongoLabs.user")
    val password = Configuration("mongoLabs.password")

    def mongoDb = {
      val database = MongoConnection(url, port)(db)
      database.authenticate(user, password)
      database
    }

    def apply(collectionName: String)= {
      mongoDb(collectionName)
    }
  }

  def using(collectionName: String) = DefaultMongoLabConnection(collectionName)

}