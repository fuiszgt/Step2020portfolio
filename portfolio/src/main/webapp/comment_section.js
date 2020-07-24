function initComments()
{
    fetchData()
    .then((comments) => addCommentsToDOM(comments))
    .catch((error) => handleFetchError(error));
}

function addCommentsToDOM(comments)
{
    comments.forEach(addSingleComment);
}

function addSingleComment(comment){
    id = comment.id;
    newCommentDiv = $("<div></div>", {"id": id});
    $("<p></p>").addClass("name")
    .html(comment.name)
    .appendTo(newCommentDiv);
    $("<p></p>").addClass("content")
    .html(comment.content)
    .appendTo(newCommentDiv);
    newCommentDiv.appendTo("#comment-section");
}

function handleFetchError(exception)
{
    console.error(exception);
}

function fetchData() {
    return fetch("/data")
    .then((response) => 
    {
        if(!response.ok)
        {
            throw new Error('Network error detected');
        }
        return response.json();
    });
}
