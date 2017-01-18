package models.entities

import com.byteslounge.slickrepo.meta.{Entity, Keyed}

case class Supplier(override val id: Option[Int], name: String, desc: String) extends Entity[Supplier, Int]{
  def withId(id: Int): Supplier = this.copy(id = Some(id))
}