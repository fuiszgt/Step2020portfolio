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

import com.google.sps.data.User;
import com.google.sps.interfaces.DatastoreInterface;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.json.JSONException;
import com.google.gson.Gson;
import javax.ws.rs.core.MediaType;

@WebServlet("/set_nickname")
public class SetNicknameServlet extends HttpServlet {

  private DatastoreInterface datastoreInterface = new DatastoreInterface();
  private Gson gson = new Gson();
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException { //TODO: extract inner logic to a class
    UserService userService = UserServiceFactory.getUserService();
    response.setContentType(MediaType.APPLICATION_JSON);
    String nick = request.getParameter("nick"); //Should check if nick is only alphanumeric
    try{
        JSONObject result = new JSONObject();
        result.put("nick", nick);
        result.put("success", false);
        if(userService.isUserLoggedIn()){ 
            String uId = userService.getCurrentUser().getUserId(); //TODO: extract this to a variable
            String email = userService.getCurrentUser().getEmail();
            User user = new User(uId, nick, email);
            result.put("nick", nick);
            if(datastoreInterface.addUser(user)){
                result.put("success", true);
            }
        }
        String resultJSON = result.toString();
        response.getWriter().println(resultJSON);
    } catch (JSONException e){
        //...
    } 
  }
  
}