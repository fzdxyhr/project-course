<template>
	<div class="course-detail-aside-index">
		<div class="start-study-button">
			<el-button v-if="!isStudy" style="width: 300px;" type="primary" @click="go2Start">开始学习</el-button>
			<el-button v-if="isStudy" style="width: 300px;" type="primary" @click="go2Continue">继续学习</el-button>
		</div>
		<div class="course-prompt">
			课程提示
			<div class="prompt-content">
				{{promptContent}}
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'courseAside',
		props: {
			promptContent: {
				type: String
			},
			courseId: {
				type: Number
			},
			course: {
				type: Object
			}
		},
		components: {},
		data() {
			return {
				isStudy: false
			}
		},
		mounted() {
			this.getStudy();
		},
		methods: {
			getStudy() {
				this.$axios.get('/v1/courses/' + this.courseId + '/study').then((response) => {
					if (response.data) {
						this.isStudy = true;
					} else {
						this.isStudy = false;
					}
				}, (response) => {
					this.$message.error('获取课程学习情况失败');
				});
			},
			go2Start() {
				const user = localStorage.getItem("WEBFRONT_USER");
				if (!user) { //未登录，跳转到登录页面
					this.$router.push({
						name: "login"
					})
				}
				if (this.course.course_chapter_vos && this.course.course_chapter_vos.length === 0) {
					this.$message.error('暂无章节信息,请先添加章节');
					return;
				}
				//没学习过的，点击开始学习，学习的是第一章
				let firstChapter = this.course.course_chapter_vos[0].course_chapter_vos[0];
        console.log("firstChapter",firstChapter)
				let type = "";
				if (firstChapter.chapter_type === 1 || firstChapter.chapter_type === 2) {
					type = 'file';
				}
				if (firstChapter.chapter_type === 3) {
					type = 'vedio';
				}
				this.$axios.put('/v1/courses/' + this.courseId + '/study?chapter_id=' + firstChapter.id).then((response) => {
					if (response.status === 200) {
						this.$router.push({
							name: "filePlay",
							query: {
								type: type,
								path: encodeURIComponent(firstChapter.chapter_file_path)
							}
						})
					}
				}, (response) => {
					this.$message.error('开始学习失败');
				});
			},
			go2Continue() {
				const user = localStorage.getItem("WEBFRONT_USER");
				if (!user) { //未登录，跳转到登录页面
					this.$router.push({
						name: "login"
					})
				} else {
					let firstChapter = {};
					this.course.course_chapter_vos.forEach((item) => {
            item.course_chapter_vos.forEach((it) => {
              if (it.recent_study) {
              	firstChapter = it;
              }
            })
					})
					let type = "";
					if (firstChapter.chapter_type === 1 || firstChapter.chapter_type === 2) {
						type = 'file';
					}
					if (firstChapter.chapter_type === 3) {
						type = 'vedio';
					}
					this.$router.push({
						name: "filePlay",
						query: {
							type: type,
							path: encodeURIComponent(firstChapter.chapter_file_path)
						}
					})
				}

			}
		},
	}
</script>

<style lang="scss">
	.course-detail-aside-index {
		.start-study-button {
			padding: 8px 10px 0 90px;
			font-size: 20px;
		}

		.course-prompt {
			padding: 20px 10px 0 90px;
			font-size: 20px;

			.prompt-content {
				font-size: 13px;
				color: #333;
				line-height: 24px;
				background-color: #EEEEEE;
				padding: 20px;
				margin-top: 30px;
			}

			.prompt-content:before {
				content: '';
				display: block;
				width: 0px;
				margin-top: -40px;
				margin-bottom: 20px;
				border-bottom: 20px solid #EEEEEE;
				border-top: 0px solid #fff;
				border-left: 20px solid #fff;
				border-right: 20px solid #fff;
			}
		}
	}
</style>
