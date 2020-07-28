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
    .catch((error) => handleFetchError(error)); //I think this should catch errors, but I am not entirely sure.
}

function handleFetchError(exception)
{
    console.error(exception);
}
