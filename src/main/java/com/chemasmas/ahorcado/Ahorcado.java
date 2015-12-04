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
        /*get("/freemarker",(req,response)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("name","Ok");
            return new ModelAndView(modelo,"hola.ftl");
        },engine);*/

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
                req.session().attribute("intentos", 4); //Revisar contra el js
                res.redirect("/problemas");
            }
            //} else {
              //  res.redirect("/");
            //}
            return "";
        });

        get("/problemas",(req,res)->{
            Problemas p=null;
            //if(req.session().attribute("nivel")==null)
            //{
                //No hay nivel
            //    res.redirect("/");
            //}
            //else
           // {
                p=getProblemas(req.session().attribute("nivel"));
                req.session().attribute("problemas",p);
                req.session().attribute("intentos",3);
                res.redirect("/problema/1");
            //}
            //return new ModelAndView(modelo,"problemas.ftl");
            return "";
        });

        //Problema
        //Retorna JSON
        get("/problema/:problema",(req,res)->{
            int indice=Integer.parseInt(req.params(":problema"));
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo","Ahorcado");
            if(indice<9){
                Problemas prob= req.session().attribute("problemas");
                System.out.println(prob.getProblemas().get(indice));
                modelo.put("Problema",prob.getProblemas().get(indice).getLatex());
                modelo.put("idproblema",indice);
                //return prob.getProblemas().get(indice);
                return new ModelAndView(modelo,"problemas.ftl");
                //return new ModelAndView(modelo,"dificultad.ftl");
            }
            else
            {
                res.redirect("/final");
                return new ModelAndView(modelo,"final.ftl");
            }
        },engine);

        //Revision de Problema
        post("/problema/:problema/validar", (req, res) -> {
            int indice=Integer.parseInt(req.params(":problema"));
           
            //if(req.queryParams("problema")!=null)
            //{
                //return null;
            //}
//            Problemas prob= req.session().attribute("problemas");
//            String respuesta=req.queryParams("respuesta");
            //recibire el resultado , procesare, asiganre puntaje retornare estado o redireccion


            String wolf[]=AhorcadoWolfram.getDerivada("x**3");
            
            res.redirect("/problema/"+(indice+1));
            //Debo de enviarla a wolfram y compararla
            //return new ModelAndView(modelo,"problemas.ftl");
            return "";
        });

        get("/final",(req,res)->{
            HashMap<String,Object> modelo=new HashMap<>();
            modelo.put("Titulo", "Ahorcado");
          //  if(req.session().attribute("nivel")==null)
           // {
                //No hay nivel
             //   res.redirect("/");
           // }
            //else
            //{
                Problemas ps=req.session().attribute("problemas");

                modelo.put("resultados",ps.getProblemas());
            //}
            return new ModelAndView(modelo,"final.ftl");
            //Termino la sesion, y despliego el resultado
        },engine);
    }
}
