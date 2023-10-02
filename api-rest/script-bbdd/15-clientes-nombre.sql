ALTER TABLE cliente ADD COLUMN nombre character varying(200);
update cliente c set nombre = (select razonsocial from cliente c2 where id = c.id = c2.id);
ALTER TABLE public.cliente ALTER COLUMN nombre SET NOT NULL;

	
