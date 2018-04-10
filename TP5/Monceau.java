import java.util.ArrayList;
import java.util.Hashtable;

public class Monceau {
	ArrayList<Node> arbres;
	

	public Monceau() {
		arbres = new ArrayList<Node>();
	}

	public void fusion(Monceau autre) {

		Monceau retenue = new Monceau();
		int maxOrder = Math.max(this.maxOrder(),autre.maxOrder());
	
		for (int j=0; j<maxOrder+1 ; j++)
		{
			//ArrayList<Node> nodesOfSameOrder = new ArrayList<Node>();
			Hashtable<String,ArrayList<Node>> nodesOfSameOrder = new Hashtable<String,ArrayList<Node>>();
			nodesOfSameOrder.put(String.valueOf(j),new ArrayList<Node>());
			int orderCount = 0;
			Node tmpNode = null;

			for (Node node : this.arbres)
			{
				if (node.ordre == j)
				{
					nodesOfSameOrder.get(String.valueOf(j)).add(node);
					tmpNode = node;
					orderCount ++;
				}
			}

			for (Node node : autre.arbres)
			{
				if (node.ordre == j)
				{
					nodesOfSameOrder.get(String.valueOf(j)).add(node);
					orderCount ++;
				}
			}

			for (Node node : retenue.arbres)
			{
				if (node.ordre == j)
				{
					nodesOfSameOrder.get(String.valueOf(j)).add(node);
					orderCount ++;
				}
			}
			
			switch(orderCount){
				case 1:
					if (tmpNode == null)//le monceau ne contient pas un noeud d'ordre j
						{
							this.arbres.add(nodesOfSameOrder.get(String.valueOf(j)).get(0));
						}
					break;
				case 2:
					retenue.arbres.add(nodesOfSameOrder.get(String.valueOf(j)).get(0).fusion(nodesOfSameOrder.get(String.valueOf(j)).get(1)));//on ajoute la fusion des deux arbres d'ordre j à la fusion
					if (tmpNode != null) //le monceau contient un noeud d'odre j
						{
							this.arbres.remove(tmpNode);
						}
					break;
				case 3:
					retenue.arbres.add(nodesOfSameOrder.get(String.valueOf(j)).get(1).fusion(nodesOfSameOrder.get(String.valueOf(j)).get(2)));//le premier element correspond à l'arbre d'ordre j que l'on laisse dans le monceau final
					break; 
			}
	
		}

	}

	public void insert(int val) {
		// A completer
	}

	public boolean delete(int val) {
		// A completer
		return false;
	}

	public void print() {
		for (Node node : arbres) {
			System.out.println("\n\nordre : " + node.ordre);
			node.print();
		}
	}

	private int maxOrder()
	{
		int tmpMax = 0;
		for (Node node : arbres)
		{
			if (node.ordre > tmpMax)
			{
				tmpMax = node.ordre;
			}
		}
		return tmpMax;
	}


}
