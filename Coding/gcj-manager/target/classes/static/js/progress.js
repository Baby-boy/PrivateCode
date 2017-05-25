var textBox = document.querySelector('#curProgress');
function runJDT(num){
	$(".modal").show();
	document.querySelector('.progress-bar').style.width=num+"%";
	textBox.innerHTML='当前进度：'+num+"%";
	if(num == 100){
		wcDJT();
	}else{
		textBox.innerHTML='当前进度：'+num+"%";
	}
}

function wcDJT(){
	textBox.innerHTML='当前进度：上传成功';
	document.querySelector('.progress-bar').style.width=100+"%";
	setTimeout(() => {
		$(".modal").hide();
	}, 1500);
}


