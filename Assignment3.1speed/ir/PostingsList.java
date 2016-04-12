/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   First version:  Johan Boye, 2010
 *   Second version: Johan Boye, 2012
 */  

package ir;

import java.util.LinkedList;
import java.io.Serializable;
import java.util.ListIterator;
import java.util.Iterator;

/**
 *   A list of postings for a given word.
 */
public class PostingsList implements Serializable {
    
    /** The postings list as a linked list. */
    public LinkedList<PostingsEntry> list = new LinkedList<PostingsEntry>();


    /**  Number of postings in this list  */
    public int size() {
	return list.size();
    }

    /**  Returns the ith posting */
    public PostingsEntry get( int i ) {
	return list.get( i );
    }
    
    public Iterator<PostingsEntry> iterator() {
		return list.iterator();
	}

    public ListIterator<PostingsEntry> getListEntires() {
        return list.listIterator(0);
    }
    
    public void addListEntry(int docid){
        //Adding one doc after another, if the docuent is not added already
        if ((list.size()==0)||(list.getLast().docID!=docid)){
            list.add(new PostingsEntry(docid));
        }
      
    }
    
    public void addscore(int docid, double score){
        list.add(new PostingsEntry(docid, score));
    }
    
    
    public void addListEntry(int docid, int offset){
        if ((list.size()==0)||(list.getLast().docID!=docid)){
            list.add(new PostingsEntry(docid));
            list.getLast().addoffset(offset);
        }
        else{
            //Check the last doc and add the location of the term
            //int i;
            //i = list.indexOf(docid);
            list.getLast().addoffset(offset);
            
        }
            
    }
    
    public void add( PostingsEntry p ) {
    	list.add(p);
    }
    
    
    public LinkedList<PostingsEntry> Returnlist(){
        return list;
    }
    
    //
    //  YOUR CODE HERE
    //
}
	

			   
