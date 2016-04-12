/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir;



/**
 *
 * @author sugandh
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;


/**
 *   Implements an inverted index as a Hashtable from words to PostingsLists.
 */
public class Bigram implements Index {

    /** The index as a hashtable. */
    private HashMap<String,PostingsList> biindex = new HashMap<String,PostingsList>();
    public HashMap<Integer,Integer> numberDocs = new HashMap<Integer,Integer>();

    private String lastTerm;
    private int lastDocID;

	public Bigram() {
		lastTerm = "";
		lastDocID = -1;
	}


    /**
     *  Inserts this token in the index.
     */
    public void insert( String token, int docID, int offset ) {
	//
	//  YOUR CODE HERE
	//

        if(docID != lastDocID) {
			lastDocID = docID;
			lastTerm = token;
		}

		else {
			//System.err.println("inserting....");
			String biWord = lastTerm +" " + token;
			lastTerm = token;

			if(numberDocs.containsKey(docID)) {
				Integer n = numberDocs.get(docID);
				numberDocs.put(docID,n+1);
			}
			else
				numberDocs.put(docID,1);
			
                        PostingsList list = biindex.get(biWord);
			if(list==null) {
				list = new PostingsList();
				//System.err.println("inserting...."+biWord);
				biindex.put(biWord,list);
			}
			list.addListEntry(docID,offset);

		}
	}


    /**
     *  Returns all the words in the index.
     */
    public Iterator<String> getDictionary() {
	//
	//  REPLACE THE STATEMENT BELOW WITH YOUR CODE
	//
	//return null;
         return biindex.keySet().iterator();
    }


    /**
     *  Returns the postings for a specific term, or null
     *  if the term is not in the index.
     */
    public PostingsList getPostings( String token ) {
	//
	//  REPLACE THE STATEMENT BELOW WITH YOUR CODE
	//
	//return null;
        //if (biindex.containsKey(token)){
            return biindex.get(token);
        //}
        //return null;
    }


    /**
     *  Searches the index for postings matching the query.
     */

  public PostingsList search( Query query, int queryType, int rankingType, int structureType ) {
	//
	//  REPLACE THE STATEMENT BELOW WITH YOUR CODE
	//
	//return null;
        //LinkedList<String> assorted = new LinkedList<>();
        //  assorted=Genascterms(query);
     //   switch (queryType) {
     // case 0:
     LinkedList<String> assorted = new LinkedList<>();
          //assorted=Genascterms(query);
          int mulfortfidf = 1;
          double time = 0.0;
          PostingsList result = null;
            String oldTerm = null;
     if(queryType==Index.RANKED_QUERY && rankingType == Index.TF_IDF)
            {       
            
            return cosSim(query);}
                
     else{
         return null;
     }
     }





//private PostingsList intersectSearch(Query q) {




//****NEW






