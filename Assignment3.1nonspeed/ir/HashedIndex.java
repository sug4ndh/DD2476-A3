/*
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 *
 *   First version:  Johan Boye, 2010
 *   Second version: Johan Boye, 2012
 *   Additions: Hedvig Kjellström, 2012-14
 */


package ir;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


/**
 *   Implements an inverted index as a Hashtable from words to PostingsLists.
 */
public class HashedIndex implements Index {

    /** The index as a hashtable. */
    private HashMap<String,PostingsList> index = new HashMap<String,PostingsList>();



    /**
     *  Inserts this token in the index.
     */
    public void insert( String token, int docID, int offset ) {
	//
	//  YOUR CODE HERE
	//

        if (index.containsKey(token)==false) {
    		index.put(token, new PostingsList());
    		//System.out.println("New token********"+token);
    	}
    	index.get(token).addListEntry(docID, offset);
    	//System.out.println("DocID********"+docID+"OFFset**********"+offset);
    }


    /**
     *  Returns all the words in the index.
     */
    public Iterator<String> getDictionary() {
	//
	//  REPLACE THE STATEMENT BELOW WITH YOUR CODE
	//
	//return null;
         return index.keySet().iterator();
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
        //if (index.containsKey(token)){
            return index.get(token);
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
     //LinkedList<String> assorted = new LinkedList<>();
          //assorted=Genascterms(query);
          int mulfortfidfonlt =1;
     if(queryType==Index.INTERSECTION_QUERY)
		{
        return intersectSearch(query);}
     else if(queryType==Index.RANKED_QUERY && rankingType == Index.TF_IDF)
            {       
				//return ranked(query);}
           // return ranked2(query,mulfortfidfonlt);}
            return cosineSim(query);}
     else if(queryType==Index.RANKED_QUERY && rankingType == Index.PAGERANK){
         return rankedpagerank(query);
     }
    else if(queryType==Index.RANKED_QUERY && rankingType == Index.COMBINATION){
         return rankedcombination(query);}
     else if(queryType==Index.PHRASE_QUERY)
     {
      return phraseSearch(query);}
    else{
		return intersectSearch(query);}
     }
  
private PostingsList cosineSimilarity(Query q) {
		HashMap<Integer, Double> docScores = new HashMap<Integer, Double>();
		final int N = docIDs.size();
		LinkedList<String> terms = q.copy().terms;
    ArrayList<PostingsList> postl = new ArrayList<>();
    //termarr empty
   
    for(String t:q.terms){
        postl.add(getPostings(t));
    }
                
		for (String term : q.terms) {
			PostingsList pl = getPostings(term);
			if (pl == null) continue;

			final int dft = pl.size();
			final double idft = Math.log10(((double) N) / dft);
			double wtq = 1 * idft * q.weights.get(term);

			Iterator<PostingsEntry> peIt = pl.iterator();
			while (peIt.hasNext()) {
				PostingsEntry pe = peIt.next();
				final int tf = pe.getoffsets().size();
				final double wtd = tf * idft;

				if (!docScores.containsKey(pe.docID)) docScores.put(pe.docID, 0.0);
				double oldScore = docScores.get(pe.docID);
				double newScore = wtq * wtd + oldScore;
				docScores.put(pe.docID, newScore);
			}
		}
		
		PostingsList result = new PostingsList();
		for (int docID : docScores.keySet()) {
			// NOTE: approximation for euclidean length here
			// should be dividing by the product of the 2-norms of the query and document
			// TODO: maybe pre-calculate that somewhere?
			double cosSim = docScores.get(docID) / docLengths.get("" + docID); 
			PostingsEntry pe = new PostingsEntry(docID, cosSim);
			result.add(pe);
		}

		Collections.sort(result.list);
		return result;
	}
  
private PostingsList ranked(Query q){
    LinkedList<String> terms = q.copy().terms;
    ArrayList<PostingsList> termarr = new ArrayList<>();
    int N = docIDs.size();
    System.err.println("N "+N);
    PostingsList retlist = new PostingsList();
   
    
    double Score[] = new double[N];
    double Length[] = new double[N];
    
    //Creating query-terms array list
    for(String t:q.terms){
        termarr.add(getPostings(t));
    }
    
    if(termarr.size()==0){
        return new PostingsList();
    }
    
    for(int i=0;i<N;i++){
        Score[i]=0.0;
    }
        /*PostingsList pl = new PostingsList();
        for (Iterator<PostingsList> it = termarr.iterator(); it.hasNext();) {
            pl = it.next();*/
    for(PostingsList pl:termarr){
            //System.err.println("posting list for query"+pl.Returnlist());
            
            for(PostingsEntry pe:pl.Returnlist()){
                Length[pe.docID] += docLengths.get(String.valueOf(pe.docID));}
    }
    double norm=0.0;
    for(PostingsList pl:termarr){
            //System.err.println("posting list for query"+pl.Returnlist());
            double df = pl.size();
            double idf = Math.log10(N/df);
            System.err.println("df "+df+" idf "+idf);
            for(PostingsEntry pe:pl.Returnlist()){
                double tf =  pe.getoffsets().size();
                //tf = Math.log10(1.0+tf);
                //System.err.println("tf "+tf);
                double tfidf = (tf*idf);
                //System.err.println("tfidf "+tfidf);
                norm += tf*tf;
                Score[pe.docID] += tfidf;
                //double tfidf = (tf*idf)/docLengths.get(String.valueOf(pe.docID));
                
                //Length.add(pe.docID, Math.sqrt(docLengths.get(pe.docID)*docLengths.get(pe.docID)));
                
                //score.add(pe.docID, tfidf);
                //Score[pe.docID] += tfidf;
                //pe.score += tfidf;
                //Length[pe.docID] += Math.pow(pe.getoffsets().size(), 2.0);
            }   
            
        }
        norm = Math.sqrt(norm);
    for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
           
				 //System.err.println("score bb "+Score[pe.docID]);
                Score[pe.docID]=Score[pe.docID]/Length[pe.docID];
				//Score[pe.docID]=Score[pe.docID]/norm;
				//Score[pe.docID]=Score[pe.docID]/Math.sqrt(Length[pe.docID]);
            pe.score+=Score[pe.docID];
            System.err.println("score 1"+pe.score);
        }
    }
    
    HashMap<Integer, PostingsEntry> entries = new HashMap<Integer, PostingsEntry>();
        for (Iterator<String> t = terms.iterator(); t.hasNext(); ){
        String term = t.next();
        for (ListIterator<PostingsEntry> it = getPostings(term).getListEntires();
             it.hasNext();) {
          PostingsEntry e = it.next();
          if (!entries.containsKey(e.docID)) {
            entries.put(e.docID, new PostingsEntry(e.docID));
          }
          entries.get(e.docID).score  = e.score;
            System.err.println("score "+entries.get(e.docID).score);
        }
      }
      PostingsList tmp = new PostingsList();
      tmp.list.addAll(entries.values());
      Collections.sort(tmp.list);
      
      for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            pe.score = 0;
            
        }
    }
      return tmp;
    
    
    
        
}
  
