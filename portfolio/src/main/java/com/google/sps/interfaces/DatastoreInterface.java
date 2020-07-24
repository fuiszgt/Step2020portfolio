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
import static com.googlecode.objectify.ObjectifyService.ofy;

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
        return comments; 
    }

    
}