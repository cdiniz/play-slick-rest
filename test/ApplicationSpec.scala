import java.time.Clock

import com.google.inject.{AbstractModule, Provides}
import models.entities.Supplier
import models.repos.SupplierRepository
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test._
import org.specs2.execute.Results
import org.specs2.matcher.Matchers
import org.specs2.mock.Mockito
import play.api.libs.json.{JsObject, JsString}
import slick.dbio.DBIOAction
import play.api.libs.concurrent.Execution.Implicits._
import slick.SlickException

import scala.concurrent.{ExecutionContext, Future}

class ApplicationSpec extends PlaySpecification with Results with Matchers with Mockito{
  sequential

  val daoMock = mock[SupplierRepository]

  val application = new GuiceApplicationBuilder().overrides(new AbstractModule {
    override def configure() = {
      bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
    }
    @Provides
    def suppliersDAO : SupplierRepository = daoMock
  }).build

  "Routes" should {

    "send 404 on a bad request" in  {
      route(application, FakeRequest(GET, "/boum")).map(status(_)) shouldEqual Some(NOT_FOUND)
    }

    "send 204 when there isn't a /supplier/1" in  {
      daoMock.findOne(0).returns(DBIOAction.from(Future{None}))
      route(application, FakeRequest(GET, "/supplier/0")).map(
        status(_)) shouldEqual Some(NO_CONTENT)
    }

    "send 200 when there is a /supplier/1" in  {
      daoMock.findOne(1).returns(DBIOAction.from(Future{ Some(Supplier(Some(1),"name","desc")) }))
      route(application, FakeRequest(GET, "/supplier/1")).map(
        status(_)) shouldEqual Some(OK)
    }

    "send 415 when post to create a supplier without json type" in {
      route(application, FakeRequest(POST, "/supplier")).map(
        status(_)) shouldEqual Some(UNSUPPORTED_MEDIA_TYPE)
    }

    "send 400 when post to create a supplier with empty json" in {
      route(application,
        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),JsObject(Seq()))).map(
        status(_)) shouldEqual Some(BAD_REQUEST)
    }

    "send 400 when post to create a supplier with wrong json" in {
      route(application,
        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),JsObject(Seq("wrong" -> JsString("wrong"))))).map(
        status(_)) shouldEqual Some(BAD_REQUEST)
    }

    "send 201 when post to create a supplier with valid json" in {
      val (name,desc) = ("Apple","Shut up and take my money")
      daoMock.save(any[Supplier])(any[ExecutionContext]).returns(DBIOAction.from(Future{Supplier(Some(1), name, desc)}))
      route(application,
        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),
          JsObject(Seq("name" -> JsString(name),"desc" -> JsString(desc))))).map(
        status(_)) shouldEqual Some(CREATED)
    }

    "send 500 when post to create a supplier with valid json" in {
      val (name,desc) = ("Apple","Shut up and take my money")
      daoMock.save(any[Supplier])(any[ExecutionContext]).returns(DBIOAction.failed(new SlickException("test")))
      route(application,
        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),
          JsObject(Seq("name" -> JsString(name),"desc" -> JsString(desc))))).map(
        status(_)) shouldEqual Some(INTERNAL_SERVER_ERROR)
    }

  }

}

