<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
        	<el-col :span="12" :offset="4">
                <el-form  :model="formData" ref="formData" label-width="110px" class="demo-formData">
        			<el-form-item label="高考总分" >
        				<el-input v-model.number="formData.score" maxLength="3"></el-input>
        			</el-form-item>
                    <el-form-item label="报考学校"  >
                        <el-select :filter-method="filter" filterable style="width: 470px;" v-model="selVal" placeholder="请选择学校">

                          <el-option v-for="(item,index) in schoolList" :key="index" :label="item.name+'，最低分'+item.score" :value="item.name+'-'+item.score"></el-option>
                        </el-select>
                      </el-form-item>
                    <el-form-item label="报考专业" >
                        <el-select style="width: 470px;" v-model="formData.job" placeholder="请选择专业">
                          <el-option   v-for="(item,index) in jobList" :key="index" :label="item" :value="item"></el-option>

                        </el-select>
                      </el-form-item>
                    <el-form-item class="button_submit">
                    	<el-button type="primary" @click="getData()">评估</el-button><el-button plain @click="selVal='';formData.score = '';formData.job='';tableData=[];" class="submit_btn">清除</el-button>

                    </el-form-item>
                    <el-form-item>
                    	</el-form-item>
                </el-form>
            </el-col>
        </el-row>

        <div class="table_container">
            <el-table
			    :data="tableData"

                :row-key="row => row.index"
			    style="width: 100%">

			    <el-table-column
			      label="分析"
			      prop="text">
			    </el-table-column>
                <el-table-column
                  label="报考难度"
                  prop="hard">
                </el-table-column>
			</el-table>

        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {schoolByScore} from '@/api/getData'
    export default {
        data(){
            return {
                copy: [],
                rules:[],
                selVal:"",
                formData:{
                    score:"",
                    job:"",
                    school:'',
                    size:''
                },
                schoolList:[],
                jobList:[
                    "工商管理","旅游管理","金融学","计算机科学与技术专业","电子信息工程学","电气工程及其自动化专业","软件工程专业，自动化专业","金融学专业","通信工程专业","会计学专业","英语专业","汉语言文学专业"
                ],
                tableData: [],
                currentRow: null,
                offset: 0,
                limit: 20,
                count: 0,
                currentPage: 1,
                restaurant_id: null,
                expendRow: [],
            }
        },
    	components: {
    		headTop,
    	},
        async created(){
        	this.restaurant_id = this.$route.query.restaurant_id;
            //this.initData();
            this.getSchool();
        },
        mounted(){

        },
        methods: {
            filter(v) {
                //对绑定数据赋值
                this.schoolList = this.copy.filter((item) => {
                    //如果直接包含输入值直接返回true
                    if (item.name.indexOf(v) !== -1) return true;
                })
            },
            async getSchool(){
                let score = this.formData.score;
                if(!score){
                    score = 750;
                }
                const res = await schoolByScore({score:score});
                if (res.code == 200) {

                    res.data.map(function(item,index,arr){
                        item['size'] = parseInt(score) - item.score;
                    })
                    this.schoolList = res.data;
                    this.copy = Object.assign(this.schoolList);
                }else{
                    throw new Error('获取数据失败')
                    this.$message({
                        type: 'error',
                        message: res.msg
                    });
                }
            },getData(){
                if(!this.formData.score || !this.selVal ||!this.formData.job){
                    this.$message({
                        type: 'error',
                        message: "请填写完整"
                    });
                    return;
                }
               this.tableData = [];
                let name = this.selVal.split("-");
                let low = parseInt(name[1]);
                name = name[0];
                let text = "";
                let hard = ""
                if(Math.abs(this.formData.score - low) <= 10){
                    text = "你志愿报考的"+name+this.formData.job+"相对你的分数来说有一定难度，你的分数和往年最低录取分数"+low+"相差不大，可能会受到今年招生情况浮动影响较大，建议更换学校报考。如看好此志愿，建议报考小语、古生物、地质等冷门专业，并服从调剂。";
                    hard = "较难"

                }else if(this.formData.score - low > 10 && this.formData.score - low < 20){
                    text = "你志愿报考的"+name+this.formData.job+"相对你的分数来说比较有把握，你的分数超出往年最低录取分数一定水平，不过有一定可能会受到今年招生情况浮动影响，保险起见可服从调剂。";
                    hard = "普通"
                }else if(this.formData.score - low >= 20){
                    text = "你志愿报考的"+name+this.formData.job+"相对你的分数来说很有把握，你的分数超出往年最低录取分数较多，几乎不会出现意外。";
                    hard = "容易"
                }else if(this.formData.score - low < -10){
                    text = "你志愿报考的"+name+this.formData.job+"相对你的分数来说不太现实，你的分数和往年最低录取分数"+low+"相差很大，建议更换学校报考。";
                    hard = "极难"
                }
                this.tableData = [{text:text,hard:hard}];
            }

        },
    }
</script>

<style lang="less">
	@import '../style/mixin';
    .table_container{
        padding: 20px;
    }
    .demo-table-expand {
	    font-size: 0;
	}
	.demo-table-expand label {
	    width: 90px;
	    color: #99a9bf;
	}
	.demo-table-expand .el-form-item {
	    margin-right: 0;
	    margin-bottom: 0;
	    width: 50%;
	}
</style>
