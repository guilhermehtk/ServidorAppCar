package servidor.rest;

import java.util.ArrayList;
import model.Endereco;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class EnderecoJSON {

    public static Endereco getEnderecoJSON(JSONObject json) {
        //instancia vetor de enderecos
        Endereco endereco = new Endereco();
        try {
            //pega do json os registros da tag endereco
            JSONArray vetor = (JSONArray) json.get("endereco");
            JSONObject object = (JSONObject) vetor.get(0);
            endereco.setCod(object.getInt("endCod"));
            endereco.setRua(object.getString("endRua"));
            endereco.setNumero(object.getString("endNumero"));
            endereco.setComplemento(object.getString("endComplemento"));
            endereco.setBairro(object.getString("endBairro"));
            endereco.setCidade(object.getString("endCidade"));
            endereco.setCep(object.getString("endCep"));
        } catch (Exception x) {
        }
        return endereco;
    }

    public static String geraJSONEnderecos(ArrayList<Endereco> enderecos) {
        ArrayList<JSONObject> tabelaEnderecos = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Endereco endereco : enderecos) {
            registro = preencheJSON(endereco);

            //adiciona registro à lista de registros
            tabelaEnderecos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("endereco", (Object) tabelaEnderecos);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONEndereco(Endereco endereco) {
        return UtilJSON.limpaJSON(preencheJSON(endereco));
    }

    public static JSONObject preencheJSON(Endereco endereco) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("endCod", endereco.getCod());
            registro.put("endRua", endereco.getRua());
            registro.put("endNumero", endereco.getNumero());
            registro.put("endComplemento", endereco.getComplemento());
            registro.put("endBairro", endereco.getBairro());
            registro.put("endCidade", endereco.getCidade());
            registro.put("endCep", endereco.getCep());
        } catch (JSONException k) {
        }
        return null;
    }

}
