/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   First version:  Johan Boye, 2012
 */  

//import java.util.*;
//import java.io.*;

//public class PageRank{

    ///**  
     //*   Maximal number of documents. We're assuming here that we
     //*   don't have more docs than we can keep in main memory.
     //*/
    //final static int MAX_NUMBER_OF_DOCS = 2000000;

    ///**
     //*   Mapping from document names to document numbers.
     //*/
    //Hashtable<String,Integer> docNumber = new Hashtable<String,Integer>();

    ///**
     //*   Mapping from document numbers to document names
     //*/
    //String[] docName = new String[MAX_NUMBER_OF_DOCS];

    ///**  
     //*   A memory-efficient representation of the transition matrix.
     //*   The outlinks are represented as a Hashtable, whose keys are 
     //*   the numbers of the documents linked from.<p>
     //*
     //*   The value corresponding to key i is a Hashtable whose keys are 
     //*   all the numbers of documents j that i links to.<p>
     //*
     //*   If there are no outlinks from i, then the value corresponding 
     //*   key i is null.
     //*/
    //Hashtable<Integer,Hashtable<Integer,Boolean>> link = new Hashtable<Integer,Hashtable<Integer,Boolean>>();

    ///**
     //*   The number of outlinks from each node.
     //*/
    //int[] out = new int[MAX_NUMBER_OF_DOCS];

    ///**
     //*   The number of documents with no outlinks.
     //*/
    //int numberOfSinks = 0;

    ///**
     //*   The probability that the surfer will be bored, stop
     //*   following links, and take a random jump somewhere.
     //*/
    //final static double BORED = 0.15;

    ///**
     //*   Convergence criterion: Transition probabilities do not 
     //*   change more that EPSILON from one iteration to another.
     //*/
    //final static double EPSILON = 0.0001;

    ///**
     //*   Never do more than this number of iterations regardless
     //*   of whether the transistion probabilities converge or not.
     //*/
    //final static int MAX_NUMBER_OF_ITERATIONS = 1000;

    
        //public float[] initializepivector(int N){
        //float[] pi = new float[N];
        //float sum = 0;
        //Random r = new java.util.Random();{
                
            //for(int i=0;i<N;i++){
                //pi[i] = r.nextFloat();
                //sum += pi[i];
            //}
            
        //}
        //System.out.println("sum "+sum);
        //for(int i=0;i<N;i++){
                //pi[i] = pi[i]/sum;
                //System.out.println(pi[i]);
            //}
        //return pi;
    //}
    
    ///* --------------------------------------------- */


    //public PageRank( String filename ) {
	//int noOfDocs = readDocs( filename );
	//computePagerank( noOfDocs );
    //}


    ///* --------------------------------------------- */


    ///**
     //*   Reads the documents and creates the docs table. When this method 
     //*   finishes executing then the @code{out} vector of outlinks is 
     //*   initialised for each doc, and the @code{p} matrix is filled with
     //*   zeroes (that indicate direct links) and NO_LINK (if there is no
     //*   direct link. <p>
     //*
     //*   @return the number of documents read.
     //*/
    //int readDocs( String filename ) {
	//int fileIndex = 0;
	//try {
	    //System.err.print( "Reading file... " );
	    //BufferedReader in = new BufferedReader( new FileReader( filename ));
	    //String line;
	    //while ((line = in.readLine()) != null && fileIndex<MAX_NUMBER_OF_DOCS ) {
		//int index = line.indexOf( ";" );
		//String title = line.substring( 0, index );
		//Integer fromdoc = docNumber.get( title );
		////  Have we seen this document before?
		//if ( fromdoc == null ) {	
		    //// This is a previously unseen doc, so add it to the table.
		    //fromdoc = fileIndex++;
		    //docNumber.put( title, fromdoc );
		    //docName[fromdoc] = title;
		//}
		//// Check all outlinks.
		//StringTokenizer tok = new StringTokenizer( line.substring(index+1), "," );
		//while ( tok.hasMoreTokens() && fileIndex<MAX_NUMBER_OF_DOCS ) {
		    //String otherTitle = tok.nextToken();
		    //Integer otherDoc = docNumber.get( otherTitle );
		    //if ( otherDoc == null ) {
			//// This is a previousy unseen doc, so add it to the table.
			//otherDoc = fileIndex++;
			//docNumber.put( otherTitle, otherDoc );
			//docName[otherDoc] = otherTitle;
		    //}
		    //// Set the probability to 0 for now, to indicate that there is
		    //// a link from fromdoc to otherDoc.
		    //if ( link.get(fromdoc) == null ) {
			//link.put(fromdoc, new Hashtable<Integer,Boolean>());
		    //}
		    //if ( link.get(fromdoc).get(otherDoc) == null ) {
			//link.get(fromdoc).put( otherDoc, true );
			//out[fromdoc]++;
		    //}
		//}
	    //}
	    //if ( fileIndex >= MAX_NUMBER_OF_DOCS ) {
		//System.err.print( "stopped reading since documents table is full. " );
	    //}
	    //else {
		//System.err.print( "done. " );
	    //}
	    //// Compute the number of sinks.
	    //for ( int i=0; i<fileIndex; i++ ) {
		//if ( out[i] == 0 )
		    //numberOfSinks++;
	    //}
	//}
	//catch ( FileNotFoundException e ) {
	    //System.err.println( "File " + filename + " not found!" );
	//}
	//catch ( IOException e ) {
	    //System.err.println( "Error reading file " + filename );
	//}
	//System.err.println( "Read " + fileIndex + " number of documents" );
	//return fileIndex;
    //}


    ///* --------------------------------------------- */


    ///*
     //*   Computes the pagerank of each document.
     //*/
    //void computePagerank( int numberOfDocs ) {
	////
	////   YOUR CODE HERE
	////
		//initializepivector(numberOfDocs);
    //}


    ///* --------------------------------------------- */


    //public static void main( String[] args ) {
	//if ( args.length != 1 ) {
	    //System.err.println( "Please give the name of the link file" );
	//}
	//else {
	    //new PageRank( args[0] );
	//}
    //}
