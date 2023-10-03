ALTER TABLE public.moldes ADD COLUMN id_cliente_duenio BIGINT NOT NULL REFERENCES cliente(id_cliente);
ALTER TABLE public.moldes ADD COLUMN propio BOOLEAN NOT NULL;