
  var  ws = new WebSocket("ws://localhost:8080/web/websocket");  
  var message="hello";
  ws.onopen = function (evt) { onOpen(evt) }; 
  ws.onmessage = function (evt) { onMessage(evt) }; 
  ws.onerror = function (evt) { onError(evt) };
  
  function onOpen(evt) { 
		 console.log("Connected to WebSocket server."); 
	 }
  
  function onMessage(evt) {  
	  console.log(evt.data);
	  var str = evt.data.split(",");
	  var i = parseInt(str[0]);
	  switch(i){
	  	case 0:
	  		console.log("run here");
			document.getElementById('networkID').innerHTML=str[1];
			break;
	  }
	  }
  function onError(evt) { 
		 console.log('Error occured: ' + evt.data); 
	 }
  function send(evt) { 
		 ws.send(""); 
	 }
  
  
  function onOpen(evt) { 
		 console.log("Connected to WebSocket server."); 
	 }
  
//  function onMessage(evt) {  
//	  var num=evt.data;
//	  console.log('Retrieved data from server: ' + evt.data);
//	  document.getElementById('number').innerHTML = num;
//	  draw(num);
//	  }

  function draw(num){
	  var graph = new Q.Graph('canvas');
	  var gw = graph.createNode("GetWay",0,-400);
	  gw.image="Q-server";
	  var ap = graph.createNode("Ap",0,-300);
	  ap.image="Q-server";
	  var edge1 = graph.createEdge("",gw,ap);
	  var i=1;
	  console.log(i);
	  var str=new Array(num+1);
	  for (i;i<=num;i++){
		  var str1="device"+i;
		  console.log(str);
		  str[i]=graph.createNode(str1,0,-300+100*i);
	 	 if(i==1){
	 		var edge = graph.createEdge("",ap,str[i]);
	 	 }
	 	 else{
	 		var edge = graph.createEdge("",str[i-1],str[i]);
	 	 }
  }
}