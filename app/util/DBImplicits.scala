package util

import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import scala.concurrent.Future

class DBImplicits @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val driver: JdbcProfile = dbConfigProvider.get[JdbcProfile].driver
  import driver.api._

  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T] = {
    dbConfigProvider.get[JdbcProfile].db.run(databaseOperation)
  }
}