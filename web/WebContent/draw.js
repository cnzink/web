
function draw(){
	 var graph = new Q.Graph('canvas');
	 var gw = graph.createNode("GetWay",0,-400);
	 gw.image="Q-server";
	 var ap = graph.createNode("Ap",0,-300);
	 ap.image="Q-server";
	 var edge1 = graph.createEdge("",gw,ap);
	 
	 for(var i=0;i<uid.length;i++){
		 var str = uid[i];
		 str = graph.createNode(nickname[i],0,-200+100*i);
	 }
//	 var edge = graph.createEdge("",ap,str[i]);
//	 var edge = graph.createEdge("",str[i-1],str[i]);

}