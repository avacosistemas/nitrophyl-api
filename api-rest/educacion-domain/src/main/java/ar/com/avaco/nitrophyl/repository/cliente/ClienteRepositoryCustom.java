package ar.com.avaco.nitrophyl.repository.cliente;

import java.util.List;

import ar.com.avaco.nitrophyl.domain.entities.cliente.Cliente;

public interface ClienteRepositoryCustom {

	Cliente getClienteCompleto(Long id);

	List<Cliente> listClientesListado();
	
}
