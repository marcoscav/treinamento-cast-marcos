package br.com.cast.scc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cast.scc.dao.DominioTipoVeiculoDAO;
import br.com.cast.scc.model.DominioTipoVeiculo;

@Component
public class DominioTipoVeiculoService implements IDominioTipoVeiculoService {
	@Autowired
	private DominioTipoVeiculoDAO dominioTipoVeiculoDAO;

	@Override
	@Transactional
	public List<DominioTipoVeiculo> consultarTodos() {
		return dominioTipoVeiculoDAO.consultaTodos(DominioTipoVeiculo.class);
	}

}