//}


//***********************************************************************************************************************************
///*  
 //*   This file is part of the computer assignment for the
 //*   Information Retrieval course at KTH.
 //* 
 //*   First version:  Johan Boye, 2012
 //*/  

//import java.util.*;
//import java.io.*;

//public class PageRank{

    ///**  
     //*   Maximal number of documents. We're assuming here that we
     //*   don't have more docs than we can keep in main memory.
     //*/
    //final static int MAX_NUMBER_OF_DOCS = 2000000;

    ///**
     //*   Mapping from document names to document numbers.
     //*/
    //Hashtable<String,Integer> docNumber = new Hashtable<String,Integer>();

    ///**
     //*   Mapping from document numbers to document names
     //*/
    //String[] docName = new String[MAX_NUMBER_OF_DOCS];

    ///**  
     //*   A memory-efficient representation of the transition matrix.
     //*   The outlinks are represented as a Hashtable, whose keys are 
     //*   the numbers of the documents linked from.<p>
     //*
     //*   The value corresponding to key i is a Hashtable whose keys are 
     //*   all the numbers of documents j that i links to.<p>
     //*
     //*   If there are no outlinks from i, then the value corresponding 
     //*   key i is null.
     //*/
    //Hashtable<Integer,Hashtable<Integer,Boolean>> link = new Hashtable<Integer,Hashtable<Integer,Boolean>>();

    ///**
     //*   The number of outlinks from each node.
     //*/
    //int[] out = new int[MAX_NUMBER_OF_DOCS];

    ///**
     //*   The number of documents with no outlinks.
     //*/
    //int numberOfSinks = 0;

    ///**
     //*   The probability that the surfer will be bored, stop
     //*   following links, and take a random jump somewhere.
     //*/
    //final static double BORED = 0.15;

    ///**
     //*   Convergence criterion: Transition probabilities do not 
     //*   change more that EPSILON from one iteration to another.
     //*/
    //final static double EPSILON = 0.0001;

    ///**
     //*   Never do more than this number of iterations regardless
     //*   of whether the transistion probabilities converge or not.
     //*/
    //final static int MAX_NUMBER_OF_ITERATIONS = 30;

    //Hashtable<Integer, ArrayList<Integer>> linkvals= new Hashtable<Integer,ArrayList<Integer>>();
    ///* --------------------------------------------- */


    //public PageRank( String filename ) throws IOException {
	//int noOfDocs = readDocs( filename );
	//computePagerank( noOfDocs );
    //}


    ///* --------------------------------------------- */


    ///**
     //*   Reads the documents and creates the docs table. When this method 
     //*   finishes executing then the @code{out} vector of outlinks is 
     //*   initialised for each doc, and the @code{p} matrix is filled with
     //*   zeroes (that indicate direct links) and NO_LINK (if there is no
     //*   direct link. <p>
     //*
     //*   @return the number of documents read.
     //*/
    //int readDocs( String filename ) {
	//int fileIndex = 0;
	//try {
	    //System.err.print( "Reading file... " );
	    //BufferedReader in = new BufferedReader( new FileReader( filename ));
	    //String line;
	    //while ((line = in.readLine()) != null && fileIndex<MAX_NUMBER_OF_DOCS ) {
		//int index = line.indexOf( ";" );
		//String title = line.substring( 0, index );
		//Integer fromdoc = docNumber.get( title );
		////  Have we seen this document before?
		//if ( fromdoc == null ) {	
		    //// This is a previously unseen doc, so add it to the table.
		    //fromdoc = fileIndex++;
		    //docNumber.put( title, fromdoc );
		    //docName[fromdoc] = title;
		//}
		//// Check all outlinks.
		//StringTokenizer tok = new StringTokenizer( line.substring(index+1), "," );
		//while ( tok.hasMoreTokens() && fileIndex<MAX_NUMBER_OF_DOCS ) {
		    //String otherTitle = tok.nextToken();
		    //Integer otherDoc = docNumber.get( otherTitle );
		    //if ( otherDoc == null ) {
			//// This is a previousy unseen doc, so add it to the table.
			//otherDoc = fileIndex++;
			//docNumber.put( otherTitle, otherDoc );
			//docName[otherDoc] = otherTitle;
		    //}
		    //// Set the probability to 0 for now, to indicate that there is
		    //// a link from fromdoc to otherDoc.
		    //if ( link.get(fromdoc) == null ) {
			//link.put(fromdoc, new Hashtable<Integer,Boolean>());
			//linkvals.put(fromdoc, new ArrayList<Integer>());
		    //}
		    //if ( link.get(fromdoc).get(otherDoc) == null ) {
			//link.get(fromdoc).put( otherDoc, true );
			//linkvals.get(fromdoc).add(otherDoc);
			//out[fromdoc]++;
		    //}
		//}
	    //}
	    //if ( fileIndex >= MAX_NUMBER_OF_DOCS ) {
		//System.err.print( "stopped reading since documents table is full. " );
	    //}
	    //else {
		//System.err.print( "done. " );
	    //}
	    //// Compute the number of sinks.
	    //for ( int i=0; i<fileIndex; i++ ) {
		//if ( out[i] == 0 )
		    //numberOfSinks++;
	    //}
	//}
	//catch ( FileNotFoundException e ) {
	    //System.err.println( "File " + filename + " not found!" );
	//}
	//catch ( IOException e ) {
	    //System.err.println( "Error reading file " + filename );
	//}
	//System.err.println( "Read " + fileIndex + " number of documents" );
	//return fileIndex;
    //}

    
    
    
    ///* --------------------------------------------- */
    

    ///*
     //*   Computes the pagerank of each document.
     //*/
    //void computePagerank( int numberOfDocs )  throws IOException{
	////
	////   YOUR CODE HERE
	////
		//Returndname();
		////double[] pi;
        //double[] pi = initializepivector(numberOfDocs);
        //pi = powermethod(pi,numberOfDocs);
        ////pi = mc1(numberOfDocs, 100);
        ////sort(pi, 52, numberOfDocs);
        //sortandstore(pi,numberOfDocs);
    //}
    //public double[] mc1(int numberOfDocs, int multiplier){
        ///*Random mcr = new Random();
        //long nwalks = numberOfDocs * multiplier;
        //double[] walkends = new double[numberOfDocs];
		//for (int i = 0; i < nwalks; i++) {
			//int randomc = mcr.nextInt(numberOfDocs);
			//ArrayList<Integer> outLinks = linkvals.get(randomc);
			//while (mcr.nextDouble() > BORED) {
				//if (outLinks != null) {
					//int randInt = mcr.nextInt(outLinks.size());
					//randomc = outLinks.get(randInt);
					////for(int entry : link.get(mcr).keySet()){
                                        ////outLinks.add(entry);}
                                        //outLinks = linkvals.get(mcr);
				//} else {
					//randomc = mcr.nextInt(numberOfDocs);
                                        ////for(int entry : link.get(mcr).keySet()){
                                        ////outLinks.add(entry);}
					//outLinks = linkvals.get(randomc);
				//}
			//}
			//walkends[randomc]++;
		//}
                
                //double[] pi = new double[numberOfDocs];
		//for (int i = 0; i < walkends.length; i++){
                    //pi[i] = (walkends[i]) / nwalks;
                //}

		//return pi;*/
		//int[] hits = new int[numberOfDocs];
	//int nperlink = 100000000;	
		//Random RNG = new Random(System.currentTimeMillis());
	//int L =0 ;	
		//double next_target = 0.0;
		//for (int i = 0; i < nperlink; i++) {
			//if (i > next_target*nperlink) {
				//System.out.println("" + Math.round(100*next_target) + "% done");
				//next_target += 0.1;
			//}
                        
			
				//int j = RNG.nextInt(numberOfDocs);
                                //L++;
                                //hits[j]++;
				//// System.out.println("Starting i:" + i);
				//while (RNG.nextDouble() > BORED) {
					//if (out[j] > 0) {
						//Integer[] outlinks = link.get(j).keySet().toArray(new Integer[0]);
						//int next_j = RNG.nextInt(outlinks.length);
						//// System.out.println("Next i:" + next_i);
						//j = outlinks[next_j];
                                                //hits[j]++;
                                                //L++;
					//} /*else {
						//j = RNG.nextInt(numberOfDocs);
					//}
					//hits[j]++;*/
				//}
			
		//}
		
		//double[] pi = new double[numberOfDocs];
		//for (int i = 0; i < pi.length; i++) {
			//pi[i] = 1.0 * hits[i] / L;
		//}
                //return pi;
			//}
			
    //public double[] initializepivector(int N){
        //double[] pi = new double[N];
        //double sum = 0.0;
        
        ////initialize the pi vector
        //Random r = new Random(System.currentTimeMillis());{
               
            //for(int i=0;i<N;i++){
                //pi[i] = r.nextDouble();
                //sum += pi[i];
            //}
            
        //}
        ////System.out.println("sum "+sum);
        
        //// normalize to obtain probabilities
        //for(int i=0;i<N;i++){
                //pi[i] = pi[i]/sum;
                ////System.out.println(pi[i]);
            //}
        //return pi;
        ////return null;
    //}
         //public double[] powermethod2(double[] pi, int numberOfDocs){
        //int N = pi.length;
        //double[] pistar = new double[N];
        //double[][] prob = new double[N][N];
        
        //for(int i=0;i<N;i++){
            //pistar[i]=0.0;
            //for(int j=0;j<N;j++){
                //prob[i][j]=0.0;
            //}
        //}
        
        //for(int i=0;i<N;i++){
            //for(int j=0;j<N;j++){
                //if(out[i]==0){
                    //prob[i][j]+=1.0/N;
                //}
                //else{
                    //if(link.get(i).get(j)!=null){
                        //prob[i][j]+=1.0/out[i];
                    //}
                //}
            //}
        //}
		//for(int i=0;i<N;i++){
            //for(int j=0;j<N;j++){
                //pistar[i]+=pi[j]*prob[i][j];
            //}
        //}
        
        //boolean flag = true;
       //int count = 0;
       //double diff = 1.0;
        //while(flag){
            
            //System.err.println(++count);
            
            //if(diff<=EPSILON){flag=false;}
            //for(int i=0;i<N;i++){
                //for(int j=0;j<N;j++){
                    //pistar[i]+=pi[j]*prob[i][j];
                //}
            //}
            //diff = 0.0;
            //for(int i = 0; i < numberOfDocs; i++){
                   //diff += Math.abs(pistar[i]-pi[i]);
                    //System.err.println("p"+pi[i]+" "+pistar[i]);
            //}
            //System.err.println("diiff"+diff);
            //for(int ik=0;ik<N;ik++){
                //pi[ik]=pistar[ik];
                //pistar[ik]=0.0;
            //}
        
            
        //}
        
        //for(int i=0;i<N;i++){
            
                //System.err.print(pi[i]+"\t");
            
            //System.err.println("");
        //}
        //return pi;
    //}
    //public double[] powermethod(double[] pi, int numberOfDocs){
        ////int N = pi.length;
        ////double[] pistar = new double[N];
        
        
               //int N = pi.length;
        //double[] pistar = new double[N];
        
        //for(int it=0;it<MAX_NUMBER_OF_ITERATIONS;it++){
            //System.err.println("it= "+it);
            //for(int i=0;i<numberOfDocs;i++){
                //pistar[i]=0.0;
                //for(int j = 0; j < numberOfDocs; j++){
                    //double pij = 0;
                    //if(out[j] == 0){
						////pij = BORED/N;
                        //pij += 1.0/N;
                    //} else if(link.get(j).get(i) == null) {
						////System.err.println(" ..."+link.get(j).get(i));
                        //pij = BORED/N;
                    //} else if(link.get(j).get(i)){
						////System.err.println(" val..."+link.get(j).get(i));
                        //pij = BORED/N;
                        //pij += (1.0-BORED)/(double)out[j];
                    //} else {
                        //System.out.println("Something wrong?");
                    //}
                    //pistar[i] += pi[j]*pij;
                //}
            //}

            //double diff = 0;
            //for(int i = 0; i < numberOfDocs; i++){
               //diff += Math.abs(pistar[i]-pi[i]);
                //System.out.println("difference "+diff);
            //}
            //if(diff < EPSILON) break;
            //for(int i = 0; i < numberOfDocs; i++){
                //pi[i] = pistar[i];
            
        
            //}
        //}
        
        //return pi;
	//}
    
    //private class Pair implements Comparable<Pair>{
        //private String docNumber;
        //private double pagerank;

        //public Pair(String key, double value){
            //docNumber = key;
            //pagerank = value;
        //}

        //public double getPagerank(){
            //return pagerank;
        //}
		
		//final int ascending = 1;
        //public int compareTo(Pair o){
            //if (this.pagerank == o.getPagerank()) return 0;
            //if (this.pagerank < o.getPagerank()) return ascending;
            //return -ascending;
        //}

        ///*public String toString(){
            ////return String.format("%5s %.12f", docNumber, pagerank);
            //return String.format("%s %f", docNumber, pagerank);
        //}*/
    //}
	//public HashMap<Integer, String> dname = new HashMap<Integer, String>();
    //private void Returndname() throws IOException{
		//System.err.println("Storing names...");
        ////HashMap<Integer, String> dname = new HashMap<Integer, String>();
       //String filename = "/home/sugandh/Downloads/Assignment2/ir/pagerank/articleTitles.txt";
         //Scanner sc2 = null;
        //try{
               //sc2 = new Scanner(new File(filename));
        //}   catch (FileNotFoundException e) {
            //e.printStackTrace();  
        //}
        //while (sc2.hasNextLine()) {
            //String[] words = sc2.nextLine().split(";");
                //dname.put(Integer.parseInt(words[0]), words[1]);
            //}
            
        ////return null;
    //}
	
	
    //public void sort(double[] pi, int N, int numberOfDocs){
        //ArrayList<Pair> tmp = new ArrayList<Pair>();
        //for(int i = 0; i < numberOfDocs; i++){
            //tmp.add(new Pair(docName[i], pi[i]));
        //}
         //Collections.sort(tmp);
         
        //for(int i = 0; i < N; i++){
            ////System.out.printf("%2d. %s\n", i+1, tmp.get(i));
            //System.out.printf("%2d. %6s %f\n", i+1, tmp.get(i).docNumber, tmp.get(i).pagerank);
           //// System.out.println(i+1+"."+"\t"+dname.get(Integer.parseInt(tmp.get(i).docNumber))+"\t"+tmp.get(i).pagerank);
           ////System.out.printf("%2d. %30s "+tmp.get(i).pagerank+"\n", i+1, dname.get(Integer.parseInt(tmp.get(i).docNumber)));
        //}
    //}
    

