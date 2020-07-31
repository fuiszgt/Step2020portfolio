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

package com.google.sps.interfaces;

import java.util.List;
import com.google.sps.data.Comment;
import com.google.sps.data.User;
import static com.googlecode.objectify.ObjectifyService.ofy;

public class DatastoreInterface{

    public boolean addUser(User user){
        if(!isNickTaken(user.getNick())){
            ofy().save().entity(user).now();
            return true;
        }
        return false;
    }

    public String getUserNick(String userId){
        User querriedUser = ofy().load().type(User.class).id(userId).now();
        if(querriedUser != null){
            return querriedUser.getNick();
        }else{
            return null;
        }
    }

    public boolean isNickTaken(String nick){
        User existing = ofy().load().type(User.class).filter("nick", nick).first().now();
        return (existing != null);
    }

    public void addComment(Comment comment){
        ofy().save().entity(comment).now();
    }

    public void deleteCommentById(Long id){
        ofy().delete().type(Comment.class).id(id).now();
    }
    
    public Comment getSingleComment(Long id){
        return ofy().load().type(Comment.class).id(id).now();
    }

    public List<Comment> getComments(){
        List<Comment> comments = ofy().load().type(Comment.class).order("date").list();
        return comments; 
    }
       
}