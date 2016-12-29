
  var  ws = new WebSocket("ws://localhost:8080/test/websocket");  
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