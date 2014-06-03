package br.com.cast.scc.util;

import org.apache.wicket.Component;

import br.com.cast.scc.pages.BasePage;

public class ComponentUtil {
	
	private ComponentUtil() {
		//apenas acesso por metodos estaticos
	}
	
	public static BasePage getTemplate(Component filho) {
		
		do {
			filho = filho.getParent();
		} while (!(filho instanceof BasePage));
		
		return (BasePage) filho;
	}
	
	public static void mensagemErro(String mensagem) {
		
	}
	
}
