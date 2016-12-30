function insert_nickname(str){
	  if(have_nickname(str)){
		  return;
	  }
	  else{
		  nickname.push(str);
	  }
  }
 function have_nickname(str){
	  var have=false;
	  var i=0;
	  while(nickname.length!=undefined){
		  for(i;i<nickname.length;i++){
			  if(str==nickname[i]) have=true;
			  }
		  break;
		  }
	  return have;
  }
  
 
 
  function insert_uid(str){
	  if(have_uid(str)){
		  return;
	  }
	  else{
		  uid.push(str);
	  }
  }
  function have_uid(str){
	  var have=false;
	  var i=0;
	  for(i;i<nickname.length;i++){
		  if(str==nickname[i]) have=true;
		  }
	  return have;
  }