//public void sortandstore(double[] pi, int numberOfDocs){
        //ArrayList<Pair> tmp = new ArrayList<Pair>();
        //for(int i = 0; i < numberOfDocs; i++){
            //tmp.add(new Pair(docName[i], pi[i]));
        //}
         //Collections.sort(tmp);
         
         //String fileName = "/home/sugandh/Downloads/Assignment2/ir/pagerank/SortedNames.txt";
        
        
        //BufferedWriter writer = null;
        //try {

            //File logFile = new File(fileName);
            //writer = new BufferedWriter(new FileWriter(fileName,true));
            //writer.write("");
            //for(int i = 0; i < numberOfDocs; i++){
            ////System.out.printf("%2d. %s\n", i+1, tmp.get(i));
            ////System.out.printf("%2d. %6s %f\n", i+1, tmp.get(i).docNumber, tmp.get(i).pagerank);
            //System.out.println(dname.get(Integer.parseInt(tmp.get(i).docNumber))+"\t"+tmp.get(i).pagerank);
            ////System.out.println(i+1+"."+ "\t" + dname.get(i)+"\t"+tmp.get(i).pagerank);
            
            //writer.write("/home/sugandh/Downloads/ir-assignments/davisWiki/"+dname.get(Integer.parseInt(tmp.get(i).docNumber))+".f"+"\t"+tmp.get(i).pagerank+"\n");
        //}
            
            
            //writer.close();
        //}
        //catch(IOException ex) {
            //System.out.println(
                //"Error writing to file '"
                //+ fileName + "'");
            //// ex.printStackTrace();
        //}
         
        
    //}



    ///* --------------------------------------------- */




    //public static void main( String[] args ) throws IOException{
	//if ( args.length != 1 ) {
	    //System.err.println( "Please give the name of the link file" );
	//}
	//else {
	    //new PageRank( args[0] );
	//}
    //}
