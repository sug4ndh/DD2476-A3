/*package ir;

import java.io.*;
import java.util.*;

public class Query {

	public LinkedList<String> terms = new LinkedList<String>();
	public HashMap<String, Double> weights = new HashMap<String, Double>();

	public Query() {
	}

	public Query(String queryString) {
		StringTokenizer tok = new StringTokenizer( queryString );
		while ( tok.hasMoreTokens() ) {
			String term = tok.nextToken();
			terms.add(term);
			weights.put(term, 1.0);
		}    
	}

	public int size() {
		return terms.size();
	}

	public Query copy() {
		Query queryCopy = new Query();
		queryCopy.terms = (LinkedList<String>) terms.clone();
		queryCopy.weights = (HashMap<String, Double>) weights.clone();
		return queryCopy;
	}
	
	// TODO: either move this to SearchEngine or call functions in SearchEngine to compute 
	// tfidf weights
	
	public void relevanceFeedback( PostingsList results, boolean[] docIsRelevant, Indexer indexer ) {
		final double ALPHA = 0.25;
		final double BETA = 0.75;
        int N = indexer.index.docIDs.size();
		
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
		for (String term : terms) {
            PostingsList pl = indexer.index.getPostings(term);
			if (pl == null) continue;
            int tf = queryTerms.get(term);
			final int df = pl.size();
			final double idf = Math.log10(((double) N) / df);
                        double tfidfq = tf*idf/queryNorm;
			//int df = indexer.index.getPostings(term).size();
                        double weight = weights.get(term)+tfidfq;
			weights.put(term, weight / weights.size());
		}

		int numRelevant = 0;
		for (boolean b : docIsRelevant)
			if (b) {
				numRelevant++;
			}

		
		Iterator<PostingsEntry> peIt = results.iterator();
		for (boolean isRelevant : docIsRelevant) {
			PostingsEntry pe = peIt.next();
			
			if (isRelevant) {
				Map<String, Integer> documentTerms = indexer.getTermsInDoc(pe.docID);
				int docLength = indexer.index.docLengths.get(String.valueOf(pe.docID));
				
				for (Map.Entry<String, Integer> termEntry : documentTerms.entrySet()) {
					String term = termEntry.getKey();
					int df1 = indexer.index.getPostings(term).size();
                     double idf = Math.log10((double)N/df1);
					if (!weights.containsKey(term)) {
						weights.put(term, 0.0);
						terms.add(term);
					}
					int tf = termEntry.getValue();
					double oldW = weights.get(term);
					System.err.println("oldW "+oldW);
                    weights.put(term, ALPHA*oldW + (BETA / numRelevant) * (((double) tf) / idf));
				}
			}
		}
	}
	
	/*public void relevanceFeedback( PostingsList results, boolean[] docIsRelevant, Indexer indexer ) {
		final double ALPHA = 1.0;
		final double BETA = 0.75;
                int N = indexer.index.docIDs.size();
                
                ArrayList<Pair> highidfterms = new ArrayList<Pair>();
                HashMap<String, Integer> termsforidf = new HashMap<String, Integer>();
                
		// weights (without idf) for each term in the original query
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
		for (String term : terms) {
                        PostingsList pl = indexer.index.getPostings(term);
			if (pl == null) continue;
                        int tf = queryTerms.get(term);
			final int df = pl.size();
			final double idf = Math.log10(((double) N) / df);
                        double tfidfq = tf*idf/queryNorm;
			//int df = indexer.index.getPostings(term).size();
                        double weight = weights.get(term)+tfidfq;
			weights.put(term, weight / weights.size());
		}

		int numRelevant = 0;
		for (boolean b : docIsRelevant) if (b) numRelevant++;

		Iterator<PostingsEntry> peIt = results.iterator();
		for (boolean isRelevant : docIsRelevant) {
			PostingsEntry pe = peIt.next();
			
			if (isRelevant) {
				Map<String, Integer> dTerms = indexer.getTermsInDoc(pe.docID);
				int docLength = indexer.index.docLengths.get(String.valueOf(pe.docID));
				
				for (Map.Entry<String, Integer> termEntry : dTerms.entrySet()) {
					String term = termEntry.getKey();
                                        int df1 = indexer.index.getPostings(term).size();
                                        highidfterms.add(new Pair(term, Math.log10((double)N/df1)));
                                        int tf = termEntry.getValue();
                                        termsforidf.put(term, tf);
                                }
                        }
                }
                Collections.sort(highidfterms);
                                        
                                        
               
				System.err.println("min "+Math.min(50, highidfterms.size()));
				for (int i=0;i<Math.min(50, highidfterms.size()) ;i++) {
					//String term = termEntry.getKey();
					//System.err.println("val i "+i);
					if (!weights.containsKey(highidfterms.get(i).term)) {
						weights.put(highidfterms.get(i).term, 0.0);
						terms.add(highidfterms.get(i).term);
					}
					//int tf = termEntry.getValue();
                                        int tf = termsforidf.get(highidfterms.get(i).term);
                                        double oldW = weights.get(highidfterms.get(i).term);
										System.err.println("oldW "+oldW+" "+highidfterms.get(i).term);
                                        weights.put(highidfterms.get(i).term, ALPHA*oldW + (BETA / numRelevant) * (((double) tf) / highidfterms.get(i).idf));
				}
			
		
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
	
}*/

package ir;

import java.io.*;
import java.util.*;

