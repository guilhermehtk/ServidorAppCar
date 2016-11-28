package servidor;

import control.CarroController;
import control.ClienteController;
import control.FuncionarioController;
import control.LoginController;
import control.OrdemServicoController;
import control.ServicoController;
import control.Servico_OSController;
import java.net.*;
import java.io.*;
import model.Carro;
import model.Cliente;
import model.Funcionario;
import model.OrdemServico;
import model.Servico;
import model.Servico_OS;
import servidor.json.JSONException;
import servidor.json.JSONObject;
import servidor.rest.CarroJSON;
import servidor.rest.ClienteJSON;
import servidor.rest.FuncionarioJSON;
import servidor.rest.LoginJSON;
import servidor.rest.OrdemServicoJSON;
import servidor.rest.ServicoJSON;
import servidor.rest.Servico_OSJSON;

public class ServidorTCP {

    private ServerSocket ss;
    private int porta;
    private Socket s = null;
    private BufferedReader br = null;
    private PrintStream ps = null;
    private String msgRecebida = "";

    public ServidorTCP(int p) {
        porta = p;//a porta pode ser um argumento 
        try {
            ss = new ServerSocket(porta);//soket do servidor
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Servidor Ligado");
    }

    public void esperaConexao() {
        //laço infinito para esperar várias conexões
        while (ss != null && ss.isBound() && !ss.isClosed()) {
            try {
                System.out.println("Esperando Conexoes");
                //inicia processo de conexão
                try {
                    //aguarda conexões
                    s = ss.accept();
                    //buffer de leitura, classe io, poderia estar manipulando um arquivo    
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    msgRecebida = br.readLine(); // recebendo dados do cliente como string    
                    System.out.println("JSON do Cliente: \n" + msgRecebida + "\n");

                    //cria prinstream para responder ao cliente
                    ps = new PrintStream(s.getOutputStream());

                    JSONObject json = new JSONObject(msgRecebida);
                    JSONObject jsonResposta = new JSONObject();

                    LoginController loginController = new LoginController();
                    int flag = loginController.validaLogin(LoginJSON.getLoginJSON(json.getJSONObject("login")));
                    // testa o login do usuário
                    if (flag == 0) {
                        System.out.println("Senha Incorreta");
                        jsonResposta.put("login", "senha_incorreta");
                    } else if (flag == 1) {
                        System.out.println("Usuário Não Cadastrado");
                        jsonResposta.put("login", "usuario_nao_cadastrado");
                    } else {
                        //se logar
                        if (json.has("object")) {
                            // tem objeto
                            jsonResposta.put("return", this.request(json.getString("request"), json.getJSONObject("object")));
                        } else {
                            // não tem
                            jsonResposta.put("return", this.request(json.getString("request"), null));
                        }
                    }
                    //manda(responde) msg para o cliente
                    ps.println(jsonResposta.toString());
                    s.close();
                    //System.gc();
                } catch (IOException | JSONException e) {
                }//fim da exeção do socket
            } catch (Exception sd) {
            }// fim da thread
        }
    }

    public void close() throws IOException {
        System.out.println("Servidor Desligado");
        ss.close();
    }

    public void restart() throws IOException {
        System.out.println("Servidor Desligado");
        ss.close();
        ss = null;
        try {
            ss = new ServerSocket(porta);//soket do servidor
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Servidor Ligado");

    }

    public String request(String request, JSONObject objeto) {
        if(objeto.length()>1){
            System.out.println("Tamanho:"+objeto.length());
            
        }
        switch (request) {
            // ------------------------------- GET all ------------------------- //
            case "get_Carro_All":
                return CarroJSON.geraJSONCarros(new CarroController().getAll());
            case "get_Cliente_All":
                return ClienteJSON.geraJSONClientes(new ClienteController().getAll());
            case "get_Funcionario_All":
                return FuncionarioJSON.geraJSONFuncionarios(new FuncionarioController().getAll());
            case "get_OrdemServico_All":
                return OrdemServicoJSON.geraJSONOrdemServicos(new OrdemServicoController().getAll());
            case "get_Servico_All":
                return ServicoJSON.geraJSONServicos(new ServicoController().getAll());
            case "get_Servico_OS_All":
                return Servico_OSJSON.geraJSONServico_OSs(new Servico_OSController().getAll());

            // ------------------------------- GET id ------------------------- //
            case "get_Carro":
                return CarroJSON.geraJSONCarro((Carro) new CarroController().get(objeto.getInt("carCod")));
            case "get_Cliente":
                return ClienteJSON.geraJSONCliente((Cliente) new ClienteController().get(objeto.getInt("pesCod")));
            case "get_Funcionario":
                return FuncionarioJSON.geraJSONFuncionario((Funcionario) new FuncionarioController().get(objeto.getInt("pesCod")));
            case "get_OrdemServico":
                return OrdemServicoJSON.geraJSONOrdemServico((OrdemServico) new OrdemServicoController().get(objeto.getInt("osCod")));
            case "get_Servico":
                return ServicoJSON.geraJSONServico((Servico) new ServicoController().get(objeto.getInt("svcCod")));
            case "get_Servico_OS":
                return Servico_OSJSON.geraJSONServico_OS((Servico_OS) new Servico_OSController().get(objeto.getInt("serCod")));
            case "get_Servico_OS_osCod":
                return Servico_OSJSON.geraJSONServico_OS((Servico_OS) new Servico_OSController().get(objeto.getInt("ser_osCod")));

            // ------------------------------- SET object ------------------------- //
            case "set_Carro":
                new CarroController().altera(CarroJSON.getCarroJSON(objeto.getJSONObject("carro")));
                return "true";
            case "set_Cliente":
                new ClienteController().altera(ClienteJSON.getClienteJSON(objeto.getJSONObject("cliente")));
                return "true";
            case "set_Funcionario":
                new FuncionarioController().altera(FuncionarioJSON.getFuncionarioJSON(objeto.getJSONObject("funcionario")));
                return "true";
            case "set_OrdemServico":
                new OrdemServicoController().altera(OrdemServicoJSON.getOrdemServicoJSON(objeto.getJSONObject("ordemservico")));
                return "true";
            case "set_Servico":
                new ServicoController().altera(ServicoJSON.getServicoJSON(objeto.getJSONObject("servico")));
                return "true";
            case "set_Servico_OS":
                new Servico_OSController().altera(Servico_OSJSON.getServico_OSJSON(objeto.getJSONObject("servico_os")));
                return "true";

            // ------------------------------- ADD object ------------------------- //
            case "add_Carro":
                return Integer.toString(new CarroController().add(CarroJSON.getCarroJSON(objeto.getJSONObject("carro"))));
            case "add_Cliente":
                return Integer.toString(new ClienteController().add(ClienteJSON.getClienteJSON(objeto.getJSONObject("cliente"))));
            case "add_Funcionario":
                return Integer.toString(new FuncionarioController().add(FuncionarioJSON.getFuncionarioJSON(objeto.getJSONObject("funcionario"))));
            case "add_OrdemServico":
                return Integer.toString(new OrdemServicoController().add(OrdemServicoJSON.getOrdemServicoJSON(objeto.getJSONObject("ordemservico"))));
            case "add_Servico":
                return Integer.toString(new ServicoController().add(ServicoJSON.getServicoJSON(objeto.getJSONObject("servico"))));
            case "add_Servico_OS":
                return Integer.toString(new Servico_OSController().add(Servico_OSJSON.getServico_OSJSON(objeto.getJSONObject("servico_os"))));

            // ------------------------------- REMOVE object ------------------------- //
            case "rmv_Carro":
                new CarroController().remove(CarroJSON.getCarroJSON(objeto.getJSONObject("carro")).getCod());
                return "true";
            case "rmv_Cliente":
                new ClienteController().remove(ClienteJSON.getClienteJSON(objeto.getJSONObject("cliente")).getCodigo());
                return "true";
            case "rmv_Funcionario":
                new FuncionarioController().remove(FuncionarioJSON.getFuncionarioJSON(objeto.getJSONObject("funcionario")).getCodigo());
                return "true";
            case "rmv_OrdemServico":
                new OrdemServicoController().remove(OrdemServicoJSON.getOrdemServicoJSON(objeto.getJSONObject("ordemservico")).getCod());
                return "true";
            case "rmv_Servico":
                new ServicoController().remove(ServicoJSON.getServicoJSON(objeto.getJSONObject("servico")).getCod());
                return "true";
            case "rmv_Servico_OS":
                new Servico_OSController().remove(Servico_OSJSON.getServico_OSJSON(objeto.getJSONObject("servico_os")).getCod());
                return "true";

            // ------------------------------- Default ------------------------- //    
            default:
                break;
        }
        return null;
    }
}
