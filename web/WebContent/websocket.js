
var  ws = new WebSocket("ws://localhost:8080/web/websocket");  
var device = new Array(new Array(),new Array(),new Array());
var node = new Array();
var link = new Array();
var edge = new Array(new Array() , new Array() , new Array() );

ws.onopen = function onOpen(evt) { 
	 console.log("Connected to WebSocket server."); 
}

ws.onmessage = function onMessage(evt) {  
	  var str = evt.data.split(",");
	  var i = parseInt(str[0]);
	  switch(i){
	  	case 0:
			document.getElementById('networkID').innerHTML=str[1];
			break;
	  	case 1:
	  		insert_device(str[1],str[2],str[3]);
	  		break;
	  	case 3:
	  		insert_edge(str[1],str[2],str[3]);
	  		break;
	  		}
	  }

ws.onerror = function onError(evt) { 
	console.log('Error occured: ' + evt.data); 
	}
  

 
  
  
  
  
  
  

