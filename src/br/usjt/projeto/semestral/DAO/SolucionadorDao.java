package br.usjt.projeto.semestral.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.projeto.semestral.Model.Solucionador;


@Repository
public class SolucionadorDao {
	@PersistenceContext
	EntityManager manager;

	public void incluirSolucionador(Solucionador usuario)
	{
		usuario.setTipo("solucionador");
		manager.persist(usuario);
	}
	/**
	 * 
	 * @param usuario
	 */
	public void atualizaSolucionador(Solucionador usuario)
	{
		manager.merge(usuario);
	}
	/**
	 * 
	 * @param usuario
	 */
	public void excluiSolucionador(Solucionador usuario)
	{
		manager.remove(manager.find(Solucionador.class, usuario.getId()));
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Solucionador selecionarSolucionador(int id)
	{
		return manager.find(Solucionador.class, id);
	}
	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Solucionador> listarSolucionador()
	{
		return manager.createQuery("select l from Solucionador l").getResultList();
		
	}
	public boolean ValidaUsuario(Solucionador usuario)
	{
		String sqlSelect = "select u from Solucionador u where u.cpf = :cpf  u.senha = :senha";
		Query query = manager.createQuery(sqlSelect);
		query.setParameter("cpf", usuario.getCpf());
		query.setParameter("senha", usuario.getSenha());
		
		@SuppressWarnings("unchecked")
		List<Solucionador> result = query.getResultList();
		return (result != null && result.size()== 1);
	}	
}
