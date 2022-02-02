package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import dao.ClienteDAO;
import dominio.Cliente;
import org.omnifaces.util.Messages;


import lombok.Data;

@Named //bean do cdi
@Data
@ViewScoped //meus objetos ficam "vivos" enquanto a tela estiver ativa
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Cliente cliente;
    private List<Cliente> clientes;
    private ClienteDAO clienteDAO;

    @PostConstruct
    public void instanciarCliente() {
        this.clienteDAO = new ClienteDAO();
        this.refreshCliente();
    }

    private void refreshCliente() {
        this.cliente = new Cliente();
        this.listarClientes();
    }

    public void listarClientes() {
        this.clientes = this.clienteDAO.listarClientes();
    }

    public void buscarCliente(Cliente clienteSelecionado) {
        this.cliente = this.clienteDAO.buscar(clienteSelecionado.getIdCliente());
    }

    public void cadastrarCliente() {
        this.clienteDAO.salvar(this.cliente);
        Messages.addFlashGlobalInfo("Cliente " + cliente.getNome() + " cadastrad@ com sucesso");
        this.refreshCliente();
    }

    public void removerCliente() {
        Messages.addFlashGlobalInfo("Cliente " + this.cliente.getNome() + " removid@ com sucesso");
        this.clienteDAO.excluirCliente(this.cliente);
        this.refreshCliente();
    }

    public void alterarCliente() {
        Messages.addFlashGlobalInfo("Cliente com id " + this.cliente.getIdCliente() + " alterad@ com sucesso");
        this.clienteDAO.alterarCliente(this.cliente);
        this.refreshCliente();
    }
}
