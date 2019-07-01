$(function () {
    $('#dg').datagrid({
        //一个用以从远程站点请求数据的超链接地址。
        url:'getUsers',
        //设置为true将自动使列适应表格宽度以防止出现水平滚动。
        fitColumns: true,
        //设置true将在数据表格底部显示分页工具栏。
        pagination: true,
        //当设置分页属性时，初始化每页记录数列表。
        pageList: [5, 10, 15, 20],
        //数据表格顶部面板的工具栏。
        toolbar:"#ToolBar",
        pageSize:20,
        columns: [[
            {field:'ck',checkbox:true},  //复选框列
            { field: 'id', title: '编号', width: 50, align: "center" },
            { field: 'name', title: '类型', width: 50, align: "center" },
            {field:'opt',title:'操作', width:80, align: "center",
                formatter: function(value,row,index){
                    return "<input type='button' value='删除' onclick='deltetById("+row.id+")'>";
                }
            }

        ]]
    });
})
function Add() {
    // alert("打开窗口");
    $("#AddDialog").dialog("open").dialog('setTitle',"添加类型");
}

//关闭添加窗口
function CloseDialog(obj){
    $("#"+obj).dialog("close");
}
//保存
function SaveDialog() {
    $('#ModiyDialogForm').form('submit',{
        url:"addtype",
        success:function (date) {
            var obj = $.parseJSON(date);
            if(obj.result>0){
                $("#AddDialog").dialog("close");  //关闭
                //alert("添加成功");
                $('#dg').datagrid('reload');
                $.messager.alert('提示框','恭喜添加成功!');

            }else {
                $.messager.alert('提示框','添加失败');
            }
        }
    });
}
function ModifyBySelect() {
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length!=1){
        $.messager.alert('提示框','您没有选中数据或选择多行数据');
        return;
    }
    var selectRow = SelectRows[0];
    $("#UpdateDialog").dialog('open').dialog('setTitle','修改窗口');
    $("#ModiyDialogForm1").form('load',selectRow);
}
function Up() {
    alert("123");
    $('#ModiyDialogForm1').form('submit',{
        url:"updateType",
        success:function (date) {
            var obj = $.parseJSON(date);
            if(obj.result>0){
                $('#dg').datagrid('reload');
                $("#UpdateDialog").dialog("close");  //关闭
                $.messager.alert('提示框','恭喜修改成功!');

            }else {
                $("#UpdateDialog").dialog("close");
                $.messager.alert('提示框','修改失败');
            }
        }
    });
}
//删除单条
function deltetById(id) {
    $.messager.confirm('提示框', '确定要把我送到火星吗？', function(result){
        if (result){
            $.post("deltetById",{"id":id},
                function(data){
                    $('#dg').datagrid('reload');
                }, "json");
            // exit action;
        }
    });
}
function Delete1() {
    //获取选中行
    var SelectRows = $("#dg").datagrid('getSelections');
    if(SelectRows.length==0){
        $.messager.alert("提示标题","亲，你还没用选中行");
        return
    }
    var delValue="";
    for (var i = 0; i <SelectRows.length ; i++) {
        delValue=delValue+SelectRows[i].id+",";
    }
    delValue= delValue.substring(0,delValue.length-1);
    $.messager.confirm("提示","确定删除"+SelectRows.length+"行",function (r) {
        if(r){
            $.post("deltetUsers",{"ids":delValue},function (data) {
                if(data.result>0){
                    $("#dg").datagrid('reload'); //刷新
                    //alert("添加成功");
                    $.messager.alert('提示框','恭喜你成功删除'+data.result+'行!');
                }
                else
                {
                    $.messager.alert('提示框','系统正在维护!');
                }
            },"json")
        }
    })
}
/*实现查询*/
// function searchUser() {
//     alert(123);
//         var $telephone = $("#tel").val();
//         var $ageFrom = $("#ageFrom").val();
//         var $ageTo = $("#ageTo").val();
//         $("#dg").dialog("load",{"telephone":$telephone,"ageFrom":$ageFrom,"ageTo":$ageTo});
// }
  function searchUser() {
      var $telephone = $("#tel").val();
      alert($telephone);
        var $ageFrom = $("#ageFrom").val();
        var $ageTo = $("#ageTo").val();
  }