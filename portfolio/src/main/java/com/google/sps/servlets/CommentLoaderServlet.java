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

import java.util.List;
import com.google.sps.data.Comment;
import com.google.sps.interfaces.DatastoreInterface;
import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@WebServlet("/load_comments")
public class CommentLoaderServlet extends HttpServlet {

  private Gson gson = new Gson();
  private ObjectMapper objectMapper = new ObjectMapper();
  private DatastoreInterface datastoreInterface = new DatastoreInterface();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON);
    String commentsJSON = commentsToJson();
    response.getWriter().println(commentsJSON);
  }

  private String commentsToJson(){
    List<Comment> comments = datastoreInterface.getComments();
    try{
        String commentsJSON =  objectMapper.writeValueAsString(comments);
        return commentsJSON;
    }catch(JsonProcessingException e){
        return "";
    }
    
  }
}
