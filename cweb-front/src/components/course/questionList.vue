<template>
	<div class="question-index">
		<div class="button-top">
			<el-button type="primary" @click="go2Question">我要提问</el-button>
		</div>
		<div class="fixclear"></div>
		<div style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="content">
					<img style="float: left;" :src="item.photo_path" :alt="item.user_name" width="80px" height="80px" />
					<div style="float: left;margin-left: 50px;">
						<div class="title">{{item.user_name}}</div>
						<div class="comment-content">{{item.content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<div style="float: left;margin-left: 17%;"><span style="margin-right: 5px;" v-text="answerTotal"></span>回答</div>
					<div style="float: right;">{{item.time}}</div>
				</div>
			</el-card>
		</div>
		<rjDialog></rjDialog>
	</div>
</template>

<script>
	import rjDialog from '@components/common/dialog.vue'
	import questionAdd from '@components/course/questionAdd.vue'

	export default {
		components: {
			rjDialog,
			questionAdd
		},
		data() {
			return {
				answerTotal:0,
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
			go2Question() {
				this.rjDialog.
				title("我要提问").
				width("600px").
				top("2%").
				closeOnClickModal(false).
				currentView(questionAdd, {}).
				sizeSelf("question-add-index").
				showClose(true).
				then((opt) => {}).show();
			}
		}
	}
</script>

<style lang="scss">
	.question-index {
		.button-top {
			float: right;
		}

		.content {}

		.page-content {
			margin-top: 10px;
		}

		.title {
			font-size: 14px;
			color: #1c1f21;
			font-weight: 700;
		}

		.title:hover {
			color: red;
			cursor: pointer
		}

		.comment-content {
			margin-top: 25px;
			font-size: 14px;
		}
	}
</style>
