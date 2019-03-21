<template>
	<div class="index-content">
		<div class="title-coursesList">
			<h2>精品课程</h2>
			<div class="courseSearch">
				<el-input type="text" placeholder="输入课程名查找" suffix-icon="el-icon-search" v-model="searchText">
				</el-input>
			</div>
			<swiper style="height: 40px;margin-top: 14px;float: right;" :options="swiperOption" ref="aaaa">
				<swiper-slide>最近听说会下雨啊。。。。。</swiper-slide>
				<swiper-slide>国内要闻，阿萨达所大所大所大所大所大所多</swiper-slide>
			</swiper>
			<div style="float: right;margin-top: 14px;margin-right: 10px;">
				资讯:
			</div>
		</div>
		<div class="course-labels">
			<el-row>
				<el-col :span="2">
					<div class="cl-title">
						标签:
					</div>
				</el-col>
				<el-col :span="22">
					<div class="cl-labels">
						<span :class="selectedId == ''?'label active':'label'" @click="go2Select('')">
							全部
						</span>
						<span :class="selectedId == tag.id?'label active':'label'" v-for="(tag,index) in tags" v-if="index<4" @click="go2Select(tag.id)">
							{{tag.tag_name}}
						</span>
						<el-button @click="btnShowMordLabel">{{labelMore.btnText}}<i :style="{transform: 'rotate('+labelMore.iconRotate+'deg)'}"
							 class="el-icon-caret-bottom"></i></el-button>
						<div class="cll-more" :class="{showMore:this.labelMore.state}">
							<span :class="selectedId == tag.id?'label active':'label'" v-for="(tag,index) in tags" v-if="index>=4" @click="go2Select(tag.id)">
								{{tag.tag_name}}
							</span>
						</div>
					</div>
				</el-col>
			</el-row>
		</div>
		<courses-list ref="course"></courses-list>
		<rjDialog></rjDialog>
	</div>
</template>
<script>
	import 'swiper/dist/css/swiper.css'
	import coursesList from './coursesList.vue'
	import rjDialog from '@components/common/dialog.vue'
	import signTip from '@components/sign/signTip.vue'
	import {
		swiper,
		swiperSlide
	} from 'vue-awesome-swiper'

	//主页走马灯下的内容
	export default {
		name: "home-content",
		components: {
			coursesList,
			signTip,
			rjDialog,
			swiper,
			swiperSlide
		},
		watch: {
			searchText(newValue, oldValue) {
				if (newValue != oldValue) {
					this.$refs.course.getCourses(this.searchText, this.selectedId, 'search');
				}
			}
		},
		data: () => {
			return {
				searchText: '',
				labelMore: {
					state: false,
					btnText: '更多',
					iconRotate: 0,
				},
				tags: [],
				selectedId: "",
				swiperOption: {
					direction: 'vertical',
					autoplay: true,
					loop : true
				}
			}
		},
		methods: {
			go2Select(id) {
				this.selectedId = id;
				this.$refs.course.getCourses(this.searchText, this.selectedId, 'search');
			},
			handleIconClick(ev) {
				console.log(ev);
			},
			btnShowMordLabel() {
				const l = this.labelMore;
				if (!l.state) {
					//未展开
					l.state = !l.state;
					l.btnText = '收起';
					l.iconRotate = 180;

				} else {
					//已展开
					l.state = !l.state;
					l.btnText = '更多';
					l.iconRotate = 0;
				}
			},
			getAllTags() {
				this.$axios.get('/v1/tags?page_no=1&page_size=10000000').then((response) => {
					this.tags = response.data.items;
				}, (response) => {
					this.$message.error('获取标签失败');
				});
			},
			getSignStatus() {
				this.$axios.get('/v1/signs/status').then((response) => {
					if (response.data === 0) {
						this.rjDialog.
						title("签到提示").
						width("500px").
						top("10%").
						closeOnClickModal(false).
						currentView(signTip, {}).
						showClose(true).
						then((opt) => {}).show();
					}
				}, (response) => {
					this.$message.error('获取签到状态失败');
				});
			},
			getSignTime() {
				this.$axios.get('/v1/signs/time').then((response) => {
					if (response.data) {
						let currentDateTime = new Date().getTime();
						let startTime = new Date(response.data.sign_start_time).getTime();
						let endTime = new Date(response.data.sign_end_time).getTime();
						if (currentDateTime >= startTime && currentDateTime <= endTime) {
							this.getSignStatus();
						}
					}
				}, (response) => {
					this.$message.error('获取签到状态失败');
				});
			}
		},
		created() {
			this.getAllTags();
			const user = JSON.parse(localStorage.getItem("WEBFRONT_USER"));
			if (user.role === 'student') {
				this.getSignTime();
			}
		}
	}
</script>
<style lang="scss">
	.index-content {
		height: 100%;
		overflow: auto;

		.title-coursesList {
			background-color: #edeff0;
			/*background: linear-gradient(#edeff0, #ffffff);*/
			// overflow: hidden;
			padding: 5px 10px;
			height: 46px;
		}

		.title-coursesList h2 {
			float: left;
			font-size: 20px;
			font-weight: 200;
			line-height: 46px;
		}

		.courseSearch {
			float: left;
			/*line-height: 60px;*/
			/*ie9下不行*/
			padding: 3px 40px;
		}

		.courseSearch .el-input__inner {
			border-radius: 40px;
			outline: none;
			height: 40px;
		}

		.course-labels {
			padding-top: 10px;
		}

		.cl-title {
			font-size: 16px;
			font-weight: 200;
			line-height: 27px;
			padding: 5px 5px 5px 15px;
		}

		.cl-labels {
			padding: 5px;
		}

		.cl-labels .label {
			display: inline-block;
			font-size: 13px;
			color: #324057;
			border: 1px solid #D3DCE6;
			padding: 4px 8px;
			border-radius: 40px;
			cursor: pointer;
			margin: 0 20px 20px 0;
		}

		.cl-labels .label:hover,
		.cl-labels .label.active {
			border-color: #20A0FF;
			color: #1D8CE0;
		}

		.cl-labels .el-button {
			border: none;
		}

		.cll-more {
			height: 0;
			overflow: hidden;
			transition: all ease .5s;
		}

		.cll-more.showMore {
			height: auto;
			overflow: hidden;
		}

		.cl-labels .el-icon-caret-bottom {
			transition: all ease .5s;
		}
	}
</style>
