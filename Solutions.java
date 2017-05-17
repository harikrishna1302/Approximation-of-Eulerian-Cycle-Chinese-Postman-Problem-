import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solutions {
	 
private static  Integer START;
private static  Integer END;
	int V;
	int E;	
	Edge[] edge;
	public Solutions(int v,int e){
		V=v;
		E=e;
		edge=new Edge[e];
		
		for(int i=0;i<e;i++){
			edge[i]=new Edge();
		}
	
	}
	public void insert(int i,int s,int d,int l){
		edge[i].source=s;
		edge[i].destination=d;
		edge[i].length=l;
	}
	
public static void main(String[] args) throws FileNotFoundException{
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the number of vertices/nodes in a graph");
	int num_vertices=sc.nextInt();
	int num_edges= num_vertices*(num_vertices-1)/2;
	System.out.println("There should be "+num_edges+" edges in the text File of the format 'source destination cost' to be a complete graph");
	System.out.println();
	Scanner inputfile=new Scanner(new File("inputEdges.txt"));
	Solutions g1=new Solutions(num_vertices,num_edges);
	int[][] mat = new int[num_vertices][num_vertices];
	for(int i=0;i<num_vertices;i++){
	for(int j=0;j<num_vertices;j++){
	mat[i][j]=0;
	}}
	for(int i=0;i<num_edges;i++){
		int a=inputfile.nextInt();
		int b=inputfile.nextInt();
		int l=inputfile.nextInt();
		mat[a][b] = l;
		mat[b][a] = l;
		g1.insert(i,a, b, l);
	}
	boolean Tinequality = true;
    for(int i=0;i<num_vertices;i++){
	for(int j=0;j<num_vertices;j++){
	for(int k=0;k<num_vertices;k++){
	if((i!=j)&&(j!=k)&&(i!=k)){
	   if((mat[i][j]+mat[j][k])<mat[i][k])
	      Tinequality = false;
	   }

	}	
	}
	}
    System.out.println("a1) ");
	System.out.println("The non-negative symmetric distance matrix for the given input data is :");
     for(int i=0;i<num_vertices;i++){
	for(int j=0;j<num_vertices;j++){
	System.out.print(mat[i][j]+"    ");
	}
	System.out.println();
	}
    System.out.println();
	System.out.println("a2)");
if (Tinequality==false)
System.out.println("Triangle inequality doesn't exists");
else
System.out.println("Triangle inequality exists");


	Arrays.sort(g1.edge,new Comparator<Edge>(){
		public int compare(Edge a,Edge b){
			if(a.length!=b.length){
			return a.length-b.length;
			}
			else return a.source+a.destination-(b.source+b.destination);
		}
	});
	int j=0;
	Solutions g2=new Solutions(num_vertices,num_vertices-1);
	int[] parent=new int[g2.V+1];
	Arrays.fill(parent, -1);
	for(int i=0;i<g2.E;i++){
		while(g2.findCycle(parent,g1.edge[j])){
			j++;
		}
		
		g2.edge[i]=g1.edge[j];
		j++;
	}
	int ans=0;
       System.out.println();
	   System.out.println("b1) ");
	   System.out.println("The minimum spanning edges are given by");
	DFSPATH d = new DFSPATH(num_edges);
	int[][] SPANmat = new int[num_vertices][num_vertices];
	
     int MSTedges=0;
	for(Edge e:g2.edge){
		d.addEdge(e.source, e.destination);
		d.addEdge(e.destination, e.source);
		SPANmat[e.source][e.destination]=e.length;
		SPANmat[e.destination][e.source]=e.length;
		System.out.print(e.source+"/"+e.destination+" ----"+e.destination+"/"+e.source+"  ");
		ans=ans+e.length;
		MSTedges++;
	}
	System.out.println();
	System.out.println("Minimum Spanning Tree Distance is "+ans);
    System.out.println();
	System.out.println("b2) ");
	System.out.println("Generating a 2*OPT M TSP R cycle from the MST");
	System.out.println("Enter the starting node from all the nodes to start Tree path");
	int startingvertex = sc.nextInt();
	int[] DFSarray = new int[num_vertices];
	d.DFS(startingvertex);  
	int[] trash = new int[num_vertices];
	trash = d.dfsoriginal();
	for(int i=0;i<num_vertices;i++){
	DFSarray[i]=trash[i];
	}
	
	System.out.println();
	  Graph graph = new Graph();
	  for(Edge e:g2.edge){
		  if(e.length>0){
	     graph.addEdge(e.source, e.destination);
		 graph.addEdge(e.destination, e.source);
		
		 }
	  }
	  ArrayList<Integer> originalList = new ArrayList<Integer>();
    

	for(int i=0; i<num_vertices-1;i++){
	   g2.START=DFSarray[i];
	   g2.END = DFSarray[i+1];
	List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    Integer currentNode = START;
    List<Integer> visited = new ArrayList<Integer>();
    visited.add(START);
    new Solutions(num_vertices, num_edges).findAllPaths(graph, visited, paths, currentNode);
	for(ArrayList<Integer> path : paths){
        for (Integer node : path) {
			originalList.add(node);
        }
    }  
	originalList.remove(originalList.size()-1);
	}

	g2.START = DFSarray[num_vertices-1];
	g2.END = DFSarray[0];
	List<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
    Integer currentNode = START;
    List<Integer> visited = new ArrayList<Integer>();
    visited.add(START);
    new Solutions(num_vertices, num_edges).findAllPaths(graph, visited, paths, currentNode);
	for(ArrayList<Integer> path : paths){
        for (Integer node : path) {
			originalList.add(node);
        }
    } 
	int duplicatevariable=0;
	int[] dfswithDuplicates = new int[originalList.size()];
	System.out.println("The overall path of the graph including Duplicates is");
      Iterator itr = originalList.iterator();
	  while(itr.hasNext()){
	  int i = (Integer)itr.next();
	  dfswithDuplicates[duplicatevariable] =i;
	  duplicatevariable++;
	  System.out.print(i+" "); 
	  }
       System.out.println();
	   int DuplicateSum=0;
	  for(int i=1; i<dfswithDuplicates.length;i++){
          int h = dfswithDuplicates[i-1];
		  int k = dfswithDuplicates[i];
		  DuplicateSum = DuplicateSum + SPANmat[h][k];
		  		  
		  }
	  System.out.println("The total cost of the graph including Duplicates (2*OPT MTSP_R cycle) is "+DuplicateSum);

	  System.out.println("The overall path of the graph excluding Duplicates is");
	
           int[] distinct = new int[dfswithDuplicates.length];
		   distinct = g2.removeDuplicates(dfswithDuplicates);
		   for(int i=0;i<distinct.length;i++)
		   System.out.print(distinct[i]+" ");
		   System.out.print(startingvertex);
		   System.out.println();

         int distinctsum = 0;
         for(int i=1;i<distinct.length;i++){
			 int h = distinct[i-1];
			 int k = distinct[i];
			 distinctsum = distinctsum+ mat[h][k];
		 }
		 int lastvariable = distinct[distinct.length-1];
		 distinctsum = distinctsum+mat[lastvariable][startingvertex];

	     System.out.println("The total cost of the graph excluding Duplicates is "+distinctsum);
		 ArrayList<Integer> odd = new ArrayList();
		 for(int i : DFSarray){
			int cnt=0;
		for(int h : DFSarray){
		   if(SPANmat[i][h]!=0)
			   cnt++;
		}
		if((cnt%2)!=0)
			odd.add(i);
		}
        System.out.println();
		System.out.println("b3) ");
        System.out.println("Implementing perfect matching for the MST: ");
		System.out.println("The odd degree nodes of the graph are "+odd);
		
		 List<List<Integer>> differentpartitions = new ArrayList<>();
              List<Integer> minimumlist = new ArrayList<>();
		
		
		int min =0;
       for (List<List<Integer>> partitions : partitions(odd)) {
		   List<Integer> minlist = new ArrayList<>();
                     differentpartitions = partitions;
					 int odddistance =0;
            for(List<Integer> eachpartition : differentpartitions){
				int sour =eachpartition.get(0);
				minlist.add(sour);
				int dest =eachpartition.get(1);
				minlist.add(dest);
				 odddistance = odddistance+mat[sour][dest];
			}
			if(min ==0){
					 min = odddistance;
					 minimumlist = minlist;
					 }
				 else{
				 if(min>odddistance){
				 min = odddistance;
				 minimumlist = minlist;
				 }
				 }

      
        }
		//System.out.println();
		//System.out.println(minimumlist);
		System.out.println("minimum cost of all the partitions cost is "+ min);
		System.out.println("Pefect Matching edges are");
		for(int i=0;i<minimumlist.size();i=i+2)
        System.out.print(minimumlist.get(i)+"-----"+minimumlist.get(i+1)+" ");
		System.out.println();
        int V = MSTedges+(minimumlist.size()/2);
		GraphHash ec = new GraphHash(V);
        int[][] Eulerianmatrix = new int[num_vertices][num_vertices];
		for(int i=0; i<num_vertices;i++){
		for(int h=0; h<num_vertices;h++){
		       Eulerianmatrix[i][h] =SPANmat[i][h];
			   if(Eulerianmatrix[i][h]!=0)
                 ec.addEdge(i, h);
		}
		
		}
		for(int i=0;i<minimumlist.size();i=i+2){
			int aa = minimumlist.get(i);
			int bb = minimumlist.get(i+1);
                 Eulerianmatrix[aa][bb] = mat[aa][bb];
                  ec.addEdge(aa, bb);
				 Eulerianmatrix[bb][aa] = mat[bb][aa];
				 ec.addEdge(bb, aa);
				 }
		System.out.println();
		System.out.println("b4)" );
		System.out.println("The Eulerian graph matrix is given by,");
		for(int i=0; i<num_vertices;i++){
		for(int h=0; h<num_vertices;h++){
		      System.out.print(Eulerianmatrix[i][h]+"     ");
		}
		System.out.println();
		}
		System.out.println("The Eulerian path of the graph is,");
             printEulerPathCircuit(ec);
			 int EulerianDistance=0;
			 for(int i=0;i<EP.size();i=i+2)
                  EulerianDistance = EulerianDistance + Eulerianmatrix[EP.get(i)][EP.get(i+1)];

			 System.out.println("The total cost of the Eulerian Path is : "+EulerianDistance);
			 System.out.println();
             System.out.println("b5) ");
			 List<Integer> rd = new ArrayList<>();
			 for(int i=0;i<EP.size();i++){
			 if(!rd.contains(EP.get(i)))
                 rd.add(EP.get(i));
			 }
             rd.add(EP.get(0));
			 System.out.println(rd);
			 int optimaldistance=0;
			 for(int i=0;i<rd.size()-1;i++)
			 optimaldistance= optimaldistance+mat[rd.get(i)][rd.get(i+1)]; 
             System.out.println("The optimized cost of the Eulerian Cycle is " +optimaldistance);
      
   // Scanner.close();  
    }
	private static List<Integer> EP= new ArrayList<>();
	private static int time = 1;
	private static void removeEdge(GraphHash g, int u, int v)
	{
		if(g == null)
			return;
		Integer vNode = g.adj[u].get(v);
		g.list[u].remove(vNode);
		g.adj[u].remove(vNode);
 
		Integer uNode = g.adj[v].get(u);
		g.list[v].remove(uNode);
		g.adj[v].remove(uNode);
	}
 
	private static boolean doDFS(GraphHash g, int source, int u, int v, int[] disc, int[] low, int[] parent)
	{
		disc[source] = low[source] = time++;
		int i=0, size=0, next=0;
		size = g.list[source].size();
 
		for(i=0; i<size; i++)
		{
			next = (int) g.list[source].get(i);		
			if(disc[next] == -1)
			{
				parent[next] = source;
				if(doDFS(g, next, u, v, disc, low, parent) == true)
					return true;
				low[source] = Math.min(low[source], low[next]);
				if(low[next] > disc[source] && ( (source == u && next == v) || (source == v && next == u) ))
					return true;
			}
			else if(next != parent[source])
				low[source] = Math.min(low[source], disc[next]);
		}
		return false;
	}
 
	private static boolean isBridgeEdge(GraphHash g, int u, int v)
	{
		int[] disc = new int[g.V];
		int[] low = new int[g.V];
		int[] parent = new int[g.V];
		Arrays.fill(disc,  -1);
		Arrays.fill(low,  -1);
		Arrays.fill(parent, -1);
		return doDFS(g, 0, u, v, disc, low, parent);
	}
 
	private static boolean isValidNextEdge(GraphHash g, int u, int v)
	{
		int size = g.list[u].size();//System.out.println(u + " " + v + " " + size);
		if(size == 1)
			return true;
		return !isBridgeEdge(g, u, v);
	}
 
	private static void printEulerPathCircuitUtil(GraphHash g, int u)
	{
		if(g == null)
			return;
		int v = 0;
		for(int i=0; i<g.list[u].size(); i++)
		{
			v = (int) g.list[u].get(i);
			if(isValidNextEdge(g, u, v))
			{EP.add(u);
			 EP.add(v);
				System.out.print(u + " - " + v + " ");
				removeEdge(g, u, v);
				printEulerPathCircuitUtil(g, v);
			}
		}
	}
 
	private static void printEulerPathCircuit(GraphHash g)
	{
		if(g == null)
			return;
 
		int u = 0, i=0;
		for(i=0; i<g.V; i++)
		{
			if((g.adj[i].size() & 1) == 1)
			{
				u = i;
				System.out.print("Euler Path is: ");
				break;
			}
		}
		if(i == g.V)
			System.out.print("Euler Circuit is: ");
		printEulerPathCircuitUtil(g, u);
 
		System.out.println("");
	}
		private static List<List<List<Integer>>> partitions(List<Integer> inputSet) {
			List<List<List<Integer>>> res = new ArrayList<>();
			if (inputSet.isEmpty()) {
            List<List<Integer>> empty = new ArrayList<>();
            res.add(empty);
            return res;
        }
        
        int limit = 1 << (inputSet.size() - 1);
        
        for (int j = 0; j < limit; ++j) {
            List<List<Integer>> parts = new ArrayList<>();
            List<Integer> part1 = new ArrayList<>();
            List<Integer> part2 = new ArrayList<>();
            parts.add(part1);
            parts.add(part2);
            int i = j;
            for (Integer item : inputSet) {
                parts.get(i&1).add(item);
                i >>= 1;
            }
            for (List<List<Integer>> b : partitions(part2)) {
                List<List<Integer>> holder = new ArrayList<>();
				if(part1.size()==2){
                holder.add(part1);
                holder.addAll(b);
                res.add(holder);}
            }
        }
        return res;
    }

public int[] removeDuplicates(int[] arr) {

    int end = arr.length;

    for (int i = 0; i < end; i++) {
        for (int j = i + 1; j < end; j++) {
            if (arr[i] == arr[j]) {                  
                int shiftLeft = j;
                for (int k = j+1; k < end; k++, shiftLeft++) {
                    arr[shiftLeft] = arr[k];
                }
                end--;
                j--;
            }
        }
    }

    int[] whitelist = new int[end];
    for(int i = 0; i < end; i++){
        whitelist[i] = arr[i];
    }
    return whitelist;
}


private void findAllPaths(Graph graph, List<Integer> visited, List<ArrayList<Integer>> paths, Integer currentNode) {        
    if (currentNode.equals(END)) { 
        paths.add(new ArrayList(Arrays.asList(visited.toArray())));
        return;
    }
    else {
        LinkedList<Integer> nodes = graph.adjacentNodes(currentNode);    
        for (Integer node : nodes) {
            if (visited.contains(node)) {
                continue;
            } 
            List<Integer> temp = new ArrayList<Integer>();
            temp.addAll(visited);
            temp.add(node);          
            findAllPaths(graph, temp, paths, node);
        }
    }
}

public boolean findCycle(int[] parent,Edge e){
	int a=find(parent,e.source);
	int b=find(parent,e.destination);
	if(a==b){
		return true;
	}
	else{
		union(parent,a,b);
	}
	return false;
}
public int find(int[] parent,int a){
	if(parent[a]==-1){
		return a;
	}
	return find(parent,parent[a]);
}
public void union(int[] parent,int a,int b){
	parent[a]=b;
}

}
class Edge {
int source;
int destination;
int length;
}