
accessid = ''
accesskey = ''
host = ''
policyBase64 = ''
signature = ''
callbackbody = ''
filename = ''
key = ''
expire = 0
g_object_name = ''
g_object_name_type = ''
now = timestamp = Date.parse(new Date()) / 1000; 
uploader = '';
$(function(){
	function send_request()
	{
	    var xmlhttp = null;
	    if (window.XMLHttpRequest)
	    {
	        xmlhttp=new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject)
	    {
	        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	    }
	  
	    if (xmlhttp!=null)
	    {
	        serverUrl = '/api/v1.0/oss/generate';
	        xmlhttp.open( "GET", serverUrl, false );
	        xmlhttp.send( null );
	        return xmlhttp.responseText
	    }
	    else
	    {
	        alert("Your browser does not support XMLHTTP.");
	    }
	};

	function check_object_radio() {
	    var tt = document.getElementsByName('myradio');
	    for (var i = 0; i < tt.length ; i++ )
	    {
	        if(tt[i].checked)
	        {
	            g_object_name_type = tt[i].value;
	            break;
	        }
	    }
	}

	function get_signature()
	{
	    // 可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
	    now = timestamp = Date.parse(new Date()) / 1000; 
	    if (expire < now + 3)
	    {
	        body = send_request()
	        var obj = eval ("(" + body + ")");
	        host = obj['host']
	        policyBase64 = obj['policy']
	        accessid = obj['accessid']
	        signature = obj['signature']
	        expire = parseInt(obj['expire'])
	        callbackbody = obj['callback'] 
	        key = obj['dir']
	        return true;
	    }
	    return false;
	};

	function random_string(len) {
	　　len = len || 32;
	　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
	　　var maxPos = chars.length;
	　　var pwd = '';
	　　for (i = 0; i < len; i++) {
	    　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
	    }
	    return pwd;
	}

	function get_suffix(filename) {
	    pos = filename.lastIndexOf('.')
	    suffix = ''
	    if (pos != -1) {
	        suffix = filename.substring(pos)
	    }
	    return suffix;
	}

	function calculate_object_name(filename)
	{
	    if (g_object_name_type == 'local_name')
	    {
	        g_object_name += "${filename}"
	    }
	    else if (g_object_name_type == 'random_name')
	    {
	        suffix = get_suffix(filename)
	        g_object_name = key + random_string(10) + suffix
	    }
	    return ''
	}

	function get_uploaded_object_name(filename)
	{
	    if (g_object_name_type == 'local_name')
	    {
	        tmp_name = g_object_name
	        tmp_name = tmp_name.replace("${filename}", filename);
	        return tmp_name
	    }
	    else if(g_object_name_type == 'random_name')
	    {
	        return g_object_name
	    }
	}

	function set_upload_param(up, filename, ret)
	{
	    if (ret == false)
	    {
	        ret = get_signature()
	    }
	    g_object_name = key;
	    if (filename != '') { suffix = get_suffix(filename)
	        calculate_object_name(filename)
	    }
	    new_multipart_params = {
	        'key' : g_object_name,
	        'policy': policyBase64,
	        'OSSAccessKeyId': accessid, 
	        'success_action_status' : '200', // 让服务端返回200,不然，默认会返回204
	        'callback' : callbackbody,
	        'signature': signature,
	    };

	    up.setOption({
	        'url': host,
	        'multipart_params': new_multipart_params
	    });

	    up.start();
	}

	uploader = new plupload.Uploader({
		runtimes : 'html5,flash,silverlight,html4',
		browse_button : 'selectfiles', 
	    // multi_selection: false,
		//container: document.getElementById('container1'),
		flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
		silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
	    url : 'http://oss.aliyuncs.com',

	    filters: {
	        mime_types : [ // 只允许上传图片和zip文件
	        { title : "Image files", extensions : "jpg,gif,png,bmp" }, 
	        { title : "Zip files", extensions : "zip,rar" },
	        { title : "Video files", extensions : "mp4,webm,ogg,swf" }
	        ],
	        max_file_size : '50mb', // 最大只能上传10mb的文件
	        prevent_duplicates : true // 不允许选取重复文件
	    },

		init: {
			PostInit: function() {
				document.getElementById('postfiles').onclick = function() {
					set_upload_param(uploader, '', false);
	            return false;
				};
			},

			FilesAdded: function(up, files) {
				plupload.each(files, function(file,index) {
					!function(index){
						previewImage(files[index],function(imgsrc){
							$(".enclosure dl").append("<dd id='" + file.id + "'><img src='" + imgsrc + "' /><i></i><div class=\"progress\"><div class=\"progress-bar\"></div><div class=\"progress-text\">100%</div></div></dd>");
							
							$(".moxie-shim-html5").css({
								left:$("#selectfiles").position().left,
								top:$("#selectfiles").position().top
							});
						})
				    }(index);
				});
			},

			BeforeUpload: function(up, file) {
				check_object_radio();
	            set_upload_param(up, file.name, true);
	        },

			UploadProgress: function(up, file) {
				var pro = $("#"+file.id);
				pro.find(".progress").show();
				//d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				pro.find(".progress-text").html(file.percent + "%");
				pro.find(".progress-bar").css("width",file.percent + "%");
				if( file.percent == 100){
					pro.find(".progress-text").html("已完成");
					pro.find("i").remove();
					setTimeout(function(){
						pro.find(".progress").hide();
					},1000);
				}
			},

			FileUploaded: function(up, file, info) {
	            if (info.status == 200)
	            {	
	            	c2c.pics.push('"'+get_uploaded_object_name(file.name)+'"');
	                //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + get_uploaded_object_name(file.name) + ' 回调服务器返回的内容是:' + info.response;
	            }
	            else if (info.status == 203)
	            {
	                //console.log( '上传到OSS成功，但是oss访问用户设置的上传回调服务器失败，失败原因是:' + info.response );
	            }
	            else
	            {
	                //document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
	            } 
			},

			Error: function(up, err) {
	            if (err.code == -600) {
	                document.getElementById('console').appendChild(document.createTextNode("\n选择的文件太大了,可以根据应用情况，在upload.js 设置一下上传的最大大小"));
	            }
	            else if (err.code == -601) {
	                document.getElementById('console').appendChild(document.createTextNode("\n选择的文件后缀不对,可以根据应用情况，在upload.js进行设置可允许的上传文件类型"));
	            }
	            else if (err.code == -602) {
	                document.getElementById('console').appendChild(document.createTextNode("\n这个文件已经上传过一遍了"));
	            }
	            else 
	            {
	                document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
	            }
			}
		}
	});


	function previewImage(file,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
		//if(!file || !/image\//.test(file.type)) return; //确保文件是图片
		if(file.type=='image/gif'){//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
			var fr = new mOxie.FileReader();
			fr.onload = function(){
				callback(fr.result);
				fr.destroy();
				fr = null;
			}
			fr.readAsDataURL(file.getSource());
		}else if(file.type=='video/mp4'){
			console.log(1);
			callback("http://res.zwzyd.com/dby/web/images/icon_video.png");
		}else{
			var preloader = new mOxie.Image();
			preloader.onload = function() {
				preloader.downsize( 300, 300 );//先压缩一下要预览的图片,宽300，高300
				var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
				callback && callback(imgsrc); //callback传入的参数为预览图片的url
				preloader.destroy();
				preloader = null;
			};
			preloader.load( file.getSource() );
		}	
	}

	uploader.init();

});
