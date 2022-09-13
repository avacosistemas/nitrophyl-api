ALTER TABLE public.moldeboca
    ALTER COLUMN estado TYPE character varying(50);
	
ALTER TABLE public.moldes
    ALTER COLUMN estado TYPE character varying(50);
	
alter table public.moldeboca add column descripcion  TYPE character varying(100);