<template>
	<div class="question-add-index">
		<div class="star-content">
			<div>
				<span style="color: red;">*</span>
				<span>标题</span><span v-if="showStarError" style="color: red;margin-left: 15px;"><i class="el-icon-info"></i>请填写问题标题</span>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;">
				<el-input type="text" placeholder="请输入问题标题" v-model="questionTitle">
				</el-input>
			</div>
		</div>
		<div class="comment-content">
			<div>
				<span>内容</span>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;">
				<el-input type="textarea" :rows="12" placeholder="请输入问题内容" v-model="questionContent">
				</el-input>
			</div>
		</div>
		<div class="fixclear"></div>
		<div class="button-footer">
			<el-button type="primary" @click="go2Submit">提交</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				questionTitle: "",
				questionContent: "",
				showContentError: false,
				showStarError: false
			}
		},
		mounted() {

		},
		methods: {
			go2Submit() {
				if (!this.questionTitle) {
					this.showStarError = true;
					return
				} else {
					this.showStarError = false;
				}
				this.closeRjDialog();
			},
			doCancel(){
				this.closeRjDialog();
			}
		}
	}
</script>

<style lang="scss">
	.question-add-index {
		.el-rate__text {
			margin-left: 15px !important;
		}

		.button-footer {
			margin-top: 30px;
			text-align: right;
		}

		.comment-content {
			margin-top: 20px;
		}
	}
</style>
