package br.com.cast.scc.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.cast.scc.model.IEntidade;

public class DAOBase<T extends IEntidade> {
	@PersistenceContext(unitName = "persistencia")
	private EntityManager entityManager;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	protected Session getHibernateCurrentSession() {
		return (Session) getEntityManager().getDelegate();
	}

	@SuppressWarnings("unchecked")
	public List<T> consultaTodos(Class<T> classe) {

		String hql = "FROM " + classe.getName();

		return getEntityManager().createQuery(hql).getResultList();
	}

	public long total(Class<T> classe) {
		StringBuilder hql = new StringBuilder();

		hql.append("SELECT COUNT(*) as total ").append("FROM ")
				.append(classe.getName());

		Query query = getEntityManager().createQuery(hql.toString());

		return (long) query.getSingleResult();
	}
}
