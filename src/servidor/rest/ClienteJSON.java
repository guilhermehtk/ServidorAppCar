package servidor.rest;

import java.util.ArrayList;
import model.Cliente;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class ClienteJSON {

    public static Cliente getClienteJSON(JSONObject json) {
        //instancia vetor de clientes
        Cliente cliente = new Cliente();
        try {
            //pega do json os registros da tag cliente
            JSONArray vetor = (JSONArray) json.get("cliente");
            JSONObject object = (JSONObject) vetor.get(0);
            cliente.setCodigo(object.getInt("pesCod"));
            cliente.setNome(object.getString("pesNome"));
            cliente.setCpf(object.getString("pesCpf"));
            cliente.setEmail(object.getString("pesEmail"));
            cliente.setEndereco(EnderecoJSON.getEnderecoJSON(object.getJSONObject("endereco")));
            cliente.setRg(object.getString("pesRg"));
            cliente.setSexo(object.getString("pesSexo"));
            cliente.setTelefoneF(object.getString("pesTelefoneF"));
            cliente.setTelefoneM(object.getString("pesTelefoneM"));
        } catch (Exception x) {
        }
        return cliente;
    }

    public static String geraJSONClientes(ArrayList<Cliente> clientes) {
        ArrayList<JSONObject> tabelaClientes = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Cliente cliente : clientes) {
            registro = preencheJSON(cliente);

            //adiciona registro à lista de registros
            tabelaClientes.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("cliente", (Object) tabelaClientes);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONCliente(Cliente cliente) {
        return UtilJSON.limpaJSON(preencheJSON(cliente));
    }

    public static JSONObject preencheJSON(Cliente cliente) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("pesCod", cliente.getCodigo());
            registro.put("pesNome", cliente.getNome());
            registro.put("pesCpf", cliente.getCpf());
            registro.put("pesRg", cliente.getRg());
            registro.put("pesTelefoneM", cliente.getTelefoneM());
            registro.put("pesTelefoneF", cliente.getTelefoneF());
            registro.put("pesEmail", cliente.getEmail());
            registro.put("pesSexo", cliente.getSexo());
            registro.put("pesEndereco", cliente.getEndereco());
        } catch (JSONException k) {
        }
        return null;
    }

}