//}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/*  
 *   This file is part of the computer assignment for the
 *   Information Retrieval course at KTH.
 * 
 *   First version:  Johan Boye, 2012
 */  

import java.util.*;
import java.io.*;

public class PageRank{

    /**  
     *   Maximal number of documents. We're assuming here that we
     *   don't have more docs than we can keep in main memory.
     */
    final static int MAX_NUMBER_OF_DOCS = 2000000;

    /**
     *   Mapping from document names to document numbers.
     */
    Hashtable<String,Integer> docNumber = new Hashtable<String,Integer>();

    /**
     *   Mapping from document numbers to document names
     */
    String[] docName = new String[MAX_NUMBER_OF_DOCS];

    /**  
     *   A memory-efficient representation of the transition matrix.
     *   The outlinks are represented as a Hashtable, whose keys are 
     *   the numbers of the documents linked from.<p>
     *
     *   The value corresponding to key i is a Hashtable whose keys are 
     *   all the numbers of documents j that i links to.<p>
     *
     *   If there are no outlinks from i, then the value corresponding 
     *   key i is null.
     */
    Hashtable<Integer,Hashtable<Integer,Boolean>> link = new Hashtable<Integer,Hashtable<Integer,Boolean>>();

    /**
     *   The number of outlinks from each node.
     */
    int[] out = new int[MAX_NUMBER_OF_DOCS];

