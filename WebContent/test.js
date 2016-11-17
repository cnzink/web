function draw(){
	var cvs = document.getElementById("test");
	if(cvs=null) return false;
	var ctx=cvs.getContext("2d");
	
	ctx.removeTo(110,100);
	ctx.lineTo(110,190);
	ctx.strockstyle="blue";
	ctx.strock();
}