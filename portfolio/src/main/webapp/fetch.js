function fetchData(url) {

    return fetch(url)
    .then((response) => 
    {
        if(!response.ok)
        {
            throw new Error('Network error detected');
        }
        return response.json();
    })
    .catch((error) => handleFetchError(error));
}

function postData(url, data) {
    return fetch(url, {
     method: "POST",
     headers: {
         "Content-Type": "application/x-www-form-urlencoded"
     },
     body: data
     }
    )
    .then((response) => 
    {
        if(!response.ok)
        {
            throw new Error('Network error detected');
        }
        return response.json();
    })
    .catch((error) => handleFetchError(error));
}

function handleFetchError(exception)
{
    console.error(exception);
}
