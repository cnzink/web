
  
var  ws = new WebSocket("ws://localhost:8080/web/websocket");  
var nickname = new Array();
var uid = new Array();

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
	  		insert_nickname(str[1]);
	  		break;
	  	case 2:
	  		insert_uid(str[1]);
	  		break;
	  		}
	  }

ws.onerror = function onError(evt) { 
	console.log('Error occured: ' + evt.data); 
	}
  

 
  
  
  
  
  
  

