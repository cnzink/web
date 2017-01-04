function insert_device(str1,str2,str3){
	  if(have_nickname(str1)){
		  return;
	  }
	  else{
		  device[0].push(str1);
		  device[1].push(str2);
		  device[2].push(str3);
	  }
  }
 function have_nickname(str){
	  var have=false;
	  for(var i=0;i<device[0].length;i++){
		  if(str==device[0][i])
			  have = true;
	  }
	  return have;
  }
  
  function insert_edge(str1,str2,str3){
	  if(have_edge(str1)){
		  return;
	  }
	  else{
		  edge[0].push(str1);
		  edge[1].push(str2);
		  edge[2].push(str3);
	  }
  }
  function have_edge(str){
	  for(var i=0;i<edge[0].length;i++){
		  if(str==edge[i])
			  return true;
	  }
	  return false;
  }