<template>
	<div class="courseslist">
		<div @click="go2CourseDetail(c.id)" class="c-item" v-for="c in courseslist">
			<img class="c-img" :src="c.course_image_url" />
			<div class="course">
				<div class="title">{{c.course_name}}</div>
				<div class="c-describe">
					{{c.course_desc}}
				</div>
			</div>
			<div class="c-label">
			</div>
		</div>
		<div class="getMode">
			<el-button type="primary" v-if="!noMoreData" @click="getModeCourse" icon="el-icon-arrow-down" :loading="loadMoreCourse">加载更多</el-button>
			<div v-if="noMoreData" style="font-size: 14px;color: #93999f;">暂无更多数据了...</div>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'courseList',
		props: ['searchText'],
		data() {
			return {
				loadMoreCourse: false,
				courseslist: [],
				page: 1,
				rows: 10,
				noMoreData: false,
				key: ""
			}
		},
		created() {
			this.getCourses('', 'search');
		},
    computed: {
    	host() {
    		return this.$store.state.host 
    	}
    },
		methods: {
			getCourses(key, type) { //获取数据(页数，每页多少条，关键词)
				if (type === 'search') {
					this.page = 1;
					this.noMoreData = false;
				}
				this.key = key;
				this.$axios.get("/v1/courses?key=" + this.key + "&page_no=" + this.page + "&page_size=" + this.rows).then((
					response) => {
					let message = response.data;
					if (type === 'search') {
						this.courseslist = [];
					}
					message.items.forEach((c) => {
						this.courseslist.push(c)
					})
					if (message.items.length === 0) {
						this.noMoreData = true;
					}
					this.loadMoreCourse = false;
				}, (response) => {
					this.$message.error('获取课程失败');
				});
			},
			getModeCourse() {
				this.page++;
				this.loadMoreCourse = true;
				this.getCourses(this.key, 'more')
			},
			go2CourseDetail(courseId) {
				this.$router.push({
					name: "courseDetail",
					query: {
						courseId: courseId
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	.courseslist {
		overflow: hidden;
		margin-bottom: 20px;
		position: relative;
		padding-bottom: 60px;

		.c-item {
			width: 260px;
			height: 272px;
			background: #fff;
			display: inline-block;
			position: relative;
			cursor: pointer;
			border-radius: 4px;
			-webkit-box-shadow: 0 8px 16px rgba(7, 17, 27, .2);
			-moz-box-shadow: 0 8px 16px rgba(7, 17, 27, .2);
			box-shadow: 0 8px 16px rgba(7, 17, 27, .2);
			overflow: hidden;
			margin: 20px 20px 30px;
		}

		.c-item:hover .course {
			top: 50px;
		}

		.c-img {
			width: 100%;
			height: 142px;
			border-radius: 4px;
			border-top-left-radius: 5px;
			border-top-right-radius: 5px;
		}

		.c-label {
			position: absolute;
			bottom: 0;
			left: 0;
			right: 0;
			height: 29px;
			background-color: #FFFFFF;
			padding: 15px 28px 0;
			overflow: hidden;
			color: #324057;
		}

		.course {
			background-color: #FFFFFF;
			position: absolute;
			left: 0;
			right: 0;
			top: 110px;
			height: 150px;
			transition: top .5s;
			padding: 30px 28px 0;
			text-align: left;
		}

		.title {
			max-height: 48px;
			font-size: 16px;
			line-height: 24px;
			color: #07111b;
			font-weight: 700;
			margin-bottom: 6px;
			padding-bottom: 15px;
			border-bottom: 1px solid #d9dde1;
		}

		.c-describe {
			color: #93999f;
			font-size: 12px;
			line-height: 20px;
		}

		.getMode {
			position: absolute;
			bottom: 0;
			left: 50%;
			width: 120px;
			margin-left: -60px;
		}

		.el-button {
			width: 120px;
			text-align: center;
		}
	}
</style>
