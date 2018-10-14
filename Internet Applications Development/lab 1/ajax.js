window.onload = function(){


    var x;
    var y;
    var r;

    document.addEventListener('keydown', (event) => {
        if(event.keyCode == 13) {
        event.preventDefault();
        document.querySelector('input[name=done]').click();
    }
});


    document.querySelector('input[name=done]').onclick = function() {
        x = getButtonValue(document.field);
        y = document.field.y.value;
        r = getCheckedValue(document.field.r);
        y = yComma(y);

        if (volidet(x, y, r))
            window.location.href = "/lab3/kek?x="+x+"&y="+y+"&r="+r;
        else
            return;
    }
}

function yComma(y){
    var pos=y.indexOf(',');
    if(pos!=-1 && pos!=0 && pos!=y.length-1){
        if(!isNaN(Number(y.substring(0,pos))) && !isNaN(Number(y.substring(pos+1)))){
            return y.substring(0,pos)+'.'+y.substring(pos+1);
        }else{
            return y;
        }
    }else{
        return y;
    }
}

function volidet(x,y,r){
    if(x=="Выберите X"){
        document.getElementById("volidButton").innerHTML=x;
    }else{
        document.getElementById("volidButton").innerHTML="";
    }
    if(y=="" || y[0]==' '){
        document.getElementById("volidText").innerHTML="Установите Y";
    }else if(isNaN(Number(y))){
        document.getElementById("volidText").innerHTML="Y может быть только числом!!!";
    }else if(+y<=-3 || +y>=5){
        document.getElementById("volidText").innerHTML="Y должен входить в диапозон (-3;5)";
    }else if(y[0]=='.'){
        document.getElementById("volidText").innerHTML="Y не должен начинаться с \".\"";
    }else{
        document.getElementById("volidText").innerHTML="";
    }
    if(r=="Выберите R"){
        document.getElementById("volidRedio").innerHTML=r;
    }else{
        document.getElementById("volidRedio").innerHTML="";
    }

    return (x!="Выберите X") && (y!="") && (y[0]!=" ") && (r!="Выберите R") && (!isNaN(Number(y))) && (+y>-3) && (+y<5) && (y[0]!='.');
}

function getButtonValue(buttonObj) {
    if(!buttonObj)
        return "";
    var buttonLength = buttonObj.length;
    if(buttonLength == undefined)
        if(buttonObj.style.background)
            return buttonObj.value;
        else
            return "";
    for(var i = 0; i < buttonLength; i++) {
        if(buttonObj[i].style.background) {
            return buttonObj[i].value;
        }
    }
    return "Выберите X";
}


function getCheckedValue(radioObj) {
    if(!radioObj)
        return "";
    var radioLength = radioObj.length;
    if(radioLength == undefined)
        if(radioObj.checked)
            return radioObj.value;
        else
            return "";
    for(var i = 0; i < radioLength; i++) {
        if(radioObj[i].checked) {
            return radioObj[i].value;
        }
    }
    return "Выберите R";
}

function ajax2(x,y,r){
    var params = '?x='+x+'&y='+y+'&r='+r;
    var request = new XMLHttpRequest();

    request.open('GET', '/lab3/kek'+params);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send();
}


function ajax(x,y,r){
    var params = '?x='+x+'&y='+y+'&r='+r;
    var request = new XMLHttpRequest();

    request.onreadystatechange = function(){
        if(request.readyState == 4 && request.status == 200){

            var element1 = document.getElementById('foo1');
            var positionInfo1 = element1.getBoundingClientRect();
            var w1 = positionInfo1.width;
            var element2 = document.getElementById('foo2');
            var positionInfo2 = element2.getBoundingClientRect();
            var w2 = positionInfo2.width;
            var element3 = document.getElementById('foo3');
            var positionInfo3 = element3.getBoundingClientRect();
            var w3 = positionInfo3.width;
            var element4 = document.getElementById('foo4');
            var positionInfo4 = element4.getBoundingClientRect();
            var w4 = positionInfo4.width;
            var element5 = document.getElementById('foo5');
            var positionInfo5 = element5.getBoundingClientRect();
            var w5 = positionInfo5.width;
            var element6 = document.getElementById('foo6');
            var positionInfo6 = element6.getBoundingClientRect();
            var w6 = positionInfo6.width;


            if(request.responseText[0]=="{"){

                var json = JSON.parse(request.responseText);
                var tbody = document.getElementById('tbody');
                var tr = document.createElement('tr');

                var td1 = document.createElement('td'); td1.innerHTML = x;
                td1.style.backgroundColor = "green";  /*Khaki*/
                td1.style.width = w1+"px";

                var td2 = document.createElement('td');

                if(y.length-y.indexOf('.')<=4){
                    td2.innerHTML=y;
                }else{
                    td2.innerHTML = y.substring(0,y.indexOf('.')+4);  /*(+y).toFixed(3);*/
                }


                td2.style.backgroundColor = "green";  /*Khaki*/
                td2.style.width = (w2-1)+"px";

                var td3 = document.createElement('td'); td3.innerHTML = r;
                td3.style.backgroundColor = "green";  /*Khaki*/
                td3.style.width = (w3-1)+"px";

                if(+json.res){
                    var td4 = document.createElement('td'); td4.innerHTML = "попадание";
                }else{
                    var td4 = document.createElement('td'); td4.innerHTML = "не попадание";
                }
                td4.style.backgroundColor = "#A52A2A";
                td4.style.width = (w4-1)+"px";

                var date = new Date();
                var options = {year: 'numeric',month: 'numeric',day: 'numeric',hour: 'numeric',minute: 'numeric',second: 'numeric'};
                var td5 = document.createElement('td'); td5.innerHTML = date.toLocaleString("ru", options);
//date.toTimeString();
//date.toDateString();
//date.getDate()+'.'+date.getMonth()+'.'+date.getFullYear()+' '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
                td5.style.backgroundColor = "#00BFFF";
                td5.style.width = (w5-1)+"px";

                var td6 = document.createElement('td'); td6.innerHTML = json.time;
                td6.style.backgroundColor = "#00BFFF";
                td6.style.width = (w6-19)+"px";

                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);
                tbody.appendChild(tr);

                document.field.y.value='';

            }
        }
    }

    request.open('GET', 'server.php'+params);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send();
}



