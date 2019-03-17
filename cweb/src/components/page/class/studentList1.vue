<template>
	<div class="class-student-index">
		<div class="body-content" v-for="item in studentList" v-if="studentList.length > 0">
			<el-card>
				<div style="padding-top: 15px;padding-bottom: 5px;width: 150px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">
					<div style="display: inline-block;">{{item.user_name}}</div>
					<div style="display: inline-block;margin-left: 23px;width: 80px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;" :title="item.account">{{item.account}}</div>
				</div>
			</el-card>
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
				homeworkId: ""
			};
		},
		mounted() {
			if (this.rjDialogParams().data) {
				this.homeworkId = this.rjDialogParams().data.id;
			}
			this.go2Query();
		},
		methods: {
			go2Query() {
				this.$axios.get('/v1/homeworks/' + this.homeworkId + "/users").then((response) => {
					this.studentList = response.data;
				}, (response) => {
					this.$message.error('获取学生作业失败');
				});
			}
		},
	}
</script>

<style lang="scss">
	.class-student-index {
		.body-content {
			position: relative;
			width: 225px;
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