public void weights(int N, ArrayList<PostingsList> termarr){
    double[] Length = new double[N];
    for (Iterator<PostingsList> it = termarr.iterator(); it.hasNext();) {
      PostingsList p = it.next();
      //System.out.println("p "+p.Returnlist());
      double df = p.size();
      double idf = Math.log10(N/df);
      //System.out.println("p idf "+idf);
      for (ListIterator<PostingsEntry> it2 = p.getListEntires(); it2.hasNext();) {
        PostingsEntry e = it2.next();
        Length[e.docID] = e.getoffsets().size()*e.getoffsets().size()*idf*idf;
      }
    }
    for (int i = 0; i < N; i++) {
      if (Length[i] > 0) {
        Length[i] = Math.sqrt(Length[i]);
      }
    }
    for (Iterator<PostingsList> it = termarr.iterator(); it.hasNext();) {
      PostingsList p = it.next();
      double df=p.size();
      double idf = Math.log10(N/df);
      
      for (ListIterator<PostingsEntry> it2 = p.getListEntires(); it2.hasNext();) {
        PostingsEntry e = it2.next();
        double tf = Math.log10(1.0 +e.getoffsets().size());
        e.score = (tf*idf)/docLengths.get(String.valueOf(e.docID));
      }
    }
}    

private PostingsList ranked2(Query q, int multiplier){
    //weights(docLengths.size());
    LinkedList<String> terms = q.copy().terms;
    ArrayList<PostingsList> termarr = new ArrayList<>();
    for(String t:q.terms){
        termarr.add(getPostings(t));
    }
    weights(docLengths.size(),termarr);
    if(terms.size()==0){
        return new PostingsList();
    }
    
    HashMap<String, Integer> queryTerms = new HashMap<String, Integer>();
      for ( int i = 0; i < terms.size(); i++ ) {
        String term = q.terms.get(i);
        if (queryTerms.containsKey(term)){
          queryTerms.put(term, queryTerms.get(term)+1);
        } else {
          queryTerms.put(term, 1);
        }
      }
      double queryNorm = 0;
      for (Iterator<String> t = queryTerms.keySet().iterator(); t.hasNext(); ){
        String term = t.next();
        queryNorm += Math.pow(queryTerms.get(term),2.0);
      }
      queryNorm = Math.sqrt(queryNorm);
      
      HashMap<Integer, PostingsEntry> entries = new HashMap<Integer, PostingsEntry>();
      for (Iterator<String> t = queryTerms.keySet().iterator(); t.hasNext(); ){
        String term = t.next();
        for (ListIterator<PostingsEntry> it = getPostings(term).getListEntires();
             it.hasNext();) {
          PostingsEntry e = it.next();
          if (!entries.containsKey(e.docID)) {
            entries.put(e.docID, new PostingsEntry(e.docID));
          }
            entries.get(e.docID).score += e.score*multiplier*(queryTerms.get(term)/queryNorm);
            System.err.println("score "+entries.get(e.docID).score);
        }
      }
      PostingsList tmp = new PostingsList();
      tmp.list.addAll(entries.values());
      Collections.sort(tmp.list);
      return tmp;
    
    
    //return null;
}