    /**
     *   The number of documents with no outlinks.
     */
    int numberOfSinks = 0;

    /**
     *   The probability that the surfer will be bored, stop
     *   following links, and take a random jump somewhere.
     */
    final static double BORED = 0.15;

    /**
     *   Convergence criterion: Transition probabilities do not 
     *   change more that EPSILON from one iteration to another.
     */
    final static double EPSILON = 0.0001;

    /**
     *   Never do more than this number of iterations regardless
     *   of whether the transistion probabilities converge or not.
     */
    final static int MAX_NUMBER_OF_ITERATIONS = 1000;

    Hashtable<Integer, ArrayList<Integer>> linkvals= new Hashtable<Integer,ArrayList<Integer>>();
    /* --------------------------------------------- */


    public PageRank( String filename ) throws IOException {
	int noOfDocs = readDocs( filename );
	computePagerank( noOfDocs );
    }


    /* --------------------------------------------- */


    /**
     *   Reads the documents and creates the docs table. When this method 
     *   finishes executing then the @code{out} vector of outlinks is 
     *   initialised for each doc, and the @code{p} matrix is filled with
     *   zeroes (that indicate direct links) and NO_LINK (if there is no
     *   direct link. <p>
     *
     *   @return the number of documents read.
     */
    int readDocs( String filename ) {
	int fileIndex = 0;
	try {
	    System.err.print( "Reading file... " );
	    BufferedReader in = new BufferedReader( new FileReader( filename ));
	    String line;
	    while ((line = in.readLine()) != null && fileIndex<MAX_NUMBER_OF_DOCS ) {
		int index = line.indexOf( ";" );
		String title = line.substring( 0, index );
		Integer fromdoc = docNumber.get( title );
		//  Have we seen this document before?
		if ( fromdoc == null ) {	
		    // This is a previously unseen doc, so add it to the table.
		    fromdoc = fileIndex++;
		    docNumber.put( title, fromdoc );
		    docName[fromdoc] = title;
		}
		// Check all outlinks.
		StringTokenizer tok = new StringTokenizer( line.substring(index+1), "," );
		while ( tok.hasMoreTokens() && fileIndex<MAX_NUMBER_OF_DOCS ) {
		    String otherTitle = tok.nextToken();
		    Integer otherDoc = docNumber.get( otherTitle );
		    if ( otherDoc == null ) {
			// This is a previousy unseen doc, so add it to the table.
			otherDoc = fileIndex++;
			docNumber.put( otherTitle, otherDoc );
			docName[otherDoc] = otherTitle;
		    }
		    // Set the probability to 0 for now, to indicate that there is
		    // a link from fromdoc to otherDoc.
		    if ( link.get(fromdoc) == null ) {
			link.put(fromdoc, new Hashtable<Integer,Boolean>());
                        linkvals.put(fromdoc, new ArrayList<Integer>());
		    }
		    if ( link.get(fromdoc).get(otherDoc) == null ) {
			link.get(fromdoc).put( otherDoc, true );
                        linkvals.get(fromdoc).add(otherDoc);
			out[fromdoc]++;
		    }
		}
	    }
	    if ( fileIndex >= MAX_NUMBER_OF_DOCS ) {
		System.err.print( "stopped reading since documents table is full. " );
	    }
	    else {
		System.err.print( "done. " );
	    }
	    // Compute the number of sinks.
	    for ( int i=0; i<fileIndex; i++ ) {
		if ( out[i] == 0 )
		    numberOfSinks++;
	    }
	}
	catch ( FileNotFoundException e ) {
	    System.err.println( "File " + filename + " not found!" );
	}
	catch ( IOException e ) {
	    System.err.println( "Error reading file " + filename );
	}
	System.err.println( "Read " + fileIndex + " number of documents" );
	return fileIndex;
    }

    
    
    
    /* --------------------------------------------- */
    

