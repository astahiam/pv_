# --- !Ups

CREATE TABLE solar_info
(
  id uuid NOT NULL,
  monitoring_time timestamp without time zone,
  irradiance double precision, -- Sunlight or Irradiance (W/m2)
  panel_temp double precision, -- on celcius degree
  ambient_temp double precision, -- on celcius degree
  wind_speed double precision, -- on meter per second
  wind_dir double precision, -- on degree
  sum_energy double precision, -- sum of Energy of sunlight (kWh/m2) on daily basis
  CONSTRAINT solar_info_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.solar_info
  OWNER TO postgres;
COMMENT ON TABLE public.solar_info
  IS 'monitoring solar info';
COMMENT ON COLUMN public.solar_info.irradiance IS 'Sunlight or Irradiance (W/m2)';
COMMENT ON COLUMN public.solar_info.panel_temp IS 'on celcius degree';
COMMENT ON COLUMN public.solar_info.ambient_temp IS 'on celcius degree';
COMMENT ON COLUMN public.solar_info.wind_speed IS 'on meter per second';
COMMENT ON COLUMN public.solar_info.wind_dir IS 'on degree';
COMMENT ON COLUMN public.solar_info.sum_energy IS 'sum of Energy of sunlight (kWh/m2) on daily basis';


-- Index: public.solar_info_id_monitoring_time_idx

-- DROP INDEX public.solar_info_id_monitoring_time_idx;

CREATE INDEX solar_info_id_monitoring_time_idx
  ON public.solar_info
  USING btree
  (id, monitoring_time);

# --- !Downs
DROP TABLE solar_info;
DROP INDEX solar_info_id_monitoring_time_idx;