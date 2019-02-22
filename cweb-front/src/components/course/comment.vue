<template>
	<div class="comment-index">
		<div class="button-top">
			<el-button type="primary" @click="go2Comment">我要评价</el-button>
		</div>
		<div class="fixclear"></div>
		<div style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="content">
					<img style="float: left;" :src="item.photo_path" :alt="item.user_name" width="80px" height="80px" />
					<div style="float: left;margin-left: 50px;">
						<div class="user">{{item.user_name}}</div>
						<div class="comment-content">{{item.content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<span>{{item.time}}</span>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	import crDialog from '@components/common/dialog'

	export default {
		components: {
			crDialog
		},
		data() {
			return {
				comments: [{
						user_name: "我是谁",
						photo_path: "https://www.baidu.com/img/bd_logo1.png?where=super",
						content: "sssssssssss",
						time: "6秒前"
					},
					{
						user_name: "谁是我",
						photo_path: "https://www.baidu.com/img/bd_logo1.png?where=super",
						content: "bbbbbbbbbbbbbb",
						time: "8秒前"
					}
				]
			};
		},
		methods: {
			go2Query() {
				this.$http.get("../../../static/testData/courses.json?searchStr=" + this.searchText + "&page=" + this.page +
					"&size=" + this.rows).then((response) => {
					let message = response.data;

				}, (response) => {
					this.$message.error('获取评论失败');
				});
			},
			go2Comment() {
				this.crDialog.
				title("导入学生").
				width("600px").
				top("2%").
				currentView(UploadImportData, {}).
				showClose(true).
				then((opt) => {}).show();
			}
		}
	}
</script>

<style lang="scss">
	.comment-index {
		.button-top {
			float: right;
		}

		.content {}

		.page-content {
			text-align: right;
		}

		.user {
			font-size: 14px;
			color: #1c1f21;
			font-weight: 700;
		}
    .comment-content {
      margin-top: 30px;
      font-size: 14px;
    }
	}
</style>
