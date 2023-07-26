<template>
    <div>
        <head-top></head-top>
        <el-row style="margin-top: 20px;">
  			<el-col :span="12" :offset="4">
		        <el-form :model="formData" :rules="rules" ref="formData" label-width="110px" class="demo-formData">
					<el-form-item label="手机号" prop="tel">
						<el-input v-model.number="formData.tel" maxLength="11"></el-input>
					</el-form-item>
                    <el-form-item label="用户名" prop="nickname">
						<el-input v-model="formData.nickname"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="password">
						<el-input v-model="formData.password"></el-input>
					</el-form-item>
					<el-form-item label="重复密码" prop="password2">
						<el-input v-model="formData.password2"></el-input>
					</el-form-item>
					<el-form-item class="button_submit">
			
						{ required: true, message: '请输入密码', trigger: 'blur' }
					],
					tel: [
						{ required: true, message: '请输入手机号' },
						{ type: 'number', message: '电话号码必须是数字' }
					],
				}
    		}
    	},
    	components: {
    		headTop,
    	},
    	mounted(){
    	},
    	methods: {

		    submitForm(formName) {
				this.$refs[formName].validate(async (valid) => {
					if (valid) {
						if(this.formData.password != this.formData.password2){
                            this.$message({
                            	type: 'error',
                            	message: "两次密码不一致"
                            });
                            return;
                        }
						try{
                            this.formData.password = atob(this.formData.password);
							let result = await register(this.formData);
							if (result.code == 200){
								this.$message({
					            	type: 'success',
					            	message: '注册成功'
					          	});
					          	this.$router.push('manage')
							}else{
								this.$message({
					            	type: 'error',
					            	message: result.msg
					          	});
							}
							console.log(result)
						}catch(err){
							console.log(err)
						}
					} else {
						this.$notify.error({
							title: '错误',
							message: '请检查输入是否正确',
							offset: 100
						});
						return false;
					}
				});
			},
		}
    }
</script>

<style lang="less">
	@import '../style/mixin';
	.button_submit{
		text-align: center;
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
	.el-table .info-row {
	    background: #c9e5f5;
	}

	.el-table .positive-row {
	    background: #e2f0e4;
	}
</style>
