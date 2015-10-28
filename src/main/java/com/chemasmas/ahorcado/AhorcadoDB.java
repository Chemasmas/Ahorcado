package com.chemasmas.ahorcado;

import com.chemasmas.ahorcado.entidades.Score;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;

import java.util.ArrayList;

public class AhorcadoDB {
    MongoClient cliente=new MongoClient();
    MongoDatabase db=cliente.getDatabase("ahorcado");


    public ArrayList<Score> getTopScore()
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
}
