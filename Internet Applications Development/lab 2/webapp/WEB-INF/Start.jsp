    <%@ page contentType="text/html;charset=UTF-8" language="java" %>



<html>
<head>
    <meta charset="UTF-8">
    <title>Стартовая страница</title>
    <link rel="stylesheet" href="ret2.css">
    <%--<script type="text/javascript" charset="utf-8" src="snow.js" defer></script>--%>
    <!--    <script type="text/javascript" charset="utf-8" src="ajax.js" defer></script>  -->
</head>


<body>
<div id="body">
    <header>
        <h4>
            Университет ИТМО<br>
            Кафедра ВТ
        </h4>
        <h1>
            Лабораторная работа №1 по дисциплине: "Программирование интернет приложений"
        </h1>
        <div id="name">
            <p><b>
                <big>
                    Выполнил студент группы P3100<br>
                    <em>Шуст Иван Владимирович</em><br>
                </big>
                Вариант 18025<br>
            </b></p>
        </div>
    </header>

    <div class="content">
        <div id="layer">
            <form id="form" name="field" action="" method="get" autocomplete="on">
                <p>Чтобы узнать попала ли ваша точка в в заданную область введите: <label for="x1"><em>координату X</em></label>, <label for="y"><em>координату Y</em></label>, а также <em>параметр R</em></p>
                <ul>
                    <li>
                        <script>
                            var bt;
                            function val(val) {
                                if(bt!=undefined){
                                    bt.style.background=val.style.background;
                                }
                                val.style.background='yellow';
                                bt=val;
                            }
                        </script>
                        <label for="x1">X:</label>
                        <input name="x1" id="x1" type="button" value="-2" onclick="val(this)">
                        <input name="x2" type="button" value="-1.5" onclick="val(this)">
                        <input name="x3" type="button" value="-1" onclick="val(this)">
                        <input name="x4" type="button" value="-0.5" onclick="val(this)">
                        <input name="x5" type="button" value="0" onclick="val(this)">
                        <input name="x6" type="button" value="0.5" onclick="val(this)">
                        <input name="x7" type="button" value="1" onclick="val(this)">
                        <input name="x8" type="button" value="1.5" onclick="val(this)">
                        <input name="x9" type="button" value="2" onclick="val(this)">
                        <%
                            int code=new Integer(request.getAttribute("xyr_code").toString());

                            String x_state="",y_state="",r_state="";
                            if((int)(code*0.01)==1)
                                x_state="enter x";
                            if((int)(code*0.1)%10==1)
                                y_state="enter y";
                            if(code%10==1)
                                r_state="enter r";

                            if((int)(code*0.01)==2)
                                x_state="can't start from point";
                            if((int)(code*0.1)%10==2)
                                y_state="can't start from point";
                            if(code%10==2)
                                r_state="can't start from point";

                            if((int)(code*0.01)==4)
                                x_state="must be a number";
                            if((int)(code*0.1)%10==4)
                                y_state="must be a number";
                            if(code%10==4)
                                r_state="must be a number";

                            if((int)(code*0.01)==8)
                                x_state="|x| must be one of the values 0,1,2,3,4";
                            if((int)(code*0.1)%10==8)
                                y_state="should be in range (-3; 5)";
                            if(code%10==8)
                                r_state="must be one of the values 1,2,3,4,5";
                        %>
                        <div id=volidButton>
                            <%=x_state%>
                        </div>
                    </li>
                    <li>
                        Y <input id="y" name="y" type="text" placeholder="(-3...5)">
                        <div id=volidText>
                            <%=y_state%>
                        </div>
                    </li>
                    <li>
                        <label for="r">R:</label>
<<<<<<< Updated upstream
                        <input id="r" name="r" type="radio" value="1" checked>1
                        <input name="r" type="radio"  value="2">2
                        <input name="r" type="radio" value="3">3
                        <input name="r" type="radio" value="4">4
                        <input name="r" type="radio" value="5">5
                        <div id=volidRedio>
                            <%=r_state%>
                        </div>
=======
                        <input id="r" name="r" type="radio" value="1" onclick="canvas('canvas', 0, 0, 1);" checked>1
                        <input name="r" type="radio" value="2" onclick="canvas('canvas', 0, 0, 2);">2
                        <input name="r" type="radio" value="3" onclick="canvas('canvas', 0, 0, 3);">3
                        <input name="r" type="radio" value="4" onclick="canvas('canvas', 0, 0, 4);">4
                        <input name="r" type="radio" value="5" onclick="canvas('canvas', 0, 0, 5);">5
                        <div id=volidRedio></div>
>>>>>>> Stashed changes
                    </li>
                </ul>
            </form>

            <div id="table">
                <table>
                    <caption>Данные и результаты</caption>
                    <thead>
                    <tr>
                        <th id="foo1" style="color:green">x</th>
                        <th id="foo2" style="color:green">y</th>
                        <th id="foo3" style="color:green">R</th>
                        <th id="foo4" style="color:#A52A2A">Результат</th>
                        <th id="foo5" style="color:blue">Текущее время</th>
                        <th id="foo6" style="color:blue">Время работы (мкс)</th>
                    </tr>
                    </thead>
                    <tbody id="tbody">
                    </tbody>
                </table>
            </div>

            <p>
                <input type="button" name="done" value="Узнать результат попадания">
            </p>

        </div>
        <div id="img" title="Бесполезный график:)">
            <%--<figure>График с заданной областью</figure>--%>
            <%--<img src="123.png" alt="Картинка не грузится" usemap="#picture">--%>
            <%--<map name="picture">--%>
                <%--<area shape="rect" coords="30,111,110,150" title="попадание">--%>
                <%--<area shape="poly" coords="110,110,110,32,150,110,110,110" title="попадание">--%>
                <%--<area shape="poly" coords="70,110,70,105,72,98,74,93,76,90,79,86,82,82,85,79,88,77,92,75,95,73,100,72,105,71,110,70,110,110,70,110" title="попадание">--%>
                <%--<area shape="default" title="не попадание">--%>
            <%--</map>--%>
        </div>

    </div>


    <!--
        <footer>
            <small>Санкт-Петербург – 2018</small>

        </footer>
    -->
</div>
<canvas id="canvas" width="400px" height="400px">canvas</canvas>

<script type="text/javascript">
    <%@include file="/views/js/canvas.js"%>
</script>

</body>
</html>

