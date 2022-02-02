package bean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.CarroDAO;
import dao.ClienteDAO;
import dominio.Carro;
import dominio.Cliente;
import org.omnifaces.util.Messages;

import lombok.Data;

@Named
@Data
@ViewScoped
public class CarroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private CarroDAO dao;
    private Carro carro;
    private List<Carro> carros;
    private ClienteDAO clienteDAO;
    private List<Cliente> clientes;
    private Integer numero;
    private String nome;
    private boolean status;
    private Cliente cliente;

    @PostConstruct
    public void iniciarTarefa() {
        this.dao = new CarroDAO();
        this.refreshCarro();
        this.clienteDAO = new ClienteDAO();
        this.clientes = clienteDAO.listarClientes();
    }

    private void refreshCarro() {
        this.carro = new Carro();
        this.listarCarros();
    }

    public void listarCarros() {
        this.carros = this.dao.listarCarros();
    }

    public void buscarCarro(Carro carroSelecionado) {
        this.carro = this.dao.buscarCarro(carroSelecionado.getNumero());
    }

    public void cadastrarCarro() {
        this.dao.salvar(this.carro);
        Messages.addFlashGlobalInfo("Tarefa " + this.carro.getNomeCarro() + " cadastrado com sucesso");
        this.refreshCarro();
    }

    public void excluirCarro() {
        Messages.addFlashGlobalInfo("Tarefa " + this.carro.getNomeCarro()+ " removido com sucesso");
        dao.removerCarro(this.carro);
        this.refreshCarro();
    }

    public void alterarCarro() {
        Messages.addFlashGlobalInfo("Tarefa " + this.carro.getNomeCarro()+ " alterado com sucesso");
        dao.alterarCarro(this.carro);
        this.refreshCarro();
    }

    public void concluirAluguel() {
        Messages.addFlashGlobalInfo("Tarefa " + this.carro.getNomeCarro() + " Confirmado!");
        this.carro.setStatus(true);
        dao.alterarCarro(this.carro);
        this.refreshCarro();
    }

    public void listarCarroStatus() {
        Messages.addFlashGlobalInfo("Carros listados por Status");
        this.carros = this.dao.listarCarrosStatus(this.status);
    }

    public void listarCarrosNumero() {
        Messages.addFlashGlobalInfo("Carros listados por Numero");
        this.carros = this.dao.listarCarrosNumero(this.numero);
    }

    public void listarCarrosNome() {
        Messages.addFlashGlobalInfo("Carros listados por nome");
        this.carros = this.dao.listarCarrosNome(this.nome+"");
    }


    public void listarCarrosCliente() {
        Messages.addFlashGlobalInfo("Tarefas listadas por Funcionario");
        this.carros = this.dao.listarCarrosCliente(this.cliente);
    }

}
