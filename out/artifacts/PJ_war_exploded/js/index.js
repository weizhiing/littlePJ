/**
 * 
 */

var pageTotal=0;//总页数 
var rowTotal=0;//总行数 
var currentPage=0;//当前页数 
var startRow=0;//页开始行数 
var endRow=0;//页结束行数 
var pageSize=2;//每页行数 
   
function page(){ 
    $.ajax({ 
        url:"data.json", 
        type:"POST", 
        dataType:"json", 
        timeout:1000, 
        error:function(){ 
            alert("ajax error"); 
        }, 
        success:function(data){ 
            rowTotal=data.length; 
            pageTotal=Math.ceil(rowTotal/pageSize);//上取整 
            currentPage=1; 
            
            //绘制数据table 
            if(pageTotal==1){ 
                for(var i=0;i<pageSize;i++){ 
                    $("#table tbody").append( 
                    $("<tr><td>"+ 
                        data[i].name+ 
                        "</td><td>"+ 
                        data[i].stargazers_count+ 
                        "</td><td>"+ 
                        data[i].forks_count+ 
                        "</td><td>"+ 
                        data[i].description+ 
                        "</td></tr>") 
                    ); 
                } 
            }else{ 
                for(var i=0;i<pageSize;i++){ 
                    $("#table tbody").append( 
                    $("<tr><td>"+ 
                        data[i].name+ 
                        "</td><td>"+ 
                        data[i].stargazers_count+ 
                        "</td><td>"+ 
                        data[i].forks_count+ 
                        "</td><td>"+ 
                        data[i].description+ 
                        "</td></tr>") 
                    ); 
                } 
                
                          //绘制页面ul 
                for(var i=1;i<pageTotal+1;i++){ 
                    $("#page_ul").append( 
                        $("<li><a href='#'>"+i+"</a><li>") 
                    ); 
                } 
            } 
        } 
    }); 
} 
//翻页 
function gotoPage(pageNum){ 
    $.ajax({ 
        url:"data.json", 
        type:"POST", 
        dataType:"json", 
        timeout:1000, 
        error:function(){ 
            alert("ajax error"); 
        }, 
        success:function(data){ 
            currentPage=pageNum; 
            startRow=pageSize*(pageNum-1); 
            endRow=startRow+pageSize; 
            endRow=(rowTotal>endRow)?endRow:rowTotal; 
            $("#table tbody").empty(); 
            for(var i=startRow;i<endRow;i++){ 
                $("#table tbody").append( 
                    $("<tr><td>"+ 
                        data[i].name+ 
                        "</td><td>"+ 
                        data[i].stargazers_count+ 
                        "</td><td>"+ 
                        data[i].forks_count+ 
                        "</td><td>"+ 
                        data[i].description+ 
                        "</td></tr>") 
                    ); 
            } 
               
        } 
    }); 
} 
   
   
$(function(){ 
    page(); 
   
    $("#page_ul li").live("click",function(){ 
        var pageNum=$(this).text(); 
        gotoPage(pageNum); 
    }); 
   
    $("#page_prev li").live("click",function(){ 
        if(currentPage==1){ 
   
        }else{ 
            gotoPage(--currentPage); 
        } 
    }); 
   
    $("#page_next li").live("click",function(){ 
        if(currentPage==pageTotal){ 
   
        }else{ 
            gotoPage(++currentPage); 
        } 
    }) 
}); 