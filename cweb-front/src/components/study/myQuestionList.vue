<template>
	<div class="my-question-index">
		<div class="fixclear"></div>
		<div v-if="questions.length > 0" style="margin-top: 20px;" v-for="item in questions">
			<el-card class="box-card" style="height: 150px;">
				<div class="question-content">
					<img @click="go2CourseDetail(item.course_id)" style="float: left;cursor: pointer;" :src="item.course_path" :alt="item.course_name"
					 width="150px" height="90px" />
					<div style="float: left;margin-left: 50px;">
						<div @click="go2CourseDetail(item.course_id)" class="title">{{item.course_name}}</div>
						<div class="comment-content">{{item.question_content}}</div>
					</div>
				</div>
				<div class="fixclear"></div>
				<div class="page-content">
					<div style="float: left;margin-left: 23%;">
						<span style="margin-right: 5px;" v-text="item.answer_vos.length"></span>
            <span style="color: #66b1ff;cursor: pointer;" @click="go2Answer(item)">回答</span>
					</div>
					<div style="float: right;">{{item.create_time}}</div>
				</div>
			</el-card>
		</div>
		<div v-if="questions.length == 0" class="no-data-content">
			暂时没有提问~~~ <el-button style="margin-left: 10px;" size="small" type="primary" @click="go2Study">去学习</el-button>
		</div>
    <rjDialog></rjDialog>
	</div>
</template>

<script>
  import rjDialog from '@components/common/dialog.vue'
  import answerList from '@components/course/answerList.vue'
  
	export default {
		components: {
      rjDialog,
      answerList
    },
		data() {
			return {
				answerTotal: 0,
				page_no: 1,
				page_size: 10000,
				questions: []
			};
		},
		mounted() {
			this.go2Query();
		},
		methods: {
      go2Answer(item){
      	this.rjDialog.
      	title("查看回复").
      	width("700px").
      	top("").
      	closeOnClickModal(false).
      	currentView(answerList, {
      		questionId: item.id,
          type:"mystudy"
      	}).
      	sizeSelf("answer-list-index").
      	showClose(true).
      	then((opt) => {
      		this.go2Query();
      	}).show();
      },
			go2Query() {
				const loading = this.$loading({
					lock: true,
					text: '加载中...',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				this.$axios.get("/v1/mine/questions?page_no=" + this.page_no + "&page_size=" + this.page_size).then((
					response) => {
					loading.close();
					let message = response.data;
					this.questions = message.items;
				}, (response) => {
          loading.close
					this.$message.error('获取我的回答失败');
				});
			},
			go2Study() {
				this.$router.push({
					name: "courseManage"
				});
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
	.my-question-index {
		.button-top {
			float: right;
		}

		.no-data-content {
			text-align: center;
			padding-top: 26%;
			color: #787d82;
			font-size: 14px;
		}

		.question-content {}

		.page-content {
			margin-top: 10px;
		}

		.title {
			font-size: 14px;
			color: #1c1f21;
			font-weight: 700;
		}

		.title:hover {
			color: red;
			cursor: pointer
		}

		.comment-content {
			margin-top: 25px;
			font-size: 14px;
		}
	}
</style>
