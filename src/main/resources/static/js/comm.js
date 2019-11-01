/**
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 */
var app = angular.module("mainApp", ['ui.bootstrap','ngFileUpload']);
app.controller("ctrl", function($scope,$http,$filter,Upload) {
    //请求url
    $scope.url="";
    //操作状态
    $scope.status="";
    //默认第一页
    $scope.currentPage=1;
    //每页10条
    $scope.rows=8;
    //弹窗标题
    $scope.title="信息";
    //初始化
    $scope.myinit = function(filter){
        showLoading();
        if(!filter){filter={}}
        filter.rows=$scope.rows;
        filter.page=$scope.currentPage;
        $http({method: "post",params:filter, url: loadUrl}).success(function (data) {
            hideLoading();
            if(data.status=="success"){
                //数据
                $scope.dataRows=data.dataRows;
                //总条数
                $scope.total = data.total;
            }else{
                showMsg("加载失败");
            }
        }).error(function(xhr){
            hideLoading();
            showMsg("加载失败");
            console.info(xhr);
        });
    }
    $scope.myinit();
    //翻页
    $scope.pageChanged = function() {
        $scope.myinit();
    }
    //新建
    $scope.addInfo= function(){
        $scope.status="add";
        $scope.url=addUrl;
        $scope.title="新建";
        $("#myForm").find('input,select').each(function() {
            $(this).val('');
            $(this).removeAttr("disabled");
        });
        if(fileName){
            $("#img").attr("src","");
        }
        if(editorName){//清除editor内容
            editor.$txt.html('<p><br></p>');
        }
        $('#myModal').modal({keyboard: true});
    }
    //修改
    $scope.editInfo= function (data) {
        $scope.status="edit";
        $scope.url=editUrl;
        $scope.title="修改";
        $("#myForm").setForm(data,false);
        if (editorName) {
            editor.$txt.html(data[editorName]);
        }
        if(fileName){
            if(data.cover){
                $("#img").attr("src",base+data.cover.filePath);
            }else if(data.icon){
                $("#img").attr("src",base+data.icon.filePath);
            }
        }
        $('#myModal').modal({keyboard: true});
    }
    //查看
    $scope.lookInfo= function (data) {
        $scope.status="";
        $scope.url="";
        $scope.title="查看";
        $("#myForm").setForm(data,true);
        if (editorName) {
            editor.$txt.html(data[editorName]);
        }
        if(fileName){
            if(data.cover){
                $("#img").attr("src",base+data.cover.filePath);
            }else if(data.icon){
                $("#img").attr("src",base+data.icon.filePath);
            }
        }
        $('#myModal').modal({keyboard: true});
    }
    //保存
    $scope.saveInfo= function(){
        if(!$scope.url){
            $('#myModal').modal("hide");
            return;
        }
        var jsonobject=$("#myForm").serializeJson();
        if (editorName) {
            jsonobject[editorName]=editor.$txt.html();
        }
        if(fileName){//有文件的添加
            showLoading();
            Upload.upload({method: 'post',url: $scope.url, file: $scope.file,data:jsonobject}).success(function (data) {
                $scope.myinit();
                hideLoading();
                $('#myModal').modal("hide");
                if(data.status=="success"){
                    showMsg($scope.title+"成功");
                }else{
                    showMsg($scope.title+"失败"+data.msg);
                }
            }).error(function (xhr) {
                hideLoading();
                $('#myModal').modal("hide");
                showMsg($scope.title+"失败");
                console.info(xhr);
            });
        }else{//普通添加
            showLoading();
            $http({method: "post",params:jsonobject,url:$scope.url}).success(function (data) {
                $scope.myinit();
                hideLoading();
                $('#myModal').modal("hide");
                if(data.status=="success"){
                    showMsg("操作成功");
                }else{
                    showMsg("操作失败"+data.msg);
                }
            }).error(function(xhr){
                hideLoading();
                $('#myModal').modal("hide");
                showMsg("操作失败");
                console.info(xhr);
            });
        }

    }
    //删除
    $scope.deleteInfo= function (data) {
        confirm("确认删除此条信息吗",function(){
            showLoading();
            $http({method: "post",params:{id:data.id},url:deleteUrl}).success(function (data) {
                hideLoading();
                if(data.status=="success"){
                    $scope.myinit();
                    showMsg("删除成功");
                }else{
                    showMsg("操作失败:"+data.msg);
                }
            }).error(function(xhr){
                hideLoading();
                showMsg("操作失败");
                console.info(xhr);
            });
        });
    }
    //批量删除
    $scope.deleteInfoes= function(){
        var notes= $('input[name="checkbox"]:checked');
        if(notes.length>0){
            confirm('确认批量删除'+notes.length+'条数据吗?',function() {
                var ids = [];
                $(notes).each(function (index, item) {
                    var id = item.value;
                    ids.push(id);
                });
                showLoading();
                $http({method: "post", params: {ids: ids}, url: deleteMoreUrl}).success(function (data) {
                    hideLoading();
                    if (data.status == "success") {
                        $scope.myinit();
                        showMsg("删除成功");
                        $('input[type="checkbox"]').removeAttr("checked", false);
                    } else {
                        showMsg("操作失败:" + data.msg);
                    }
                }).error(function (xhr) {
                    hideLoading();
                    showMsg("操作失败");
                    console.info(xhr);
                });
            });
        }else{
            showMsg("请选择数据")
        }
    }
    //预览图片
    $scope.preview= function(ele){
        if(ele.files &&ele.files[0]){
            var r= new FileReader();
            var f=ele.files[0];
            r.readAsDataURL(f);
            r.onload=function  (e) {
                document.getElementById('img').src=this.result;
            };
        }
    }
});