    /**
     *  No need for cleanup in a HashedIndex.
     */
    public void cleanup() {
    }
    
    
     
    
    public PostingsList cosSim(Query q) {
		System.err.println("inside bi search "+biindex.size());
	HashMap<Integer, Double> dScores = new HashMap<Integer, Double>();
	final int N = docIDs.size();
	LinkedList<String> termstmp = q.copy().terms;
        LinkedList<String> terms = new LinkedList<String>();
        String biqwords;
        String lastterm = termstmp.removeFirst();
        
        while(!termstmp.isEmpty()){
            biqwords=lastterm;
            lastterm=termstmp.removeFirst();
            biqwords+=" "+lastterm;
            terms.add(biqwords);
            biqwords="";
        }
        
        ArrayList<PostingsList> postl = new ArrayList<>();
        
        ArrayList<Pair> highidfterms = new ArrayList<Pair>();
        HashMap<String, Integer> termsforidf = new HashMap<String, Integer>();
        
    //termarr empty
   
        for(String t:terms){
			System.err.println(t);
			System.err.println(getPostings(t));
            postl.add(getPostings(t));
        }
            
      
        ArrayList<PostingsList> termarr = new ArrayList<>();
 
    
        for(String t:terms){
        termarr.add(getPostings(t));
        }
        //weights(docLengths.size(),termarr);
        if(terms.size()==0){
        return new PostingsList();
        }
    
        HashMap<String, Integer> queryTerms = new HashMap<String, Integer>();
        //integer for number of times the query terms appear in the query
        for ( int i = 0; i < terms.size(); i++ ) {
            String term = terms.get(i);
            if (queryTerms.containsKey(term)){
            //tf
            queryTerms.put(term, queryTerms.get(term)+1);
            } else {
            queryTerms.put(term, 1);
            }
        }
      
        double queryNorm = 0;
    
        queryNorm = terms.size();
        int tf;  
        
        boolean flag = false;
        if(!flag){
	for (String term : terms) {
            PostingsList pl = getPostings(term);
            if (pl == null) continue;
            tf = queryTerms.get(term);
            final int df = pl.size();
            final double idf = Math.log10(((double) N) / df);
            
            double tfidfq = tf*idf/queryNorm;

            double wtq = tfidfq;

            Iterator<PostingsEntry> peIt = pl.iterator();
            while (peIt.hasNext()) {
                    PostingsEntry pe = peIt.next();
                    int tfd = pe.getoffsets().size();
                    double tfidfdoc = tf*idf;
                    //final double wtd = tf * idf;
                    double wtd = tfidfdoc;
                    if(pe.docID==9178){
                        System.err.println("term "+term+" tf "+tfd+" tfidf "+tfidfdoc);
                    }

                    if (!dScores.containsKey(pe.docID)) dScores.put(pe.docID, wtd*wtq);
                    double oldScore = dScores.get(pe.docID);
                    double newScore = wtq * wtd + oldScore;
                    dScores.put(pe.docID, newScore);
			}
		}
        }
        else{
            for (String term : terms) {
            PostingsList pl = getPostings(term);
            if (pl == null) continue;
            tf = queryTerms.get(term);
            final int df = pl.size();
            final double idf = Math.log10(((double) N) / df);
            
            highidfterms.add(new Pair(term, idf));
            }
            Collections.sort(highidfterms);
            
            for(int i =0;i<Math.min(50, highidfterms.size());i++){
            PostingsList pl = getPostings(highidfterms.get(i).term);
            if (pl == null) continue;
            tf = queryTerms.get(highidfterms.get(i).term);
            final int df = pl.size();
            final double idf = Math.log10(((double) N) / df);   
                
            
            double tfidfq = tf*idf/queryNorm;

            double wtq = tfidfq;

            Iterator<PostingsEntry> peIt = pl.iterator();
            while (peIt.hasNext()) {
                    PostingsEntry pe = peIt.next();
                    int tfd = pe.getoffsets().size();
                    double tfidfdoc = tf*idf;
                    //final double wtd = tf * idf;
                    double wtd = tfidfdoc;
                    if(pe.docID==9178){
                        System.err.println("term "+highidfterms.get(i).term+" tf "+tfd+" idf "+idf);
                    }

                    if (!dScores.containsKey(pe.docID)) dScores.put(pe.docID, wtd*wtq);
                    double oldScore = dScores.get(pe.docID);
                    double newScore = wtq * wtd + oldScore;
                    dScores.put(pe.docID, newScore);
			}
		}
            
        }
		
            PostingsList result = new PostingsList();
            for (int docID : dScores.keySet()) {

                    double cosSim = dScores.get(docID) / docLengths.get(String.valueOf(docID));
                    PostingsEntry pe = new PostingsEntry(docID, cosSim);
                    result.add(pe);
            }

            Collections.sort(result.list);
            return result;
    }
    
    private class Pair implements Comparable<Pair>{
        private String term;
        private double idf;

        public Pair(String key, double value){
            term = key;
            idf = value;
        }

        public double getidf(){
            return idf;
        }
		
	final int ascending = 1;
        
        public int compareTo(Pair o){
            if (this.idf == o.getidf()) return 0;
            if (this.idf < o.getidf()) return ascending;
            return -ascending;
        }
        }
    
}
