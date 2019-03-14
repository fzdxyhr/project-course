<template>
	<div class="question-index">
		<div class="button-top">
			<el-button type="primary" @click="go2Question">我要提问</el-button>
		</div>
		<div class="fixclear"></div>
		<div style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="content">
					<img style="float: left;border-radius: 50%;" :src="item.photo_path" :alt="item.user_name" width="80px" height="80px" />
					<div style="float: left;margin-left: 50px;">
						<div class="title">{{item.user_name}}</div>
						<div class="comment-content">{{item.question_content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<div style="float: left;margin-left: 17%;"><span style="margin-right: 5px;" v-text="answerTotal"></span>回答</div>
					<div style="float: right;">{{item.create_time}}</div>
				</div>
			</el-card>
		</div>
		<div v-if="comments.length == 0" style="margin-top: 10%;text-align: center;color: #93999f;">
			暂无提问~~~
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
		props: {
			courseName: {
				type: String
			},
			courseId: {
				type: Number
			},
		},
		data() {
			return {
				answerTotal: 0,
				comments: []
			};
		},
		mounted() {
			this.go2Query();
		},
		methods: {
			go2Query() {
         const loading = this.$loading({
        	lock: true,
        	text: '上传中...',
        	spinner: 'el-icon-loading',
        	background: 'rgba(0, 0, 0, 0.7)'
        });
				this.$axios.get("/v1/course_questions?course_id=" + this.courseId).then((response) => {
          loading.close();
					let message = response.data;
					this.comments = message.items;
				}, (response) => {
          loading.close();
					this.$message.error('获取问题失败');
				});
			},
			go2Question() {
				this.rjDialog.
				title("我要提问").
				width("600px").
				top("2%").
				closeOnClickModal(false).
				currentView(questionAdd, {courseId: this.courseId}).
				sizeSelf("question-add-index").
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
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
