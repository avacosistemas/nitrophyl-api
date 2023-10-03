ALTER TABLE cliente ADD COLUMN nombre character varying(200);
update cliente c set nombre = (select razonsocial from cliente c2 where id = c.id = c2.id);
ALTER TABLE public.cliente ALTER COLUMN nombre SET NOT NULL;

ALTER TABLE cliente ADD COLUMN nombre character varying(200);
UPDATE cliente c SET nombre = (SELECT razon_social FROM cliente c2 WHERE id_cliente = c2.id_cliente);
ALTER TABLE public.cliente ALTER COLUMN nombre SET NOT NULL;