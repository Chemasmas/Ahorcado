<#include "/Encabezado.ftl">
<div class="container-fluid">
    <div class="margin-10 row">
        <div class="col-sm-12 col-md-12">
            <div class="titulo2"> Re\(\int\)ultados</div>
            </div>
        </div>
    </div>

<div class="container-fluid">
    <div class="margin-10 row">
        <div class="col-sm-12 col-md-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Problema</th>
                        <th>Resultado</th>
                        <th>Procedimiento</th>
                    </tr>
                </thead>
                <tbody>
                <#list problemas as problema>
                    <tr>
                        <td>${score.pos}</td>
                        <td>${score.nombre}</td>
                        <td>${score.puntaje}</td>
                        <td>${score.dificultad}</td>
                        </tr>
                </#list>
                    </tbody>
                </table>
            </div>
        </div>
    <div class="margin-10 row">
        <div class="col-sm-12 col-md-12">
            <a href="/" class="btn2 btn-green">Regresar</a>
            </div>
        </div>
    </div>
<#include "/Pie.ftl">