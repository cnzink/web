
function draw(){
	 var graph = new Q.Graph('canvas');
//	 graph.enbaleTooltip = false;
	 graph.enableWheelZoom = false;
//	 graph.moveToCenter();
	 graph.interactionMode = Q.Consts.INTERACTION_MODE_SELECTION;
	 	 
	 for(var i=0;i<device[0].length;i++){
//		 var str = graph.createNode(nickname[i],0,-200+100*i);
//		 node.push(str);
		 var str = graph.createNode(device[0][i]);
		 str.size = {width:30};
		 if(device[2][i]=="gateway"||device[2][i]=="ap"){
			 str.image="Q-server";
		 }
		 node.push(str);
	 }
	 
	 console.log(edge);
	 console.log(node)
	 for(var i=0;i<edge[0].length;i++){
		 var str = graph.createEdge(edge[2][i],select(edge[0][i]),select(edge[1][i]));
		 link.push(str);
	 }
	 
	 var layouter = new Q.SpringLayouter(graph);
	 layouter.repulsion = 100;
	 layouter.attractive = 0.2;
	 layouter.elastic = 1;
	 layouter.start();
}

function select(str){
	for(var i=0;i<node.length;i++){
		if(node[i].name==str){
			return node[i];
			break;
		}
	}
}