public class Query {

	public LinkedList<String> terms = new LinkedList<String>();
	public HashMap<String, Double> weights = new HashMap<String, Double>();

	public Query() {
	}

	public Query(String queryString) {
		StringTokenizer tok = new StringTokenizer( queryString );
		while ( tok.hasMoreTokens() ) {
			String term = tok.nextToken();
			terms.add(term);
			weights.put(term, 1.0);
		}    
	}

	public int size() {
		return terms.size();
	}

	public Query copy() {
		Query queryCopy = new Query();
		queryCopy.terms = (LinkedList<String>) terms.clone();
		queryCopy.weights = (HashMap<String, Double>) weights.clone();
		return queryCopy;
	}
	
	// TODO: either move this to SearchEngine or call functions in SearchEngine to compute 
	// tfidf weights
	
	public void relevanceFeedback( PostingsList results, boolean[] docIsRelevant, Indexer indexer ) {
		final double ALPHA = 0.25;
		final double BETA = 0.75;
                int N = indexer.index.docIDs.size();
		// weights (without idf) for each term in the original query
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
		for (String term : terms) {
                        PostingsList pl = indexer.index.getPostings(term);
			if (pl == null) continue;
                        int tf = queryTerms.get(term);
			final int df = pl.size();
			final double idf = Math.log10(((double) N) / df);
                        double tfidfq = tf*idf/queryNorm;
			//int df = indexer.index.getPostings(term).size();
                        double weight = weights.get(term)+tfidfq;
			weights.put(term, weight / weights.size());
		}

		int numRelevant = 0;
		for (boolean b : docIsRelevant) if (b) numRelevant++;

		// normalised weights (without idf) for each term in each relevant document
		Iterator<PostingsEntry> peIt = results.iterator();
		for (boolean isRelevant : docIsRelevant) {
			PostingsEntry pe = peIt.next();
			
			// only use the relevant documents (GAMMA = 0)
			if (isRelevant) {
				Map<String, Integer> documentTerms = indexer.getTermsInDoc(pe.docID);
				int docLength = indexer.index.docLengths.get("" + pe.docID);
				
				for (Map.Entry<String, Integer> termEntry : documentTerms.entrySet()) {
					String term = termEntry.getKey();
					int df1 = indexer.index.getPostings(term).size();
                     double idf = Math.log10((double)N/df1);
					if (!weights.containsKey(term)) {
						weights.put(term, 0.0);
						terms.add(term);
					}
					int tf = termEntry.getValue();
					double oldW = weights.get(term);
					System.err.println("oldW "+oldW);
                                        weights.put(term, ALPHA*oldW + (BETA / numRelevant) * (((double) tf) / idf));
				}
			}
		}
	}
	
	/*public void relevanceFeedback( PostingsList results, boolean[] docIsRelevant, Indexer indexer ) {
		final double ALPHA = 1.0;
		final double BETA = 0.75;
                int N = indexer.index.docIDs.size();
                
                ArrayList<Pair> highidfterms = new ArrayList<Pair>();
                HashMap<String, Integer> termsforidf = new HashMap<String, Integer>();
                
		// weights (without idf) for each term in the original query
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
		for (String term : terms) {
                        PostingsList pl = indexer.index.getPostings(term);
			if (pl == null) continue;
                        int tf = queryTerms.get(term);
			final int df = pl.size();
			final double idf = Math.log10(((double) N) / df);
                        double tfidfq = tf*idf/queryNorm;
			//int df = indexer.index.getPostings(term).size();
                        double weight = weights.get(term)+tfidfq;
			weights.put(term, weight / weights.size());
		}

		int numRelevant = 0;
		for (boolean b : docIsRelevant) if (b) numRelevant++;

		// normalised weights (without idf) for each term in each relevant document
		Iterator<PostingsEntry> peIt = results.iterator();
		for (boolean isRelevant : docIsRelevant) {
			PostingsEntry pe = peIt.next();
			
			if (isRelevant) {
				Map<String, Integer> dTerms = indexer.getTermsInDoc(pe.docID);
				int docLength = indexer.index.docLengths.get(String.valueOf(pe.docID));
				
				for (Map.Entry<String, Integer> termEntry : dTerms.entrySet()) {
					String term = termEntry.getKey();
                                        int df1 = indexer.index.getPostings(term).size();
                                        highidfterms.add(new Pair(term, Math.log10((double)N/df1)));
                                        int tf = termEntry.getValue();
                                        termsforidf.put(term, tf);
                                }
                        }
                }
                Collections.sort(highidfterms);
                                        
                                        
               
				System.err.println("min "+Math.min(50, highidfterms.size()));
				for (int i=0;i<Math.min(50, highidfterms.size()) ;i++) {
					//String term = termEntry.getKey();
					//System.err.println("val i "+i);
					if (!weights.containsKey(highidfterms.get(i).term)) {
						weights.put(highidfterms.get(i).term, 0.0);
						terms.add(highidfterms.get(i).term);
					}
					//int tf = termEntry.getValue();
                                        int tf = termsforidf.get(highidfterms.get(i).term);
                                        double oldW = weights.get(highidfterms.get(i).term);
										System.err.println("oldW "+oldW+" "+highidfterms.get(i).term);
                                        weights.put(highidfterms.get(i).term, ALPHA*oldW + (BETA / numRelevant) * (((double) tf) / highidfterms.get(i).idf));
				}
			
		
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


