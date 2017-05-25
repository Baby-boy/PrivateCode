(function(){
   /* var y=0;
    var timer=null;
//    var divs=document.querySelectorAll('[data-image]');
    var lists=document.querySelectorAll('.gcj-special-list');
    for(var i= 0,l=lists.length;i<l;i++){
        lists[i].onmouseover=function(){
            $('[data-image]').stop();
            clearInterval(timer);
//            var _this=this;
            // var y=0;
            var div=this.querySelector('[data-image]');
            timer=setInterval(function(){
                y+=-175;
                div.style.backgroundPosition="0px "+y+"px";
                if(y==-10325){
                    clearInterval(timer);
                }

            },20);
        };
        lists[i].onmouseout=function(){
//            var _this=this;
            $('[data-image]').stop();
            clearInterval(timer);
            var div=this.querySelector('[data-image]');
            timer=setInterval(function(){
                y+=175;
                div.style.backgroundPosition="0px "+y+"px";
                if(y==0){
                    clearInterval(timer);
                }

            },20);
        };
    }*/

    var y=0;
    var timer=null;
    demo1.onmouseover=function(){
        clearInterval(timer);
        var _this=demo;
        // var y=0;
        timer=setInterval(function(){
            y+=-175;
            _this.style.backgroundPosition="0px "+y+"px";
            if(y<=-10325){
                clearInterval(timer);
            }

        },20);
    };
    demo1.onmouseout=function(){
        clearInterval(timer);
        var _this=demo;
        timer=setInterval(function(){
            y+=175;
            _this.style.backgroundPosition="0px "+y+"px";
            if(y>=0){
                clearInterval(timer);
            }

        },20);
    }
})()