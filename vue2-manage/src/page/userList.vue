<template>
    <div class="fillcontain">
        <head-top></head-top>

        <el-row style="margin-top: 20px;">
        	<el-col :span="12" :offset="4">
                <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
                    <el-form-item label="分数" prop="score">
        				<el-input v-model.number="formData.score"></el-input>
        			</el-form-item>
        			<el-form-item class="button_submit">
        				<el-button type="primary" @click="getData(formData.score)">立即查询</el-button>
        			</el-form-item>
        		</el-form>
        	</el-col>
        </el-row>
        <div class="table_container">
            <el-table
                :data="tableData"
                highlight-current-row
                style="width: 100%">
                <el-table-column
                  type="index"
                  width="100">
                </el-table-column>
                <el-table-column
                  property="name"
                  label="学校名称"
                  width="220">
                </el-table-column>
                <el-table-column
                  property="score"
                  label="最低分数"
                  width="220">
                </el-table-column>
                <el-table-column
                  property="type"
                  label="性质">
                </el-table-column>
                <el-table-column
                  property="level"
                  label="分段">
                </el-table-column>
                <el-table-column
                  property="size"
                  label="分差">
         
<style lang="less">
	@import '../style/mixin';
    .table_container{
        padding: 20px;
    }
</style>
