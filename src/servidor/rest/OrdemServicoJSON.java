package servidor.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import model.OrdemServico;
import model.dao.CarroDao;
import model.dao.ClienteDao;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class OrdemServicoJSON {

    public static OrdemServico getOrdemServicoJSON(JSONObject json) {
        //instancia vetor de oss
        OrdemServico os = new OrdemServico();
        try {
            //pega do json os registros da tag os
            JSONArray vetor = (JSONArray) json.get("OrdemServico");
            JSONObject object = (JSONObject) vetor.get(0);
            os.setCod(object.getInt("osCod"));
            os.setCliente(new ClienteDao().get(object.getInt("os_cliCod")));
            os.setCarro(new CarroDao().get(object.getInt("os_carCod")));
            os.setData(Timestamp.valueOf(object.getString("osData")));
            os.setSituacao(object.getInt("osSituacao"));
            os.setTipo(object.getString("osTipo"));
            os.setDescricao(object.getString("osDescricao"));

        } catch (Exception x) {
        }
        return os;
    }

    public static String geraJSONOrdemServicos(ArrayList<OrdemServico> ordens) {
        ArrayList<JSONObject> tabelaOrdemServicos = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (OrdemServico os : ordens) {
            registro = preencheJSON(os);

            //adiciona registro à lista de registros
            tabelaOrdemServicos.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("os", (Object) tabelaOrdemServicos);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }

    public static String geraJSONOrdemServico(OrdemServico os) {
        return UtilJSON.limpaJSON(preencheJSON(os));
    }

    public static JSONObject preencheJSON(OrdemServico os) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("osCod", os.getCod());
            registro.put("os_cliCod", os.getCliente().getCodigo());
            registro.put("os_carCod", os.getCarro().getCod());
            registro.put("osData", os.getData().toString());
            registro.put("osDescricao", os.getDescricao());
            registro.put("osSituacao", os.getSituacao());
            registro.put("osTipo", os.getTipo());
        } catch (JSONException k) {
        }
        return null;
    }

}
