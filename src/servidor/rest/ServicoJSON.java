package servidor.rest;

import java.util.ArrayList;
import model.Servico;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class ServicoJSON {

    public static Servico getServicoJSON(JSONObject json) {
        //instancia vetor de servicos
        Servico servico = new Servico();
        try {
            //pega do json os registros da tag servico
            JSONArray vetor = (JSONArray) json.get("servico");
            JSONObject object = (JSONObject) vetor.get(0);
            servico.setCod(object.getInt("svcCod"));
            servico.setDescricao(object.getString("svcDescricao"));
            servico.setValor(object.getDouble("svcValor"));
        } catch (Exception x) {
        }
        return servico;
    }

    public static String geraJSONServicos(ArrayList<Servico> servicos) {
        ArrayList<JSONObject> tabelaServicos = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Servico servico : servicos) {
            registro = preencheJSON(servico);

            //adiciona registro à lista de registros
            tabelaServicos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("servico", (Object) tabelaServicos);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONServico(Servico servico) {
        return UtilJSON.limpaJSON(preencheJSON(servico));
    }

    public static JSONObject preencheJSON(Servico servico) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("svcCod", servico.getCod());
            registro.put("svcDescricao", servico.getDescricao());
            registro.put("svcValor", servico.getValor());
        } catch (JSONException k) {
        }
        return null;
    }

}
