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

package com.google.sps.data;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class User{
    @Id
    private String uId;
    @Index
    private String nick;
    private String email;

    public User(String uId, String nick, String email){
        this.uId = uId;
        this.nick = nick;
        this.email = email;
    }

    public User(){}
    
    public String getUId(){
        return this.uId;
    }
    public String getNick(){
        return this.nick;
    }
    public String getEmail(){
        return this.email;
    }
    
}