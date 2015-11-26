package com.chemasmas.ahorcado;

import com.chemasmas.ahorcado.entidades.Problema;
import com.chemasmas.ahorcado.entidades.Problemas;
import com.chemasmas.ahorcado.entidades.Score;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AhorcadoDB {
    static MongoClient cliente=new MongoClient();
    static MongoDatabase db=cliente.getDatabase("ahorcado");


    public static  ArrayList<Score> getTopScore()
    {
        ArrayList<Score> scoresAL=new ArrayList<>();

        FindIterable<Document> scores=db.getCollection("scores").find().sort(new Document("puntaje",-1)).limit(10);
        scores.forEach(new Block<Document>() {
            int cnt=1;
            @Override
            public void apply(Document document) {
                Score curr=new Score();
                curr.setPos(cnt++);
                curr.setNombre(document.getString("nombre"));
                curr.setDificultad(document.getString("dificultad"));
                curr.setPuntaje(document.getDouble("puntaje"));
                scoresAL.add(curr);
                System.out.println(document);
            }
        });
        return scoresAL;
    }

    public static Problemas getProblemas(String nivel) {
        Problemas res=new Problemas();
        Random rnd=new Random(System.currentTimeMillis());

        FindIterable<Document> directos=db.getCollection("problemas").find(new Document("nivel",nivel).append("tipo","directo"));
        ArrayList<Problema> directosAL=new ArrayList<>();
        directos.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                Problema curr=new Problema();
                curr.setProblema(document.getString("problema"));
                curr.setLatex(document.getString("latex"));
                curr.setNivel(document.getString("nivel"));
                curr.setSol(document.getString("solucion"));
                curr.setTipo(document.getString("tipo"));

                System.out.println(curr.getProblema());
                directosAL.add(curr);
            }
        });
        Collections.shuffle(directosAL,rnd);
        //problemasAL.addAll(directosAL.subList(1, 3));
        res.getProblemas().addAll(directosAL.subList(0, 3));


        FindIterable<Document> cambio=db.getCollection("problemas").find(new Document("nivel",nivel).append("tipo","cambio"));
        ArrayList<Problema> cambioAL=new ArrayList<>();
        cambio.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                Problema curr=new Problema();
                curr.setProblema(document.getString("problema"));
                curr.setLatex(document.getString("latex"));
                curr.setNivel(document.getString("nivel"));
                curr.setSol(document.getString("solucion"));
                curr.setTipo(document.getString("tipo"));

                System.out.println(curr.getProblema());
                cambioAL.add(curr);
            }
        });
        Collections.shuffle(cambioAL,rnd);
        //problemasAL.addAll(cambioAL.subList(1,3));
        res.getProblemas().addAll(cambioAL.subList(0, 3));

        FindIterable<Document> partes=db.getCollection("problemas").find(new Document("nivel",nivel).append("tipo","partes"));
        ArrayList<Problema> partesAL=new ArrayList<>();
        partes.forEach(new Block<Document>() {
            @Override
            public void apply(Document document) {
                Problema curr=new Problema();
                curr.setProblema(document.getString("problema"));
                curr.setLatex(document.getString("latex"));
                curr.setNivel(document.getString("nivel"));
                curr.setSol(document.getString("solucion"));
                curr.setTipo(document.getString("tipo"));

                System.out.println(curr.getProblema());
                partesAL.add(curr);
            }
        });
        Collections.shuffle(partesAL,rnd);
        //problemasAL.addAll(partesAL.subList(1,3));
        res.getProblemas().addAll(partesAL.subList(0, 3));
        return res;
    }

}
