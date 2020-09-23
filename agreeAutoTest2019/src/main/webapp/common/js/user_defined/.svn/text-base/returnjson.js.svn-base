       /** 
       * @see   将json字符串转换为对象 
       * @param   json字符串 
       * @return 返回object,array,string等对象 
       */  
        function evalJSON(strJson)  
       {  
        return eval( "(" + strJson + ")");  
       }  
      
   
       /** 
       * @see   将javascript数据类型转换为json字符串 
       * @param 待转换对象,支持object,array,string,function,number,boolean,regexp 
       * @return 返回json字符串 
       */  
         function obj2str(o)  
       {  
          var r = [];
		   if(typeof o == "string" || o == null) {
		     return o;
		   }
		   if(typeof o == "object"){
		     if(!o.sort){
		       r[0]="{"
		       for(var i in o){
		         r[r.length]='\"'+ i+'\"';
		         r[r.length]=":";
		         if(typeof o[i] == "string" || o[i] == null) {
		        	 r[r.length]=obj2str('\"'+o[i]+'\"');
				 }else{
					 r[r.length]=obj2str(o[i]);
				 }
		         r[r.length]=",";
		       }
		       r[r.length-1]="}"
		     }else{
		       r[0]="["
		       for(var i =0;i<o.length;i++){
		         r[r.length]=obj2str(o[i]);
		         r[r.length]=",";
		       }
		       r[r.length-1]="]"
		     }
		     return r.join("");
		   }
		   return o.toString();
      
    }  