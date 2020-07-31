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

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.ws.rs.core.MediaType;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.sps.interfaces.DatastoreInterface;
import com.google.sps.controllers.Authenticator;
import com.google.sps.data.LoginInfo;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private Gson gson = new Gson();
  private DatastoreInterface datastoreInterface = new DatastoreInterface();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON);
    Authenticator authenticator = new Authenticator();
    LoginInfo loginInfo = authenticator.getLoginInfo();
    String loginJSON = gson.toJson(loginInfo);
    response.getWriter().println(loginJSON);
  }
}