//private PostingsList intersectSearch(Query q) {
private LinkedList<String> Genascterms(Query q) {
    LinkedList<String> terms = q.copy().terms;
    int finalsize = terms.size();
    System.out.println("Searching for " + terms.peekFirst() + " among " + index.size());
    LinkedList<String> ascterms = new LinkedList<String>();
    LinkedList<String> temp = new LinkedList<String>();
    //temp.add("lalal");
    //System.out.println("size "+temp.size());

    while(ascterms.size()!=finalsize){
		System.out.println("enter asc while\n");
        String minterm = terms.peekFirst();
        PostingsList l1 = getPostings(terms.removeFirst());
        System.out.println("minterm " +minterm);
        String tempterm;
	  //PostingsList l1 = getPostings(terms.removeFirst());

        int min = l1.size();
        while (!terms.isEmpty()) {
			System.out.println("enter TERMS while\n");
      //  System.out.println("Searching for " + terms.peekFirst() + " among " + index.size());
     // list = intersect(list.getListEntires(), getPostings(terms.removeFirst()).getListEntires());
            tempterm = terms.peekFirst();
            PostingsList temppl = getPostings(terms.removeFirst());
            if(min > temppl.size()){
                temp.add(minterm);
                minterm = tempterm;
                min = temppl.size();
            }
            else{
                temp.add(tempterm);
            }
        }
        ascterms.add(minterm);

        System.out.println("size "+temp.size());
        if (!temp.isEmpty()) {
			minterm = temp.peekFirst();
			PostingsList l2 = getPostings(temp.removeFirst());
			//l1 = getPostings(temp.removeFirst());
			min = l2.size();
			while (!temp.isEmpty()) {
				tempterm = temp.peekFirst();
				PostingsList temppl2 = getPostings(temp.removeFirst());
				if(min > temppl2.size()){
					terms.add(minterm);
					minterm = tempterm;
					min = temppl2.size();
				}
				else{
					terms.add(tempterm);
				}
			}
			ascterms.add(minterm);
		}
	}
        /*PostingsList ll1 = getPostings(ascterms.removeFirst());
	if(ascterms.size()>1){

            PostingsList ll2 = getPostings(ascterms.removeFirst());
            if(ll1.size()>ll2.size()){
             ll1 = intersect(ll1, ll2 );
            }
            else{
                ll1 = intersect(ll2,ll1);
            }

    //System.out.println("min term "+minterm);

 }*/
        return ascterms;
}

