<template>
	<div class="sign-statistic-index">
		<el-card class="box-card">
			<div slot="header" class="clearfix" style="padding: 4px 0;">
				<span style="font-size: 14px;font-weight: 600;">班级学生签到统计</span>
				<div style="float: right; margin: -8px 0">
					<span>班级: </span>
					<el-select size="small" v-model="chassId" placeholder="请选择班级" @change="selectChange">
						<el-option v-for="item in classes" :key="item.value" :label="item.label" :value="item.value">
						</el-option>
					</el-select>
				</div>
			</div>
			<div>
				<v-chart v-if="signData.length > 0" style="width: 100%;" :options="signOptions"></v-chart>
				<div v-if="signData.length == 0" style="text-align: center;height: 280px;">暂无签到数据</div>
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
				signData: [],
				signOptions: {
					tooltip: {
						trigger: 'axis'
					},
					legend: {
						data: ['签到人数', '未签到人数']
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
						},
						{
							name: "未签到人数",
							data: [820, 932, 901, 934, 1290, 1330, 1320],
							type: 'bar'
						}
					]
				},
			};
		},
		mounted() {
			this.findClasses();
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
					this.chassId = response.data.items[0].id;
					this.initSignData();
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
						let notSignData = [];
						response.data.forEach((item) => {
							xLabelData.push(item.date);
							seriesData.push(item.sign);
							notSignData.push(item.not_sign)
						});
						this.signOptions.xAxis.data = xLabelData;
						this.signOptions.series[0].data = seriesData;
						this.signOptions.series[1].data = notSignData;
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
	.sign-statistic-index {
		.el-card__header {
			padding: 10px 20px !important;
		}
	}
</style>
