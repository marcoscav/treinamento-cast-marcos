package br.com.cast.scc.util;

public class UtilString {
	private UtilString() {
		// Somente metodos estaticos
	}

	public static boolean isStringVazia(String valor) {
		return valor == null || valor.equals("");
	}

}
