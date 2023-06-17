# 仓库管理系统

#### 技术栈
后端：springboot+springSecurity+MybatisPlius+Redis+websocket
前端：vue+elementui+echarts+vuex+websocket

#### 系统介绍
基于B/S的仓库管理系统，本系统基于客户->订单->生产->待检->入库->盘点->出库这条企业管理链路开发。整合了springSecurity实现了，用户的认证和权限的管理，整合了Redis在权限认证时使用的redis缓存了权限的用户角色权限的数据。通过MybatisPlus大大提高了了工作效率。使用了快递鸟的第三方接口实现了物流跟踪（免费使用只支持中通、圆通、申通）。

#### 项目快照
##### 登录页
![login](https://images.gitee.com/uploads/images/2020/0528/195524_354c62ff_4855683.jpeg "login.JPG")
##### websocket
![websocket](https://images.gitee.com/uploads/images/2020/0528/195418_b54da6ac_4855683.jpeg "com.JPG")
##### Home
![主页](https://images.gitee.com/uploads/images/2020/0422/223431_3e8f784f_4855683.jpeg "welcome.JPG")
##### 个人中心
![个人中心](https://images.gitee.com/uploads/images/2020/0422/223519_8e07b234_4855683.jpeg "user.JPG")
##### 用户列表
![输入图片说明](https://images.gitee.com/uploads/images/2020/0422/223613_adab8b51_4855683.jpeg "userM.JPG")
##### 角色
![角色列表](https://images.gitee.com/uploads/images/2020/0422/223640_bdb33caa_4855683.jpeg "role.JPG")
##### 分配权限
![分配权限](https://images.gitee.com/uploads/images/2020/0422/223724_db8e06c3_4855683.jpeg "assP.JPG")
##### 订单
![订单](https://images.gitee.com/uploads/images/2020/0422/223913_4937eeba_4855683.jpeg "order.JPG")
##### 生产线
![生产线](https://images.gitee.com/uploads/images/2020/0422/223746_0587de64_4855683.jpeg "output.JPG")
##### 我的仓库
![仓库](https://images.gitee.com/uploads/images/2020/0422/223851_2b0ebcec_4855683.jpeg "warehouse.JPG")
##### 入库管理
![入库](https://images.gitee.com/uploads/images/2020/0422/223948_ac46be87_4855683.jpeg "enter.JPG")



