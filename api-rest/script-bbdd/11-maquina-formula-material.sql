CREATE TABLE public.maquina (
	id_maquina int8 NOT NULL,
	nombre varchar NOT NULL,
	CONSTRAINT maquina_pk PRIMARY KEY (id_maquina)
);

ALTER TABLE public.maquina ADD estado varchar NOT NULL;

CREATE SEQUENCE public.maquina_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.material (
	id_material int8 NOT NULL,
	nombre varchar NOT NULL,
	CONSTRAINT material_pk PRIMARY KEY (id_material)
);

CREATE SEQUENCE public.material_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

CREATE TABLE public.formula (
	id_formula varchar NOT NULL,
	nombre varchar NOT NULL,
	id_material int8 NOT NULL,
	CONSTRAINT formula_pk PRIMARY KEY (id_formula),
	CONSTRAINT formula_fk FOREIGN KEY (id_material) REFERENCES public.material(id_material)
);

CREATE SEQUENCE public.formula_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
	
	