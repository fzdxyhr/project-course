<template>
	<div class="answer-list-index">
		<div class="question-info">
			<div class="question-detail-content">
				{{question.question_content}}
			</div>
			<div class="question-detail-footer">
				<span v-text="question.user_name"></span>
				<span style="margin-left: 10px;" v-text="question.create_time"></span>
			</div>
		</div>
		<div class="question-answer-list">
			共{{question.answer_vos?question.answer_vos.length : 0}}回复
		</div>
		<div class="question-answer-list-content" v-for="item in question.answer_vos">
			<div>{{item.answer_content}}</div>
			<div style="text-align: right;">
				<span>{{item.user_name}}</span>
				<span style="background-color: #66b1ff;color: #FFFFFF;padding: 1px 5px;" v-if="item.role ==='teacher'">老师</span>
				<span style="margin-left: 15px;">{{item.create_time}}</span>
			</div>
		</div>
		<div class="input-content">
			<el-input :disabled="type === 'mystudy'" type="textarea" :rows="7" placeholder="请输入内容" v-model="answer.answer_content">
			</el-input>
			<div style="text-align: right;margin-top: 10px;">
				<el-button :disabled="type === 'mystudy'" size="small" type="primary" @click="addAnswer">发表回复</el-button>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				questionId: "",
				question: {
					question_content: "",
					user_name: "",
					create_time: ""
				},
				answer: {
					answer_content: "",
					question_id: ""
				},
        type:""
			};
		},
		mounted() {
			if (this.rjDialogParams().questionId) {
				this.questionId = this.rjDialogParams().questionId;
			}
      if (this.rjDialogParams().type) {
      	this.type = this.rjDialogParams().type;
      }
			this.findAnswers();
		},
		methods: {
			findAnswers() {
				this.$axios.get("/v1/course_questions/" + this.questionId).then((response) => {
					let message = response.data;
					this.question = message;
				}, (response) => {
					this.$message.error('获取问题回复失败');
				});
			},
			addAnswer() {
        this.answer.question_id = this.questionId;
				this.$axios.post("/v1/answers", this.answer).then((response) => {
					let message = response.data;
					this.closeRjDialog();
					this.$message.success('发表回复成功');
				}, (response) => {
					this.$message.error('发表回复失败');
				});
			}
		},
	}
</script>

<style lang="scss">
	.answer-list-index {
		.question-info {}

		.question-detail-content {}

		.question-detail-footer {
			text-align: right;
		}

		.question-answer-list {
			padding: 30px 0 10px 0;
			border-bottom: 1px solid #eee;
		}

		.question-answer-list-content {
			padding: 20px 0 10px 0;
			border-bottom: 1px solid #eee;
		}

		.input-content {
			height: 200px;
			margin-top: 10px;
		}
	}
</style>
