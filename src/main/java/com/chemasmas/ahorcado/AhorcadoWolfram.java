package com.chemasmas.ahorcado;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AhorcadoWolfram {
    //URI derivada=new U

    final static String APPID="HH7R5A-XW766K5L8T";
    final static String IMAGENTEXTO="image,plaintext";

    static String getDerivada(String input) throws URISyntaxException, IOException {
        input="derivate "+input;
        URI derivada = new URIBuilder().setScheme("http")
                .setHost("api.wolframalpha.com")
                .setPath("/v2/query")
                .setParameter("appid", APPID)
                .setParameter("input", input)
                .setParameter("format", IMAGENTEXTO)
                .build();
        HttpGet httpGet = new HttpGet(derivada);
        CloseableHttpClient cliente = HttpClients.createDefault();
        CloseableHttpResponse response = cliente.execute(httpGet);

        HttpEntity entidad = response.getEntity();
        if(entidad!=null){
            long len=entidad.getContentLength();
            System.out.println(EntityUtils.toString(entidad));
        }
        return "";
    }

    public static void main(String []args)
    {
        try {
            getDerivada("x**2");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
