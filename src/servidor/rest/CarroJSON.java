package servidor.rest;

import java.util.ArrayList;
import model.Carro;
import model.dao.ClienteDao;
import servidor.json.JSONArray;
import servidor.json.JSONException;
import servidor.json.JSONObject;

public class CarroJSON {

    public static Carro getCarroJSON(JSONObject json) {
        //instancia vetor de carros
        Carro carro = new Carro();
        try {
            //pega do json os registros da tag carro
            JSONArray vetor = (JSONArray) json.get("carro");
            JSONObject object = (JSONObject) vetor.get(0);
            carro.setCod(object.getInt("carCod"));
            carro.setMarca(object.getString("carMarca"));
            carro.setModelo(object.getString("carModelo"));
            carro.setCor(object.getString("carCor"));
            carro.setAno(object.getString("carAno"));
            carro.setChassi(object.getString("carNumeroChassi"));
            carro.setKm(object.getString("carQuilometragem"));
            carro.setPlaca(object.getString("carPlaca"));
            carro.setObs(object.getString("carObs"));
            carro.setDono(new ClienteDao().get(object.getInt("car_pesCod")));
        } catch (Exception x) {
        }
        return carro;
    }

    public static String geraJSONCarros(ArrayList<Carro> carros) {
        ArrayList<JSONObject> tabelaCarros = new ArrayList<>();
        JSONObject registro;
        //cria um registro primeiro
        for (Carro carro : carros) {
            registro = preencheJSON(carro);
            //adiciona registro à lista de registros
            tabelaCarros.add(registro);
        }

        //adiciona tabela
        JSONObject bd = new JSONObject();
        try {
            bd.putOpt("carro", (Object) tabelaCarros);
        } catch (JSONException u) {
        }
        return UtilJSON.limpaJSON(bd);
    }
    
      public static String geraJSONCarro(Carro carro) { 
        return UtilJSON.limpaJSON(preencheJSON(carro));
    }

    public static JSONObject preencheJSON(Carro carro) {
        JSONObject registro = new JSONObject();
        try {
            registro.put("carCod", carro.getCod());
            registro.put("carMarca", carro.getMarca());
            registro.put("carModelo", carro.getModelo());
            registro.put("carCor", carro.getCor());
            registro.put("carAno", carro.getAno());
            registro.put("carNumeroChassi", carro.getChassi());
            registro.put("carQuilometragem", carro.getKm());
            registro.put("carPlaca", carro.getPlaca());
            registro.put("carObs", carro.getObs());
            registro.put("car_pesCod", carro.getDono());
            return registro;
        } catch (JSONException k) {
        }
        return null;
    }

}
