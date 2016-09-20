package support.database

/**
  * Created by alkha on 9/21/16.
  */

import com.github.tminglei.slickpg._

import com.github.tminglei.slickpg.{PgArraySupport, PgDate2Support, PgPlayJsonSupport}
import play.api.libs.json.{JsValue, Json, Reads}
import slick.driver.PostgresDriver
import slick.driver.PostgresDriver.api._


trait PvPostgresDriver extends PostgresDriver
  with PgArraySupport
  with PgPlayJsonSupport
  with PgDate2Support
with PgPostGISSupport
with PgSearchSupport {

override val pgjson = "jsonb"
override val api = MyAPI

trait MyImplicits extends JsonImplicits {
  implicit val intListTypeMapper = new SimpleArrayJdbcType[Int]("int4").to(_.toVector)
  implicit val longListTypeMapper = new SimpleArrayJdbcType[Long]("bigint").to(_.toVector)
  implicit val stringListTypeMapper = new SimpleArrayJdbcType[String]("text").to(_.toVector)

}

object MyAPI extends API
  with ArrayImplicits
  with DateTimeImplicits
  with PostGISImplicits
  with PostGISAssistants
  with PlayJsonImplicits
  with SimpleArrayPlainImplicits
  with PostGISPlainImplicits
  with PlayJsonPlainImplicits
  with Date2DateTimePlainImplicits
with SearchImplicits
with SearchAssistants
with MyImplicits {}

}

object PvPostgresDriver extends PvPostgresDriver