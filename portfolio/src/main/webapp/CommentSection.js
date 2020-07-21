 async function initComments()
{
    fetchData()
    .then(text => addCommentsToDOM(text))
    .catch(error => handleFetchError(error));
}

function addCommentsToDOM(text)
{
    debugger;
    $("#commentSection").html(text);
}

function handleFetchError(text)
{
    console.log(text);
}

function fetchData() {
    return fetch("/data")
    .then(response => 
    {
        if(!response.ok)
        {
            throw new Error('Network error detected');
        }
        return response.text();
    });
}
