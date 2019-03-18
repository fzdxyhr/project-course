<template>
	<div class="homework-student-index">
		<div class="body-content" v-for="item in studentList" v-if="studentList.length > 0">
			<el-card>
				<div style="">
					<div style="width: 100px;float: left;">{{item.user_name.substring(0,10)}}<span v-if="item.user_name.length > 10">...</span></div>
					<div style="margin-left: 5%;float: right;color: #409eff;cursor: pointer;" @click="go2Score(item)">评分</div>
					<div style="text-align: right;padding-top: 20px;font-size: 12px;clear: both;" :title="item.submit_time">{{item.submit_time}}</div>
				</div>
			</el-card>
		</div>
		<div style="text-align: center;" v-if="studentList.length === 0">
			暂无学生提交作业
		</div>
    <rjDialog></rjDialog>
	</div>
</template>

<script>
  import rjDialog from '@components/common/dialog.vue'
  import score from '@components/page/homework/score.vue'
  
	export default {
    components: {
    	rjDialog,
      score
    },
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
			},
      go2Score(row){
        this.rjDialog.
        title("评分").
        width("300px").
        top("").
        currentView(score, {
        	data: row
        }).
        closeOnClickModal(false).
        showClose(true).
        then((opt) => {
        }).show();
      }
		},
	}
</script>

<style lang="scss">
	.homework-student-index {
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
