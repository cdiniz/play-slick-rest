package models.repos

import com.byteslounge.slickrepo.meta.Keyed
import com.byteslounge.slickrepo.repository.Repository
import com.google.inject.Inject
import models.entities.Supplier
import play.api.db.slick.DatabaseConfigProvider
import slick.ast.BaseTypedType
import slick.jdbc.JdbcProfile

class SupplierRepository @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Repository[Supplier, Int](dbConfigProvider.get[JdbcProfile].profile) {
  import driver.api._
  val pkType = implicitly[BaseTypedType[Int]]
  val tableQuery = TableQuery[Suppliers]
  type TableType = Suppliers

  class Suppliers(tag: slick.lifted.Tag) extends Table[Supplier](tag, "SUPPLIERS") with Keyed[Int] {
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def desc = column[String]("desc")
    def * = (id.?, name, desc) <> ((Supplier.apply _).tupled, Supplier.unapply)
  }


}
