<template>
	<div class="statistic-index">
		<div class="sign-statistic-content">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span>学生签到报表</span>
					<el-select class="clearfix" style="float: right; padding: 3px 0" size="small" v-model="chassId" placeholder="请选择班级"
					 @change="selectChange">
						<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
					<!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
				</div>
				<div>
					<v-chart v-if="signData.length > 0" style="width: 100%;" :options="signOptions"></v-chart>
					<div v-if="signData.length == 0" style="text-align: center;height: 280px;">暂无签到数据</div>
				</div>
			</el-card>
		</div>
		<div class="homework-statistic-content">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<el-select size="small" v-model="teacherId" placeholder="请选择教师" @change="selectChange">
						<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
					<!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
				</div>
				<div>
					<v-chart v-if="homeworkData.length > 0" style="width: 100%;" :options="homeworkOptions"></v-chart>
					<div v-if="homeworkData.length == 0" style="text-align: center;height: 280px;">暂无作业提交数据</div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				classes: [],
				chassId: "",
				teacherId: "",
				signData: [],
				homeworkData: [],
				signOptions: {
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['签到人数']
					},
					xAxis: {
						type: 'category',
						axisLabel: {
							interval: 0,
							rotate: 40
						},
						data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
					},
					yAxis: {
						type: 'value'
					},
					series: [{
						name: "签到人数",
						data: [820, 932, 901, 934, 1290, 1330, 1320],
						type: 'bar'
					}]
				},
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
						axisLabel: {
							interval: 0,
							rotate: 40
						},
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
			this.initSignData();
			this.findClasses();
			this.initHomeworkData();
		},
		methods: {
			selectChange() {
				this.initSignData();
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
				}, (response) => {
					this.$message.error('获取班级失败');
				});
			},
			initSignData() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get('/v1/statistic/sign?class_id=' + this.chassId).then((response) => {
					loading.close()
					this.signData = response.data;
					if (response.data) {
						let xLabelData = [];
						let seriesData = [];
						response.data.forEach((item) => {
							xLabelData.push(item.date);
							seriesData.push(item.sign);
						});
						this.signOptions.xAxis.data = xLabelData;
						this.signOptions.series[0].data = seriesData;
					}
				}, (response) => {
					loading.close()
					this.$message.error('获取考勤报表信息失败');
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
			}
		},

	}
</script>

<style lang="scss">
	.statistic-index {
		padding: 10px;
		overflow: auto;

		.sign-statistic-content {}

		.homework-statistic-content {
			margin-top: 15px;
		}

		.el-card__header {
			padding: 10px 20px !important;
		}
	}
</style>
