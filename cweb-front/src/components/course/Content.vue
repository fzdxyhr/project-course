<template>
	<div class="course-detail">
		<div class="title-background" :style="{'background-image':'url(http://localhost:8085/v1/courses/images/download?relative_path='+(course.course_image_url&&course.course_image_url)+')'}">
			<div class="c-title">
				{{course.course_name}}
			</div>
		</div>
		<el-row>
			<el-col :span="16">
				<el-tabs v-model="activeName" style="margin-top: 10px;">
					<el-tab-pane label="基本信息" name="first">
						<div class="c-describe">
							简介：{{course.course_desc}}
						</div>
						<el-collapse>
							<el-collapse-item v-for="(chapter,i) in course.course_chapter_vos" :title="('第'+(i+1)+'章 ')+chapter.chapter_name"
							 :name="i">
								<div style="margin-left: 10px;font-size: 12px;color: #545c63;padding-top: -10px;">{{chapter.chapter_desc}}</div>
								<div class="articles-title">
									<div class="child-item" v-for="item in chapter.course_chapter_vos" @click="go2Start(item)">
										<div class="chapter-title">
											<i v-if="item.chapter_type === 1 " class="course-icon-doc"></i>
											<i v-if="item.chapter_type === 2 " class="course-icon-ppt"></i>
											<i v-if="item.chapter_type === 3 " class="course-icon-vedio"></i>
											{{item.chapter_name}}
										</div>
										<div class="chapter-content">
											<el-button style="border-radius: 0%;" v-if="!isStudy" type="danger" @click="go2Start(item)" size="small">开始学习</el-button>
											<el-button v-if="isStudy" type="danger" size="small" @click="go2Start(item)">继续学习</el-button>
										</div>
									</div>
									<!-- <span class="getMore-articles-title" title="获取更多">
										获取更多<i class="el-icon-caret-bottom"></i>
									</span> -->
								</div>
							</el-collapse-item>
						</el-collapse>
					</el-tab-pane>
					<el-tab-pane label="评价" name="second">
						<comment-content></comment-content>
					</el-tab-pane>
					<el-tab-pane label="提问" name="third">
						<questionList></questionList>
					</el-tab-pane>
				</el-tabs>
			</el-col>
			<el-col :span="8">
				<course-aside :prompt-content="course.course_tip" :courseId="this.$route.query.courseId"></course-aside>
			</el-col>
		</el-row>
	</div>
</template>
<script>
	import CourseAside from './Aside.vue'
	import commentContent from '@components/course/commentList.vue'
	import questionList from '@components/course/questionList.vue'

	export default {
		name: 'courseContehtmlnt',
		components: {
			'course-aside': CourseAside,
			commentContent,
			questionList
		},
		data() {
			return {
				course: {},
				isStudy: false,
				courseId: "",
				activeName: "first"
			};
		},
		mounted() {
			this.courseId = this.$route.query.courseId;
			this.getCourse();
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
			getCourse() {
				this.$axios.get('/v1/courses/' + this.courseId).then((response) => {
					this.course = response.data;
				}, (response) => {
					this.$message.error('获取课程详情失败');
				});
			},
			go2Start(item) {
				let type = "";
				if (item.chapter_type === 1 || item.chapter_type === 2) {
					type = 'file';
				}
				if (item.chapter_type === 3) {
					type = 'vedio';
				}
				if (!this.isStudy) {
					this.$axios.put('/v1/courses/' + this.courseId + '/study').then((response) => {
						if (response.status === 200) {
							this.$router.push({
								name: "filePlay",
								query: {
									type: type,
									path: encodeURIComponent(item.chapter_file_path)
								}
							})
						}
					}, (response) => {
						this.$message.error('开始学习失败');
					});
				} else {
					this.$router.push({
						name: "filePlay",
						query: {
							type: type,
							path: encodeURIComponent(item.chapter_file_path)
						}
					})
				}

			},
			go2Continue() {
				this.$router.push({
					name: "filePlay"
				})
			}
		}
	}
</script>
<style lang="scss">
	.course-detail {
		margin: 0 auto;
		min-height: 800px;

		.chapter-content {
			float: right;
			margin-right: 30px;
			display: none;
		}

		.child-item {
			margin-left: 20px;
			// padding-top: 10px;
			margin-top: 10px;
			padding-left: 15px;
			font-size: 14px;
			height: 48px;
			line-height: 48px;
			width: 92%;
		}

		.child-item:hover {
			background-color: rgba(233, 150, 122, 0.1);
			color: red;
			cursor: pointer;

			.chapter-content {
				display: inline-block;
			}
		}

		.chapter-title {
			display: inline-block;
		}

		.el-button--small {
			padding: 4px 15px;
		}

		.title-background {
			height: 200px;
			width: 100%;
			background-color: rgba(0, 0, 0, .4);
			background-repeat: no-repeat;
			background-size: cover;
			background-position: center;
		}

		.c-title {
			font-size: 48px;
			line-height: 137px;
			color: #FFFFFF;
		}

		.c-title,
		.el-row {
			max-width: 1200px;
			min-width: 800px;
			margin: 0 auto;
		}

		.c-describe {
			font-size: 14px;
			font-weight: 400;
			color: #888;
			padding: 30px 0 30px;
			line-height: 30px;
		}

		div.el-collapse {
			border-left-width: 0;
			border-right-width: 0;
		}

		div.el-collapse-item__header {
			padding: 5px 10px 0px 10px;
			font-weight: 700;
			font-size: 16px;
		}

		.course .labels {
			margin: 10px 0 5px;
		}

		.course .labels span {
			display: inline-block;
			font-size: 13px;
			border: 1px solid #20A0FF;
			padding: 0 8px;
			margin-right: 5px;
			border-radius: 40px;
			cursor: pointer;
			color: #1D8CE0;
		}

		.articles-title a {
			display: block;
			font-size: 16px;
			width: 100%;
			overflow: hidden;
			white-space: nowrap;
			text-overflow: ellipsis;
			margin: 3px 0;
		}

		.el-tabs__item {
			font-size: 16px !important;
			color: #000 !important;
			font-weight: 600 !important;
		}

		.articles-title a:hover {
			color: #888;
		}

		.getMore-articles-title {
			cursor: pointer;
			display: block;
			width: 70px;
			margin: 0 auto;
			font-size: 14px;
			transition: color ease .3s;
		}

		.getMore-articles-title:hover {
			color: #999;
		}

		.articles-title a:hover {
			color: red;
		}
	}
</style>
