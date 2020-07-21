function initComments()
{
    fetchData()
    .then(comments => addCommentsToDOM(comments))
    .catch(error => handleFetchError(error));
}

function addCommentsToDOM(comments)
{
    for(id in comments){
        addSingleComment(id, comments[id]);
    }
}

function addSingleComment(id, comment){
    $("#commentSection").append('<div id="'+id+'"></div>');
    $("#commentSection #" + id).append('<p class="name"></p>');
    $("#commentSection #" + id).append('<p class="content"></p>');
    $("#commentSection #" + id + " .name").html(comment.name);
    $("#commentSection #" + id + " .content").html(comment.content);
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
        return response.json();
    });
}
