<template>
	<div class="homework-statistic-index">
		<el-card class="box-card">
			<div slot="header" class="clearfix" style="padding: 4px 0;">
				<span>作业提交情况统计</span>
				<div style="float: right; margin: -8px 0">
					<span>教师: </span>
					<el-select size="small" :disabled="isTeacher" v-model="teacherId" placeholder="请选择教师" @change="selectChange">
						<el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</div>
			</div>
			<div>
				<v-chart v-if="homeworkData.length > 0" style="width: 100%;" :options="homeworkOptions"></v-chart>
				<div v-if="homeworkData.length == 0" style="text-align: center;height: 280px;">暂无作业提交数据</div>
			</div>
		</el-card>
	</div>
</template>

<script>
	export default {
		data() {
			return {
        isTeacher:false,
				teacherId: "",
				homeworkData: [],
				options: [],
				homeworkOptions: {
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['作业提交人数']
					},
					xAxis: {
						type: 'category',
						data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
						// 						axisLabel: {
						// 							interval: 0,
						// 							rotate: 40
						// 						},
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						name: "作业提交人数",
						data: [820, 932, 901, 934, 1290, 1330, 1320],
						type: 'line'
					}]
				}
			}
		},
		mounted() {
      const user = JSON.parse(localStorage.getItem("USER"));
      if (user && user.role=='teacher') {
        this.isTeacher = true;
      	this.teacherId = user.id;
      }
			this.findTeachers();
		},
		methods: {
      selectChange(){
        this.initHomeworkData();
      },
			findTeachers() {
				this.$axios.get("/v1/users?page_no=1&page_size=10000&role=teacher").then((response) => {
					let message = response.data;
					this.options = [];
					message.items.forEach((item) => {
						let temp = {};
						temp.value = item.id;
						temp.label = item.user_name;
						this.options.push(temp);
					});
					this.initHomeworkData();
				}, (response) => {
					this.$message.error('获取老师失败');
				});
			},
			initHomeworkData() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get('/v1/statistic/homework?teacher_id=' + this.teacherId).then((response) => {
					loading.close()
					this.homeworkData = response.data;
					if (response.data) {
						let xLabelData = [];
						let seriesData = [];
						response.data.forEach((item) => {
							xLabelData.push(item.title);
							seriesData.push(item.user);
						});
						this.homeworkOptions.xAxis.data = xLabelData;
						this.homeworkOptions.series[0].data = seriesData;
					}
				}, (response) => {
					loading.close()
					this.$message.error('获取考勤报表信息失败');
				});
			},
		},

	}
</script>

<style lang="scss">
	.homework-statistic-index {
		.el-card__header {
			padding: 10px 20px !important;
		}
	}
</style>
