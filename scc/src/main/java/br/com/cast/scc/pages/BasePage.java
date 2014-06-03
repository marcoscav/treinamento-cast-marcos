package br.com.cast.scc.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import br.com.cast.scc.pages.veiculo.ConsultarVeiculoIndexPage;

public class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public BasePage() {
		criaLinkManterVeiculo();
    }

	/**
	 * Cria o link para redirecionar à página ConsultarVeiculoIndexPage.
	 */
	private void criaLinkManterVeiculo() {
		add(new Link<Void>("manterVeiculo"){
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(ConsultarVeiculoIndexPage.class);
			}
		});
	}
}
