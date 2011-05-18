package com.mongodb.example;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;

@UrlBinding("/delete/{id}")
public class DeleteActionBean implements ActionBean {
    private ActionBeanContext context;
    private String id;
    
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getId() { return id; }
    public void setId(String s) { this.id = s; }

   
   
    @DefaultHandler
    public Resolution deleteMsg() {
    	
    	try {
    		Mongo m = MongoHolder.MONGOS.connect(new MongoURI("mongodb://localhost"));
    		
    		DB db = m.getDB("msgsdb");
    		DBCollection coll = db.getCollection("msgs");
    		coll.setWriteConcern(WriteConcern.SAFE);
    		System.out.println("id:"+ this.getId());
    		BasicDBObject doc = new BasicDBObject("_id", new ObjectId(this.getId()));
            coll.remove(doc);
            System.out.println("Deleted Message");
            
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
       
        return new RedirectResolution("/messages");
    }
    
}
    