private PostingsList intersectSearch(Query q) {
	//for (int i=0;i<=terms.size();i++){
	//	System.out.println(terms.get(i));
	//
	LinkedList<String> terms = q.copy().terms;

    PostingsList list = getPostings(terms.removeFirst());

    while (!terms.isEmpty()) {

      list = intersect(list, getPostings(terms.removeFirst()));
    }
    return list;
  }

//****NEW
private PostingsList phraseSearch(Query q) {
	//System.out.println("here 1");
	System.out.println("size "+index.size());
	Iterator<String> d1 = getDictionary();
	Iterator<String> d1copy = getDictionary();

  /* while(d1.hasNext()){
	  // int ik=0;
      //System.out.println(" "+d1.toString());
      // System.out.println(" "+d1.next());
       PostingsList temp= new PostingsList();
       temp = getPostings(d1.next());
       for(int xtemp=0;xtemp!=temp.size();xtemp++){
				System.out.print(" "+d1copy.next()+"   offsets are \t"+temp.get(xtemp).getoffsets());
				System.out.println("");
			}

   }*/

	//System.out.println("q zize"+q.size());
    LinkedList<String> terms = q.copy().terms;
    PostingsList list = getPostings(terms.removeFirst());
	System.out.println("terms zize"+terms.size());
    while (!terms.isEmpty()) {
		System.out.println("calling func phrasesear");
      list = phrasequery(list, getPostings(terms.removeFirst()));
    }
    return list;
  }


private PostingsList intersect(PostingsList p1, PostingsList p2) {
    PostingsList answer = new PostingsList();

     int i=0;
     int j=0;
     System.out.println("p1 zize"+p1.size());
     System.out.println("p1 zize"+p2.size());
     while(i<p1.size() && j<p2.size()){
         if((p1.get(i).docID) == (p2.get(j).docID)){
             answer.addListEntry(p1.get(i).docID);
             System.out.println("doc id"+p1.get(i).docID);
             i++;
             j++;
         }
         else if(p1.get(i).docID<p2.get(j).docID){
             i++;
         }
         else{
             j++;
         }
     }
     return answer;
 }

private PostingsList phrasequery(PostingsList p1, PostingsList p2) {
	  PostingsList answer = new PostingsList();
    System.out.println("inside pquery");
    int i,j,m,n;
    i=j=0;
    while(i<p1.size() && j<p2.size()){
	/*	for(;i<p1.size();i++){
				System.out.print("docs 1 are \t"+p1.get(i).docID);
				System.out.println("");
			}
			for(;j<p2.size();j++){
				System.out.print("docs  2 are \t"+p2.get(j).docID);
			}*/
			//i=0;j=0;
        if((p1.get(i).docID) == (p2.get(j).docID)){
			System.out.println("found doc "+p1.get(i).docID);

           // PostingsList l = new PostingsList();

            LinkedList<Integer> pp1 = p1.get(i).getoffsets();
            LinkedList<Integer> pp2 = p2.get(j).getoffsets();
           // System.out.println("size 1 "+pp1.size());
           // System.out.println("size 2 "+pp2.size());
            m=n=0;
            int x =0;
            int y=0;
            /*for(x=0;x<pp1.size();x++){
				System.out.print("offsets 1 are \t"+pp1.get(x));
			}
			for(y=0;y<pp2.size();y++){
				System.out.print("offsets  2 are \t"+pp2.get(y));
			}*/
            while(m<pp1.size() && n<pp2.size()){
					System.out.println("iinside loop");
					System.out.println("pp2 n "+pp2.get(n));
					System.out.println("pp1 m "+pp1.get(m));
					if(pp2.get(n)-pp1.get(m)==1){
						System.out.println("iinside if");
						answer.add(p2.get(j));
						m++;
						n++;
					}
					else if ((pp2.get(n)) > pp1.get(m)) {
							m++;
						}
						else {
							n++;
						}

				}
                i++;
                j++;

            }

            else if (p1.get(i).docID < p2.get(j).docID) {
               // System.out.println(".docid"+p1.get(i).docID);
               // System.out.println("Return docid"+p1.get(i).Returndocid());
                i++;
            }
            else {
                j++;
            }
        }
        return answer;
        }

