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

import java.io.*;
import com.google.sps.data.Comment;
import com.google.sps.data.LoginInfo;
import com.google.sps.controllers.Authenticator;
import com.google.sps.interfaces.DatastoreInterface;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/delete_comment")
public class DeleteCommentServlet extends HttpServlet {

  private DatastoreInterface datastoreInterface = new DatastoreInterface();
  private Authenticator authenticator = new Authenticator();
 
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    LoginInfo loginInfo = authenticator.getLoginInfo();
    if(loginInfo.isLoggedIn){ 
        String signedInUserId = authenticator.getUId();
        Long deleteId = Long. parseLong(request.getParameter("id")); //look out for values which are not numeric
        Comment deleteCandidate = datastoreInterface.getSingleComment(deleteId);
        if(deleteCandidate != null){
            String commentUserId = deleteCandidate.getUserId();
            System.out.println(signedInUserId);
            System.out.println(commentUserId);
            if(signedInUserId.equals(commentUserId)){
                System.out.println("Im here");
                datastoreInterface.deleteCommentById(deleteId);
            }
        }
    }
    response.sendRedirect("/index.html#comment-section");
  }
  
}