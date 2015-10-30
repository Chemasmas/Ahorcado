package com.chemasmas.ahorcado;

import com.chemasmas.ahorcado.entidades.Problemas;
import com.chemasmas.ahorcado.entidades.Score;
import freemarker.template.Configuration;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import spark.ModelAndView;

import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import spark.template.freemarker.GSONTemplateEngine;

import static com.chemasmas.ahorcado.AhorcadoDB.*;
import static spark.Spark.*;


/**
 *
 * @author Chemasmas
 */
public class Ahorcado {


    
    public static void main(String[] args) {
        staticFileLocation("/");
        port(1234);
        //externalStaticFileLocation("/resources");
        Configuration config=new Configuration();
        config.setClassForTemplateLoading(FreeMarkerEngine.class, "/");
        FreeMarkerEngine engine=new FreeMarkerEngine();
        GSONTemplateEngine json=new GSONTemplateEngine();
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
            modelo.put("scores",getTopScore());
            return new ModelAndView(modelo,"score.ftl");
        },engine);

        //Juego Nuevo
        get("/dificultad",(req,res)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            req.session().invalidate();
            return new ModelAndView(modelo,"dificultad.ftl");
        },engine);

        //Seleccion de Nivel
        get("/juego", (req, res) -> {
            //Aqui seteare una variable de sesion , y rediccionare a los problemas
            if (req.queryParams("nivel") != null) {
                req.session(true);
                req.session().attribute("nivel", req.queryParams("nivel"));/**/
                req.session().attribute("intentos",4); //Revisar contra el js
                res.redirect("/problemas");
            } else {
                res.redirect("/");
            }
            return "";
        });

        get("/problemas",(req,res)->{
            Problemas p=null;
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            if(req.session().attribute("nivel")==null)
            {
                //No hay nivel
                res.redirect("/");
            }
            else
            {
                p= getProblemas(req.session().attribute("nivel"));
                req.session().attribute("problemas",p);
            }
            return new ModelAndView(modelo,"problemas.ftl");
        },engine);

        //Solo habra 6 problemas
        //Problema
        //Retorna JSON
        get("/problema/:problema",(req,res)->{
            if(req.queryParams("problema")!=null)
            {
                return null;
            }
            int indice=req.session().attribute("problema");
            Problemas prob= req.session().attribute("problemas");
            return prob.getProblemas().get(indice);
        },json);

        //Revision de Problema
        post("/problema/:problema/validar", (req, res) -> {
            if(req.queryParams("problema")!=null)
            {
                return null;
            }
            int indice=req.session().attribute("problema");
            Problemas prob= req.session().attribute("problemas");
            String respuesta=req.queryParams("respuesta");
            //recibire el resultado , procesare, asiganre puntaje retornare estado o redireccion

            String wolf[]=AhorcadoWolfram.getDerivada("x**3");
            //Debo de enviarla a wolfram y compararla
            return req.params(":problema");
        },json);

        get("/final",(req,res)->{
            //System.out.println((String)req.attribute("nivel"));
            //System.out.println(req.params("nivel"));
            //Termino la sesion, y despliego el resultado
            return req.params(":sesion");
        });
    }
}
