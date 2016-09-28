package storages

import java.util.UUID
import java.time.LocalDateTime
import models.SolarInfo
import slick.lifted.ProvenShape
import support.database.PvPostgresDriver.api._
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