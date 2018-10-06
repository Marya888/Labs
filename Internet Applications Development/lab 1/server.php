<?php
	$start = microtime(true);
	$x=$_GET["x"];
	$y=$_GET["y"];
	$r=$_GET["r"];
	$error="";
	$res=true;

	$x=comma($x);	
    	$y=comma($y);
	$r=comma($r);

	if(volidet($x,$y,$r)){		
		echo "{\"res\":\"". find($x,$y,$r) ."\", \"time\":\"". round((microtime(true) - $start)*1000000, 6) ."\"}";

	}else{		
		echo $GLOBALS['error'];
	}
		
	function find($x,$y,$r){
		if($x<=0 && $y<=0){
			if($x>=-$r && $y>=-$r/2)
				return 1;
			else 
				return 0;
		}else if($x<0 && $y>0){
			if($x*$x+$y*$y<=$r*$r)
				return 1;
			else 
				return 0;
		}else if($x>=0 && $y>=0){
			if(-2*$x+$r>=$y)
				return 1;
			else 
				return 0;
		}else
			return 0;
	}

	
	function volidet($x,$y,$r){

		if(volidet_availabil("X",$x)){
		    
			$GLOBALS['res']=false;
		
		}else if(volidet_is_numeric("X",$x)){
		    
			$GLOBALS['res']=false;
	;
	
		}else if(volidet_point("X",$x)){
		    
			$GLOBALS['res']=false;
	;
			
		}else if(abs($x)!=2 & abs($x)!=1.5 & abs($x)!=1 & abs($x)!=0.5 & abs($x)!=0){
			
			$GLOBALS['error']=$GLOBALS['error']." X должен принимать одно из значений -2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2";
	    	
			$GLOBALS['res']=false;
		
		}		
		

		if(volidet_availabil("Y",$y)){
		    
			$GLOBALS['res']=false;
	;
		
		}else if(volidet_is_numeric("Y",$y)){
		    
			$GLOBALS['res']=false;
	;

		}else if(volidet_point("Y",$y)){
		    
			$GLOBALS['res']=false;
	;
		
		}else if($y<=-3 | $y>=5){
			
			$GLOBALS['error']=$GLOBALS['error']." Y должен входить в диапозон (-3;5)";
			
			$GLOBALS['res']=false;
	;
				
		}

	
	
		if(volidet_availabil("R",$r)){
		    
			$GLOBALS['res']=false;
			
		}else if(volidet_is_numeric("R",$r)){
		    
			$GLOBALS['res']=false;
	
		}else if(volidet_point("R",$r)){
		    
			$GLOBALS['res']=false;
	
			
		}else if(abs($r)!=1 & abs($r)!=2 & abs($r)!=3 & abs($r)!=4 & abs($r)!=5){
			
			$GLOBALS['error']=$GLOBALS['error']." R должен принимать одно из значений 1, 2, 3, 4, 5";
			
			$GLOBALS['res']=false;
			
		}
	
	
		
		return $GLOBALS['res'];
	
	}

	
	
	function volidet_availabil($paramName, $param){
		
		if($param==""){
			
			$GLOBALS['error']=$GLOBALS['error']." Установите ".$paramName;
			
			return true;
		
		}

		return false;
	
	}
	
	

	function volidet_is_numeric($paramName, $param){
		
		if(!is_numeric($param)){
			
			$GLOBALS['error']=$GLOBALS['error'].$paramName." может быть только числом!!!";
			
			return true;
		
		}
		
		return false;
	
	}	
	
	
	

	function volidet_point($paramName, $param){
		
		if((string) $param[0]=='.'){
			
			$GLOBALS['error']=$GLOBALS['error'].$paramName." не должен начинаться с \".\"";
			
			return true;
		
		}
	
		return false;
	
	}	
		

	function comma($arg){
		$pos=strrpos($arg,',');	
		if($pos!=0 & $pos!=strlen($arg)-1){	
		
			if((is_numeric(substr($arg,0,$pos))) & (is_numeric(substr($arg,$pos+1)))){
				return substr($arg,0,$pos).'.'.substr($arg,$pos+1);
			}else{
				return $arg;
			}
		}else{
			return $arg;
		}
	}

?>



