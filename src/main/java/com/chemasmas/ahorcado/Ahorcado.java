package com.chemasmas.ahorcado;

import com.chemasmas.ahorcado.entidades.Score;
import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import spark.ModelAndView;

import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import static spark.Spark.*;


/**
 *
 * @author Chemasmas
 */
public class Ahorcado {
    
    
    public static void main(String[] args) {
        staticFileLocation("/");
        //externalStaticFileLocation("/resources");
        Configuration config=new Configuration();
        config.setClassForTemplateLoading(FreeMarkerEngine.class, "/");
        FreeMarkerEngine engine=new FreeMarkerEngine();
        engine.setConfiguration(config);  
        
        //get("/hello",(req,res)->"Hola Spark");
        //get("/hello/:nombre",(req,res)-> "Hola "+req.params(":nombre")+"!");
        //get("/say/*/to/*", (request, response) -> {
        //    return request.splat()[0]+" "+request.splat()[1];
        //});
        //get("/freemarker",(req,response)->{
        //    HashMap<String,Object> modelo=new HashMap<>();
        //    modelo.put("name","Ok");
        //    return new ModelAndView(modelo,"hola.ftl");
        //},engine);

        //Bienvenida
        get("/",(req,res)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            return new ModelAndView(modelo,"Bienvenida.ftl");
        },engine);

        //Ver Score
        get("/score",(req,res)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            AhorcadoDB db=new AhorcadoDB();
            ArrayList<Score> scores=db.getTopScore();
            modelo.put("scores",scores);
            return new ModelAndView(modelo,"score.ftl");
        },engine);

        //Juego Nuevo
        get("/dificultad",(req,res)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            return new ModelAndView(modelo,"dificultad.ftl");
        },engine);

        //Seleccion de Nivel
        get("/juego", (req, res) -> {
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            //Aqui seteare una variable de sesion , y rediccionare a los problemas
            if (req.queryParams("nivel") != null) {
                req.session(true);
                req.session().attribute("nivel", req.queryParams("nivel"));/**/
                res.redirect("/problemas");
            } else {
                res.redirect("/");
            }
            return "";
        });

        get("/problema/:problema",(req,res)->{
            if(req.queryParams("problema")!=null)
            {
                //no han deinido problema

            }
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            return req.params(":problema");
        });
        //Problema
        get("/problema/:problema",(req,res)->{
            if(req.queryParams("problema")!=null)
            {
                //no han deinido problema

            }
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            return req.params(":problema");
        });

        //Revision de Problema
        post("/problema/:problema", (req, res) -> {
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            //recibire el resultado , procesare, asiganre puntaje y rediccionare al siguiente problema
            return req.params(":problema");
        });

        get("/final/:sesion",(req,res)->{
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            //Termino la sesion, y despliego el resultado
            return req.params(":sesion");
        });
    }
}
