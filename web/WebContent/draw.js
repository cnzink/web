
function draw(){
	 var graph = new Q.Graph('canvas');
//	 graph.enbaleTooltip = false;
	 graph.enableWheelZoom = false;
//	 graph.moveToCenter();
	 graph.interactionMode = Q.Consts.INTERACTION_MODE_SELECTION;
	 console.log(device);
	 	 
	 for(var i=0;i<device[0].length;i++){
//		 var str = graph.createNode(nickname[i],0,-200+100*i);
//		 node.push(str);
		 var str = graph.createNode(device[0][i]);
		 str.size = {width:30};
		 if(device[2][i]=="gateway"||device[2][i]=="ap"){
			 str.image="Q-server";
		 }
		 else{
			 if(device[3][i]>8){
				 str.setStyle(Q.Styles.BACKGROUND_COLOR, '#006400');
				 str.setStyle(Q.Styles.PADDING, new Q.Insets(5));
				 }
			 else if(device[3][i]>4){
				 str.setStyle(Q.Styles.BACKGROUND_COLOR, '#FFFF00');
				 str.setStyle(Q.Styles.PADDING, new Q.Insets(5));
				 }
			 else if(device[3][i]>=0){
				 str.setStyle(Q.Styles.BACKGROUND_COLOR, '#FF0000');
				 str.setStyle(Q.Styles.PADDING, new Q.Insets(5));
				 }
			 }
		 node.push(str);
	 }
	 
	 console.log(edge);
	 console.log(node);
	 for(var i=0;i<edge[0].length;i++){
		 var str = graph.createEdge(edge[2][i],select(edge[0][i]),select(edge[1][i]));
		 console.log(edge[2][i]);
		 if(Math.abs(parseInt(edge[2][i]))<10){
			 str.setStyle(Q.Styles.EDGE_COLOR, '#006400');
		 }
		 else if (Math.abs(parseInt(edge[2][i]))<40){
			 str.setStyle(Q.Styles.EDGE_COLOR, '#FFFF00');
		 }
		 else if (Math.abs(parseInt(edge[2][i]))<100){
			 str.setStyle(Q.Styles.EDGE_COLOR, '#FF0000');
		 }
//		 str.setStyle(Q.Styles.EDGE_LINE_DASH, [8, 4, 0.01, 4]);
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