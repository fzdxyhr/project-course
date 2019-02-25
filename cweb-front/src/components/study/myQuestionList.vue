<template>
	<div class="my-question-index">
		<div class="fixclear"></div>
		<div style="margin-top: 20px;" v-for="item in comments">
			<el-card class="box-card" style="height: 150px;">
				<div class="content">
					<img style="float: left;" :src="item.photo_path" :alt="item.course_name" width="80px" height="80px" />
					<div style="float: left;margin-left: 50px;">
						<div class="title">{{item.course_name}}</div>
						<div class="comment-content">{{item.content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<div style="float: left;margin-left: 15%;">
						<span style="margin-right: 5px;" v-text="answerTotal"></span>回答
					</div>
					<div style="float: right;">{{item.time}}</div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		components: {},
		data() {
			return {
				answerTotal: 0,
				comments: [{
						course_name: "课程标题1",
						photo_path: "https://www.baidu.com/img/bd_logo1.png?where=super",
						content: "sssssssssss",
						time: "6秒前"
					},
					{
						course_name: "课程标题2",
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
		}
	}
</script>

<style lang="scss">
	.my-question-index {
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