    /*
     *   Computes the pagerank of each document.
     */
    void computePagerank( int numberOfDocs ) throws IOException {
	//
	//   YOUR CODE HERE
	//
		Returndname();
		double[] pi;
        pi = initializepivector(numberOfDocs);
        pi = powermethod(pi,numberOfDocs);
        //pi=mc1EPRS(numberOfDocs, 10);
       // pi=mc1EPRS(numberOfDocs, 100);
        //pi=mc1EPRS(numberOfDocs, 1000);
        
        //pi=mc2EPCS(numberOfDocs, 10);
        //pi=mc2EPCS(numberOfDocs, 100);
        //pi=mc2EPCS(numberOfDocs, 1000);
        //pi=mc2EPCS(numberOfDocs, 10000);
      
        //pi=mc3CP(numberOfDocs, 10);
        //pi=mc3CP(numberOfDocs, 100);
        //pi=mc3CP(numberOfDocs, 1000);
        //pi=mc3CP(numberOfDocs, 10000);
      
        //pi=mc4CPDN(numberOfDocs,10);
        //pi=mc4CPDN(numberOfDocs, 100);
       //pi=mc4CPDN(numberOfDocs, 1000);
        //pi=mc4CPDN(numberOfDocs, 10000);
        
        //pi=mc5CPRS(numberOfDocs, 10);
        //pi=mc5CPRS(numberOfDocs, 100);
        //pi=mc5CPRS(numberOfDocs, 1000);
        //pi=mc5CPRS(numberOfDocs, 10000);
        sort(pi, 50, numberOfDocs);
        //sortandstore(pi,numberOfDocs);
    }
    
    public double[] initializepivector(int N){
        double[] pi = new double[N];
        double sum = 0.0;
        
        //initialize the pi vector
        Random r = new java.util.Random();{
               
            for(int i=0;i<N;i++){
                pi[i] = r.nextDouble();
                sum += pi[i];
            }
            
        }
        //System.out.println("sum "+sum);
        
        // normalize to obtain probabilities
        for(int i=0;i<N;i++){
                pi[i] = pi[i]/sum;
                //System.out.println(pi[i]);
            }
        return pi;
        //return null;
    }
    
    private class Pair implements Comparable<Pair>{
        private String docNumber;
        private double pagerank;

        public Pair(String key, double value){
            docNumber = key;
            pagerank = value;
        }

        public double getPagerank(){
            return pagerank;
        }
		
	final int ascending = 1;
        public int compareTo(Pair o){
            if (this.pagerank == o.getPagerank()) return 0;
            if (this.pagerank < o.getPagerank()) return ascending;
            return -ascending;
        }

      
    }
    
