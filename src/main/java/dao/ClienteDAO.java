package dao;

import dominio.Cliente;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ClienteDAO {
    public void salvar(Cliente cliente) {
        // abre uma nova sessao de conexao
        EntityManager sessao = JPAutil.getEntityManager();
        // cria uma transacao
        EntityTransaction transacao = sessao.getTransaction();
        // iniciando a transacao
        transacao.begin();
        // armazena um objeto funcionario numa tabela do banco
        sessao.persist(cliente);
        // confirmando a operação
        transacao.commit();
        // fechando a sessao
        sessao.close();
    }

    public Cliente buscar(Integer id) {
        // criando sessao para a busca OBS: nao estamos usando o transaction pq ele so
        // precisa ser usando quando se que alterar dados da tabela
        EntityManager sessao = JPAutil.getEntityManager();
        return sessao.find(Cliente.class, id);
    }

    public List<Cliente> listarClientes() {
        // string mistura de slq com jpa pra consultar as coisas
        String jpql = "select f from Cliente f order by idCliente";
        // entidade pra acessar a sessao, como não ta alterando os dados nao precissa de
        // transaction
        EntityManager sessao = JPAutil.getEntityManager();
        // uma query pra poder pegar o resultado da consulta
        Query consulta = sessao.createQuery(jpql);
        // uma lista de funcionarios pra poder enviar todos os funcionarios de uma vez
        // so
        @SuppressWarnings("unchecked")
        List<Cliente> clientes = consulta.getResultList();
        return clientes;
    }

    //fazer metodo teste
    public void excluirCliente(Cliente cliente) {
        EntityManager sessao = JPAutil.getEntityManager();
        EntityTransaction transacao = sessao.getTransaction();
        transacao.begin();
        sessao.remove(sessao.find(Cliente.class, cliente.getIdCliente()));
        transacao.commit();
        sessao.close();
    }


    //fazer metodo teste
    public void alterarCliente(Cliente cliente) {
        EntityManager sessao = JPAutil.getEntityManager();
        EntityTransaction transacao = sessao.getTransaction();
        transacao.begin();
        sessao.merge(cliente);
        transacao.commit();
        sessao.close();
    }
}
