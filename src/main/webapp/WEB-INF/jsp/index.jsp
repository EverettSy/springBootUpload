<%@page contentType="text/html; charset=utf-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/css/fileinput.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/fileinput.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.8/js/locales/zh.js"></script>
    <!--BaseFile组件-->
    <script src="../resources/common/js/base-file.js"></script>
    <script type="javascript">
        $("#uploadFile").file({
            title: "请上传附件",
            fileinput: {
                maxFileSize: 10240,
                maxFileCount:3
            },
            fileIdContainer:"[name='fileIds']",
            showContainer:'#attachment',
            //显示文件类型 edit=可编辑  detail=明细 默认为明细
            showType:'edit',
            //弹出窗口 执行上传附件后的回调函数(window:false不调用此方法)
            window:true,
            callback:function(fileIds,oldfileIds){
                //更新fileIds
                this.showFiles({
                    fileIds:fileIds
                });
            }
        });
        $("#attachment").file({
            fileinput: {
                maxFileSize: 10240,
                maxFileCount:3
            },
            fileIdContainer:"[name='fileIds']",
            window:false
        });
    </script>
    <title>文件上传</title>
</head>
<body>
单个文件上传：<br/>
<form action="/upload" method="post" enctype="multipart/form-data">
    <input type="hidden" name="fileIds" id="fileIds">
    <div class="form-group">
        <div class="btn btn-default btn-file" id="uploadFile">
            <i class="fa fa-paperclip"></i>上传附件（Max. 10MB）
        </div>
    </div>
    <div class="form-group" id="file_container">
        <input type="file" name="file"  id="attachment">
        <input type="file" name="file"/>
        <input type="submit" value="提交上传"/>
    </div>
</form>
<br/>
多个文件上传：
<form action="/uploads" method="post" enctype="multipart/form-data">
    文件1：<input type="file" name="file"/><br/>
    文件2：<input type="file" name="file"/><br/>
    文件3：<input type="file" name="file"/><br/>
    <input type="submit" value="上传多个文件"/>
</form>
</body>
</html>