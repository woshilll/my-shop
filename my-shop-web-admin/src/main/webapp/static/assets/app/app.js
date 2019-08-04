let App = function(){
    let _checkboxMaster;
    let _checkbox;
    let _idArray;
    let defaultDropZoneOpts = {
        url: "", // 文件提交地址
        method: "post",  // 也可用put
        paramName: "dropFile", // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

    /**
     * 多选框功能
     */
    let handlerInitChecked = function(){
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        _checkboxMaster=$('input[type="checkbox"].minimal.check_master');
        _checkbox=$('input[type="checkbox"].minimal')
    };
    let checkAll = function () {
        _checkboxMaster.on("ifClicked",function (e) {
            if (e.target.checked) {
                //全不选
                _checkbox.iCheck("uncheck")
            }else {
                //全选
                _checkbox.iCheck("check")
            }
        })
    };
    /**
     * 批量删除功能
     * @param url
     */
    let handlerBatchDelete = function (url) {
        _idArray=new Array();
        _checkbox.each(function () {
            let _id=$(this).attr('id');
            if (_id != null && _id != 'undefined' && $(this).is(':checked')) {
                _idArray.push(_id);
            }
        });
        //判断是否选中选项
        if (_idArray.length === 0){
            $('#modal-message').html("最少要选择一项数据哦(｀⌒´メ)")
        }else {
            $('#modal-message').html("你确定要删除这些吗？")
        }
        $('#modal-default').modal('show');
        
        $('#btnModalOk').bind("click",function () {
            handlerDelete(_idArray , url);
        });
    };
    /**
     * 单个删除功能实现
     * @param url
     * @param id
     */
    let handlerSingleDelete = function (url , id , msg) {
        msg = msg || "你确定要删除它吗QAQ？";
        _idArray = new Array();
        _idArray.push(id);
        $('#modal-message').html(msg);
        $('#modal-default').modal('show');
        $('#btnModalOk').bind("click",function () {
            handlerDelete(_idArray , url);
        });
    };
    /**
     * 删除功能实现
     * @param _idArray ID数组
     * @param url 删除路径
     */
    let handlerDelete = function (_idArray , url){
        $('#modal-default').modal('hide');
        if (_idArray.length === 0){

        }
        else {
            setTimeout(function () {
                $.ajax({
                    'url':url,
                    'type':'post',
                    'data':{'ids':_idArray.toString()},
                    'dataType':'JSON',
                    'success':function (data) {
                        //解绑操作
                        $('#btnModalOk').unbind('click');
                        if (data.status === 200){
                            //重新绑定按钮点击事件，并加载页面
                            $('#btnModalOk').bind('click',function () {
                                window.location.reload();
                            });

                        }else {
                            //删除失败，隐藏莫太狂
                            $('#btnModalOk').bind('click',function () {
                                $('#modal-default').modal('hide');
                            });

                        }
                        //显示莫太狂，并添加信息
                        $('#modal-message').html(data.message);
                        $('#modal-default').modal('show');
                    }
                });
            },500)

        }
    };



    /**
     * 分页数据功能
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    let handlerDataTables = function (url , columns) {
        let x = new Array();
        for (let i = 1; i < columns.length -1; i++) {
            x.push(i)
        }
        let dataTable=$('#dataTables').DataTable({
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'excel',
                    exportOptions: {
                        columns: x,

                    },
                }
            ],
            'paging':true,
            'info':true,
            'lengthChange':false,
            'ordering':false,
            'processing':true,
            'searching':false,
            'serverSide':true,
            'deferRender':true,
            'ajax':{
                'url':url
            },
            'columns':columns,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            'drawCallback':function (settings) {
                handlerInitChecked();
                checkAll();
            }
        });
        let excel = $('.dt-button');
        excel.attr('id','export');
        excel.css('display','none')

        return dataTable;
    };
    /**
     * 详情信息展示功能
     * @param url
     */
    let handlerShowDetail = function (url) {
        $.ajax({
            'url':url,
            'type':'get',
            'dataType':'html',
            'success':function (data) {
                $('#modal-body-detail').html(data);
                $('#modal-detail').modal('show')
            }
        })
    };

    /**
     * 分类的树结构，使用了JQuery插件ztree
     * @param url 异步路径
     * @param autoParam 传入的参数
     * @param callback 回调函数
     */
    let handlerMyTree = function (url , autoParam , callback) {
        let setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                url: url,
                autoParam: autoParam,
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        //莫太狂的确认按钮绑定点击事件，
        $('#btnModalOk').bind('click' , function () {
            let zTree = $.fn.zTree.getZTreeObj("myTree");
            let nodes = zTree.getSelectedNodes();
            if (nodes.length == 0) {
                alert("请先选择一个父节点");
            }else {
                //回调函数
                callback(nodes);
            }
        })
    };

    /**
     * 文件上传
     * @param opts 主要传url id等参数 ，也可以修改默认参数
     */
    let handlerInitDropZoneUpload = function (opts) {
        Dropzone.autoDiscover=false;
        //defaultDropZoneOpts 继承 opts
        $.extend(defaultDropZoneOpts , opts);
        new Dropzone(defaultDropZoneOpts.id , defaultDropZoneOpts)
    };

    return{
        /**
         * 初始化多选框
         */
        init:function(){
            handlerInitChecked();
            checkAll();
        },
        /**
         * 初始化批量删除
         * @param url 指定路径
         */
        batchDelete:function (url) {
            handlerBatchDelete(url);
        },
        /**
         * 初始化分页表
         * @param url 异步地址
         * @param columns 列
         * @returns {jQuery}
         */
        initDataTables:function (url , columns) {
           return  handlerDataTables(url,columns)
        },
        /**
         * 详情信息展示
         * @param url
         */
        showDetail:function (url) {
            handlerShowDetail(url);
        },
        /**
         * 树结构
         * @param url
         * @param autoParam
         * @param callback
         */
        initMyTree:function (url , autoParam , callback) {
            handlerMyTree(url , autoParam , callback);
        },
        /**
         * 文件上传
         * @param opts
         */
        initDropZoneUpload:function (opts) {
            handlerInitDropZoneUpload(opts);
        },
        /**
         * 初始化单个删除功能
         * @param url
         * @param id
         */
        initSingleDelete:function (url , id , msg) {
            handlerSingleDelete(url , id , msg);
        }
    }
}();
$(document).ready(function(){
    App.init();
});