private PostingsList rankedpagerank(Query q){
       LinkedList<String> terms = q.copy().terms;
    ArrayList<PostingsList> termarr = new ArrayList<>();
    int N = docIDs.size();
    
    PostingsList retlist = new PostingsList();
   
    
    double Score[] = new double[N];
    double Length[] = new double[N];
    
    //Creating query-terms array list
    for(String t:q.terms){
        termarr.add(getPostings(t));
    }
    
    if(termarr.size()==0){
        return new PostingsList();
    }
    
    for(int i=0;i<N;i++){
        Score[i]=0.0;
    }
    for(PostingsList pl:termarr){
            //System.err.println("posting list for query"+pl.Returnlist());
            
            for(PostingsEntry pe:pl.Returnlist()){
                Length[pe.docID] = docLengths.get(String.valueOf(pe.docID));}
    }
    double norm=0.0;
    Scanner sc1 = null;
        try {
            sc1 = new Scanner(new File("/home/sugandh/Downloads/Assignment2/ir/pagerank/SortedNames.txt"));
        }   catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
    Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("/home/sugandh/Downloads/Assignment2/ir/pagerank/doclist.txt"));
        }   catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
    HashMap<String, Double> prscore = new HashMap<>();
    while (sc1.hasNextLine()) {
                String[] words = sc1.nextLine().split("\\s");
                prscore.put(words[0], Double.parseDouble(words[1]));
    }
    
    for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            for(String key: prscore.keySet()){    
               // System.err.println(docIDs.get(String.valueOf(pe.docID))+"\t"+key);
                if (docIDs.get(String.valueOf(pe.docID)).equals(key)){
					 //System.err.println("Found******************************************************************");
                    Score[pe.docID] = prscore.get(key);
                }
                
                
            }   
            
        }
    }
    
    for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            pe.score = Score[pe.docID];
            System.err.println("score 1 "+pe.score);
        }
    }
    HashMap<Integer, PostingsEntry> entries = new HashMap<Integer, PostingsEntry>();
        for (Iterator<String> t = terms.iterator(); t.hasNext(); ){
        String term = t.next();
        for (ListIterator<PostingsEntry> it = getPostings(term).getListEntires();
             it.hasNext();) {
          PostingsEntry e = it.next();
          if (!entries.containsKey(e.docID)) {
            entries.put(e.docID, new PostingsEntry(e.docID));
          }
            entries.get(e.docID).score = e.score;
            System.err.println("score "+entries.get(e.docID).score);
        }
      }
      PostingsList tmp = new PostingsList();
      tmp.list.addAll(entries.values());
      Collections.sort(tmp.list);
      return tmp;
}

