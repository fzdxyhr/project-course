<template>
	<div class="my-comment-index">
		<div class="fixclear"></div>
		<div v-if="comments.length > 0" style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="comment-content-div">
					<img @click="go2CourseDetail(item.course_id)" style="float: left;cursor: pointer;" :src="item.course_path" :alt="item.course_name"
					 width="150px" height="90px" />
					<div style="float: left;margin-left: 50px;">
						<div class="user" @click="go2CourseDetail(item.course_id)">{{item.course_name}}</div>
						<div class="comment-content">{{item.comment_content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<span>{{item.create_time}}</span>
				</div>
			</el-card>
		</div>
		<div v-if="comments.length == 0" class="no-data-content">
			暂时没有评价~~~ <el-button style="margin-left: 10px;" size="small" type="primary" @click="go2Study">去学习</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		components: {},
		data() {
			return {
				page_no: 1,
				page_size: 10,
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
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get("/v1/mine/comments?page_no=" + this.page_no + "&page_size=" + this.page_size).then((
					response) => {
					loading.close();
					let message = response.data;
					this.comments = message.items;
				}, (response) => {
          loading.close
					this.$message.error('获取我的评论课程失败');
				});
			},
			go2CourseDetail(courseId) {
				this.$router.push({
					name: "courseDetail",
					query: {
						courseId: courseId
					}
				})
			},
			go2Study() {
				this.$router.push({
					name: "courseManage"
				});
			}
		}
	}
</script>

<style lang="scss">
	.my-comment-index {
		.button-top {
			float: right;
		}

		.no-data-content {
			text-align: center;
			padding-top: 26%;
			color: #787d82;
			font-size: 14px;
		}

		.comment-content-div {}

		.page-content {
			text-align: right;
		}

		.user {
			font-size: 14px;
			color: #1c1f21;
			font-weight: 700;
		}

		.user:hover {
			color: red;
			cursor: pointer
		}

		.comment-content {
			margin-top: 30px;
			font-size: 14px;
		}
	}
</style>
