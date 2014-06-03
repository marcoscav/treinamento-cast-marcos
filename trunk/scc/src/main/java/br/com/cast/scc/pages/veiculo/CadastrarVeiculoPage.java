package br.com.cast.scc.pages.veiculo;

import br.com.cast.scc.model.Veiculo;
import br.com.cast.scc.pages.BasePage;

public class CadastrarVeiculoPage extends BasePage {
	private static final long serialVersionUID = 1L;

	private CadastrarVeiculoForm formulario;

	public CadastrarVeiculoPage() {
		add(formulario = new CadastrarVeiculoForm("formulario"));
	}

	public void atualizaModelo(Veiculo veiculo) {
		formulario.setDefaultModelObject(veiculo);
	}
}
