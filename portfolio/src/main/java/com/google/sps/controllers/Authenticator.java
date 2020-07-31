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

package com.google.sps.controllers;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.sps.interfaces.DatastoreInterface;
import com.google.sps.data.LoginInfo;
import com.google.sps.data.User;

public class Authenticator{
    private static final String RETURN_URL = "/index.html#comment-section";
    private UserService userService = UserServiceFactory.getUserService();
    private DatastoreInterface datastoreInterface = new DatastoreInterface();

    public String getUId(){
        return userService.getCurrentUser().getUserId();
    }

    public LoginInfo getLoginInfo(){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.isLoggedIn = userService.isUserLoggedIn();
          if(loginInfo.isLoggedIn){
                com.google.appengine.api.users.User apiUser = userService.getCurrentUser();
              loginInfo.logoutUrl= userService.createLogoutURL(RETURN_URL);
              loginInfo.nick = datastoreInterface.getUserNick(apiUser.getUserId());
              loginInfo.hasNick = loginInfo.nick != null;
          }else{
              loginInfo.loginUrl = userService.createLoginURL(RETURN_URL);
          }
        return loginInfo;
    }
}