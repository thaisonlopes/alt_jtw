package org.primefaces.teste;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import org.primefaces.apollo.dao.usuarios.UsuarioDAO;
import org.primefaces.apollo.entidades.usuarios.Usuario;

public class InserirUsuarioTeste {

	public static void main(String[] args) {
		// Configurar a conexão com o banco de dados
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
		EntityManager em = emf.createEntityManager();

		// Inserir um usuário de teste
		em.unwrap(Session.class).doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				connection.setAutoCommit(false);
			}
		});

		em.getTransaction().begin();

		UsuarioDAO usuarioDAO = new UsuarioDAO(em);

		Usuario usuario = new Usuario();
		usuario.setCpf("11111111111");
		usuario.setLogin("alt");
		usuario.setSenha("17082020");
		usuario.setTipoUsuario(1);
		usuario.setEmpresa(1);

		usuarioDAO.inserir(usuario);

		em.getTransaction().commit();

		// Fechar a conexão com o banco de dados
		em.close();
		emf.close();
	}
}
