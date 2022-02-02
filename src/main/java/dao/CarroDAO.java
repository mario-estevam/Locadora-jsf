package dao;

import dominio.Carro;
import dominio.Cliente;
import util.JPAutil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class CarroDAO {
    public void salvar(Carro carro) {
        EntityManager sessao = JPAutil.getEntityManager();
        EntityTransaction transacao = sessao.getTransaction();
        transacao.begin();
        sessao.persist(carro);
        transacao.commit();
        sessao.close();
    }

    public Carro buscarCarro(Integer numero) {
        EntityManager sessao = JPAutil.getEntityManager();
        return sessao.find(Carro.class, numero);
    }

    public List<Carro> listarCarros() {
        String jpql = "select t from Carro t order by numero";
        EntityManager sessao = JPAutil.getEntityManager();
        Query consulta = sessao.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Carro> carros = consulta.getResultList();
        return carros;
    }

    public void removerCarro(Carro carro) {
        EntityManager sessao = JPAutil.getEntityManager();
        EntityTransaction transacao = sessao.getTransaction();
        transacao.begin();
        sessao.remove(sessao.find(Carro.class, carro.getNumero()));
        transacao.commit();
        sessao.close();
    }

    public void alterarCarro(Carro carro) {
        EntityManager sessao = JPAutil.getEntityManager();
        EntityTransaction transacao = sessao.getTransaction();
        transacao.begin();
        sessao.merge(carro);
        transacao.commit();
        sessao.close();
    }

    public List<Carro> listarCarrosNumero(Integer numero) {
        String jpql = "select c from Carro c where c.numero="+numero+" order by numero";
        EntityManager sessao = JPAutil.getEntityManager();
        Query consulta = sessao.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Carro> carros = consulta.getResultList();
        return carros;
    }

    public List<Carro> listarCarrosStatus(boolean status) {
        String jpql = "select c from Carro c where c.status="+status+" order by numero";
        EntityManager sessao = JPAutil.getEntityManager();
        Query consulta = sessao.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Carro> carros = consulta.getResultList();
        return carros;
    }

    public List<Carro> listarCarrosNome(String nomeCarro) {
        String jpql = "select c from Carro c where c.nomeCarro like '%"+nomeCarro+"%' order by numero";
        EntityManager sessao = JPAutil.getEntityManager();
        Query consulta = sessao.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Carro> carros = consulta.getResultList();
        return carros;
    }

    public List<Carro> listarCarrosCliente(Cliente cliente) {
        String jpql = "select c from Carro c where c.cliente="+cliente.getIdCliente()+" order by numero";
        EntityManager sessao = JPAutil.getEntityManager();
        Query consulta = sessao.createQuery(jpql);
        @SuppressWarnings("unchecked")
        List<Carro> carros = consulta.getResultList();
        return carros;
    }
}
