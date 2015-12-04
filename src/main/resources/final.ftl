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
                        <th>Nivel</th>
                    </tr>
                </thead>
                <tbody>
                <#list resultados as resultado>
                    <tr>
                        <td>${resultado.tipo}</td>
                        <td> \( ${resultado.latex} \)</td>
                        <td>
                            <#if (resultado.sol)??>
                                <table>
                                \( ${resultado.sol} \)
                                </table>
                            </#if>
                        </td>
                        <td>${resultado.nivel}</td>
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