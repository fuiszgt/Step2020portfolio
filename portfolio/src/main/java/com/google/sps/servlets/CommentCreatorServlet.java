// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.sps.data.Comment;
import com.google.sps.interfaces.DatastoreInterface;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add_comment")
public class CommentCreatorServlet extends HttpServlet {

  private DatastoreInterface datastoreInterface = new DatastoreInterface();
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    UserService userService = UserServiceFactory.getUserService();
    if(userService.isUserLoggedIn()){
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        Comment comment = new Comment(name, content);
        datastoreInterface.addComment(comment);
    }
    response.sendRedirect("/index.html#comment-section");
  }
  
}