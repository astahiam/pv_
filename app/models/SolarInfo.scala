package models

import java.time.LocalDateTime
import java.util.UUID

/**
  * Created by alkha on 9/29/16.
  */
case class SolarInfo(
  id: UUID,
  monitoring_time: LocalDateTime,
  irradiance: Double,
  panel_temp: Double,
  ambient_temp: Double,
  wind_speed: Double,
  wind_dir: Double,
  sum_energy: Double
)
