package controllers

import javax.inject._

import models.entities.{DBExecuter, Supplier, SupplierRepository}
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.{Json, Writes}
import play.api.mvc._
import slick.driver.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SuppliersController @Inject()(suppliersDAO : SupplierRepository, dbExecuter: DBExecuter)(implicit exec: ExecutionContext) extends Controller {
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
    request =>
//      {
//        for {
//          name <- (request.body \ "name").asOpt[String]
//          desc <- (request.body \ "desc").asOpt[String]
//        } yield {
//          (suppliersDAO.save(Supplier(None, name, desc))) map { n  => Created("Id of Supplier Added : " + n) }.recoverWith {
//            case e => Future {
//              InternalServerError("There was an error at the server")
//            }
//          }
//        }
//      }.getOrElse(Future{BadRequest("Wrong json format")})
      Future{BadRequest("Wrong json format")}
  }
}
