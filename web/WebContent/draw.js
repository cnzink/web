
function draw(){
	 var graph = new Q.Graph('canvas');

	 
	 for(var i=0;i<uid.length;i++){
		 var str = graph.createNode(nickname[i],0,-200+100*i);
		 node.push(str);
	 }
	 
	 console.log(edge);
	 console.log(node)
	 for(var i=0;i<edge[0].length;i++){
		 var str = graph.createEdge(i.toString(),select(edge[0][i]),select(edge[1][i]));
		 link.push(str);
	 }
}

function select(str){
	for(var i=0;i<node.length;i++){
		if(node[i].name==str){
			return node[i];
			break;
		}
	}
}