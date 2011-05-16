package com.mongodb.example;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;


public class AddMessageActionBean implements ActionBean {
    private ActionBeanContext context;
    private String author;
    private String msg;
    
    public ActionBeanContext getContext() { return context; }
    public void setContext(ActionBeanContext context) { this.context = context; }

    public String getMsg() { return msg; }
    public void setMsg(String s) { this.msg = s; }

    public String getAuthor() { return author; }
    public void setAuthor(String s) { this.author = s; }

   
    @DefaultHandler
    public Resolution addMsg() {
    	
    	try {
    		Mongo m = MongoHolder.MONGOS.connect(new MongoURI("mongodb://localhost"));
    		
    		DB db = m.getDB("msgs");
    		DBCollection coll = db.getCollection("msgs");
    		coll.setWriteConcern(WriteConcern.SAFE);
    		
    		BasicDBObject doc = new BasicDBObject();

            doc.put("msg", this.getMsg());
            doc.put("author", this.getAuthor());
            coll.insert(doc);
            System.out.println("Added Message");
            
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
       
        return new ForwardResolution("/messages");
    }
    

    
}