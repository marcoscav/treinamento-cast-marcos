package br.com.cast.scc.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import br.com.cast.scc.filtro.FiltroPesquisaVeiculo;
import br.com.cast.scc.model.DominioTipoVeiculo;
import br.com.cast.scc.model.Veiculo;
import br.com.cast.scc.util.UtilString;

@Component
public class VeiculoDAO extends DAOBase<Veiculo> {

	@SuppressWarnings("unchecked")
	public Iterator<? extends Veiculo> consultaPaginada(long inicio,
			long total, FiltroPesquisaVeiculo filtro) {

		Map<String, Object> parametros = new HashMap<>();

		StringBuilder hql = criaQueryConsultaPaginada(filtro, parametros);

		Query query = getEntityManager().createQuery(hql.toString());

		for (String parametro : parametros.keySet()) {
			query.setParameter(parametro, parametros.get(parametro));
		}

		query.setFirstResult((int) inicio);
		query.setMaxResults((int) total);

		return query.getResultList().iterator();
	}

	private StringBuilder criaQueryConsultaPaginada(
			FiltroPesquisaVeiculo filtro, Map<String, Object> parametros) {

		StringBuilder hql = new StringBuilder();

		hql.append("SELECT v ").append("FROM Veiculo v ")
				.append(" JOIN v.tpVeiculo t ").append(" WHERE 1 = 1 ");

		if (filtro.getTpVeiculo() != null) {
			hql.append(" AND t.codigo = :tpVeiculo");
			parametros.put("tpVeiculo", filtro.getTpVeiculo().getCodigo());
		}

		if (filtro.getStatus() != null) {
			hql.append(" AND v.status = :status ");
			parametros.put("status", filtro.getStatus().getSituacao());
		}

		if (isTipoTerrestreSelecionado(filtro)) {

			if (!UtilString.isStringVazia(filtro.getRntrc())) {

				hql.append(" AND v.rntrc = :rntrc ");
				parametros.put("rntrc", filtro.getRntrc());
			}

			if (!UtilString.isStringVazia(filtro.getPlaca())) {
				hql.append(" AND (v.placa1 = :placa ")
						.append(" 	OR v.placa2 = :placa ")
						.append(" 	OR v.placa3 = :placa ")
						.append(" 	OR v.placa4 = :placa ) ");
				parametros.put("placa", filtro.getPlaca());
			}

		} else {

			if (filtro.getNuEmbarcacao() != null) {
				hql.append(" AND v.nuEmbarcacao = :nuEmbarcacao ");
				parametros.put("nuEmbarcacao", filtro.getNuEmbarcacao());
			}
		}
		return hql;
	}

	private boolean isTipoTerrestreSelecionado(FiltroPesquisaVeiculo filtro) {
		return filtro.getTpVeiculo() != null
				&& filtro.getTpVeiculo().getCodigo()
						.equals(DominioTipoVeiculo.COD_TERRESTRE);
	}

}
