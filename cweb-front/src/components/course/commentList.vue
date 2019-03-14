<template>
	<div class="comment-index">
		<div class="button-top">
			<el-button type="primary" @click="go2Comment">我要评价</el-button>
		</div>
		<div class="fixclear"></div>
		<div v-if="comments.length > 0" style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="content">
					<img style="float: left;border-radius: 50%;" :src="item.photo_path" :alt="item.user_name" width="80px" height="80px" />
					<div style="float: right;">
						<el-rate v-model="item.score" disabled>
						</el-rate>
					</div>
					<div style="float: left;margin-left: 50px;">
						<div class="user">{{item.user_name}}</div>
						<div class="comment-content">{{item.comment_content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<span>{{item.create_time}}</span>
				</div>
			</el-card>
		</div>
		<div v-if="comments.length == 0" style="margin-top: 10%;text-align: center;color: #93999f;">
			暂无评价~~~
		</div>
		<rjDialog></rjDialog>
	</div>
</template>

<script>
	import rjDialog from '@components/common/dialog.vue'
	import commentAdd from '@components/course/commentAdd.vue'

	export default {
		components: {
			rjDialog,
			commentAdd
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
				this.$axios.get("/v1/course_comments?course_id=" + this.courseId).then((response) => {
          loading.close();
					let message = response.data;
					this.comments = response.data.items;
				}, (response) => {
          loading.close();
					this.$message.error('获取评论失败');
				});
			},
			go2Comment() {
				this.rjDialog.
				title("撰写《" + this.courseName + "》的评价").
				width("600px").
				top("2%").
				closeOnClickModal(false).
				currentView(commentAdd, {
					courseId: this.courseId
				}).
				showClose(true).
				then((opt) => {
					this.go2Query();
				}).show();
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