    public double[] powermethod2(double[] pi, int numberOfDocs){
        int N = pi.length;
        double[] pistar = new double[N];
        double[][] prob = new double[N][N];
        
        for(int i=0;i<N;i++){
            pistar[i]=0.0;
            for(int j=0;j<N;j++){
                prob[i][j]=0.0;
            }
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(out[i]==0){
                    prob[i][j]+=1.0/N;
                }
                else{
                    if(link.get(i).get(j)!=null){
                        prob[i][j]+=1.0/out[i];
                    }
                }
            }
        }
        /*for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                System.err.print(prob[i][j]+"\t");
            }
            System.err.println("");
        }*/
        double[][] G = new double[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                G[i][j]=(1-BORED)*prob[i][j]+BORED;
            }
        }
        boolean flag = true;
        
        
        while(flag){
            double diff = 1.0;
            
            
            if(diff<=EPSILON){flag=false;}
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    pistar[i]+=pi[j]*prob[i][j];
                }
            }
            diff = 0.0;
            for(int i = 0; i < numberOfDocs; i++){
                   diff += Math.abs(pistar[i]-pi[i]);
            }
            for(int ik=0;ik<N;ik++){
                pi[ik]=pistar[ik];
                pistar[ik]=0.0;
            }
        
            
        }
        
        for(int i=0;i<N;i++){
            
                System.err.print(pi[i]+"\t");
            
            System.err.println("");
        }
        return null;
    }
    
    public double[] mc1EPRS(int numberOfDocs, int multiplier){

        
                int[] hits = new int[numberOfDocs];
		int noofwalks = numberOfDocs * multiplier;
		Random mcrand = new Random(System.currentTimeMillis());
		
		
		for (int walk = 0; walk < noofwalks; walk++) {
			
			int j = mcrand.nextInt(numberOfDocs);
			// System.out.println("Chosen node:" + i);
			while (mcrand.nextDouble() > BORED) {
				if (out[j] > 0) {
					Integer[] outlinks = link.get(j).keySet().toArray(new Integer[0]);
					int next_j = mcrand.nextInt(outlinks.length);
					// System.out.println("Chosen now:" + next_j);
					j = outlinks[next_j];
				} else {
					j = mcrand.nextInt(numberOfDocs);
				}
			}
			hits[j]++;
			
		}
		
		double[] pi = new double[numberOfDocs];
		for (int i = 0; i < pi.length; i++) {
			pi[i] = 1.0 * hits[i] / noofwalks;
		}

		return pi;
    }
        
    
    
    public double[] mc2EPCS(int numberOfDocs, int mperlink){
        int[] hits = new int[numberOfDocs];
	//mperlink = 1000;	
	Random mcrand = new Random(System.currentTimeMillis());
	
		for (int i = 0; i < mperlink; i++) {
			
                        
			for (int startnode = 0; startnode < numberOfDocs; startnode++) {
				int ik = startnode;
				// System.out.println("chosen node:" + ik);
				while (mcrand.nextDouble() > BORED) {
					if (out[ik] > 0) {
						Integer[] outlinks = link.get(ik).keySet().toArray(new Integer[0]);
						int next_ik = mcrand.nextInt(outlinks.length);
						// System.out.println("chosen now:" + next_ik);
						ik = outlinks[next_ik];
					} else {
						ik = mcrand.nextInt(numberOfDocs);
					}
				}
				hits[ik]++;
			}
		}
		
		double[] pi = new double[numberOfDocs];
		for (int i = 0; i < pi.length; i++) {
			pi[i] = 1.0 * hits[i] / (numberOfDocs*mperlink);
		}
                return pi;
    }
    
    public double[] mc3CP(int numberOfDocs, int mperlink ){
        int[] hits = new int[numberOfDocs];
	//mperlink = 1000;	
	Random mcrand = new Random(System.currentTimeMillis());
	int L = 0 ;	
		
		for (int i = 0; i < mperlink; i++) {
			
			for (int start_node = 0; start_node < numberOfDocs; start_node++) {
				int ik = start_node;
                                L++;
                                hits[ik]++;
				// System.out.println("Chosen node:" + ik);
				while (mcrand.nextDouble() > BORED) {
					if (out[ik] > 0) {
						Integer[] outlinks = link.get(ik).keySet().toArray(new Integer[0]);
						int next_ik = mcrand.nextInt(outlinks.length);
						// System.out.println("chosen now:" + next_ik);
						ik = outlinks[next_ik];
					} else {
						ik = mcrand.nextInt(numberOfDocs);
					}
					hits[ik]++;
				}
			}
		}
		
		double[] pi = new double[numberOfDocs];
		for (int i = 0; i < pi.length; i++) {
			pi[i] = 1.0 * hits[i] / L;
		}
                return pi;
    }
    
    public double[] mc4CPDN(int numberOfDocs, int mperlink ){
    int[] hits = new int[numberOfDocs];
	//mperlink = 1000;	
	Random mcrand = new Random(System.currentTimeMillis());
	int L = 0 ;	
		
		for (int i = 0; i < mperlink; i++) {
			
			for (int start_node = 0; start_node < numberOfDocs; start_node++) {
				int ik = start_node;
                                L++;
                                hits[ik]++;
				// System.out.println("Chosen node:" + ik);
				while (mcrand.nextDouble() > BORED) {
					if (out[ik] > 0) {
						Integer[] outlinks = link.get(ik).keySet().toArray(new Integer[0]);
						int next_ik = mcrand.nextInt(outlinks.length);
						// System.out.println("Next i:" + next_i);
						ik = outlinks[next_ik];
                                                hits[ik]++;
                                                L++;
					} /*else {
						ik = mcrand.nextInt(numberOfDocs);
					}
					hits[ik]++;*/
				}
			}
		}
		
		double[] pi = new double[numberOfDocs];
		for (int i = 0; i < pi.length; i++) {
			pi[i] = 1.0 * hits[i] / L;
		}
                return pi;
    }
    
    public double[] mc5CPRS(int numberOfDocs, int mperlink ){
        
        int[] hits = new int[numberOfDocs];
	//mperlink = 1000000;	
	Random mcrand = new Random(System.currentTimeMillis());
	int L =0 ;	
        
        for (int i = 0; i < mperlink; i++) {
            
                        
			
            int ik = mcrand.nextInt(numberOfDocs);
            L++;
            hits[ik]++;
            // System.out.println("Chosen node:" + ik);
            while (mcrand.nextDouble() > BORED) {
                if (out[ik] > 0) {
                        Integer[] outlinks = link.get(ik).keySet().toArray(new Integer[0]);
                        int next_ik = mcrand.nextInt(outlinks.length);
                        // System.out.println("chosen now:" + next_ik);
                        ik = outlinks[next_ik];
                        hits[ik]++;
                        L++;
                } /*else {
                        j = mcrand.nextInt(numberOfDocs);
                }
                hits[ik]++;*/
            }

        }

        double[] pi = new double[numberOfDocs];
        for (int i = 0; i < pi.length; i++) {
                pi[i] = 1.0 * hits[i] / L;
        }
        return pi;
    }
    
    
    public double[] powermethod(double[] pi, int numberOfDocs){
                
        int N = pi.length;
        double[] pistar = new double[N];
        
        for(int it=0;it<MAX_NUMBER_OF_ITERATIONS;it++){
            System.err.println("it= "+it);
            for(int i=0;i<numberOfDocs;i++){
                pistar[i]=0.0;
                for(int j = 0; j < numberOfDocs; j++){
                    double pij = 0;
                    if(out[j] == 0){
                        pij = 1.0/N;
                    } else if(link.get(j).get(i) == null) {
			//System.err.println(" val linkji"+link.get(j).get(i));
                        pij = BORED/N;
                    } else if(link.get(j).get(i)){
			//System.err.println(" val..."+link.get(j).get(i));
                        pij = BORED/N;
                        pij += (1.0-BORED)/(double)out[j];
                    } 
                    pistar[i] += pi[j]*pij;
                }
            }

            double diff = 0;
            for(int i = 0; i < numberOfDocs; i++){
               diff += Math.abs(pistar[i]-pi[i]);
                System.out.println("difference "+diff);
            }
            if(diff < EPSILON) break;
            for(int i = 0; i < numberOfDocs; i++){
                pi[i] = pistar[i];
            
        
            }
        }
        
        return pi;
	}
    
    
    public HashMap<Integer, String> dname = new HashMap<Integer, String>();
    private void Returndname() throws IOException{
		System.err.println("Reading names...");
        //HashMap<Integer, String> dname = new HashMap<Integer, String>();
       String filename = "/home/sugandh/Downloads/Assignment2/ir/pagerank/articleTitles.txt";
         Scanner sc2 = null;
        try{
               sc2 = new Scanner(new File(filename));
        }   catch (FileNotFoundException e) {
            e.printStackTrace();  
        }
        while (sc2.hasNextLine()) {
            String[] words = sc2.nextLine().split(";");
                dname.put(Integer.parseInt(words[0]), words[1]);
            }
            
        //return null;
    }
	
     public void sort(double[] pi, int N, int numberOfDocs){
        ArrayList<Pair> tmp = new ArrayList<Pair>();
        for(int i = 0; i < numberOfDocs; i++){
            tmp.add(new Pair(docName[i], pi[i]));
        }
         Collections.sort(tmp);
         
        for(int i = 0; i < N; i++){
            //System.out.printf("%2d. %s\n", i+1, tmp.get(i));
            //System.out.printf("%2d. %6s %f\n", i+1, tmp.get(i).docNumber, tmp.get(i).pagerank);
            
            System.out.println(i+1+"."+ "\t"+tmp.get(i).docNumber + "\t"+tmp.get(i).pagerank);
            System.out.println(i+1+"."+"\t"+dname.get(Integer.parseInt(tmp.get(i).docNumber))+"\t"+tmp.get(i).pagerank);
           
           //System.out.printf("%2d. %30s "+tmp.get(i).pagerank+"\n", i+1, dname.get(Integer.parseInt(tmp.get(i).docNumber)));
        }
    }
    

