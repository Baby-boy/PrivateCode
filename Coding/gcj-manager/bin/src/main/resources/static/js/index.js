(function(){
    var slider = {
        TIMER:0,//保存当前定时器序号
        CURIDX:0,//保存当前轮播图片的下标，从0开始
        NOW:0,//保存indicators 下hover的下标
        NUMBER:0,//轮播图片的数量
        DURATION:5000,//每张图片轮播的时间

        init: function () {//初始化页面之后启动轮播
            this.NUMBER=$('.banner>.item').length-1;
            this.updateView();
        },
        autoMove:function(playNowIdx,playNextIdx){
            $('.banner>.item').eq(playNowIdx).css({'z-index': 10}).stop().fadeOut(200);
            $('.banner>.item').eq(playNowIdx).children('.container').removeClass('slideInUp');
            $('.banner>.item').eq(playNextIdx).css({'z-index': 20}).stop().fadeIn(200);
            $('.banner>.item').eq(playNextIdx).children('.container').addClass('slideInUp');
            $('.indicators li').removeClass().eq(playNextIdx).addClass('active');
        },
        slideNext:function(){
            //console.log(this);
            if(this.CURIDX<this.NUMBER){
                this.autoMove(this.CURIDX,this.CURIDX+1);
                this.CURIDX++;
            }else{
                this.autoMove(this.NUMBER,0);
                this.CURIDX=0;
            }
        },
        slidePrev:function(){
            if(this.CURIDX>0){
                this.autoMove(this.CURIDX,this.CURIDX-1);
                this.CURIDX--;
            }else{
                this.autoMove(this.CURIDX,this.NUMBER);
                this.CURIDX=this.NUMBER;
            }
        },
        myBinds:function(){
            this.TIMER=setInterval(this.slideNext.bind(this),this.DURATION);
            $('.banner').mouseover(function () {
                clearInterval(this.TIMER);
            }.bind(this));
            $('.banner').mouseout(function () {
                this.TIMER=setInterval(this.slideNext.bind(this),this.DURATION);
            }.bind(this));
            //小圆点指示器
            $('.indicators li').click(function (e) {
                var that=$(e.target);
                this.NOW = that.index();
                $('.indicators li').removeClass();
                this.autoMove(this.CURIDX,this.NOW);
                this.CURIDX=this.NOW;
            }.bind(this));
        },
        updateView: function () {
            $('.banner>.item').eq(0).css({'z-index': 20}).stop().fadeIn(200);
            this.myBinds();
        }

    };
    $(function(){
        jQuery.fn.slider=function(){
            slider.init();
        };
        $('.banner').slider();
    });


})();
//鼠标滑动微移效果
$('[data-isTransition="true"]').on('mousemove',function(e){
    var ax = -($(window).innerWidth()/2- e.pageX)/40;
    var ay = ($(window).innerHeight()/2- e.pageY)/20;
    var bannerRoll=$(this).find('[data-transition]');
    bannerRoll.attr("style", "transform: rotateY("+ax+"deg) rotateX("+ay+"deg);-webkit-transform: rotateY("+ax+"deg) rotateX("+ay+"deg);-moz-transform: rotateY("+ax+"deg) rotateX("+ay+"deg)");
});

//banner item点击条状
$('[data-href]').click(function(){
  //  新窗口打开页面
  window.open($(this).attr('data-href'));
    //当前窗口打开页面
    //location.href=$(this).attr('data-href');
});
