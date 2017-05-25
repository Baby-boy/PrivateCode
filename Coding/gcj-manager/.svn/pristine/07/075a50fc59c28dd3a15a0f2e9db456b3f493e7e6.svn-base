var dataval = [];

function getinput(){
	var username = $('input[name="addname"]').val();
	var usertel = $('input[name="addtel"]').val();
	var tpl = {name:username,tel:usertel};
	dataval.push(tpl);
	$('.woderenzheng-popup').addClass('dis-none');
	$('.woderenzheng-zhezhao').addClass('dis-none');
	$('.woderenzheng-table tbody').empty();
	getdata();
	$('input[name="addname"]').val('');
	$('input[name="addtel"]').val('');
}

function getdata(){
	for (var i=0;i<dataval.length;i++){
		$('.woderenzheng-table tbody').append('<tr><td>'+i+'</td><td>'+dataval[i].name+'</td><td>'+dataval[i].tel+'</td><td onclick="delDom('+i+')">删除</td></tr>')
	}
}
function delDom(i){
	$('.woderenzheng-table tbody tr').eq(i).empty();
	dataval.splice(i,1);
}
