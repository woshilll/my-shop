let App = function(){
    let _checkboxMaster;
    let _checkbox;
    let _idArray;

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
            del();
        });
        //删除功能
        function del() {
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
        }
    };


    /**
     * 分页数据功能
     * @param url
     * @param columns
     * @returns {jQuery}
     */
    let handlerDataTables = function (url , columns) {
        let dataTable=$('#dataTables').DataTable({
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
        })

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
    }

    return{
        init:function(){
            handlerInitChecked();
            checkAll();
        },
        batchDelete:function (url) {
            handlerBatchDelete(url);
        },
        initDataTables:function (url , columns) {
           return  handlerDataTables(url,columns)
        },
        showDetail:function (url) {
            handlerShowDetail(url);
        }
    }
}();
$(document).ready(function(){
    App.init();
});