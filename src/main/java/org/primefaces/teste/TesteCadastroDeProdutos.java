package org.primefaces.teste;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.sql.Timestamp;

import org.primefaces.apollo.entidades.cadastroProdutos.cadastroDeProdutos.Produto;
import org.primefaces.apollo.entidades.cadastroProdutos.categoriaProdutos.CategoriaProdutos;

public class TesteCadastroDeProdutos {
    public static void main(String[] args) {
        // Configurar o EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ALT");
        EntityManager em = emf.createEntityManager();

        // Criar uma instância de CadastroDeProdutos
        Produto produto = new Produto();
        produto.setDthr_create(new Timestamp(System.currentTimeMillis()));
        produto.setCod_usuario_create(1);
        produto.setDthr_update(new Timestamp(System.currentTimeMillis()));
        produto.setCod_usuario_update(1);
        produto.setCodigo(1);
        produto.setCod_barra("123456789");
        produto.setNome_do_produto("Produto A");
        produto.setQuantidade(10);
        produto.setModelo_marca("Marca A");
        // Definir a categoria de produtos
        CategoriaProdutos categoria = em.find(CategoriaProdutos.class, 1L); 
        produto.setId_categoria(categoria);
        produto.setUnidade("Unidade A");
        produto.setObservacao("Observação do produto");
        produto.setPreco_compra(10.5);
        produto.setPreco_venda(20.0);

        // Iniciar a transação
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        
        // Persistir o produto
        em.persist(produto);

        // Finalizar a transação
        transaction.commit();

        // Fechar o EntityManager
        em.close();
        emf.close();
    }
}
