<template>
    <div class="fillcontain">
        <head-top></head-top>
        <div style="padding: 20px;">
            <div style="font-size: 20px;font-weight: bold;text-align: center;margin;20px">MBIT测试</div>
            <div style="color:gray;text-align: center;margin-bottom: 20px;">请凭直觉完成以下问卷，每个答案没有正确或者错误，有些题目可能两个答案都不完全符合你的情况，请选择比较靠近的一个答案。</div>
            <el-form :model="formData" :rules="rules" ref="formData" label-width="250px" class="demo-formData">
                <el-form-item v-for="(item,index) in examList" :key="index" :label="item.text">
                    <el-radio-group v-model="item.res">
                      <el-radio :label="item.res1"></el-radio>
                      <el-radio :label="item.res2"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item>
                  	<el-button v-if="btn" type="success" @click="sub()" class="submit_btn">提交</el-button>
                  </el-form-item>
            </el-form>
        </div>


        <div class="table_container">
            <el-table
                :data="tableData"
                @expand='expand'
                :expand-row-keys='expendRow'
                :row-key="row => row.index"
                style="width: 100%">

                <el-table-column
                  label="类型"
                  prop="type">
                </el-table-column>
                <el-table-column
                  label="分析"
                  prop="text">
                </el-table-column>
                <el-table-column
                  label="适合工作"
                  prop="job">
                </el-table-column>
                <el-table-column
                  label="适合专业"
                  prop="info">
                </el-table-column>
            </el-table>
            

        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {baseUrl, baseImgPath} from '@/config/env'
    import {getFoods, getFoodsCount, getMenu, updateFood, deleteFood, getResturantDetail, getMenuById} from '@/api/getData'
    export default {
        data(){
            return {
                btn:true,
                rules:[],
                formData:{},
                examList:[{
                text:'你倾向从何处得到力量',
                res1:'别人',
                res2:'自己的想法',
                res:''
                },
                {
                text:'当你参加一个社交聚会的时候，你会',
                res1:'在夜晚刚开始的时候，我就疲倦了并且想回家',
                res2:'在夜色很深时，一旦你开始投入，也许就是离开最晚的那个',
                res:''
                },
                {
                text:'下列哪一件事听起来比较吸引你',
                res1:'待在家中与恋人/爱人做一些特别的事情，例如欣赏一部有趣的电影',
                res2:'与恋人/爱人到有很多人且社交活动频繁的地方',
                res:''
                },
                {
                text:'在平常的约会中，你通常：',
                res1:'整体来说很健谈',
                res2:'较安静、不喜欢展现自己',
                res:''
                },
                {
                text:'过去，你遇见大部分的朋友是：',
                res1:'在聚会中、工作上、休闲活动、会议上或当朋友介绍我给他们的朋友',
                res2:'通过私人方式，譬如亲密的朋友和家人介绍',
               '请输入规格', trigger: 'blur' },
					],
		        },
		        specsFormVisible: false,
                expendRow: [],
            }
        },
        created(){
        	this.restaurant_id = this.$route.query.restaurant_id;
            this.initData();
        },
        computed: {
        	specs: function (){
        		let specs = [];
        		if (this.selectTable.specfoods) {
	        		this.selectTable.specfoods.forEach(item => {
	        			specs.push({
	        				specs: item.specs_name,
	        				packing_fee: item.packing_fee,
	        				price: item.price,
	        			})
	        		})
        		}
        		return specs
        	}
        },
    	components: {
    		headTop,
    	},
        methods: {
            sub(){
                this.tableData = [];

                let subCheck = true;
                this.examList.map(function(item,index){
                    if(item.res==""){
                        subCheck = false;
                    }
                })
                if(subCheck){

                    this.$message({
                        type: 'success',
                        message: "已完成，已给出结果"
                    });


                    let element = this.resList[Math.floor((Math.random()*this.resList.length))];

                    this.tableData = [element];
                    this.btn = false;
                }else{
                    this.$message({
                        type: 'error',
                        message: "请完成全部题目"
                    });
                }
            },
            async initData(){
                try{
                    const countData = await getFoodsCount({restaurant_id: this.restaurant_id});
                    if (countData.status == 1) {
                        this.count = countData.count;
                    }else{
                        throw new Error('获取数据失败');
                    }
                    this.getFoods();
                }catch(err){
                    console.log('获取数据失败', err);
                }
            },
            async getMenu(){
            	this.menuOptions = [];
                try{
                    const menu = await getMenu({restaurant_id: this.selectTable.restaurant_id, allMenu: true});
                    menu.forEach((item, index) => {
                        this.menuOptions.push({
                        	label: item.name,
                        	value: item.id,
                        	index,
                        })
                    })
                }catch(err){
                    console.log('获取食品种类失败', err);
                }
            },
            async getFoods(){
                const Foods = await getFoods({offset: this.offset, limit: this.limit, restaurant_id: this.restaurant_id});
                this.tableData = [];
                Foods.forEach((item, index) => {
                    const tableData = {};
                    tableData.name = item.name;
                    tableData.item_id = item.item_id;
                    tableData.description = item.description;
                    tableData.rating = item.rating;
                    tableData.month_sales = item.month_sales;
                    tableData.restaurant_id = item.restaurant_id;
                    tableData.category_id = item.category_id;
                    tableData.image_path = item.image_path;
                    tableData.specfoods = item.specfoods;
                    tableData.index = index;
                    this.tableData.push(tableData);
                })
            },
            tableRowClassName(row, index) {
		        if (index === 1) {
		        	return 'info-row';
		        } else if (index === 3) {
		        	return 'positive-row';
		        }
		        return '';
		    },
		    addspecs(){
				this.specs.push({...this.specsForm});
				this.specsForm.specs = '';
				this.specsForm.packing_fee = 0;
				this.specsForm.price = 20;
				this.specsFormVisible = false;
			},
			deleteSpecs(index){
				this.specs.splice(index, 1);
			},
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.offset = (val - 1)*this.limit;
                this.getFoods()
            },
            expand(row, status){
            	if (status) {
            		this.getSelectItemData(row)
            	}else{
                    const index = this.expendRow.indexOf(row.index);
                    this.expendRow.splice(index, 1)
                }
            },
            handleEdit(row) {
            	this.getSelectItemData(row, 'edit')
                this.dialogFormVisible = true;
            },
            async getSelectItemData(row, type){
            	const restaurant = await getResturantDetail(row.restaurant_id);
            	const category = await getMenuById(row.category_id)
                this.selectTable = {...row, ...{restaurant_name: restaurant.name, restaurant_address: restaurant.address, category_name: category.name}};

                this.selectMenu = {label: category.name, value: row.category_id}
                this.tableData.splice(row.index, 1, {...this.selectTable});
                this.$nextTick(() => {
                    this.expendRow.push(row.index);
                })
                if (type == 'edit' && this.restaurant_id != row.restaurant_id) {
                	this.getMenu();
                }
            },
            handleSelect(index){
            	this.selectIndex = index;
            	this.selectMenu = this.menuOptions[index];
            },
            async handleDelete(index, row) {
                try{
                    const res = await deleteFood(row.item_id);
                    if (res.status == 1) {
                        this.$message({
                            type: 'success',
                            message: '删除食品成功'
                        });
                        this.tableData.splice(index, 1);
                    }else{
                        throw new Error(res.message)
                    }
                }catch(err){
                    this.$message({
                        type: 'error',
                        message: err.message
                    });
                    console.log('删除食品失败')
                }
            },
            handleServiceAvatarScucess(res, file) {
                if (res.status == 1) {
                    this.selectTable.image_path = res.image_path;
                }else{
                    this.$message.error('上传图片失败！');
                }
            },
            beforeAvatarUpload(file) {
                const isRightType = (file.type === 'image/jpeg') || (file.type === 'image/png');
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isRightType) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isRightType && isLt2M;
            },
            async updateFood(){
                this.dialogFormVisible = false;
                try{
                	const subData = {new_category_id: this.selectMenu.value, specs: this.specs};
                	const postData = {...this.selectTable, ...subData};
                    const res = await updateFood(postData)
                    if (res.status == 1) {
                        this.$message({
                            type: 'success',
                            message: '更新食品信息成功'
                        });
                        this.getFoods();
                    }else{
                        this.$message({
                            type: 'error',
                            message: res.message
                        });
                    }
                }catch(err){
                    console.log('更新餐馆信息失败', err);
                }
            },
        },
    }
</script>

<style lang="less">
	@import '../style/mixin';
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
    .table_container{
        padding: 20px;
    }
    .Pagination{
        display: flex;
        justify-content: flex-start;
        margin-top: 8px;
    }
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }
    .avatar-uploader .el-upload:hover {
        border-color: #20a0ff;
    }
    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 120px;
        height: 120px;
        line-height: 120px;
        text-align: center;
    }
    .avatar {
        width: 120px;
        height: 120px;
        display: block;
    }
</style>
