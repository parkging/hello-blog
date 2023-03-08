
function aaa() {
    alert("aaa");
}

function sendPostMultipart(url, params) {

    var form = document.createElement('form');

    form.setAttribute('method', 'post');
    form.setAttribute('action', url);
    form.setAttribute('enctype', 'multipart/form-data');
    document.characterSet = "utf-8";

    params.forEach((value, key) => {
        var hiddenField = document.createElement('input');
        // if(value.type == "file") {
        //     hiddenField.setAttribute('type', 'file');
        // } else {
        //     hiddenField.setAttribute('type', 'hidden');
        // }

        if(value instanceof File) {
            hiddenField.setAttribute('type', 'file');

        } else {
            hiddenField.setAttribute('type', 'hidden');
        }
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', value);
        console.log(key + ': ' + value);

        form.appendChild(hiddenField);

    })
    document.body.appendChild(form);
    debugger;
    form.submit();
}

function snedPost(url, params) {
    var form = document.createElement('form');
    form.setAttribute('method', 'post');
    form.setAttribute('action', url);
    document.characterSet = "utf-8";

    params.forEach((value, key) => {
        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', key);
        hiddenField.setAttribute('value', value);
        form.appendChild(hiddenField);
    })

    document.body.appendChild(form);
    form.submit();
}

function fetchPage(url, target) {
    fetch(url)
        .then((response) => {
        if (response.ok) {
            return response.text();
        }
        throw new Error('요청을 처리하지 못했습니다.');
        })
        .then((response) => {
            let domParser = new DOMParser();
            let html = domParser.parseFromString(response, 'text/html');
            let source = html.getElementById(target);
            let frag = document.getElementById(target);
            frag.replaceWith(source);
        })
        .catch((e) => {
            console.log(e);
        })
}