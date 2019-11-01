//初始化编辑器
var editor;
$(function(){
    if(typeof(editorName) == 'undefined')editorName=null;
    if(editorName){
        editor= new wangEditor(editorName);
        editor.config.uploadImgUrl = base+'/upload/img';// 上传图片
        editor.config.emotions = {//定义表情
            'default': {
                title: '默认',
                data: base+'/static/js/wangEditor/emotions.data'
            }
        }
        editor.create();
    }
});

