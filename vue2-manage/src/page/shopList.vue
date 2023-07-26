<template>
    <div class="fillcontain">
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
        	<el-col :span="12" :offset="4">
                <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
        			<el-form-item label="语文" prop="chinese">
        				<el-input v-model.number="formData.chinese" maxLength="11"></el-input>
        			</el-form-item>
                    <el-form-item label="数学" prop="math">
        				<el-input v-model="formData.math"></el-input>
        			</el-form-item>
        			<el-form-item label="英语" prop="english">
        				<el-input v-model="formData.english"></el-input>
        			</el-form-item>
                    <el-form-item label="政治" prop="zz">
                    	<el-input v-model="formData.zz"></el-input>
                    </el-form-item>
                    <el-form-item label="历史" prop="his">
                    	<el-input v-model="formData.his"></el-input>
                    </el-form-item>
                    <el-form-item label="化学" prop="hx">
                    	<el-input v-model="formData.hx"></el-input>
                    </el-form-item>
        			<el-form-item label="物理" prop="wl">
        				<el-input v-model="formData.wl"></el-input>
        			</el-form-item>
                    <el-form-item label="生物" prop="sw">
                    	<el-input v-model="formData.sw"></el-input>
                    </el-form-item>
        			<el-form-item class="button_submit">
        				<el-button type="primary" @click="getData()">评估</el-button>
        			</el-form-item>
        		</el-form>
        	</el-col>
        </el-row>
        <div class="table_container">
                <el-table
                    :data="tableData"
                    highlight-current-row
                    style="width: 600px">
                    <el-table-column
                      type="index"
                      width="100">
                    </el-table-column>
                    <el-table-column
                      property="name"
                      label="适合专业"
                      width="500">
                    </el-table-column>
                </el-table>
\
            <!-- <el-dialog title="修改店铺信息" v-model="dialogFormVisible">
                <el-form :model="selectTable">
                    <el-form-item label="店铺名称" label-width="100px">
                        <el-input v-model="selectTable.name" auto-complete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="详细地址" label-width="100px">
                        <el-autocomplete
                          v-model="address.address"
                          :fetch-suggestions="querySearchAsync"
                          placeholder="请输入地址"
                          style="width: 100%;"
                          @select="addressSelect"
                        ></el-autocomplete>
                        <span>当前城市：{{city.name}}</span>
                    </el-form-item>
                    <el-form-item label="店铺介绍" label-width="100px">
                        <el-input v-model="selectTable.description"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话" label-width="100px">
                        <el-input v-model="selectTable.phone"></el-input>
                    </el-form-item>
                    <el-form-item label="店铺分类" label-width="100px">
                        <el-cascader
                          :options="categoryOptions"
                          v-model="selectedCategory"
                          change-on-select
                        ></el-cascader>
                    </el-form-item>
                    <el-form-item label="商铺图片" label-width="100px">
                        <el-upload
                          class="avatar-uploader"
                          :action="baseUrl + '/v1/addimg/shop'"
                          :show-file-list="false"
                          :on-success="handleServiceAvatarScucess"
                          :before-upload="beforeAvatarUpload">
                          <img v-if="selectTable.image_path" :src="baseImgPath + selectTable.image_path" class="avatar">
                          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="updateShop">确 定</el-button>
              </div>
            </el-dialog> -->
        </div>
    </div>
</template>

