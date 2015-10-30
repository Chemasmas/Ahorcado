<#include "/Encabezado.ftl">
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-7 col-md-7 alto">
                    <div class="row Pregunta">
                        <div class="col-sm-12 col-md-12" >
                            <div class="integral hide" id="integral">

                                \(\int_a^b \! f(x) \, \mathrm{d}x.\)

                            </div>
                        </div>
                    </div>
                    <div class="row">
                            <form class="form-horizontal col-sm-12 col-md-12" action="">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" for="respuesta">Respuesta</label>
                                    <div class="col-sm-10">
                                        <textarea cols="90" rows="6" name="respuesta"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-2 col-sm-3">
                                        <a class="btn2 btn-blue">Salir</a>
                                    </div>
                                    <div class="col-sm-offset-4 col-sm-3">
                                        <a class="btn2 btn-green">Siguiente</a>
                                    </div>

                                </div>
                            </form>
                    </div>
                </div>
                <div class="col-sm-5 col-md-5 alto">
                    <canvas class="monito" id="mono" name="mono">

                    </canvas>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="https://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>
        <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script type="text/javascript">
            $(function() {
                //$(".Pregunta").show();
                setTimeout(function() {
                    console.log("Hola");
                    $(".integral").removeClass("hide");
                }, 2000);
            });
        </script>
<#include "/Pie.ftl">