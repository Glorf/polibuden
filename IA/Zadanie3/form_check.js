function isEmpty(text) {
    return !text;
}

function validate(form) {
    checkStringAndFocus(form.elements["f_fname"], "First name");
    checkString(form.elements["f_lname"], "Last name");
    checkZIPCodeRegEx(form.elements["f_zip"].value);
    checkString(form.elements["f_street"], "Street");
    checkString(form.elements["f_city"], "City");
    checkEmailRegEx(form.elements["f_email"]);

    for(var i=0; i<form.elements.length; i++) {
        form.elements.item(i).className = "wrong"
    }
}

function isWhiteSpace(str)  {
    var ws = "\t\n\r ";
    for(var i = 0; i < str.length; i++) {
        var c = str.charAt(i);
        if(ws.indexOf(c) == -1) {
            return false;
        }
    }
    return true;
}

function checkString(obj, msg) {
    text = obj.value;
    if(isEmpty(text) || isWhiteSpace(text)) {
        alert(msg + " cannot be empty!");
        return false;
    }

    return true;
}

function checkStringAndFocus(obj, msg) {
    var str = obj.value;
    var errorFieldName = "e_"+ obj.name.substr(2, obj.name.length);
    if(isWhiteSpace(str) || isEmpty(str)) {
        document.getElementById(errorFieldName).innerHTML = msg;
        obj.focus();
        startTimer(errorFieldName);
        return false;
    }
    else {
        return true;
    }
}

function checkEmail(str) {
    if(isWhiteSpace(str)) {
        alert("Incorrect e-mail");
        return false;
    }
    else {
        var at = str.indexOf("@");
        if(at < 1) {
            alert("Incorrect e-mail");
            return false;
        }
        else {
            var l = -1;
            for(var i = 0; i < str.length; i++) {
                var c = str.charAt(i);
                if(c == ".") {
                    l = i;
                }
            }
            if((l < (at + 2)) || (l == str.length-1)) {
                alert("Incorrect e-mail");
                return false;
            }
        }
        return true;
    }
}

function checkEmailRegEx(obj) {
    var email = /[a-zA-Z_0-9\.]+@[a-zA-Z_0-9\.]+\.[a-zA-Z][a-zA-Z]+/;
    if(email.test(obj.value))
        return true;
    else {
        alert("Wrong e-mail address");
        return false;
    }
}

function checkZIPCodeRegEx(text) {
    var zipField = document.getElementById("zip");
    var zip = /[0-9]{2}-[0-9]{3}/;
    if(zip.test(text)) {
        zipField.innerHTML = "OK";
        zipField.className = "green";
        return false;
    }
    else {
        zipField.innerText = "WRONG";
        zipField.className = "red";
        return true;
    }
}

var errorField = "";
function startTimer(fName) {
    errorField = fName;
    window.setTimeout("clearError(errorField)", 5000);
}

function clearError(objName) {
    document.getElementById(objName).innerHTML = "";
}

function showElement(e) {
    document.getElementById(e).style.visibility = 'visible';
}
function hideElement(e) {
    document.getElementById(e).style.visibility = 'hidden';
}

function alterRows(i, e) {
    if(e) {
        if(i % 2 == 1) {
            e.setAttribute("style", "background-color: aqua;");
        }
        e = e.nextSibling;
        while(e && e.nodeType != 1) {
            e = e.nextSibling;
        }
        alterRows(++i, e);
    }
}

alterRows(1, document.getElementsByTagName("TR")[0]);


function nextNode(e) {
    while(e && e.nodeType != 1) {
        e = e.nextSibling;
    }
    return e;
}

function prevNode(e) {
    while(e && e.nodeType != 1) {
        e = e.previousSibling;
    }
    return e;
}

function swapRows(b) {
    var tab = prevNode(b.previousSibling);
    var tBody = nextNode(tab.firstChild);
    var lastNode = prevNode(tBody.lastChild);
    tBody.removeChild(lastNode);
    var firstNode = nextNode(tBody.firstChild);
    tBody.insertBefore(lastNode, firstNode);
}

function cnt(form, msg, maxSize) {
    if(form.value.length > maxSize)
        form.value = form.value.substring(0, maxSize);
    else
        msg.innerHTML = maxSize -form.value.length;
}



















