import java.util.*;
import java.io.*;
class GraphHash {
	int V;
	HashMap<Integer, Integer> adj[];
	LinkedList<Integer> list[];
 
	public GraphHash(int V)
	{
		this.V = V;
		adj = new HashMap[V];
		list = new LinkedList[V];
		for(int i=0 ; i<V ; i++)
		{
			adj[i] = new HashMap<Integer, Integer>();
			list[i] = new LinkedList<Integer>();
		}
	}
 
	public void addEdge(int u, int v)
	{
		Integer obj = new Integer(v);
		list[u].addLast(obj);
		adj[u].put(v, obj);
	}
}