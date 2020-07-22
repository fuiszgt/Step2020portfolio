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
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

@WebServlet("/data")
public class DataServlet extends HttpServlet {

  private Gson gson = new Gson();
  private Vector<Comment> comments = new Vector<Comment>();
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON);
    ArrayList<Comment> comments = new ArrayList<Comment>();
    Comment comment1 = new Comment("1","Ada","So cool!");
    Comment comment2 = new Comment("2","G","Don't be evil");
    this.comments.add(comment1);
    this.comments.add(comment2);
    String json = commentsToJson();
    response.getWriter().println(json);
  }

  private String commentsToJson(){
    String json = gson.toJson(this.comments);
    return json;
  }
}
