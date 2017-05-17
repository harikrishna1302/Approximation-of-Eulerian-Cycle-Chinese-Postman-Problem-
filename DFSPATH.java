import java.util.*;
class DFSPATH
{
	private int V; 
	private LinkedList<Integer> adj[];
	int[] array;
	private int z=0;
	int[][] matrix;
	DFSPATH(int v)
	{
		V = v;
		adj = new LinkedList[v];
		matrix = new int[V][V];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();	
		array = new int[V];
		
	}
	void addEdge(int v, int w)
	{
		adj[v].add(w);
		matrix[v][w]=1;
		matrix[w][v]=1;
	}
	void DFSUtil(int v,boolean vist[])
	{
		vist[v] = true;
		array[z] =v;
		z++;
		//System.out.print(v+" ");
		Iterator<Integer> i = adj[v].listIterator();
		while (i.hasNext())
		{
			int n = i.next();
			if (!vist[n])
				
		   DFSUtil(n, vist);
		}
	}
	void DFS(int v)
	{
		boolean vist[] = new boolean[V];
		DFSUtil(v, vist);
	}
	int[] dfsoriginal(){
	
	return array;
	}

}