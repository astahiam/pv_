# --- Ups!
insert into solar_info (id, monitoring_time, irradiance, panel_temp, ambient_temp, wind_speed, wind_dir, sum_energy) 	values	(uuid_generate_v4(),	timestamp '2014-01-01 04:00'	,	0,	19.6,	19.6,	0,	5.5,	0);

# --- Downs!
delete from solar_info where monitoring_time = timestamp '2014-01-01 04:00';