package com.mongodb.example;

import java.util.Map;
import java.util.Vector;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;

public class MessageListActionBean implements ActionBean {
	
	private ActionBeanContext context;
	
	private Vector<Map<String, String>> msgs = new Vector<Map<String, String>>();
	
	public Vector<Map<String, String>> getMsgs() {return msgs;}
	public void setMsgs(Vector<Map<String, String>> v){ msgs = v;}

	@Override
	public ActionBeanContext getContext() {return context;}

	@Override
	public void setContext(ActionBeanContext context) {this.context = context;	}
	
	@DefaultHandler
    public Resolution addition() {
        getMsgList();
        return new ForwardResolution("/msglist.jsp");
    }
	
	 public void getMsgList() {
			try {
	    		
				Mongo m = MongoHolder.MONGOS.connect(new MongoURI("mongodb://localhost"));
	    		DB db = m.getDB("msgs");
	    		DBCollection coll = db.getCollection("msgs");
	    		DBCursor cursor = coll.find();
	    		System.out.println("Cursor count:"+cursor.count());
	    		System.out.println("vector size:"+msgs.size());
	    		
	    		 while(cursor.hasNext()){
	    		     DBObject obj = cursor.next();
	    		     msgs.add(obj.toMap());
	    		 }
	    		 System.out.println("vector size:"+msgs.size());
	    		 
	    		
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    		return;
	    	}
	    }
}