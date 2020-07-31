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
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Load;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;
import java.util.Date;

@Entity
public class Comment{
    @Id
    private Long id;
    private transient String uId;
    @Ignore
    private String nick;
    private String content;
    @Index
    private Date date;
    @Load 
    private Ref<User> user;

    public Comment(String uId, String content){
        this.uId = uId;
        this.content = content;
        this.date = new Date();
        Key<User> userKey = Key.create(User.class, uId);
        this.user = Ref.create(userKey);
        this.setNick();
    }

    public Comment(){}

    public Long getId(){
        return this.id;
    }

    public String getContent(){
        return this.content;
    }

    @OnLoad
    public void setNick(){ 
        if(this.user.get() != null){
            this.nick = this.user.get().getNick();
        }else{
            this.nick = null;
        }
    }
    
}