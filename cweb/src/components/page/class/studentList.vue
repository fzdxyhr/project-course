<template>
	<div class="class-student-index">
		<div class="body-content" v-for="item in studentList" v-if="studentList.length > 0">
			<el-card>
				<div style="padding-top: 15px;padding-bottom: 5px;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
					<div style="display: inline-block;">{{item.user_name}}</div>
					<div style="display: inline-block;margin-left: 23px;width: 80px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" :title="item.account">{{item.account}}</div>
				</div>
			</el-card>
			<div v-if="item.sign === 1" class="sign-content"><i class="el-icon-check" style="margin-right: 5px;"></i>已签到</div>
			<div v-if="item.sign === 0" class="not-sign-content"><i class="el-icon-close" style="margin-right: 5px;"></i>未签到</div>
		</div>
		<div style="text-align: center;" v-if="studentList.length === 0">
			暂无学生信息
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				studentList: [],
				classId: ""
			};
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.classId = this.rjDialogParams().data.id;
			}
			this.go2Query();
		},
		methods: {
			go2Query() {
				this.$axios.get('/v1/classes/' + this.classId + "/students").then((response) => {
					console.log(response)
					this.studentList = response.data;
				}, (response) => {
					this.$message.error('获取班级失败');
				});
			}
		},
	}
</script>

<style lang="scss">
	.class-student-index {
		.body-content {
			position: relative;
			width: 185px;
			font-size: 16px;
			display: inline-block;
			margin-left: 15px;
			margin-top: 10px;
		}

		.sign-content {
			// transform:rotate(10deg);
			width: 75px;
			height: 20px;
			color: white;
			background-color: #67c23a;
			position: absolute;
			right: 0;
			top: 0;
		}

		.not-sign-content {
			width: 75px;
			height: 20px;
			color: white;
			background-color: #e6a23c;
			position: absolute;
			right: 0;
			top: 0;
		}
	}
</style>
