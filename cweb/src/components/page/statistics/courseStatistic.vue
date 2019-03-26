<template>
	<div class="course-statistic-index">
		<el-card class="box-card">
			<div slot="header" class="clearfix" style="padding: 4px 0;">
        <span style="font-size: 14px;font-weight: 600;">班级学生学习情况统计</span>
        <div style="float: right; margin: -8px 0">
          <span>班级: </span>
          <el-select size="small" v-model="chassId" placeholder="请选择班级" @change="selectChange">
          	<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
          	</el-option>
          </el-select>
        </div>
			</div>
			<div>
				<v-chart v-if="courseData.length > 0" style="width: 100%;" :options="courseOptions"></v-chart>
				<div v-if="courseData.length == 0" style="text-align: center;height: 280px;">暂无课程统计数据</div>
			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				classes: [],
				chassId: "",
				courseData: [],
				courseOptions: {
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['学习课程数']
					},
					xAxis: {
						type: 'category',
						data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
						axisLabel: {
							interval: 0,
							rotate: 40
						},
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						name: "学习课程数",
						data: [820, 932, 901, 934, 1290, 1330, 1320],
						type: 'line'
					}]
				}
			}
		},
		mounted() {
			this.findClasses();
		},
		methods: {
      selectChange(){
        this.initCourseData();
      },
			findClasses() {
				this.$axios.get("/v1/classes?page_no=1&page_size=10000").then((response) => {
					this.classes = [];
					response.data.items.forEach((item) => {
						let temp = {};
						temp.label = item.class_name;
						temp.value = item.id;
						this.classes.push(temp);
					})
          this.chassId = response.data.items[0].id;
          this.initCourseData();
				}, (response) => {
					this.$message.error('获取班级失败');
				});
			},
			initCourseData() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get('/v1/statistic/course?class_id=' + this.chassId).then((response) => {
					loading.close()
					this.courseData = response.data;
					if (response.data) {
						let xLabelData = [];
						let seriesData = [];
						response.data.forEach((item) => {
							xLabelData.push(item.user_name);
							seriesData.push(item.study_course);
						});
						this.courseOptions.xAxis.data = xLabelData;
						this.courseOptions.series[0].data = seriesData;
					}
				}, (response) => {
					loading.close()
					this.$message.error('获取学习课程数失败');
				});
			}
		},

	}
</script>

<style lang="scss">
	.course-statistic-index {
		.el-card__header {
			padding: 10px 20px !important;
		}
	}
</style>
