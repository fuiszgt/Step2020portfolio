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

import java.util.*;
import com.google.sps.data.Comment;
import com.google.sps.interfaces.DatastoreInterface;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@WebServlet("/data")
public class CommentLoaderServlet extends HttpServlet {

  private Gson gson = new Gson();
  private DatastoreInterface datastoreInterface = new DatastoreInterface();
  private UserService userService = UserServiceFactory.getUserService();

  private class DataPackage {
      private boolean isUserLoggedIn;
      private List<Comment> comments;
      DataPackage(){
          this.isUserLoggedIn = DataServlet.this.userService.isUserLoggedIn();
          this.comments = DataServlet.this.datastoreInterface.getComments();
      }

  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON);
    String json = dataToJson();
    response.getWriter().println(json);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      //TODO: use GSON deserialization
      if(userService.isUserLoggedIn()){
        String name = request.getParameter("name");
        String content = request.getParameter("content");
        Comment comment = new Comment(name, content);
        datastoreInterface.addComment(comment);
        response.sendRedirect("/index.html#comment-section");
      }else{
        response.sendRedirect("/login");
      }
  }

  private String dataToJson(){
    DataPackage dataPackage = new DataPackage();
    String json = gson.toJson(dataPackage);
    return json;
  }
}