<script>
    import headTop from '../components/headTop'
    import {baseUrl, baseImgPath} from '@/config/env'
    import {cityGuess, getResturants, getResturantsCount, foodCategory, updateResturant, searchplace, deleteResturant} from '@/api/getData'
    export default {
        data(){
            return {
                jobList:[
                    "计算机科学与技术专业","电子信息工程学","电气工程及其自动化专业","软件工程专业，自动化专业","金融学专业","通信工程专业","会计学专业","英语专业","汉语言文学专业"
                ],
                tableData:[],
                formData:{
                    chinese:"",
                    math:"",
                    english:"",
                    zz:"",
                    his:"",
                    hx:"",
                    sw:"",
                    wl:"",
                    dl:""
                },
                baseUrl,
                baseImgPath,
                city: {},
                offset: 0,
                limit: 20,
                count: 0,
                tableData: [],
                currentPage: 1,
                selectTable: {},
                dialogFormVisible: false,
                categoryOptions: [],
                selectedCategory: [],
                address: {},
                rules:[]
            }
        },
        created(){
            this.initData();
            console.log(11111)
        },
    	components: {
    		headTop,
    	},
        methods: {
            getData(){
                let res = false;
                for(let key in this.formData){
                    if(this.formData[key]){
                        res = true;
                    }
                }
                if(res){
                    this.tableData = [{name:this.getRes()}];
                }else{
                    this.$message({
                        type: 'error',
                        message: "请输入至少一科分数"
                    });
                    this.tableData = [];
                }

            },
            getRes(){
              if(this.formData.chinese > 100 && this.formData.math > 125 && this.formData.english > 100){
                  if(this.formData.wl > 90){
                      return "电气工程及其自动化专业";
                  }
                  return "计算机科学与技术专业";
              }else if(this.formData.math > 124 ){
                  return "软件工程";
              }else if(this.formData.chinese > 129){
                  return "汉语言文学专业";
              }else if(this.formData.chinese > 119 && this.formData.math > 129){
                  return "金融学、会计学专业";
              }else if( this.formData.english > 129){
                  return "英语专业";
              }else{
                  let jobList  = [
                      "土木工程专业","电子信息工程学","电气工程及其自动化专业","软件工程专业","自动化专业","通信工程专业","会计学专业"
                  ];

                  var element = jobList[Math.floor((Math.random()*jobList.length))];

                  return element;
              }



            },
            async initData(){
                try{
                    this.city = await cityGuess();
                    const countData = await getResturantsCount();
                    if (countData.status == 1) {
                        this.count = countData.count;
                    }else{
                        throw new Error('获取数据失败');
                    }
                    this.getResturants();
                }catch(err){
                    console.log('获取数据失败', err);
                }
            },
            async getCategory(){
                try{
                    const categories = await foodCategory();
                    categories.forEach(item => {
                        if (item.sub_categories.length) {
                            const addnew = {
                                value: item.name,
                                label: item.name,
                                children: []
                            }
                            item.sub_categories.forEach((subitem, index) => {
                                if (index == 0) {
                                    return
                                }
                                addnew.children.push({
                                    value: subitem.name,
                                    label: subitem.name,
                                })
                            })
                            this.categoryOptions.push(addnew)
                        }
                    })
                }catch(err){
                    console.log('获取商铺种类失败', err);
                }
            },
            async getResturants(){
                const {latitude, longitude} = this.city;
                const restaurants = await getResturants({latitude, longitude, offset: this.offset, limit: this.limit});
                this.tableData = [];
                restaurants.forEach(item => {
                    const tableData = {};
                    tableData.name = item.name;
                    tableData.address = item.address;
                    tableData.description = item.description;
                    tableData.id = item.id;
                    tableData.phone = item.phone;
                    tableData.rating = item.rating;
                    tableData.recent_order_num = item.recent_order_num;
                    tableData.category = item.category;
                    tableData.image_path = item.image_path;
                    this.tableData.push(tableData);
                })
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.offset = (val - 1)*this.limit;
                this.getResturants()
            },
            handleEdit(index, row) {
                this.selectTable = row;
                this.address.address = row.address;
                this.dialogFormVisible = true;
                this.selectedCategory = row.category.split('/');
                if (!this.categoryOptions.length) {
                    this.getCategory();
                }
            },
            addFood(index, row){
                this.$router.push({ path: 'addGoods', query: { restaurant_id: row.id }})
            },
            async handleDelete(index, row) {
                try{
                    const res = await deleteResturant(row.id);
                    if (res.status == 1) {
                        this.$message({
                            type: 'success',
                            message: '删除店铺成功'
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
                    console.log('删除店铺失败')
                }
            },
            async querySearchAsync(queryString, cb) {
                if (queryString) {
                    try{
                        const cityList = await searchplace(this.city.id, queryString);
                        if (cityList instanceof Array) {
                            cityList.map(item => {
                                item.value = item.address;
                                return item;
                            })
                            cb(cityList)
                        }
                    }catch(err){
                        console.log(err)
                    }
                }
            },
            addressSelect(vale){
                const {address, latitude, longitude} = vale;
                this.address = {address, latitude, longitude};
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
            async updateShop(){
                this.dialogFormVisible = false;
                try{
                    Object.assign(this.selectTable, this.address);
                    this.selectTable.category = this.selectedCategory.join('/');
                    const res = await updateResturant(this.selectTable)
                    if (res.status == 1) {
                        this.$message({
                            type: 'success',
                            message: '更新店铺信息成功'
                        });
                        this.getResturants();
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
