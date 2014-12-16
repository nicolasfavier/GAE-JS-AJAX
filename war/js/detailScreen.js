 $(document).ready(function() { 
	 
	/* var clock = $("#flipcountdownbox1").FlipClock({speedFlip:60,tick:function(){
         currentTime=time+1000;
         time=currentTime;
         date=new Date(currentTime);
         return date;
     }})
	 
	 alert("good");*/
	 
	
            $("#e1").select2(); 
            date=new Date('5.10.2012 00:20:00');
            time=date.getTime();
            
            date2=new Date('5.10.2012 00:05:00');
            time2=date2.getTime();
            
            date3=new Date('5.10.2012 00:25:00');
            time3=date.getTime();
            $("#flipcountdownbox1").flipcountdown({speedFlip:60,tick:function(){
                    currentTime=time-1000;
                    time=currentTime;
                    date=new Date(currentTime);
                    return date;
                }});
            
            $("#flipcountdownbox2").flipcountdown({speedFlip:60,tick:function(){
                    currentTime=time2-1000;
                    time2=currentTime;
                    date2=new Date(currentTime);
                    return date2;
                }});
            
            $("#flipcountdownbox3").flipcountdown({speedFlip:60,tick:function(){
                    currentTime=time3-1000;
                    time3=currentTime;
                    date3=new Date(currentTime);
                    return date3;
                }});
        });

 
 
 $(document).ready(function(){
		$("#playButton").click(function(){
			 alert("coucou");
	   });
	});	 