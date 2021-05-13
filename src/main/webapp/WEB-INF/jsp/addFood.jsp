<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/layui/css/layui.css">
<script type="text/javascript" src="/layui/layui.js"></script>
<style type="text/css">
.layui-upload-img {
	width: 92px;
    height: 92px;
    margin: 0 10px 10px 0;
}
</style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>常规使用：普通图片上传</legend>
</fieldset>
<div class="layui-upload">
  <button type="button" class="layui-btn" id="test1">上传图片</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo1">
    <p id="demoText"></p>
  </div>
</div> 
<script type="text/javascript">
layui.use([ 'element', 'form', 'layer','upload' ],function() {
	var element = layui.element, $ = layui.jquery, layer = layui.layer, form = layui.form, upload = layui.upload;

	  //普通图片上传
	  var uploadInst = upload.render({
	    elem: '#test1'
	    ,url: 'https://httpbin.org/post' //改成您自己的上传接口
	    ,before: function(obj){
	      //预读本地文件示例，不支持ie8
	      obj.preview(function(index, file, result){
	        $('#demo1').attr('src', result); //图片链接（base64）
	      });
	    }
	    ,done: function(res){
	      //如果上传失败
	      if(res.code > 0){
	        return layer.msg('上传失败');
	      }
	      //上传成功
	    }
	    ,error: function(){
	      //演示失败状态，并实现重传
	      var demoText = $('#demoText');
	      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
	      demoText.find('.demo-reload').on('click', function(){
	        uploadInst.upload();
	      });
	    }
	  });
});
</script>
</body>
</html>