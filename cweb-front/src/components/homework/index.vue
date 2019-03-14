<template>
	<div class="homework-index">
		<div class="homework-content">
			<el-card class="box-card" v-for="item in homeworks">
				<div slot="header" class="clearfix">
					<span>{{item.homework_title}}</span>
					<el-button style="float: right; padding: 3px 0 3px 10px" type="text" @click="go2Submit(item.id)">上传作业</el-button>
					<el-button style="float: right; padding: 3px 0" type="text" @click="go2Download(item.homework_file_path)">下载作业</el-button>
				</div>
				<div class="text item">
					{{item.homework_desc}}
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
				this.$axios.get("/v1/homeworks/front?key=" + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then(
					(
						response) => {
						let message = response.data;
						this.homeworks = message.items;
					}, (response) => {
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
			width: 500px;
			height: 200px;
			margin: 20px;
		}

		.text {
			font-size: 14px;
		}

		.item {
			margin-bottom: 18px;
		}
	}
</style>
