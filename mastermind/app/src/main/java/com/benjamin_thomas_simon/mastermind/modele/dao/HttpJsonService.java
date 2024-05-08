package com.benjamin_thomas_simon.mastermind.modele.dao;

import com.benjamin_thomas_simon.mastermind.modele.entite.Code;
import com.benjamin_thomas_simon.mastermind.modele.entite.EntiteMasterMind;
import com.benjamin_thomas_simon.mastermind.modele.entite.Stat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Sébastien Vermandele
 * @since 01/11/2020
 */

public class HttpJsonService {

    private static final String URL_POINT_ENTREE = "http://10.0.2.2:3000";

    public EntiteMasterMind getEntites() throws IOException, JSONException {

        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder().url(URL_POINT_ENTREE + "/codesSecrets").build();

        Response response = okHttpClient.newCall(request).execute();
        ResponseBody responseBody = response.body();
        String jsonStr = responseBody.string();
        List<Code> codes = null;

        if (!jsonStr.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                codes = Arrays.asList(mapper.readValue(jsonStr, Code[].class));

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        okHttpClient = new OkHttpClient();

        request = new Request.Builder().url(URL_POINT_ENTREE + "/stats").build();

        response = okHttpClient.newCall(request).execute();
        responseBody = response.body();
        jsonStr = responseBody.string();
        List<Stat> stats = null;

        if (!jsonStr.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                stats = Arrays.asList(mapper.readValue(jsonStr, Stat[].class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        //Couleurs

        okHttpClient = new OkHttpClient();

        request = new Request.Builder().url(URL_POINT_ENTREE + "/couleursDisponibles").build();

        response = okHttpClient.newCall(request).execute();
        responseBody = response.body();
        jsonStr = responseBody.string();
        List<String> couleurs = null;

        if (!jsonStr.isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            try {

                JsonNode jsonNode = mapper.readTree(jsonStr);
                String arrayString = jsonNode.get("liste").toString();
                couleurs = mapper.readValue(arrayString, new TypeReference<List<String>>() {
                });

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }

        EntiteMasterMind entiteMasterMind = null;
        if (couleurs != null && stats != null && codes != null) {
            entiteMasterMind = new EntiteMasterMind(couleurs, codes, stats);
        }
        return entiteMasterMind;
    }

    public boolean enregistrerStat(Stat stat, Boolean newEntree) throws IOException, JSONException {
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; chartset=utf-8");

        JSONObject obj = new JSONObject();
        obj.put("id", String.valueOf(stat.getId()));
        obj.put("idCode", stat.getIdCode());
        obj.put("record", stat.getRecord());
        obj.put("courriel", stat.getCourriel());

        RequestBody corpsRequete = RequestBody.create(String.valueOf(obj), JSON);
        String url = URL_POINT_ENTREE + "/stats/";
        Request request;
        if (!newEntree) {
            url += "" + stat.getId();
            request = new Request.Builder().url(url).put(corpsRequete).build();
        } else {
            request = new Request.Builder().url(url).post(corpsRequete).build();
        }

        Response response = okHttpClient.newCall(request).execute();
        return response.code() < 300;
    }
}