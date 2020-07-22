function initComments()
{
    fetchData()
    .then(text => addCommentsToDOM(text))
    .catch(error => handleFetchError(error));
}

function addCommentsToDOM(text)
{
    $("#commentSection").html(text);
}

function handleFetchError(exception)
{
    console.error(exception);
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
