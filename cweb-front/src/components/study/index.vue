<template>
	<div class="study-index">
		<div class="side-bar">
			<div class="user-pic-bg">
				<img class="img" src="https://img1.mukewang.com/5333a0600001f9ed02000200-140-140.jpg" alt="">
			</div>
			<div style="text-align: center;margin-top: 30px;">
				<span style="font-size: 15px;font-weight: 600;">{{userName}}</span>
			</div>
			<div style="text-align: center;margin-top: 10px;">
				<span style="font-size: 14px;">学习时长:{{studyTime}}h</span>
				<span style="font-size: 14px;margin-left: 15px;">参与课程:{{courseTotal}}</span>
			</div>
			<div class="button-info">
				<ul class="ul-body">
					<li>
						<a @click="changeTab('myCourse')" href="javascript:" :class="selectCurrent == 'myCourse'?'active' :''">
							<i class="el-icon-tickets"></i>
							<span>我的课程</span>
						</a>
					</li>
					<li>
						<a href="javascript:" @click="changeTab('myQuestion')" :class="selectCurrent == 'myQuestion'?'active' :''">
							<i class="el-icon-question"></i>
							<span>我的提问</span>
						</a>
					</li>
					<li>
						<a href="javascript:" @click="changeTab('myComment')" :class="selectCurrent == 'myComment'?'active' :''">
							<i class="el-icon-message"></i>
							<span>我的评价</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="content-body">
			<myCourseList v-if="selectCurrent == 'myCourse'"></myCourseList>
			<myCommentList v-if="selectCurrent == 'myComment'"></myCommentList>
			<myQuestionList v-if="selectCurrent == 'myQuestion'"></myQuestionList>
		</div>
	</div>
</template>

<script>
	import myCourseList from './myCourseList.vue'
	import myCommentList from './myCommentList.vue'
	import myQuestionList from './myQuestionList.vue'

	export default {
		components: {
			myCourseList,
			myCommentList,
			myQuestionList
		},
		data() {
			return {
				userName: "测试",
				courseTotal: 10,
				studyTime: 20,
				tabPosition: "left",
				selectCurrent: "myCourse"
			}
		},
		methods: {
			changeTab(key) {
				this.selectCurrent = key;
			}
		},
	}
</script>

<style lang="scss">
	.study-index {
		position: relative;
		width: 1200px;
		margin: 0 auto;

		.side-bar {
			position: absolute;
			left: 24px;
			top: 0px;
			width: 200px;
			text-align: center;

			.user-pic-bg {
				border: 4px solid #fff;
				box-shadow: 0 4px 8px 0 rgba(7, 17, 27, .1);
				width: 140px;
				height: 140px;
				position: relative;
				margin-left: 26px;
				border-radius: 50%;
				background: #fff;
				top: 24px;

				.img {
					text-align: center;
					width: 140px;
					height: 140px;
					border-radius: 50%;
				}
			}

			.button-info {
				margin-top: 20px;

				.ul-body a.active {
					color: #fff;
					background: rgba(240, 20, 20, .8);
					border-radius: 8px 0 0 8px;
				}

				.ul-body a.active i {
					color: #fff;
				}

				.ul-body a {
					position: relative;
					display: block;
					height: 48px;
					font-size: 16px;
					line-height: 48px;
					text-align: center;
					color: #787d82;
					padding-left: 54px;
					text-align: left;
				}

				.ul-body li:first-child {
					margin-top: 30px;
				}

				.ul-body li {
					height: 48px;
					width: 160px;
					margin: 0 auto 11px;
				}

				.ul-body a i {
					position: absolute;
					left: 20px;
					top: 0;
					font-size: 20px;
					color: #93999f;
					line-height: 48px;
				}

				.ul-body a.active:after {
					display: block;
					content: "";
					position: absolute;
					bottom: 0;
					right: 0;
					width: 0;
					height: 0;
					border-top: 24px solid #f8fafc;
					border-bottom: 24px solid #f8fafc;
					border-left: 15px solid transparent;
				}
			}
		}

		.content-body {
			margin-left: 300px;
			// overflow: auto;
		}
	}
</style>