//批量选择
$('#checkAll').click(function(){
    if($(this).is(':checked')){
        $('input[name="checkbox"]').prop("checked",true);
    }else{
        $('input[name="checkbox"]').removeAttr("checked");
    }
});

/**
 * 将form里面的内容序列化成json
 * 相同的checkbox用分号拼接起来
 * @param {dom} 指定的选择器
 * @param {obj} 需要拼接在后面的json对象
 * @method serializeJson
 * */
$.fn.serializeJson=function(otherString){
    var serializeObj={}, array=this.serializeArray();
    $(array).each(function(){
        if(serializeObj[this.name]){
            serializeObj[this.name]+=';'+this.value;
        }else{
            serializeObj[this.name]=this.value;
        }
    });
    if(otherString!=undefined){
        var otherArray = otherString.split(';');
        $(otherArray).each(function(){
            var otherSplitArray = this.split(':');
            serializeObj[otherSplitArray[0]]=otherSplitArray[1];
        });
    }
    return serializeObj;
};


/**
 * 将json对象赋值给form $("#fomr").setForm(json,false);
 * @param jsonValue 需要给form赋值的json对象
 * @param readOnly  是否只读
 * @method serializeJson
 */
$.fn.setForm = function(jsonValue,readOnly){
    $.each(jsonValue,function(name,ival){
        name=name.replace("$$","");
        if(readOnly){
            $("[name="+name+"]").attr("disabled",true);
        }else {
            $("[name="+name+"]").removeAttr("disabled");
        }
        if(name.indexOf("Date")>=0){
            ival=new Date(ival).format('yyyy-MM-dd h:m:s');
        }
        var $oinput = $("input[name="+name+"]");
        if($oinput.attr("type")=="checkbox"){
            if(ival !== null){
                var checkboxObj = $("[name="+name+"]");
                var checkArray = ival.split(";");
                for(var i=0;i<checkboxObj.length;i++){
                    for(var j=0;j<checkArray.length;j++){
                        if(checkboxObj[i].value == checkArray[j]){
                            checkboxObj[i].click();
                        }
                    }
                }
            }
        } else if($oinput.attr("type")=="radio"){
            $oinput.each(function(){
                var radioObj = $("[name="+name+"]");
                for(var i=0;i<radioObj.length;i++){
                    if(radioObj[i].value == ival){
                        radioObj[i].click();
                    }
                }
            });
        } else if($oinput.attr("type")=="textarea"){
            $("[name="+name+"]").html(ival);
        } else{
            $("[name="+name+"]").val(ival);
        }
    });
}

//时间格式转换 var time = date.format('yyyy-MM-dd h:m:s');
Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
}

//loading start
function showLoading(){
    closeLayer();
    layer.load(0, {shade: [0.8, '#393D49']});
}

//loading end
function hideLoading(){
    layer.closeAll('loading'); //关闭加载层
}

//关闭最新弹出的层
function closeLayer(){
    layer.close(layer.index);
}

//弹出提示
function alert(msg){
    layer.alert(msg,{shade:0.001});
}

//弹出消息提示
function showMsg(msg){
    layer.msg(msg,{anim: 6});
}

//询问对话框 返回值boolean
function confirm(msg,callback) {
    layer.confirm(msg, {
        shade:0.01,
        btn : [ '确定', '取消' ]
    },callback);
}

//ajax get数据
function get(url,callback){
    $.get(url,function(data){
        callback && callback(data);
    });
}

//ajax post数据
function post(url,param,callback){
    $.post(url,param, function (data) {
        callback && callback(data);
    });
}
