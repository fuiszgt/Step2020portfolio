function initComments()
{
    fetchData("/load_comments")
    .then((comments) => addCommentsToDOM(comments));
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
    $("<span></span>").addClass("date")
    .html(comment.date)
    .appendTo(newCommentDiv);
    $("<p></p>").addClass("content")
    .html(comment.content)
    .appendTo(newCommentDiv);
    newCommentDiv.appendTo("#comment-section");
}

