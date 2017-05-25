var inter = null;
var jd = 0;
var sd = 1;
var sdt = sd*1000;
var sleep = 91;
var textBox = document.querySelector('#curProgress');
function runJDT(){
	$(".modal").show();
	inter = setInterval(function(){
		document.querySelector('.progress-bar').style.width=jd+"%";
		textBox.innerHTML='当前进度：'+jd+"%";
		jd++;
		if(jd==sleep){
			clearJDT();
		}
    },sdt);
}

function wcDJT(){
	clearJDT();
	textBox.innerHTML='当前进度：上传成功';
	document.querySelector('.progress-bar').style.width=100+"%";
	setTimeout(() => {
		$(".modal").hide();
	}, 1500);
	jd = 0;
}

//设置1%的进度所需时间（如：num=1时，为每秒进度1%）
function setSD(num){
	sd = num;
}

//设置进度条停止百分比（如：num=50，进度条走到50%时将停止前进）
function setSleep(num){
	sleep = num+1;
}

//设置进度条的进度
function setJD(num){
	jd = num;
	document.querySelector('.progress-bar').style.width=jd+"%";
	if(num == 100){
		textBox.innerHTML='当前进度：上传成功';
	}else{
		textBox.innerHTML='当前进度：'+jd+"%";
	}
}

function clearJDT(){
	if(inter != null){
		clearInterval(inter);
	}
}