public void sortandstore(double[] pi, int numberOfDocs){
        ArrayList<Pair> tmp = new ArrayList<Pair>();
        for(int i = 0; i < numberOfDocs; i++){
            tmp.add(new Pair(docName[i], pi[i]));
        }
         Collections.sort(tmp);
         
         String fileName = "/home/sugandh/Downloads/Assignment2/ir/pagerank/SortedNames.txt";
        
        
        BufferedWriter writer = null;
        try {

            File logFile = new File(fileName);
            writer = new BufferedWriter(new FileWriter(fileName,true));
            writer.write("");
            for(int i = 0; i < numberOfDocs; i++){
            //System.out.printf("%2d. %s\n", i+1, tmp.get(i));
            //System.out.printf("%2d. %6s %f\n", i+1, tmp.get(i).docNumber, tmp.get(i).pagerank);
            System.out.println(dname.get(Integer.parseInt(tmp.get(i).docNumber))+"\t"+tmp.get(i).pagerank);
            //System.out.println(i+1+"."+ "\t" + dname.get(i)+"\t"+tmp.get(i).pagerank);
            
            writer.write("/home/sugandh/Downloads/Assignment2/davisWiki/"+dname.get(Integer.parseInt(tmp.get(i).docNumber))+".f"+"\t"+tmp.get(i).pagerank+"\n");
        }
            
            
            writer.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            // ex.printStackTrace();
        }
         
        
    }
    
    /* --------------------------------------------- */


    public static void main( String[] args ) throws IOException {
	if ( args.length != 1 ) {
	    System.err.println( "Please give the name of the link file" );
	}
	else {
	    new PageRank( args[0] );
	}
    }
}





