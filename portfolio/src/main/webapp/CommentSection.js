async function initComments(){
    text = await fetchData();
    addCommentsToDOM(text);
}

function addCommentsToDOM(text){
    $("#commentSection").html(text);
}

async function fetchData() {
    const response = await fetch("/data");
    const text = await response.text();
    return text;
}
