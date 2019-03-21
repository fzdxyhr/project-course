<template>
	<div class="sign-index">
		<div class="class-conent" v-for="item in classes">
			<el-card :body-style="{ padding: '0px' }">
				<img width="300px" height="200px" src="../../../static/img/carousel.jpg" class="image">
				<div style="padding: 14px;">
					<span style="font-size: 18px;">{{item.class_name}}</span>
					<div class="bottom clearfix">
						<div class="time">{{ item.teacher_name }}</div>
						<el-button type="text" class="button" :disabled="item.can_sign === 0" @click="go2Sign(item.teacher_id)">签到</el-button>
					</div>
				</div>
			</el-card>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				classes: []
			}
		},
		mounted() {
			this.go2Query();
		},
		methods: {
			go2Query() {
				this.$axios.get('/v1/signs/classes').then((response) => {
					this.classes = response.data;
				}, (response) => {
					this.$message.error('获取数据失败');
				});
			},
			go2Sign(teacherId) {
				this.$axios.post('/v1/signs?teacher_id=' + teacherId).then((response) => {
					if (response.data) {
						this.$message.success('签到成功');
						this.$router.push({
							name: "courseManage"
						})
					} else {
						this.$message.error('签到失败');
					}
				}, (response) => {
					this.$message.error('签到失败');
				});
			}
		},
	}
</script>

<style lang="scss">
	.sign-index {
		.class-conent {
			height: 300px;
			width: 300px;
			// border: 1px solid #555555;
			display: inline-block;
			margin-left: 15px;
			margin-top: 10px;
		}

		.time {
			font-size: 13px;
			color: #999;
		}

		.bottom {
			margin-top: 13px;
			margin-bottom: 13px;
			line-height: 12px;
		}

		.button {
			padding: 0;
			float: right;
		}
	}
</style>
