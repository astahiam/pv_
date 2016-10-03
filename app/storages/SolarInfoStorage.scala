package storages

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import models.SolarInfo
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import support.database.PvPostgresDriver.api._

import scala.concurrent.{ExecutionContext, Future}
/**
  * Created by alkha on 9/29/16.
  */
class SolarInfoStorage(tag: Tag) extends Table[SolarInfo](tag, "solar_info") {
  def id = column[UUID]("id", O.PrimaryKey)
  def monitoring_time = column[LocalDateTime]("monitoring_time")
  def irradiance = column[Double]("irradiance")
  def panel_temp = column[Double]("panel_temp")
  def ambient_temp = column[Double]("ambient_temp")
  def wind_speed = column[Double]("wind_speed")
  def wind_dir = column[Double]("wind_dir")
  def sum_energy = column[Double]("sum_energy")
  override def * = (id, monitoring_time, irradiance, panel_temp, ambient_temp, wind_speed, wind_dir, sum_energy) <> (SolarInfo.tupled, SolarInfo.unapply)
}

object SolarInfoStorage {
  val tableQuery = TableQuery[SolarInfoStorage]
}

class SolarInfoRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  val dbconfig = dbConfigProvider.get[JdbcProfile]
  val db = dbconfig.db

  private val SolarInfos = SolarInfoStorage.tableQuery

  def findById(id: UUID): Future[SolarInfo] = db.run(SolarInfos.filter(_.id === id).result.head)

  def all(): Future[List[SolarInfo]] = db.run(SolarInfos.to[List].result)

  def create(solarInfo: SolarInfo): Future[UUID] = db.run(SolarInfos returning SolarInfos.map(_.id) += solarInfo)

  def delete(id: UUID): Future[Boolean] = db.run(SolarInfos.filter(_.id === id).delete).map(_ > 0)

}