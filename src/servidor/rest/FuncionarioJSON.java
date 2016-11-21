package servidor.rest;

import java.util.ArrayList;
import model.Endereco;
import model.Funcionario;
import model.Login;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class FuncionarioJSON {

    public static Funcionario getFuncionarioJSON(JSONObject json) {
        //instancia vetor de funcionarios
        Funcionario funcionario = new Funcionario();
        try {
            //pega do json os registros da tag funcionario
            JSONArray vetor = (JSONArray) json.get("funcionario");
            JSONObject object = (JSONObject) vetor.get(0);
            funcionario.setCodigo(object.getInt("pesCod"));
            funcionario.setNome(object.getString("pesNome"));
            funcionario.setCpf(object.getString("pesCpf"));
            funcionario.setEmail(object.getString("pesEmail"));
            funcionario.setEndereco(EnderecoJSON.getEnderecoJSON(object.getJSONObject("pesEndereco")));
            funcionario.setRg(object.getString("pesRg"));
            funcionario.setSexo(object.getString("pesSexo"));
            funcionario.setTelefoneF(object.getString("pesTelefoneF"));
            funcionario.setTelefoneM(object.getString("pesTelefoneM"));
            funcionario.setLogin(LoginJSON.getLoginJSON(object.getJSONObject("pesLogin")));
        } catch (Exception x) {
        }
        return funcionario;
    }

    public static String geraJSONFuncionarios(ArrayList<Funcionario> funcionarios) {
        ArrayList<JSONObject> tabelaFuncionarios = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Funcionario funcionario : funcionarios) {
            registro = preencheJSON(funcionario);

            //adiciona registro à lista de registros
            tabelaFuncionarios.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("funcionario", (Object) tabelaFuncionarios);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONFuncionario(Funcionario funcionario) {
        return UtilJSON.limpaJSON(preencheJSON(funcionario));
    }

    public static JSONObject preencheJSON(Funcionario funcionario) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("pesCod", funcionario.getCodigo());
            registro.put("pesNome", funcionario.getNome());
            registro.put("pesCpf", funcionario.getCpf());
            registro.put("pesRg", funcionario.getRg());
            registro.put("pesTelefoneM", funcionario.getTelefoneM());
            registro.put("pesTelefoneF", funcionario.getTelefoneF());
            registro.put("pesEmail", funcionario.getEmail());
            registro.put("pesSexo", funcionario.getSexo());
            registro.put("pesEndereco", EnderecoJSON.geraJSONEndereco(funcionario.getEndereco()));
            registro.put("pesLogin", LoginJSON.geraJSONLogin(funcionario.getLogin()));
        } catch (JSONException k) {
        }
        return null;
    }

}
