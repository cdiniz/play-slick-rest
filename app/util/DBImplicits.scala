package util

import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile
import scala.concurrent.Future

class DBImplicits @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  implicit def executeOperation[T](databaseOperation: DBIO[T]): Future[T] = {
    dbConfigProvider.get[JdbcProfile].db.run(databaseOperation)
  }
}
