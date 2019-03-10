<template>
	<div class="comment-add-index">
		<div class="star-content">
			<div>
				<span style="color: red;">*</span>
				<span>总评</span><span v-if="showStarError" style="color: red;margin-left: 15px;"><i class="el-icon-info"></i>请填写总评</span>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;">
				<el-rate v-model="star" show-text :texts="texts">
				</el-rate>
			</div>
		</div>
		<div class="comment-content">
			<div>
				<span style="color: red;">*</span>
				<span>评价内容</span>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;">
				<el-input type="textarea" :rows="12" placeholder="可以从课程内容组织、授课方式的角度评价课程，或者分享课程给你带来了什么收获和启发" v-model="commentContent">
				</el-input>
			</div>
			<div style="margin-top: 5px;">
				<div v-if="showContentError" style="color: red;float: left;"><i class="el-icon-info"></i>请填写评价内容</div>
				<div v-if="commentContent.length < 5" style="color: #999;float: right;">至少输入5个字</div>
				<div v-if="commentContent.length >= 5 && commentContent.length <= 500" style="color: #999;float: right;">输入5-500个字</div>
				<div v-if="commentContent.length > 500" style="color: red;float: right;">超过{{commentContent.length - 500}}个字</div>
			</div>
		</div>
		<div class="fixclear"></div>
		<div class="button-footer">
			<el-button type="primary" @click="go2Submit">提交</el-button>
			<el-button @click="doCancel">取消</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				star: 0,
				texts: ["非常失望", "比较失望,不推荐", "马马虎虎,不太推荐", "课程很不错,有待提高", "棒！强烈推荐"],
				commentContent: "",
				showContentError: false,
				showStarError: false,
				courseId:""
			}
		},
		mounted() {
			if(this.rjDialogParams().courseId) {
				this.courseId = this.rjDialogParams().courseId;
			}
		},
		methods: {
			go2Submit() {
				if (this.star == 0) {
					this.showStarError = true;
					return
				} else {
					this.showStarError = false;
				}
				if (!this.commentContent) {
					this.showContentError = true;
					return
				} else {
					this.showContentError = false;
				}
				if (this.commentContent.length > 500) {
					return
				}
				let req = {};
				req.score = this.star;
				req.comment_content = this.commentContent;
				req.course_id = this.courseId;
				this.$axios.post("/v1/course_comments",req).then((response) => {
					let message = response.data;
					this.closeRjDialog();
				}, (response) => {
					this.$message.error('发表评论失败');
				});
			},
			doCancel(){
				this.closeRjDialog();
			}
		}
	}
</script>

<style lang="scss">
	.comment-add-index {
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
