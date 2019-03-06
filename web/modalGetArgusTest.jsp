<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*,Bean.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<title>课程详情</title>

</head>

<body>
	<input type="hidden" name="getid" id="getid" value="123" />
	<input type="hidden" name="getdepid" id="getdepid" value="456" />
	
	<button class="btn" data-toggle="modal" data-target="#myModal" onclick="transmit()" >不通过</button>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    请说明不通过原因
                </h4>
            </div>
            <form action="__URL__/disagree" method="POST">
            <div class="modal-body" align="center">
                <input type="text" name="remark" placeholder="请输入" style="width:520px; height:45px;" />
 
                <input type="text" name="id" id="id" value="" />
                <input type="text" name="depid" id="depid" value="" />
 
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="submit" class="btn btn-primary">
                    确定
                </button>
            </div>
        </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>




	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
<script type="text/javascript">
    $("#myModal").modal("hide");//加载页面首先隐藏模态框，指向模态框的ID
    function transmit(){
        $("#myModal").modal("show");//显示模态框
        var id = document.getElementById("getid").value;    //获取所需传递的参数id
        var depid = document.getElementById("getdepid").value;   //获取所需传递的参数depid
        
        //向模态框中传值
        $('#id').val(id);  
        $('#depid').val(depid);  
    }
</script>
</body>
</html>