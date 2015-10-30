package com.chemasmas.ahorcado;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.*;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;

public class AhorcadoWolfram {
    //URI derivada=new U

    final static String APPID="HH7R5A-XW766K5L8T";
    final static String IMAGENTEXTO="image,plaintext";

    static String[] getDerivada(String input) throws URISyntaxException, IOException, ParserConfigurationException, SAXException {
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
            return processXML(EntityUtils.toString(entidad));
        }
        return null;
    }

    private static String[] processXML(String xml) throws ParserConfigurationException, IOException, SAXException {
        String[] res=new String[2];
        DocumentBuilderFactory dbf =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(xml));
        Document doc = db.parse(is);
        NodeList texto=doc.getElementsByTagName("plaintext");
        String ecu=texto.item(0).getTextContent().trim();
        res[0]=ecu;
        System.out.println(ecu);
        NodeList imagenes=doc.getElementsByTagName("img");
        Node imagen=imagenes.item(0);
        res[1]= String.valueOf(imagen.getAttributes().getNamedItem("src"));
        System.out.println(res[1]);

        return res;
    }
    public static void main(String []args)
    {
        try {
            getDerivada("x**2");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

}
