package servidor.rest;

import java.util.ArrayList;
import model.Servico_OS;
import model.dao.FuncionarioDao;
import model.dao.OrdemServicoDao;
import model.dao.ServicoDao;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class Servico_OSJSON {

    public static Servico_OS getServico_OSJSON(JSONObject json) {
        //instancia vetor de servico_os
        Servico_OS servico_os = new Servico_OS();
        try {
            //pega do json os registros da tag servico_os
            JSONArray vetor = (JSONArray) json.get("servico_os");
            JSONObject object = (JSONObject) vetor.get(0);
            servico_os.setCod(object.getInt("cod"));
            servico_os.setFuncionario(new FuncionarioDao().get(object.getInt("funcionario_codigo")));
            servico_os.setOrdemservico(new OrdemServicoDao().get(object.getInt("ordemservico_cod")));
            servico_os.setServico(new ServicoDao().get(object.getInt("servico_cod")));
        } catch (Exception x) {
        }
        return servico_os;
    }

    public static String geraJSONServico_OSs(ArrayList<Servico_OS> servico_os) {
        ArrayList<JSONObject> tabelaServico_OSs = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Servico_OS servico : servico_os) {
            registro = preencheJSON(servico);

            //adiciona registro à lista de registros
            tabelaServico_OSs.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("servico_os", (Object) tabelaServico_OSs);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONServico_OS(Servico_OS servico_os) {
        return UtilJSON.limpaJSON(preencheJSON(servico_os));
    }

    public static JSONObject preencheJSON(Servico_OS servico) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("cod", servico.getCod());
            registro.put("funcionario_codigo", servico.getFuncionario().getCodigo());
            registro.put("ordemservico_codigo", servico.getOrdemservico().getCod());
            registro.put("servico_codigo", servico.getServico().getCod());
        } catch (JSONException k) {
        }
        return null;
    }

}
