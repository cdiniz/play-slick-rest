import java.time.Clock

import com.google.inject.{AbstractModule, Provides}
import models.daos.AbstractBaseDAO
import models.entities.Supplier
import models.persistence.SlickTables.SuppliersTable
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.test._
import org.specs2.execute.Results
import org.specs2.matcher.Matchers
import org.specs2.mock.Mockito
import play.api.libs.json.{JsObject, JsString}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ApplicationSpec extends PlaySpecification with Results with Matchers with Mockito{
  sequential
//
//  val daoMock = mock[AbstractBaseDAO[SuppliersTable,Supplier]]
//
//  val application = new GuiceApplicationBuilder().overrides(new AbstractModule {
//    override def configure() = {
//      bind(classOf[Clock]).toInstance(Clock.systemDefaultZone)
//    }
//    @Provides
//    def provideSuppliersDAO : AbstractBaseDAO[SuppliersTable,Supplier] = daoMock
//  }).build
//
//  "Routes" should {
//
//    "send 404 on a bad request" in  {
//      route(application, FakeRequest(GET, "/boum")).map(status(_)) shouldEqual Some(NOT_FOUND)
//    }
//
//    "send 204 when there isn't a /supplier/1" in  {
//      daoMock.findById(0).returns(Future{None})
//      route(application, FakeRequest(GET, "/supplier/0")).map(
//        status(_)) shouldEqual Some(NO_CONTENT)
//    }
//
//    "send 200 when there is a /supplier/1" in  {
//      daoMock.findById(1).returns(Future{ Some(Supplier(1,"name","desc")) })
//      route(application, FakeRequest(GET, "/supplier/1")).map(
//        status(_)) shouldEqual Some(OK)
//    }
//
//    "send 415 when post to create a supplier without json type" in {
//      route(application, FakeRequest(POST, "/supplier")).map(
//        status(_)) shouldEqual Some(UNSUPPORTED_MEDIA_TYPE)
//    }
//
//    "send 400 when post to create a supplier with empty json" in {
//      route(application,
//        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),JsObject(Seq()))).map(
//        status(_)) shouldEqual Some(BAD_REQUEST)
//    }
//
//    "send 400 when post to create a supplier with wrong json" in {
//      route(application,
//        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),JsObject(Seq("wrong" -> JsString("wrong"))))).map(
//        status(_)) shouldEqual Some(BAD_REQUEST)
//    }
//
//    "send 201 when post to create a supplier with valid json" in {
//      val (name,desc) = ("Apple","Shut up and take my money")
//      daoMock.insert(Supplier(0, name, desc)).returns(Future{1})
//      route(application,
//        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),
//          JsObject(Seq("name" -> JsString(name),"desc" -> JsString(desc))))).map(
//        status(_)) shouldEqual Some(CREATED)
//    }
//
//    "send 500 when post to create a supplier with valid json" in {
//      val (name,desc) = ("Apple","Shut up and take my money")
//      daoMock.insert(Supplier(0, name, desc)).returns(Future.failed{new Exception ("Slick exception")})
//      route(application,
//        FakeRequest(POST, "/supplier", FakeHeaders(("Content-type","application/json") :: Nil),
//          JsObject(Seq("name" -> JsString(name),"desc" -> JsString(desc))))).map(
//        status(_)) shouldEqual Some(INTERNAL_SERVER_ERROR)
//    }
//
//
//  }

}

