<#include "/Encabezado.ftl">
<div class="container-fluid">
    <div class="margin-10 row">
        <div class="col-sm-12 col-md-12">
            <div class="titulo2"> Puntaje\(\int\) &nbsp;&nbsp; Ma\(\int\) &nbsp;&nbsp; Alto\(\int\)</div>
            </div>
        </div>
    </div>

<div class="container-fluid">
    <div class="margin-10 row">
        <div class="col-sm-12 col-md-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Puntaje</th>
                        <th>Difilcutad</th>
                        </tr>
                    </thead>
                <tbody>
                <#list scores as score>
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