private PostingsList rankedcombination(Query q){
   int mulforcombtfidf = 10;
   int mulforpr = 100;
   ranked2(q,mulforcombtfidf);
   LinkedList<String> terms = q.copy().terms;
    ArrayList<PostingsList> termarr = new ArrayList<>();
    int N = docIDs.size();
    
    PostingsList retlist = new PostingsList();
   
    
    double Score[] = new double[N];
    double Length[] = new double[N];
    
    //Creating query-terms array list
    for(String t:q.terms){
        termarr.add(getPostings(t));
    }
    
    if(termarr.size()==0){
        return new PostingsList();
    }
    
    for(int i=0;i<N;i++){
        Score[i]=0.0;
    }
    for(PostingsList pl:termarr){
            //System.err.println("posting list for query"+pl.Returnlist());
            
            for(PostingsEntry pe:pl.Returnlist()){
                Length[pe.docID] = docLengths.get(String.valueOf(pe.docID));}
    }
    double norm=0.0;
    Scanner sc1 = null;
        try {
            sc1 = new Scanner(new File("/home/sugandh/Downloads/Assignment2/ir/pagerank/SortedNames.txt"));
        }   catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
    Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File("/home/sugandh/Downloads/Assignment2/ir/pagerank/doclist.txt"));
        }   catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
    HashMap<String, Double> prscore = new HashMap<>();
    while (sc1.hasNextLine()) {
                String[] words = sc1.nextLine().split("\\s");
                prscore.put(words[0], Double.parseDouble(words[1]));
    }
    
    for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            for(String key: prscore.keySet()){    
               // System.err.println(docIDs.get(String.valueOf(pe.docID))+"\t"+key);
                if (docIDs.get(String.valueOf(pe.docID)).equals(key)){
					 //System.err.println("Found******************************************************************");
                    Score[pe.docID] = prscore.get(key);
                }
                
                
            }   
            
        }
    }
    
    for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            pe.score += Score[pe.docID]*mulforpr;
            System.err.println("score 1 "+pe.score);
        }
    }
    HashMap<Integer, PostingsEntry> entries = new HashMap<Integer, PostingsEntry>();
        for (Iterator<String> t = terms.iterator(); t.hasNext(); ){
        String term = t.next();
        for (ListIterator<PostingsEntry> it = getPostings(term).getListEntires();
             it.hasNext();) {
          PostingsEntry e = it.next();
          if (!entries.containsKey(e.docID)) {
            entries.put(e.docID, new PostingsEntry(e.docID));
          }
            entries.get(e.docID).score = e.score/(mulforcombtfidf+mulforpr);
            System.err.println("score "+entries.get(e.docID).score);
        }
      }
      PostingsList tmp = new PostingsList();
      tmp.list.addAll(entries.values());
      
      for(PostingsList pl:termarr){
        for(PostingsEntry pe:pl.Returnlist()){
            pe.score = 0;
            
        }
    }
    
      Collections.sort(tmp.list);
      return tmp;
    
   
}
    /**
     *  No need for cleanup in a HashedIndex.
     */
    public void cleanup() {
    }
    
        private PostingsList cosineSim(Query q) {
			System.err.println("inside cosinesim");
	double start = System.currentTimeMillis();

	final int N = docIDs.size();
	LinkedList<String> terms = q.copy().terms;
        ArrayList<PostingsList> postl = new ArrayList<>();
        
        HashMap<Integer, Double> doc_w = new HashMap<Integer, Double>();
    
        //termarr empty
   
        for(String t:q.terms){
            postl.add(getPostings(t));
        }
            
      
        ArrayList<PostingsList> termarr = new ArrayList<>();


        for(String t:q.terms){
            termarr.add(getPostings(t));
        }
        //weights(docLengths.size(),termarr);
        if(terms.size()==0){
            return new PostingsList();
        }

        HashMap<String, Integer> queryTerms = new HashMap<String, Integer>();
        //integer for number of times the query terms appear in the query
        for ( int i = 0; i < terms.size(); i++ ) {
            String term = q.terms.get(i);
            if (queryTerms.containsKey(term)){
                //tf
              queryTerms.put(term, queryTerms.get(term)+1);
            } else {
              queryTerms.put(term, 1);
            }
        }

        double queryNorm = 0;

        queryNorm = Math.pow(terms.size(),0.6);
        int tf;  

        int counter=0;
        for (String term : q.terms) {
            counter++;
            System.err.println("term " + term);
            PostingsList pl = getPostings(term);
            if (pl == null) continue;
            tf = queryTerms.get(term);
            final int df = pl.size();
            final double idf = Math.log10(((double) N) / df);
            double tfidfq = tf*idf/queryNorm;

            double wt_q = tfidfq;

            Iterator<PostingsEntry> peIt = pl.iterator();
            while (peIt.hasNext()) {
                PostingsEntry pe = peIt.next();
                int tfd = pe.getoffsets().size();
                double tfidfdoc = tfd*idf;
                //final double wtd = tf * idf;
                double wt_doc = tfidfdoc;
               // System.err.println("term "+pe.docID);
                    //if(pe.docID==9871){
                   // System.err.println("term "+term+" tf "+tfd+" tfidf "+tfidfdoc+" idf "+idf);
                //}

                if (!doc_w.containsKey(pe.docID)) doc_w.put(pe.docID, wt_doc*wt_q);
                double prevscore = doc_w.get(pe.docID);
                double newscore = wt_q * wt_doc+prevscore;
                doc_w.put(pe.docID, newscore);
            }
        }

                PostingsList result = new PostingsList();
                
                for (int docID : doc_w.keySet()) {
                        double cosinesim = doc_w.get(docID) / Math.pow(docLengths.get(String.valueOf(docID)),0.6);
                        PostingsEntry pe = new PostingsEntry(docID, cosinesim);
                        result.add(pe);
                }
                System.err.println("Length of query vector now " + counter);
                Collections.sort(result.list);
                double end = System.currentTimeMillis();

                System.err.println("time is "+(end-start));
                return result;
	}
	
	/*private PostingsList cosineSim(Query q) {
	final int N = docIDs.size();
	
	LinkedList<String> terms = q.copy().terms;
        ArrayList<PostingsList> postl = new ArrayList<>();
        
        HashMap<Integer, Double> doc_w = new HashMap<Integer, Double>();
        ArrayList<Pair> highidfterms = new ArrayList<Pair>();
        HashMap<String, Integer> termsforidf = new HashMap<String, Integer>();
        
    //termarr empty
   
        for(String t:q.terms){
            postl.add(getPostings(t));
        }
            
      
        ArrayList<PostingsList> termarr = new ArrayList<>();
 
    
        for(String t:q.terms){
        termarr.add(getPostings(t));
        }
        //weights(docLengths.size(),termarr);
        if(terms.size()==0){
        return new PostingsList();
        }
    
        HashMap<String, Integer> queryTerms = new HashMap<String, Integer>();
        //integer for number of times the query terms appear in the query
        for ( int i = 0; i < terms.size(); i++ ) {
            String term = q.terms.get(i);
            if (queryTerms.containsKey(term)){
            //tf
            queryTerms.put(term, queryTerms.get(term)+1);
            } else {
            queryTerms.put(term, 1);
            }
        }
      
        double queryNorm = 0;
    
        queryNorm =  Math.pow(terms.size(),0.6);;
        int tf;  
        
        for (String term : q.terms) {
            PostingsList pl = getPostings(term);
            if (pl == null) continue;
            tf = queryTerms.get(term);
            final int df = pl.size();
            final double idf = Math.log10(((double) N) / df);
            
            highidfterms.add(new Pair(term, idf));
            }
            Collections.sort(highidfterms);
            
            for(int i =0;i<Math.min(50, highidfterms.size());i++){
		System.err.println("term " + highidfterms.get(i).term);
                PostingsList pl = getPostings(highidfterms.get(i).term);
                if (pl == null) continue;
                tf = queryTerms.get(highidfterms.get(i).term);
                final int df = pl.size();
                final double idf = Math.log10(((double) N) / df);   


                double tfidfq = tf*idf/queryNorm;

                double wt_q = tfidfq;

                Iterator<PostingsEntry> peIt = pl.iterator();
                while (peIt.hasNext()) {
                    PostingsEntry pe = peIt.next();
                    int tfd = pe.getoffsets().size();
                    double tfidfdoc = tfd*idf;
                    //final double wtd = tf * idf;
                    double wt_doc = tfidfdoc;
                    //if(pe.docID==9178){
                     //   System.err.println("term "+highidfterms.get(i).term+" tf "+tfd+" idf "+idf);
                    //}

                    if (!doc_w.containsKey(pe.docID)) doc_w.put(pe.docID, wt_doc*wt_q);
                        double prevscore = doc_w.get(pe.docID);
                        double newscore = wt_q * wt_doc + prevscore;
                        doc_w.put(pe.docID, newscore);
                        }
                    }
            
        
		
       PostingsList result = new PostingsList();
            for (int docID : doc_w.keySet()) {

                double cosinesim = doc_w.get(docID) / Math.pow(docLengths.get(String.valueOf(docID)),0.6);
                PostingsEntry pe = new PostingsEntry(docID, cosinesim);
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
        }*/
 }

