package br.com.cast.scc.pages.veiculo;

import br.com.cast.scc.pages.BasePage;

public class ConsultarVeiculoIndexPage extends BasePage {
	
	private ConsultarVeiculoIndexForm form;
	
	public ConsultarVeiculoIndexPage() {
		
		form = new ConsultarVeiculoIndexForm("formularioPesquisaVeiculo");
		add(form);
	}

	private static final long serialVersionUID = 1L;
}
