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
            JSONArray vetor = (JSONArray) json.get("ordemservico");
            JSONObject object = (JSONObject) vetor.get(0);
            os.setCod(object.getInt("cod"));
            os.setCliente(new ClienteDao().get(object.getInt("cliente_codigo")));
            os.setCarro(new CarroDao().get(object.getInt("carro_cod")));
            os.setData(Timestamp.valueOf(object.getString("data")));
            os.setSituacao(object.getInt("situacao"));
            os.setTipo(object.getString("tipo"));
            os.setDescricao(object.getString("descricao"));
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
            registro.put("cod", os.getCod());
            registro.put("cliente_codigo", os.getCliente().getCodigo());
            registro.put("carro_cod", os.getCarro().getCod());
            registro.put("data", os.getData().toString());
            registro.put("descricao", os.getDescricao());
            registro.put("situacao", os.getSituacao());
            registro.put("tipo", os.getTipo());
        } catch (JSONException k) {
        }
        return null;
    }

}
