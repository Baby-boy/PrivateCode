/// <reference path="plugin/jquery-1.12.4.js" />
/// <reference path="plugin/jquery.mCustomScrollbar.concat.min.js" />
/// <reference path="plugin/zTree/js/jquery.ztree.all-3.5.js" />
/// <reference path="baiduTemplate.js" />
/*
 * 通过组织架构选择人员的页面
 */
'use strict';
(function ($, window) {

    var GroupFramework = function (params) {
        var BASE_IMG_PATH = params.baseImgPath || "";
        var allUserData = params.user, //所有人员信息
            groupData = params.group;//住址架构信息

        allUserData.baseImgPath = BASE_IMG_PATH;

        var singleSelect = !!params.singleSelect; //是否单选
        var selectedInitUsers =params.selectedUsers ||[]; //初始化已经选择的人员
        var disabledInitUsers = params.disabledInitUsers || []; //禁用的人员
        var selectedCallback = params.selectedCallback ||$.noop; //选中人员点确定之后的回调
        var cancelCallback = params.cancelCallback ||$.noop; //取消的回调
        var isRequired = params.isRequired ||[]; //是否必须选一个人员

        //预设禁用的用户，把传入的引用用户从数组中删除
        allUserData.data = $.grep(allUserData.data, function (user) {
            return $.inArray(+user.id, disabledInitUsers)<0;
        });

        var bt = baidu.template;
        var allSelectUser,allSelectedUser;

        var UserManager = function () {
            var me = this;

            this.FILTER_CLASS = "group-user-filter";
            this.ACTIVE_CLASS = "group-user-active";
            this.SELECTED_CLASS = "group-user-selected";

            this.userList = $("#selectUserList");
            this.selectedUserList = $("#selectedUserList");

            this.getAllUserItems = function () {
                return this.userList.find(".group-user-item");
            }

            this.getAllSelectedUserItems = function () {
                return this.selectedUserList.find(".group-user-item");
            }

            this.getActiveUserItems = function () {
                var userItems = this.getAllUserItems();
                return userItems.filter(".group-user-active.group-user-filter:not(.group-user-selected)");
            }

            this.getActiveSelectedUserItems = function () {
                var selectedUserItems = this.getAllSelectedUserItems();
                return selectedUserItems.filter(".group-user-active.group-user-filter");
            }

            this.userList.html(bt('selectUserTemplate', allUserData));


            this.resetUserListStyle = function () {
                var userItems = this.getAllUserItems();
                userItems.removeClass(this.FILTER_CLASS);
                userItems.removeClass(this.ACTIVE_CLASS);
            }

            this.unactiveUserItems = function ($target) {
                if (!$target || $target.length === 0) {
                    var userItems = this.getAllUserItems();
                    $target = userItems.filter(".group-user-active");
                }

                $target.removeClass(this.ACTIVE_CLASS);
            }

            this.unactiveSelectedUserItems = function ($target) {
                if (!$target || $target.length === 0) {
                    var selectedUserItems = this.getAllSelectedUserItems();
                    $target = selectedUserItems.filter(".group-user-active");
                }

                $target.removeClass(this.ACTIVE_CLASS);
            }

            this.showUserItems = function ($target) {
                $target.addClass(this.FILTER_CLASS);
            }

            this.hideUserItems = function ($target) {
                $target.removeClass(this.FILTER_CLASS);
            }

            /*
             * 选择人员
             * 人员从左侧列表到右侧列表的动作
             */
            this.selectUsers = function ($target) {
                if (!$target || $target.length === 0) {
                    var userItems = me.getAllUserItems();
                    $target = userItems.filter(".group-user-active.group-user-filter:not(.group-user-selected)");
                }

                if (singleSelect) {
                    $target = $target.first();
                    me.cleanSelectUsers();
                }

                var userData = {
                    data: me.buildUserData($target),
                    baseImgPath: allUserData.baseImgPath
                };

                var $html =$(bt("selectedUserTemplate", userData));
                if(allSelectedUser && allSelectedUser.checked()){
                  $html.addClass("group-user-active");
                }

                var customContainer = me.selectedUserList.find(".mCSB_container");
                if (customContainer.length > 0) {
                    customContainer.append($html);
                } else {
                    me.selectedUserList.append($html);
                }

                $target.removeClass(this.ACTIVE_CLASS);
                $target.addClass(this.SELECTED_CLASS);
                return $target;
            }

            /*
             * 清除所有已选的人员
             * 清除所有已选的人员（无论是否单击选中），回到待选状态
             */
            this.cleanSelectUsers = function () {
                this.getAllUserItems().removeClass(this.SELECTED_CLASS);
                this.getAllSelectedUserItems().remove();
            }

            /*
             * 取消选中人员
             * 如果传参，就是传入的人员。如果不传参，就是所有已经被单击选中的人员
             */
            this.unselectUsers = function ($target) {
                if (!$target || $target.length === 0) {
                    var selectedUserItems = this.getAllSelectedUserItems();
                    $target = selectedUserItems.filter(".group-user-active.group-user-filter");
                }

                var userItems = this.getAllUserItems();
                $target.each(function () {
                    var $this = $(this);
                    var id = $this.data("id");
                    userItems.filter("[data-id='" + id + "']").removeClass(me.SELECTED_CLASS);
                });

                $target.remove();
            }

            this.buildUserData = function($users) {
                var users = $.grep(allUserData.data, function(user) {
                    var isExists = false;
                    $users.each(function() {
                        var $user = $(this);
                        var id = +$user.data("id") || 0;
                        if (id === user.id) {
                            isExists = true;
                            return false;
                        }
                    });

                    return isExists;
                });


                //$users.each(function () {
                //    var $user = $(this);
                //    var id = +$user.data("id");
                //    //var deptId = $user.data("deptId");
                //    //var avatar = $user.find("img").attr("src");
                //    //var realname = $user.find("span").text();
                //    //var letters = $user.data("letters");
                //    //var user = {
                //    //    "id": id,
                //    //    "deptId": deptId,
                //    //    "avatar": avatar,
                //    //    "realname": realname,
                //    //    "letters": letters
                //    //}

                //    users.push(user);
                //});
                return users;
            }

            //初始化用户列表，将已选的用户选中
            this.initSelectedUsers = function (users) {
                if (users && users.length > 0) {
                    $.each(users, function(i,v) {
                        me.selectUsers(me.getAllUserItems().filter("[data-id='" + v.id + "']"));
                    });
                }

            }

            me.initSelectedUsers(selectedInitUsers);
        }

        var DeptManager = function (params) {
            var groupTree = $("#groupTree");

            //组织架构树
            var setting = {
                view: {
                    dblClickExpand: false,
                    showLine: true,
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "parentId",
                        rootPId: ""
                    }
                },
                callback: {
                    onClick: function (event, treeId, treeNode) {
                        if (params.onDeptClick)
                            params.onDeptClick.call(groupTree, treeNode, event);
                    }
                }
            };

            //构建tree
            groupTree = $.fn.zTree.init(groupTree, setting, groupData);

            this.getChildDeptId = function (deptId) {
                var result = [];
                $.each(groupData, function (i, v) {
                    if (v.parentId === deptId) {
                        result.push(v.id);
                    }
                });

                return result;
            }

            this.getSelectedDepts = function () {
                var curDeptIds = [];
                $.each(groupTree.getSelectedNodes(), function (i, node) {
                    curDeptIds.push(node.id);
                });

                return curDeptIds;
            }


            this.selectFirst = function() {

                //如果节点数量大于1，选中第一个节点
                if (groupTree.getNodes().length > 0) {
                    var firstNode = groupTree.getNodes()[0];
                    groupTree.selectNode(groupTree.getNodes()[0]);
                    if (params.onDeptClick)
                        params.onDeptClick.call(groupTree, firstNode);
                }
            }
        }

        if (singleSelect) {
            $("#allUser").hide();
            $("#allSelectUser").hide();
        }

        var ARROW_ACTIVE_CLASS = "group-arrow-active";

        var userManager = new UserManager();

        allSelectUser = $("#allUser").checkAll({
            "text": "全选",
            "activeText": "取消全选",
            "activeClass": userManager.ACTIVE_CLASS,
            "checkbox": "#selectUserList .group-user-item.group-user-filter",
            "checked": false,
            "afterCheckAll": function() {
                checkAddBtn();
            },
            "beforeCheck": function() {
                if (singleSelect) {
                    userManager.unactiveUserItems();
                }
            },
            "afterCheck": function() {

                checkAddBtn();
            }
        });

           allSelectedUser = $("#allSelectUser").checkAll({
                "text": "全选",
                "activeText": "取消全选",
                "activeClass": userManager.ACTIVE_CLASS,
                "checkbox": "#selectedUserList .group-user-item.group-user-filter:not(.group-user-selected)",
                "checked": false,
                "afterCheckAll": function() {
                    checkRemoveBtn();
                },
                "beforeCheck": function () {
                    if (singleSelect) {
                        userManager.unactiveSelectedUserItems();
                    }
                },
                "afterCheck": function() {
                    checkRemoveBtn();
                }
            });

        var deptManager = new DeptManager({
            "onDeptClick": function () {
                filter();
                checkAddBtn();
                allSelectUser.reset();
            }
        });

        deptManager.selectFirst();

        function checkEmptyUserList() {
            //group-select-user-empty
            if (userManager.getAllUserItems().filter(":visible").length > 0) {
                userManager.userList.removeClass("group-select-user-empty");
                allSelectUser.css("visibility", "visible");

            } else {
                userManager.userList.addClass("group-select-user-empty");
                allSelectUser.css("visibility", "hidden");
            }

            if (userManager.getAllSelectedUserItems().filter(":visible").length > 0) {
                userManager.selectedUserList.removeClass("group-select-user-empty");
                  $("#btnOK").removeClass("pop-btn-disabled");
                  $("#btnOK").attr("disabled",false);
                allSelectedUser.css("visibility", "visible");
            } else {
                userManager.selectedUserList.addClass("group-select-user-empty");
                $("#btnOK").addClass("pop-btn-disabled");
                $("#btnOK").attr("disabled",true);
                allSelectedUser.css("visibility","hidden");
            }
        }

        function filter() {
            userManager.resetUserListStyle();
            var curKeywords = $.trim($("#txtSelectUser").val());
            var curDeptIds = deptManager.getSelectedDepts();

            filterInner(curKeywords, curDeptIds);

            checkEmptyUserList();
            allSelectUser.reset();
        }

        function filterInner(keywords, deptIds) {
            $.each(deptIds, function (i, deptId) {
                userManager.getAllUserItems().each(function () {
                    var $me = $(this);
                    var name = $me.find("span").text();
                    var curDeptId = +$me.data("deptid");
                    if (curDeptId === deptId && name.indexOf(keywords) >= 0) {
                        userManager.showUserItems($me);
                    }
                });

                var childDeptIds = deptManager.getChildDeptId(deptId);
                filterInner(keywords, childDeptIds);
            });

        }

        function checkAddBtn() {
            if (userManager.getActiveUserItems().length > 0) {
                $(".group-arrow-right").addClass(ARROW_ACTIVE_CLASS);
            } else {
                $(".group-arrow-right").removeClass(ARROW_ACTIVE_CLASS);
            }
        }

        function checkRemoveBtn() {
            if (userManager.getActiveSelectedUserItems().length > 0) {
                $(".group-arrow-left").addClass(ARROW_ACTIVE_CLASS);
            } else {
                $(".group-arrow-left").removeClass(ARROW_ACTIVE_CLASS);
            }
        }


        // $("#txtSelectUser").keydown(function (e) {
        //     if (util.keyHelper.isEnter(e)) {
        //         $(this).trigger("change");
        //     }
        // });

        var filterSelectTimer;
        $("#txtSelectUser").keyup(function (e) {
            var $me =$(this);
            clearTimeout(filterSelectTimer);
            filterSelectTimer = setTimeout(function(){
              $me.trigger("change");
            },300);
        });


        $("#txtSelectUser").change(function () {
            filter();
        });

        // $("#txtSelectedUser").keydown(function (e) {
        //     if (util.keyHelper.isEnter(e)) {
        //         $(this).trigger("change");
        //     }
        // });

        var filterSelectedTimer;
        $("#txtSelectedUser").keyup(function (e) {
            var $me =$(this);
            clearTimeout(filterSelectedTimer);
            filterSelectedTimer = setTimeout(function(){
              $me.trigger("change");
            },300);
        });

        $("#txtSelectedUser").change(function () {
            var keywords = $.trim($(this).val());
            userManager.getAllSelectedUserItems().each(function () {
                var $me = $(this);
                var name = $me.find("span").text();
                if (name.indexOf(keywords) >= 0) {
                    userManager.showUserItems($me);
                } else {
                    userManager.hideUserItems($me);
                }
            });

            allSelectedUser.reset(); //动态添加了元素，刷新全选事件
            checkEmptyUserList(); //检查搜索之后如果列表空了增加列表空样式
        });

        $(".group-search-ico").click(function () {
            $(this).parents(".group-search").find(".group-search-input").trigger("change");
        });

        $(".group-arrow-right").click(function() {
            userManager.selectUsers();

            allSelectUser.reset();
            allSelectedUser.reset(); //动态添加了元素，刷新全选事件

            checkAddBtn();
            checkEmptyUserList();
        });

        $(".group-arrow-left").click(function() {
            userManager.unselectUsers();

            allSelectedUser.reset(); //动态添加了元素，刷新全选事件

            checkRemoveBtn();
            checkEmptyUserList();
        });

        userManager.userList.on("click", ".group-user-operation", function () {
            var $target = $(this).parents("[data-id]");
            userManager.selectUsers($target);
            checkEmptyUserList();
        });

        userManager.selectedUserList.on("click", ".group-user-operation", function () {
            var $target = $(this).parents("[data-id]");
            userManager.unselectUsers($target);
            checkEmptyUserList();
        });

        //滚动条绑定
        $(".group-tree,.group-select-user .group-select-user-box,.group-selected-user .group-select-user-box").gwCustomScrollbar();


            $("#btnOK").click(function () {
                var result = userManager.buildUserData(userManager.getAllSelectedUserItems());

                    if (selectedCallback) {
                        selectedCallback(result);
                    }

            });

            $("#btnCancel").click(function () {
                  if (cancelCallback) {
                      cancelCallback();
                  }
            });
    }

    window.GroupFramework = GroupFramework;

})(jQuery, window);
