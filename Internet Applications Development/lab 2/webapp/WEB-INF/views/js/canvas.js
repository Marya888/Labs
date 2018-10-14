
draw("canvas");



document.getElementById("canvas").onclick = function (ev) {
    var r = document.getElementById("form").r.value;
    clicCanvas("canvas", r, ev);
};

function canvas(id, x, y, r) {
    var canvas = document.getElementById(id);
    if (canvas.getContext) {
        var context = canvas.getContext("2d");

        var width = canvas.width;
        var height = canvas.height;

        var half_width = width / 2;
        var half_height = height / 2;

        var quarter_width = half_width / 2 - (width / 20);
        var quarter_height = half_height / 2 - (height / 20);

        var pointer_x = (x / r) * quarter_width * 2;
        var pointer_y = (y / r) * quarter_height * 2;

        context.clearRect(0, 0, width, height);
        //Create figure
        {
            context.beginPath();
            context.moveTo(half_width, half_height);
            context.ellipse(half_width, half_height, quarter_width, quarter_height, 0, Math.PI / 2, Math.PI);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();

            context.beginPath();
            context.moveTo(half_width, half_height);
            context.rect(half_width, half_height - quarter_height, quarter_width*2, quarter_height);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();

            context.beginPath();
            context.moveTo(half_width, half_height);
            context.lineTo(half_width, half_height + quarter_height * 2);
            context.lineTo(half_width  + quarter_width * 2, half_height);
            context.lineTo(half_width, half_height);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();
        }


        context.strokeStyle = "black";
        context.fillStyle = "black";
        //Create grid
        {
            context.beginPath();
            context.font = "10px sans-serif";
            context.moveTo(0, half_height);
            context.lineTo(width, half_height);
            context.lineTo(width - 8, half_height + 3);
            context.lineTo(width - 8, half_height - 3);
            context.lineTo(width, half_height);
            context.fillText("X", width - 8, half_height - 7);

            context.moveTo(half_width, 0);
            context.lineTo((half_width) - 3, 8);
            context.lineTo((half_width) + 3, 8);
            context.lineTo(half_width, 0);
            context.lineTo(half_width, height);
            context.fillText("Y", half_width + 5, 10);


            context.moveTo(half_width - 2 * quarter_width, half_height - 4);
            context.lineTo(half_width - 2 * quarter_width, half_height + 4);
            context.fillText(-r, half_width - 2 * quarter_width - 5, half_height - 6);

            context.moveTo(half_width - 1 * quarter_width, half_height - 4);
            context.lineTo(half_width - 1 * quarter_width, half_height + 4);
            context.fillText(-r / 2, half_width - 1 * quarter_width - 8, half_height - 6);

            context.moveTo(half_width + 2 * quarter_width, half_height - 4);
            context.lineTo(half_width + 2 * quarter_width, half_height + 4);
            context.fillText(r, half_width + 2 * quarter_width - 3, half_height - 6);

            context.moveTo(half_width + 1 * quarter_width, half_height - 4);
            context.lineTo(half_width + 1 * quarter_width, half_height + 4);
            context.fillText(r / 2, half_width + 1 * quarter_width - 5, half_height - 6);


            context.moveTo(half_width - 4, half_height - (2 * quarter_height));
            context.lineTo(half_width + 4, half_height - (2 * quarter_height));
            context.fillText(r, half_width + 5, half_height - 2 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height - (1 * quarter_height));
            context.lineTo(half_width + 4, half_height - (1 * quarter_height));
            context.fillText(r / 2, half_width + 5, half_height - 1 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height + (2 * quarter_height));
            context.lineTo(half_width + 4, half_height + (2 * quarter_height));
            context.fillText(-r, half_width + 5, half_height + 2 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height + (1 * quarter_height));
            context.lineTo(half_width + 4, half_height + (1 * quarter_height));
            context.fillText(-r / 2, half_width + 5, half_height + 1 * quarter_height + 4);

            context.closePath();
            context.strokeStyle = "black";
            context.fillStyle = "black";
            context.stroke();
            context.fill();
        }

        context.strokeStyle = "red";
        context.fillStyle = "red";
        //Create point of answer
        {
            context.beginPath();
            context.moveTo(half_width + pointer_x - 1, half_height - pointer_y);
            context.lineTo(half_width + pointer_x + 1, half_height - pointer_y);
            context.moveTo(half_width + pointer_x, half_height - pointer_y - 1);
            context.lineTo(half_width + pointer_x, half_height - pointer_y + 1);
            context.closePath();
        }
        context.stroke();
        context.fill();
    }
    x = x.toFixed(3);
    y = y.toFixed(3);
    window.location.href = "/lab3/check?x="+x+"&y="+y+"&r="+r+"&iscanvas=true";
}

