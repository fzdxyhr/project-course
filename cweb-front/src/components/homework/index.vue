<template>
	<div class="homework-index">
		<div class="homework-content">
			<el-card class="box-card" v-for="item in homeworks">
				<div slot="header" class="clearfix">
					<span>{{item.homework_title}}</span>
					<el-button style="float: right; padding: 3px 0" type="text" @click="go2Download(item.homework_file_path)">下载作业</el-button>
				</div>
				<div class="text item">
					{{item.homework_desc}}
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				key:"",
				homeworks: [],
				page_no:1,
				page_size:10
			}
		},
		mounted(){
			this.go2Query();
		},
		methods: {
			go2Query() {
				this.$axios.get("/v1/homeworks?key=" + this.key + "&page_no=" + this.page_no + "&page_size=" + this.page_size).then((
					response) => {
					let message = response.data;
					this.homeworks = message.items;
				}, (response) => {
					this.$message.error('获取作业失败');
				});
			},
			go2Download(path){
				window.open(path);
			}
		}
	}
</script>

<style lang="scss">
	.homework-index {
		.homework-content {
			margin-left: 10px;
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
