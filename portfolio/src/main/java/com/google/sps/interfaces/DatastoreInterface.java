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

import java.io.*;
import java.util.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.sps.data.Comment;

public class DatastoreInterface{
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public void addComment(Comment comment){
        //TODO: use Objectify
        Entity commentEntity = new Entity("Comment");
        commentEntity.setProperty("name", comment.getName());
        commentEntity.setProperty("content", comment.getContent());
        this.datastore.put(commentEntity);
    }
    
    public ArrayList<Comment> getComments(){
        //I was thinking of making this a private field, but I'm not sure
        //The problem is, that in that case it could end up containing depracted data (e.g. if a comment is added, and it is not refreshed.)
        ArrayList<Comment> comments = new ArrayList<Comment>();
        Query query = new Query("Comment");
        PreparedQuery results = this.datastore.prepare(query);
        for (Entity entity : results.asIterable()) {
            Long id = (Long) entity.getKey().getId();
            String name = (String) entity.getProperty("name");
            String content = (String) entity.getProperty("content");

            Comment comment = new Comment(id, name, content);
            comments.add(comment);
        }
        return comments; //It would be nice to pass by reference here, I do not know how to do that in Java
    }

    
}