<template>
	<div class="homework-index">
		<div class="homework-content">
			<el-card class="box-card" v-for="item in homeworks">
				<div slot="header" class="clearfix">
					<span>{{item.homework_title}}</span>
					<el-button v-if="!item.submit_homework_file_path" style="float: right; padding: 3px 0 3px 10px" type="text" @click="go2Submit(item.id)">上传作业</el-button>
          <el-button v-if="item.submit_homework_file_path" :disabled="item.score" style="float: right; padding: 3px 0 3px 10px" type="text" @click="go2Update(item)">修改上传作业</el-button>
					<el-button style="float: right; padding: 3px 0" type="text" @click="go2Download(item.homework_file_path)">下载作业</el-button>
				</div>
				<div class="text item">
					{{item.homework_desc}}
				</div>
        <div v-if="item.score" style="float: right;position: absolute;right: 0;bottom: 0;margin-bottom: 15px;margin-right: 15px;">
        	评分: {{item.score}}
        </div>
        <div v-if="!item.score" style="float: right;position: absolute;right: 0;bottom: 0;margin-bottom: 15px;margin-right: 15px;">
        	暂无评分,请提交作业
        </div>
			</el-card>
		</div>
		<rjDialog></rjDialog>
	</div>
</template>

<script>
	import rjDialog from '@components/common/dialog.vue'
	import homeworkSubmit from '@components/homework/homeworkSubmit.vue'
	
	export default {
		components: {
			rjDialog,
			homeworkSubmit
		},
		data() {
			return {
				key: "",
				homeworks: [],
				page_no: 1,
				page_size: 10
			}
		},
		mounted() {
			this.go2Query();
		},
		methods: {
			go2Query() {
        const loading = this.$loading({
        	lock: true,
        	text: '加载中...',
        	spinner: 'el-icon-loading',
        	background: 'rgba(0, 0, 0, 0.7)'
        });
				this.$axios.get("/v1/homeworks/front?key=" + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then(
					(response) => {
            loading.close();
						let message = response.data;
						this.homeworks = message.items;
					}, (response) => {
            loading.close();
						this.$message.error('获取作业失败');
					});
			},
			go2Download(path) {
				window.open(path);
			},
			go2Submit(id){
				this.rjDialog.
				title("上传作业").
				width("600px").
				top("").
				currentView(homeworkSubmit, {
					id: id
				}).
				closeOnClickModal(false).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
			},
      go2Update(item){
      	this.rjDialog.
      	title("修改上传作业").
      	width("600px").
      	top("").
      	currentView(homeworkSubmit, {
      		data: item,
          id: item.id
      	}).
      	closeOnClickModal(false).
      	showClose(true).
      	then((opt) => {
      		this.go2Query();
      	}).show();
      }
		}
	}
</script>

<style lang="scss">
	.homework-index {
		margin: auto 5%;

		.homework-content {
			margin-left: 20px;
		}

		.box-card {
			display: inline-block;
      position: relative;
			width: 500px;
			height: 200px;
			margin: 20px;
      
      .el-card__body {
        height: 120px;
      }
		}

		.text {
			font-size: 14px;
		}

		.item {
			margin-bottom: 18px;
		}
	}
</style>
