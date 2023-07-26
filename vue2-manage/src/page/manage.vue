<template>
	<div class="manage_page fillcontain">
		<el-row style="height: 100%;">
	  		<el-col :span="4"  style="min-height: 100%; background-color: #20A0FF;">
				<el-menu :default-active="defaultActive" style="min-height: 100%;" theme="light" router>
					<el-menu-item index="manage"><i class="el-icon-menu"></i>功能列表</el-menu-item>

                    <!-- <el-submenu index="7">
                    	<template slot="title"><i class="el-icon-warning"></i>说明</template>
                    </el-submenu> -->

                    <el-submenu index="2">
						<template slot="title"><i class="el-icon-document"></i>高考助手</template>
						<el-menu-item index="userList">学校推荐</el-menu-item>
						<el-menu-item index="shopList">专业推荐</el-menu-item>
						<el-menu-item index="foodList">职业测试</el-menu-item>
						<el-menu-item index="orderList">志愿评估</el-menu-item>
					</el-submenu>
                    
					
				</el-menu>
			</el-col>
			<el-col :span="20" style="height: 100%;overflow: auto;">
				<keep-alive>
				    <router-view></router-view>
				</keep-alive>
			</el-col>
		</el-row>
  	</div>
</template>

<script>
    export default {
		computed: {
			defaultActive: function(){
				return "userList";
			}
		},
    }
</script>


<style lang="less" scoped>
	@import '../style/mixin';
	.manage_page{

	}
</style>
