package controllers

import javax.inject._

import models.entities.Supplier
import models.repos.SupplierRepository
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import util.DBImplicits

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._

@Singleton
class SuppliersController @Inject()(suppliersDAO : SupplierRepository, dbExecuter: DBImplicits) extends Controller {
  import dbExecuter.executeOperation

  implicit val supplierWrites = new Writes[Supplier] {
    def writes(sup: Supplier) = Json.obj(
      "id" -> sup.id,
      "name" -> sup.name,
      "desc" -> sup.desc
    )
  }

  def supplier(id : Int) = Action.async {
   suppliersDAO.findOne(id) map { sup => sup.fold(NoContent)(sup => Ok(Json.toJson(sup))) }
  }

  def insertSupplier = Action.async(parse.json) {
    request => {
      for {
        name <- (request.body \ "name").asOpt[String]
        desc <- (request.body \ "desc").asOpt[String]
      } yield {
        suppliersDAO.save(Supplier(None, name, desc)).mapTo[Supplier] map {
          sup => Created("Id of Supplier Added : " + sup.id.getOrElse(0))
        } recoverWith {
          case _ => Future { InternalServerError("Something wrong on server")}
        }
      }
    }.getOrElse(Future {
      BadRequest("Wrong json format")
    })
  }

}