function draw(id) {
    var canvas = document.getElementById(id);
    if (canvas.getContext) {
        var context = canvas.getContext("2d");

        var width = canvas.width;
        var height = canvas.height;

        var half_width = width / 2;
        var half_height = height / 2;

        var quarter_width = half_width / 2 - (width / 20);
        var quarter_height = half_height / 2 - (height / 20);

        context.clearRect(0, 0, width, height);
        //Create figure
        {
            context.beginPath();
            context.moveTo(half_width, half_height);
            context.ellipse(half_width, half_height, quarter_width, quarter_height, 0, Math.PI / 2, Math.PI);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();

            context.beginPath();
            context.moveTo(half_width, half_height);
            context.rect(half_width, half_height - quarter_height, quarter_width*2, quarter_height);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();

            context.beginPath();
            context.moveTo(half_width, half_height);
            context.lineTo(half_width, half_height + quarter_height * 2);
            context.lineTo(half_width  + quarter_width * 2, half_height);
            context.lineTo(half_width, half_height);
            context.closePath();
            context.fillStyle = 'rgba(35, 148, 149, 0.7)';
            context.fill();
        }


        context.strokeStyle = "black";
        context.fillStyle = "black";
        //Create grid
        {
            context.beginPath();
            context.font = "10px sans-serif";
            context.moveTo(0, half_height);
            context.lineTo(width, half_height);
            context.lineTo(width - 8, half_height + 3);
            context.lineTo(width - 8, half_height - 3);
            context.lineTo(width, half_height);
            context.fillText("X", width - 8, half_height - 7);

            context.moveTo(half_width, 0);
            context.lineTo((half_width) - 3, 8);
            context.lineTo((half_width) + 3, 8);
            context.lineTo(half_width, 0);
            context.lineTo(half_width, height);
            context.fillText("Y", half_width + 5, 10);


            context.moveTo(half_width - 2 * quarter_width, half_height - 4);
            context.lineTo(half_width - 2 * quarter_width, half_height + 4);
            context.fillText("-R", half_width - 2 * quarter_width - 5, half_height - 6);

            context.moveTo(half_width - 1 * quarter_width, half_height - 4);
            context.lineTo(half_width - 1 * quarter_width, half_height + 4);
            context.fillText("-R/2", half_width - 1 * quarter_width - 8, half_height - 6);

            context.moveTo(half_width + 2 * quarter_width, half_height - 4);
            context.lineTo(half_width + 2 * quarter_width, half_height + 4);
            context.fillText("R", half_width + 2 * quarter_width - 3, half_height - 6);

            context.moveTo(half_width + 1 * quarter_width, half_height - 4);
            context.lineTo(half_width + 1 * quarter_width, half_height + 4);
            context.fillText("R/2", half_width + 1 * quarter_width - 5, half_height - 6);


            context.moveTo(half_width - 4, half_height - (2 * quarter_height));
            context.lineTo(half_width + 4, half_height - (2 * quarter_height));
            context.fillText("R", half_width + 5, half_height - 2 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height - (1 * quarter_height));
            context.lineTo(half_width + 4, half_height - (1 * quarter_height));
            context.fillText("R/2", half_width + 5, half_height - 1 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height + (2 * quarter_height));
            context.lineTo(half_width + 4, half_height + (2 * quarter_height));
            context.fillText("-R", half_width + 5, half_height + 2 * quarter_height + 4);

            context.moveTo(half_width - 4, half_height + (1 * quarter_height));
            context.lineTo(half_width + 4, half_height + (1 * quarter_height));
            context.fillText("-R/2", half_width + 5, half_height + 1 * quarter_height + 4);

            context.closePath();
            context.strokeStyle = "black";
            context.fillStyle = "black";
            context.stroke();
            context.fill();
        }
        context.stroke();
        context.fill();
    }
}


function clicCanvas(canvId, R, ev) {
    var elem = document.getElementById(canvId);
    var br = elem.getBoundingClientRect();
    var left = br.left;
    var top = br.top;

    var sdvig_x = elem.width / 2;
    var sdvig_y = elem.height / 2;
    var x = (ev.clientX-left - sdvig_x) / (4/5*sdvig_x) * R;
    var y = - (ev.clientY-top - sdvig_y) / (4/5*sdvig_y) * R;

    canvas(canvId, x , y, R);
}