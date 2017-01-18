package models.entities

import com.byteslounge.slickrepo.meta.{Entity, Keyed}
import com.byteslounge.slickrepo.repository.Repository
import com.google.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import slick.ast.BaseTypedType
import slick.driver.JdbcProfile

import scala.concurrent.Future


case class Supplier(override val id: Option[Int], name: String, desc: String) extends Entity[Supplier, Int]{
  def withId(id: Int): Supplier = this.copy(id = Some(id))
}


class SupplierRepository @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Repository[Supplier, Int](dbConfigProvider.get[JdbcProfile].driver) {
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